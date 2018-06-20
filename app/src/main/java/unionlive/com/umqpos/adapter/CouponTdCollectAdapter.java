package unionlive.com.umqpos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransBasicResponse;
import unionlive.com.umqpos.holder.CouponTdCollectViewHolder;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/28 18:32
 * @describe ${TODO}
 */
public class CouponTdCollectAdapter extends RecyclerView.Adapter<CouponTdCollectViewHolder> {

    private List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo;
    private Context                                              context;

    public CouponTdCollectAdapter(Context context, List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo) {
        this.context = context;
        this.transInfo = transInfo;
    }

    @Override
    public CouponTdCollectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CouponTdCollectViewHolder holder = new CouponTdCollectViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_coupon_today, parent, false)
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(CouponTdCollectViewHolder holder, int position) {
        holder.mTv_shopName_today.setText(transInfo.get(position).prodName);
        holder.mTv_tick_num.setText(transInfo.get(position).amount+" 笔");
    }

    @Override
    public int getItemCount() {
        return transInfo.size();
    }
}
