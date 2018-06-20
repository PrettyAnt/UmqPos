package unionlive.com.umqpos.ui.fragment.manage_wm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.ShopBusinessInfoResoponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/15 17:15
 * @describe 外卖->管理-->订单统计
 */
public class OrderStatisticsFragment extends BaseFragment{

    private Button mBt_all_orderList_amount;
    private Button mBt_all_money_amount;
    private Button mBt_baidu_orderList_amount;
    private Button             mBt_baidu_money_amount;
    private Button             mBt_eleme_orderList_amount;
    private Button             mBt_eleme_money_amount;
    private Button             mBt_meituan_orderList_amount;
    private Button             mBt_meituan_money_amount;
    private SwipeRefreshLayout mSrf_waimai_order_fresh;
    private String             mAllOrderMsg;
    private String             mBaiduOrderMsg;
    private String             mMeituanOrderMsg;
    private String             mElemeOrderMsg;
    private Button             mBt_channel_name_all;
    private Button             mBt_channel_name_baidu;
    private Button             mBt_channel_name_meituan;
    private Button             mBt_channel_name_eleme;
    private String mT102;
    private LinearLayout mLl_order_list_all;
    private LinearLayout mLl_order_list_baidu;
    private LinearLayout mLl_order_list_meituan;
    private LinearLayout mLl_order_list_eleme;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manage_orderstatistics, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        initEvent();
    }

    private void initEvent() {
        mSrf_waimai_order_fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetUtil.waiMaiNetWorkUtil(mT102,Fields.channelId, ShopBusinessInfoResoponse.class,
                        getActivity(),Fields.ORDER_TYPE_QUERY_NUM_MONEY);
            }
        });
    }

    private void initData() {
        mT102 = InPutJsonData.T102(getActivity(), Fields.loginsessionId);
        NetUtil.waiMaiNetWorkUtil(mT102,Fields.channelId, ShopBusinessInfoResoponse.class,
                getActivity(),Fields.ORDER_TYPE_QUERY_NUM_MONEY);
    }

    private void initView(View view) {
        mBt_all_orderList_amount = (Button) view.findViewById(R.id.bt_all_orderList_amount);
        mBt_all_money_amount = (Button) view.findViewById(R.id.bt_all_money_amount);
        mBt_baidu_orderList_amount = (Button) view.findViewById(R.id.bt_baidu_orderList_amount);
        mBt_baidu_money_amount = (Button) view.findViewById(R.id.bt_baidu_money_amount);
        mBt_eleme_orderList_amount = (Button) view.findViewById(R.id.bt_eleme_orderList_amount);
        mBt_eleme_money_amount = (Button) view.findViewById(R.id.bt_eleme_money_amount);
        mBt_meituan_orderList_amount = (Button) view.findViewById(R.id.bt_meituan_orderList_amount);
        mBt_meituan_money_amount = (Button) view.findViewById(R.id.bt_meituan_money_amount);
        mSrf_waimai_order_fresh = (SwipeRefreshLayout) view.findViewById(R.id.srf_waimai_order_fresh);
        //显示的渠道名
        mBt_channel_name_all = (Button) view.findViewById(R.id.bt_channel_name_all);
        mBt_channel_name_baidu = (Button) view.findViewById(R.id.bt_channel_name_baidu);
        mBt_channel_name_meituan = (Button) view.findViewById(R.id.bt_channel_name_maituan);
        mBt_channel_name_eleme = (Button) view.findViewById(R.id.bt_channel_name_eleme);
        //显示渠道的View
        mLl_order_list_all = (LinearLayout) view.findViewById(R.id.ll_order_list_all);
        mLl_order_list_baidu = (LinearLayout) view.findViewById(R.id.ll_order_list_baidu);
        mLl_order_list_meituan = (LinearLayout) view.findViewById(R.id.ll_order_list_meituan);
        mLl_order_list_eleme = (LinearLayout) view.findViewById(R.id.ll_order_list_eleme);
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onEvent(CoubonsDecoderEvent event){
        if (event.getOrderType() == Fields.ORDER_TYPE_QUERY_NUM_MONEY) {
            ShopBusinessInfoResoponse businessInfoResoponse = (ShopBusinessInfoResoponse) event.getT();
            if (businessInfoResoponse != null && businessInfoResoponse.body != null&&businessInfoResoponse.body.total!=null) {
                int orderCount = businessInfoResoponse.body.total.orderCount;//订单总数
                int orderAmount = businessInfoResoponse.body.total.orderAmount;//订单总金额
                mBt_channel_name_all.setText("全部");
                mBt_all_orderList_amount.setText(String.valueOf(orderCount));
                mBt_all_money_amount.setText(String.format("%.2f",orderAmount/100.00));

                List<ShopBusinessInfoResoponse.BodyBean.ChannelBean> channel = businessInfoResoponse.body.channel;
                for (int i = 0; i < channel.size(); i++) {
                    if (TextUtils.equals(channel.get(i).channelId, TitleInfomation.channelId_platform_baidu)) {
                        mBt_channel_name_baidu.setText(TitleInfomation.channelName_platform_baidu);
                        mBt_baidu_orderList_amount.setText(String.valueOf(channel.get(i).orderCount));
                        mBt_baidu_money_amount.setText(String.format("%.2f",(channel.get(i).orderAmount)/100.00));
                        mLl_order_list_baidu.setVisibility(View.VISIBLE);

                    }
                    if (TextUtils.equals(channel.get(i).channelId,TitleInfomation.channelId_platform_meituan)) {
                        mBt_channel_name_meituan.setText(TitleInfomation.channelName_platform_meituan);
                        mBt_meituan_orderList_amount.setText(String.valueOf(channel.get(i).orderCount));
                        mBt_meituan_money_amount.setText(String.format("%.2f",(channel.get(i).orderAmount)/100.00));
                        mLl_order_list_meituan.setVisibility(View.VISIBLE);
                    }
                    if (TextUtils.equals(channel.get(i).channelId, TitleInfomation.channelId_platform_eleme)) {
                        mBt_channel_name_eleme.setText(TitleInfomation.channelId_platform_eleme);
                        mBt_meituan_orderList_amount.setText(String.valueOf(channel.get(i).orderCount));
                        mBt_meituan_money_amount.setText(String.format("%.2f",(channel.get(i).orderAmount)/100.00));
                        mLl_order_list_eleme.setVisibility(View.VISIBLE);
                    }
                }
            }
            if (mSrf_waimai_order_fresh != null) {
                mSrf_waimai_order_fresh.setRefreshing(false);
            }
        }
    }
}
