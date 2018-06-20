package unionlive.com.umqpos.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import unionlive.com.umqpos.event.CoubonsDecoderEvent;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/20 19:31
 * @describe ${TODO}
 */

public class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
