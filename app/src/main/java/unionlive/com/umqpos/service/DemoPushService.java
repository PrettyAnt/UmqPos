package unionlive.com.umqpos.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.igexin.sdk.GTServiceManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unionlive.com.umqpos.event.CoubonsDecoderEvent;

/**
 * 核心服务, 继承 android.app.Service, 必须实现以下几个接口, 并在 AndroidManifest 声明该服务并配置成
 * android:process=":pushservice", 具体参考 {}
 * PushManager.getInstance().initialize(this.getApplicationContext(), userPushService), 其中
 * userPushService 为 用户自定义服务 即 DemoPushService.
 */
@SuppressWarnings("WrongConstant")
public class DemoPushService extends Service {

    public static final String TAG = DemoPushService.class.getName();

    @Override
    public void onCreate() {
        // 该行日志在 release 版本去掉
        Log.d(TAG, TAG + " call -> onCreate -------");

        super.onCreate();
        EventBus.getDefault().register(this);
        GTServiceManager.getInstance().onCreate(this);
//        initTitleData();
    }

    private void initData() {
//        String queryPlatform = InPutJsonData.T106(this, loginsessionId);
//        NetUtil.waiMaiNetWorkUtil(queryPlatform, Fields.channelId,
//                QueryPlatformResponse.class, this, Fields.TITLE_ORDER_TYPE_PLATFORM);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 该行日志在 release 版本去掉
        Log.d(TAG, TAG + " call -> onStartCommand -------");

        super.onStartCommand(intent, flags, startId);
        return GTServiceManager.getInstance().onStartCommand(this, intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 该行日志在 release 版本去掉
        Log.d(TAG, "onBind -------");
        return GTServiceManager.getInstance().onBind(intent);
    }

    @Override
    public void onDestroy() {
        // 该行日志在 release 版本去掉
        Log.d(TAG, "onDestroy -------");

        super.onDestroy();
        EventBus.getDefault().unregister(this);
        GTServiceManager.getInstance().onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        GTServiceManager.getInstance().onLowMemory();
    }
    /**
     * 以下4个方法是EventBus需要配置的方法
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(CoubonsDecoderEvent event) {
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(CoubonsDecoderEvent event) {
    }
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(CoubonsDecoderEvent event) {
    }
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(CoubonsDecoderEvent event) {
    }

}
