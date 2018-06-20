package unionlive.com.umqpos.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.landicorp.android.eptapi.device.Printer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.OrderTreateListResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryOrderNumResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryPlatformResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.ExitEvent;
import unionlive.com.umqpos.event.InitUiEvent;
import unionlive.com.umqpos.event.PrinterErrorEvent;
import unionlive.com.umqpos.event.StartDialogEvent;
import unionlive.com.umqpos.event.TitleStatus;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.service.DemoIntentService;
import unionlive.com.umqpos.service.DemoPushService;
import unionlive.com.umqpos.ui.activity.OrderFoodActivity;
import unionlive.com.umqpos.ui.dialog.DialogStyleActivity;
import unionlive.com.umqpos.ui.dialog.DialogStyleActivity2;
import unionlive.com.umqpos.utils.OrdersDataInfo;
import unionlive.com.umqpos.utils.TimeHelper;
import unionlive.com.umqpos.utils.TimerTaskHelper;

import static com.landicorp.android.eptapi.device.Printer.ERROR_HARDERR;
import static com.landicorp.android.eptapi.device.Printer.ERROR_OVERHEAT;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDED;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERJAM;

/**
 * @Author's GitHub  https://github.com/PrettyAnt
 */
public class BaseActivity extends AppCompatActivity {

    public    TranslateAnimation mAlphaAnimation;
    protected LinearLayout       mLl_waimai_state;
    private   LinearLayout       mRl_base_root;//这是根布局
    private   TextView           mTv_home_baidu;
    private   TextView           mTv_home_eleme;
    private   TextView           mTv_home_meituan;
    private   TextView           mTv_home_wechat;
    private   TextView           mTv_home_other;
    private   LinearLayout       mLl_title_channel;
    private   TextView           mTv_home_treate;
    private   TextView           mTv_home_untreate;
    private   LinearLayout       mLl_home_coupons;
    private   LinearLayout       mLl_home_wm;
    private   TextView           mTv_home_nearTime;
    private   TextView           mTv_home_autoState;
    private   TextView           mTv_home_cusInfo;
    private   TextView           mTv_home_cusPhone;
    private   TextView           mTv_home_cusAddress;
    private   TextView           mTv_home_net_state;
    private   LinearLayout       mLl_home_order_people;
    private   TextView           mTv_home_print_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        //        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initBaseView();
        bindGtService();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        //隐藏标题栏
        //        getSupportActionBar().hide();
        // 将系统自带的标题框隐藏
        //        requestWindowFeature(Window.FEATURE_NO_TITLE);
        EventBus.getDefault().register(this);//注册eventBus

        // 摇摆动画
        mAlphaAnimation = new TranslateAnimation(-10f, 10f, 0, 0);
        mAlphaAnimation.setDuration(12);
        mAlphaAnimation.setRepeatCount(6);
        mAlphaAnimation.setRepeatMode(Animation.REVERSE);
    }

    private void bindGtService() {
        PushManager.getInstance().initialize(this, DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this, DemoIntentService.class);
    }

    private void initBaseView() {
        mRl_base_root = (LinearLayout) findViewById(R.id.rl_base_root);//根布局
        mLl_waimai_state = (LinearLayout) findViewById(R.id.ll_waimai_state);//状态栏
        //百度外卖
        mTv_home_baidu = (TextView) findViewById(R.id.tv_home_baidu);
        //饿了吗外卖
        mTv_home_eleme = (TextView) findViewById(R.id.tv_home_eleme);
        //美团外卖
        mTv_home_meituan = (TextView) findViewById(R.id.tv_home_meituan);
        //微信外卖
        mTv_home_wechat = (TextView) findViewById(R.id.tv_home_wechat);
        //其他外卖
        mTv_home_other = (TextView) findViewById(R.id.tv_home_other);
        //各个外卖的平台
        mLl_title_channel = (LinearLayout) findViewById(R.id.ll_title_channel);
        //已处理
        mTv_home_treate = (TextView) findViewById(R.id.tv_home_order_treate);
        //未处理
        mTv_home_untreate = (TextView) findViewById(R.id.tv_home_order_untreate);
        //最近订单的时间
        mTv_home_nearTime = (TextView) findViewById(R.id.tv_home_nearTime);
        //接单的状态---自动接单
        mTv_home_autoState = (TextView) findViewById(R.id.tv_home_autoReceive);
        //客户的信息
        mTv_home_cusInfo = (TextView) findViewById(R.id.tv_home_cusInfo);
        //手机号
        //mTv_home_cusPhone = (TextView) findViewById(R.id.tv_home_cusPhone);
        //地址
        //mTv_home_cusAddress = (TextView) findViewById(R.id.tv_home_cusAddress);
        mTv_home_net_state = (TextView) findViewById(R.id.tv_home_net_state);
        mLl_home_order_people = (LinearLayout) findViewById(R.id.ll_home_order_people);
        mTv_home_print_status = (TextView) findViewById(R.id.tv_home_print_status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (TextUtils.equals(Fields.WAIMAI, "1")) {//外卖功能开启
            reFreshStatus();
        } else {
            mLl_waimai_state.setVisibility(View.GONE);
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        lp.addRule(RelativeLayout.BELOW, R.id.ll_waimai_state);
        if (null != mRl_base_root) {
            mRl_base_root.addView(view, lp);
        }
    }

    /**
     * 以下4个方法是EventBus需要配置的方法
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(CoubonsDecoderEvent event) {
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(CoubonsDecoderEvent event) {
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(CoubonsDecoderEvent event) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 接受子类传来的消息,退出程序
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onExitEvent(ExitEvent event) {
        if (event.isExit()) {
            Fields.is_Open_WaiMai_Flag = false;//设置外卖是否打开
            TitleInfomation.baiduBusinessStatus = 0;
            TitleInfomation.channelName_platform_baidu = "";
            TitleInfomation.channelName_platform_meituan = "";
            TitleInfomation.channelName_platform_eleme = "";
            TitleInfomation.channelName_platform_wechat = "";
            TitleInfomation.treate = 0;
            TitleInfomation.untreate = 0;
            TitleInfomation.nearorder = "--";
            TitleInfomation.personinfo = "--";//
            Timer timer = TimerTaskHelper.getTimer();
            timer.cancel();//停止计数器
            finish();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CoubonsDecoderEvent event) {
        //查询商户开通了哪些外卖平台
        if (event.getOrderType() == Fields.TITLE_ORDER_TYPE_PLATFORM) {
            //得到外卖平台的信息---平台的名称和Id
            QueryPlatformResponse platformResponse = (QueryPlatformResponse) event.getT();
            switch (platformResponse.header.returnCode) {
                case "0000"://返回成功
                    if (platformResponse == null || platformResponse.body == null ||
                            platformResponse.body.channels == null) {
                        mLl_title_channel.setVisibility(View.INVISIBLE);
                        break;
                    }
                    //
                    List<QueryPlatformResponse.BodyBean.ChannelsBean> channels = platformResponse
                            .body.channels;
                    for (int i = 0; i < channels.size(); i++) {//得到外卖平台信息
                        switch (i) {
                            case 0:
                                TitleInfomation.baiduIsOpen = true;//百度外卖已经开通
                                //初始化开通了哪些外卖平台
                                TitleInfomation.channelId_platform_baidu = channels.get(i)
                                        .channelId;
                                TitleInfomation.channelName_platform_baidu = channels.get(i)
                                        .channelName;
                                TitleInfomation.baiduBusinessStatus = channels.get(i)
                                        .businessStatus;//得打百度外卖的营业状态
                                TitleInfomation.baiduAutoOrder = channels.get(i).autoOrder;
                                TitleInfomation.allAutoOrder = channels.get(i).autoOrder;
                                //百度外卖的自动接单状态
                                break;
                            case 1:
                                TitleInfomation.meituanIsOpen = true;//美团外卖已经开通
                                //初始化开通了哪些外卖平台
                                TitleInfomation.channelId_platform_meituan = channels.get(i)
                                        .channelId;
                                TitleInfomation.channelName_platform_meituan = channels.get(i)
                                        .channelName;
                                TitleInfomation.meituanBusinessStatus = channels.get(i)
                                        .businessStatus;//得到美团外卖的营业状态
                                TitleInfomation.meituanAutoOrder = channels.get(i).autoOrder;
                                //美团外卖的自动接单状态
                                break;
                            case 2:
                                TitleInfomation.elemeIsOpen = true;//饿了么已经开通
                                //初始化开通了哪些外卖平台
                                TitleInfomation.channelId_platform_eleme = channels.get(i)
                                        .channelId;
                                TitleInfomation.channelName_platform_eleme = channels.get(i)
                                        .channelName;
                                TitleInfomation.elemeAutoOrder = channels.get(i).autoOrder;
                                //饿了么外卖自动接单的状态
                                break;

                            case 3:
                                TitleInfomation.wechatIsOpen = true;//微信外卖开通
                                //初始化开通了哪些外卖平台
                                TitleInfomation.channelId_platform_wechat = channels.get(i)
                                        .channelId;
                                TitleInfomation.channelName_platform_wechat = channels.get(i)
                                        .channelName;
                                TitleInfomation.wechatBusinessStatus = channels.get(i)
                                        .businessStatus;//得到微信外卖的而营业状态
                                TitleInfomation.wechatAutoOrder = channels.get(i).autoOrder;
                                //微信外卖的自动接单状态
                                break;
                        }
                    }
                    //查询外卖平台信息成功时----->>再处理已完成和未完成订单
                    //查询已处理和未处理的订单
                    String t204 = InPutJsonData.T204(this, Fields.loginsessionId, "");
                    NetUtil.GTNetWorkUtil(t204,
                            Fields.channelId,
                            QueryOrderNumResponse.class,
                            this, Fields.TITLE_ORDER_TYPE_TREATE_UNTREATE_NUM
                    );
                    break;
            }
        }
        ///********************************得到已处理和未处理的数量*********************************************/
        if (event.getOrderType() == Fields.TITLE_ORDER_TYPE_TREATE_UNTREATE_NUM) {
            QueryOrderNumResponse numResponse = (QueryOrderNumResponse) event.getT();
            switch (numResponse.header.returnCode) {
                case "0000":
                    TitleInfomation.untreate = numResponse.body.wNum;//未处理
                    TitleInfomation.treate = numResponse.body.yNum;//已处理
                    String t201 = "";
                    if (numResponse.body.wNum == 0) {
                        //查询已处理的最近一笔订单
                        t201 = InPutJsonData.T201(this, Fields.loginsessionId, 101, "",
                                1, 0, TimeHelper.exceptDay(0), TimeHelper.exceptDay(0), "");

                    } else {
                        //查询未处理的订单消息
                        t201 = InPutJsonData.T201(this, Fields.loginsessionId, -1, "",
                                1, 0, TimeHelper.exceptDay(0), TimeHelper.exceptDay(0), "");
                    }
                    NetUtil.GTNetWorkUtil(t201,
                            Fields.channelId,
                            OrderTreateListResponse.class,
                            this, Fields.TITLE_ORDER_TYPE_NEAR_ORDER_PEOPLE_INFO
                    );
                    break;
            }

        }
        /**
         * 最近订单的信息
         */
        if (event.getOrderType() == Fields.TITLE_ORDER_TYPE_NEAR_ORDER_PEOPLE_INFO) {
            OrderTreateListResponse listResponse = (OrderTreateListResponse) event.getT();
            List<OrderTreateListResponse.BodyBean.OrdersBean> orders = listResponse.body.orders;
            switch (listResponse.header.returnCode) {
                case "0000":
                    try {
                        String orderDate = orders.get(0).orderDate;
                        String orderTime = orders.get(0).orderTime;
                        TitleInfomation.nearorder = TimeHelper.transTime(orderDate + orderTime);
                        TitleInfomation.personinfo = orders.get(0).userName + "\t" + orders.get
                                (0).userPhone + "\t" + orders.get(0).userAddr;
                        if (TitleInfomation.personinfo.length() > 24) {
                            TitleInfomation.personinfo = TitleInfomation.personinfo.substring(0,
                                    24) + "...";
                        }
                    } catch (Exception e) {

                    }

                    Fields.is_Open_WaiMai_Flag = true;//外卖业务是否已经开启
                    Fields.current_ui_flag = "wm_01";//设置登录外卖的标记
                    reFreshStatus();
                    if (Fields.is_start_waimai_act) {
                        Intent intent = new Intent(this, OrderFoodActivity.class);
                        startActivity(intent);
                        Fields.is_start_waimai_act = false;
                    }
                    break;
            }

        }
        if (event.getOrderType() == Fields.EXIT_LOADING_HOME) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000":
                    EventBus.getDefault().postSticky(new ExitEvent(true));//直接退出了
                    break;
                default:
                    EventBus.getDefault().postSticky(new ExitEvent(true));//直接退出了
                    Timer timer1 = TimerTaskHelper.getTimer();
                    timer1.cancel();//停止计数器
                    break;
            }
        }
    }

    /**
     * 接收来自设置界面的消息，更新状态栏
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(TitleStatus event) {
        switch (event.getPlatformType()) {
            case 0://百度
                if (event.getBusinessStatus() == 0) {//百度外卖开始营业
                    mTv_home_baidu.setTextColor(Color.argb(255, 1, 140, 66));//绿色---字体景色
                    mTv_home_baidu.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                    //背景色--绿色
                }
                if (event.getBusinessStatus() == 1) {//百度外卖关闭营业
                    mTv_home_baidu.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_baidu.setBackgroundResource(R.drawable.title_plantform_grace_shap);//灰色
                }
                break;
            case 1://美团
                if (event.getBusinessStatus() == 0) {//美团外卖开始营业
                    mTv_home_meituan.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                    mTv_home_meituan.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                }
                if (event.getBusinessStatus() == 1) {//美团外卖关闭营业
                    mTv_home_meituan.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_meituan.setBackgroundResource(R.drawable.title_plantform_grace_shap)
                    ;//灰色
                }
                break;
            case 2://饿了么
                if (event.getBusinessStatus() == 0) {//饿了么外卖开始营业
                    mTv_home_eleme.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                    mTv_home_eleme.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                }
                if (event.getBusinessStatus() == 1) {//饿了么外卖关闭营业
                    mTv_home_eleme.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_eleme.setBackgroundResource(R.drawable.title_plantform_grace_shap);//灰色
                }
                break;
            case 3://微信
                if (event.getBusinessStatus() == 0) {//微信外卖开始营业
                    mTv_home_wechat.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                    mTv_home_wechat.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                }
                if (event.getBusinessStatus() == 1) {//微信外卖关闭营业
                    mTv_home_wechat.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_wechat.setBackgroundResource(R.drawable.title_plantform_grace_shap);
                    //灰色
                }
                break;
        }
        switch (event.getAutoOrder()) {
            case 0:
                mTv_home_autoState.setText("自动接单");
                mTv_home_autoState.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                mTv_home_autoState.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                break;
            case 1:
                mTv_home_autoState.setText("手动接单");
                mTv_home_autoState.setBackgroundResource(R.drawable.title_plantform_hand_shap);//黄色
                mTv_home_autoState.setTextColor(Color.argb(255, 255, 255, 67));//黄色
                break;
        }
    }

    /**
     * 刷新状态栏
     */
    private void reFreshStatus() {
        if (Fields.is_Open_WaiMai_Flag) {//外卖业务开启
            if (TitleInfomation.allAutoOrder == 0) {//是自动接单
                mTv_home_autoState.setText("自动接单");
                mTv_home_autoState.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                mTv_home_autoState.setTextColor(Color.argb(255, 1, 140, 66));//绿色


            }
            if (TitleInfomation.allAutoOrder == 1) {//手动接单
                mTv_home_autoState.setText("手动接单");
                mTv_home_autoState.setBackgroundResource(R.drawable.title_plantform_hand_shap);//黄色
                mTv_home_autoState.setTextColor(Color.argb(255, 255, 255, 67));//黄色
            }

            if (TitleInfomation.baiduIsOpen) {//百度外卖开通
                if (TitleInfomation.baiduBusinessStatus == 0) {//百度外卖平台开启
                    mTv_home_baidu.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                    mTv_home_baidu.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                    //背景色--绿色
                }
                if (TitleInfomation.baiduBusinessStatus == 1) {//百度外卖平台关闭
                    mTv_home_baidu.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_baidu.setBackgroundResource(R.drawable.title_plantform_grace_shap);//灰色
                }
                mTv_home_baidu.setText(TitleInfomation.channelName_platform_baidu);
                mTv_home_baidu.setVisibility(View.VISIBLE);
            } else {//百度外卖关闭
                mTv_home_baidu.setVisibility(View.INVISIBLE);
            }

            if (TitleInfomation.meituanIsOpen) {//美团外卖开通
                if (TitleInfomation.meituanBusinessStatus == 0) {
                    mTv_home_meituan.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                    mTv_home_meituan.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                    //背景色--绿色
                }
                if (TitleInfomation.meituanBusinessStatus == 1) {
                    mTv_home_meituan.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_meituan.setBackgroundResource(R.drawable.title_plantform_grace_shap)
                    ;//灰色
                }
                mTv_home_meituan.setText(TitleInfomation.channelName_platform_meituan);
                mTv_home_meituan.setVisibility(View.VISIBLE);
            } else {
                mTv_home_meituan.setVisibility(View.INVISIBLE);
            }

            if (TitleInfomation.elemeIsOpen) {//饿了么外卖开通
                if (TitleInfomation.elemeBusinessStatus == 0) {
                    mTv_home_eleme.setTextColor(Color.argb(255, 1, 140, 66));//绿色
                    mTv_home_eleme.setBackgroundResource(R.drawable.title_plantform_auto_shap);
                    //背景色--绿色
                }
                if (TitleInfomation.elemeBusinessStatus == 1) {
                    mTv_home_eleme.setTextColor(Color.argb(255, 149, 149, 149));//灰色
                    mTv_home_eleme.setBackgroundResource(R.drawable.title_plantform_grace_shap);//灰色
                }
                mTv_home_eleme.setText(TitleInfomation.channelName_platform_eleme);
                mTv_home_eleme.setVisibility(View.VISIBLE);
            } else {
                mTv_home_eleme.setVisibility(View.INVISIBLE);
            }
            mTv_home_autoState.setVisibility(View.VISIBLE);

        } else {//外卖业务关闭
            //            mLl_waimai_state.setVisibility(View.GONE);
            mTv_home_meituan.setVisibility(View.GONE);
            mTv_home_eleme.setVisibility(View.GONE);
            mTv_home_wechat.setVisibility(View.GONE);
            mTv_home_autoState.setVisibility(View.INVISIBLE);
            mTv_home_baidu.setText("当前终端外卖未开启");
            mTv_home_baidu.setVisibility(View.VISIBLE);
            mTv_home_baidu.setTextColor(Color.argb(255, 201, 0, 0));//红色
            mTv_home_baidu.setBackgroundResource(R.drawable.title_plantform_auto_red_shap);//背景色--红色

        }
        if (TitleInfomation.netStatus == 0) {//网络正常
            mTv_home_net_state.setVisibility(View.INVISIBLE);
        }
        if (TitleInfomation.netStatus == 1) {//设备离线
            mTv_home_net_state.setText("网络异常");
            mTv_home_net_state.setTextColor(Color.RED);
            mTv_home_net_state.setVisibility(View.VISIBLE);
        }
        if (TitleInfomation.printStatus == Printer.ERROR_BUSY
                || TitleInfomation.printStatus == Printer.ERROR_NONE) {//打印机正常
            mTv_home_print_status.setVisibility(View.INVISIBLE);
        } else if (TitleInfomation.printStatus == ERROR_PAPERENDED) {//打印机故障
            mTv_home_print_status.setVisibility(View.VISIBLE);
            mTv_home_print_status.setText("打印缺纸");
            mTv_home_print_status.setTextColor(Color.RED);
        } else {
            mTv_home_print_status.setVisibility(View.VISIBLE);
            mTv_home_print_status.setText("打印故障");
            mTv_home_print_status.setTextColor(Color.RED);
        }
        mTv_home_treate.setText(String.valueOf(TitleInfomation.treate));
        mTv_home_untreate.setText(String.valueOf(TitleInfomation.untreate));
        mTv_home_nearTime.setText(TitleInfomation.nearorder);
        mTv_home_cusInfo.setText(TitleInfomation.personinfo);
    }

    /**
     * 更新UI操作
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initUiEvent(InitUiEvent event) {
        switch (event.getType()) {
            case 0://接受到个推的消息，更新操作
                reFreshStatus();
                break;
            case 1://接受到心跳包离线的消息
                TitleInfomation.netStatus = 1;
                mTv_home_net_state.setText("网络异常");
                mTv_home_net_state.setTextColor(Color.RED);
                mTv_home_net_state.setVisibility(View.VISIBLE);
                break;
            case 2://w
                TitleInfomation.netStatus = 0;
                mTv_home_net_state.setVisibility(View.INVISIBLE);
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void startDialogEvent(StartDialogEvent event) {
        if (event.getType() == 1) {
            Intent intent = new Intent(this, DialogStyleActivity.class);
            startActivity(intent);
        }
    }

    /**
     * 处理打印异常
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void printErrorEvent(PrinterErrorEvent event) {
        int statusCode = event.getStatus();//打印异常
        TitleInfomation.printStatus = statusCode;
        String status = getStatus(statusCode);
        if (event.getStatus() == Printer.ERROR_BUSY//打印设备正常
                || event.getStatus() == Printer.ERROR_NONE) {
            mTv_home_print_status.setVisibility(View.INVISIBLE);
        } else {//打印设备异常
            mTv_home_print_status.setVisibility(View.VISIBLE);
            mTv_home_print_status.setTextColor(Color.RED);
            if (event.isPrint()) {//打印的状态
                Intent intent = new Intent(this, DialogStyleActivity.class);//// FIXME: 2017/2/15

                mTv_home_print_status.setText(status);
                String orderId = OrdersDataInfo.getOrderId();
                Object orderData = OrdersDataInfo.getOrderData();
                //            System.out.println("map集合信息:"+orderId + "----" + orderData);
                if (!TextUtils.isEmpty(orderId)) {
                    status = status + "\t订单号为:" + orderId + "的订单可能未打印完全!";
                }
                intent.putExtra("printError", status);//错误状态
                intent.putExtra("isReprint", event.isReprint());//是否重打印
                startActivity(intent);
            } else {//检测的状态
                Intent intent = new Intent(this, DialogStyleActivity2.class);
                intent.putExtra("printError", status);//错误状态
                startActivity(intent);
            }

        }

    }

    public String getStatus(int status) {
        String printError;
        switch (status) {
            case ERROR_PAPERENDED:
                printError = "打印缺纸";
                break;
            case ERROR_HARDERR:
                printError = "打印机硬件错误";
                break;
            case ERROR_OVERHEAT:
                printError = "打印头过热";
                break;
            case ERROR_PAPERJAM:
                printError = "打印机卡纸";
                break;
            default:
                printError = "打印机异常:" + status;
                break;
        }
        return printError;
    }

}
