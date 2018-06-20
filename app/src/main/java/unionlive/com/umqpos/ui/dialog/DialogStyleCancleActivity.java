package unionlive.com.umqpos.ui.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.CancleReason;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.NewOrderComing;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.utils.InitDataHelper;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/13 3:23
 * @describe ${TODO}
 */

public class DialogStyleCancleActivity extends BaseActivity implements View.OnClickListener {
    ArrayList<String> reason = new ArrayList<>();
    private RadioButton mRb_1;
    private RadioButton mRb_2;
    private RadioButton mRb_3;
    private RadioButton mRb_5;
    private RadioButton mRb_4;
    private RadioButton mRb_6;
    private RadioButton mRb_7;
    private RadioButton mRb_8;
    private RadioButton mRb_9;
    private RadioButton mRb_custom;
    private TextView    mTv_dialog_back;
    private TextView    mTv_dialog_cancle;
    private TextView    mTv_dialog_sure;
    private RadioGroup  mRg_cancle;
    ArrayList<Integer> num = new ArrayList<Integer>();
    private int flag = -2;//标记
    HashMap<Integer, String> cancleReasonInstance = CancleReason.getCancleReasonInstance();
    private EditText mEt_dialog_reason;
    private String mOrderId;
    private int mCancle_state;
    private TextView mTv_dialog_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_dialog_cancle_style);

        initView();
        initData();
        initEvent();
    }

    private void initData() {
        Intent intent = getIntent();
        mOrderId = intent.getStringExtra("cancle_orderId");
        mCancle_state = intent.getExtras().getInt("cancle_state");
        mTv_dialog_title.setText(getReasonData(mCancle_state));//设置标题
        num.clear();
        System.out.println("-------------原因信息----------"+cancleReasonInstance);
        Set<Map.Entry<Integer, String>> entries = cancleReasonInstance.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            int key = next.getKey();
            num.add(key);
        }
        for (int i = 0; i < num.size(); i++) {
            switch (i) {
                case 0:
                    mRb_1.setText(cancleReasonInstance.get(num.get(0)));
                    break;
                case 1:
                    mRb_2.setText(cancleReasonInstance.get(num.get(1)));
                    break;
                case 2:
                    mRb_3.setText(cancleReasonInstance.get(num.get(2)));
                    break;
                case 3:
                    mRb_4.setText(cancleReasonInstance.get(num.get(3)));
                    break;
                case 4:
                    mRb_5.setText(cancleReasonInstance.get(num.get(4)));
                    break;
                case 5:
                    mRb_6.setText(cancleReasonInstance.get(num.get(5)));
                    break;
                case 6:
                    mRb_7.setText(cancleReasonInstance.get(num.get(6)));
                    break;
                case 7:
                    mRb_8.setText(cancleReasonInstance.get(num.get(7)));
                    break;
                case 8:
                    mRb_9.setText(cancleReasonInstance.get(num.get(8)));
                    break;


            }
        }
    }

    private String getReasonData(int cancle_state) {
        String reason = "";
        switch (cancle_state) {
            case 8:
                reason = "请选择拒绝接单的原因:";
                break;
            case 9:
                reason = "请选择取消接单的原因:";
                break;
        }
        return reason;
    }

    private void initView() {
        mLl_waimai_state.setVisibility(View.GONE);
        mTv_dialog_title = (TextView) findViewById(R.id.tv_dialog_title);
        mRb_custom = (RadioButton) findViewById(R.id.rb_custom);//自定义原因
        mRg_cancle = (RadioGroup) findViewById(R.id.rg_cancle);//单选框原因
        mRb_1 = (RadioButton) findViewById(R.id.rb_1);
        mRb_2 = (RadioButton) findViewById(R.id.rb_2);
        mRb_3 = (RadioButton) findViewById(R.id.rb_3);
        mRb_4 = (RadioButton) findViewById(R.id.rb_4);
        mRb_5 = (RadioButton) findViewById(R.id.rb_5);
        mRb_6 = (RadioButton) findViewById(R.id.rb_6);
        mRb_7 = (RadioButton) findViewById(R.id.rb_7);
        mRb_8 = (RadioButton) findViewById(R.id.rb_8);
        mRb_9 = (RadioButton) findViewById(R.id.rb_9);
        mTv_dialog_back = (TextView) findViewById(R.id.tv_dialog_back);//返回
        mTv_dialog_cancle = (TextView) findViewById(R.id.tv_dialog_cancle);//取消
        mTv_dialog_sure = (TextView) findViewById(R.id.tv_dialog_sure);//确定
        mEt_dialog_reason = (EditText) findViewById(R.id.et_dialog_reason);

    }

    private void initEvent() {
        //        mRg_cancle.setOnClickListener(this);
        mRb_1.setOnClickListener(this);
        mRb_2.setOnClickListener(this);
        mRb_3.setOnClickListener(this);
        mRb_4.setOnClickListener(this);
        mRb_5.setOnClickListener(this);
        mRb_6.setOnClickListener(this);
        mRb_7.setOnClickListener(this);
        mRb_8.setOnClickListener(this);
        mRb_9.setOnClickListener(this);
        mTv_dialog_back.setOnClickListener(this);//返回
        mTv_dialog_cancle.setOnClickListener(this);//取消
        mTv_dialog_sure.setOnClickListener(this);//确定
        mRb_custom.setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_1:
                flag = 0;
                break;
            case R.id.rb_2:
                flag = 1;
                break;
            case R.id.rb_3:
                flag = 2;
                break;
            case R.id.rb_4:
                flag = 3;
                break;
            case R.id.rb_5:
                flag = 4;
                break;
            case R.id.rb_6:
                flag = 5;
                break;
            case R.id.rb_7:
                flag = 6;
                break;
            case R.id.rb_8:
                flag = 7;
                break;
            case R.id.rb_9:
                flag = 8;
                break;
            case R.id.tv_dialog_sure:
                int intReason = 1;
                if (flag == -2) {//如果什么都没操作，则弹出提示框
                    MyToast.show(this, "请选择原因!", false);
                } else {
                    try {
                        if (flag == -1) {
                            intReason = -1;//此时为自定义原因
                        } else {
                            intReason = num.get(flag);//取消或者拒绝的原因
                        }
                        sendReason(intReason);
//                        finish();
                    } catch (IndexOutOfBoundsException e) {
                        e.printStackTrace();//数组越界异常
                    }
                }

                break;
            case R.id.tv_dialog_cancle://取消
                finish();
                break;
            case R.id.tv_dialog_back://返回

                mTv_dialog_back.setVisibility(View.GONE);//设置返回键不可见
                mEt_dialog_reason.setVisibility(View.GONE);
                mRg_cancle.setVisibility(View.VISIBLE);//设置取点选的所有RadioGroup可见
                break;
            case R.id.rb_custom://自定义取消原因
                flag = -1;
                mRg_cancle.setVisibility(View.GONE);//设置取点选的所有RadioGroup不可见
                mTv_dialog_back.setVisibility(View.VISIBLE);//设置返回键可见
                mEt_dialog_reason.setVisibility(View.VISIBLE);//设置编辑框可见
                break;
        }
    }

    private void sendReason(int intReason) {
        String t302 = InPutJsonData.T302(
                this,
                Fields.loginsessionId,
                mOrderId, //订单编号
                mCancle_state,//订单状态
                intReason, //取消的原因类型----------->选择对应的数字
                mEt_dialog_reason.getText().toString().trim(),//自定义取消的原因
                "");
        NetUtil.waiMaiNetWorkUtil(t302, Fields.channelId, OpenWmResponse.class,
                this, Fields.ORDER_TYPE_CANCLE_REFUSE_SUCCESS);  // FIXME: 2017/1/4
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_CANCLE_REFUSE_SUCCESS) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000"://取消订单成功
                    flushUi();
                    //                    InitDataHelper.flashUI(getActivity());
                    //                    InitDataHelper.getTitleMsg(getActivity());//确定订单或者取消订单,都需要初始化标题一下
                    break;
                default://取消订单失败
                    flushUi();
                    MyToast.show(this, openWmResponse.header.returnMessage, false);
                    InitDataHelper.sendError(this, openWmResponse.header.returnMessage
                            + openWmResponse.header.returnCode);//取消订单失败，上送错误信息
                    break;
            }
        }
    }

    private void flushUi() {
        EventBus.getDefault().postSticky(new NewOrderComing(true));//发送一条消息通知各个界面更新ui
        InitDataHelper.getTitleMsg(this);//确定订单或者取消订单,都需要初始化标题一下
        finish();
    }

}
