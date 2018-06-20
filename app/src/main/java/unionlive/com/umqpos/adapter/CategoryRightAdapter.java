package unionlive.com.umqpos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.DishFoodType;
import unionlive.com.umqpos.holder.CategoryRightViewHolder;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/20 9:53
 * @describe 菜品详情
 */
public class CategoryRightAdapter extends RecyclerView.Adapter<CategoryRightViewHolder> {
    private  List<DishFoodType> foodTypeList;
    private Context                                    mContext;

    private ItemClickListener mItemClickListener;


    public CategoryRightAdapter(Context context, List<DishFoodType> foodTypeList) {
        this.mContext = context;
        this.foodTypeList=foodTypeList;
    }


    @Override
    public CategoryRightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoryRightViewHolder holder = new CategoryRightViewHolder(
                LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.item_rightlist_category, parent, false)
                , mItemClickListener
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(CategoryRightViewHolder holder, int position) {
//        Glide
//                .with(mContext)
//                .load(foodTypeList.get(position).pic)
//                .error(R.mipmap.ic_launcher)
//                .into(holder.mIv_order_category_pic);//显示图标
        holder.mIv_order_category_pic.setVisibility(View.GONE);
        holder.mTv_order_category_foodName.setText(foodTypeList.get(position).foodName);
        holder.mTv_order_category_norms.setText(foodTypeList.get(position).sizeName);

    }

    @Override
    public int getItemCount() {
        return foodTypeList.size();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }
}
