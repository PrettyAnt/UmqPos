package unionlive.com.umqpos.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.OrderDetialAdapter;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.DetialResponse;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.ui.dialog.DialogStyleCancleActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.LdPrintStatus;
import unionlive.com.umqpos.utils.LdPrintWaimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/17 13:27
 * @describe 订单详情这块已处理和未处理公用的一个界面，他们之间的区别是订单状态不同--->>显示的按钮不同
 * 如果上送的报文的状态的101
 * -->>则该订单为已处理订单
 * --->>显示的botton分别为取消接单和重新打印
 * --->>点击取消接单后按钮变为灰色，重新打印的button消失
 * ---->>右上角的状态也需要改变
 * ---------------------------------------------------
 * 如果上送的报文为-1
 * --->>则该订单为未处理订单
 * --->>显示的button分别为拒绝接单和打印接单
 * ----->>点击拒绝接单后button变为拒绝接单，打印接单的button的图标消失
 * --->>右上角的状态也需要改变
 */
public class OrderDetialActivity extends BaseActivity implements View.OnClickListener {

    private TextView     mTv_untreate_viewOrderId;
    private TextView     mTv_untreate_channelName;
    private TextView     mTv_untreate_status;
    private TextView     mTv_untreate_orderId;
    private TextView     mTv_untreate_time;
    private TextView     mTv_untreate_expecttime;
    private TextView     mTv_untreate_info;
    private TextView     mTv_untreate_context;
    private TextView     mTv_untreate_income;
    private TextView     mTv_untreate_payType;
    private LinearLayout mLl_btn_order;
    private Button       mBt_wm_cancle_order;
    private Button       mBt_wm_reprint;
    private RecyclerView mRv_order_detial;
    private TextView     mTv_order_detial_deliveryAmount;
    private TextView     mTv_order_detial_packageAmount;
    private TextView     mTv_order_detial_discountAmount;
    private TextView     mTv_order_detial_shopAmount;
    private TextView     mTv_order_detial_orderAmount;
    private TextView     mTv_order_detial_remark;
    private String       mCancleOrder;
    private String       mPrintOrder;

    List<DetialResponse.BodyBean.OrderProdsBean> mProdsList = new ArrayList<>();
    private String             mOrderId;
    private ImageView          mIv_back;
    private TextView           mTv_title;
    private OrderDetialAdapter mOrderDetialAdapter;
    private int                mStatus;
    private int                mCurrentOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detial);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mTv_untreate_viewOrderId = (TextView) findViewById(R.id.tv_untreate_viewOrderId);
        mTv_untreate_channelName = (TextView) findViewById(R.id.tv_untreate_channelName);
        mTv_untreate_status = (TextView) findViewById(R.id.tv_untreate_status);
        mTv_untreate_orderId = (TextView) findViewById(R.id.tv_untreate_orderId);
        mTv_untreate_time = (TextView) findViewById(R.id.tv_untreate_time);
        mTv_untreate_expecttime = (TextView) findViewById(R.id.tv_untreate_expecttime);
        mTv_untreate_info = (TextView) findViewById(R.id.tv_untreate_info);
        mTv_untreate_context = (TextView) findViewById(R.id.tv_untreate_context);
        mTv_untreate_income = (TextView) findViewById(R.id.tv_untreate_income);
        mTv_untreate_payType = (TextView) findViewById(R.id.tv_untreate_payType);

        mLl_btn_order = (LinearLayout) findViewById(R.id.ll_btn_order);

        mBt_wm_cancle_order = (Button) findViewById(R.id.bt_wm_cancle_order);
        mBt_wm_reprint = (Button) findViewById(R.id.bt_wm_reprint);

        mRv_order_detial = (RecyclerView) findViewById(R.id.rv_order_detial);

        mTv_order_detial_deliveryAmount = (TextView) findViewById(R.id.tv_order_detial_deliveryAmount);
        mTv_order_detial_packageAmount = (TextView) findViewById(R.id.tv_order_detial_packageAmount);
        mTv_order_detial_discountAmount = (TextView) findViewById(R.id.tv_order_detial_discountAmount);
        mTv_order_detial_shopAmount = (TextView) findViewById(R.id.tv_order_detial_shopAmount);
        mTv_order_detial_orderAmount = (TextView) findViewById(R.id.tv_order_detial_orderAmount);
        mTv_order_detial_remark = (TextView) findViewById(R.id.tv_order_detial_remark);

        mIv_back = (ImageView) findViewById(R.id.iv_back);
        mTv_title = (TextView) findViewById(R.id.tv_title);
    }

    private void initEvent() {
        getTicketsStatus(Fields.ORDER_DETIAL_STATE);//得到UI的状态
        mIv_back.setOnClickListener(this);
        mBt_wm_cancle_order.setOnClickListener(this);
        mBt_wm_reprint.setOnClickListener(this);

        mRv_order_detial.setLayoutManager(new LinearLayoutManager(this));
        mOrderDetialAdapter = new OrderDetialAdapter(this, mProdsList);
        mRv_order_detial.setAdapter(mOrderDetialAdapter);
    }

    /**
     * 判断当前是已处理还是未处理，然后设置状态
     * @param orderDetialState
     */
    private void getTicketsStatus(int orderDetialState) {
        switch (orderDetialState) {
            case 101://已处理订单
                mBt_wm_cancle_order.setText("取消接单");
                mBt_wm_reprint.setText("重新打印");
                mCurrentOrderStatus = 9;//取消接单
                break;
            case -1://未处理订单
                mBt_wm_cancle_order.setText("拒绝接单");
                mBt_wm_reprint.setText("打印接单");
                mCurrentOrderStatus = 8;//拒绝接单
                break;
        }
    }

    private void initData() {
        mOrderId = getIntent().getStringExtra("orderId");
        String currentTitle = getIntent().getStringExtra("currentTitle");
        mTv_title.setText(currentTitle);
        String enOrderDetialStr = InPutJsonData.T202(this, Fields.loginsessionId, mOrderId, "");//得到上送的报文
        NetUtil.waiMaiNetWorkUtil(enOrderDetialStr, Fields.channelId,
                DetialResponse.class, this, Fields.ORDER_TYPE_DETIAL_SHOW_TICKS);//得到报文，通过EventBus获取
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wm_cancle_order://拒绝接单
                //已处理-->>取消接单  未处理-->>拒绝接单
                //当前的订单状态8：拒绝接单  9:取消接单  101：已处理订单
                Intent intent1 = new Intent(this, DialogStyleCancleActivity.class);
                intent1.putExtra("cancle_orderId", mOrderId);
                if (Fields.ORDER_DETIAL_STATE == 101) {//已处理订单
                    intent1.putExtra("cancle_state", Fields.Cancle_order);
                } else {
                    intent1.putExtra("cancle_state", Fields.Refuse_order);
                }
                startActivity(intent1);
                break;
            case R.id.bt_wm_reprint:
                //调用手动接单的接口--->>
                if (Fields.ORDER_DETIAL_STATE == 101) {//已处理订单----
                    //已处理的订单--->>直接去进行重打印操作
                    String t202 = InPutJsonData.T202(this, Fields.loginsessionId, mOrderId, "");
                    NetUtil.waiMaiNetWorkUtil(t202, Fields.channelId, DetialResponse.class, this,
                            Fields.ORDER_TYPE_DETIAL_PRINT);//将消息发送给打印
                } else if (Fields.ORDER_DETIAL_STATE == -1) {//未处理订单
                    //未处理订单--->>打印接单---->>先调用确认接单的接口---->>确认接单完成后--->>调用订单详情接口--->>得到数据开始打印
                    String t301 = InPutJsonData.T301(this, Fields.loginsessionId, mOrderId, 1);
                    LdPrintStatus.startPrint();
                    if (TitleInfomation.printStatus == Printer.ERROR_NONE
                            || TitleInfomation.printStatus == Printer.ERROR_BUSY) {
                        NetUtil.waiMaiNetWorkUtil(t301, Fields.channelId, OpenWmResponse.class, this,
                                Fields.ORDER_TYPE_DETIAL_RECEIVE_ORDER_PRINT);//确定接单
                    }
                }


                break;
            case R.id.iv_back:
                this.finish();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_DETIAL_SHOW_TICKS) {//得到订单详情的数据
            DetialResponse detialResponse = (DetialResponse) event.getT();
            switch (detialResponse.header.returnCode) {
                case "0000":
                    if (detialResponse.body != null) {
                        setData(detialResponse.body);//设置数据
                        List<DetialResponse.BodyBean.OrderProdsBean> orderProds = detialResponse.body.orderProds;
                        mProdsList.clear();
                        mProdsList.addAll(orderProds);
                    }
                    if (mOrderDetialAdapter != null) {
                        mOrderDetialAdapter.notifyDataSetChanged();
                    }
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(this, detialResponse.header.returnMessage
                            + ":" + detialResponse.header.returnCode, null);//弹框
                    InitDataHelper.sendError(this, detialResponse.header.returnMessage
                            + ":" + detialResponse.header.returnCode);//上传错误日志
                    break;
            }

        }
        //本类订单详情的打印--->>先确认订单
        if (event.getOrderType() == Fields.ORDER_TYPE_DETIAL_RECEIVE_ORDER_PRINT) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            String returnCode = openWmResponse.header.returnCode;
            switch (returnCode) {
                case "0000"://确认接单成功
                    //开始进行订单详情查询的操作
                    String t202 = InPutJsonData.T202(this, Fields.loginsessionId, mOrderId, "");
                    NetUtil.waiMaiNetWorkUtil(t202, Fields.channelId, DetialResponse.class, this,
                            Fields.ORDER_TYPE_DETIAL_PRINT);//将消息发送给打印
                    getOrderStatus(1);
                    InitDataHelper.flush(this);
                    InitDataHelper.getTitleMsg(this);//确定订单或者取消订单,都需要初始化标题一下
                    break;
                default:
                    InitDataHelper.sendError(this, openWmResponse.header.returnMessage + ":" +
                            openWmResponse.header.returnCode);//上送错误信息
                    break;
            }
        }
        //查详情--->>去打印
        if (event.getOrderType() == Fields.ORDER_TYPE_DETIAL_PRINT) {
            DetialResponse detialResponse = (DetialResponse) event.getT();
            switch (detialResponse.header.returnCode) {
                case "0000":
                    //得到实体类的信息，
//                    Printer landiPrinter = Printer.getInstance();
                    LdPrintWaimai landiPrintWaimai = LdPrintWaimai.getInstance();
                    if (Fields.ORDER_DETIAL_STATE == 101) {//如果当前状态是101,说明这个是已处理的订单
                        landiPrintWaimai.setIsReprint(true);
                    }
                    landiPrintWaimai.setT(detialResponse);
                    try {
                        landiPrintWaimai.start();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(this, detialResponse.header.returnMessage
                            + ":" + detialResponse.header.returnCode, null);//弹框
                    InitDataHelper.sendError(this, detialResponse.header.returnMessage + ":" +
                            detialResponse.header.returnCode);//上送错误信息
                    break;
            }

        }


    }

    private void setData(DetialResponse.BodyBean body) {
        mTv_untreate_viewOrderId.setText("#" + body.orderSeq);
        mTv_untreate_channelName.setText(body.channelName == null ? "--" : body.channelName);
        mStatus = body.status;
        mTv_untreate_status.setText(getOrderStatus(mStatus));
        mTv_untreate_orderId.setText(body.orderId == null ? "--" : body.orderId);//订单编号.setText(body.);
        if (body.orderDate != null && body.orderTime != null) {
            mTv_untreate_time.setText(getFormartTime(body.orderDate, body.orderTime));//下单时间.setText(body.);
        }
        if (!TextUtils.isEmpty(body.expectDate) && !TextUtils.isEmpty(body.expectTime)) {
            mTv_untreate_expecttime.setText(getFormartTime(body.expectDate, body.expectTime));//期望送达时间.setText(body.);
        } else {
            if (body.sendImmediately.equals("0")) {
                mTv_untreate_expecttime.setText("非立即送达");
            } else {
                mTv_untreate_expecttime.setText("立即送达");
            }
        }
        mTv_untreate_info.setText(getPeopleInfo(body));//收货人信息
        if (body.invoiceTitle == null) {//发票抬头.setText(body.);
            mTv_untreate_context.setVisibility(View.GONE);
        } else {
            mTv_untreate_context.setText(body.invoiceTitle);
        }
        mTv_untreate_income.setText(getFormatMoney(body.orderAmount));//订单总金额.setText(body.);
        mTv_untreate_payType.setText(body.payType == "0" ? "在线支付" : "货到付款");//支付类型.setText(body.);

        mTv_order_detial_deliveryAmount.setText(getFormatMoney(body.deliveryAmount));//配送费.setText(body);
        mTv_order_detial_packageAmount.setText(getFormatMoney(body.packageAmount));//餐盒费.setText(body);
        mTv_order_detial_discountAmount.setText(getFormatMoney(body.discountAmount));//优惠金额.setText(body);
        mTv_order_detial_shopAmount.setText(getFormatMoney(body.shopAmount));//本单收入.setText(body);
        mTv_order_detial_orderAmount.setText(getFormatMoney(body.orderAmount));//订单总金额.setText(body);
        mTv_order_detial_remark.setText(body.remark == null ? "无" : body.remark);//多加米饭.setText(body);
    }

    /**
     * 收货人信息
     *
     * @param body
     * @return
     */
    private String getPeopleInfo(DetialResponse.BodyBean body) {
        String info = body.userName + "\t\t" + body.userPhone + "\n" + body.userAddr;
        return info;
    }

    /**
     * 获取订单状态
     *
     * @param status
     * @return
     */
    private String getOrderStatus(int status) {
        String statusStr = "";
        switch (status) {
            case -1:
                statusStr = "待确认";
                mLl_btn_order.setVisibility(View.VISIBLE);
                break;
            case 1:
                statusStr = "已确认";
//                mBt_wm_cancle_order.setVisibility(View.GONE);
                mLl_btn_order.setVisibility(View.VISIBLE);
                mTv_untreate_status.setText(statusStr);
                mBt_wm_reprint.setText("重新打印");
                break;
            case 2:
                statusStr = "正在取单";
                mBt_wm_cancle_order.setVisibility(View.GONE);
                mLl_btn_order.setVisibility(View.VISIBLE);
                break;
            case 3:
                statusStr = "正在配送";
                mBt_wm_cancle_order.setVisibility(View.GONE);
                mLl_btn_order.setVisibility(View.VISIBLE);
                break;
            case 0:
                statusStr = "已完成";
                mLl_btn_order.setVisibility(View.VISIBLE);
                mBt_wm_cancle_order.setVisibility(View.GONE);
                mBt_wm_reprint.setText("重新打印");
                mTv_untreate_status.setTextColor(Color.argb(255,30,190,36));
                mTv_untreate_status.setText(statusStr);
                break;
            case 8://拒绝接单
                statusStr = "已拒绝";
                mTv_untreate_status.setTextColor(Color.RED);
                mLl_btn_order.setVisibility(View.GONE);
                mTv_untreate_status.setText(statusStr);
                break;
            case 9://取消接单
                statusStr = "已取消";
                mLl_btn_order.setVisibility(View.GONE);
                mTv_untreate_status.setTextColor(Color.RED);
                mTv_untreate_status.setText(statusStr);
                //                mBt_wm_cancle_order.setVisibility(View.GONE);
                break;
            case 10:
                statusStr = "已退款";
                mLl_btn_order.setVisibility(View.GONE);
                mTv_untreate_status.setTextColor(Color.RED);
                //                mBt_wm_cancle_order.setVisibility(View.GONE);
                break;
        }
        return statusStr;
    }

    /**
     * 获取支付总金额
     * "¥"+String.format("%.2f", mOrders.get(position).orderAmount / 100.00);
     *
     * @param
     * @return
     */
    private String getFormatMoney(String num) {
        int money = Integer.parseInt(num);
        return "¥" + String.format("%.2f", money / 100.00);
    }

    /**
     * 获取支付总金额
     * "¥"+String.format("%.2f", mOrders.get(position).orderAmount / 100.00);
     *
     * @param
     * @return
     */
    private String getFormatMoney(int num) {
        //        int money = Integer.parseInt(num);
        return "¥" + String.format("%.2f", num / 100.00);
    }

    /**
     * 送货时间
     *
     * @param
     * @return
     */
    private String getFormartTime(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "--";
        }
        String orderDate = str1 + str2;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf2.format(d);
    }

    /**
     * 接受来自个推的消息，判断是否退出当前的界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGtEvent(IsFinishCurrUiEvent event) {
        if (TextUtils.equals(event.getFlag(), "wm_01")) {
            if (event.isFinish()) {
                this.finish();
            }
        }
    }

}
