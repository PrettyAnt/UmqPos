package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/16 18:31
 * @describe ${TODO}
 */
public class SpalishStartEvent {
    private  boolean isInitEvent;//如果是true，则进入下一个界面，如果是false的话，则调用baseActivity的退出方法，退出程序

    public SpalishStartEvent(boolean isInitEvent) {
        this.isInitEvent = isInitEvent;
    }

    public boolean isInitEvent() {
        return isInitEvent;
    }
}
