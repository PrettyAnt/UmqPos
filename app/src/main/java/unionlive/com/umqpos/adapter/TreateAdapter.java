package unionlive.com.umqpos.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.entitys.out_waimai.OrderTreateListResponse;
import unionlive.com.umqpos.holder.TreateViewHolder;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/20 9:53
 * @describe ${TODO}
 */
public class TreateAdapter extends RecyclerView.Adapter<TreateViewHolder> {
    private List<OrderTreateListResponse.BodyBean.OrdersBean> mOrders;
    private Context                                           mContext;
    private ItemClickListener                                 mItemClickListener;

    public TreateAdapter(List<OrderTreateListResponse.BodyBean.OrdersBean> orders, Context context) {
        this.mOrders = orders;
        this.mContext = context;
    }


    @Override
    public TreateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TreateViewHolder holder = new TreateViewHolder(LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_untreate_common, parent, false), mItemClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(TreateViewHolder holder, int position) {
        int status = mOrders.get(position).status;
        String orderStatus = getOrderStatus(holder, status);
        holder.mTv_untreate_current_status.setText(orderStatus);//显示当前的订单状态
        holder.mTv_untreate_orderId.setText("#" + mOrders.get(position).orderSeq);//今日订单编号
        holder.mTv_untreate_channelName.setText(mOrders.get(position).channelName);//渠道名称
        holder.mTv_untreate_num.setText(mOrders.get(position).orderId);//订单编号
        holder.mTv_untreate_time.setText(OrderTime(position));//下单时间
        holder.mTv_untreate_info.setText(OrderPeopleInfo(position));//收货人信息
        holder.mTv_untreate_context.setText(mOrders.get(position).invoiceTitle);//发票抬头
        holder.mTv_untreate_income.setText(OrderInCome(position));//本单收入
        holder.mTv_untreate_payType.setText(mOrders.get(position).payType == "0" ? "在线支付" : "货到付款");
//        holder.mIv_order_status_paystatus.setImageResource(R.mipmap.error);
        isImmediately(holder, position);
        //        holder.mLl_list_order_btn


    }
    private void isImmediately(TreateViewHolder holder, int position) {
        switch (mOrders.get(position).sendImmediately) {
            case "0"://非立即送达
                holder.mIv_order_status_paystatus.setImageResource(R.drawable.advance_order);
                holder.mIv_order_status_paystatus.setVisibility(View.VISIBLE);
                break;
            case "1"://立即送达
                holder.mIv_order_status_paystatus.setVisibility(View.INVISIBLE);
                break;
        }
    }
    /**
     * 获取订单状态
     *
     * @param holder
     * @param status
     * @return
     */
    private String getOrderStatus(TreateViewHolder holder, int status) {
        String statusStr = "";
        switch (status) {
            case -1:
                statusStr = "待确认";//需要显示的Butto的信息有-----取消接单和打印接单
                holder.mLl_list_order_btn.setVisibility(View.VISIBLE);//设置按钮的状态
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mBt_wm_cancle_order.setText("取消接单");
                holder.mBt_wm_reprint.setText("打印接单");
                break;
            case 1:
                statusStr = "已确认";//需要显示的Butto的信息有-----取消接单和重新打印
                holder.mLl_list_order_btn.setVisibility(View.VISIBLE);//设置按钮的状态
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mBt_wm_cancle_order.setVisibility(View.VISIBLE);
                holder.mBt_wm_cancle_order.setText("取消接单");
                holder.mBt_wm_reprint.setText("重新打印");
                break;
            case 2:
                statusStr = "正在取单";//需要显示的Butto的信息有-----取消接单和重新打印
                holder.mLl_list_order_btn.setVisibility(View.VISIBLE);//设置按钮的状态
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mBt_wm_cancle_order.setVisibility(View.GONE);
                holder.mBt_wm_reprint.setText("重新打印");
                break;
            case 3:
                statusStr = "正在配送";//需要显示的Butto的信息有-----取消接单和重新打印
                holder.mLl_list_order_btn.setVisibility(View.VISIBLE);//设置按钮的状态
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mBt_wm_cancle_order.setVisibility(View.GONE);
                holder.mBt_wm_reprint.setText("重新打印");
                break;
            case 0:
                statusStr = "已完成";//需要显示的Butto的信息有-----取消接单和重新打印
                holder.mLl_list_order_btn.setVisibility(View.VISIBLE);//设置按钮的状态
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mBt_wm_cancle_order.setVisibility(View.GONE);
                holder.mBt_wm_reprint.setText("重新打印");
                break;
            case 8:
                statusStr = "已拒绝";//需要显示的Butto的信息有-----取消接单和重新打印
                holder.mTv_untreate_current_status.setTextColor(Color.RED);//红色
                holder.mLl_list_order_btn.setVisibility(View.GONE);//设置按钮的状态
                break;
            case 9:
                statusStr = "已取消";//隐藏掉Button的按钮
                holder.mTv_untreate_current_status.setTextColor(Color.RED);//红色
                holder.mLl_list_order_btn.setVisibility(View.GONE);//设置按钮的状态
                break;
            case 10:
                statusStr = "已退款";//隐藏掉Button的按钮
                holder.mTv_untreate_current_status.setTextColor(Color.RED);//红色
                holder.mLl_list_order_btn.setVisibility(View.GONE);//设置按钮的状态
                break;
        }
        return statusStr;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    /**
     * 本单的金额
     *
     * @param position
     * @return
     */
    private String OrderInCome(int position) {
        return "¥" + String.format("%.2f", mOrders.get(position).orderAmount / 100.00);
    }
    //  String totalMoney = "¥"+String.format("%.2f",orderAmount/100.00);

    /**
     * 收货人的信息   姓名+手机号+地址
     *
     * @param position
     * @return
     */
    private String OrderPeopleInfo(int position) {
        String info = mOrders.get(position).userName + "\t\t" + mOrders.get(position).userPhone
                + "\n" + mOrders.get(position).userAddr;
        return info;
    }

    /**
     * 处理返回来的时间
     *
     * @param position
     * @return
     */
    private String OrderTime(int position) {
        String orderDate = mOrders.get(position).orderDate + mOrders.get(position).orderTime;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }

    @Override
    public int getItemCount() {
        //        System.out.println("-------------"+mOrders.get(1).body);
        return mOrders.size();
    }

}
