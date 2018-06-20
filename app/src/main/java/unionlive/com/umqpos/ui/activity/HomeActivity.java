package unionlive.com.umqpos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.landicorp.android.eptapi.utils.SystemInfomation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryPlatformResponse;
import unionlive.com.umqpos.entitys.out_waimai.ShopInfoResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.activity.coupon_act.CouponExchangeActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.SPUtils;

import static unionlive.com.umqpos.content.Fields.loginsessionId;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/12 16:46
 * @Author's GitHub  https://github.com/PrettyAnt
 * @describe ${TODO}
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView    mIv_back;
    private TextView     mTv_title;
    private LinearLayout mLl_home_coupons;
    private LinearLayout mLl_home_wm;
    private String       mListStr;
    private LinearLayout mLl_home_setting;
    private TextView     mTv_home_bottom_info;
    //    private LinearLayout mLl_waimai_state;
    private String       mClientId;
    //    TitleInfomation titleInfo = new TitleInfomation();
    private LinearLayout mLl_title_channel;
    private String       mOrderId;
    private ImageView    mIv_waimai;
    private ImageView    mIv_coupon;
    private ImageView    mIv_about;
    private String       mDeviceModel;
    private String       mShopName;
    private String       cusInfo;
    //    private boolean is_Open_WaiMai_Flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        //        initPlatForm();
        initData();//商铺基本信息
        initEvent();
        initWaimaiLoading();
        // 应用未启动, 个推 service已经被唤醒,显示该时间段内离线消息
        //        if (DemoApplication.payloadData != null) {
        //            tLogView.append(DemoApplication.payloadData);
        //        }
        // cpu 架构
        //        System.out.println("cpu arch = " + (Build.VERSION.SDK_INT < 21 ? Build.CPU_ABI : Build.SUPPORTED_ABIS[0]));

        // 检查 so 是否存在
        //        File file = new File(this.getApplicationInfo().nativeLibraryDir + File.separator + "libgetuiext2.so");
        //        System.out.println("file.exists()" + file.exists());
        //        PlayMusic.initSound(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 初始化外卖的登录状态
     */
    private void initWaimaiLoading() {
        Fields.is_start_waimai_act = false;//判断是否启动外卖的activity
        loadWaimai();//登录外卖
    }


    private void initView() {
//        Button bt_home_wm = (Button) findViewById(R.id.bt_home_wm);
//        Button bt_home_coupon = (Button) findViewById(R.id.bt_home_coupon);
//        Button bt_home_setting = (Button) findViewById(R.id.bt_home_setting);
//        Drawable drawable= ContextCompat.getDrawable(this, R.drawable.home_wm_open);
//        drawable.setBounds(0,0,50,50);
//        bt_home_wm.setCompoundDrawables(null,drawable,null,null);
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mLl_home_coupons = (LinearLayout) findViewById(R.id.ll_home_coupons);
        //外卖
        mLl_home_wm = (LinearLayout) findViewById(R.id.ll_home_wm);
        mLl_home_setting = (LinearLayout) findViewById(R.id.ll_home_setting);//设置
        mIv_waimai = (ImageView) findViewById(R.id.iv_waimai);
        mIv_coupon = (ImageView) findViewById(R.id.iv_coupon);
        mIv_about = (ImageView) findViewById(R.id.iv_about);
        //底部信息
        mTv_home_bottom_info = (TextView) findViewById(R.id.tv_home_bottom_info);

        mIv_back.setVisibility(View.GONE);//返回按钮
        mTv_title.setText("首页");//标题栏


    }

    private void initEvent() {
        mLl_home_wm.setOnClickListener(this);
        mLl_home_coupons.setOnClickListener(this);
        mLl_home_setting.setOnClickListener(this);
        if (TextUtils.equals(Fields.WAIMAI, "1")) {//外卖的功能开启了
            mLl_home_wm.setClickable(true);
            mIv_waimai.setBackgroundResource(R.drawable.home_wm_open);//设置外卖功能开启的图标
        } else {
            mLl_home_wm.setClickable(false);//设置不可点击
            mIv_waimai.setBackgroundResource(R.drawable.home_wm_close);//设置外卖功能关闭的图标
        }
    }

    private void initData() {
        //查询店铺的基本信息
        String encryptStr = InPutJsonData.T101(this, Fields.loginsessionId, "");
        NetUtil.waiMaiNetWorkUtil(encryptStr, Fields.channelId, ShopInfoResponse.class, this, Fields.ORDER_TYPE_ABOUT_SHOPINFO);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home_coupons:
                //验券界面的标记，根据当前所在的界面，返回到对应的界面
                Fields.current_ui_flag = "yq_01";//验券
                Intent couponIntent = new Intent(this, CouponExchangeActivity.class);
                startActivity(couponIntent);
                break;
            case R.id.ll_home_wm://点击外卖获取外卖平台
                Fields.is_start_waimai_act = true;//判断是否启动外卖的activity

//                loadWaimai();//登录外卖 fixme 绕开登录
                Intent intent = new Intent(this, OrderFoodActivity.class);
                startActivity(intent);
                Fields.is_start_waimai_act = false;
                break;
            case R.id.ll_home_setting:
                Fields.current_ui_flag = "set_01";//设置界面的标记
                Intent intent1 = new Intent(this, AboutActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;
        }
    }

    private void loadWaimai() {
        if (Fields.is_Open_WaiMai_Flag) {//外卖功能是否开启
            //如果开启了，就查询开通了哪些外卖平台
            //                    InitDataHelper.getTitleMsg(this);//初始化外卖平台的信息 完成后开始登录 // FIXME: 2017/2/6   String t106 = InPutJsonData.T106(context, Fields.loginsessionId);
            queryWaimaiPlatform();
        } else {
            //如果未开启，则强制开启---初始化时，先用默认的方式开启
            mClientId = Fields.clientId;
            try {
                String t109 = InPutJsonData.T109(loginsessionId, mClientId, "1", "1");//默认方式开启外卖
                NetUtil.waiMaiNetWorkUtil(t109, Fields.channelId, OpenWmResponse.class, this, Fields.ORDER_TYPE_OPEN_WM);
            } catch (Exception e) {
                MyToast.show(this, "您需要重新登录", true);
                this.finish();
            }
        }
    }

    /**
     * 查询外卖平台
     */
    private void queryWaimaiPlatform() {
        String t106 = InPutJsonData.T106(this, Fields.loginsessionId);
        NetUtil.waiMaiNetWorkUtil(t106, Fields.channelId,
                QueryPlatformResponse.class, this, Fields.TITLE_ORDER_TYPE_PLATFORM);//查询有哪些外卖平台
    }

    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 1500) {
            MyToast.show(this, "再按一次退出", false);

            mExitTime = System.currentTimeMillis();
        } else {
            if (Fields.is_Open_WaiMai_Flag) {//如果外卖开启了，弹出对话框
                CancleOrderDialog.isExitDialog(this, "退出后将不能接外卖订单");
            } else {//否则直接退出
                CancleOrderDialog.isExitDialog(this,"确定退出?");
            }

        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMainEvent(CoubonsDecoderEvent event) {
        //根据返回码判断是否成功开启外卖，如果成功开启---->>查询外卖平台
        if (event.getOrderType() == Fields.ORDER_TYPE_OPEN_WM) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000"://返回码为0000是默认登录
                    Fields.is_Open_WaiMai_Flag = true;//外卖业务已经开启
                    //                    QueryPlatform();
                    //                    InitDataHelper.getTitleMsg(this);
                    queryWaimaiPlatform();
                    break;
                case "6090"://未登录的返回码
                    finish();
                    break;

                case "6091"://提示有其他终端--->>强制开启外卖平台
                    CancleOrderDialog.showIsLoadWaimaiDialog(openWmResponse.header.returnMessage, this);
                    break;
                default://错误对话框
                    CancleOrderDialog.isNetErrorDialog(this, openWmResponse.header.returnMessage
                            + openWmResponse.header.returnCode, null);
                    break;
            }
        }

        ///******************************************开启外卖平台*******************************************/
        if (event.getOrderType() == Fields.ORDER_TYPE_OPEN_INIT_WM) {//初始化外卖信息,有其他终端，则继续调用此方法
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000"://返回码为0000,表示可以直接登录外卖--->>>查询开通了哪些外卖平台
                    Fields.is_Open_WaiMai_Flag = true;//外卖业务已经开启
                    //外卖平台的报文   商户外卖平台查询----得到的结果在Baseactivity中
                    //                    QueryPlatform();
                    //                    InitDataHelper.getTitleMsg(this);
                    queryWaimaiPlatform();
                    break;
                case "6091"://提示有其他终端
                    CancleOrderDialog.showIsLoadWaimaiDialog(openWmResponse.header.returnMessage, this);
                    break;
                default://错误对话框
                    CancleOrderDialog.isNetErrorDialog(this, openWmResponse.header.returnMessage
                            + openWmResponse.header.returnCode, null);
                    break;
            }
        }
        //        初始化外卖平台，默认设置平台为上线状态，并设置为自动接单
        if (event.getOrderType() == Fields.ORDER_TYPE_ABOUT_SHOPINFO) {//店铺信息
            ShopInfoResponse shopInfoResponse = (ShopInfoResponse) EventBus.getDefault()
                    .getStickyEvent(CoubonsDecoderEvent.class).getT();
            switch (shopInfoResponse.header.returnCode) {
                case "0000":
                    if (shopInfoResponse != null && shopInfoResponse.body != null) {
                        String shopId = (String) SPUtils.get(this, Fields.shopId, "");
                        mShopName = shopInfoResponse.body.shopName;
                        Fields.shopName = mShopName;
                        //设备型号
                        mDeviceModel = DevicesInfoUtil.getDeviceModel();
                        cusInfo = mShopName + "\n商户编号:" + shopId
                                + "\n" + "优麦圈服务热线\t4008-8848-20\n"
                                + "SN:" + SystemInfomation.getDeviceInfo().getSerialNo()
                                + "\t\t" + mDeviceModel;
                        SPUtils.put(this, "CUSINFO", cusInfo);
                        mTv_home_bottom_info.setText(cusInfo);
                    }
                    break;
                default://错误对话框
//                    try {
//                        CancleOrderDialog.isNetErrorDialog(this, shopInfoResponse.header.returnMessage
//                                + shopInfoResponse.header.returnCode, null);
//                    } catch (Exception e) {
//
//                    }

                    break;
            }
        }

    }
}
