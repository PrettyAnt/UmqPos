package unionlive.com.umqpos.content;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/2 13:25
 * @describe 存放字段
 */

public class Fields {


    //    /********************************* 下面是电子券验券的URL ************************************/
    //    public static String dev_couponsBaseUrl = "http://d.umq.me/";//测试环境的BaseUrl
    //        //    http://d.umq.me/umqservice/PosService.asmx?aid=
    //        //    http://u.umq.me/umqservice/PosService.asmx?aid=
    //        //    http://umq.cc/posservice/PosService.asmx?aid=
    public static String sessionId      = "";//唯一的会话ID M001接口的后台返回值//FixME
    // 6a9a866d-4a73-4dee-b055-3236a56d524f
    public static String loginsessionId = "";//这是签到的sessionId aae30d61-0d87-4767-bafc-a1db660f5f6d

    //    public static String dev_BaseUrl = "http://www.baidu.com/";//测试环境的BaseUrl
    public static String Ciphertext;//密文
    public static String Cleartext;// 明文
    //    public static String channelId = "182000010000001";//渠道编号 182000010000001---------验券中的aid
    public static String  channelId           = getChannelId();//渠道编号
    // 182000010000001---------验券中的aid
    //************************************判断是否登录外卖**********************************/
    public static boolean is_Open_WaiMai_Flag = false;//判断外卖是否已经开启
    //    public static boolean is_checking         = false;//判断是否已经签到
    public static String  current_ui_flag     = "main_01";//默认界面----这是每个界面的标记
    public static boolean is_start_waimai_act = false;
    public static int     count               = 0;


    //判断是否启动外卖的act---tips:点击外卖的按钮后，需要先刷新状态栏，再启动外卖的activity。但某些情况仅需要刷新状态栏，以此来标记是否启动activity

    private static String getChannelId() {
        return "182000010000001";
    }

    public static String clientId = "clientId";//个推返回过来的id
    //************************************** 下面是SharedPreferences里的字段-----用于初始化商户信息
    // ****************************************/
    public static String shopName;//商户名,供打印调用
    public static String shopOperId = "shopOperId";
    public static String pwd        = "pwd";


    public static String shopId   = "shopId";//由后台返回得到的，存在SP中
    public static String transMap = "transMap";//由后台返回得到的，存在sp中
    public static String termId   = "termId";//终端编号，存放sp中  调用案例：String termId =
    // (String) SPUtils.get(context, Fields.termId, "");
    public static String authCode = "authCode";//接口M102返回字段，供M202使用，存放Sp中
    public static String termSn   = "termSn";//终端编号，存放sp中  调用案例：String termId =
    // (String) SPUtils.get(context, Fields.termId, "");
    // /***************************************下面是设置界面开关的状态*************************************************************************/
    public static boolean SWITCH_MEITUAN     = false;//美团外卖的开关状态
    public static boolean SWITCH_BAIDU       = false;//百度外卖的开关状态
    public static boolean SWITCH_ELEME       = false;//饿了么外卖的开关状态
    public static boolean SWITCH_WECHAT      = false;//微信外卖的开关状态
    public static boolean SWITCH_AUTORECEIVE = false;//自动接单的开关状态
    ///******************************************当前商户的接单状态---是否自动接单******************************************************************/
    //    public static boolean is_auto_receive_order = true;//当前的商户接单状态
    ///**********************************************************根据字段调用对应apiIntface
    // 的方法**********************************************************************/
    public static String  ValidateApp        = "ValidateApp";//客户端应用校验
    public static String  QueryAppVersion    = "QueryAppVersion";//手机客户端应用版本查询
    public static String  ReportException    = "ReportException";//客户端错误报告
    public static String  QueryAppInfo       = "QueryAppInfo";//手机客户端应用信息查询
    public static String  CheckIn            = "CheckIn";//签到
    public static String  PurchaseCoupons    = "PurchaseCoupons";//电子券兑换
    public static String  QueryTransBasic    = "QueryTransBasic";//交易基本信息查询
    public static String  QueryTransDetails  = "QueryTransDetails";//交易明细查询

    ///*****************************************查询获取到的已开通外卖的渠道编号*******************************************************/
    public static       String channelId_platform_baidu     = "";//百度
    public static       String channelId_platform_meituan   = "";//美团
    public static       String channelId_platform_eleme     = "";//饿了么
    public static       String channelId_platform_wechat    = "";//微信
    public static       String channelId_platform_other     = "";  //其他---兼容下个版本
    ///*****************************************外卖开始营业*******************************************************/
    public static final int    platform_baidu_open          = 120;//百度
    public static final int    platform_eleme_open          = 121;//饿了么
    public static final int    platform_meituan_open        = 122;//美团
    public static final int    platform_wechat_open         = 123;//微信
    public static final int    platform_other_open          = 124;  //其他---兼容下个版本
    ///*****************************************外卖关闭营业*******************************************************/
    public static final int    platform_baidu_close         = 125;//百度
    public static final int    platform_eleme_close         = 126;//饿了么
    public static final int    platform_meituan_close       = 127;//美团
    public static final int    platform_wechat_close        = 128;//微信
    public static final int    platform_other_close         = 129;  //其他---兼容下个版本
    ///***********************************查询获得的渠道名称*******************************************/
    public static       String channelName_platform_baidu   = "";//百度
    public static       String channelName_platform_eleme   = "";//饿了么
    public static       String channelName_platform_meituan = "";//美团
    public static       String channelName_platform_wechat  = "";//微信
    public static       String channelName_platform_other   = "";  //其他---兼容下个版本
    ///****************下面是一个标记，使用eventbus
    // 的时候，用来判断是由那个界面传来的，主要处理返回时的界面显示问题*****************************/
    public static       int    ORDER_TYPE_UNTREATE_ALL      = 1;
    public static       int    ORDER_TYPE_UNTREATE_BAIDU    = 2;
    public static       int    ORDER_TYPE_UNTREATE_MEITUAN  = 3;
    public static       int    ORDER_TYPE_UNTREATE_ELEME    = 4;
    public static       int    ORDER_TYPE_TREATE_ALL        = 5;
    public static       int    ORDER_TYPE_TREATE_BAIDU      = 6;
    public static       int    ORDER_TYPE_TREATE_MEITUAN    = 7;
    public static       int    ORDER_TYPE_TREATE_ELEME      = 8;
    public static       int    ORDER_TYPE_ABOUT_SHOPINFO    = 9;//店铺信息

    public static int ORDER_TYPE_DETIAL_PRINT               = 10;//订单详情里面的打印--->>直接查询详情就打印
    public static int ORDER_TYPE_DETIAL_RECEIVE_ORDER_PRINT = 11;
    //订单详情里面的打印--->>先确认--->>在查询详情--->>再打印
    public static int ORDER_TYPE_CATEGORY                   = 12;//是否是查询菜品分类
    public static int ORDER_TYPE_CATEGORY_DETIAL            = 13;//是否是分类下的菜品列表
    public static int ORDER_TYPE_CHANGE_STORE               = 14;//外卖---修改库存
    public static int ORDER_TYPE_CHANGE_STORE_BAIDU         = 15;//外卖---修改库存--百度
    public static int ORDER_TYPE_CHANGE_STORE_MEITUAN       = 16;//外卖---修改库存--美团
    public static int ORDER_TYPE_CHANGE_STORE_ELEME         = 17;//外卖---修改库存--饿了么
    public static int ORDER_TYPE_CHANGE_STORE_SAVE          = 18;//外卖---修改库存--保存
    public static int ORDER_TYPE_CHECK_STATUS               = 19;//显示当前的接单状态
    public static int ORDER_TYPE_OPEN_WM                    = 20;//开启关闭外卖的状态

    public static int COUPON_TYPE_QUERY_BASE_TRANS          = 21;//交易基本信息的标记
    public static int COUPON_TYPE_PURCHASE                  = 22;//验券的标记
    public static int COUPON_TYPE_QUERY_TRANS_DETAILS_TD    = 23;//验券交易明细的标记---今天
    public static int COUPON_TYPE_QUERY_TRANS_DETAILS_YESTD = 24;//验券交易明细的标记--昨天
    public static int COUPON_TYPE_PURCHASE_TODAY            = 25;//验券交易明细-->今天-->获取小票的广告等信息
    public static int TYPE_COUPON_VALIDATE_APP              = 26;//判断返回的类型,验券的M001接口

    public static int ORDER_TYPE_QUERY_SHOP_NAME                = 27;//查询商铺名称
    public static int COUPON_TYPE_QUERY_BASE_TRANS_HOME         = 28;//载入界面查询设备版本号
    public static int TYPE_COUPON_CHECK_VERSION                 = 29;//版本升级
    public static int ORDER_TYPE_SURE_ORDER                     = 31;//
    public static int ORDER_TYPE_GET_ORDER_ID                   = 32;//打印未处理的订单
    public static int ORDER_TYPE_DETIAL_CLICK_PRINT_ALL         = 33;//打印订单详情里面的订单标记
    public static int ORDER_TYPE_DETIAL_SHOW_TICKS              = 34;//加载订单详情里的所有信息的标记
    public static int ORDER_TYPE_TREATED_PRINT_ALL              = 35;//已处理订单--->全部
    public static int ORDER_TYPE_TREATED_PRINT_BAIDU            = 36;//已处理订单--->全部
    public static int ORDER_TYPE_TREATED_PRINT_MEITUAN          = 37;//已处理订单--->全部
    public static int ORDER_TYPE_TREATED_PRINT_ELEME            = 38;//已处理订单--->全部
    public static int ORDER_TYPE_UNTREATED_PRINT_ALL            = 39;//未处理订单--->全部
    public static int ORDER_TYPE_UNTREATED_PRINT_BAIDU          = 40;//未处理订单--->全部
    public static int ORDER_TYPE_UNTREATED_PRINT_MEITUAN        = 41;//未处理订单--->全部
    public static int ORDER_TYPE_UNTREATED_PRINT_ELEME          = 42;//未处理订单--->全部
    public static int ORDER_TYPE_QUERY_NUM_MONEY                = 43;//查询今日营业额与营业数量的标记
    public static int ORDER_TYPE_AUTO_WAIMAI_SURE               = 44; //完成订单的状态
    public static int ORDER_TYPE_GT_ORDER_UNTREATED_MSG_PAGENUM = 45;//标记分页的字段
    public static int ORDER_TYPE_ACCORDING_ID_TO_PRING          = 46;//打印未处理的订单

    public static int TITLE_ORDER_TYPE_PLATFORM               = 60;//查询平台
    public static int TITLE_ORDER_TYPE_TREATE_UNTREATE_NUM    = 61;//查询已处理个未处理的订单数
    public static int TITLE_ORDER_TYPE_NEAR_ORDER             = 62;//查询最近订单的时间
    public static int TITLE_ORDER_TYPE_NEAR_ORDER_PEOPLE_INFO = 63;//查询最近接单人的信息
    public static int ORDER_TYPE_WAIMAI_IS_SURE               = 64;//判断是否自动接单

    public static int ORDER_TYPE_ALL_UNTREATE_RECEIVE     = 65;//确定接单
    public static int ORDER_TYPE_BAIDU_UNTREATE_RECEIVE   = 66;//确定接单
    public static int ORDER_TYPE_MEITUAN_UNTREATE_RECEIVE = 67;//确定接单
    public static int ORDER_TYPE_ELEME_UNTREATE_RECEIVE   = 68;//确定接单
    public static int ORDER_TYPE_ALL_UNTREATE_REFUSE      = 69;//取消接单--全部
    public static int ORDER_TYPE_BAIDU_UNTREATE_REFUSE    = 70;//取消接单--百度
    public static int ORDER_TYPE_MEITUAN_UNTREATE_REFUSE  = 71;//取消接单--美团
    public static int ORDER_TYPE_ELEME_UNTREATE_REFUSE    = 72;//取消接单--饿了么


    public static int OPEN_ORDER_AUTO                   = 73;//开启自动接单
    public static int CLOSE_ORDER_AUTO                  = 74;//关闭自动接单
    public static int ORDER_TYPE_CANCLE_REFUSE_SUCCESS  = 79;//取消接单成功
    public static int ORDER_TYPE_TREATE_DETIAL          = 80;//订单详情里面的拒绝接单
    public static int ORDER_TYPE_CANCLE_SUCCESS         = 81;//取消接单成功
    public static int FLASH_UI_TYPE_COUPON_TD           = 51;//退出fragment的标记
    public static int ORDER_TYPE_BAIDU_CANCLE_SUCCESS   = 52;//百度取消接单的标记
    public static int ORDER_TYPE_MEITUAN_CANCLE_SUCCESS = 53;//百度取消接单的标记
    public static int ORDER_TYPE_ELEME_CANCLE_SUCCESS   = 54;//百度取消接单的标记
    public static int ORDER_TYPE_WECHAT_CANCLE_SUCCESS  = 55;//百度取消接单的标记
    public static int TYPE_LOADING                      = 100;//登录的标记
    public static int ORDER_TYPE_OPEN_INIT_WM           = 101;//初始化登录外卖
    public static int EXIT_LOADING_HOME                 = 102;//退出登录
    public static int EXIT_LOADING_WAIMAI               = 103;//退出外卖业务
    ///***************************哪一天的订单***************************/
    public static int DAY_FLAG_ALL                      = 0;//默认是今天
    public static int DAY_FLAG_BAIDU                    = 0;//当前是哪一天的状态
    public static int DAY_FLAG_ELEME                    = 0;//当前是哪一天的状态
    public static int DAY_FLAG_MEITUAN                  = 0;//当前是哪一天的状态

    ///*****************************************************接单的状态************************************************/
    public static int ORDER_DETIAL_STATE = -1;//订单状态
    ///************************记住splash界面的状态***************************/
    //    public static boolean LOGIN_COUNT        = false;

    ///*************************************************下面是根据登录返回的字段transMap
    // 来获取的功能开关****************************/
    public static String COUPON; //外卖
    public static String UNIONPAY; //银联支付
    public static String EASYPAY;//支付宝或微信支付功能
    public static String RECOMMED; //推荐有礼功能
    public static String INTEGRA; //交行信用卡积分功能
    public static String WAIMAI="1"; //外卖功能

    ///*************************************************判断当前的订单状态*******************************/
    public static       int    ORDER_STATUS_CANCEL       = 89;//取消订单
    public static       int    ORDER_STATUS_ADVANCE      = 21;//预定订单
    public static       int    ORDER_STATUS_ALL          = 101;//全部已处理的订单
    public static final int    Refuse_order              = 8;//拒绝接单
    public static final int    Cancle_order              = 9;//取消接单
    public static final int    unTreate_order            = -1;//待确认订单---未处理订单
    ///*************************密钥****************************/
    public static final String desAndMd5Decoder_padding  = "DES/CBC/PKCS5Padding";
    public static final String desAndMd5Decoder_macSeret = "boyasafe";
    public static final String desAndMd5Decoder_seret    = "boyasafe";

    ///*******************************下载的文件路劲*********************************/
    public static String SD_CACHE_URL = "umq";


    //    public static final String channelId_platform_baidu   ="189021000009002";//
    //    public static final String channelId_platform_meituan ="189021000009003";//
    //    public static final String channelId_platform_eleme   ="189021000009004";//
    //    public static final String channeId_platfprm_weiChat ="189021000009005";//
    //    public static final String channeId_platfprm_other   ="189021000009006";//
}
