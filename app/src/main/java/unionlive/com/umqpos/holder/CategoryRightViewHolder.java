package unionlive.com.umqpos.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/26 16:58
 * @describe ${TODO}
 */
public class CategoryRightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private  ItemClickListener mListener;
    public ImageView mIv_order_category_pic;
    public TextView mTv_order_category_foodName;
    public TextView mTv_order_category_norms;
    public TextView mTv_order_category_reset;

    public CategoryRightViewHolder(View itemView,ItemClickListener itemClickListener) {
        super(itemView);
        //菜品图片
        mIv_order_category_pic = (ImageView) itemView.findViewById(R.id.iv_order_category_pic);
        //菜品名
        mTv_order_category_foodName = (TextView) itemView.findViewById(R.id.tv_order_category_foodName);
        //菜品规格
        mTv_order_category_norms = (TextView) itemView.findViewById(R.id.tv_order_category_norms);
        //修改库存
        mTv_order_category_reset = (TextView) itemView.findViewById(R.id.tv_order_category_reset);
        this.mListener = itemClickListener;
        mTv_order_category_reset.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(mListener != null){
            mListener.onItemClick(view,getPosition());
        }
    }
}
