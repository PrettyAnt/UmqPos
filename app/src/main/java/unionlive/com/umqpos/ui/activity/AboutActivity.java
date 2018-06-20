package unionlive.com.umqpos.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.CouponsResponse;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.ShopInfoResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.SPUtils;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/16 18:42
 * @describe ${TODO}
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {

    private TextView  mTv_title;
    private ImageView mIv_back;
    private ImageView mIv_setting_about_icon;
    private TextView  mTv_setting_about_shop;
    private TextView  mTv_setting_about_address;
    private TextView  mTv_setting_about_name;
    private TextView  mTv_setting_about_tel;
    private TextView  mTv_setting_about_oper;
    private TextView  mTv_setting_about_connus;
    private TextView  mTv_setting_about_mngt;
    private TextView  mTv_setting_about_devicesInfo;
    private TextView  mTv_setting_about_version;
    private TextView  mTv_setting_about_checkvsn;
    private TextView  mTv_setting_about_help;
    private TextView  mTv_setting_about_shopcomplain;
    private Button mBt_exit;
    private LinearLayout mLl_waimai_state;
    private TextView mTv_home_bottom_info;
    private TextView mTv_setting_about_shopid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

//        initPlatForm();//初始化平台
        initView();
        initEvent();
        initData();
    }



    private void initView() {
        //标题
        mTv_title = (TextView) findViewById(R.id.tv_title);
        //返回键
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        //店铺图标
        mIv_setting_about_icon = (ImageView) findViewById(R.id.iv_setting_about_icon);
        //店铺名
        mTv_setting_about_shop = (TextView) findViewById(R.id.tv_setting_about_shop);
        mTv_setting_about_shopid = (TextView) findViewById(R.id.tv_setting_about_shopid);
        //店铺的地址
        mTv_setting_about_address = (TextView) findViewById(R.id.tv_setting_about_address);
        //店铺的名字
        mTv_setting_about_name = (TextView) findViewById(R.id.tv_setting_about_name);
        //电话
        mTv_setting_about_tel = (TextView) findViewById(R.id.tv_setting_about_tel);

        //操作员编号
        mTv_setting_about_oper = (TextView) findViewById(R.id.tv_setting_about_oper);
        //联系我们
        mTv_setting_about_connus = (TextView) findViewById(R.id.tv_setting_about_connus);
        //联系客户经理
        mTv_setting_about_mngt = (TextView) findViewById(R.id.tv_setting_about_mngt);
        //当前硬件信息
        mTv_setting_about_devicesInfo = (TextView) findViewById(R.id.tv_setting_about_devicesInfo);

        //软件版本
        mTv_setting_about_version = (TextView) findViewById(R.id.tv_setting_about_version);
        //检查新版本
        mTv_setting_about_checkvsn = (TextView) findViewById(R.id.tv_setting_about_checkvsn);
        //帮助信息
        mTv_setting_about_help = (TextView) findViewById(R.id.tv_setting_about_help);
        //投诉建议
        mTv_setting_about_shopcomplain = (TextView) findViewById(R.id.tv_setting_about_shopcomplain);
        mTv_home_bottom_info = (TextView) findViewById(R.id.tv_home_bottom_info);//底部信息
        //退出登录
        mBt_exit = (Button) findViewById(R.id.bt_exit);
    }

    private void initEvent() {
        mTv_title.setText("关于");
        mIv_back.setOnClickListener(this);
        mTv_setting_about_mngt.setOnClickListener(this);
        mTv_setting_about_checkvsn.setOnClickListener(this);
        mTv_setting_about_help.setOnClickListener(this);
        mTv_setting_about_shopcomplain.setOnClickListener(this);
        mBt_exit.setOnClickListener(this);
        //String shopId = (String) SPUtils.get(this, Fields.shopId, "");
    }
    private void CheckVersion() {
        String s = InPutJsonData.M001a(this);//得到的是上送的密文
        NetUtil.couponsNetWorkUtil(s, Fields.channelId, CouponsResponse.class,
                this, Fields.ValidateApp, Fields.TYPE_COUPON_CHECK_VERSION);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_setting_about_mngt:
                break;
            case R.id.tv_setting_about_checkvsn:
                CheckVersion();
//                if (Fields.NEW_VERSION > Fields.CURRENT_VERSION) {//有最新版本
//                    CancleOrderDialog.IsUpDateDialog(
//                            "有新版本!是否升级?",
//                            "",//版本描述信息
//                            Fields.DOWNLOADURL,//下载链接
//                            this,
//                            false,
//                            true);
//                } else {
//                    MyToast.show(this, "当前为最新版本", true);
//                }

                break;
            case R.id.tv_setting_about_help:
                //                showPopuWindow();
                //                initDataBase();
                break;
            case R.id.tv_setting_about_shopcomplain:
                final EditText editText = new EditText(this);
                new AlertDialog
                        .Builder(this)
                        .setTitle("投诉建议")
                        .setIcon(R.drawable.loading)
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (!TextUtils.isEmpty(editText.getText().toString())) {
                                    String t104 = InPutJsonData.T104(getApplicationContext(),
                                            Fields.loginsessionId, editText.getText().toString());
                                    NetUtil.GTNetWorkUtil(t104, Fields.channelId, OpenWmResponse.class,
                                            getApplicationContext(), -1);
                                    MyToast.show(getApplicationContext(), "感谢您的反馈~", true);
                                    dialogInterface.dismiss();
                                } else {
                                    MyToast.show(getApplicationContext(), "内容不能为空~", true);
                                }
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.bt_exit:
                if (Fields.is_Open_WaiMai_Flag) {//如果外卖开启，弹出对话框
                    CancleOrderDialog.isExitDialog(this,"退出后将不能接外卖订单");
                } else {//否则直接退出
                    CancleOrderDialog.isExitDialog(this,"确定退出?");
                }
                break;
        }
    }

    private void initData() {
        String encryptStr = InPutJsonData.T101(this, Fields.loginsessionId, "");
        NetUtil.waiMaiNetWorkUtil(encryptStr, Fields.channelId, ShopInfoResponse.class, this, Fields.ORDER_TYPE_ABOUT_SHOPINFO);
        String cusinfo = (String) SPUtils.get(this, "CUSINFO", "");
        mTv_home_bottom_info.setText(cusinfo);
        String shopId = (String) SPUtils.get(this, Fields.shopId, "");
        mTv_setting_about_shopid.setText("门店编号:"+shopId);
        String versionName = DevicesInfoUtil.getVersionName(this);
        mTv_setting_about_version.setText(versionName);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_ABOUT_SHOPINFO) {
            ShopInfoResponse shopInfoResponse = (ShopInfoResponse) event.getT();
            switch (shopInfoResponse.header.returnCode) {
                case "0000":
                    if (shopInfoResponse.body == null) {
                        return;
                    }
                    Glide
                            .with(this)
                            .load(shopInfoResponse.body.shopLogo)
                            .error(R.mipmap.error)
                            .into(mIv_setting_about_icon);
                    mTv_setting_about_shop.setText(shopInfoResponse.body.shopName);
                    mTv_setting_about_address.setText("地址:" + shopInfoResponse.body.addr);
                    mTv_setting_about_name.setText("联系人:" + shopInfoResponse.body.contact);
                    mTv_setting_about_tel.setText("电话:" + shopInfoResponse.body.tel);
                    mTv_setting_about_oper.setText(shopInfoResponse.body.customerManager);
                    mTv_setting_about_connus.setText("");
                    mTv_setting_about_mngt.setText(shopInfoResponse.body.managerPhone);
                    if (shopInfoResponse != null) {
                    }
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(this, shopInfoResponse.header.returnMessage
                            + shopInfoResponse.header.returnCode, null);
                    break;
            }

        }
        if (event.getOrderType() == Fields.TYPE_COUPON_CHECK_VERSION) {//校验客户端版本
            CouponsResponse appCouponsResponse = (CouponsResponse) event.getT();
            //            MyToast.show(this, appCouponsResponse.header.returnMessage, false);
            CouponsResponse.HeaderBean header = appCouponsResponse.header;
            switch (header.returnCode) {
                case "0000":
                    //                    Fields.sessionId = header.sessionId;//将唯一会话编号存在全局变,后面的接口需要传入此值  FIXME
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
                                            true);
                                    break;
                                case "1"://// FIXME: 2017/1/17
                                    CancleOrderDialog.IsUpDateDialog(
                                            "有新版本!必须升级才能使用，是否升级?",
                                            description,
                                            downloadUrl,
                                            this,
                                            true,
                                            true);
                                    break;
                                default:
                                    break;
                            }
                        } else {
                            MyToast.show(this,"当前是最新版本,无需升级!",true);
                        }
                    } catch (Exception e) {
                        //body为空，直接进入程序
                        InitDataHelper.sendError(this,e.toString());
                        MyToast.show(this,"当前是最新版本,无需升级!",true);
                    }
                    break;
                default:
                    break;
            }

        }


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGtEvent(IsFinishCurrUiEvent event){
        if (TextUtils.equals(event.getFlag(),"wm_01")) {
            if (event.isFinish()) {
                CancleOrderDialog.isFinishDialog(this,null,event.getTitleInfo());//判断是否退出当前的界面
            }
        }
    }

}
