package unionlive.com.umqpos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/26 12:40
 * @describe ${TODO}
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public   ItemClickListener mListener;
    public TextView mTv_order_pro_list;

    public CategoryViewHolder(View inflate, ItemClickListener itemClickListener) {
        super(inflate);
        mTv_order_pro_list = (TextView) inflate.findViewById(R.id.tv_order_pro_list);

        this.mListener = itemClickListener;
        mTv_order_pro_list.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(mListener != null){
            mListener.onItemClick(view,getPosition());
        }
    }
}
