package unionlive.com.umqpos.ui.fragment.coupon_frag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.CouponTdCollectAdapter;
import unionlive.com.umqpos.bean.CouponTdCollectMsg;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransBasicResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.DividerItemDecoration;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.TimeHelper;
import unionlive.com.umqpos.utils.LdPrintCoupon;

import static unionlive.com.umqpos.content.Fields.shopId;
import static unionlive.com.umqpos.content.Fields.shopOperId;
import static unionlive.com.umqpos.content.Fields.termId;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/28 16:51
 * @describe ${TODO}
 */
public class CouponTdCollectFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTv_coupon_td_collect;
    private RecyclerView mRv_coupon_td_collect;
    List<QueryTransBasicResponse.BodyBean.TransInfoBean> mTransInfo = new ArrayList<>();
    private SwipeRefreshLayout mSrf_coupon_today_print;
    private CouponTdCollectAdapter mCouponTdCollectAdapter;
    private String mMercName;
    private String mMercId;
    private String mDevicesSN;
    private String mHostTime;
    private String mPrintTime;
    private int mCount;
    private CouponTdCollectMsg mCouponTdCollectMsg;
    private Button mBtn_coupon_print;
    private String mTermId;
    private String mShopId;
    private String mShopOperId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon_td_collect, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        initData();
    }


    private void initView(View view) {
        mTv_coupon_td_collect = (TextView) view.findViewById(R.id.tv_coupon_td_collect);
        mRv_coupon_td_collect = (RecyclerView) view.findViewById(R.id.rv_coupon_td_collect);
        mSrf_coupon_today_print = (SwipeRefreshLayout) view.findViewById(R.id.srf_coupon_today_print);
        mBtn_coupon_print = (Button) view.findViewById(R.id.btn_coupon_print);
    }

    private void initEvent() {
        mRv_coupon_td_collect.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRv_coupon_td_collect.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mCouponTdCollectAdapter = new CouponTdCollectAdapter(getActivity(),mTransInfo);
        mRv_coupon_td_collect.setAdapter(mCouponTdCollectAdapter);
        mSrf_coupon_today_print.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                String m201 = InPutJsonData.M201(getActivity(), "", 0, 0,mTermId,mShopId,mShopOperId);
                NetUtil.couponsNetWorkUtil(m201, Fields.channelId, QueryTransBasicResponse.class, getActivity(),
                        Fields.QueryTransBasic,Fields.COUPON_TYPE_QUERY_BASE_TRANS);//交易基本信息查询
            }
        });

        mBtn_coupon_print.setOnClickListener(this);
    }

    private void initData() {
        mTermId = (String) SPUtils.get(getActivity(), termId, "");
        mShopId = (String) SPUtils.get(getActivity(), shopId, "");
        mShopOperId = (String) SPUtils.get(getActivity(), shopOperId, "");
        String m201 = InPutJsonData.M201(getActivity(), "", 0, 0, mTermId, mShopId, mShopOperId);
        NetUtil.couponsNetWorkUtil(m201, Fields.channelId,
                QueryTransBasicResponse.class, getActivity(),
                Fields.QueryTransBasic,Fields.COUPON_TYPE_QUERY_BASE_TRANS);//交易基本信息查询
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void collectTodayEvent(CoubonsDecoderEvent event){
        if (event.getOrderType() == Fields.COUPON_TYPE_QUERY_BASE_TRANS) {
            QueryTransBasicResponse basicResponse = (QueryTransBasicResponse) event.getT();
            if (basicResponse == null || basicResponse.body == null || basicResponse.body.transInfo == null) {
                return;
            }
            switch (basicResponse.header.returnCode) {
                case "0000":
                    List<QueryTransBasicResponse.BodyBean.TransInfoBean> transInfo = basicResponse.body.transInfo;
                    //                    mTransInfo.addAll(transInfo);
                    //门店名称
                    mMercName = transInfo.get(0).mercName;
                    //门店号
                    mMercId = transInfo.get(0).mercId;
                    //设备的sn号
                    mDevicesSN = DevicesInfoUtil.getDevicesSN(getActivity());
                    //交易时间
                    mHostTime = TimeHelper.OrderTime(transInfo.get(0).hostTime);
                    //打印时间
                    mPrintTime = TimeHelper.getPrintTime();
                    //验券总数
                    mCount = transInfo.size();
                    mCouponTdCollectMsg = new CouponTdCollectMsg(mMercName, mMercId, mDevicesSN, mHostTime, mPrintTime, mCount);
                    if (mTv_coupon_td_collect == null) {
                        return;
                    }
                    if (TimeHelper.daysOfTwo(transInfo.get(0).transTime) == 0) {
                        mBtn_coupon_print.setClickable(true);
                        mBtn_coupon_print.setBackgroundColor(Color.argb(255, 46, 195, 140));
                        mTv_coupon_td_collect.setText(
                                "商　户　名 :\t" + mMercName
                                        + "\n商　户　号 :\t" + mMercId
                                        + "\n终　端　号 :\t" + mDevicesSN
                                        + "\n交 易 日 期 :\t" + mHostTime
                                        + "\n打 印 时 间 :\t" + mPrintTime
                                        + "\n验 券 总 数 :\t" + mCount + "笔");
                    } else {
                        mBtn_coupon_print.setClickable(false);
                        mBtn_coupon_print.setBackgroundColor(Color.argb(255, 196, 194, 199));
                    }

                    mTransInfo.clear();
                    for (int i = 0; i < transInfo.size(); i++) {
                        if (TimeHelper.daysOfTwo(transInfo.get(i).transTime) == 0) {
                            QueryTransBasicResponse.BodyBean.TransInfoBean transInfoBean = transInfo.get(i);
                            //                    strMsg.put(transInfoBean.prodId, transInfoBean.prodName);
                            //                    Integer.getInteger(transInfoBean.prodId);
                            mTransInfo.add(transInfoBean);

                        }
                    }
                    if (mCouponTdCollectAdapter != null) {
                        mCouponTdCollectAdapter.notifyDataSetChanged();
                    }
                    if (mSrf_coupon_today_print != null) {
                        mSrf_coupon_today_print.setRefreshing(false);
                    }
                    break;
                default:
                    InitDataHelper.sendError(getActivity(),basicResponse.header.returnMessage
                    +":"+basicResponse.header.returnCode);//上传错误信息
                    break;
            }

        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_coupon_print) {
            //mCouponTdCollectMsg
            Printer printer = Printer.getInstance();
            LdPrintCoupon LdPrintCoupon = new LdPrintCoupon(printer);
            LdPrintCoupon.setT(mCouponTdCollectMsg);
            try {
                LdPrintCoupon.start();
            } catch (RequestException e) {
                e.printStackTrace();
            }
        }
    }
}
