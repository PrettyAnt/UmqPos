package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 17:21
 * @describe 商户退出登录,记录退出状态日志
 */

public class ExitLogin {

    /**
     * header : {"version":"1.0","transType":"T108","submitTime":"20161208172233","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.exitlogin"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb"}
     */

    public HeaderBean header;
    public BodyBean body;

    public ExitLogin(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T108
         * submitTime : 20161208172233
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.exitlogin
         */

        public String version;//报文版本号
        public String transType; //交易类型
        public String submitTime;//交易时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;    //后台处理时间
        public String termSn;

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo, String method,
                          String termSn) {
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

    public static class BodyBean {
        /**
         * loginSessionId : a393e99a-bc87-49e2-b99a-35318cff88eb
         */

        public String loginSessionId;//登录会话编号

        public BodyBean(String loginSessionId) {
            this.loginSessionId = loginSessionId;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
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
