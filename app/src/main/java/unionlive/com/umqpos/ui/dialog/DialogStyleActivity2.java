package unionlive.com.umqpos.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.ui.BaseActivity;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/2/13 3:23
 * @describe ${TODO}
 */

public class DialogStyleActivity2 extends BaseActivity implements View.OnClickListener {

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
        if (mPrintError != null) {
            mTv_dialog_title.setText(mPrintError);
        } else {
            mTv_dialog_title.setText("打印异常,请检查打印机!");
        }
        mTv_dialog_sure.setText("确定");
    }

    private void initView() {
        mLl_waimai_state.setVisibility(View.GONE);
        mTv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        mTv_dialog_sure = (TextView) findViewById(R.id.tv_dialog_sure);
        mTv_dialog_cancle = (TextView) findViewById(R.id.tv_dialog_cancle);
        mTv_dialog_sure.setOnClickListener(this);
        mTv_dialog_cancle.setOnClickListener(this);
        mTv_dialog_cancle.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_dialog_cancle:
                finish();
                break;
            case R.id.tv_dialog_sure:
                finish();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
