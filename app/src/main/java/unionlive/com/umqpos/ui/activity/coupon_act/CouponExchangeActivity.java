package unionlive.com.umqpos.ui.activity.coupon_act;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.PurchaseCouponsResponse;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransBasicResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.bean.CouponPrintMsg;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.FormatStrCode;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.TimeHelper;
import unionlive.com.umqpos.utils.LdPrintCoupon;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/13 10:56
 * @describe ${TODO}
 */
public class CouponExchangeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView    mBack_home;
    private TextView     mTv_title;
    private LinearLayout mLl_waimai_state;
    private Button       mBt_0;
    private Button       mBt_1;
    private Button       mBt_2;
    private Button       mBt_3;
    private Button       mBt_4;
    private Button       mBt_5;
    private Button       mBt_6;
    private Button       mBt_7;
    private Button       mBt_8;
    private Button       mBt_9;
    private ImageButton  mBt_clear;
    private Button       mBt_clearall;
    private Button       mBt_sure;
    private EditText     mEt_coupon_code;
    private ImageView    mIv_coupon_scan;
    private TextView     mTv_coupon_returnState;
    private TextView     mTv_coupon_desc;
    private TextView     mTv_coupon_exchange;
    private TextView     mTv_coupon_num;
    private TextView     mTv_coupon_success;
    private Button       mBt_coupons_query_trade;
    private String       mResultCode;
    private LinearLayout mUploadview;
    private ImageView    mIv_orderfood_titleright;
    private TextView     mTv_check_trace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        Fresco.initialize(this);
        setContentView(R.layout.activity_couponexchange);
        initView();
        initEvent();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initData() {
        String termId = (String) SPUtils.get(this, Fields.termId, "");
        String shopId = (String) SPUtils.get(this, Fields.shopId, "");
        String shopOperId = (String) SPUtils.get(this, Fields.shopOperId, "");
        String m201 = InPutJsonData.M201(this, "", 1, 0, termId, shopId, shopOperId);
        NetUtil.couponsNetWorkUtil(m201, Fields.channelId, QueryTransBasicResponse.class, this,
                Fields.QueryTransBasic, Fields.COUPON_TYPE_QUERY_BASE_TRANS_HOME);//交易基本信息查询
    }

    private void initView() {

        mBack_home = (ImageView) findViewById(R.id.iv_back);//返回
        mTv_title = (TextView) findViewById(R.id.tv_title);//标题
        mEt_coupon_code = (EditText) findViewById(R.id.et_coupon_code);//输入券号
        mIv_coupon_scan = (ImageView) findViewById(R.id.iv_coupon_scan);//扫一扫
        mTv_coupon_returnState = (TextView) findViewById(R.id.tv_coupon_returnState);//交易状态
        mTv_coupon_desc = (TextView) findViewById(R.id.tv_coupon_desc);//交易描述
        mTv_coupon_exchange = (TextView) findViewById(R.id.tv_coupon_exchange);//上次兑换
        mTv_coupon_num = (TextView) findViewById(R.id.tv_coupon_num);//上次兑换的券码
        mTv_coupon_success = (TextView) findViewById(R.id.tv_coupon_success);//交易状态
        mBt_coupons_query_trade = (Button) findViewById(R.id.bt_coupons_query_trade);//交易查询
        mIv_orderfood_titleright = (ImageView) findViewById(R.id.iv_orderfood_titleright);
        mTv_check_trace = (TextView) findViewById(R.id.tv_check_trace);//交易查询
        mTv_check_trace.setVisibility(View.VISIBLE);
        mTv_check_trace.setOnClickListener(this);
        mIv_orderfood_titleright.setVisibility(View.INVISIBLE);
        mBt_coupons_query_trade.setVisibility(View.GONE);
        mIv_orderfood_titleright.setOnClickListener(this);
        //        mDv_welcome = (SimpleDraweeView) findViewById(R.id.dv_welcome);
        //        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
        //                .setAutoPlayAnimations(true)//自动播放动画
        //                .setUri(Uri.parse("asset://loading.gif"))//路径E:\UnionLive_project1\UmqPos\app\src\main\assets\loading.gif
        //                .build();
        //        mDv_welcome.setController(draweeController);

        mUploadview = (LinearLayout) findViewById(R.id.uploadview);
        mBt_0 = (Button) findViewById(R.id.bt_0);
        mBt_1 = (Button) findViewById(R.id.bt_1);
        mBt_2 = (Button) findViewById(R.id.bt_2);
        mBt_3 = (Button) findViewById(R.id.bt_3);
        mBt_4 = (Button) findViewById(R.id.bt_4);
        mBt_5 = (Button) findViewById(R.id.bt_5);
        mBt_6 = (Button) findViewById(R.id.bt_6);
        mBt_7 = (Button) findViewById(R.id.bt_7);
        mBt_8 = (Button) findViewById(R.id.bt_8);
        mBt_9 = (Button) findViewById(R.id.bt_9);
        mBt_clear = (ImageButton) findViewById(R.id.bt_clear);
        mBt_clearall = (Button) findViewById(R.id.bt_clearall);
        mBt_sure = (Button) findViewById(R.id.bt_sure);
        mBt_0.setOnClickListener(this);
        mBt_1.setOnClickListener(this);
        mBt_2.setOnClickListener(this);
        mBt_3.setOnClickListener(this);
        mBt_4.setOnClickListener(this);
        mBt_5.setOnClickListener(this);
        mBt_6.setOnClickListener(this);
        mBt_7.setOnClickListener(this);
        mBt_8.setOnClickListener(this);
        mBt_9.setOnClickListener(this);
        mBt_clear.setOnClickListener(this);
        mBt_clearall.setOnClickListener(this);
        mBt_sure.setOnClickListener(this);
        mBack_home.setOnClickListener(this);
        mTv_title.setText("电子券验证");
        mBt_coupons_query_trade.setOnClickListener(this);//交易查询
        mIv_coupon_scan.setOnClickListener(this);
    }

    private void initEvent() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEt_coupon_code.getWindowToken(), 0);
        mEt_coupon_code.setInputType(InputType.TYPE_NULL);
        mIv_coupon_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(CouponExchangeActivity.this);
                intentIntegrator
                        .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                        .setPrompt("将二维码/条码放入框内，即可自动扫描")//写那句提示的话
                        .setOrientationLocked(false)//扫描方向固定
                        .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是CustomActivity
                        .initiateScan(); // 初始化扫描
            }
        });
    }

    //获取扫描的结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {

            } else {
                // ScanResult 为获取到的字符串
                mResultCode = intentResult.getContents();
                //                ScanResult=FormatResult(ScanResult);
                mResultCode = FormatStrCode.getDivisionString(mResultCode, 4);
                mEt_coupon_code.setText(mResultCode);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    StringBuffer sb = new StringBuffer();

    @Override
    public void onClick(View view) {
        switch (sb.length()) {
            case 4:
                sb.append("\t");
                break;
            case 9:
                sb.append("\t");
                break;
            case 14:
                sb.append("\t");
                break;
        }
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.bt_0:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(0).toString());
                break;
            case R.id.bt_1:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(1).toString());
                break;
            case R.id.bt_2:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(2).toString());
                break;
            case R.id.bt_3:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(3).toString());
                break;
            case R.id.bt_4:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(4).toString());
                break;
            case R.id.bt_5:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(5).toString());
                break;
            case R.id.bt_6:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(6).toString());
                break;
            case R.id.bt_7:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(7).toString());
                break;
            case R.id.bt_8:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(8).toString());
                break;
            case R.id.bt_9:
                if (sb.length() == 19) {
                    break;
                }
                mEt_coupon_code.setText(sb.append(9).toString());
                break;
            case R.id.bt_clear:
                String subStr = sb.toString().trim();
                if (subStr.length() == 0) {
                    break;
                } else {
                    String substring = subStr.substring(0, subStr.length() - 1);
                    sb = new StringBuffer();
                    sb.append(substring);
                    mEt_coupon_code.setText(sb.toString());
                    break;
                }
            case R.id.bt_clearall:
                sb = new StringBuffer();
                mEt_coupon_code.setText(sb.toString());
                break;
            case R.id.bt_sure:

                mResultCode = mEt_coupon_code.getText().toString().trim().replaceAll("\\s*", "");
                //                System.out.println("得到输入框的内容-----"+mResultCode);
                String termId = (String) SPUtils.get(this, Fields.termId, "");
                String shopId = (String) SPUtils.get(this, Fields.shopId, "");
                String shopOperId = (String) SPUtils.get(this, Fields.shopOperId, "");
                SPUtils.put(this, "MRESULTCODE", mResultCode);
                //                mUploadview.setVisibility(View.VISIBLE);
                //                EventBus.getDefault().postSticky(new UploadingEvent(false));

                String m102 = InPutJsonData.M102(this, mResultCode, 1, termId, shopId, shopOperId);
                NetUtil.couponsNetWorkUtil(m102, Fields.channelId, PurchaseCouponsResponse.class, this,
                        Fields.PurchaseCoupons, Fields.COUPON_TYPE_PURCHASE);//电子券兑换
                //                if (!NetUtil.isNetworkConnected(this)) {
                //                    mUploadview.setVisibility(View.GONE);
                //                }
                break;
            case R.id.bt_coupons_query_trade:
                //                Intent intent = new Intent(this,QueryCouponInfoActivity.class);
                //                startActivity(intent);
                break;
            //            case R.id.iv_coupon_scan:
            //                Intent intent1 = new Intent(this, ScanActivity.class);
            //                startActivity(intent1);
            //                break;
            case R.id.iv_orderfood_titleright:
                //                Intent intent = new Intent(this,QueryCouponInfoActivity.class);
                //                startActivity(intent);
                break;
            case R.id.tv_check_trace:
                Intent intent = new Intent(this, QueryCouponInfoActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void couponExchangeEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.COUPON_TYPE_PURCHASE) {
            PurchaseCouponsResponse couponsResponse = (PurchaseCouponsResponse) event.getT();
            String mresultcode = (String) SPUtils.get(this, "MRESULTCODE", "");//// FIXME: 2017/2/6
            switch (couponsResponse.header.returnCode) {
                case "0000":
                    if (couponsResponse.body != null) {
                        String authCode = couponsResponse.body.authCode;
                        SPUtils.put(this, authCode, authCode);
                    }


                    PurchaseCouponsResponse.BodyBean body = couponsResponse.body;
                    PurchaseCouponsResponse.HeaderBean header = couponsResponse.header;
                    mTv_coupon_returnState.setText(couponsResponse.header.returnMessage);//3DCB2B
                    mTv_coupon_returnState.setTextColor(getResources().getColor(R.color.successColor));
                    CouponPrintMsg couponPrintMsg = new CouponPrintMsg(
                            body.title,//标题
                            true,
                            body.mercId,//门店编号
                            body.operId,//操作员编号
                            body.termId,//终端编号
                            body.prodId,//产品编号
                            body.authCode == null ? "--" : body.authCode,//授权码
                            TimeHelper.transTime(couponsResponse.header.submitTime),//交易时间
                            body.expireDate,//券有效期
                            body.prodName,  //产品名称
                            getTransNum(sb.toString()),//券号
                            body.prodDesc,//产品描述
                            TimeHelper.getPrintTime(),//打印时间
                            body.useTimes + "",//使用次数
                            body.mercName,   //门店名称
                            body.transName,   //交易名称
                            body.ad,//广告
                            header.hostTraceNo,//后台交易流水号--->参考号
                            TimeHelper.getBatchNum(couponsResponse.header.submitTime),//批次号
                            TimeHelper.getCertificNum(couponsResponse.header.submitTime),//凭证号
                            DevicesInfoUtil.getVersionCode(this)//当前应用程序的版本号
                    );
                    Printer printer = Printer.getInstance();
                    LdPrintCoupon LdPrintCoupon = new LdPrintCoupon(printer);
                    LdPrintCoupon.setT(couponPrintMsg);
                    try {
                        LdPrintCoupon.start();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }

                    sb = new StringBuffer();
                    mEt_coupon_code.setText(sb.toString());
                    mresultcode = getTransNum(mresultcode);
                    mTv_coupon_num.setText(mresultcode);
                    getState(0);
                    break;
                default:
                    getState(1);
                    mTv_coupon_desc.setText(couponsResponse.header.returnMessage);
                    mresultcode = getTransNum(mresultcode);
                    mTv_coupon_num.setText(mresultcode);
                    sb = new StringBuffer();
                    mEt_coupon_code.setText(sb.toString());
                    break;
            }

        }
        if (event.getOrderType() == Fields.COUPON_TYPE_QUERY_BASE_TRANS_HOME) {//查询交易的基本信息
            QueryTransBasicResponse transBasicResponse = (QueryTransBasicResponse) event.getT();
            switch (transBasicResponse.header.returnCode) {
                case "0000":
                    if (transBasicResponse != null && transBasicResponse.body != null && transBasicResponse.body.transInfo != null) {
                        List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo = transBasicResponse.body.transInfo;
                        if (!TextUtils.isEmpty(transInfo.get(0).no)) {
                            String number = getTransNum(transInfo.get(0).no);
                            mTv_coupon_num.setText(number);

                        }
                    }
                    getState(0);
                    break;
                default:
                    getState(1);
                    mTv_coupon_desc.setText(transBasicResponse.header.returnMessage);
                    String mresultcode = (String) SPUtils.get(this, "MRESULTCODE", "");//// FIXME: 2017/2/6
                    mresultcode = getTransNum(mresultcode);
                    mTv_coupon_num.setText(mresultcode);
                    break;
            }

        }
    }

    private void getState(int i) {
        switch (i) {
            case 0://成功
                mTv_coupon_success.setText("交易成功");
                mTv_coupon_success.setTextColor(getResources().getColor(R.color.successColor));//绿色
                mTv_coupon_returnState.setText("交易成功");
                mTv_coupon_returnState.setTextColor(getResources().getColor(R.color.successColor));//绿色
                mTv_coupon_returnState.setVisibility(View.INVISIBLE);
                mTv_coupon_desc.setVisibility(View.INVISIBLE);
                break;
            case 1://失败
                mTv_coupon_success.setText("交易失败");
                mTv_coupon_success.setTextColor(getResources().getColor(R.color.redfailColor));//红色
                mTv_coupon_returnState.setText("交易失败");
                mTv_coupon_returnState.setTextColor(getResources().getColor(R.color.redfailColor));//红色
                mTv_coupon_returnState.setVisibility(View.VISIBLE);
                mTv_coupon_desc.setVisibility(View.VISIBLE);
                break;
        }
    }

    private String getTransNum(String no) {
        String behind = "";
        String afterStr = "";
        if (no.length() >= 12) {
            behind = no.substring(0, 4);
            afterStr = no.substring(no.length() - 4, no.length());
        } else if (no.length() >= 4 && no.length() < 12) {
            behind = no.substring(0, 2);
            afterStr = no.substring(no.length() - 2, no.length());
        } else {
            return no;
        }
        //        return no;
        return behind + " **** **** " + afterStr;
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
