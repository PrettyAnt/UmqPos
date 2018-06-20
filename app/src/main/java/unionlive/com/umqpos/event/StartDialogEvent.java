package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/2/15 17:29
 * @describe ${TODO}
 */
public class StartDialogEvent {

    private  int type;

    public int getType() {
        return type;
    }

    public StartDialogEvent(int type) {
        this.type = type;
    }
}
