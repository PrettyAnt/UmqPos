package unionlive.com.umqpos.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
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
import unionlive.com.umqpos.entitys.out_waimai.OrderCommonResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryDishResponse;
import unionlive.com.umqpos.event.CateIdEvent;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.IsFinishCurrUiEvent;
import unionlive.com.umqpos.event.StockChangedEvent;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseActivity;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.InitDataHelper;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.SPUtils;


/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/2/7 9:36
 * @describe ${TODO}
 */

public class OrderResertCategoryActivity extends BaseActivity implements View.OnClickListener {
    private EditText                             mEt_order_baidu_price;
    private EditText                             mEt_order_meituan_price;
    private EditText                             mEt_order_eleme_price;
    private TextView                             mTv_order_baidu_maxtstock;
    private TextView                             mTv_order_meituan_maxtstock;
    private TextView                             mTv_order_eleme_maxstock;
    private EditText                             mEt_order_baidu_currentstock;
    private EditText mEt_order_meituan_currentstock;
    private EditText mEt_order_eleme_currentstock;
    private Button   mBt_order_baidu_offline;
    private Button   mBt_order_meituan_offline;
    private Button   mBt_order_eleme_offline;
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
    private LinearLayout                         mLl_platform_baidu;
    private LinearLayout                         mLl_platform_meituan;
    private LinearLayout                         mLl_platform_eleme;
    private ImageView mIv_back;
    private TextView mTv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.order_reset_category_activity);
        initView();
        initEvent();
        initShowData();//初始化显示的数据
        initPostData();//初始化上送的报文数据
    }




    private void initView() {
        mTv_platform_baidu = (TextView) findViewById(R.id.tv_platform_baidu);
        mTv_platform_meituan = (TextView)findViewById(R.id.tv_platform_meituan);
        mTv_platform_eleme = (TextView)findViewById(R.id.tv_platform_eleme);
        mEt_order_baidu_price = (EditText) findViewById(R.id.et_order_baidu_price);//百度外卖单价
        mEt_order_meituan_price = (EditText)findViewById(R.id.et_order_meituan_price);//美团外卖单价
        mEt_order_eleme_price = (EditText) findViewById(R.id.et_order_eleme_price);//饿了么外卖单价
        mTv_order_baidu_maxtstock = (TextView)findViewById(R.id.tv_order_baidu_maxstock);//百度外卖现有库存
        mTv_order_meituan_maxtstock = (TextView)findViewById(R.id.tv_order_meituan_maxstock);//美团外卖现有库存
        mTv_order_eleme_maxstock = (TextView)findViewById(R.id.tv_order_eleme_maxstock);//饿了么外卖现有库存
        mEt_order_baidu_currentstock = (EditText)findViewById(R.id.et_order_baidu_currentstock);//百度外卖当前库存
        mEt_order_meituan_currentstock = (EditText)findViewById(R.id.et_order_meituan_currentstock);//美团当前目标库存
        mEt_order_eleme_currentstock = (EditText)findViewById(R.id.et_order_eleme_currentstock);//饿了么外卖当前库存
        mBt_order_baidu_offline = (Button) findViewById(R.id.bt_order_baidu_statu);   //百度外卖当前的上下架状态
        mBt_order_meituan_offline = (Button)findViewById(R.id.bt_order_meituan_statu);//美团外卖当前的上下架状态
        mBt_order_eleme_offline = (Button)findViewById(R.id.bt_order_eleme_statu);   //饿了么外卖当前的上下架状态
        mBtn_category_save = (Button)findViewById(R.id.btn_category_save);
        mLl_platform_baidu = (LinearLayout)findViewById(R.id.ll_platform_baidu);
        mLl_platform_meituan = (LinearLayout)findViewById(R.id.ll_platform_meituan);
        mLl_platform_eleme = (LinearLayout)findViewById(R.id.ll_platform_eleme);
        //返回键
        mIv_back = (ImageView) findViewById(R.id.iv_back);
        //标题
        mTv_title = (TextView) findViewById(R.id.tv_title);
    }

    private void initEvent() {
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
        mIv_back.setOnClickListener(this);

    }
    private void initShowData() {

        CateIdEvent event = EventBus.getDefault().getStickyEvent(CateIdEvent.class);
        List<DishFoodType> dishFoodTypeList = event.getDishFoodTypeList();
        int position = event.getPosition();
        DishFoodType dishFoodType = dishFoodTypeList.get(position);
        String foodId = dishFoodType.foodId;
        String foodName = dishFoodType.foodName;
        String sizeName = dishFoodType.sizeName;
        mTv_title.setText("修改库存"+"("+foodName+(TextUtils.isEmpty(sizeName)?"":"-"+sizeName)+")");
        mTv_title.setTextSize(16);
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

    ArrayList<String> platformList = new ArrayList<>();//存放平台的信息
    private void initPostData() {
        mNorms = new ArrayList<>();
        mNorms.clear();
        mShopId = (String) SPUtils.get(this, Fields.shopId, "");
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
        platformList.clear();
        for (int i = 0; i < channel.size(); i++) {
            if (channel.get(i).channelId == TitleInfomation.channelId_platform_baidu) {
                platformList.add(channel.get(i).channelId);
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
                platformList.add(channel.get(i).channelId);
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
                platformList.add(channel.get(i).channelId);
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
                NetUtil.waiMaiNetWorkUtil(t422_baidu, Fields.channelId, OrderCommonResponse.class, this,
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
                NetUtil.waiMaiNetWorkUtil(t422_meituan, Fields.channelId, OrderCommonResponse.class, this,
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
                NetUtil.waiMaiNetWorkUtil(t422_eleme, Fields.channelId, OrderCommonResponse.class, this,
                        Fields.ORDER_TYPE_CHANGE_STORE_ELEME);
                break;

            case R.id.btn_category_save:
                for (int i = 0; i < platformList.size(); i++) {//保存信息
                    String t421 = InPutJsonData.T421(Fields.loginsessionId, "",platformList.get(i),
                            mShopId, mFoodId, mNorms);//得到上送的报文
                    NetUtil.waiMaiNetWorkUtil(t421, Fields.channelId, OrderCommonResponse.class, this,
                            Fields.ORDER_TYPE_CHANGE_STORE_SAVE);//请求网络
                }

                break;
            case R.id.iv_back:
                this.finish();
                break;
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

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(CoubonsDecoderEvent event) {
        if (event.getOrderType() == Fields.ORDER_TYPE_CHANGE_STORE_SAVE) {
            //当前接收到的是百度的修改返回报文
            OrderCommonResponse orderCommonResponse = (OrderCommonResponse) event.getT();
            switch (orderCommonResponse.header.returnCode) {
                case "0000":
                    //说明修改成功，更新Ui
                    MyToast.show(this, "保存成功", true);
                    break;
                default:
                    MyToast.show(this, orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                    InitDataHelper.sendError(this,orderCommonResponse.header.returnMessage
                            +":"+orderCommonResponse.header.returnCode);
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
                                MyToast.show(this, "百度外卖停售成功", true);
                                break;
                            case "上架":
                                mBt_order_baidu_offline.setText("停售");
                                mBt_order_baidu_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                                MyToast.show(this, "百度外卖上架成功", true);
                                break;
                        }
                        //由于此界面的信息是由stockManageFragment传过来的，存在的情况是当此界面的信息更新了，原来的界面的信息没有更新
                        //所以应该发送一条消息通知上一个界面更新数据！
                        EventBus.getDefault().postSticky(new StockChangedEvent(true));
                        break;
                    default:
                        MyToast.show(this, orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                        InitDataHelper.sendError(this,orderCommonResponse.header.returnMessage
                                +":"+orderCommonResponse.header.returnCode);
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
                                MyToast.show(this, "美团外卖停售成功", true);
                                break;
                            case "上架":
                                mBt_order_meituan_offline.setText("停售");
                                mBt_order_meituan_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                                MyToast.show(this, "美团外卖上架成功", true);
                                break;
                        }
                        EventBus.getDefault().postSticky(new StockChangedEvent(true));
                        break;
                    default:
                        MyToast.show(this, orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                        InitDataHelper.sendError(this,orderCommonResponse.header.returnMessage
                        +":"+orderCommonResponse.header.returnCode);
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
                                MyToast.show(this, "饿了么外卖停售成功", true);
                                break;
                            case "上架":
                                mBt_order_eleme_offline.setText("停售");
                                mBt_order_eleme_offline.setBackgroundColor(Color.argb(255, 255, 60, 94));
                                MyToast.show(this, "饿了么外卖上架成功", true);
                                break;
                        }
                        EventBus.getDefault().postSticky(new StockChangedEvent(true));
                        break;
                    default:
                        MyToast.show(this, orderCommonResponse.header.returnMessage, true);//未能成功保存，返回状态
                        InitDataHelper.sendError(this,orderCommonResponse.header.returnMessage
                                +":"+orderCommonResponse.header.returnCode);
                        break;
                }
            }

        }
    }
    /**
     * 接受来自个推的消息，判断是否退出当前的界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGtEvent(IsFinishCurrUiEvent event) {
        if (TextUtils.equals(event.getFlag(), "wm_01")) {
            if (event.isFinish()) {
                this.finish();
            }
        }
    }
}
