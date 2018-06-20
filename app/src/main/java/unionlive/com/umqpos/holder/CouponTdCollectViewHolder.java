package unionlive.com.umqpos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import unionlive.com.umqpos.R;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/28 18:34
 * @describe ${TODO}
 */
public class CouponTdCollectViewHolder extends RecyclerView.ViewHolder {

    public final TextView mTv_shopName_today;
    public final TextView mTv_tick_num;

    public CouponTdCollectViewHolder(View inflate) {
        super(inflate);
        mTv_shopName_today = (TextView) inflate.findViewById(R.id.tv_shopName_today);
        mTv_tick_num = (TextView) inflate.findViewById(R.id.tv_tick_num);
    }
}
