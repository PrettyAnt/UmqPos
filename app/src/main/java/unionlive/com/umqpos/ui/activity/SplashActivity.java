package unionlive.com.umqpos.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.CouponsResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.ExitEvent;
import unionlive.com.umqpos.event.SpalishStartEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.ViewPagerHelper;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/11/30 11:11
 * @describe ${TODO}
 */

public class SplashActivity extends BaseActivity {

    private List<View> views = null;
    private ViewPager    mViewpager;
    private LinearLayout mDots_parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
//        initData(); fixme 绕过相应代码
        initEvent();
    }


    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mDots_parent = (LinearLayout) findViewById(R.id.dots_parent);
        mLl_waimai_state.setVisibility(View.GONE);
    }

    private void initData() {
        String s = InPutJsonData.M001a(this);//得到的是上送的密文
        NetUtil.couponsNetWorkUtil(s, Fields.channelId, CouponsResponse.class,
                this, Fields.ValidateApp, Fields.TYPE_COUPON_VALIDATE_APP);
    }

    private void initEvent() {
        boolean splash_flag = (boolean) SPUtils.get(this, "SPLASH_FLAG", true);
        if (!splash_flag) {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            views = new ArrayList<View>();
            View view1 = LayoutInflater.from(this).inflate(R.layout.page_guide_first, null);
            View view2 = LayoutInflater.from(this).inflate(R.layout.page_guide_second, null);
            View view3 = LayoutInflater.from(this).inflate(R.layout.page_guide_third, null);
            views.add(view1);
            views.add(view2);
            views.add(view3);
            view3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SPUtils.put(getApplicationContext(), "SPLASH_FLAG", false);
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            new ViewPagerHelper(false, mViewpager, views, mDots_parent, R.mipmap.page_indicator_focused, R.mipmap.page_indicator_unfocused);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void isFinishCurrentEvent(SpalishStartEvent event) {
        //如果是true的话，则直接退出当前的界面
        if (event.isInitEvent()) {//则直接初始化 initEvent();进入到下个界面
            EventBus.getDefault().postSticky(new ExitEvent(true));//如果返回成功,向baseActivity发送消息,则退出程序
        } else {
            //如果是false的话，则进入到下一个界面
            initEvent();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onMain(CoubonsDecoderEvent event) {

        if (event.getOrderType() == Fields.TYPE_COUPON_VALIDATE_APP) {//校验客户端版本
            CouponsResponse appCouponsResponse = (CouponsResponse) event.getT();
            //            MyToast.show(this, appCouponsResponse.header.returnMessage, false);
            CouponsResponse.HeaderBean header = appCouponsResponse.header;
            switch (header.returnCode) {
                case "0000":
                    Fields.sessionId = header.sessionId;//将唯一会话编号存在全局变,后面的接口需要传入此值  FIXME
                    try {
                        //先判断版本号，再判断强制升级
                        CouponsResponse.BodyBean body = appCouponsResponse.body;
                        String description = body.description;//描述
                        String downloadUrl = body.downloadUrl;//下载链接
                        String forceUpdate = body.forceUpdate;//强制升级
                        String latestVersion = body.latestVersion;//最后一次版本号
                        int newVersion = Integer.parseInt(latestVersion.replaceAll("\\.", ""));////得到版本号，去掉点得到版本号---软件的最新版本
                        int currentVersion = Integer.parseInt(DevicesInfoUtil.getVersionName(this).replaceAll("\\.", ""));//软件的当前版本
                        if (newVersion > currentVersion) {
                            switch (forceUpdate) {
                                case "0":
                                    CancleOrderDialog.IsUpDateDialog(
                                            "有新版本!是否升级?",
                                            description,
                                            downloadUrl,
                                            this,
                                            false,
                                            false);
                                    break;
                                case "1"://// FIXME: 2017/1/17
                                    CancleOrderDialog.IsUpDateDialog(
                                            "有新版本!必须升级才能使用，是否升级?",
                                            description,
                                            downloadUrl,
                                            this,
                                            true,
                                            false);
                                    break;
                                default:
                                    initEvent();//如果都不是的话，那么直接初始化界面
                                    break;
                            }
                        } else {
                            initEvent();
                        }
                    } catch (Exception e) {
                        //body为空，直接进入程序
                        InitDataHelper.sendError(this,e.toString());
                        initEvent();
                    }
                    break;
                default:
//                    if (!NetUtil.isNetworkConnected(this)) {
//                        String s = InPutJsonData.M001a(this);//得到的是上送的密文
//                        NetUtil.couponsNetWorkUtil(s, Fields.channelId, CouponsResponse.class,
//                                this, Fields.ValidateApp, Fields.TYPE_COUPON_VALIDATE_APP);
//                    }
                    break;
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
}
