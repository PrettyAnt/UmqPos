package unionlive.com.umqpos.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.Timer;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.event.ExitEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.service.ApkDownService;
import unionlive.com.umqpos.utils.TimerTaskHelper;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/4 20:27
 * @describe $相应的对话框
 */

public class CancleOrderDialog {
    private static int     refuseOrCancleType = 0;
    public static  boolean isSave             = false;

    /**
     * @param context              上下文
     * @param loginsessionId       签到Id
     * @param orderId              订单编号
     * @param status               订单--》》拒绝？取消？---->订单的状态-->拒绝接单8  取消订单9
     * @param refuseOrCancleReason 拒绝取消的原因--------自定义原因
     * @param reasonCancle         取消的原因（数组）
     * @param t                    返回报文对应的实体类
     * @param orderType            判断返回消息的类型
     * @param <T>
     */
    public static <T> void RefuseReason(
            final String title,//标题
            final Context context,//上下文
            final String loginsessionId,//登录Id
            final String orderId,//订单编号
            final int status,//两种状态:未处理是拒绝接单,已处理是取消订单   拒绝接单8  取消订单9
            final String refuseOrCancleReason,//拒绝取消的原因--------自定义原因
            String[] reasonCancle,
            final Class<T> t,
            final int orderType) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setIcon(R.drawable.loading)
                .setSingleChoiceItems(reasonCancle, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        refuseOrCancleType = i+1;//取消订单的类型标号正好等于单选项的位置
                    }
                })
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isSave = true;
                        String t302 = InPutJsonData.T302(
                                context,
                                loginsessionId,
                                orderId, //订单编号
                                status,//订单状态
                                refuseOrCancleType, //取消的原因类型----------->选择对应的数字
                                refuseOrCancleReason,//自定义取消的原因
                                "");
                        NetUtil.waiMaiNetWorkUtil(t302, Fields.channelId, t, context, orderType);  // FIXME: 2017/1/4
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        isSave = false;
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * 强制登录
     * @param titleStr
     * @param context
     */
    public static void showIsLoadWaimaiDialog(String titleStr, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleStr)
                .setIcon(R.drawable.loading)
                .setPositiveButton("强制切换", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String data =Fields.clientId;
                        String t109 = InPutJsonData.T109(Fields.loginsessionId, data, "0", "1");//强制登录
                        NetUtil.waiMaiNetWorkUtil(t109, Fields.channelId,
                                OpenWmResponse.class, context, Fields.ORDER_TYPE_OPEN_INIT_WM);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     *
     * @param titleStr  对话框的标题
     * @param message   对话款的内容
     * @param downloadUrl  下载的地址
     * @param context  上下文
     * @param isMustUpdate  是否必须下载
     */
    public static void IsUpDateDialog(String titleStr,
                                      String message,
                                      final String downloadUrl,
                                      final Context context,
                                      final boolean isMustUpdate,
                                      final boolean isabout
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.warning)
                .setTitle(titleStr)
//                .setMessage(message)
                //点击确定的时候，不管是否强制下载，都在下载
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        //当前有新版本,
                        Intent intent = new Intent(context, ApkDownService.class);
                        intent.putExtra("url", downloadUrl);
                        context.startService(intent);
                        if (!isabout) {//如果是关于界面，不退出
                            CancleOrderDialog.directExit(context);
                        }
//                        EventBus.getDefault().postSticky(new SpalishStartEvent(true));//退出程序，开始下载
                    }
                })
                //点击取消的时候，如果是强制下载，则直接退出程序，如果不是强制下载，则直接返回当前界面
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if (!isabout) {//不是关于界面
                            if (isMustUpdate) {//如果返回的是强制更新
                                //如果是强制升级  并且取消的话，那么直接退出程序
                                CancleOrderDialog.directExit(context);//告诉之前的splash界面是退出还是进入下个界面

                            } else {
                                //如果不是强制升级  并且取消的话，那么直接进入到下一个界面
//                                EventBus.getDefault().postSticky(new SpalishStartEvent(false));
                            }
                        }
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * 退出的对话框
     * @param context
     */
    public static void isExitDialog(final Context context,String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.warning)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        directExit(context);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 是否结束当前的ui
     * @param context
     * @param orderFoodActivity
     */
    public static void isFinishDialog(final Context context, final Activity orderFoodActivity,String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.warning)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        if (orderFoodActivity != null) {
                            orderFoodActivity.finish();
                        }
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    /**
     * 是否结束当前的ui
     * @param context
     */
    public static void exitWaimaiDialog(final Context context, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.warning)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                        String exitLogin = InPutJsonData.T108(context, Fields.loginsessionId, "");
                        NetUtil.waiMaiNetWorkUtil(exitLogin, Fields.channelId, OpenWmResponse.class, context, Fields.EXIT_LOADING_WAIMAI);//调用退出登录接口
//                        orderFoodActivity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * 请求网络出现异常的对话框、loadingDialog其他情况也可用
     * @param context
     * @param title
     * @param loadingDialog
     */
    public static void isNetErrorDialog(final Context context, String title, final Dialog loadingDialog) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.mipmap.warning)
                .setTitle(title)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
//                        directExit(context);
                        if (loadingDialog!=null) {
                            WeiboDialogUtils.closeDialog(loadingDialog);
                        }
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }


    public static void  directExit(Context context) {
        if (Fields.is_Open_WaiMai_Flag) {//如果外卖登录了
            String exitLogin = InPutJsonData.T108(context, Fields.loginsessionId, "");
            NetUtil.waiMaiNetWorkUtil(exitLogin, Fields.channelId, OpenWmResponse.class,
                    context, Fields.EXIT_LOADING_HOME);//调用退出登录接口
        } else {
            EventBus.getDefault().postSticky(new ExitEvent(true));//直接退出了
            Timer timer = TimerTaskHelper.getTimer();
            timer.cancel();//停止计数器
        }
    }
}
