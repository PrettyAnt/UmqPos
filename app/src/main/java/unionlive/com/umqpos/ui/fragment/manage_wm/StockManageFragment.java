package unionlive.com.umqpos.ui.fragment.manage_wm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.CategoryAdapter;
import unionlive.com.umqpos.adapter.CategoryRightAdapter;
import unionlive.com.umqpos.bean.DishFoodType;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.QueryCategoryResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryDishResponse;
import unionlive.com.umqpos.event.CateIdEvent;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.StockChangedEvent;
import unionlive.com.umqpos.imp.ItemClickListener;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.ui.activity.OrderResertCategoryActivity;
import unionlive.com.umqpos.utils.DividerItemDecoration;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.SPUtils;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/15 17:17
 * @describe 这是库存管理的fragment
 */
public class StockManageFragment extends BaseFragment implements ItemClickListener {

    private RecyclerView mRv_order_pro_list;
    private CategoryAdapter mCategoryAdapter;
    List<QueryCategoryResponse.BodyBean.CatesBean> mCates = new ArrayList<>();
    private RecyclerView         mRv_order_right;
    private CategoryRightAdapter mCategoryRightAdapter;
    private String mCateId;

    private int pageIndex = 0;//当前的页码
    private boolean isLoading = false, isEnd = false;
    private final int PAGE_SIZE = 10;
    private SwipeRefreshLayout mSrf_waimai_foodlist;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manage_stockmanage, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        initData();
    }

    /**
     * 得到左边的数据
     */
    private void initData() {
        //菜品分类查询，得到左边的菜品数据--->>cateId
        String categoryStr = InPutJsonData.T403(Fields.loginsessionId, (String) SPUtils.get(getActivity(), Fields.shopId, ""), "", "");
        NetUtil.waiMaiNetWorkUtil(categoryStr, Fields.channelId,
                QueryCategoryResponse.class, getActivity(), Fields.ORDER_TYPE_CATEGORY);
    }


    private void initView(View view) {
        mRv_order_pro_list = (RecyclerView) view.findViewById(R.id.rv_order_pro_list);
        mRv_order_right = (RecyclerView) view.findViewById(R.id.rv_order_right);
        mSrf_waimai_foodlist = (SwipeRefreshLayout) view.findViewById(R.id.srf_waimai_foodlist);

    }

    private void initEvent() {
        mRv_order_pro_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv_order_pro_list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mCategoryAdapter = new CategoryAdapter(getActivity(), mCates);
        mRv_order_pro_list.setAdapter(mCategoryAdapter);
        mCategoryAdapter.setOnItemClickListener(this);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRv_order_right.setLayoutManager(mLinearLayoutManager);//FIXME
        mRv_order_right.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mCategoryRightAdapter = new CategoryRightAdapter(getActivity(),mDishFoodTypeList);
        mRv_order_right.setAdapter(mCategoryRightAdapter);
        mCategoryRightAdapter.setItemClickListener(this);//修改库存设置点击事件
        //下拉加载
        mSrf_waimai_foodlist.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRightDatas(mCateId);//刷新右边的菜品
                pageIndex = 0;//当前的页码
                isEnd = false;
            }
        });
        //上拉刷新
        mRv_order_right.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition + 1 == mCategoryRightAdapter.getItemCount()) {
                    boolean isRefreshing = mSrf_waimai_foodlist.isRefreshing();
                    if (!isRefreshing && !isLoading && !isEnd) {
                        isLoading = true;
                        pageIndex++;
                        getRightDatas(mCateId);
                    }
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        switch (view.getId()) {
            case R.id.tv_order_pro_list:
                String cateId = mCates.get(postion).cateId;
                getRightDatas(cateId);//获取右边数据
                //                EventBus.getDefault().postSticky(new OrderCategoryEvent(cateId));//让右边的fragment更新数据
                //再次请求网络，刷新适配器 // FIXME: 2016/12/26 ........2
                break;
            case R.id.tv_order_category_reset:
                //// FIXME: 2017/1/18 在这里需要将fragment替换成activity会更好
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager()
//                        .beginTransaction();//开启一个事物
//                transaction.hide(this);
//                OrderReserCategoryFragment orderReserCategoryFragment = new OrderReserCategoryFragment();
//                transaction.add(R.id.fragment_content, orderReserCategoryFragment);//替换fragment
//                transaction.commit();
                Intent intent = new Intent(getActivity(),OrderResertCategoryActivity.class);
                startActivity(intent);
                EventBus.getDefault().postSticky(new CateIdEvent(mDishFoodTypeList,postion,this));
                //fixme 进入修改库存界面   将本类的实例通过EventBus传给orderFoodActiity来替换fragment
                break;
        }
    }
    List<DishFoodType> mDishFoodTypeList = new ArrayList<>();
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void OrderMangeEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_CATEGORY) {
            mCates.clear();
            QueryCategoryResponse categoryResponse = (QueryCategoryResponse) event.getT();
            switch (categoryResponse.header.returnCode) {
                case "0000":
                    if (categoryResponse == null || categoryResponse.body == null || categoryResponse.body.cates == null) {
                        return;
                    }
                    List<QueryCategoryResponse.BodyBean.CatesBean> cates = categoryResponse.body.cates;
                    mCates.addAll(cates);
                    mCategoryAdapter.notifyDataSetChanged();//---->>在这里得到了左边列表的信息，然后默认查询左边的第一条信息，是右边的分类信息展示出来
                    //Fixme 在这里得到cateId,更新右边的适配器，默认值为cates.get（0）.cateId..........1
                    mCateId = mCates.get(0).cateId;//获得左边的第一个条目
                    getRightDatas(mCateId);
                    break;
                default:
                    InitDataHelper.sendError(getActivity(),categoryResponse.header.returnMessage
                    +":"+categoryResponse.header.returnCode);//上传错误码
                    break;
            }

        }
        /**
         * 右边的条目需要展示的信息有：菜品的名称，菜品的规格，菜品的图标
         * 点击右边的条目，进入修改库存，所以右边的条目还应包含foodId
         */
        if (event.getOrderType() == Fields.ORDER_TYPE_CATEGORY_DETIAL) {
            QueryDishResponse dishResponse = (QueryDishResponse) event.getT();
            switch (dishResponse.header.returnCode) {
                case "0000":
                    List<QueryDishResponse.BodyBean.FoodsBean> foods = dishResponse.body.foods;
                    if (mDishFoodTypeList.size() < PAGE_SIZE) {
                        isEnd = true;
                    }
                    if (pageIndex == 0) {
                        mDishFoodTypeList.clear();
                    }
                    for (int i = 0; i < foods.size(); i++) {
                        List<QueryDishResponse.BodyBean.FoodsBean.NormsBean> norms = foods.get(i).norms;
                        if (norms.size() == 0) {
                            DishFoodType dishFoodType = new DishFoodType();
                            dishFoodType.foodId =foods.get(i).foodId;
                            dishFoodType.foodName = foods.get(i).foodName;
                            dishFoodType.pic = foods.get(i).pic;
                            dishFoodType.cateId=foods.get(i).cateId;
                            //                    foods.get(i).
                            dishFoodType.sizeName = "--";
                            //                    dishFoodType.sizeId=foods.get(i).
                            mDishFoodTypeList.add(dishFoodType);
                        }
                        for (int j = 0; j < norms.size(); j++) {
                            DishFoodType dishFoodType = new DishFoodType();
                            dishFoodType.foodId =foods.get(i).foodId;
                            dishFoodType.foodName = foods.get(i).foodName;
                            dishFoodType.pic = foods.get(i).pic;
                            dishFoodType.cateId = foods.get(i).cateId;
                            if (norms.get(j).sizeName == null) {
                                dishFoodType.sizeName = "--";
                            } else {
                                dishFoodType.sizeName = norms.get(j).sizeName;
                                dishFoodType.sizeId = norms.get(j).sizeId;
                            }
                            try {
                                dishFoodType.channel = norms.get(j).channel;
                            } catch (Exception e) {
                                MyToast.show(getActivity(), "平台尚未开放", true);
                            }

                            mDishFoodTypeList.add(dishFoodType);
                        }
                    }
                    if (mCategoryRightAdapter != null) {
                        mCategoryRightAdapter.notifyDataSetChanged();
                    }
                    if (mSrf_waimai_foodlist != null) {
                        mSrf_waimai_foodlist.setRefreshing(false);
                    }
                    break;
                default:
                    InitDataHelper.sendError(getActivity(), dishResponse.header.returnMessage
                            + ":" + dishResponse.header.returnCode);//上传错误日志
                    break;
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void stockChangedEvent(StockChangedEvent event) {
        if (event.isStockChanged()) {
            isLoading = true;
            pageIndex=0;
            getRightDatas(mCateId);
        }
    }
    /**
     * 得到右边的菜品数据
     * @param cateId
     */
    private void getRightDatas(String cateId) {
        String shopId = (String) SPUtils.get(getActivity(), Fields.shopId, "");
        String t412 = InPutJsonData.T412(Fields.loginsessionId, shopId,
                cateId, String.valueOf(PAGE_SIZE), String.valueOf(pageIndex), "");//得到报文
        NetUtil.waiMaiNetWorkUtil(t412,Fields.channelId, QueryDishResponse.class,getActivity(),Fields.ORDER_TYPE_CATEGORY_DETIAL);
    }

}
