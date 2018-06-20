package unionlive.com.umqpos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import unionlive.com.umqpos.R;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/23 19:03
 * @describe ${TODO}
 */
public class OrderDetialViewHolder extends RecyclerView.ViewHolder {

    public TextView mTv_order_detial_amount;
    public TextView mTv_order_detial_num;
    public TextView mTv_order_detial_varied;

    public OrderDetialViewHolder(View itemView) {
        super(itemView);
        //总金额
        mTv_order_detial_amount = (TextView) itemView.findViewById(R.id.tv_order_detial_Amount);
        //数量
        mTv_order_detial_num = (TextView) itemView.findViewById(R.id.tv_order_detial_num);
        //品种
        mTv_order_detial_varied = (TextView) itemView.findViewById(R.id.tv_order_detial_varied);
    }
}
