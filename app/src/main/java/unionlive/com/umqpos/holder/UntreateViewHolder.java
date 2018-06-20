package unionlive.com.umqpos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/21 11:30
 * @describe ${TODO}
 */
public class UntreateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ItemClickListener mListener;
    public  TextView          mTv_untreate_orderId;
    public  TextView          mTv_untreate_channelName;
    public  TextView          mTv_untreate_detial;
    public  TextView          mTv_untreate_num;
    public  TextView          mTv_untreate_time;
    public  TextView          mTv_untreate_info;
    public  TextView          mTv_untreate_context;
    public  TextView          mTv_untreate_income;
    public  TextView          mTv_untreate_payType;
    public  Button            mBt_wm_cancle_order;
    public  Button            mBt_wm_reprint;
    public TextView mTv_untreate_current_status;
    public LinearLayout mLl_list_order_btn;
    public  ImageView mIv_order_status_paystatus;//预订单
    private final LinearLayout mLinearLayout;

    public UntreateViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        mTv_untreate_orderId = (TextView) itemView.findViewById(R.id.tv_untreate_viewOrderId);
        mTv_untreate_channelName = (TextView) itemView.findViewById(R.id.tv_untreate_channelName);
        mTv_untreate_detial = (TextView) itemView.findViewById(R.id.tv_untreate_status);
        mTv_untreate_num = (TextView) itemView.findViewById(R.id.tv_untreate_orderId);
        mTv_untreate_time = (TextView) itemView.findViewById(R.id.tv_untreate_time);
        mTv_untreate_info = (TextView) itemView.findViewById(R.id.tv_untreate_info);
        mTv_untreate_context = (TextView) itemView.findViewById(R.id.tv_untreate_context);
        mTv_untreate_income = (TextView) itemView.findViewById(R.id.tv_untreate_income);
        mTv_untreate_payType = (TextView) itemView.findViewById(R.id.tv_untreate_payType);
        mBt_wm_cancle_order = (Button) itemView.findViewById(R.id.bt_wm_cancle_order);
        mBt_wm_reprint = (Button) itemView.findViewById(R.id.bt_wm_reprint);
        mTv_untreate_current_status = (TextView) itemView.findViewById(R.id.tv_untreate_current_status);
        mLl_list_order_btn = (LinearLayout) itemView.findViewById(R.id.ll_list_order_btn);
        mIv_order_status_paystatus = (ImageView) itemView.findViewById(R.id.iv_order_status_paystatus);
        mLinearLayout = (LinearLayout) itemView.findViewById(R.id.ll_order_list_detial);
        this.mListener = itemClickListener;
        mLinearLayout.setOnClickListener(this);
        mTv_untreate_detial.setOnClickListener(this);//给查看详情设置点击事件
        mBt_wm_cancle_order.setOnClickListener(this);
        mBt_wm_reprint.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if(mListener != null){
            mListener.onItemClick(view,getPosition());
        }
    }
}
