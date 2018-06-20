package unionlive.com.umqpos.ui.fragment.untreate_wm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.UntreateAdapter;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.DetialResponse;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.OrderUnTreateListResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.NewOrderComing;
import unionlive.com.umqpos.imp.ItemClickListener;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.ui.activity.OrderDetialActivity;
import unionlive.com.umqpos.ui.dialog.DialogStyleCancleActivity;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.DividerItemDecoration;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.LdPrintStatus;
import unionlive.com.umqpos.utils.LdPrintWaimai;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.TimeHelper;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/15 16:22
 * @describe 这是外卖--->未处理---->全部的界面
 */
public class AllUntreateFragment extends BaseFragment implements ItemClickListener {

    private SwipeRefreshLayout mSrl_waimai_fresh;
    private RecyclerView       mRv_untreate_all;
    List<OrderUnTreateListResponse.BodyBean.OrdersBean> mOrders = new ArrayList<>();
    private UntreateAdapter mUntreateAdapter;
    private String          mUnTreateMsg;
    int cancleType = -1;//取消订单的原因//1：不在配送范围
    private String mOrderId;

    private int     pageIndex = 0;//当前的页码
    private boolean isLoading = false, isEnd = false;
    private final int PAGE_SIZE = 5;
    private LinearLayoutManager mLinearLayoutManager;

    /* 2：餐厅已打烊
     3：美食已售完
     4：菜品价格发生变化
     5：用户取消订单
     6：重复订单
     7：餐厅太忙
     8：联系不上用户
     9：假订单
     -1：自定义*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_untreated_iteam, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initEvent();
    }


    private void initData() {
        pageIndex = 0;//当前的页码
        isEnd = false;
        getDatas();
    }

    private void getDatas() {
        //订单查询上送的密文
        mUnTreateMsg = InPutJsonData.T201(getActivity(), Fields.loginsessionId, -1,
                "", PAGE_SIZE, pageIndex, TimeHelper.exceptDay(0), TimeHelper.exceptDay(0), "");
        NetUtil.waiMaiNetWorkUtil(mUnTreateMsg, Fields.channelId, OrderUnTreateListResponse.class,
                getActivity(), Fields.ORDER_TYPE_UNTREATE_ALL);

    }

    private void initView(View view) {
        mSrl_waimai_fresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_waimai_fresh);
        mRv_untreate_all = (RecyclerView) view.findViewById(R.id.rv_order);
    }

    private void initEvent() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRv_untreate_all.setLayoutManager(mLinearLayoutManager);
        mRv_untreate_all.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mUntreateAdapter = new UntreateAdapter(mOrders, getActivity());
        mRv_untreate_all.setAdapter(mUntreateAdapter);
        mUntreateAdapter.setOnItemClickListener(this);
        mSrl_waimai_fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
                    if (lastVisibleItemPosition + 1 == mUntreateAdapter.getItemCount()) {
                        boolean isRefreshing = mSrl_waimai_fresh.isRefreshing();
                        if (!isRefreshing && !isLoading && !isEnd) {//如果没有滑动到底部
                            isLoading = true;
                            pageIndex++;
                            getDatas();
                        }
                    }
                }

            }
        });
    }

    @Override
    public void onItemClick(View view, int postion) {
        mOrderId = mOrders.get(postion).orderId;
        switch (view.getId()) {
            case R.id.ll_order_list_detial://订单详情
                Fields.ORDER_DETIAL_STATE = -1;
                Intent intent = new Intent(getActivity(), OrderDetialActivity.class);
                intent.putExtra("orderId", mOrderId);
                intent.putExtra("currentTitle", "订单详情");
                startActivity(intent);
                break;
            case R.id.bt_wm_cancle_order:
                Intent intent1 = new Intent(getActivity(), DialogStyleCancleActivity.class);
                intent1.putExtra("cancle_orderId", mOrderId);
                intent1.putExtra("cancle_state", Fields.Refuse_order);
                startActivity(intent1);
                break;
            case R.id.bt_wm_reprint:
                //调用手动接单的接口--->>
                String t301 = InPutJsonData.T301(getActivity(), Fields.loginsessionId, mOrderId, 1);
                LdPrintStatus.startPrint();
                if (TitleInfomation.printStatus == Printer.ERROR_NONE
                        || TitleInfomation.printStatus == Printer.ERROR_BUSY) {
                    NetUtil.waiMaiNetWorkUtil(t301, Fields.channelId, OpenWmResponse.class, getActivity(),
                            Fields.ORDER_TYPE_ALL_UNTREATE_RECEIVE);//确定接单
                }
                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_UNTREATE_ALL) {
            OrderUnTreateListResponse treateListResponse
                    = (OrderUnTreateListResponse) event.getT();
            switch (treateListResponse.header.returnCode) {
                case "0000":
                    if (treateListResponse == null || treateListResponse.body == null || treateListResponse.body.orders == null) {
                        return;
                    }
                    List<OrderUnTreateListResponse.BodyBean.OrdersBean> orders = treateListResponse.body.orders;
                    if (orders.size() < PAGE_SIZE) {
                        isEnd = true;
                    }
                    if (pageIndex == 0) {
                        mOrders.clear();
                    }
                    mOrders.addAll(orders);
                    if (mUntreateAdapter != null) {
                        mUntreateAdapter.notifyDataSetChanged();
                    }
                    if (mSrl_waimai_fresh != null) {
                        mSrl_waimai_fresh.setRefreshing(false);
                    }
                    isLoading = false;
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(getActivity(), treateListResponse.header.returnMessage
                            + treateListResponse.header.returnCode, null);//上传错误日志
                    break;
            }

        }
        if (event.getOrderType() == Fields.ORDER_TYPE_ALL_UNTREATE_RECEIVE) {
            //确认订单
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000":
                    flushUi();
                    //InitDataHelper.getTitleMsg(getActivity());//确定订单或者取消订单,都需要初始化标题一下
                    //查询未处理的订单-->>然后将实体类的信息传递给打印机
                    String t202 = InPutJsonData.T202(getActivity(), Fields.loginsessionId, mOrderId, "");
                    NetUtil.waiMaiNetWorkUtil(t202, Fields.channelId, DetialResponse.class, getActivity(), Fields.ORDER_TYPE_UNTREATED_PRINT_ALL);

                    break;
                default:
                    flushUi();
                    MyToast.show(getActivity(), openWmResponse.header.returnMessage, false);
                    InitDataHelper.sendError(getActivity(), openWmResponse.header.returnMessage
                            + openWmResponse.header.returnCode);//确定订单失败，上送错误信息
                    break;
            }

        }
        //订单详情
        if (event.getOrderType() == Fields.ORDER_TYPE_UNTREATED_PRINT_ALL) {
            DetialResponse detialResponse = (DetialResponse) event.getT();
            switch (detialResponse.header.returnCode) {
                case "0000":
//                    Printer landiPrinter = Printer.getInstance();
                    LdPrintWaimai landiPrintWaimai = LdPrintWaimai.getInstance();
                    landiPrintWaimai.setT(detialResponse);
                    try {
                        landiPrintWaimai.start();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    //                            flushUi();
                    MyToast.show(getActivity(), detialResponse.header.returnMessage, false);
                    InitDataHelper.sendError(getActivity(), detialResponse.header.returnMessage
                            + detialResponse.header.returnCode);//确定订单失败，上送错误信息
                    break;
            }
            //还需要刷新适配器

        }
    }

    /**
     * 刷新界面
     */
    private void flushUi() {
        EventBus.getDefault().postSticky(new NewOrderComing(true));//发送一条消息通知各个界面更新ui
        InitDataHelper.getTitleMsg(getActivity());//确定订单或者取消订单,都需要初始化标题一下
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(NewOrderComing event) {
        if (event.isNewGtOrder()) {//如果个推的消息来了，更新操作
            initData();
        }
    }
}
