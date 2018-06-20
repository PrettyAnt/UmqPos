package unionlive.com.umqpos.entitys.in_coupons;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 14:20
 * @describe 商户登录上送的报文-----用的是电子券验券的接口
 */

public class CheckIn {

    /**
     * header : {"version":"1.0","transType":"T100","submitTime":"20161208142050","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.login"}
     * body : {"shopId":"182000899000001","shopOperId":"002","password":"123456"}
     */

    public HeaderBean header;
    public BodyBean body;

    public CheckIn(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String serialNo;//终端硬件序列号
        public String clientTraceNo;//客户端交易流水号
        public String signMessage;//签名数据

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String serialNo, String clientTraceNo,
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
        public String operId;//操作员编号
        public String operPwd;//操作员密码

        public BodyBean(String operId, String operPwd) {
            this.operId = operId;
            this.operPwd = operPwd;
        }

        @Override
        public String toString() {
            return "{" +
                    "operId:'" + operId + '\'' +
                    ", operPwd:'" + operPwd + '\'' +
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
