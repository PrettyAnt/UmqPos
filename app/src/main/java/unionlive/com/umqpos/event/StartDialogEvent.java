package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
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
