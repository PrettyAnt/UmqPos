package unionlive.com.umqpos.ui.activity.coupon_act;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.MyToast;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/3 13:12
 * @describe ${TODO}
 */
public class ScanActivity extends BaseActivity implements DecoratedBarcodeView.TorchListener {
    // 添加一个按钮用来控制闪光灯，同时添加两个按钮表示其他功能，先用Toast表示
    ImageView               swichLight;
    DecoratedBarcodeView mDBV;
    private CaptureManager captureManager;
    private boolean isLightOn = false;
    private TextView mTv_title;
    private ImageView mIv_back;


    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_customscan);
        initView();
        initEvent();

        // 如果没有闪光灯功能，就去掉相关按钮
        if (!hasFlash()) {
            swichLight.setVisibility(View.GONE);
        }
        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(), savedInstanceState);
        captureManager.decode();
    }
    private void initView() {
        swichLight = (ImageView) findViewById(R.id.iv_switch);
        mDBV= (DecoratedBarcodeView) findViewById(R.id.dbv_custom);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mTv_title.setText("扫一扫");
        mDBV.setTorchListener(this);
    }
    private void initEvent() {
        //选择闪关灯
        swichLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLightOn) {
                    mDBV.setTorchOff();
                } else {
                    mDBV.setTorchOn();
                }
            }
        });
        mIv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // torch 手电筒
    @Override
    public void onTorchOn() {
        MyToast.show(this,"开启闪关灯",true);
        isLightOn = true;
    }

    @Override
    public void onTorchOff() {
        MyToast.show(this,"关闭闪关灯",true);
        isLightOn = false;
    }

    // 判断是否有闪光灯功能
    private boolean hasFlash() {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }


}
