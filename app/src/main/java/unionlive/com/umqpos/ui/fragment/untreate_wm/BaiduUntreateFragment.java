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
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/15 16:25
 * @describe 这是-->外卖-->未处理-->百度商家的界面
 */
public class BaiduUntreateFragment extends BaseFragment implements ItemClickListener {

    private RecyclerView      mRv_untreate_bd;
    public List<OrderUnTreateListResponse.BodyBean.OrdersBean> mOrderList_Bd = new ArrayList<OrderUnTreateListResponse.BodyBean.OrdersBean>();
    private UntreateAdapter    mAdapter;
    private SwipeRefreshLayout mSrf_waimai_fresh;
    private String             mEnStr;
    private String mOrderId;

    private int pageIndex = 0;//当前的页码
    private boolean isLoading = false, isEnd = false;
    private final int PAGE_SIZE = 5;
    private LinearLayoutManager mLinearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_untreated_iteam, container, false);
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
        mEnStr = InPutJsonData.T201(getActivity(), Fields.loginsessionId, -1,
                TitleInfomation.channelId_platform_baidu, PAGE_SIZE,pageIndex,
                TimeHelper.exceptDay(0), TimeHelper.exceptDay(0),"");
        NetUtil.waiMaiNetWorkUtil(mEnStr,Fields.channelId, OrderUnTreateListResponse.class,
                getActivity(),Fields.ORDER_TYPE_UNTREATE_BAIDU);
    }

    private void initView(View view) {
        mRv_untreate_bd = (RecyclerView) view.findViewById(R.id.rv_order);
        mSrf_waimai_fresh = (SwipeRefreshLayout) view.findViewById(R.id.srl_waimai_fresh);
    }

    private void initEvent() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRv_untreate_bd.setLayoutManager(mLinearLayoutManager);
        mRv_untreate_bd.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new UntreateAdapter(mOrderList_Bd, getActivity());
        mRv_untreate_bd.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        mSrf_waimai_fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        //上拉刷新,得到第二页的报文
        mRv_untreate_bd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {//如果是向下滑动
                    int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                    if (lastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
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

    }

    @Override
    public void onItemClick(View view, int postion) {
        mOrderId = mOrderList_Bd.get(postion).orderId;
        switch (view.getId()) {
            case R.id.ll_order_list_detial:
                Fields.ORDER_DETIAL_STATE =-1;
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
            case R.id.bt_wm_reprint://打印接单
                //调用手动接单的接口--->>
                String t301 = InPutJsonData.T301(getActivity(), Fields.loginsessionId, mOrderId, 1);
                LdPrintStatus.startPrint();
                if (TitleInfomation.printStatus == Printer.ERROR_NONE
                        || TitleInfomation.printStatus == Printer.ERROR_BUSY) {
                    NetUtil.waiMaiNetWorkUtil(t301,Fields.channelId,OpenWmResponse.class,
                            getActivity(),Fields.ORDER_TYPE_BAIDU_UNTREATE_RECEIVE);
                }

                break;
        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType()==Fields.ORDER_TYPE_UNTREATE_BAIDU) {
            OrderUnTreateListResponse treateListResponse = (OrderUnTreateListResponse) event.getT();
            switch (treateListResponse.header.returnCode) {
                case "0000":
                    if (treateListResponse==null||treateListResponse.body==null||treateListResponse.body.orders==null) {
                        return;
                    }
                    List<OrderUnTreateListResponse.BodyBean.OrdersBean> orders = treateListResponse.body.orders;
                    if (orders.size() < PAGE_SIZE) {
                        isEnd = true;
                    }
                    if (pageIndex == 0) {
                        mOrderList_Bd.clear();
                    }
                    mOrderList_Bd.addAll(orders);
                    if (mAdapter!=null) {
                        mAdapter.notifyDataSetChanged();
                    }
                    if (mSrf_waimai_fresh!=null) {
                        mSrf_waimai_fresh.setRefreshing(false);
                    }
                    isLoading = false;
                    break;
                default:
                    InitDataHelper.sendError(getActivity(), treateListResponse.header.returnMessage
                            + treateListResponse.header.returnCode);//刷新订单失败，上送错误信息
                    break;
            }

        }
        //打印功能
        if (event.getOrderType() == Fields.ORDER_TYPE_UNTREATED_PRINT_BAIDU) {
            DetialResponse detialResponse = (DetialResponse) event.getT();
            switch (detialResponse.header.returnCode) {
                case "0000":
                    //得到实体类的信息，
//                    Printer landiPrinter = Printer.getInstance();
                    LdPrintWaimai landiPrintWaimai = LdPrintWaimai.getInstance();
                    landiPrintWaimai.setT(detialResponse);
                    try {
                        landiPrintWaimai.start();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                    break;
                default://打印异常应该弹出dialog的提示信息
                    String orderId = detialResponse.body.orderId;
                    int orderSeq = detialResponse.body.orderSeq;
                    CancleOrderDialog.isNetErrorDialog(getActivity(),"打印失败,订单号:#"+orderSeq,null);
                    InitDataHelper.sendError(getActivity(),detialResponse.header.returnMessage
                            + detialResponse.header.returnCode+"\n订单号:"+orderId);//上送报文
                    break;
            }

        }
        //打印前的操作--->>>确定订单
        if (event.getOrderType()==Fields.ORDER_TYPE_BAIDU_UNTREATE_RECEIVE) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000"://成功接单
                    flushUi();
                    //成功接订单后开始打印
                    String t202 = InPutJsonData.T202(getActivity(), Fields.loginsessionId, mOrderId, "");
                    NetUtil.waiMaiNetWorkUtil(t202,Fields.channelId,DetialResponse.class,getActivity(),
                            Fields.ORDER_TYPE_UNTREATED_PRINT_BAIDU);
                    break;
                default:
                    flushUi();
                    InitDataHelper.sendError(getActivity(), openWmResponse.header.returnMessage
                            + openWmResponse.header.returnCode);//确定订单失败，上送错误信息
                    MyToast.show(getActivity(), openWmResponse.header.returnMessage, false);//确定订单失败谈吐司
                    break;
            }
        }
    }

    private void flushUi() {
        InitDataHelper.flush(getActivity());
        InitDataHelper.getTitleMsg(getActivity());//确定订单或者取消订单,都需要初始化标题一下
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(NewOrderComing event) {
        if (event.isNewGtOrder()) {//如果个推的消息来了，更新操作
            initData();
        }
    }
}
