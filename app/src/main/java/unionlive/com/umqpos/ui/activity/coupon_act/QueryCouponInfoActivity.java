package unionlive.com.umqpos.ui.activity.coupon_act;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.event.FlashUiEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.fragment.coupon_frag.CouponTdCollectFragment;
import unionlive.com.umqpos.ui.fragment.coupon_frag.CouponTdDetialFragment;
import unionlive.com.umqpos.ui.fragment.coupon_frag.CouponYesDetialFragment;
import unionlive.com.umqpos.ui.fragment.coupon_frag.TodayDetialFragment;
import unionlive.com.umqpos.ui.fragment.coupon_frag.YestodayDetialFragment;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/28 15:48
 * @describe ${TODO}
 */

public class QueryCouponInfoActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton             mRb_coupon_today_collect;
    private RadioButton             mRb_coupon_today_detial;
    private RadioButton             mRb_coupon_yes_detial;
    private ImageView               mIv_back;
    private TextView                mTv_title;
    private CouponTdCollectFragment mCouponTdCollectFragment;
    private CouponTdDetialFragment  mCouponTdDetialFragment;
    private CouponYesDetialFragment mCouponYesDetialFragment;
    private TodayDetialFragment     mExpectFragment;
    private CouponTdDetialFragment  mCurrentFragment;
    private String                  mTitle;
    private YestodayDetialFragment  mYesFragment;
    private CouponYesDetialFragment mYesDetialFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_coupon_info);
        initView();
        initEvent();
        initData();
        select(0);
    }


    private void initView() {
        mRb_coupon_today_collect = (RadioButton) findViewById(R.id.rb_coupon_today_collect);
        mRb_coupon_today_detial = (RadioButton) findViewById(R.id.rb_coupon_today_detial);
        mRb_coupon_yes_detial = (RadioButton) findViewById(R.id.rb_coupon_yes_detial);
        //返回键
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mTv_title.setText("交易查询");
    }

    private void initEvent() {
        mRb_coupon_today_collect.setOnClickListener(this);
        mRb_coupon_today_detial.setOnClickListener(this);
        mRb_coupon_yes_detial.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
    }


    private void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_coupon_today_collect:
                select(0);
                break;
            case R.id.rb_coupon_today_detial:
                select(1);
                break;
            case R.id.rb_coupon_yes_detial:
                select(2);
                break;
            case R.id.iv_back:
                this.finish();
                break;
        }
    }

    private void select(int i) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();//开启一个事物
        hintFragment(transaction);//先隐藏fragment
        switch (i) {
            case 0://今日汇总
                if (mCouponTdCollectFragment == null) {
                    mCouponTdCollectFragment = new CouponTdCollectFragment();
                    transaction.add(R.id.fragment_counpon_content, mCouponTdCollectFragment);
                } else {
                    transaction.show(mCouponTdCollectFragment);
                }
                break;
            case 1://今日明细
                if (mCouponTdDetialFragment == null) {
                    mCouponTdDetialFragment = new CouponTdDetialFragment();
                    transaction.add(R.id.fragment_counpon_content, mCouponTdDetialFragment);
                } else {
                    transaction.show(mCouponTdDetialFragment);
                }
                break;
            case 2://昨日明细
                if (mCouponYesDetialFragment == null) {
                    mCouponYesDetialFragment = new CouponYesDetialFragment();
                    transaction.add(R.id.fragment_counpon_content, mCouponYesDetialFragment);
                } else {
                    transaction.show(mCouponYesDetialFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hintFragment(FragmentTransaction transaction) {
        if (mCouponTdCollectFragment != null) {
            transaction.hide(mCouponTdCollectFragment);
        }
        if (mCouponTdDetialFragment != null) {
            transaction.hide(mCouponTdDetialFragment);
        }
        if (mCouponYesDetialFragment != null) {
            transaction.hide(mCouponYesDetialFragment);
        }
        if (mExpectFragment != null) {
            transaction.hide(mExpectFragment);
        }
        if (mYesFragment != null) {
            transaction.hide(mYesFragment);
        }
        if (mYesDetialFragment != null) {
            transaction.hide(mYesDetialFragment);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getCouponTdMsgEvent(FlashUiEvent event) {
        if (event.flag == Fields.FLASH_UI_TYPE_COUPON_TD) {
            mExpectFragment = (TodayDetialFragment) event.getExpectFragment();
            mCurrentFragment = (CouponTdDetialFragment) event.getCurrentFragment();
            mTitle = event.getTitle();
            event.getFlag();
        }
        if (event.getFlag() == Fields.COUPON_TYPE_QUERY_TRANS_DETAILS_YESTD) {
            mYesFragment = (YestodayDetialFragment) event.getExpectFragment();
            mYesDetialFragment = (CouponYesDetialFragment) event.getCurrentFragment();
            mTitle = event.getTitle();
            event.getFlag();
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
