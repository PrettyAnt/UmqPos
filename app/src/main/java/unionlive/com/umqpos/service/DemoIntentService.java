package unionlive.com.umqpos.service;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.GTMsgResponse;
import unionlive.com.umqpos.entitys.out_waimai.DetialResponse;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.OrderUnTreateListResponse;
import unionlive.com.umqpos.event.InitUiEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.LdPrintStatus;
import unionlive.com.umqpos.utils.LdPrintWaimai;
import unionlive.com.umqpos.utils.SoundPlayUtils;
import unionlive.com.umqpos.utils.TimeHelper;

import static unionlive.com.umqpos.pwd.DatagramSign.desAndMd5Decoder;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    private static final String TAG = "GetuiSdkDemo";
    private int pageSize=5;

    @Override
    public void onCreate() {
        super.onCreate();
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    /**
     * 为了观察透传数据变化.
     */
    private static int cnt;

    public DemoIntentService() {

    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid -> " + pid);
    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();

        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
//        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
//        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));

//        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg
//                + "\ncid = " + cid);
        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            System.out.println(payload);
            String data = new String(payload);
            // 测试消息为了观察数据变化
//            if (data.equals("收到一条透传测试消息")) {
//                data = data + "-" + cnt;
//                cnt++;
//            }
            //            System.out.println("收到透传"+data.toString());
            String decoderStr = desAndMd5Decoder(data,//
                    Fields.desAndMd5Decoder_padding, Fields.desAndMd5Decoder_macSeret,
                    Fields.desAndMd5Decoder_seret);//得到解密后Json数据
            //            System.out.println("data----------" + data);
            //将接收到的json转化为实体类
            GTMsgResponse gtMsgResponse = new Gson().fromJson(decoderStr, GTMsgResponse.class);
            System.out.println("-------------"+gtMsgResponse);
//            showDialog(context);
            //不论用户是否开启自动接单，都要将将实体类的消息发送到相应的界面，通知更新UI
//            EventBus.getDefault().postSticky(new GTMsgEvent(gtMsgResponse));
            //判断消息的类别
            switch (gtMsgResponse.msgType) {
                case "0"://新订单

                    //如果商户开启自动接单，则开始打印，否则不打印
                    if (Fields.is_Open_WaiMai_Flag) {
                        SoundPlayUtils.play(3);
                        /*&&TitleInfomation.printStatus==Printer.ERROR_NONE*/
                        if (TitleInfomation.allAutoOrder==0) {//自动接单状态
                            //调用订单查询方法，查看未处理订单
                            //订单查询上送的密文
                            //每个分页的条数为1,则有多少页就查询多少次订单
                            a:
                            if (TitleInfomation.dealOrderState == 0) {//当前空闲状态
                                TitleInfomation.dealOrderState = 1;
                                //                                Printer landiPrinter = Printer.getInstance();
                                LdPrintWaimai landiPrintWaimai = LdPrintWaimai.getInstance();
                                while (true) {
                                    //查询未处理订单
                                    String t201 = InPutJsonData.T201(context, Fields.loginsessionId, Fields.unTreate_order,
                                            "", pageSize, 0, TimeHelper.exceptDay(0), TimeHelper.exceptDay(0), "");
                                    OrderUnTreateListResponse unTreateListResponse =
                                            NetUtil.getData(t201, Fields.channelId, OrderUnTreateListResponse.class);
                                    switch (unTreateListResponse.header.returnCode) {
                                        case "0000":
                                            List<OrderUnTreateListResponse.BodyBean.OrdersBean> orders = unTreateListResponse.body.orders;
                                            if (orders.size() == 0) {
                                                TitleInfomation.dealOrderState = 0;
                                                break a;
                                            }
                                            for (int i = 0; i < orders.size(); i++) {
                                                InitDataHelper.getTitleMsg(context);//确定订单或者取消订单,都需要初始化标题一下
                                                InitDataHelper.flush(context);
                                                LdPrintStatus.startPrint();//确定每一笔订单之前，获取该订单的状态,如果没有打印纸，则弹出提示框
                                                //确定每一笔订单前都要先确认下打印机的状态，否则不确认
                                                if (TitleInfomation.printStatus == Printer.ERROR_BUSY
                                                        || TitleInfomation.printStatus == Printer.ERROR_NONE) {
                                                    sureReceiveOrder(context, landiPrintWaimai, orders.get(i).orderId);//确定接单

                                                } else {

//                                                    EventBus.getDefault().postSticky(new StartDialogEvent(0));
                                                    break a;
                                                }

                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            } else {
                                TitleInfomation.dealOrderState = 0;
                            }


                        } else {
                            InitDataHelper.getTitleMsg(context);//确定订单或者取消订单,都需要初始化标题一下
                            InitDataHelper.flush(context);
                        }
                    }


                    break;
                case "1"://订单状态更新
                    if (Fields.is_Open_WaiMai_Flag) {
                        InitDataHelper.getTitleMsg(context);//确定订单或者取消订单,都需要初始化标题一下
                        InitDataHelper.flush(context);
                        if (TitleInfomation.allAutoOrder==0
                                &&TitleInfomation.untreate > 0) {//如果当前是自动接单，并且未处理订单数量大于1,则打印未处理的订单
                            String t201 = InPutJsonData.T201(context, Fields.loginsessionId, Fields.unTreate_order,
                                    "", 0, 0, TimeHelper.exceptDay(0), TimeHelper.exceptDay(0), "");
                            NetUtil.GTNetWorkUtil(t201, Fields.channelId, OrderUnTreateListResponse.class,
                                    context, Fields.ORDER_TYPE_GET_ORDER_ID);
                        }
                    }

                    break;
                case "8"://下线消息--->>
                    Fields.is_Open_WaiMai_Flag = false;//当前状态的外卖为未登录状态
                    TitleInfomation.initTitleData();//初始化数据
                    EventBus.getDefault().postSticky(new InitUiEvent(0));//通知baseActivity刷新界面
                    EventBus.getDefault().postSticky(new IsFinishCurrUiEvent("wm_01",true,"已有其他终端开启外卖业务"));//判断是否销毁当前的界面
                    break;
            }
        }
    }


    private void sureReceiveOrder(Context context, LdPrintWaimai landiPrintWaimai,
                                  String orderId) {
        //确定订单
        String t301 = InPutJsonData.T301(context, Fields.loginsessionId,orderId, 0);
        OpenWmResponse openWmResponse = NetUtil.getData(t301, Fields.channelId, OpenWmResponse.class);
        switch (openWmResponse.header.returnCode) {
            case "0000":
                String t202 = InPutJsonData.T202(context, Fields.loginsessionId,orderId, "");
                DetialResponse detialResponse = NetUtil.getData(t202, Fields.channelId, DetialResponse.class);
                switch (detialResponse.header.returnCode) {
                    case "0000":
                        System.out.println("得到的订单详情:" + detialResponse.body.toString());
                        try {
                            landiPrintWaimai.setT(detialResponse);
                            landiPrintWaimai.start();
                        } catch (RequestException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }

                break;
            default:
                break;
        }
    }


    @Override
    public void onReceiveClientId(Context context, String clientid) {
//        SPUtils.put(context,Fields.clientId,clientid);
        Fields.clientId = clientid;

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        Log.d(TAG, "onReceiveOnlineState -> " + (online ? "online" : "offline"));
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
    }
}
