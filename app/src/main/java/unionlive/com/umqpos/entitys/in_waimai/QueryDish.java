package unionlive.com.umqpos.entitys.in_waimai;

public class QueryDish {

    /**
     * header : {"version":"1.0","transType":"T423","submitTime":"20161231133649","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.food.query"}
     * body : {"shopId":"182000899000001","cateId":""}
     */

    public HeaderBean header;
    public BodyBean   body;

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T423
         * submitTime : 20161231133649
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.food.query
         */

        public String version;
        public String transType;
        public String submitTime;
        public String sessionId;
        public String clientTraceNo;
        public String method;
        public String termSn;

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId, String clientTraceNo, String method, String termSn) {
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
        public String loginSessionId;
        public String shopId;
        public String cateId;
        public String pageSize;
        public String pageIndex;

        public BodyBean(String loginSessionId, String shopId, String cateId, String pageSize, String pageIndex) {
            this.loginSessionId = loginSessionId;
            this.shopId = shopId;
            this.cateId = cateId;
            this.pageSize = pageSize;
            this.pageIndex = pageIndex;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    ", cateId:'" + cateId + '\'' +
                    ", pageSize:'" + pageSize + '\'' +
                    ", pageIndex:'" + pageIndex + '\'' +
                    '}';
        }
    }

    public QueryDish(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    @Override
    public String toString() {
        return "{" +
                "header:" + header +
                ", body:" + body +
                '}';
    }
}