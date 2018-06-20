package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 17:48
 * @describe 订单详情查询
 */

public class Detial {
    /**
     * header : {"version":"1.0","transType":"T202","submitTime":"20161208174959","sessionId":"","clientTraceNo":"","method":"unionlive.order.detail"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb","orderId":"1467887261030"}
     */

    public HeaderBean header;
    public BodyBean body;

    public Detial(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T202
         * submitTime : 20161208174959
         * sessionId :
         * clientTraceNo :
         * method : unionlive.order.detail
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//设备号

        public HeaderBean(String version, String transType,
                          String submitTime,
                          String sessionId,
                          String clientTraceNo,
                          String method,String termSn) {
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
