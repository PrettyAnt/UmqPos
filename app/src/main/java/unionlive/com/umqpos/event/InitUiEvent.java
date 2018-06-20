package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/22 21:46
 * @describe ${TODO}
 */
public class InitUiEvent {

    private int type;//type=0:个推的消息，type=1：离线状态  type=2：网络通讯正常
    public int getType() {
        return type;
    }

    public InitUiEvent(int type) {
        this.type = type;
    }

}
