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
import unionlive.com.umqpos.entitys.out_waimai.OrderUnTreateListResponse;
import unionlive.com.umqpos.holder.UntreateViewHolder;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/20 9:53
 * @describe ${TODO}
 */
public class UntreateAdapter extends RecyclerView.Adapter<UntreateViewHolder> {
    private List<OrderUnTreateListResponse.BodyBean.OrdersBean> mOrders;
    private Context                                             mContext;
    private ItemClickListener                                   mItemClickListener;

    public UntreateAdapter(List<OrderUnTreateListResponse.BodyBean.OrdersBean> orders, Context context) {
        this.mOrders = orders;
        this.mContext = context;
    }


    @Override
    public UntreateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final UntreateViewHolder holder = new UntreateViewHolder(LayoutInflater
                .from(mContext)
                .inflate(R.layout.item_untreate_common, parent, false), mItemClickListener);

        return holder;
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public void onBindViewHolder(UntreateViewHolder holder, int position) {
        String orderStatus = getOrderStatus(mOrders.get(position).status, holder);
        holder.mTv_untreate_current_status.setText(orderStatus);//当前的订单状态
        holder.mTv_untreate_orderId.setText("#" + mOrders.get(position).orderSeq);//今日订单编号
        holder.mTv_untreate_channelName.setText(mOrders.get(position).channelName);//渠道名称
        holder.mTv_untreate_num.setText(mOrders.get(position).orderId);//订单编号
        holder.mTv_untreate_time.setText(OrderTime(position));//下单时间
        holder.mTv_untreate_info.setText(OrderPeopleInfo(position));//收货人信息
        holder.mTv_untreate_context.setText(mOrders.get(position).invoiceTitle);//发票抬头
        holder.mTv_untreate_income.setText(OrderInCome(position));//本单收入
        holder.mTv_untreate_payType.setText(mOrders.get(position).payType == "0" ? "在线支付" : "货到付款");
        isImmediately(holder, position);
    }

    private void isImmediately(UntreateViewHolder holder, int position) {
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
     * @param status
     * @param holder
     * @return
     */
    private String getOrderStatus(int status, UntreateViewHolder holder) {
        String statusStr = "";
        switch (status) {
            case -1:
                statusStr = "待确认";
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mBt_wm_cancle_order.setText("拒绝接单");
                holder.mBt_wm_reprint.setText("打印接单");
                holder.mLl_list_order_btn.setVisibility(View.VISIBLE);
                break;
            case 1:
                statusStr = "已确认";
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
            case 2:
                statusStr = "正在取单";
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
            case 3:
                statusStr = "正在配送";
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
            case 0:
                statusStr = "已完成";
                holder.mTv_untreate_current_status.setTextColor(Color.argb(255, 79, 194, 41));//蓝色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
            case 8:
                statusStr = "已拒绝";
                holder.mTv_untreate_current_status.setTextColor(Color.RED);//红色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
            case 9:
                statusStr = "已取消";
                holder.mTv_untreate_current_status.setTextColor(Color.RED);//红色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
            case 10:
                statusStr = "已退款";
                holder.mTv_untreate_current_status.setTextColor(Color.RED);//红色
                holder.mLl_list_order_btn.setVisibility(View.GONE);
                break;
        }
        return statusStr;
    }

    /**
     * 本单的金额
     *
     * @param
     * @return
     */
    private String OrderInCome(int position) {

        return "¥" + String.format("%.2f", mOrders.get(position).orderAmount / 100.00);
    }

    /**
     * 收货人的信息   姓名+手机号+地址
     *
     * @param position
     * @return
     */
    private String OrderPeopleInfo(int position) {
        final String info = mOrders.get(position).userName + "\t\t" + mOrders.get(position).userPhone
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
