package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 19:05
 * @describe 取消订单 拒绝渠道方退过来的新订单
 */

public class OrderCancel {
    /**
     * header : {"version":"1.0","transType":"T302","submitTime":"20161208190549","sessionId":"","clientTraceNo":"","method":"unionlive.order.cancel"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb","orderId":"","cancelType":"2","cancelReason":"不开心，不接单"}
     */

    public HeaderBean header;
    public BodyBean body;

    public OrderCancel(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        private  String termSn;
        /**
         * version : 1.0
         * transType : T302
         * submitTime : 20161208190549
         * sessionId :
         * clientTraceNo :
         * method : unionlive.order.cancel
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名

        public HeaderBean(String version, String transType,
                          String submitTime,
                          String sessionId, String clientTraceNo,
                          String method, String termSn) {
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
         * orderId :
         * cancelType : 2
         * cancelReason : 不开心，不接单
         */

        public String loginSessionId;//登录会话编号
        public String orderId;//订单编号
        public int status;//订单状态
        public int cancelType;//取消原因类型
        public String cancelReason;//自定义取消原因

        public BodyBean(String loginSessionId, String orderId,
                        int status, int cancelType,
                        String cancelReason) {
            this.loginSessionId = loginSessionId;
            this.orderId = orderId;
            this.status = status;
            this.cancelType = cancelType;
            this.cancelReason = cancelReason;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", orderId:'" + orderId + '\'' +
                    ", status:" + status +
                    ", cancelType:" + cancelType +
                    ", cancelReason:'" + cancelReason + '\'' +
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
