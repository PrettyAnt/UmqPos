package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 16:33
 * @describe 投诉建议
 */

public class ShopComplain {

    /**
     * header : {"version":"1.0","transType":"T105","submitTime":"20161208162756","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.shopcomplain"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb","complainMsg":"投诉建议..."}
     */

    public HeaderBean header;
    public BodyBean body;

    public ShopComplain(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T105
         * submitTime : 20161208162756
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.shopcomplain
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo, String method,String termSn) {
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
                    '}';
        }
    }

    public static class BodyBean {
        /**
         * loginSessionId : a393e99a-bc87-49e2-b99a-35318cff88eb
         * complainMsg : 投诉建议...
         */

        public String loginSessionId;
        public String complainMsg;

        public BodyBean(String loginSessionId, String complainMsg) {
            this.loginSessionId = loginSessionId;
            this.complainMsg = complainMsg;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", complainMsg:'" + complainMsg + '\'' +
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
