package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/31 13:22
 * @describe 用来处理没有返回体的json
 */

public class HeaderBeanIn {
    /**
     * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
     * method : unionlive.dish.update.stock
     * sessionId :
     * submitTime : 20161231131802
     * transType : T421
     * version : 1.0
     * termSn :
     */

    public String clientTraceNo;
    public String method;
    public String sessionId;
    public String submitTime;
    public String transType;
    public String version;
    public String termSn;

    public HeaderBeanIn(String clientTraceNo, String method, String sessionId,
                      String submitTime, String transType, String version, String termSn) {
        this.clientTraceNo = clientTraceNo;
        this.method = method;
        this.sessionId = sessionId;
        this.submitTime = submitTime;
        this.transType = transType;
        this.version = version;
        this.termSn = termSn;
    }

    @Override
    public String toString() {
        return "{" +
                "clientTraceNo:'" + clientTraceNo + '\'' +
                ", method:'" + method + '\'' +
                ", sessionId:'" + sessionId + '\'' +
                ", submitTime:'" + submitTime + '\'' +
                ", transType:'" + transType + '\'' +
                ", version:'" + version + '\'' +
                ", termSn:'" + termSn + '\'' +
                '}';
    }
}
