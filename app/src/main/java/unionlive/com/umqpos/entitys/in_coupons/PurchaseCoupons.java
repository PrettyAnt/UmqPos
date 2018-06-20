package unionlive.com.umqpos.entitys.in_coupons;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 10:50
 * @describe 电子券兑换上送报文
 */

public class PurchaseCoupons {

    public HeaderBean header;
    public BodyBean body;

    public PurchaseCoupons(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {

        public String version;//报文版本号
        public String transType;//报文类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String serialNo;//终端硬件序列号
        public String clientTraceNo;//客户端交易流水号
        public String signMessage;//签名数据

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String serialNo,
                          String clientTraceNo, String signMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.serialNo = serialNo;
            this.clientTraceNo = clientTraceNo;
            this.signMessage = signMessage;
        }

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", serialNo:'" + serialNo + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", signMessage:'" + signMessage + '\'' +
                    '}';
        }
    }

    public static class BodyBean {

        public String sessionId;//签到会话编号
        public String termId;//终端编号
        public String mercId;//门店编号
        public String operId;//操作员编号
        public String couponsNo;//券号
        public int useTimes;//使用次数

        public BodyBean(String sessionId, String termId,
                        String mercId,
                        String operId, String couponsNo,
                        int useTimes) {
            this.sessionId = sessionId;
            this.termId = termId;
            this.mercId = mercId;
            this.operId = operId;
            this.couponsNo = couponsNo;
            this.useTimes = useTimes;
        }

        @Override
        public String toString() {
            return "{" +
                    "sessionId:'" + sessionId + '\'' +
                    ", termId:'" + termId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", operId:'" + operId + '\'' +
                    ", couponsNo:'" + couponsNo + '\'' +
                    ", useTimes:" + useTimes +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "header:" + header +
                ", body:" + body +
                '}';
    }
}
