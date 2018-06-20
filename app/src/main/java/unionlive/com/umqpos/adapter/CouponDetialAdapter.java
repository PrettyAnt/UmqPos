package unionlive.com.umqpos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransBasicResponse;
import unionlive.com.umqpos.holder.CouponDetialViewHolder;
import unionlive.com.umqpos.imp.ItemClickListener;
import unionlive.com.umqpos.utils.TimeHelper;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/28 20:59
 * @describe ${TODO}
 */
public class CouponDetialAdapter extends RecyclerView.Adapter<CouponDetialViewHolder> {


    private Context                                              context;
    private List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo;
    private ItemClickListener                                    mItemClickListener;

    public CouponDetialAdapter(Context context, List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo) {
        this.context = context;
        this.transInfo = transInfo;
    }

    @Override
    public CouponDetialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CouponDetialViewHolder detialViewHolder = new CouponDetialViewHolder(
                LayoutInflater
                        .from(context)
                        .inflate(R.layout.item_coupon_detial, parent, false), mItemClickListener
        );
        return detialViewHolder;
    }

    @Override
    public void onBindViewHolder(CouponDetialViewHolder holder, int position) {
        holder.mTv_coupon_detial_info.setText(getDetialInfo(position));
        holder.mTv_coupon_refer_num.setText("参考号:" + transInfo.get(position).authCode);
        holder.mTv_coupon_exchange_num.setText(transInfo.get(position).prodCate==1?"电子卡":"电子券");
    }

    private String getDetialInfo(int position) {
        String prodName = transInfo.get(position).prodName;
        String prodId = transInfo.get(position).prodId;
        String no = transInfo.get(position).no;
        no=getTransNum(no);//券号
        String amount = transInfo.get(position).amount;
        if (transInfo.get(position).prodCate == 1) {//说明该商品为电子卡
            //            amount=amount/100.00;
            amount = Integer.getInteger(amount) / 100.00 + "元";
        } else {
            amount = amount + "次";
        }
        String transTime = TimeHelper.transTime(transInfo.get(position).transTime);

        return prodName
                +"\n礼券编号:\t"+prodId
                +"\n券　　号:\t"+no
                +"\n兑换次数:\t"+amount
                +"\n交易时间:\t"+transTime;
    }

    @Override
    public int getItemCount() {
        return transInfo.size();
    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener) {
        this.mItemClickListener = listener;
    }
    private String getTransNum(String no) {
        String behind = no.substring(0, 4);
        String afterStr = no.substring(no.length() - 4, no.length());
        return behind+" **** **** "+afterStr;
    }
}
