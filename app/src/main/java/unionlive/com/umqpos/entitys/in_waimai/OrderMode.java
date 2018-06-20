package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 9:07
 * @describe $跟新餐厅接单模式 饿了吗专用
 */

public class OrderMode {
    /**
     * header : {"version":"1.0","transType":"T320","submitTime":"20161209091026","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.order.mode"}
     * body : {"channelId":"1003","mercId":"182000899000001","mode":2}
     */

    public HeaderBean header;
    public BodyBean body;

    public OrderMode(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T320
         * submitTime : 20161209091026
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.order.mode
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
         * channelId : 1003
         * mercId : 182000899000001
         * mode : 2
         */

        public String channelId;//外卖渠道编号
        public String mercId;//商户ID
        public int    mode;//饿了么ID

        public BodyBean(String channelId, String mercId, int mode) {
            this.channelId = channelId;
            this.mercId = mercId;
            this.mode = mode;
        }

        @Override
        public String toString() {
            return "{" +
                    "channelId:'" + channelId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", mode:" + mode +
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
