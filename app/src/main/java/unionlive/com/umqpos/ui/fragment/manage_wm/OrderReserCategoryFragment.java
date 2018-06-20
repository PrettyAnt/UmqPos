package unionlive.com.umqpos.ui.fragment.manage_wm;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.DishFoodType;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.in_waimai.UpDateStock;
import unionlive.com.umqpos.entitys.out_waimai.QueryDishResponse;
import unionlive.com.umqpos.entitys.out_waimai.OrderCommonResponse;
import unionlive.com.umqpos.event.CateIdEvent;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.ui.activity.OrderFoodActivity;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.SPUtils;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/23 13:21
 * @describe $订单详情
 */

public class OrderReserCategoryFragment extends BaseFragment implements View.OnClickListener {

    private EditText mEt_order_baidu_price;
    private EditText mEt_order_meituan_price;
    private EditText mEt_order_eleme_price;
    private TextView mTv_order_baidu_maxtstock;
    private TextView mTv_order_meituan_maxtstock;
    private TextView mTv_order_eleme_maxstock;
    private EditText mEt_order_baidu_currentstock;
    private EditText mEt_order_meituan_currentstock;
    private EditText mEt_order_eleme_currentstock;
    private Button   mBt_order_baidu_offline;
    private Button   mBt_order_meituan_offline;
    private Button   mBt_order_eleme_offline;

    //    StringBuffer sb_baidu   = new StringBuffer();
    //    StringBuffer sb_meituan = new StringBuffer();
    //    StringBuffer sb_eleme   = new StringBuffer();
    //    private Button      mBt_0;
    //    private Button      mBt_1;
    //    private Button      mBt_2;
    //    private Button      mBt_3;
    //    private Button      mBt_4;
    //    private Button      mBt_5;
    //    private Button      mBt_6;
    //    private Button      mBt_7;
    //    private Button      mBt_8;
    //    private Button      mBt_9;
    //    private ImageButton mBt_clear;
    //    private Button      mBt_clearall;
    //    private Button      mBt_sure;

    private Button                               mBtn_category_save;
    private TextView                             mTv_platform_baidu;
    private TextView                             mTv_platform_meituan;
    private TextView                             mTv_platform_eleme;
    private String                               mStatus_eleme;
    private String                               mStatus_meituan;
    private String                               mStatus_baidu;
    private String                               mMaxStockBaidu;
    private String                               mRealStockBaidu;
    private String                               mMaxStockMeiTuan;
    private String                               mRealStockMeiTuan;
    private String                               mMaxStockEleMe;
    private String                               mRealStockEleMe;
    private String                               mShopId;
    private String                               mFoodId;
    private String                               mSizeId;
    private List<UpDateStock.BodyBean.NormsBean> mNorms;
    private LinearLayout mLl_platform_baidu;
    private LinearLayout mLl_platform_meituan;
    private LinearLayout mLl_platform_eleme;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return inflater.inflate(R.layout.order_reset_category_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initEvent();
        initShowData();//初始化显示的数据
        initPostData();//初始化上送的报文数据
    }


    private void initView(View view) {
        mTv_platform_baidu = (TextView) view.findViewById(R.id.tv_platform_baidu);
        mTv_platform_meituan = (TextView) view.findViewById(R.id.tv_platform_meituan);
        mTv_platform_eleme = (TextView) view.findViewById(R.id.tv_platform_eleme);

        mEt_order_baidu_price = (EditText) view.findViewById(R.id.et_order_baidu_price);//百度外卖单价
        mEt_order_meituan_price = (EditText) view.findViewById(R.id.et_order_meituan_price);//美团外卖单价
        mEt_order_eleme_price = (EditText) view.findViewById(R.id.et_order_eleme_price);//饿了么外卖单价

        mTv_order_baidu_maxtstock = (TextView) view.findViewById(R.id.tv_order_baidu_maxstock);//百度外卖现有库存
        mTv_order_meituan_maxtstock = (TextView) view.findViewById(R.id.tv_order_meituan_maxstock);//美团外卖现有库存
        mTv_order_eleme_maxstock = (TextView) view.findViewById(R.id.tv_order_eleme_maxstock);//饿了么外卖现有库存

        mEt_order_baidu_currentstock = (EditText) view.findViewById(R.id.et_order_baidu_currentstock);//百度外卖当前库存
        mEt_order_meituan_currentstock = (EditText) view.findViewById(R.id.et_order_meituan_currentstock);//美团当前目标库存
        mEt_order_eleme_currentstock = (EditText) view.findViewById(R.id.et_order_eleme_currentstock);//饿了么外卖当前库存

        mBt_order_baidu_offline = (Button) view.findViewById(R.id.bt_order_baidu_statu);   //百度外卖当前的上下架状态
        mBt_order_meituan_offline = (Button) view.findViewById(R.id.bt_order_meituan_statu);//美团外卖当前的上下架状态
        mBt_order_eleme_offline = (Button) view.findViewById(R.id.bt_order_eleme_statu);   //饿了么外卖当前的上下架状态

        mBtn_category_save = (Button) view.findViewById(R.id.btn_category_save);

        mLl_platform_baidu = (LinearLayout) view.findViewById(R.id.ll_platform_baidu);
        mLl_platform_meituan = (LinearLayout) view.findViewById(R.id.ll_platform_meituan);
        mLl_platform_eleme = (LinearLayout) view.findViewById(R.id.ll_platform_eleme);

        //        mBt_order_eleme_save = (Button) view.findViewById(R.id.bt_order_eleme_save);//饿了么外卖保存
        //        mBt_0 = (Button) view.findViewById(R.id.bt_0);
        //        mBt_1 = (Button) view.findViewById(R.id.bt_1);
        //        mBt_2 = (Button) view.findViewById(R.id.bt_2);
        //        mBt_3 = (Button) view.findViewById(R.id.bt_3);
        //        mBt_4 = (Button) view.findViewById(R.id.bt_4);
        //        mBt_5 = (Button) view.findViewById(R.id.bt_5);
        //        mBt_6 = (Button) view.findViewById(R.id.bt_6);
        //        mBt_7 = (Button) view.findViewById(R.id.bt_7);
        //        mBt_8 = (Button) view.findViewById(R.id.bt_8);
        //        mBt_9 = (Button) view.findViewById(R.id.bt_9);
        //        mBt_clear = (ImageButton) view.findViewById(R.id.bt_clear);
        //        mBt_clearall = (Button) view.findViewById(R.id.bt_clearall);
        //        mBt_sure = (Button) view.findViewById(R.id.bt_sure);
        //        mBt_0.setOnClickListener(this);
        //        mBt_1.setOnClickListener(this);
        //        mBt_2.setOnClickListener(this);
        //        mBt_3.setOnClickListener(this);
        //        mBt_4.setOnClickListener(this);
        //        mBt_5.setOnClickListener(this);
        //        mBt_6.setOnClickListener(this);
        //        mBt_7.setOnClickListener(this);
        //        mBt_8.setOnClickListener(this);
        //        mBt_9.setOnClickListener(this);
        //        mBt_clear.setOnClickListener(this);
        //        mBt_clearall.setOnClickListener(this);
        //        mBt_sure.setOnClickListener(this);

    }

    private void initEvent() {
        //        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        //        imm.hideSoftInputFromWindow(mEt_order_baidu_currentstock.getWindowToken(), 0);
        //        imm.hideSoftInputFromWindow(mEt_order_meituan_currentstock.getWindowToken(), 0);
        //        imm.hideSoftInputFromWindow(mEt_order_eleme_currentstock.getWindowToken(), 0);
        //        boolean active = imm.isWatchingCursor(mEt_order_baidu_currentstock);
        mEt_order_baidu_price.setInputType(InputType.TYPE_CLASS_NUMBER);
        mEt_order_meituan_price.setInputType(InputType.TYPE_CLASS_NUMBER);
        mEt_order_eleme_price.setInputType(InputType.TYPE_CLASS_NUMBER);
        mEt_order_baidu_currentstock.setInputType(InputType.TYPE_CLASS_NUMBER); // 设置输入类型
        mEt_order_meituan_currentstock.setInputType(InputType.TYPE_CLASS_NUMBER); // 设置输入类型
        mEt_order_eleme_currentstock.setInputType(InputType.TYPE_CLASS_NUMBER); // 设置输入类型

        mBt_order_baidu_offline.setOnClickListener(this);
        mBt_order_meituan_offline.setOnClickListener(this);
        mBt_order_eleme_offline.setOnClickListener(this);
        mBtn_category_save.setOnClickListener(this);
        //                EventBus.getDefault().postSticky(new FlashUiEvent());
    }

    private void initShowData() {
        CateIdEvent event = EventBus.getDefault().getStickyEvent(CateIdEvent.class);
        List<DishFoodType> dishFoodTypeList = event.getDishFoodTypeList();
        int position = event.getPosition();
        DishFoodType dishFoodType = dishFoodTypeList.get(position);
        String foodId = dishFoodType.foodId;
        List<QueryDishResponse.BodyBean.FoodsBean.NormsBean.ChannelBean> channel = dishFoodType.channel;
        if (channel == null || channel.size() == 0) {
            return;
        }
        for (int i = 0; i < channel.size(); i++) {
            if (TextUtils.equals(channel.get(i).channelId, TitleInfomation.channelId_platform_baidu)) {
                mLl_platform_baidu.setVisibility(View.VISIBLE);
                String priceBaidu = channel.get(i).price;//String.format("%.2f",num / 100.00)
                priceBaidu=getPrice(priceBaidu);
                mMaxStockBaidu = channel.get(i).stock;
                //最大库存--- fixme
                mRealStockBaidu = channel.get(i).realStock;
                String statuCurrentBaidu = "";//根据返回的状态来显示操作按钮的状态，如果返回的是上架，则显示的是停售
                mStatus_baidu = channel.get(i).status;
                switch (mStatus_baidu) {
                    case "0"://返回的是上架状态,则显示的是停售状态
                        statuCurrentBaidu = "停售";
                        mBt_order_baidu_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));//红色
                        break;
                    case "1":
                        statuCurrentBaidu = "上架";
                        mBt_order_baidu_offline.setBackgroundColor(Color.argb(255, 46, 195, 140));//绿色
                        break;
                    default:
                        statuCurrentBaidu = "--";
                        mBt_order_baidu_offline.setBackgroundColor(Color.argb(255, 196, 194, 199));//灰色
                        break;
                }
                mTv_platform_baidu.setText(TitleInfomation.channelName_platform_baidu);
                mEt_order_baidu_price.setText(priceBaidu + "");//当前价格
                mEt_order_baidu_currentstock.setText(mRealStockBaidu + "");//目标库存
                mTv_order_baidu_maxtstock.setText("/" + mMaxStockBaidu);//最大库存
                mBt_order_baidu_offline.setText(statuCurrentBaidu);
                mBt_order_baidu_offline.setClickable(true);
            }

            if (TextUtils.equals(channel.get(i).channelId, TitleInfomation.channelId_platform_meituan)) {
                mLl_platform_meituan.setVisibility(View.VISIBLE);
                String priceMeiTuan = channel.get(i).price;
                priceMeiTuan=getPrice(priceMeiTuan);//转换价格
                mMaxStockMeiTuan = channel.get(i).stock;
                //最大库存--- fixme
                mRealStockMeiTuan = channel.get(i).realStock;
                String statuCurrentMeiTuan = "";//根据返回的状态来显示操作按钮的状态，如果返回的是上架，则显示的是停售
                mStatus_meituan = channel.get(i).status;
                switch (mStatus_meituan) {
                    case "0"://返回的是上架状态,则显示的是停售状态
                        statuCurrentMeiTuan = "停售";
                        mBt_order_meituan_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                        break;
                    case "1":
                        statuCurrentMeiTuan = "上架";
                        mBt_order_meituan_offline.setBackgroundColor(Color.argb(255, 46, 195, 140));
                        break;
                    default:
                        statuCurrentMeiTuan = "--";
                        mBt_order_meituan_offline.setBackgroundColor(Color.argb(255, 196, 194, 199));
                        break;
                }
                mTv_platform_meituan.setText(TitleInfomation.channelName_platform_meituan);
                mEt_order_meituan_price.setText(priceMeiTuan + "");//当前价格
                mEt_order_meituan_currentstock.setText(mRealStockMeiTuan + "");//目标库存
                mTv_order_meituan_maxtstock.setText("/" + mMaxStockMeiTuan);//最大库存
                mBt_order_meituan_offline.setText(statuCurrentMeiTuan);
                mBt_order_meituan_offline.setClickable(true);
            }

            if (TextUtils.equals(channel.get(i).channelId,TitleInfomation.channelId_platform_eleme)) {
                mLl_platform_eleme.setVisibility(View.VISIBLE);
                String priceEleMe = channel.get(i).price;
                priceEleMe=getPrice(priceEleMe);
                mMaxStockEleMe = channel.get(i).stock;
                //最大库存--- fixme
                mRealStockEleMe = channel.get(i).realStock;
                String statuCurrentEleMe = "";//根据返回的状态来显示操作按钮的状态，如果返回的是上架，则显示的是停售
                mStatus_eleme = channel.get(i).status;
                switch (mStatus_eleme) {
                    case "0"://返回的是上架状态,则显示的是停售状态
                        statuCurrentEleMe = "停售";
                        mBt_order_eleme_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                        break;
                    case "1":
                        statuCurrentEleMe = "上架";
                        mBt_order_eleme_offline.setBackgroundColor(Color.argb(255, 46, 195, 140));
                        break;
                    default:
                        statuCurrentEleMe = "--";
                        mBt_order_eleme_offline.setBackgroundColor(Color.argb(255, 196, 194, 199));//
                        break;
                }
                mTv_platform_eleme.setText(TitleInfomation.channelName_platform_eleme);
                mEt_order_eleme_price.setText(priceEleMe + "");//当前价格
                mEt_order_eleme_currentstock.setText(mRealStockEleMe + "");//目标库存
                mTv_order_eleme_maxstock.setText("/" + mMaxStockEleMe);//最大库存
                mBt_order_eleme_offline.setText(statuCurrentEleMe);
                mBt_order_eleme_offline.setClickable(true);
            }
        }


    }

    /**
     * 转换价格
     * @param priceBaidu
     * @return
     */
    private String getPrice(String priceBaidu) {//// FIXME: 2017/2/6
        try {
            int currentPrice = Integer.parseInt(priceBaidu);//String.format("%.2f",num / 100.00)
            priceBaidu = String.format("%.2f", currentPrice / 100.00);
        } catch (Exception e) {

        }

        return priceBaidu;
    }

    private void initPostData() {
        mNorms = new ArrayList<>();
        mNorms.clear();
        mShopId = (String) SPUtils.get(getActivity(), Fields.shopId, "");
        CateIdEvent event = EventBus.getDefault().getStickyEvent(CateIdEvent.class);
        List<DishFoodType> dishFoodTypeList = event.getDishFoodTypeList();
        int position = event.getPosition();
        List<QueryDishResponse.BodyBean.FoodsBean.NormsBean.ChannelBean> channel = dishFoodTypeList.get(position).channel;
        if (channel == null) {
            return;
        }
        //得到菜品Id
        mFoodId = dishFoodTypeList.get(position).foodId;
        //得到规格Id
        mSizeId = dishFoodTypeList.get(position).sizeId;
        for (int i = 0; i < channel.size(); i++) {
            if (channel.get(i).channelId == TitleInfomation.channelId_platform_baidu) {
                String stock_baidu = mEt_order_baidu_currentstock.getText().toString();//得到输入框的目标库存
                String price_baidu = mEt_order_baidu_price.getText().toString();//得到输入框的价格
                price_baidu = getPrice(price_baidu);//转换价格
                String maxStock_baidu = mTv_order_baidu_maxtstock.getText().toString();
                UpDateStock.BodyBean.NormsBean normsBean = new UpDateStock.BodyBean.NormsBean(
                        mSizeId,
                        stock_baidu,
                        price_baidu,
                        maxStock_baidu
                );
                mNorms.add(normsBean);
            }
            if (channel.get(i).channelId == TitleInfomation.channelId_platform_meituan) {
                String stock_meituan = mEt_order_meituan_currentstock.getText().toString();//得到输入框的目标库存
                String price_meituan = mEt_order_meituan_price.getText().toString();//得到输入框的目标价格
                price_meituan = getPrice(price_meituan);
                String maxStock_meituan = mTv_order_meituan_maxtstock.getText().toString();
                UpDateStock.BodyBean.NormsBean normsBean_meituan = new UpDateStock.BodyBean.NormsBean(
                        mSizeId,
                        stock_meituan,
                        price_meituan,
                        maxStock_meituan
                );
                mNorms.add(normsBean_meituan);
            }
            if (channel.get(i).channelId ==TitleInfomation.channelId_platform_eleme) {
                String stock_eleme = mEt_order_eleme_currentstock.getText().toString();//得到输入框的目标库存
                String price_eleme = mEt_order_eleme_price.getText().toString();//得到输入框的目标库存
                price_eleme = getPrice(price_eleme);
                String maxStock_eleme = mTv_order_eleme_maxstock.getText().toString();
                UpDateStock.BodyBean.NormsBean normsBean_eleme = new UpDateStock.BodyBean.NormsBean(
                        mSizeId,
                        stock_eleme,
                        price_eleme,
                        maxStock_eleme
                );
                mNorms.add(normsBean_eleme);
            }
        }

    }

    @Override
    public void onClick(View view) {

        String termSn = DevicesInfoUtil.getTermSn();//获取终端硬件号
        String pushStatus_baidu = "";
        String pushStatus_meituan = "";
        String pushStatus_eleme = "";
        switch (view.getId()) {
            case R.id.bt_order_baidu_statu://修改百度外卖的库存
                if (mStatus_baidu == null) {
                    break;
                }
                switch (mStatus_baidu) {
                    case "0":
                        pushStatus_baidu = "1";
                        break;
                    case "1":
                        pushStatus_baidu = "0";
                        break;
                    default:
                        return;
                }
                String t422_baidu = InPutJsonData.T422(termSn, TitleInfomation.channelId_platform_baidu,
                        mFoodId, Fields.loginsessionId, mShopId, mSizeId, pushStatus_baidu);
                NetUtil.waiMaiNetWorkUtil(t422_baidu, Fields.channelId, OrderCommonResponse.class, getActivity(),
                        Fields.ORDER_TYPE_CHANGE_STORE_BAIDU);
                break;
            case R.id.bt_order_meituan_statu:
                if (mStatus_meituan == null) {
                    break;
                }
                switch (mStatus_meituan) {
                    case "0":
                        pushStatus_meituan = "1";
                        break;
                    case "1":
                        pushStatus_meituan = "0";
                        break;
                    default:
                        return;
                }
                String t422_meituan = InPutJsonData.T422(termSn,TitleInfomation.channelId_platform_meituan,
                        mFoodId, Fields.loginsessionId, mShopId, mSizeId, pushStatus_meituan);
                NetUtil.waiMaiNetWorkUtil(t422_meituan, Fields.channelId, OrderCommonResponse.class, getActivity(),
                        Fields.ORDER_TYPE_CHANGE_STORE_MEITUAN);
                break;
            case R.id.bt_order_eleme_statu:
                if (mStatus_eleme == null) {
                    break;
                }
                switch (mStatus_eleme) {
                    case "0":
                        pushStatus_eleme = "1";
                        break;
                    case "1":
                        pushStatus_eleme = "0";
                        break;
                    default:
                        return;
                }
                String t422_eleme = InPutJsonData.T422(termSn,TitleInfomation.channelId_platform_eleme,
                        mFoodId, Fields.loginsessionId, mShopId, mSizeId, pushStatus_eleme);
                NetUtil.waiMaiNetWorkUtil(t422_eleme, Fields.channelId, OrderCommonResponse.class, getActivity(),
                        Fields.ORDER_TYPE_CHANGE_STORE_ELEME);
                break;

            case R.id.btn_category_save:
                //                MyToast.show(getActivity(), "保存成功", true);
                //                if (Integer.getInteger(mRealStockBaidu) > Integer.getInteger(mEt_order_baidu_currentstock.getText().toString())) {
                //                    MyToast.show(getActivity(),"您输入的百度库存太大了哦",true);
                //                    return;
                //                }
                //                if (Integer.getInteger(mRealStockMeiTuan) > Integer.getInteger(mEt_order_meituan_currentstock.getText().toString())) {
                //                    MyToast.show(getActivity(),"您输入的美团库存太大了哦",true);
                //                    return;
                //                }
                //                if (Integer.getInteger(mRealStockEleMe) > Integer.getInteger(mEt_order_eleme_currentstock.getText().toString())) {
                //                    MyToast.show(getActivity(),"您输入的饿了么库存太大了哦",true);
                //                    return;
                //                }
                String t421 = InPutJsonData.T421(Fields.loginsessionId, "", TitleInfomation.channelId_platform_baidu,
                        mShopId, mFoodId, mNorms);//得到上送的报文
                NetUtil.waiMaiNetWorkUtil(t421, Fields.channelId, OrderCommonResponse.class, getActivity(),
                        Fields.ORDER_TYPE_CHANGE_STORE_SAVE);//请求网络
                break;
            case R.id.iv_back:
                if (flag) {

                    StockManageFragment stockManageFragment = EventBus.getDefault().getStickyEvent(CateIdEvent.class).getStockManageFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.hide(this);
                    transaction.show(stockManageFragment);
                    transaction.commit();
                    flag = false;
                } else {
                    getActivity().finish();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_CHANGE_STORE_SAVE) {
            //当前接收到的是百度的修改返回报文
            OrderCommonResponse orderCommonResponse = (OrderCommonResponse) event.getT();
            switch (orderCommonResponse.header.returnCode) {
                case "0000":
                    //说明修改成功，更新Ui
                    MyToast.show(getActivity(), "保存成功", true);
                    break;
                default:
                    MyToast.show(getActivity(), orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                    InitDataHelper.sendError(getActivity(), orderCommonResponse.header.returnMessage
                            + ":" + orderCommonResponse.header.returnCode);
                    break;
            }
        }
        if (event.getOrderType() == Fields.ORDER_TYPE_CHANGE_STORE_BAIDU) {
            OrderCommonResponse orderCommonResponse = (OrderCommonResponse) event.getT();
            if (orderCommonResponse != null
                    && orderCommonResponse.header != null
                    && orderCommonResponse.header.returnCode != null) {
                switch (orderCommonResponse.header.returnCode) {
                    case "0000":
                        //说明修改成功，更新Ui
                        switch (mBt_order_baidu_offline.getText().toString()) {
                            case "停售":
                                mBt_order_baidu_offline.setText("上架");
                                mBt_order_baidu_offline.setBackgroundColor(Color.argb(255, 46, 195, 140));
                                MyToast.show(getActivity(), "百度外卖停售成功", true);
                                break;
                            case "上架":
                                mBt_order_baidu_offline.setText("停售");
                                mBt_order_baidu_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                                MyToast.show(getActivity(), "百度外卖上架成功", true);
                                break;
                        }
                        break;
                    default:
                        MyToast.show(getActivity(), orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                        InitDataHelper.sendError(getActivity(), orderCommonResponse.header.returnMessage
                                + ":" + orderCommonResponse.header.returnCode);
                        break;
                }
            }

        }
        if (event.getOrderType() == Fields.ORDER_TYPE_CHANGE_STORE_MEITUAN) {
            OrderCommonResponse orderCommonResponse = (OrderCommonResponse) event.getT();
            if (orderCommonResponse != null
                    && orderCommonResponse.header != null
                    && orderCommonResponse.header.returnCode != null) {
                switch (orderCommonResponse.header.returnCode) {
                    case "0000":
                        //说明修改成功，更新Ui
                        switch (mBt_order_meituan_offline.getText().toString()) {
                            case "停售":
                                mBt_order_meituan_offline.setText("上架");
                                mBt_order_meituan_offline.setBackgroundColor(Color.argb(255, 46, 195, 140));
                                MyToast.show(getActivity(), "美团外卖停售成功", true);
                                break;
                            case "上架":
                                mBt_order_meituan_offline.setText("停售");
                                mBt_order_meituan_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                                MyToast.show(getActivity(), "美团外卖上架成功", true);
                                break;
                        }
                        break;
                    default:
                        MyToast.show(getActivity(), orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                        InitDataHelper.sendError(getActivity(), orderCommonResponse.header.returnMessage
                                + ":" + orderCommonResponse.header.returnCode);
                        break;
                }
            }
        }
        if (event.getOrderType() == Fields.ORDER_TYPE_CHANGE_STORE_ELEME) {
            OrderCommonResponse orderCommonResponse = (OrderCommonResponse) event.getT();
            if (orderCommonResponse != null
                    && orderCommonResponse.header != null
                    && orderCommonResponse.header.returnCode != null) {
                switch (orderCommonResponse.header.returnCode) {
                    case "0000":
                        //说明修改成功，更新Ui
                        switch (mBt_order_eleme_offline.getText().toString()) {
                            case "停售":
                                mBt_order_eleme_offline.setText("上架");
                                mBt_order_eleme_offline.setBackgroundColor(Color.argb(255, 46, 195, 140));
                                MyToast.show(getActivity(), "饿了么外卖停售成功", true);
                                break;
                            case "上架":
                                mBt_order_eleme_offline.setText("停售");
                                mBt_order_eleme_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                                MyToast.show(getActivity(), "饿了么外卖上架成功", true);
                                break;
                        }
                        break;
                    default:
                        MyToast.show(getActivity(), orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                        InitDataHelper.sendError(getActivity(), orderCommonResponse.header.returnMessage
                                + ":" + orderCommonResponse.header.returnCode);
                        break;
                }
            }

        }
    }

    boolean flag = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OrderFoodActivity) {
            //            TextView title = (TextView) ((OrderFoodActivity) context).findViewById(R.id.tv_title);
            //            title.setText("我很烦");
            ImageView iv_back = (ImageView) ((OrderFoodActivity) context).findViewById(R.id.iv_back);
            iv_back.setOnClickListener(this);
        } else {
            getActivity().finish();
        }
    }
}

