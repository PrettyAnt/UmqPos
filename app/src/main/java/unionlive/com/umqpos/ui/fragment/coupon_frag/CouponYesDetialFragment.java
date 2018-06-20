package unionlive.com.umqpos.ui.fragment.coupon_frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
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
import unionlive.com.umqpos.adapter.CouponDetialAdapter;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransBasicResponse;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransDetailsResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.FlashUiEvent;
import unionlive.com.umqpos.imp.ItemClickListener;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.utils.DividerItemDecoration;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.TimeHelper;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/28 16:53
 * @describe ${TODO}
 */
public class CouponYesDetialFragment extends BaseFragment implements ItemClickListener {

    private SwipeRefreshLayout  mSrf_coupon_td_detial;
    private RecyclerView        mRv_coupon_td_detial;
    private CouponDetialAdapter mCouponDetialAdapter;
    List<QueryTransBasicResponse.BodyBean.TransInfoBean> mTransInfo = new ArrayList<>();
    private String mTermId;
    private String mShopId;
    private String mShopOperId;
    private String mM201;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon_td_detial, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        initData();
    }


    private void initView(View view) {
        mSrf_coupon_td_detial = (SwipeRefreshLayout) view.findViewById(R.id.srf_coupon_td_detial);
        mRv_coupon_td_detial = (RecyclerView) view.findViewById(R.id.rv_coupon_td_detial);
    }

    private void initEvent() {
        mRv_coupon_td_detial.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv_coupon_td_detial.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mCouponDetialAdapter = new CouponDetialAdapter(getActivity(), mTransInfo);
        mRv_coupon_td_detial.setAdapter(mCouponDetialAdapter);
        mSrf_coupon_td_detial.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetUtil.couponsNetWorkUtil(mM201, Fields.channelId,
                        QueryTransBasicResponse.class, getActivity(),
                        Fields.QueryTransBasic, Fields.COUPON_TYPE_QUERY_BASE_TRANS);//交易基本信息查询
            }
        });
        mCouponDetialAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        mTermId = (String) SPUtils.get(getActivity(), Fields.termId, "");
        mShopId = (String) SPUtils.get(getActivity(), Fields.shopId, "");
        mShopOperId = (String) SPUtils.get(getActivity(), Fields.shopOperId, "");
        mM201 = InPutJsonData.M201(getActivity(), "", 0, 0, mTermId, mShopId, mShopOperId);
        NetUtil.couponsNetWorkUtil(mM201, Fields.channelId,
                QueryTransBasicResponse.class, getActivity(),
                Fields.QueryTransBasic, Fields.COUPON_TYPE_QUERY_BASE_TRANS);//交易基本信息查询
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void collectTodayEvent(CoubonsDecoderEvent event) {

        if (event.getOrderType() == Fields.COUPON_TYPE_QUERY_BASE_TRANS) {
            QueryTransBasicResponse basicResponse = (QueryTransBasicResponse) event.getT();
            switch (basicResponse.header.returnCode) {
                case "0000":
                    if (basicResponse.body != null &&basicResponse.body.transInfo != null) {
                        List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo = basicResponse.body.transInfo;

                        mTransInfo.clear();
                        for (int i = 0; i < transInfo.size(); i++) {
                            if (TimeHelper.daysOfTwo(transInfo.get(i).transTime) == 1) {
                                QueryTransBasicResponse.BodyBean.TransInfoBean transInfoBean = transInfo.get(i);
                                mTransInfo.add(transInfoBean);
                            }
                        }
                    }
                    if (mCouponDetialAdapter != null) {
                        mCouponDetialAdapter.notifyDataSetChanged();
                    }
                    if (mSrf_coupon_td_detial != null) {
                        mSrf_coupon_td_detial.setRefreshing(false);
                    }
                    break;
                default:
                    InitDataHelper.sendError(getActivity(),basicResponse.header.returnMessage
                    +":"+basicResponse.header.returnCode);
                    break;
            }

        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        String transTime = mTransInfo.get(postion).transTime;
        String hostTime = mTransInfo.get(postion).hostTime;
        String hostTrace = mTransInfo.get(postion).hostTrace;
        String authCode = mTransInfo.get(postion).authCode;
        //        EventBus.getDefault().postSticky(TodayDetialBean.class);
        String termId = (String) SPUtils.get(getActivity(), Fields.termId, "");
        String shopId = (String) SPUtils.get(getActivity(), Fields.shopId, "");
        String shopOperId = (String) SPUtils.get(getActivity(), Fields.shopOperId, "");
        String m202 = InPutJsonData.M202(getActivity(), transTime, hostTime, hostTrace, authCode,termId,shopId,shopOperId);
        NetUtil.couponsNetWorkUtil(m202, Fields.channelId, QueryTransDetailsResponse.class,
                getActivity(), Fields.QueryTransDetails,Fields.COUPON_TYPE_QUERY_TRANS_DETAILS_YESTD);//交易详情查询
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.hide(this);
        YestodayDetialFragment detialFragment = new YestodayDetialFragment();
        transaction.add(R.id.fragment_counpon_content, detialFragment);
        transaction.commit();
        EventBus.getDefault().postSticky(new FlashUiEvent(detialFragment,this,true,"",Fields.COUPON_TYPE_QUERY_TRANS_DETAILS_YESTD));
    }
}
