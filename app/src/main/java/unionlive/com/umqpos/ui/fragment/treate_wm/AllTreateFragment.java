package unionlive.com.umqpos.ui.fragment.treate_wm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.TreateAdapter;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.DetialResponse;
import unionlive.com.umqpos.entitys.out_waimai.OrderTreateListResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.NewOrderComing;
import unionlive.com.umqpos.event.OrderStatuEvent;
import unionlive.com.umqpos.imp.ItemClickListener;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.ui.activity.OrderDetialActivity;
import unionlive.com.umqpos.ui.dialog.DialogStyleCancleActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.DividerItemDecoration;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.LdPrintWaimai;
import unionlive.com.umqpos.utils.TimeHelper;

import static unionlive.com.umqpos.content.Fields.DAY_FLAG_ALL;


/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/15 13:59
 * @describe 这是-->外卖-->已处理-->全部商家的界面
 */
public class AllTreateFragment extends BaseFragment implements ItemClickListener, View.OnClickListener {

    private SwipeRefreshLayout mSrf_waimai_fresh;
    private RecyclerView       mRv_untreate_all;
    List<OrderTreateListResponse.BodyBean.OrdersBean> mOrders = new ArrayList<>();
    private TreateAdapter mTreateAdapter;
    private RadioButton   mRb_orderfood_title_today;
    private RadioButton   mRb_orderfood_title_yes;
    private RadioButton   mRb_orderfood_title_dby;
    private int     pageIndex = 0;//当前的页码
    private boolean isLoading = false, isEnd = false;
    private final int PAGE_SIZE = 5;
    private LinearLayoutManager mLinearLayoutManager;
    public  int orderStasus = 101;//订单的状态---默认情况下是全部的订单
    private int position    = 0;//当前点击的位置

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_treated_iteam, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        initData();

    }

    private void initData() {
        pageIndex = 0;//当前的页码
        isEnd = false;
        getDatas();
    }

    private void getDatas() {
        //得到第一页的报文
        String t201 = InPutJsonData.T201(getActivity(), Fields.loginsessionId, orderStasus,
                "", PAGE_SIZE, pageIndex, TimeHelper.exceptDay(DAY_FLAG_ALL),
                TimeHelper.exceptDay(DAY_FLAG_ALL), "");//订单查询上送的密文---默认情况查询的是今天
        NetUtil.waiMaiNetWorkUtil(t201, Fields.channelId, OrderTreateListResponse.class,
                getActivity(), Fields.ORDER_TYPE_TREATE_ALL);
    }

    private void initView(View view) {
        mSrf_waimai_fresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_waimai_fresh);
        mRv_untreate_all = (RecyclerView) view.findViewById(R.id.rv_order);

        //今天
        mRb_orderfood_title_today = (RadioButton) view.findViewById(R.id.rb_orderfood_title_today);
        //昨天
        mRb_orderfood_title_yes = (RadioButton) view.findViewById(R.id.rb_orderfood_title_yes);
        //前天
        mRb_orderfood_title_dby = (RadioButton) view.findViewById(R.id.rb_orderfood_title_dby);
//        mRb_orderfood_title_today.setChecked(true);
        setWhichDay(Fields.DAY_FLAG_ALL);
    }

    /**
     * 初始化选中哪一天
     * @param whichDay
     */
    private void setWhichDay(int whichDay) {
        switch (whichDay) {
            case 0:
                mRb_orderfood_title_today.setChecked(true);
                break;
            case 1:
                mRb_orderfood_title_yes.setChecked(true);
                break;
            case 2:
                mRb_orderfood_title_dby.setChecked(true);
                break;
        }
    }

    private void initEvent() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRv_untreate_all.setLayoutManager(mLinearLayoutManager);
        mRv_untreate_all.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mTreateAdapter = new TreateAdapter(mOrders, getActivity());
        mRv_untreate_all.setAdapter(mTreateAdapter);
        mTreateAdapter.setOnItemClickListener(this);
        mSrf_waimai_fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        //上拉刷新,得到第二页的报文
        mRv_untreate_all.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//如果是向下滑动
                    int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition + 1 == mTreateAdapter.getItemCount()) {
                        boolean isRefreshing = mSrf_waimai_fresh.isRefreshing();
                        if (!isRefreshing && !isLoading && !isEnd) {
                            isLoading = true;
                            pageIndex++;
                            getDatas();
                        }
                    }
                }
            }
        });


        mRb_orderfood_title_today.setOnClickListener(this);
        mRb_orderfood_title_yes.setOnClickListener(this);
        mRb_orderfood_title_dby.setOnClickListener(this);
    }

    @Override
    public void onItemClick(View view, int postion) {
        this.position = postion;
        String orderId = mOrders.get(postion).orderId;
        switch (view.getId()) {
            case R.id.ll_order_list_detial://订单详情
                Fields.ORDER_DETIAL_STATE = 101;
                Intent intent = new Intent(getActivity(), OrderDetialActivity.class);
                intent.putExtra("orderId", orderId);
                intent.putExtra("currentTitle", "订单详情");
                startActivity(intent);
                break;
            case R.id.bt_wm_cancle_order:
                Intent intent1 = new Intent(getActivity(), DialogStyleCancleActivity.class);
                intent1.putExtra("cancle_orderId", orderId);
                intent1.putExtra("cancle_state", Fields.Cancle_order);
                startActivity(intent1);

                break;
            case R.id.bt_wm_reprint:
                String enOrderDetialStr = InPutJsonData.T202(getActivity(), Fields.loginsessionId, orderId, "");//得到上送的报文
                NetUtil.waiMaiNetWorkUtil(enOrderDetialStr, Fields.channelId, DetialResponse.class,
                        getActivity(), Fields.ORDER_TYPE_TREATED_PRINT_ALL);//得到报文，通过EventBus获取
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_orderfood_title_today://今天
                Fields.DAY_FLAG_ALL = 0;
                initData();
                break;
            case R.id.rb_orderfood_title_yes://昨天
                Fields.DAY_FLAG_ALL = 1;
                initData();
                break;
            case R.id.rb_orderfood_title_dby://前天
                Fields.DAY_FLAG_ALL = 2;
                initData();
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_TREATE_ALL) {
            OrderTreateListResponse treateListResponse
                    = (OrderTreateListResponse) event.getT();//得到第一页的数据
            switch (treateListResponse.header.returnCode) {
                case "0000":
                    List<OrderTreateListResponse.BodyBean.OrdersBean> orders = treateListResponse.body.orders;
                    if (orders.size() < PAGE_SIZE) {
                        isEnd = true;
                    }
                    if (pageIndex == 0) {
                        mOrders.clear();//
                    }

                    mOrders.addAll(orders);
                    if (mTreateAdapter != null) {
                        mTreateAdapter.notifyDataSetChanged();
                    }
                    if (mSrf_waimai_fresh != null) {
                        mSrf_waimai_fresh.setRefreshing(false);
                    }
                    isLoading = false;
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(getActivity(), treateListResponse.header.returnMessage
                            + treateListResponse.header.returnCode, null);
                    break;
            }

        }
        if (event.getOrderType() == Fields.ORDER_TYPE_TREATED_PRINT_ALL) {//全部已处理的打印
            DetialResponse detialResponse = (DetialResponse) event.getT();
            switch (detialResponse.header.returnCode) {
                case "0000":
                    //得到实体类的信息，

//                    Printer landiPrinter = Printer.getInstance();
                    LdPrintWaimai landiPrintWaimai = LdPrintWaimai.getInstance();
                    landiPrintWaimai.setIsReprint(true);
                    landiPrintWaimai.setT(detialResponse);
                    try {
                        landiPrintWaimai.start();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try {
                        int orderSeq = detialResponse.body.orderSeq;
                        String channelName = detialResponse.body.channelName;
                        CancleOrderDialog.isNetErrorDialog(getActivity(), channelName+"订单" + "(#" + orderSeq + ")" + "未打印", null);
                        InitDataHelper.sendError(getActivity(),
                                channelName+"订单未打印,订单号:" + detialResponse.body.orderId);
                    } catch (Exception e) {
                    }

                    break;
            }

        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(OrderStatuEvent event) {
        orderStasus = event.getStatus();//得到订单状态
        initData();//得到相应订单状态的订单
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(NewOrderComing event) {
        if (event.isNewGtOrder()) {//如果个推的消息来了，更新操作
            initData();
        }
    }

}
