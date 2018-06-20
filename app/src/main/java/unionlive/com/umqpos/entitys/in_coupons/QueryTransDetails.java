package unionlive.com.umqpos.entitys.in_coupons;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 10:50
 * @describe 验券----查询交易的明细信息
 */

public class QueryTransDetails {

    public HeaderBean header;
    public BodyBean body;

    public QueryTransDetails(HeaderBean header, BodyBean body) {
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

        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String serialNo, String clientTraceNo,
                          String signMessage) {
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
        public String transTime;//原POS交易时间
        public String hostTime;//原主机交易时间
        public String hostTrace;//后台交易流水号
        public String authCode;//原交易授权码

        public BodyBean(String sessionId, String termId, String mercId, String operId,
                        String transTime, String hostTime, String hostTrace,
                        String authCode) {
            this.sessionId = sessionId;
            this.termId = termId;
            this.mercId = mercId;
            this.operId = operId;
            this.transTime = transTime;
            this.hostTime = hostTime;
            this.hostTrace = hostTrace;
            this.authCode = authCode;
        }

        @Override
        public String toString() {
            return "{" +
                    "sessionId:'" + sessionId + '\'' +
                    ", termId:'" + termId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", operId:'" + operId + '\'' +
                    ", transTime:'" + transTime + '\'' +
                    ", hostTime:'" + hostTime + '\'' +
                    ", hostTrace:'" + hostTrace + '\'' +
                    ", authCode:'" + authCode + '\'' +
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
