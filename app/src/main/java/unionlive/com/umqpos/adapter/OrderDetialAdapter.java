package unionlive.com.umqpos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.entitys.out_waimai.DetialResponse;
import unionlive.com.umqpos.holder.OrderDetialViewHolder;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/20 9:53
 * @describe 菜品详情
 */
public class OrderDetialAdapter extends RecyclerView.Adapter<OrderDetialViewHolder> {
    private List<DetialResponse.BodyBean.OrderProdsBean> prodsList;

    public OrderDetialAdapter(Context contex, List<DetialResponse.BodyBean.OrderProdsBean> prodsList) {
        mContex = contex;
        this.prodsList = prodsList;
    }

    private Context mContex;


    @Override
    public OrderDetialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderDetialViewHolder holder = new OrderDetialViewHolder(LayoutInflater
                .from(mContex)
                .inflate(R.layout.item_order_detial, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(OrderDetialViewHolder holder, int position) {
        holder.mTv_order_detial_amount.setText(getFormatMoney(prodsList.get(position).prodAmount));
        holder.mTv_order_detial_num.setText("x"+prodsList.get(position).prodNum);
        holder.mTv_order_detial_varied.setText(prodsList.get(position).prodName);
    }
    /**
     * 格式化金额
     * @param num
     * @return
     */
    private String getFormatMoney(int num) {
        return "¥"+String.format("%.2f",num / 100.00);
    }
    @Override
    public int getItemCount() {
        return prodsList.size();
    }
}
