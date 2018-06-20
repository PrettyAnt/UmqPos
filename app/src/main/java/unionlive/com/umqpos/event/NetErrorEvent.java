package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/7 17:06
 * @describe ${TODO}
 */
public class NetErrorEvent {
    public boolean netError;

    public NetErrorEvent(boolean netError) {
        this.netError = netError;
    }

    public boolean isNetError() {
        return netError;
    }
}
