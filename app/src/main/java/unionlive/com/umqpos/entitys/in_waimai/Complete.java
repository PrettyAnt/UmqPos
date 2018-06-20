package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 18:48
 * @describe 完成接单
 */

public class Complete {
    /**
     * header : {"version":"1.0","transType":"T300","submitTime":"20161208184833","sessionId":"","clientTraceNo":"","method":"unionlive.order.complete"}
     * body : {"loginSessionId":"2cdd575a-ff7f-4be4-b038-012dec19e5a5","orderId":"1467887261030"}
     */

    public HeaderBean header;
    public BodyBean body;

    public Complete(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T300
         * submitTime : 20161208184833
         * sessionId :
         * clientTraceNo :
         * method : unionlive.order.complete
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名

        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String clientTraceNo,
                          String method) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
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
         * loginSessionId : 2cdd575a-ff7f-4be4-b038-012dec19e5a5
         * orderId : 1467887261030
         */

        public String loginSessionId;//登录会话编号
        public String orderId;//订单编号

        public BodyBean(String loginSessionId, String orderId) {
            this.loginSessionId = loginSessionId;
            this.orderId = orderId;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", orderId:'" + orderId + '\'' +
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
