package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 15:36
 * @describe 商户营业状态变更
 */

public class ShopBusinessStatus {

    /**
     * header : {"version":"1.0","transType":"T103","submitTime":"20161208153521","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.shopbusinessstatus"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb","channelId":"","status":"1"}
     */

    public HeaderBean header;
    public BodyBean body;

    public ShopBusinessStatus(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T103
         * submitTime : 20161208153521
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.shopbusinessstatus
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//设备编号

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
         * channelId :
         * status : 1
         */

        public String loginSessionId;//登录会话编号
        public String channelId;//渠道编号
        public int status;//营业状态

        public BodyBean(String loginSessionId, String channelId, int status) {
            this.loginSessionId = loginSessionId;
            this.channelId = channelId;
            this.status = status;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", channelId:'" + channelId + '\'' +
                    ", status:'" + status + '\'' +
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
