package unionlive.com.umqpos.net;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Random;

import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.in_coupons.CheckIn;
import unionlive.com.umqpos.entitys.in_coupons.PurchaseCoupons;
import unionlive.com.umqpos.entitys.in_coupons.QueryTransBasic;
import unionlive.com.umqpos.entitys.in_coupons.QueryTransDetails;
import unionlive.com.umqpos.entitys.in_coupons.ValiDateApp_Coupons;
import unionlive.com.umqpos.entitys.in_waimai.Binding;
import unionlive.com.umqpos.entitys.in_waimai.CloseOrderAuto;
import unionlive.com.umqpos.entitys.in_waimai.Complete;
import unionlive.com.umqpos.entitys.in_waimai.Confirm;
import unionlive.com.umqpos.entitys.in_waimai.CreateCategory;
import unionlive.com.umqpos.entitys.in_waimai.CreateDish;
import unionlive.com.umqpos.entitys.in_waimai.Detial;
import unionlive.com.umqpos.entitys.in_waimai.DishOnLine;
import unionlive.com.umqpos.entitys.in_waimai.ExitLogin;
import unionlive.com.umqpos.entitys.in_waimai.Login;
import unionlive.com.umqpos.entitys.in_waimai.OpenOrderAuto;
import unionlive.com.umqpos.entitys.in_waimai.OpenWm;
import unionlive.com.umqpos.entitys.in_waimai.OrderCancel;
import unionlive.com.umqpos.entitys.in_waimai.OrderList;
import unionlive.com.umqpos.entitys.in_waimai.OrderMode;
import unionlive.com.umqpos.entitys.in_waimai.QueryAppInfo;
import unionlive.com.umqpos.entitys.in_waimai.QueryAppVersion;
import unionlive.com.umqpos.entitys.in_waimai.QueryCategory;
import unionlive.com.umqpos.entitys.in_waimai.QueryDish;
import unionlive.com.umqpos.entitys.in_waimai.QueryMercLogo;
import unionlive.com.umqpos.entitys.in_waimai.QueryOrderNum;
import unionlive.com.umqpos.entitys.in_waimai.QueryPlatform;
import unionlive.com.umqpos.entitys.in_waimai.QueryShopName;
import unionlive.com.umqpos.entitys.in_waimai.ReportException;
import unionlive.com.umqpos.entitys.in_waimai.ShopBusinessInfo;
import unionlive.com.umqpos.entitys.in_waimai.ShopBusinessStatus;
import unionlive.com.umqpos.entitys.in_waimai.ShopComplain;
import unionlive.com.umqpos.entitys.in_waimai.ShopInfo;
import unionlive.com.umqpos.entitys.in_waimai.SyncBaidu;
import unionlive.com.umqpos.entitys.in_waimai.SyncCategory;
import unionlive.com.umqpos.entitys.in_waimai.UpDateStock;
import unionlive.com.umqpos.entitys.in_waimai.UpdateCategory;
import unionlive.com.umqpos.entitys.in_waimai.ValiDateApp;
import unionlive.com.umqpos.utils.DevicesInfoUtil;
import unionlive.com.umqpos.utils.LocationInfo;

import static unionlive.com.umqpos.content.Fields.loginsessionId;
import static unionlive.com.umqpos.content.Fields.sessionId;
import static unionlive.com.umqpos.pwd.DatagramSign.desAndMd5Encrypt;
import static unionlive.com.umqpos.utils.TimeHelper.getSubmitTime;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/7 21:06
 * @describe 此类只处理上送的Json数据,
 */

public class InPutJsonData {
    /**
     * 客户端应用校验
     *
     * @param context
     * @return
     */
    public static String M001(Context context) {
        ValiDateApp.HeaderBean checkClientAppHeader = new ValiDateApp.HeaderBean(
                "1.0",
                "M001",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(), // Fixme 唯一交易流水号
                "unionlive.system.validateapp"
        );
        //        String validateappHeader = getGson().toJson(header);
        DevicesInfoUtil devicesInfoUtil = new DevicesInfoUtil(context);
        LocationInfo locationInfo = new LocationInfo(context);
        ValiDateApp.BodyBean checkClientAppBody = new ValiDateApp.BodyBean(
                devicesInfoUtil.getAppId(),
                devicesInfoUtil.getAppVersionNo(),
                devicesInfoUtil.getAppVersion(),
                devicesInfoUtil.getDeviceId(),
                devicesInfoUtil.getDeviceType(),
                devicesInfoUtil.getDeviceOs(),
                devicesInfoUtil.getDeviceOSVersion(),
                devicesInfoUtil.getDeviceModel(),
                "",//浏览器类型
                "",//浏览器版本
                "",//客户端MAC地址
                locationInfo.getLongitude(),//经度
                locationInfo.getLatitude(),//纬度
                locationInfo.getCountryName(),//所在国家
                locationInfo.getCountryCode(),//国家代码
                "",//所在省份
                locationInfo.getLocality(),//所在城市
                locationInfo.getStreet0(),//街道0
                locationInfo.getStreet1(),//街道1
                locationInfo.getStreet2(),//街道2
                "",//移动运营商国家代码
                "",//移动运营商网络代码
                "",//移动运营商网络代码
                "",//移动运营商基站ID
                "",//无线AP MAC地址
                ""//无线AP SSID
        );
        ValiDateApp valiDateApp = new ValiDateApp(checkClientAppHeader, checkClientAppBody);
        return getJsonStr(valiDateApp);
    }
    /**
     * 客户端应用校验
     *
     * @param context
     * @return
     */
    public static String M001a(Context context) {
        ValiDateApp_Coupons.HeaderBean checkClientAppHeader = new ValiDateApp_Coupons.HeaderBean(
                "1.0",
                "M001",
                getSubmitTime(),
                sessionId,
                DevicesInfoUtil.getDevicesSN(context),//sn号
                getClientTraceNo(), // Fixme 唯一交易流水号
                ""//这是signMessage
        );
        //        String validateappHeader = getGson().toJson(header);
        DevicesInfoUtil devicesInfoUtil = new DevicesInfoUtil(context);
        LocationInfo locationInfo = new LocationInfo(context);
        ValiDateApp_Coupons.BodyBean checkClientAppBody = new ValiDateApp_Coupons.BodyBean(
                devicesInfoUtil.getAppId(),
                devicesInfoUtil.getAppVersionNo(),
                devicesInfoUtil.getAppVersion(),
                devicesInfoUtil.getDeviceId(),
                devicesInfoUtil.getDeviceType(),
                devicesInfoUtil.getDeviceOs(),
                devicesInfoUtil.getDeviceOSVersion(),
                devicesInfoUtil.getDeviceModel(),
                "",//浏览器类型
                "",//浏览器版本
                "",//客户端MAC地址
                locationInfo.getLongitude(),//经度
                locationInfo.getLatitude(),//纬度
                locationInfo.getCountryName(),//所在国家
                locationInfo.getCountryCode(),//国家代码
                "",//所在省份
                locationInfo.getLocality(),//所在城市
                locationInfo.getStreet0(),//街道0
                locationInfo.getStreet1(),//街道1
                locationInfo.getStreet2(),//街道2
                "",//移动运营商国家代码
                "",//移动运营商网络代码
                "",//移动运营商网络代码
                "",//移动运营商基站ID
                "",//无线AP MAC地址
                ""//无线AP SSID
        );
        ValiDateApp_Coupons valiDateApp = new ValiDateApp_Coupons(checkClientAppHeader,checkClientAppBody);
        return getJsonStr(valiDateApp);
    }


    /**
     * 客户端错误报告
     *
     * @param context
     * @return
     */
    public static String M003(Context context,String bugDesc) {
        ReportException.HeaderBean headerBean = new ReportException.HeaderBean(
                "1.0",
                "M003",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.system.queryappversion",
                DevicesInfoUtil.getTermSn()

        );
        DevicesInfoUtil devicesInfoUtil = new DevicesInfoUtil(context);
        LocationInfo locationInfo = new LocationInfo(context);
        ReportException.BodyBean bodyBean = new ReportException.BodyBean(
                devicesInfoUtil.getAppId(),
                devicesInfoUtil.getAppVersionNo(),
                devicesInfoUtil.getAppVersion(),
                devicesInfoUtil.getDeviceId(),
                devicesInfoUtil.getDeviceType(),
                devicesInfoUtil.getDeviceOs(),
                devicesInfoUtil.getDeviceOSVersion(),
                devicesInfoUtil.getDeviceModel(),
                "",//浏览器类型
                "",//浏览器版本
                "",//客户端MAC地址
                locationInfo.getLongitude(),//经度
                locationInfo.getLatitude(),//纬度
                locationInfo.getCountryName(),//所在国家
                locationInfo.getCountryCode(),//国家代码
                "",//所在省份
                locationInfo.getLocality(),//所在城市
                locationInfo.getStreet0(),//街道0
                locationInfo.getStreet1(),//街道1
                locationInfo.getStreet2(),//街道2
                "",//移动运营商国家代码
                "",//移动运营商网络代码
                "",//移动运营商网络代码
                "",//移动运营商基站ID
                "",//无线AP MAC地址
                "",//无线AP SSID
                bugDesc
        );
        ReportException reportException = new ReportException(headerBean, bodyBean);
        return getJsonStr(reportException);//得到加密后的报文数据
    }

    /**
     * 客户端应用信息查询
     *
     * @param context
     * @param htmlType 应用富文本信息的类型：:0：关于信息 1：使用协议信息
     * @return
     */
    public static String M004(Context context, String htmlType) {
        DevicesInfoUtil devicesInfoUtil = new DevicesInfoUtil(context);

        QueryAppInfo.HeaderBean headerBean = new QueryAppInfo.HeaderBean(
                "1.0",
                "M004",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.system.queryappinfo"
        );
        QueryAppInfo.BodyBean bodyBean = new QueryAppInfo.BodyBean(
                devicesInfoUtil.getAppId(),
                htmlType
        );
        QueryAppInfo queryAppInfo = new QueryAppInfo(headerBean, bodyBean);
        return getJsonStr(queryAppInfo);//得到加密后的密文
    }

    /**
     * 商户登录接口
     *
     * @param context
     * @param shopId     门店编号
     * @param shopOperId 门店操作员编号
     * @param password   操作员密码
     * @return
     */
    public static String T100(Context context, String shopId, String shopOperId, String password) {
        Login.HeaderBean headerBean = new Login.HeaderBean(
                "1.0",
                "T100",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.login"
        );
        Login.BodyBean bodyBean = new Login.BodyBean(
                shopId,
                shopOperId,
                password
        );
        Login login = new Login(headerBean, bodyBean);
        return getJsonStr(login);
    }

    /**
     * 商铺基本信息查询
     *
     * @param context
     * @return
     */
    public static String T101(Context context, String loginSessionId,String termSn) {
        ShopInfo.HeaderBean headerBean = new ShopInfo.HeaderBean(
                "1.0",
                "T101",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.queryshopinfo",
                DevicesInfoUtil.getTermSn()
        );
        ShopInfo.BodyBean bodyBean = new ShopInfo.BodyBean(loginSessionId);
        ShopInfo shopInfo = new ShopInfo(headerBean, bodyBean);
        return getJsonStr(shopInfo);//得到加密后的密文
    }

    /**
     * 商铺营业情况查询
     *
     * @param context
     * @param loginSessionId
     * @return
     */
    public static String T102(Context context, String loginSessionId) {
        ShopBusinessInfo.HeaderBean headerBean = new ShopBusinessInfo.HeaderBean(
                "1.0",
                "T102",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.shopbusinessinfo",
                DevicesInfoUtil.getTermSn()
        );
        ShopBusinessInfo.BodyBean bodyBean = new ShopBusinessInfo.BodyBean(loginSessionId);
        ShopBusinessInfo shopBusinessInfo = new ShopBusinessInfo(headerBean, bodyBean);
        return getJsonStr(shopBusinessInfo);//店铺营业情况查询
    }

    /**
     * 商铺营业状态变更
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param channelId
     * @param status
     * @return
     */
    public static String T103(Context context, String loginSessionId, String channelId, int status) {
        ShopBusinessStatus.HeaderBean headerBean = new ShopBusinessStatus.HeaderBean(
                "1.0",
                "T103",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.shopbusinessstatus",
                DevicesInfoUtil.getTermSn()
        );
        ShopBusinessStatus.BodyBean bodyBean = new ShopBusinessStatus.BodyBean(loginSessionId, channelId, status);
        ShopBusinessStatus shopBusinessStatus = new ShopBusinessStatus(headerBean, bodyBean);
        return getJsonStr(shopBusinessStatus);
    }

    /**
     * 商户投诉建议
     *
     * @param context
     * @param loginSessionId 登录会话编号,由接口M001返回
     * @param complainMsg    投诉建议内容
     * @return
     */
    public static String T104(Context context, String loginSessionId, String complainMsg) {
        ShopComplain.HeaderBean headerBean = new ShopComplain.HeaderBean(
                "1.0",
                "T104",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.shopcomplain",
                DevicesInfoUtil.getTermSn()
        );
        ShopComplain.BodyBean bodyBean = new ShopComplain.BodyBean(loginSessionId, complainMsg);
        ShopComplain shopComplain = new ShopComplain(headerBean, bodyBean);
        return getJsonStr(shopComplain);
    }

    /**
     * 商户名称查询
     *
     * @param context
     * @param shopId  门店编号
     * @return
     */
    public static String T105(Context context, String shopId) {
        QueryShopName.HeaderBean headerBean = new QueryShopName.HeaderBean(
                "1.0",
                "T105",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.queryshopname",
                DevicesInfoUtil.getTermSn()
        );
        QueryShopName.BodyBean bodyBean = new QueryShopName.BodyBean(shopId);
        QueryShopName queryShopName = new QueryShopName(headerBean, bodyBean);
        return getJsonStr(queryShopName);
    }

    /**
     * 商户外卖平台查询
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @return
     */
    public static String T106(Context context, String loginSessionId) {
        QueryPlatform.HeaderBean headerBean = new QueryPlatform.HeaderBean(
                "1.0",
                "T106",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.queryplatform",
                DevicesInfoUtil.getTermSn()
        );
        QueryPlatform.BodyBean bodyBean = new QueryPlatform.BodyBean(loginSessionId);
        QueryPlatform queryPlatform = new QueryPlatform(headerBean, bodyBean);
        return getJsonStr(queryPlatform);
    }

    /**
     * 商户名称与LOGO查询
     *
     * @param context
     * @param appid   M001接口返回的appid
     * @return
     */
    public static String T107(Context context, String appid) {
        QueryMercLogo.HeaderBean headerBean = new QueryMercLogo.HeaderBean(
                "1.0",
                "T107",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.querymerclogo"
        );
        QueryMercLogo.BodyBean bodyBean = new QueryMercLogo.BodyBean(appid);
        QueryMercLogo queryMercLogo = new QueryMercLogo(headerBean, bodyBean);
        return getJsonStr(queryMercLogo);
    }

    /**
     * 商户退出登录
     *
     * @param context
     * @param loginSessionId 登录会话编号,由M001接口返回
     * @param termSn
     * @return
     */
    public static String T108(Context context, String loginSessionId, String termSn) {
        ExitLogin.HeaderBean headerBean = new ExitLogin.HeaderBean(
                "1.0",
                "T108",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.exitlogin",
                DevicesInfoUtil.getTermSn()
        );
        ExitLogin.BodyBean bodyBean = new ExitLogin.BodyBean(loginSessionId);
        ExitLogin exitLogin = new ExitLogin(headerBean, bodyBean);
        return getJsonStr(exitLogin);
    }

    /**
     * 外卖功能开启的接口
     * @param loginSessionId
     * @param clietId
     * @param forcedLogin
     * @param termType
     * @return
     */
    public static String T109(String loginSessionId, String clietId, String forcedLogin, String termType) {
        OpenWm.HeaderBean headerBean = new OpenWm.HeaderBean(
                "1.0",
                "T109",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.openwm",
                DevicesInfoUtil.getTermSn()
        );

        OpenWm.BodyBean bodyBean = new OpenWm.BodyBean(
                loginSessionId,
                clietId,
                forcedLogin,
                termType
        );
        OpenWm openWm = new OpenWm(bodyBean, headerBean);
        return getJsonStr(openWm);
    }

    /**
     * 订单查询
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param status         订单状态:
     *                       >>--待确认订单：-1
     *                       >>--确认接单：1；
     *                       >>--正在取餐：2
     *                       >>--正在配送：3
     *                       >>--已完成：0；
     *                       >>--拒绝接单：8
     *                       >>--取消订单：9
     *                       >>--取消订单：89（包含89两个状态）
     *                       >>--已处理订单：101（包含除未处理订单以外的所有状态）
     *                       >>--已接预定订单：21（非立即送达的）
     *                       >>--为空时，默认查询全部订单
     * @param channelId      外卖平台编号 fixme 这是外卖的渠道编号不传表示全部查询，不同于每个接口中的channelId
     * @param pageSize       分页大小
     * @param pageIndex      分页索引
     * @param beginDate      开始日期
     * @param endDate        结束日期
     * @return
     */
    public static String T201(Context context,
                              String loginSessionId,
                              int status,
                              String channelId,
                              int pageSize,
                              int pageIndex,
                              String beginDate,
                              String endDate,
                              String termSn) {

        OrderList.HeaderBean headerBean = new OrderList.HeaderBean(
                "1.0",
                "T201",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.order.list",
                DevicesInfoUtil.getTermSn()
        );
        OrderList.BodyBean bodyBean = new OrderList.BodyBean(
                loginSessionId,
                status,
                channelId,
                pageSize,
                pageIndex,
                beginDate,
                endDate

        );
        OrderList orderList = new OrderList(headerBean, bodyBean);
        return getJsonStr(orderList);
    }

    /**
     * 订单详情查询
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param orderId        订单编号
     * @param termSn
     * @return
     */
    public static String T202(Context context, String loginSessionId, String orderId, String termSn) {
        Detial.HeaderBean headerBean = new Detial.HeaderBean(
                "1.0",
                "T202",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.order.detail",
                DevicesInfoUtil.getTermSn()
        );
        Detial.BodyBean bodyBean = new Detial.BodyBean(loginSessionId, orderId);
        Detial detial = new Detial(headerBean, bodyBean);
        return getJsonStr(detial);
    }

    /**
     * 订单搜索结果 //这个接口不用了  TODO
     *
     * @param context
     * @return
     */
    public static String T203(Context context) {
        return null;
    }

    /**
     * 订单数量查询
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param orderDate      订单日期
     * @return
     */
    public static String T204(Context context, String loginSessionId, String orderDate) {
        QueryOrderNum.HeaderBean headerBean = new QueryOrderNum.HeaderBean(
                "1.0",
                "T204",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.order.process.number",
                DevicesInfoUtil.getTermSn()
        );
        QueryOrderNum.BodyBean bodyBean = new QueryOrderNum.BodyBean(loginSessionId, orderDate);
        QueryOrderNum queryOrderNum = new QueryOrderNum(headerBean, bodyBean);
        return getJsonStr(queryOrderNum);
    }

    /**
     * 完成订单
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param orderId        订单编号
     * @return 上送的密文
     */
    public static String T300(Context context, String loginSessionId, String orderId) {
        Complete.HeaderBean headerBean = new Complete.HeaderBean(
                "1.0",
                "T300",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.order.complete"
        );
        Complete.BodyBean bodyBean = new Complete.BodyBean(loginSessionId, orderId);
        Complete complete = new Complete(headerBean, bodyBean);
        return getJsonStr(complete);
    }

    /**
     * 确定订单
     *
     * @param context
     * @param loginSessionId 登录的会话编号
     * @param orderId        订单编号
     * @param autoOrder      自动接单标记    自动接单--->>0  手动接单---->>>1
     * @return
     */
    public static String T301(Context context, String loginSessionId, String orderId, int autoOrder) {
        Confirm.HeaderBean headerBean = new Confirm.HeaderBean(
                "1.0",
                "T301",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.order.confirm",
                DevicesInfoUtil.getTermSn()
        );
        Confirm.BodyBean bodyBean = new Confirm.BodyBean(loginSessionId, orderId, autoOrder);
        Confirm confirm = new Confirm(headerBean, bodyBean);
        return getJsonStr(confirm);
    }

    /**
     * 取消订单
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param orderId        订单编号
     * @param status         订单状态   拒绝接单：8   取消订单：9
     * @param cancelType     取消原因类型
     * @param cancelReason   自定义取消原因
     * @param termSn
     * @return
     */
    public static String T302(Context context, String loginSessionId, String orderId, int status,
                              int cancelType, String cancelReason, String termSn) {
        OrderCancel.HeaderBean headerBean = new OrderCancel.HeaderBean(
                "1.0",
                "T302",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.order.cancel",
                DevicesInfoUtil.getTermSn()
        );
        OrderCancel.BodyBean bodyBean = new OrderCancel.BodyBean(loginSessionId, orderId, status, cancelType, cancelReason);
        OrderCancel orderCancel = new OrderCancel(headerBean, bodyBean);
        return getJsonStr(orderCancel);
    }

    /**
     * 商户开启自动接单
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param channelId      外卖的渠道
     * @return
     */
    public static String T303(Context context, String loginSessionId, String channelId,String updateType) {
        OpenOrderAuto.HeaderBean headerBean = new OpenOrderAuto.HeaderBean(
                "1.0",
                "T303",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.openorderauto",
                DevicesInfoUtil.getTermSn()
        );
        OpenOrderAuto.BodyBean bodyBean = new OpenOrderAuto.BodyBean(loginSessionId, channelId,updateType);
        OpenOrderAuto openOrderAuto = new OpenOrderAuto(headerBean, bodyBean);
        return getJsonStr(openOrderAuto);
    }

    /**
     * 商户关闭自动接单
     *
     * @param context
     * @param loginSessionId 登录会话编号
     * @param channelId      外卖的渠道
     * @return
     */
    public static String T304(Context context, String loginSessionId, String channelId,String updateType) {
        CloseOrderAuto.HeaderBean headerBean = new CloseOrderAuto.HeaderBean(
                "1.0",
                "T304",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
          "unionlive.merchant.closeorderauto",
                DevicesInfoUtil.getTermSn()
        );
        CloseOrderAuto.BodyBean bodyBean = new CloseOrderAuto.BodyBean(loginSessionId, channelId,updateType);
        CloseOrderAuto closeOrderAuto = new CloseOrderAuto(headerBean, bodyBean);
        return getJsonStr(closeOrderAuto);
    }

    /**
     * 同步更新百度门店
     *
     * @param context
     * @param channelId 外卖渠道编号
     * @param shopId    门店ID
     * @param category1 主营分类
     * @param category2 第一副营
     * @param category3 第二幅营
     * @return
     */
    public static String T305(Context context, String channelId, String shopId, String category1, String category2, String category3) {
        SyncBaidu.HeaderBean headerBean = new SyncBaidu.HeaderBean(
                "1.0",
                "T305",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.shop.sync"
        );
        SyncBaidu.BodyBean bodyBean = new SyncBaidu.BodyBean(channelId, shopId, category1, category2, category3);
        SyncBaidu syncBaidu = new SyncBaidu(headerBean, bodyBean);
        return getJsonStr(syncBaidu);
    }

    /**
     * 更新餐厅接单模式
     *
     * @param context
     * @param channelId 外卖渠道编号
     * @param mercId    商户ID
     * @param mode      模式
     * @return
     */
    public static String T320(Context context, String channelId, String mercId, int mode) {
        OrderMode.HeaderBean headerBean = new OrderMode.HeaderBean(
                "1.0",
                "T306",//FIXME 这个要引起注意
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.order.mode"
        );
        OrderMode.BodyBean bodyBean = new OrderMode.BodyBean(channelId, mercId, mode);
        OrderMode orderMode = new OrderMode(headerBean, bodyBean);
        return getJsonStr(orderMode);
    }

    /**
     * 绑定饿了么餐厅ID
     *
     * @param context
     * @param channelId    外卖渠道编号
     * @param mercId       商户ID
     * @param restaurantId 饿了么id
     * @return
     */
    public static String T321(Context context, String channelId, String mercId, String restaurantId) {
        Binding.HeaderBean headerBean = new Binding.HeaderBean(
                "1.0",
                "T321",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.merchant.binding"
        );
        Binding.BodyBean bodyBean = new Binding.BodyBean(channelId, mercId, restaurantId);
        Binding binding = new Binding(headerBean, bodyBean);
        return getJsonStr(binding);
    }

    /**
     * 新增菜品分类
     *
     * @param context
     * @param mercId   商户ID
     * @param cateName 分类名称
     * @param cateDesc 分类描述
     * @param imgUrl   图片地址
     * @param priority 优先级
     * @return
     */
    public static String T401(Context context, String mercId, String cateName, String cateDesc, String imgUrl, int priority) {
        CreateCategory.HeaderBean headerBean = new CreateCategory.HeaderBean(
                "1.0",
                "T401",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.dish.category.create"
        );
        CreateCategory.BodyBean bodyBean = new CreateCategory.BodyBean(mercId,
                cateName,
                cateDesc,
                imgUrl,
                priority);
        CreateCategory createCategory = new CreateCategory(headerBean, bodyBean);
        return getJsonStr(createCategory);
    }

    /**
     * 更新菜品分类
     *
     * @param context
     * @param mercId   商户ID         是商户Id,不是门店ID
     * @param cateId   分类ID
     * @param cateName 分类名称
     * @param cateDesc 分类描述
     * @param priority 优先级
     * @param imgUrl   图片地址
     * @return
     */
    public static String T402(Context context, String mercId, String cateId, String cateName, String cateDesc, int priority, String imgUrl) {
        UpdateCategory.HeaderBean headerBean = new UpdateCategory.HeaderBean(
                "1.0",
                "T402",//Fixme 跟接口文档有区别,需要引起注意
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.dish.category.update"
        );
        UpdateCategory.BodyBean bodyBean = new UpdateCategory.BodyBean(
                mercId,
                cateId,
                cateName,
                cateDesc,
                priority,
                imgUrl
        );
        UpdateCategory updateCategory = new UpdateCategory(headerBean, bodyBean);
        return getJsonStr(updateCategory);
    }

    /**
     * 菜品分类查询
     *
     * @return
     */
    public static String T403(String loginsessionId, String shopId, String cateName, String termSn) {
        QueryCategory.HeaderBean headerBean = new QueryCategory.HeaderBean(
                getClientTraceNo(),
                "unionlive.dish.category.query",
                sessionId,
                getSubmitTime(),
                "T403",//Fixme 与开发文档有出入
                "1.0",
                DevicesInfoUtil.getTermSn()
        );
        QueryCategory.BodyBean bodyBean = new QueryCategory.BodyBean(loginsessionId, shopId, cateName);
        QueryCategory queryCategory = new QueryCategory(bodyBean, headerBean);
        return getJsonStr(queryCategory);
    }

    /**
     * 菜品分类同步
     *
     * @param context
     * @param channelId 外卖渠道ID
     * @param shopId    门店户ID
     * @param cateId    分类ID
     * @return
     */
    public static String T404(Context context, String channelId, String shopId, String cateId) {
        SyncCategory.HeaderBean headerBean = new SyncCategory.HeaderBean(
                "1.0",
                "T404",//Fixme 与开发文档有出入
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.dish.category.sync"
        );
        SyncCategory.BodyBean bodyBean = new SyncCategory.BodyBean(channelId, shopId, cateId);
        SyncCategory syncCategory = new SyncCategory(headerBean, bodyBean);
        return getJsonStr(syncCategory);
    }

    /**
     * 新增菜品
     *
     * @param context
     * @param mercId         商户ID
     * @param foodName       食物名称
     * @param cateId         分类编号
     * @param priority       优先级
     * @param upc            条形码
     * @param picUrl         图片地址
     * @param minOrderNum    最小起订份数
     * @param packageNum     餐盒数量
     * @param desc           描述
     * @param availDate      销售限制
     * @param availTimeStart 起售时间
     * @param availTimeEnd   截止时间
     * @param attrName       菜品属性名称
     * @param attrValue      菜品属性值
     * @param isNew          是否新菜品
     * @param isFeatured     是否招牌菜
     * @param isSpicy        是否辣
     * @param isGum          是否配菜
     * @return
     */
    public static String T410(Context context, String mercId, String foodName, String cateId, int priority, String upc, String picUrl, int minOrderNum, int packageNum, String desc, String availDate, String availTimeStart, String availTimeEnd, String attrName, String attrValue, String isNew, String isFeatured, String isSpicy, int isGum) {
        CreateDish.HeaderBean headerBean = new CreateDish.HeaderBean(
                "1.0",
                "T410",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.dish.create"
        );
        CreateDish.BodyBean bodyBean = new CreateDish.BodyBean(
                mercId,
                foodName,
                cateId,
                priority,
                upc,
                picUrl,
                minOrderNum,
                packageNum,
                desc,
                availDate,
                availTimeStart,
                availTimeEnd,
                attrName,
                attrValue,
                isNew,
                isFeatured,
                isSpicy,
                isGum
        );
        CreateDish createDish = new CreateDish(headerBean, bodyBean);
        return getJsonStr(createDish);
    }

    /**
     *
     * @param loginsessionId
     * @param shopId
     * @param cateId
     * @param pageSize
     * @param pageIndex
     * @param termSn
     * @return
     */
    public static String T412(String loginsessionId, String shopId, String cateId, String pageSize,
                              String pageIndex, String termSn) {
        QueryDish.HeaderBean headerBean = new QueryDish.HeaderBean(
                "1.0",
                "T412",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.dish.query",
                DevicesInfoUtil.getTermSn()
        );
        QueryDish.BodyBean bodyBean = new QueryDish.BodyBean(
                loginsessionId,
                shopId,
                cateId,
                pageSize,
                pageIndex
        );
        QueryDish queryDish = new QueryDish(headerBean, bodyBean);
        return getJsonStr(queryDish);
    }

    /**
     *
     * @param loginsessionId
     * @param termSn
     * @param channelId
     * @param shopId
     * @param foodId
     * @param norms
     * @return
     */
   public static String T421(String loginsessionId, String termSn, String channelId, String shopId, String foodId, List<UpDateStock.BodyBean.NormsBean> norms) {
       UpDateStock.HeaderBean headerBean = new UpDateStock.HeaderBean(
               "1.0",
               "T421",
               getSubmitTime(),
               sessionId,
               getClientTraceNo(),
               "unionlive.dish.update.stock",
               DevicesInfoUtil.getTermSn()
       );
       UpDateStock.BodyBean bodyBean = new UpDateStock.BodyBean(
               loginsessionId,
               channelId,
               shopId,
               foodId,
               norms
       );
       UpDateStock upDateStock = new UpDateStock(headerBean, bodyBean);
       return getJsonStr(upDateStock);
   }

    /**
     * 修改菜品的上下架报文
     * @param termSn
     * @param channelId
     * @param foodId
     * @param loginsessionId
     * @param shopId
     * @param sizeId
     * @param status
     * @return
     */
    public static String T422(String termSn, String channelId, String foodId,
                              String loginsessionId, String shopId, String sizeId, String status) {
        DishOnLine.HeaderBean headerBean = new DishOnLine.HeaderBean(
                "1.0",
                "T422",
                getSubmitTime(),
                sessionId,
                getClientTraceNo(),
                "unionlive.dish.online",
                DevicesInfoUtil.getTermSn()
        );
        DishOnLine.BodyBean bodyBean = new DishOnLine.BodyBean(
                channelId,
                foodId,
                loginsessionId,
                shopId,
                sizeId,
                status
        );
        DishOnLine dishOnLine = new DishOnLine(bodyBean, headerBean);
        return getJsonStr(dishOnLine);
    }
    //-------------------------------------------------------下面是电子券验券的接口-----------------------------------------

    /**
     * 客户端应用版本查询
     *
     * @param context
     * @return
     */
    public static String M002(Context context) {

        QueryAppVersion.HeaderBean queryAppVersionHeader = new QueryAppVersion.HeaderBean(
                "1.0",
                "M002",
                getSubmitTime(),
                Fields.sessionId,
                DevicesInfoUtil.getDevicesSN(context),//sn号
                getClientTraceNo(), // Fixme 唯一交易流水号
                ""

               );
        DevicesInfoUtil devicesInfoUtil = new DevicesInfoUtil(context);
        QueryAppVersion.BodyBean queryAppVersionBody = new QueryAppVersion.BodyBean(
                devicesInfoUtil.getAppId(),
                devicesInfoUtil.getAppVersionNo(),
                devicesInfoUtil.getAppVersion(),
                devicesInfoUtil.getDeviceId(),
                devicesInfoUtil.getDeviceType(),
                devicesInfoUtil.getDeviceOs(),
                devicesInfoUtil.getDeviceOSVersion(),
                devicesInfoUtil.getDeviceModel(),
                "",
                ""
        );
        QueryAppVersion queryAppVersion = new QueryAppVersion(queryAppVersionHeader,queryAppVersionBody);
        return getJsonStr(queryAppVersion);//得到加密后的报文数据
    }

    /**
     *  登录接口
     * @param context
     * @param operId 操作员编号
     * @param operPwd 操作员密码
     * @return
     */
    public static String M100(Context context, String operId, String operPwd) {
        final CheckIn.HeaderBean headerBean = new CheckIn.HeaderBean(
                "1.0",
                "M100",
                getSubmitTime(),
                Fields.sessionId,
                DevicesInfoUtil.getDevicesSN(context),//sn号
//                "LANDI50905703",
                getClientTraceNo(), // Fixme 唯一交易流水号
                ""//这是signMessage
        );
        final CheckIn.BodyBean bodyBean = new CheckIn.BodyBean(operId,operPwd);
        final CheckIn checkIn = new CheckIn(headerBean, bodyBean);
        return getJsonStr(checkIn);

    }

    /**
     * 电子券兑换
     * @param context
     * @param couponsNo
     * @param useTimes
     * @return
     */
    public static String M102(Context context, String couponsNo, int useTimes,String termId,String shopId,String shopOperId) {
        PurchaseCoupons.HeaderBean headerBean = new PurchaseCoupons.HeaderBean(
                "1.0",
                "M102",
                getSubmitTime(),
                sessionId,
                DevicesInfoUtil.getDevicesSN(context),//终端硬件序列号  FIXME
                getClientTraceNo(),
                "" //报文签名数据
        );

//        String pwd = (String) SPUtils.get(context, Fields.pwd, "");
        PurchaseCoupons.BodyBean bodyBean
                = new PurchaseCoupons.BodyBean(loginsessionId,
                termId, shopId, shopOperId, couponsNo, useTimes);
        PurchaseCoupons purchaseCoupons = new PurchaseCoupons(headerBean, bodyBean);
        return getJsonStr(purchaseCoupons);
    }

    /**
     *
     * @param context
     * @param sortBy 排序依据
     * @param pageSize 分页大小
     * @param pageIndex 分页索引
     * @return
     */
    public static String M201(Context context, String sortBy, int pageSize, int pageIndex,String termId,String shopId,String shopOperId) {
        QueryTransBasic.HeaderBean headerBean = new QueryTransBasic.HeaderBean(
                "1.0",
                "M201",
                getSubmitTime(),
                sessionId,
                DevicesInfoUtil.getDevicesSN(context),//终端硬件序列号  FIXME
                getClientTraceNo(),
                "" //报文签名数据
        );

        QueryTransBasic.BodyBean bodyBean = new QueryTransBasic.BodyBean(
                loginsessionId,
                termId,
                shopId,
                shopOperId,
                sortBy,
                pageSize,
                pageIndex

        );
        QueryTransBasic queryTransBasic = new QueryTransBasic(headerBean, bodyBean);
        return getJsonStr(queryTransBasic);
    }

    /**
     *
     * @param context
     * @param transTime 原Pos交易时间 接口M201返回
     * @param hostTime  后台处理时间   接口M201返回
     * @param hostTrace  后台交易流水号   接口M201返回
     * @param authCode   授权码，接口M102返回
     * @return
     */
    public static String M202(Context context, String transTime, String hostTime, String hostTrace, String authCode,String termId,String shopId,String shopOperId) {
        QueryTransDetails.HeaderBean headerBean = new QueryTransDetails.HeaderBean(
                "1.0",
                "M202",
                getSubmitTime(),
                sessionId,
                DevicesInfoUtil.getDevicesSN(context),//终端硬件序列号  FIXME
                getClientTraceNo(),
                "" //报文签名数据
        );

        QueryTransDetails.BodyBean bodyBean = new QueryTransDetails.BodyBean(
                loginsessionId,
                termId,
                shopId,
                shopOperId,
                transTime,
                hostTime,
                hostTrace,
                authCode
        );
        QueryTransDetails queryTransDetails = new QueryTransDetails(headerBean, bodyBean);

        return getJsonStr(queryTransDetails);
    }

    //-------------------------------------------------------上面是电子券验券的接口-----------------------------------------


    /**
     * @param t                javaBean的实例
     * @param <T>需要转换的javaBean
     * @return
     */
    private static <T> String getJsonStr(T t) {
        String jsonStr = new GsonBuilder()
                .excludeFieldsWithModifiers()
                .create()
                .toJson(t);//将javaBean转换为字符串
        System.out.println("上送的报文: \n\n\n"+jsonStr+"\n\n\n\n");
        String encryptStr = desAndMd5Encrypt(jsonStr,Fields.desAndMd5Decoder_padding,
                Fields.desAndMd5Decoder_macSeret, Fields.desAndMd5Decoder_seret);//加密后的报文
        encryptStr = encryptStr.replace("+", "%2B").replace("/", "%2F");
        return encryptStr;
    }


    /**
     * 随机生成客户端交易流水号
     *
     * @return
     */
    private static String getClientTraceNo() {
        String submitTime = getSubmitTime();//获取当前时间
        StringBuffer sb = new StringBuffer();
        sb.append(submitTime).append("_");
        for (int i = 0; i < 20; i++) {//获取20个1~9的随机数
            int j = new Random().nextInt(10);
            sb.append(j);
        }
        return sb.toString();
    }

}
