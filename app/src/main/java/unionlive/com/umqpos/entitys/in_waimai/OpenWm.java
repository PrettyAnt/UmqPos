package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/4 16:06
 * @describe T109-外卖功能开启接口
 */

public class OpenWm {

    public BodyBean body;
    public HeaderBean header;

    public OpenWm(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {

        public   String termType;
        public String loginSessionId;
        public String clientId;
        public String forcedLogin;

        public BodyBean(String loginSessionId, String clientId, String forcedLogin,String termType) {
            this.loginSessionId = loginSessionId;
            this.clientId = clientId;
            this.forcedLogin = forcedLogin;
            this.termType = termType;
        }

        @Override
        public String toString() {
            return "{" +
                    "termType:'" + termType + '\'' +
                    ", loginSessionId:'" + loginSessionId + '\'' +
                    ", clientId:'" + clientId + '\'' +
                    ", forcedLogin:'" + forcedLogin + '\'' +
                    '}';
        }
    }

    public static class HeaderBean {

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//方法名

        public HeaderBean(String version, String transType, String submitTime, String sessionId,
                          String clientTraceNo, String method, String termSn) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
            this.termSn = termSn;

        }

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", method:'" + method + '\'' +
                    ", termSn:'" + termSn + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "body:" + body +
                ", header:" + header +
                '}';
    }
}
