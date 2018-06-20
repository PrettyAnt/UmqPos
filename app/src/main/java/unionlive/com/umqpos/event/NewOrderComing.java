package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/7 17:26
 * @describe ${TODO}
 */
public class NewOrderComing {
    private boolean isNewGtOrder;

    public NewOrderComing(boolean isNewGtOrder) {
        this.isNewGtOrder = isNewGtOrder;
    }

    public boolean isNewGtOrder() {
        return isNewGtOrder;
    }
}
