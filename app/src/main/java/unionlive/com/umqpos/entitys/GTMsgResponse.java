package unionlive.com.umqpos.entitys;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/5 13:39
 * @describe $这是服务端返回过来的json数据
 */

public class GTMsgResponse {
    public String submitTime;//提交时间
    public String shopId;//门店Id
    public String msgType;//消息类型
    public String orderId;//订单编号
    public String termSn;//SN号

    public GTMsgResponse(String submitTime, String shopId,
                         String msgType, String orderId,
                         String termSn) {
        this.submitTime = submitTime;
        this.shopId = shopId;
        this.msgType = msgType;
        this.orderId = orderId;
        this.termSn = termSn;
    }

    @Override
    public String toString() {
        return "{" +
                "submitTime:'" + submitTime + '\'' +
                ", shopId:'" + shopId + '\'' +
                ", msgType:'" + msgType + '\'' +
                ", orderId:'" + orderId + '\'' +
                ", termSn:'" + termSn + '\'' +
                '}';
    }
}
