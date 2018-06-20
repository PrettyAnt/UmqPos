package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 14:20
 * @describe 商户登录上送的报文
 */

public class Login {

    /**
     * header : {"version":"1.0","transType":"T100","submitTime":"20161208142050","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.login"}
     * body : {"shopId":"182000899000001","shopOperId":"002","password":"123456"}
     */

    public HeaderBean header;
    public BodyBean body;

    public Login(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
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

        /**
         * version : 1.0
         * transType : T100
         * submitTime : 20161208142050
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.login
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名

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
        public BodyBean(String shopId, String shopOperId, String password) {
            this.shopId = shopId;
            this.shopOperId = shopOperId;
            this.password = password;
        }

        /**
         * shopId : 182000899000001
         * shopOperId : 002
         * password : 123456
         */

        public String shopId;//门店编号
        public String shopOperId;//门店操作员编号
        public String password;//操作员密码

        @Override
        public String toString() {
            return "{" +
                    "shopId:'" + shopId + '\'' +
                    ", shopOperId:'" + shopOperId + '\'' +
                    ", password:'" + password + '\'' +
                    '}';
        }
    }
}
