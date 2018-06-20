package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/30 13:21
 * @describe 用来判断当前订单的状态
 */
public class OrderStatuEvent {
    public int getStatus() {
        return status;
    }

    private int status;

    public OrderStatuEvent(int status) {
        this.status=status;
    }
}
