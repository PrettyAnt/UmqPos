package unionlive.com.umqpos.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import unionlive.com.umqpos.R;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/10 18:15
 * @describe ${TODO}
 */

public class SoundPlayUtils {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    public static SoundPlayUtils soundPlayUtils;
    // 上下文
    static        Context        mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }

        // 初始化声音
        mContext = context;

        mSoundPlayer.load(mContext, R.raw.open_auto, 1);// 1------->开启自动接单
        mSoundPlayer.load(mContext, R.raw.close_auto, 1);// 2----关闭自动接单
        mSoundPlayer.load(mContext, R.raw.new_order_msg, 1);// 3---->新订单来了
        mSoundPlayer.load(mContext, R.raw.print_tickes, 1);// 4-------->打印小票
        mSoundPlayer.load(mContext, R.raw.wel_umq, 1);// 5---------->欢迎进入优麦圈
        mSoundPlayer.load(mContext, R.raw.net_error, 1);// 6---------->网络异常

        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }

}
