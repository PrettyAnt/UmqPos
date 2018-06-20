package unionlive.com.umqpos.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.InitUiEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.event.OrderStatuEvent;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.ui.fragment.manage_wm.ManageFragment;
import unionlive.com.umqpos.ui.fragment.treate_wm.TreatedFragment;
import unionlive.com.umqpos.ui.fragment.untreate_wm.UnTreatedFragment;

import static unionlive.com.umqpos.bean.TitleInfomation.title_flag_treate;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/13 17:48
 * @describe 这是处理外卖的activity
 */

public class OrderFoodActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton       mRb_orderfood_treated;
    private RadioButton       mRb_orderfood_untreated;
    private RadioButton       mRb_orderfood_manage;
    private TreatedFragment   mTreatedFragment;
    private UnTreatedFragment mUnTreatedFragment;
    private ManageFragment    mManageFragment;
    private ImageView         mIv_back;
    private TextView          mTv_title;
    private ImageView         mIv_orderfood_titleright;
    private PopupWindow       mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderfood);
        initView();
        initEvent();
        select(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //初始化控件状态
    }

    private void initView() {
        mRb_orderfood_untreated = (RadioButton) findViewById(R.id.rb_orderfood_untreated);
        mRb_orderfood_treated = (RadioButton) findViewById(R.id.rb_orderfood_treated);
        mRb_orderfood_manage = (RadioButton) findViewById(R.id.rb_orderfood_manage);
        mIv_back = (ImageView) findViewById(R.id.iv_back);//返回键
        mTv_title = (TextView) findViewById(R.id.tv_title);
        mIv_orderfood_titleright = (ImageView) findViewById(R.id.iv_orderfood_titleright);//标题最右边的的图标
        initDrawable();
        mTv_title.setText("未处理");
    }

    private void initDrawable() {
        if (mRb_orderfood_untreated.isChecked()) {
            initDrawableTopSize(mRb_orderfood_untreated,R.drawable.bt_untreate_pressed);
            mRb_orderfood_untreated.setTextColor(Color.parseColor("#48a8fa"));
        } else {
            initDrawableTopSize(mRb_orderfood_untreated,R.drawable.bt_untreate_normal);
            mRb_orderfood_untreated.setTextColor(Color.parseColor("#333333"));
        }
        if (mRb_orderfood_treated.isChecked()) {
            initDrawableTopSize(mRb_orderfood_treated,R.drawable.bt_treate_pressed);
            mRb_orderfood_treated.setTextColor(Color.parseColor("#48a8fa"));
        } else {
            initDrawableTopSize(mRb_orderfood_treated, R.drawable.bt_treate_normal);
            mRb_orderfood_treated.setTextColor(Color.parseColor("#333333"));
        }
        if (mRb_orderfood_manage.isChecked()) {
            initDrawableTopSize(mRb_orderfood_manage,R.drawable.bt_manage_pressed);
            mRb_orderfood_manage.setTextColor(Color.parseColor("#48a8fa"));
        } else {
            initDrawableTopSize(mRb_orderfood_manage,R.drawable.bt_manage_normal);
            mRb_orderfood_manage.setTextColor(Color.parseColor("#333333"));
        }
    }

    /**
     * 设置drawableTop图片的大小
     * @param rb radiobutton
     * @param res 资源文件
     */
    private void initDrawableTopSize(RadioButton rb,int res) {
        Drawable drawable = ContextCompat.getDrawable(this, res);
        drawable.setBounds(0, 0, 55, 55);
        rb.setCompoundDrawables(null, drawable, null, null);
    }

    private void initEvent() {
        mRb_orderfood_treated.setOnClickListener(this);
        mRb_orderfood_untreated.setOnClickListener(this);
        mRb_orderfood_manage.setOnClickListener(this);
        mIv_orderfood_titleright.setOnClickListener(this);
        mIv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_orderfood_untreated:
                //                titlenum = 0;
                select(0);
                mTv_title.setText("未处理");
                mIv_orderfood_titleright.setVisibility(View.INVISIBLE);//设置右边的标题不可见
                initDrawable();
                break;
            case R.id.rb_orderfood_treated:
                //                titlenum = 1;
                select(1);
                mTv_title.setText(getTreateTitle(title_flag_treate));//已处理
                mIv_orderfood_titleright.setVisibility(View.VISIBLE);//设置右边的标题可见
                initDrawable();
                break;
            case R.id.rb_orderfood_manage:
                //                titlenum = 2;
                select(2);
                mTv_title.setText("管理");
                mIv_orderfood_titleright.setVisibility(View.INVISIBLE);//设置右边的标题不可见
                initDrawable();
                break;
            case R.id.iv_back://返回
                this.finish();
                break;
            case R.id.iv_orderfood_titleright:
                showPopuWindow();
                break;
            case R.id.tv_treate_advance://预订订单
                //通知已处理界面刷新适配器
                EventBus.getDefault().postSticky(new OrderStatuEvent(Fields.ORDER_STATUS_ADVANCE));
                title_flag_treate = 1;
                mTv_title.setText(getTreateTitle(1));
                mPopupWindow.dismiss();
                break;
            case R.id.tv_treate_chargeback://取消订单
                //通知已处理界面刷新适配器
                EventBus.getDefault().postSticky(new OrderStatuEvent(Fields.ORDER_STATUS_CANCEL));
                title_flag_treate = 2;
                mTv_title.setText(getTreateTitle(2));
                mPopupWindow.dismiss();
                break;
            case R.id.tv_treate_all://全部订单
                EventBus.getDefault().postSticky(new OrderStatuEvent(Fields.ORDER_STATUS_ALL));
                title_flag_treate = 0;
                mTv_title.setText(getTreateTitle(0));
                mPopupWindow.dismiss();
                break;
        }
    }

    private String getTreateTitle(int i) {
        String titleInfo="";
        switch (i) {
            case 0:
                titleInfo = "已处理(全部订单)";
                break;
            case 1:
                titleInfo = "已处理(预定订单)";
                break;
            case 2:
                titleInfo = "已处理(已取消订单)";
                break;
        }
        return titleInfo;
    }


    private void showPopuWindow() {//设置popupWindow
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuplayout, null);
        TextView tv_treate_advance = (TextView) contentView.findViewById(R.id.tv_treate_advance);
        TextView tv_treate_chargeback = (TextView) contentView.findViewById(R.id.tv_treate_chargeback);
        TextView tv_treate_all = (TextView) contentView.findViewById(R.id.tv_treate_all);
        tv_treate_advance.setOnClickListener(this);
        tv_treate_chargeback.setOnClickListener(this);
        tv_treate_all.setOnClickListener(this);

        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.contextMenuAnim);
        mPopupWindow.showAsDropDown(mIv_orderfood_titleright);
    }

    private void select(int i) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();//开启一个事物
        hintFragment(transaction);//先隐藏fragment
        switch (i) {
            case 0:
                if (mUnTreatedFragment == null) {
                    mUnTreatedFragment = new UnTreatedFragment();
                    transaction.add(R.id.fragment_content, mUnTreatedFragment);
                } else {
                    transaction.show(mUnTreatedFragment);//未处理界面
                }
                break;
            case 1:
                if (mTreatedFragment == null) {
                    mTreatedFragment = new TreatedFragment();
                    transaction.add(R.id.fragment_content, mTreatedFragment);
                } else {
                    transaction.show(mTreatedFragment);//已处理界面
                }
                break;
            case 2:
                if (mManageFragment == null) {
                    mManageFragment = new ManageFragment();
                    transaction.add(R.id.fragment_content, mManageFragment);
                } else {
                    transaction.show(mManageFragment);//管理界面
                }
                break;
        }
        transaction.commit();
    }

    private void hintFragment(FragmentTransaction transaction) {
        if (mTreatedFragment != null) {
            transaction.hide(mTreatedFragment);
        }
        if (mUnTreatedFragment != null) {
            transaction.hide(mUnTreatedFragment);
        }
        if (mManageFragment != null) {
            transaction.hide(mManageFragment);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


    /**
     * 接受来自个推的消息，判断是否退出当前的界面
     * @param event     EXIT_LOADING_WAIMAI
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGtEvent(IsFinishCurrUiEvent event){
        if (TextUtils.equals(event.getFlag(),"wm_01")) {
                if (event.isFinish()) {
                    CancleOrderDialog.isFinishDialog(this,OrderFoodActivity.this,event.getTitleInfo());//判断是否退出当前的界面
                }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exitWaimaiEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.EXIT_LOADING_WAIMAI) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000":
                    TitleInfomation.initTitleData();
                    EventBus.getDefault().postSticky(new InitUiEvent(0));//通知baseActivity刷新界面
                    finish();
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(this, "退出失败", null);
                    break;
            }
            this.finish();
        }
    }

}
