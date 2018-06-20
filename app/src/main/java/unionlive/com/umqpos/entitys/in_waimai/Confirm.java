package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 18:57
 * @describe 店铺确定接单
 */

public class Confirm {
    /**
     * header : {"version":"1.0","transType":"T301","submitTime":"20161208185627","sessionId":"","clientTraceNo":"","method":"unionlive.order.confirm"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb","orderId":"1467887261030"}
     */

    public HeaderBean header;
    public BodyBean body;

    public Confirm(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T301
         * submitTime : 20161208185627
         * sessionId :
         * clientTraceNo :
         * method : unionlive.order.confirm
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//方法名

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
        public int autoOrder;//自动接单标记

        public BodyBean(String loginSessionId, String orderId, int autoOrder) {
            this.loginSessionId = loginSessionId;
            this.orderId = orderId;
            this.autoOrder = autoOrder;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", orderId:'" + orderId + '\'' +
                    ", allAutoOrder:" + autoOrder +
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
