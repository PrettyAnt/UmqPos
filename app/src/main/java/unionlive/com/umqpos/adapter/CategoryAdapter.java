package unionlive.com.umqpos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.entitys.out_waimai.QueryCategoryResponse;
import unionlive.com.umqpos.holder.CategoryViewHolder;
import unionlive.com.umqpos.imp.ItemClickListener;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/20 9:53
 * @describe 菜品详情
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private Context                                        mContext;
    private List<QueryCategoryResponse.BodyBean.CatesBean> mCates;
    private ItemClickListener                              mItemClickListener;

    public CategoryAdapter(Context context,List<QueryCategoryResponse.BodyBean.CatesBean> cates) {
        this.mContext=context;
        this.mCates = cates;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CategoryViewHolder holder = new CategoryViewHolder(
                LayoutInflater
                        .from(mContext)
                        .inflate(R.layout.item_leftlist_category, parent, false)
                ,mItemClickListener
        );
        return holder;
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(ItemClickListener listener){
        this.mItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.mTv_order_pro_list.setText(mCates.get(position).cateName);

    }

    @Override
    public int getItemCount() {
        return mCates.size();
    }
}
