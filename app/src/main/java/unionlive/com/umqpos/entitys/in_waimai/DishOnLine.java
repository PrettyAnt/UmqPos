package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/1/2 16:44
 * @describe ${TODO}
 */

public class DishOnLine {
    /**
     * body : {"channelId":"","foodId":"10126","loginSessionId":"","shopId":"182000899000001","sizeId":"","status":"0"}
     * header : {"clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.food.online","sessionId":"","submitTime":"20170102164049","termSn":"termSn","transType":"T424","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public static class BodyBean {
        /**
         * channelId :
         * foodId : 10126
         * loginSessionId :
         * shopId : 182000899000001
         * sizeId :
         * status : 0
         */

        public String channelId;
        public String foodId;
        public String loginSessionId;
        public String shopId;
        public String sizeId;
        public String status;

        public BodyBean(String channelId, String foodId,
                        String loginSessionId, String shopId,
                        String sizeId, String status) {
            this.channelId = channelId;
            this.foodId = foodId;
            this.loginSessionId = loginSessionId;
            this.shopId = shopId;
            this.sizeId = sizeId;
            this.status = status;
        }

        @Override
        public String toString() {
            return "{" +
                    "channelId:'" + channelId + '\'' +
                    ", foodId:'" + foodId + '\'' +
                    ", loginSessionId:'" + loginSessionId + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    ", sizeId:'" + sizeId + '\'' +
                    ", status:'" + status + '\'' +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.food.online
         * sessionId :
         * submitTime : 20170102164049
         * termSn : termSn
         * transType : T424
         * version : 1.0
         */

        public String version;
        public String transType;
        public String submitTime;
        public String sessionId;
        public String clientTraceNo;
        public String method;
        public String termSn;

        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String clientTraceNo, String method, String termSn) {
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

    public DishOnLine(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    @Override
    public String toString() {
        return "{" +
                "body:" + body +
                ", header:" + header +
                '}';
    }
}
