package unionlive.com.umqpos.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.CheckInResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.ExitEvent;
import unionlive.com.umqpos.net.EnvironmentType;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.LdPrintStatus;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.SoundPlayUtils;
import unionlive.com.umqpos.utils.TimerTaskHelper;


/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/11/30 11:41
 * @Author's GitHub  https://github.com/PrettyAnt
 * @describe $登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, TextWatcher {

    private TextView mTv_show;
    private StringBuffer sb = new StringBuffer();
    private Button        mBtn_Login_sign;
    private EditText      mEt_Login_shopOperId;
    private EditText      mEt_Login_pwd;
    private CheckBox      mCb_Login_rmbpwd;
    private String        mShopId;
    private String        mShopOperId;
    private String        mPwd;
    private TextView      mTv_login_envri;
    private TextView      mTv_login_deviceInfo;
    private LdPrintStatus mLdPrintStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initDevicesInfo();
        initData();
    }

    private void initDevicesInfo() {
        DevicesInfoUtil.bindDevicesInfo(this);//在载入页面就绑定设备,并初始化设备信息
        LdPrintStatus.startPrint();//初始化联迪打印机的状态
        SoundPlayUtils.init(this);//初始化声音
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mShopOperId = (String) SPUtils.get(this, Fields.shopOperId, "");
        mPwd = (String) SPUtils.get(this, Fields.pwd, "");
        if (!TextUtils.isEmpty(mShopOperId) && !TextUtils.isEmpty(mPwd)) {
            mEt_Login_shopOperId.setText(mShopOperId);
            mEt_Login_pwd.setText(mPwd);
            mCb_Login_rmbpwd.setChecked(true);
            mEt_Login_shopOperId.requestFocus();
        }
    }

    private void initView() {
        mEt_Login_pwd = (EditText) findViewById(R.id.et_login_pwd);
        mEt_Login_shopOperId = (EditText) findViewById(R.id.et_login_shopOperId);
        mCb_Login_rmbpwd = (CheckBox) findViewById(R.id.cb_login_rmbpwd);
        mBtn_Login_sign = (Button) findViewById(R.id.btn_login_sign);
        mTv_login_envri = (TextView) findViewById(R.id.tv_login_envri);
        mTv_login_deviceInfo = (TextView) findViewById(R.id.tv_login_deviceInfo);

        OnAutoLeftDrawble(mEt_Login_shopOperId, R.drawable.person_blue_common, R.drawable
                .person_common);
        OnAutoLeftDrawble(mEt_Login_pwd, R.drawable.code_blue_common, R.drawable.code_common);

        mBtn_Login_sign.setOnClickListener(this);
        mEt_Login_pwd.addTextChangedListener(this);
        mEt_Login_shopOperId.addTextChangedListener(this);
        mLl_waimai_state.setVisibility(View.GONE);//隐藏根布局

    }

    private void initData() {
        switch (EnvironmentType.ENVIRONMENT_VARIABLE) {
            case 0:
                mTv_login_envri.setText("测试环境");
                break;
            case 1:
                mTv_login_envri.setText("UAT环境");
                break;
            case 2:
                mTv_login_envri.setVisibility(View.INVISIBLE);
                break;
        }
        //获取硬件的型号
        String deviceModel = DevicesInfoUtil.getDeviceModel();
        String devicesSN = DevicesInfoUtil.getDevicesSN(this);
        mTv_login_deviceInfo.setText("SN : " + devicesSN+"\t\t"+deviceModel);
        //        String s = InPutJsonData.M001a(this);//得到的是上送的密文
        //        NetUtil.couponsNetWorkUtil(s, Fields.channelId, CouponsResponse.class,
        //                this, Fields.ValidateApp, Fields.TYPE_COUPON_VALIDATE_APP);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_sign:
                mShopOperId = mEt_Login_shopOperId.getText().toString().trim();//门店操作员编号
                mPwd = mEt_Login_pwd.getText().toString().trim();//操作员密码

                if (TextUtils.isEmpty(mShopOperId)) {
                    //                    mEt_Login_shopOperId.setAnimation(mAlphaAnimation);
                    MyToast.show(this, "请输入门店操作员编号", true);
                    return;
                }
                if (TextUtils.isEmpty(mPwd)) {
                    //                    mEt_Login_pwd.setAnimation(mAlphaAnimation);
                    MyToast.show(this, "请输入密码", true);
                    return;
                }
                if (mCb_Login_rmbpwd.isChecked()) {//如果是选中状态,则记住登录信息
                    SPUtils.put(this, Fields.shopOperId, mShopOperId);
                    SPUtils.put(this, Fields.pwd, mPwd);
                }
                //                mBtn_Login_sign.setClickable(false);
                final String enM100 = InPutJsonData.M100(this, mShopOperId, mPwd);
//                NetUtil.couponsNetWorkUtil(enM100, Fields.channelId, CheckInResponse.class, this,
//                        Fields.CheckIn, Fields.TYPE_LOADING);  fixme 绕开登录
                SoundPlayUtils.play(5);
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventBackgroundThread(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.TYPE_LOADING) {
            CheckInResponse checkInResponse = (CheckInResponse) event.getT();

            switch (checkInResponse.header.returnCode) {
                case "0000"://请求成功后的返回码
                    if (checkInResponse.body == null || checkInResponse.body.transMap == null) {
                        EventBus.getDefault().postSticky(new ExitEvent(true));
                        return;
                    }
                    String transMap = checkInResponse.body.transMap;
                    //                        String transMap =
                    // "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
                    Fields.COUPON = transMap.substring(59, 60);  //优麦圈卡券
                    Fields.UNIONPAY = transMap.substring(60, 61); //银联卡功能
                    Fields.EASYPAY = transMap.substring(61, 62); //支付宝微信功能
                    Fields.RECOMMED = transMap.substring(62, 63); //推荐有礼
                    Fields.INTEGRA = transMap.substring(63, 64);  //交行信用卡积分
                    Fields.WAIMAI = transMap.substring(64, 65);   //外卖接单功能
                    Fields.loginsessionId = checkInResponse.body.sessionId;
                    SPUtils.put(this, transMap, checkInResponse.body.transMap);
                    SPUtils.put(this, Fields.shopId, checkInResponse.body.shopId);
                    SPUtils.put(this, Fields.termId, checkInResponse.body.termId);
                    TitleInfomation.shopId = checkInResponse.body.shopId;
//                    Fields.is_checking = true;
                    try {
                        Timer timer = TimerTaskHelper.getTimer();
                        TimerTaskHelper timerTaskHelper = new TimerTaskHelper(getApplicationContext());
                        timer.schedule(timerTaskHelper, TimerTaskHelper.executeTime, TimerTaskHelper.periodTime);
                    } catch (Exception e) {
                        System.out.println("testTimerTask:" + e);
                    }
                    SoundPlayUtils.play(5);
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    break;
                case "0096"://// FIXME: 2017/2/9
                    CancleOrderDialog.isExitDialog(this, checkInResponse.header.returnMessage + ":" +
                            checkInResponse.header.returnCode + "  请退出后重新登录!");
                    break;
                default:
                    MyToast.show(this, checkInResponse.header.returnMessage + ":" +
                            checkInResponse.header.returnCode, true);
                    InitDataHelper.sendError(this, checkInResponse.header.returnMessage + ":" +
                            checkInResponse.header.returnCode);//上送错误信息
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        //
        EventBus.getDefault().postSticky(new ExitEvent(true));//发送广播到baseactivity直接退出程序
        //        directExit(this);
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        OnAutoLeftDrawble(mEt_Login_shopOperId, R.drawable.person_blue_common, R.drawable
                .person_common);
        OnAutoLeftDrawble(mEt_Login_pwd, R.drawable.code_blue_common, R.drawable.code_common);
    }

    public void OnAutoLeftDrawble(EditText text, int TrueRes, int FalseRes) {
        Drawable drawable;
        if (text.getText().toString().length() > 1) {
            drawable = ContextCompat.getDrawable(this, TrueRes);
        } else {
            drawable = ContextCompat.getDrawable(this, FalseRes);
        }
        drawable.setBounds(0, 0, 50,50);
        text.setCompoundDrawables(drawable, null, null, null);
//        System.out.println("图片宽---" + drawable.getMinimumWidth() + "---图片高---" + drawable.getMinimumHeight());
    }
}
