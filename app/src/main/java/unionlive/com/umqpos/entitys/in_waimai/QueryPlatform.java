package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 17:01
 * @describe 商户外卖平台查询
 */

public class QueryPlatform {

    /**
     * header : {"version":"1.0","transType":"T106","submitTime":"20161208170202","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.queryplatform"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb"}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryPlatform(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T106
         * submitTime : 20161208170202
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.queryplatform
         */

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
