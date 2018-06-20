package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 18:37
 * @describe  订单数量查询
 */

public class QueryOrderNum {
    /**
     * header : {"version":"1.0","transType":"T204","submitTime":"20161208183757","sessionId":"","clientTraceNo":"","method":"unionlive.order.process.number"}
     * body : {"loginSessionId":"2cdd575a-ff7f-4be4-b038-012dec19e5a5"}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryOrderNum(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T204
         * submitTime : 20161208183757
         * sessionId :
         * clientTraceNo :
         * method : unionlive.order.process.number
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//方法名

        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String clientTraceNo,
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
         * loginSessionId : 2cdd575a-ff7f-4be4-b038-012dec19e5a5
         */

        public String loginSessionId;//登录会话编号
        public String orderDate;//订单日期

        public BodyBean(String loginSessionId, String orderDate) {
            this.loginSessionId = loginSessionId;
            this.orderDate = orderDate;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", orderDate:'" + orderDate + '\'' +
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
