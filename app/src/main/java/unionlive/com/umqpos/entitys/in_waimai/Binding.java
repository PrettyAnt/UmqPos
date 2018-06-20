package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 9:17
 * @describe 绑定饿了么餐厅Id
 */

public class Binding {
    /**
     * header : {"version":"1.0","transType":"T321","submitTime":"20161209091712","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.binding"}
     * body : {"channelId":"1003","mercId":"182000899000001","restaurantId":"1289545"}
     */

    public HeaderBean header;
    public BodyBean body;

    public Binding(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T321
         * submitTime : 20161209091712
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.binding
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo, String method) {
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
         * restaurantId : 1289545
         */

        public String channelId;//外卖渠道编号
        public String mercId;//商户ID
        public String restaurantId;//饿了么ID

        public BodyBean(String channelId, String mercId, String restaurantId) {
            this.channelId = channelId;
            this.mercId = mercId;
            this.restaurantId = restaurantId;
        }

        @Override
        public String toString() {
            return "{" +
                    "channelId:'" + channelId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", restaurantId:'" + restaurantId + '\'' +
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
