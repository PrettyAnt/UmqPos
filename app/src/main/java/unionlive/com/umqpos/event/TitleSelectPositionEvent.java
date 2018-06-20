package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/25 19:31
 * @describe ${TODO}
 */
public class TitleSelectPositionEvent {
    private int position;

    public TitleSelectPositionEvent(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
