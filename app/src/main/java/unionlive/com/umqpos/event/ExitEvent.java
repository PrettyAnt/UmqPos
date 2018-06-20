package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/13 17:24
 * @describe ${TODO}
 */
public class ExitEvent {
    public ExitEvent(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    private boolean isExit;
}
