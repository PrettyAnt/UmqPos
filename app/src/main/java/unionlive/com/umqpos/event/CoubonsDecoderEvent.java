package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/7 23:19
 * @describe $处理验券的接口
 */

public class CoubonsDecoderEvent<T> {

    private int orderType;
    private T t;
    public CoubonsDecoderEvent(T t,int orderType) {
        this.t = t;
        this.orderType = orderType;
    }

    public T getT() {
        return t;
    }

    public int getOrderType() {
        return orderType;
    }
}
