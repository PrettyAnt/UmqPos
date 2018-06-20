package unionlive.com.umqpos.ui.fragment.manage_wm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryShopNameResponse;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.NetErrorEvent;
import unionlive.com.umqpos.event.TitleStatus;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.LdPrintWaimai;
import unionlive.com.umqpos.ui.dialog.MyToast;
import unionlive.com.umqpos.utils.SPUtils;
import unionlive.com.umqpos.utils.SoundPlayUtils;

import static unionlive.com.umqpos.R.id.bt_setting_print_test;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/15 17:18
 * @describe 外卖-->管理--->设置
 */
public class SettingFragment extends BaseFragment implements View.OnClickListener {

    private RadioButton mRb_meituan_business_on;
    private RadioButton mRb_meituan_business_off;
    private RadioButton mRb_baidu_business_on;
    private RadioButton mRb_baidu_business_off;
    private RadioButton mRb_eleme_business_on;
    private RadioButton mRb_eleme_business_off;
    private RadioButton mRb_wechat_business_on;
    private RadioButton mRb_wechat_business_off;
    private RadioButton mRb_autoreceive_switch_on;
    private RadioButton mRb_autoreceive_swith_off;
    private Button      mBt_setting_self_print;
    private Button      mBt_setting_print_test;
    private Spinner     mSpi_setting_bule_print;

    private TextView     mTv_baidu_waimai;
    private TextView     mTv_meituan_waimai;
    private TextView     mTv_eleme_waimai;
    private TextView     mTv_wechat_waimai;
    private String       mT103_baidu_on;
    private String       mT103_baidu_off;
    private String       mT103_meituan_on;
    private String       mT103_meituan_off;
    private String       mT103_eleme_on;
    private String       mT103_eleme_off;
    private String       mT103_wechat_on;
    private String       mT103_wechat_off;
    private String       mBaidu_auto_on;
    private String       mMeituan_auto_on;
    private String       mEleme_auto_on;
    private String       mWechat_auto_on;
    private String       mBaidu_auto_off;
    private String       mMeituan_auto_off;
    private String       mEleme_auto_off;
    private String       mQuery_bussiness_status;
    private String       mShopId;
    private LinearLayout mLl_baidu_switch;
    private LinearLayout mLl_meituan_switch;
    private LinearLayout mLl_eleme_switch;
    private LinearLayout mLl_wechat_switch;
    private Button mBt_setting_exit_waimai;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manage_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        //initTitleData();
        initEvent();
        initViewState();//初始化控件的打开或者关闭状态
        //        initNet();
    }



    //    /**
    //     * 初始化平台数据
    //     * 查询外卖平台---得到各个平台的接单状态以及营业状态
    //     */
    //    private void initNet() {
    //        NetUtil.waiMaiNetWorkUtil(mQuery_bussiness_status, Fields.channelId,
    // QueryPlatformResponse.class, getActivity(),
    //                Fields.ORDER_TYPE_CHECK_STATUS);
    //    }

    @Override
    public void onStart() {
        super.onStart();
        //        initViewState();//初始化控件的状态
    }

    /**
     * 初始化控件状态
     */
    private void initViewState() {
        //        if (TitleInfomation.baiduAutoOrder == 0) {//百度的自动接单是开启状态
        //
        //        } else {//百度自动接单是关闭状态
        //
        //        }
        if (TitleInfomation.allAutoOrder == 0) {//自动接单是开启状态
            mRb_autoreceive_switch_on.setChecked(true);
        } else {//自动接单是关闭状态
            mRb_autoreceive_swith_off.setChecked(true);
        }
        if (TitleInfomation.baiduIsOpen) {//百度外卖开启了,设置百度的开关可见
            mLl_baidu_switch.setVisibility(View.VISIBLE);
            mTv_baidu_waimai.setText(TitleInfomation.channelName_platform_baidu);
            if (TitleInfomation.baiduBusinessStatus == 0) {//百度外卖开始营业
                mRb_baidu_business_on.setChecked(true);
            }
            if (TitleInfomation.baiduBusinessStatus == 1) {//百度外卖关闭营业
                mRb_baidu_business_off.setChecked(true);
            }
        } else {//百度外卖关闭了
            mLl_baidu_switch.setVisibility(View.GONE);
        }
        if (TitleInfomation.meituanIsOpen) {
            mLl_meituan_switch.setVisibility(View.VISIBLE);
            mTv_meituan_waimai.setText(TitleInfomation.channelName_platform_meituan);
            if (TitleInfomation.meituanBusinessStatus == 0) {//美团外卖开始营业
                mRb_meituan_business_on.setChecked(true);
            }
            if (TitleInfomation.meituanBusinessStatus == 1) {//美团外卖关闭营业
                mRb_meituan_business_off.setChecked(true);
            }
        } else {
            mLl_meituan_switch.setVisibility(View.GONE);
        }
        if (TitleInfomation.elemeIsOpen) {
            mLl_eleme_switch.setVisibility(View.VISIBLE);
            mTv_eleme_waimai.setText(TitleInfomation.channelName_platform_eleme);
            if (TitleInfomation.elemeBusinessStatus == 0) {//饿了么外卖开始营业
                mRb_eleme_business_on.setChecked(true);
            }
            if (TitleInfomation.elemeBusinessStatus == 1) {//饿了么外卖关闭营业
                mRb_eleme_business_off.setChecked(true);
            }
        } else {
            mLl_eleme_switch.setVisibility(View.GONE);
        }
        if (TitleInfomation.wechatIsOpen) {
            mLl_wechat_switch.setVisibility(View.VISIBLE);
            mTv_wechat_waimai.setText(TitleInfomation.channelName_platform_wechat);
            if (TitleInfomation.wechatBusinessStatus == 0) {//微信外卖开始营业
                mRb_wechat_business_on.setChecked(true);
            }
            if (TitleInfomation.wechatBusinessStatus == 1) {//微信外卖关闭营业
                mRb_wechat_business_off.setChecked(true);
            }
        } else {
            mLl_wechat_switch.setVisibility(View.GONE);
        }
    }

    private void initView(View view) {//// FIXME: 2017/2/3
        mTv_baidu_waimai = (TextView) view.findViewById(R.id.tv_baidu_waimai);
        mTv_meituan_waimai = (TextView) view.findViewById(R.id.tv_meituan_waimai);
        mTv_eleme_waimai = (TextView) view.findViewById(R.id.tv_eleme_waimai);
        mTv_wechat_waimai = (TextView) view.findViewById(R.id.tv_wechat_waimai);
        mRb_meituan_business_on = (RadioButton) view.findViewById(R.id.rb_meituan_business_on);
        //美团开始营业
        mRb_meituan_business_off = (RadioButton) view.findViewById(R.id.rb_meituan_business_off);
        //美团关闭营业
        mRb_baidu_business_on = (RadioButton) view.findViewById(R.id.rb_baidu_business_on);//百度开始营业
        mRb_baidu_business_off = (RadioButton) view.findViewById(R.id.rb_baidu_switch_off);//百度关闭营业
        mRb_eleme_business_on = (RadioButton) view.findViewById(R.id.rb_eleme_business_on);
        //饿了么开
        mRb_eleme_business_off = (RadioButton) view.findViewById(R.id.rb_eleme_business_off);
        //饿了么关闭营业
        mRb_wechat_business_on = (RadioButton) view.findViewById(R.id.rb_wechat_business_on);
        //微信开
        mRb_wechat_business_off = (RadioButton) view.findViewById(R.id.rb_wechat_business_off);
        //微信关闭营业
        mRb_autoreceive_switch_on = (RadioButton) view.findViewById(R.id
                .rb_autoreceive_switch_on); //自动接单开
        mRb_autoreceive_swith_off = (RadioButton) view.findViewById(R.id
                .rb_autoreceive_business_off);//自动接单关
        mBt_setting_self_print = (Button) view.findViewById(R.id.bt_setting_self_print);//内置打印机开
        mBt_setting_print_test = (Button) view.findViewById(bt_setting_print_test);//打印测试
        mSpi_setting_bule_print = (Spinner) view.findViewById(R.id.spi_setting_bule_print);//蓝牙打印

        mLl_baidu_switch = (LinearLayout) view.findViewById(R.id.ll_baidu_switch);//百度
        mLl_meituan_switch = (LinearLayout) view.findViewById(R.id.ll_meituan_switch);//美团
        mLl_eleme_switch = (LinearLayout) view.findViewById(R.id.ll_eleme_switch);//饿了么
        mLl_wechat_switch = (LinearLayout) view.findViewById(R.id.ll_wechat_switch);//微信
        mBt_setting_exit_waimai = (Button) view.findViewById(R.id.bt_setting_exit_waimai);

    }

    private void initEvent() {
        mSpi_setting_bule_print.setVisibility(View.INVISIBLE);
        mBt_setting_print_test.setOnClickListener(this);
        mRb_meituan_business_on.setOnClickListener(this);
        mRb_meituan_business_off.setOnClickListener(this);
        mRb_baidu_business_on.setOnClickListener(this);
        mRb_baidu_business_off.setOnClickListener(this);
        mRb_eleme_business_on.setOnClickListener(this);
        mRb_eleme_business_off.setOnClickListener(this);
        mRb_wechat_business_on.setOnClickListener(this);
        mRb_wechat_business_off.setOnClickListener(this);
        mRb_autoreceive_switch_on.setOnClickListener(this);
        mRb_autoreceive_swith_off.setOnClickListener(this);

        mBt_setting_exit_waimai.setOnClickListener(this);//退出接单

    }

    StringBuffer currentStatus = new StringBuffer();
    StringBuffer buttonStatus = new StringBuffer();
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_setting_self_print:
                MyToast.show(getActivity(), "这是内置打印机", true);
                break;
            case bt_setting_print_test:
                mShopId = (String) SPUtils.get(getActivity(), Fields.shopId, "");
                String t105 = InPutJsonData.T105(getActivity(), mShopId);
                NetUtil.waiMaiNetWorkUtil(t105, Fields.channelId, QueryShopNameResponse.class,
                        getActivity(), Fields.ORDER_TYPE_QUERY_SHOP_NAME);
                break;

            case R.id.rb_baidu_business_on://百度外卖平台上线
                //                NetUtil.waiMaiNetWorkUtil(mT103_baidu_on,Fields.channelId,
                // OpenWmResponse.class,getActivity(),-1);
                TitleInfomation.baiduBusinessStatus = 0;
                showCurrentStatus(TitleInfomation.channelName_platform_baidu,"开启失败","0");
                upDateBusinessStatus(TitleInfomation.channelId_platform_baidu, TitleInfomation
                        .baiduBusinessStatus, Fields.platform_baidu_open);
                break;
            case R.id.rb_baidu_switch_off:
                TitleInfomation.baiduBusinessStatus = 1;
                showCurrentStatus(TitleInfomation.channelName_platform_baidu,"关闭失败","1");
                upDateBusinessStatus(TitleInfomation.channelId_platform_baidu, TitleInfomation
                        .baiduBusinessStatus, Fields.platform_baidu_close);
                break;

            case R.id.rb_meituan_business_on://美团外卖平台上线-->并设置自动接单
                TitleInfomation.meituanBusinessStatus = 0;
                showCurrentStatus(TitleInfomation.channelName_platform_meituan,"开启失败","2");
                upDateBusinessStatus(TitleInfomation.channelId_platform_meituan, TitleInfomation
                        .meituanBusinessStatus, Fields.platform_meituan_open);
                break;
            case R.id.rb_meituan_business_off:
                TitleInfomation.meituanBusinessStatus = 1;
                showCurrentStatus(TitleInfomation.channelName_platform_meituan,"关闭失败","3");
                upDateBusinessStatus(TitleInfomation.channelId_platform_meituan, TitleInfomation
                        .meituanBusinessStatus, Fields.platform_meituan_close);
                break;

            case R.id.rb_eleme_business_on://饿了吗平台上线,并设置自动接单
                TitleInfomation.elemeBusinessStatus = 0;
                showCurrentStatus(TitleInfomation.channelName_platform_eleme,"开启失败","4");
                upDateBusinessStatus(TitleInfomation.channelId_platform_eleme, TitleInfomation
                        .elemeBusinessStatus, Fields.platform_eleme_open);
                break;
            case R.id.rb_eleme_business_off:
                TitleInfomation.baiduBusinessStatus = 1;
                showCurrentStatus(TitleInfomation.channelName_platform_eleme,"关闭失败","5");
                upDateBusinessStatus(TitleInfomation.channelId_platform_eleme, TitleInfomation
                        .baiduBusinessStatus, Fields.platform_baidu_close);
                break;
            case R.id.rb_wechat_business_on:
                TitleInfomation.wechatBusinessStatus = 0;
                showCurrentStatus(TitleInfomation.channelName_platform_wechat,"开启失败","6");
                upDateBusinessStatus(TitleInfomation.channelId_platform_wechat, TitleInfomation
                        .wechatBusinessStatus, Fields.platform_wechat_open);
                break;
            case R.id.rb_wechat_business_off:
                TitleInfomation.wechatBusinessStatus = 1;
                showCurrentStatus(TitleInfomation.channelName_platform_wechat,"关闭失败","7");
                upDateBusinessStatus(TitleInfomation.channelId_platform_wechat, TitleInfomation
                        .wechatBusinessStatus, Fields.platform_baidu_close);
                break;
            case R.id.rb_autoreceive_switch_on://商户开启自动接单
                showCurrentStatus("自动接单","开启失败","8");
                String openAuto = InPutJsonData.T303(getActivity(), Fields.loginsessionId,"","all");
                NetUtil.waiMaiNetWorkUtil(openAuto,Fields.channelId,OpenWmResponse.class,getActivity(),Fields.OPEN_ORDER_AUTO);//开启自动接单
                break;
            case R.id.rb_autoreceive_business_off://商户关闭自动接单
                showCurrentStatus("自动接单","关闭失败","9");
                String closeAuto = InPutJsonData.T304(getActivity(), Fields.loginsessionId, "","all");
                NetUtil.waiMaiNetWorkUtil(closeAuto,Fields.channelId,OpenWmResponse.class,getActivity(),Fields.CLOSE_ORDER_AUTO);//关闭自动接单
//                SoundPlayUtils.play(2);
//                closeOrderAuto();//关闭自动接单
                break;
            case R.id.bt_setting_exit_waimai:
                CancleOrderDialog.exitWaimaiDialog(getActivity(), "确定退出外卖?");
                break;

        }
    }

    private void showCurrentStatus(String platName,String status,String buttonState) {
        currentStatus = new StringBuffer();
        buttonStatus = new StringBuffer();
        currentStatus.append(platName + status);
        buttonStatus.append(buttonState);
    }

    /**
     * 关闭自动接单
     */
    private void closeOrderAuto() {
        ArrayList<String> channelId_platform = new ArrayList<>();
        channelId_platform.add(TitleInfomation.channelId_platform_baidu);
        channelId_platform.add(TitleInfomation.channelId_platform_meituan);
        channelId_platform.add(TitleInfomation.channelId_platform_eleme);
        channelId_platform.add(TitleInfomation.channelId_platform_wechat);
        for (int i = 0; i < channelId_platform.size(); i++) {
            String closeAuto = InPutJsonData.T304(getActivity(), Fields.loginsessionId, "","all");
            NetUtil.waiMaiNetWorkUtil(closeAuto,Fields.channelId,OpenWmResponse.class,getActivity(),-1);
        }

    }

    /**
     * 开启自动接单
     */
    private void openOrderAuto() {
        ArrayList<String> channelId_platform = new ArrayList<>();
        channelId_platform.add(TitleInfomation.channelId_platform_baidu);
        channelId_platform.add(TitleInfomation.channelId_platform_meituan);
        channelId_platform.add(TitleInfomation.channelId_platform_eleme);
        channelId_platform.add(TitleInfomation.channelId_platform_wechat);
        for (int i = 0; i < channelId_platform.size(); i++) {
            String openAuto = InPutJsonData.T303(getActivity(), Fields.loginsessionId,"","all");
            NetUtil.waiMaiNetWorkUtil(openAuto,Fields.channelId,OpenWmResponse.class,getActivity(),-1);
        }

    }

    /**
     * @param channelIdPlatform 外卖平台
     * @param businessStatus    营业状态
     * @param OrderType         区分消息源
     */
    private void upDateBusinessStatus(String channelIdPlatform, int businessStatus, int OrderType) {
        String t103 = InPutJsonData.T103(getActivity(), Fields.loginsessionId
                , channelIdPlatform, businessStatus);
        NetUtil.waiMaiNetWorkUtil(t103, Fields.channelId, OpenWmResponse.class, getActivity(),
                OrderType);
    }

    /**
     * @param platformType   用来区分外卖的渠道 0:百度，1：美团，2：饿了么，3：微信，4：其他。。。
     * @param businessStatus 用来判断外卖的营业状态
     * @param autoOrder      用来判断渠道的自动接单的状态
     */
    private void reFlushStatus(int platformType, int businessStatus, int autoOrder) {
        EventBus.getDefault().post(new TitleStatus(platformType, businessStatus, autoOrder));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(CoubonsDecoderEvent event) {
        //测试打印
        if (event.getOrderType() == Fields.ORDER_TYPE_QUERY_SHOP_NAME) {
            QueryShopNameResponse nameResponse = (QueryShopNameResponse) event.getT();
            switch (nameResponse.header.returnCode) {
                case "0000":
//                    Printer landiPrinter = Printer.getInstance();
                    LdPrintWaimai landiPrintWaimai = LdPrintWaimai.getInstance();
                    landiPrintWaimai.setShopId(mShopId);
                    landiPrintWaimai.setVersionCode(DevicesInfoUtil.getVersionName(getActivity()));
                    landiPrintWaimai.setDevicesSN(DevicesInfoUtil.getDevicesSN(getActivity()));
                    landiPrintWaimai.setT(nameResponse);
                    try {
                        landiPrintWaimai.start();
                    } catch (RequestException e) {
                        e.printStackTrace();
                    }
                    break;
                default:

                    break;
            }

        }
//根据类型开启或关闭营业
        switch (event.getOrderType()) {
            case Fields.platform_baidu_open://百度开启营业
                platFormStatus(event, Fields.platform_baidu_open);
                break;
            case Fields.platform_baidu_close://百度关闭营业
                platFormStatus(event, Fields.platform_baidu_close);
                break;
            case Fields.platform_meituan_open://美团开启营业
                platFormStatus(event, Fields.platform_meituan_open);
                break;
            case Fields.platform_meituan_close://美团关闭营业
                platFormStatus(event, Fields.platform_meituan_close);
                break;
            case Fields.platform_eleme_open://饿了么开启营业
                platFormStatus(event, Fields.platform_eleme_open);
                break;
            case Fields.platform_eleme_close://饿了么关闭营业
                platFormStatus(event, Fields.platform_eleme_close);
                break;
            case Fields.platform_wechat_open://微信开启营业
                platFormStatus(event, Fields.platform_wechat_open);
                break;
            case Fields.platform_wechat_close://微信关闭营业
                platFormStatus(event, Fields.platform_wechat_close);
                break;


        }
        //开启自动接单
        if (event.getOrderType() == Fields.OPEN_ORDER_AUTO) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000":
                    reFlushStatus(-1, -1, 0);//更新控件
                    TitleInfomation.allAutoOrder=0;
                    SoundPlayUtils.play(1);
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(getActivity(), openWmResponse.header.returnMessage, null);
                    break;
            }
        }
        //关闭自动接单
        if (event.getOrderType() == Fields.CLOSE_ORDER_AUTO) {
            OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();
            switch (openWmResponse.header.returnCode) {
                case "0000":
                    reFlushStatus(-1, -1, 1);//更新控件
                    TitleInfomation.allAutoOrder=1;
                    SoundPlayUtils.play(2);
                    break;
                default:
                    CancleOrderDialog.isNetErrorDialog(getActivity(), openWmResponse.header.returnMessage, null);
                    break;
            }
        }
    }

    /**
     * @param event            得到的报文
     * @param platformBusiness 营业状态
     */
    private void platFormStatus(CoubonsDecoderEvent event, int platformBusiness) {
        OpenWmResponse openWmResponse = (OpenWmResponse) event.getT();

        switch (openWmResponse.header.returnCode) {
            case "0000":
                switch (platformBusiness) {//判断消息源
                    case Fields.platform_baidu_open://百度外卖开始营业
                        TitleInfomation.baiduBusinessStatus = 0;
                        reFlushStatus(0, 0, -1);
                        break;
                    case Fields.platform_baidu_close://百度外卖开始营业
                        TitleInfomation.baiduBusinessStatus = 1;
                        reFlushStatus(0, 1, -1);
                        break;
                    case Fields.platform_meituan_open://美团外卖开始营业
                        TitleInfomation.meituanBusinessStatus = 0;
                        reFlushStatus(1, 0, -1);
                        break;
                    case Fields.platform_meituan_close://美团外卖关闭营业
                        TitleInfomation.meituanBusinessStatus = 1;
                        reFlushStatus(1, 1, -1);
                        break;
                    case Fields.platform_eleme_open://饿了么外卖开营业始
                        TitleInfomation.elemeBusinessStatus = 0;
                        reFlushStatus(2, 0, -1);
                        break;
                    case Fields.platform_eleme_close://饿了么外卖关闭营业
                        TitleInfomation.elemeBusinessStatus = 1;
                        reFlushStatus(2, 1, -1);
                        break;
                    case Fields.platform_wechat_open://微信外卖开始营业
                        TitleInfomation.wechatBusinessStatus = 0;
                        reFlushStatus(3, 0, -1);
                        break;
                    case Fields.platform_wechat_close://微信外卖关闭营业
                        TitleInfomation.wechatBusinessStatus = 1;
                        reFlushStatus(3, 1, -1);
                        break;

                }
                break;
            default:
                CancleOrderDialog.isNetErrorDialog(getActivity(),currentStatus.toString()
                        +"\n返回码:"+openWmResponse.header.returnCode, null);
                setButtonState();//设置开关状态

                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void NetErrorEvent(NetErrorEvent event){
        if (event.isNetError()) {
            setButtonState();//设置开关状态
        }
    }
    private void setButtonState() {
        //设置开关状态
        switch (buttonStatus.toString()) {
            case "0":
                mRb_baidu_business_off.setChecked(true);
                TitleInfomation.baiduBusinessStatus = 1;
                break;
            case "1":
                mRb_baidu_business_on.setChecked(true);
                TitleInfomation.baiduBusinessStatus = 0;
                break;
            case "2":
                mRb_meituan_business_off.setChecked(true);
                TitleInfomation.meituanBusinessStatus = 1;
                break;
            case "3":
                mRb_meituan_business_on.setChecked(true);
                TitleInfomation.meituanBusinessStatus = 0;
                break;
            case "4":
                mRb_eleme_business_off.setChecked(true);
                TitleInfomation.elemeBusinessStatus = 1;
                break;
            case "5":
                mRb_eleme_business_on.setChecked(true);
                TitleInfomation.elemeBusinessStatus = 0;
                break;
            case "6":
                mRb_wechat_business_off.setChecked(true);
                TitleInfomation.wechatBusinessStatus = 1;
                break;
            case "7":
                mRb_wechat_business_on.setChecked(true);
                TitleInfomation.wechatBusinessStatus = 0;
                break;
            case "8":
                mRb_autoreceive_swith_off.setChecked(true);
                break;
            case "9":
                mRb_autoreceive_switch_on.setChecked(true);
                break;

        }
    }

}
