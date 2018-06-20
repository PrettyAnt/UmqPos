package unionlive.com.umqpos.utils;

import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import unionlive.com.umqpos.BuildConfig;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.event.InitUiEvent;
import unionlive.com.umqpos.net.NetUtil;

import static unionlive.com.umqpos.utils.DevicesInfoUtil.getTermSn;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/2/9 10:49
 * @describe ${TODO}
 */

public class TimerTaskHelper extends TimerTask {
    private     Context context;
    public static int count = 0;//计数器-心跳包
    public static Timer   timer;
    public static long executeTime=0;//任务开启后多少秒之后执行
    public static long periodTime=30000l;//执行的间隔
    HashMap<String, String> params = new HashMap<>();
    public static Timer getTimer(){
        if (timer==null) {
            timer = new Timer();
        }
        return timer;
    }

    public TimerTaskHelper(Context context) {
        this.context = context;
    }


    @Override
    public void run() {
        params.clear();
        String shopId = (String) SPUtils.get(context, Fields.shopId, "");
        params.put("submitTime", TimeHelper.getSubmitTime());
        params.put("shopId", shopId);
        params.put("termSn", getTermSn());
        params.put("bitmap", getBitMap());
        try {
//            Date date = new Date(this.scheduledExecutionTime());
            NetUtil.checkDevice(params);//请求网络，检测
            if (count >= 4) {
                SoundPlayUtils.play(6);
                EventBus.getDefault().postSticky(new InitUiEvent(1));
            }
            if (count == 0) {
                EventBus.getDefault().postSticky(new InitUiEvent(2));//恢复正常
            }
            count++;
            System.out.println("本次任务的执行时间为:" + "---"+ count);
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                Log.d("TimerTaskHelper", "e:" + e);
        }

    }

    /**
     * 得到位图
     * @return
     */
    private String getBitMap() {
        StringBuffer buffer = new StringBuffer();
        String bitMap = buffer
                .append(TitleInfomation.printStatus)
                .toString();
        return bitMap;
    }

    @Override
    public boolean cancel() {
        return super.cancel();
    }

}
