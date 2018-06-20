package unionlive.com.umqpos.ui.fragment.coupon_frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_coupons.QueryTransDetailsResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.bean.CouponPrintMsg;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.TimeHelper;
import unionlive.com.umqpos.utils.LdPrintCoupon;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/29 11:36
 * @describe ${TODO}
 */
public class YestodayDetialFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTv_coupon_detial;
    private TextView mTv_coupon_detial_type;
    private Button   mBt_coupon_detial_print;
    private String   mMercName;
    private String   mMercId;
    private String   mShopOperId;
    private String   mTermId;
    private String   mProdId;
    private String   mAuthCode;
    private String   mTransTime;
    private String   mExpireDate;
    private String   mProdName;
    private String   mTransNum;
    private String   mProdDesc;
    private String   mPrintTime;
    private String   mAmount;
    private String   mTransName;
    private String   mHostTraceNo;
    private String   mSubmitTime;
    private String   mBatchNum;
    private String   mCertificNum;
    private int      mVersionCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coupon_detial_msg, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        initData();
    }

    private void initView(View view) {
        mTv_coupon_detial = (TextView) view.findViewById(R.id.tv_coupon_detial);
        //        mTv_coupon_detial_type = (TextView) view.findViewById(R.id.tv_coupon_detial_type);
        mBt_coupon_detial_print = (Button) view.findViewById(R.id.bt_coupon_detial_print);
    }

    private void initEvent() {
        mBt_coupon_detial_print.setOnClickListener(this);


    }

    private void initData() {

    }

    @Override
    public void onClick(View view) {
        //        Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
        CouponPrintMsg couponPrintMsg = new CouponPrintMsg(
                mMercName, false,
                mMercId, mShopOperId, mTermId, mProdId,
                mAuthCode, mTransTime, mExpireDate, mProdName,
                mTransNum, mProdDesc, mPrintTime, mAmount, mMercName, mTransName, "", mHostTraceNo, mBatchNum, mCertificNum, mVersionCode);
        Printer printer = Printer.getInstance();
        LdPrintCoupon LdPrintCoupon = new LdPrintCoupon(printer);
        LdPrintCoupon.setT(couponPrintMsg);
        try {
            LdPrintCoupon.start();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void DetialMsgEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.COUPON_TYPE_QUERY_TRANS_DETAILS_YESTD) {
            QueryTransDetailsResponse detailsResponse = (QueryTransDetailsResponse) event.getT();
            switch (detailsResponse.header.returnCode) {
                case "0000":
                    mHostTraceNo = detailsResponse.header.hostTraceNo;//交易流水号---->参考号
                    mSubmitTime = detailsResponse.header.submitTime;
                    if (detailsResponse != null || detailsResponse.body != null || detailsResponse.body.transDetail != null) {
                        List<QueryTransDetailsResponse.BodyBean.TransDetailBean> transDetail = detailsResponse.body.transDetail;
                        if (transDetail == null) {
                            return;
                        }
                        //商户名
                        mMercName = transDetail.get(0).mercName;//门店名称
                        //商户号
                        mMercId = transDetail.get(0).mercId;
                        //操作员编号
                        mShopOperId = (String) SPUtils.get(getActivity(), Fields.shopOperId, "");
                        //终端编号
                        mTermId = transDetail.get(0).termId;
                        //产品编号
                        mProdId = transDetail.get(0).prodId;
                        //授权码
                        mAuthCode = transDetail.get(0).authCode;
                        //交易时间
                        mTransTime = TimeHelper.transTime(transDetail.get(0).transTime);
                        //有效期
                        mExpireDate = TimeHelper.expireDate(transDetail.get(0).expireDate);
                        //产品名称
                        mProdName = transDetail.get(0).prodName;
                        //交易名称
                        mTransName = transDetail.get(0).transName;
                        //券号
                        mTransNum = getTransNum(transDetail.get(0).no);
                        //产品描述
                        mProdDesc = transDetail.get(0).prodDesc;
                        //打印时间
                        mPrintTime = TimeHelper.getPrintTime();
                        //验券总数
                        mAmount = transDetail.get(0).amount;
                        mBatchNum = TimeHelper.getBatchNum(mSubmitTime);//批次号
                        //凭证号
                        mCertificNum = TimeHelper.getCertificNum(mSubmitTime);
                        //版本号
                        mVersionCode = DevicesInfoUtil.getVersionCode(getActivity());
                        if (transDetail.get(0).prodCate == 1) {//说明该商品为电子卡
                            //            amount=amount/100.00;
                            mAmount = Integer.getInteger(mAmount) / 100.00 + "元";
                        } else {
                            mAmount = mAmount + "次";
                        }
                        if (mTv_coupon_detial == null) {
                            return;
                        }
                        mTv_coupon_detial.setText(
                                "商户名:\t" + mMercName + "\n" +
                                        "商户号:\t" + mMercId + "\n" +
                                        "操作员编号:\t" + mShopOperId + "\n" +
                                        "终端编号:\t" + mTermId + "\n" +
                                        "产品编号:\t" + mProdId + "\n" +
                                        "交易名称:\t" + mTransName + "\n" +
                                        "批次号:\t" + mBatchNum + "\n" +
                                        "凭证号:\t" + mCertificNum + "\n" +
                                        "授权码:\t" + mAuthCode + "\n" +
                                        "交易时间:\t" + mTransTime + "\n" +
                                        "参考号:\t" + mHostTraceNo + "\n" +

                                        "礼券名称:\t" + mProdName + "\n" +
                                        "有效期:\t" + mExpireDate + "\n" +
                                        "券号:\t" + mTransNum + "\n" +
                                        "验券次数:\t" + mAmount);


                    }
                    break;
                default:
                    InitDataHelper.sendError(getActivity(), detailsResponse.header.returnMessage
                            + ":" + detailsResponse.header.returnCode);
                    break;
            }

        }
    }

    private String getTransNum(String no) {
        String behind = no.substring(0, 4);
        String afterStr = no.substring(no.length() - 4, no.length());
        return behind + " **** **** " + afterStr;
    }

}
