package unionlive.com.umqpos.bean;

import unionlive.com.umqpos.content.Fields;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/11 14:49
 * @describe $状态栏的信息
 */

public class TitleInfomation {
    public static String shopId;
    public static int     title_flag_treate = 0;
    public static void initTitleData() {
        TitleInfomation.baiduBusinessStatus = 0;
        TitleInfomation.channelName_platform_baidu= "";
        TitleInfomation.channelName_platform_meituan = "";
        TitleInfomation.channelName_platform_eleme = "";
        TitleInfomation.channelName_platform_wechat = "";
        TitleInfomation.treate = 0;
        TitleInfomation.untreate = 0;
        TitleInfomation.nearorder = "--";
        TitleInfomation.personinfo = "--";//
        Fields.is_Open_WaiMai_Flag = false;//外卖业务关闭
    }

    public static String channelId_platform_baidu;//商户开通的外卖平台的Id--百度
    public static String channelId_platform_meituan;//美团
    public static String channelId_platform_eleme;//饿了么
    public static String channelId_platform_wechat;//微信

    public static String channelName_platform_baidu;//商户开通的外卖平台的名称--百度
    public static String channelName_platform_meituan;//美团
    public static String channelName_platform_eleme;//饿了么
    public static String channelName_platform_wechat;//微信

    public static boolean baiduIsOpen   = false;//门店是否开通百度外卖
    public static boolean meituanIsOpen = false;//门店是否开通美团外卖
    public static boolean elemeIsOpen   = false;//门店是否开通饿了么外卖
    public static boolean wechatIsOpen  = false;//门店是否开通饿了么外卖

    public static int treate;//已处理
    public static int untreate;//未处理
    public static String nearorder             = "--";//最近订单时间
    public static String personinfo            = "--";//接单人的信息
    //1----停止营业
    public static int    baiduBusinessStatus   = -1;//平台是否营业的状态---默认状态  // FIXME: 2017/1/23
    public static int    meituanBusinessStatus = -1;//美团平台是否营业的状态---默认状态
    public static int    elemeBusinessStatus   = -1;//饿了么平台是否营业的状态---默认状态
    public static int wechatBusinessStatus = -1;//饿了么平台是否营业的状态---默认状态
    //1---关闭接单
    public static int allAutoOrder         = -1;//接单的状态---默认状态-----总状态
    public static int baiduAutoOrder       = -1;//百度外卖的自动接单状态
    public static int meituanAutoOrder     = -1;//美团外卖的自动接单的状态
    public static int elemeAutoOrder       = -1;//饿了么外卖的自动接单状态
    public static int wechatAutoOrder      = -1;//微信外卖的自动接单的状态
    public static int printStatus          = 0;//打印机状态 0：正常(包括繁忙的情况)  1：不正常
    public static int netStatus            = 0;//网络状态0:正常，1，离线
    public static int dealOrderState       = 0;//当前处理订单的状态 0：空闲  1：正在处理订单


}
