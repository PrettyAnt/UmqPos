package unionlive.com.umqpos.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.utils.LdPrintStatus;
import unionlive.com.umqpos.utils.LdPrintWaimai;
import unionlive.com.umqpos.utils.OrdersDataInfo;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/2/13 3:23
 * @describe ${TODO}
 */

public class DialogStyleActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTv_dialog_title;
    private TextView mTv_dialog_sure;
    private TextView mTv_dialog_cancle;
    private Dialog mLoadingDialog;
    private boolean mIsReprint;
    private String mPrintError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dialogstyle);
//        EventBus.getDefault().register(this);//注册eventBus
        initView();
        initData();
    }

    private void initData() {
        mPrintError = getIntent().getStringExtra("printError");//显示哪种打印的异常
        mIsReprint = getIntent().getBooleanExtra("isReprint",false);//是否重新打印
        if (mPrintError != null) {
            mTv_dialog_title.setText(mPrintError);
        }
        mTv_dialog_sure.setText("继续打印");
    }

    private void initView() {
        mLl_waimai_state.setVisibility(View.GONE);
        mTv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        mTv_dialog_sure = (TextView) findViewById(R.id.tv_dialog_sure);
        mTv_dialog_cancle = (TextView) findViewById(R.id.tv_dialog_cancle);
        mTv_dialog_sure.setOnClickListener(this);
        mTv_dialog_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_cancle:
                finish();
                break;
            case R.id.tv_dialog_sure:
//                1。检测打印机
//                LdPrintStatus.startPrint();
                Object orderData = OrdersDataInfo.getOrderData();
                if (orderData != null) {
                    LdPrintWaimai ldPrintWaimai = LdPrintWaimai.getInstance();
                    ldPrintWaimai.setT(orderData);
                    ldPrintWaimai.setIsReprint(mIsReprint);//是否是重打印的订单
                    try {
                        ldPrintWaimai.start();
                        finish();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                } else {
                    mTv_dialog_title.setText(LdPrintStatus.getStatus(TitleInfomation.printStatus));
                    if (TitleInfomation.printStatus == Printer.ERROR_BUSY
                            || TitleInfomation.printStatus == Printer.ERROR_NONE) {
                        finish();
                    }
                }

                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


//    /**
//     * 打印机的状态
//     * @param status
//     * @return
//     */
//    public String getStatus(int status) {
//        String printError;
//        switch (status) {
//            case ERROR_PAPERENDED:
//                printError = "打印缺纸";
//                break;
//            case ERROR_HARDERR:
//                printError = "打印机硬件错误";
//                break;
//            case ERROR_OVERHEAT:
//                printError = "打印头过热";
//                break;
//            case ERROR_PAPERJAM:
//                printError = "打印机卡纸";
//                break;
//            default:
//                printError = "打印机异常:" + status;
//                break;
//        }
//        return printError;
//    }
}
