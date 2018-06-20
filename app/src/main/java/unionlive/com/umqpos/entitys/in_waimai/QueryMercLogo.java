package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 17:11
 * @describe 商户名称与LOGO查询
 */

public class QueryMercLogo {

    /**
     * header : {"version":"1.0","transType":"T107","submitTime":"20161208171034","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.querymerclogo"}
     * body : {"appId":"01806661"}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryMercLogo(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T107
         * submitTime : 20161208171034
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.querymerclogo
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
         * appId : 01806661
         */

        public String appId;//应用ID	 	同M001接口的appid

        public BodyBean(String appId) {
            this.appId = appId;
        }

        @Override
        public String toString() {
            return "{" +
                    "appId:'" + appId + '\'' +
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
