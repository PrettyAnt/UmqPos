package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/26 10:59
 * @describe $T403菜品分类查询
 */

public class QueryCategory {

    /**
     * body : {"loginSessionId":"","shopId":"","cateName":""}
     * header : {"clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.dish.category.query","sessionId":"","submitTime":"20161226100135","transType":"T403","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public QueryCategory(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        /**
         * loginSessionId :
         * shopId :
         * cateName :
         */

        public String loginSessionId;
        public String shopId;
        public String cateName;

        public BodyBean(String loginSessionId, String shopId, String cateName) {
            this.loginSessionId = loginSessionId;
            this.shopId = shopId;
            this.cateName = cateName;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    ", cateName:'" + cateName + '\'' +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.dish.category.query
         * sessionId :
         * submitTime : 20161226100135
         * transType : T403
         * version : 1.0
         */

        public String clientTraceNo;
        public String method;
        public String sessionId;
        public String submitTime;
        public String transType;
        public String version;
        public String termSn;

        public HeaderBean(String clientTraceNo, String method, String sessionId,
                          String submitTime, String transType, String version, String termSn) {
            this.clientTraceNo = clientTraceNo;
            this.method = method;
            this.sessionId = sessionId;
            this.submitTime = submitTime;
            this.transType = transType;
            this.version = version;
            this.termSn = termSn;
        }

        @Override
        public String toString() {
            return "{" +
                    "clientTraceNo:'" + clientTraceNo + '\'' +
                    ", method:'" + method + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", version:'" + version + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "body:" + body +
                ", header:" + header +
                '}';
    }
}
