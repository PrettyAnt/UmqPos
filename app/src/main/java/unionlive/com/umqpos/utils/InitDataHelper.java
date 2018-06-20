package unionlive.com.umqpos.utils;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.OpenWmResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryPlatformResponse;
import unionlive.com.umqpos.event.NewOrderComing;
import unionlive.com.umqpos.net.InPutJsonData;
import unionlive.com.umqpos.net.NetUtil;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/11 20:07
 * @describe $初始化标题、刷新外卖界面、上传错误日志
 */

public class InitDataHelper {
    /**
     * 查询开通了哪些外卖平台
     */
    public static void getTitleMsg(Context context) {
        //外卖平台的报文   商户外卖平台查询
        String t106 = InPutJsonData.T106(context, Fields.loginsessionId);
        NetUtil.GTNetWorkUtil(t106, Fields.channelId,
                QueryPlatformResponse.class, context, Fields.TITLE_ORDER_TYPE_PLATFORM);//查询有哪些外卖平台

    }

    /**
     * 刷新界面
     * @param context
     */
    public static void flush(Context context) {
        EventBus.getDefault().postSticky(new NewOrderComing(true));
    }

    /**
     * 上传错误日志
     * @param context
     * @param error
     */
    public static void sendError(Context context, String error){
        //body为空，直接进入程序
        String m003 = InPutJsonData.M003(context,"当前的界面为:"+context+"\n当前错误为:"+error);
        NetUtil.GTNetWorkUtil(m003,Fields.channelId, OpenWmResponse.class,context,-1);
    }
}
