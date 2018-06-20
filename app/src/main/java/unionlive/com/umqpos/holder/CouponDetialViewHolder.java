package unionlive.com.umqpos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/28 21:00
 * @describe ${TODO}
 */
public class CouponDetialViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView mTv_coupon_detial_info;
    public TextView mTv_coupon_refer_num;
    public TextView mTv_coupon_exchange_num;
    private  ItemClickListener mListener;
    private final LinearLayout mLl_coupon_detial;

    public CouponDetialViewHolder(View inflate, ItemClickListener itemClickListener) {
        super(inflate);
        mTv_coupon_detial_info = (TextView) inflate.findViewById(R.id.tv_coupon_detial_info);
        mTv_coupon_refer_num = (TextView) inflate.findViewById(R.id.tv_coupon_refer_num);
        mTv_coupon_exchange_num = (TextView) inflate.findViewById(R.id.tv_coupon_exchange_num);
        mLl_coupon_detial = (LinearLayout) inflate.findViewById(R.id.ll_coupon_detial);
        this.mListener = itemClickListener;
        mLl_coupon_detial.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (mListener != null) {
            mListener.onItemClick(view,getPosition());
        }
    }
}
