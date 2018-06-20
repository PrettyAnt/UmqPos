package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 14:31
 * @describe 商铺基本信息查询
 */

public class ShopInfo {

    /**
     * header : {"version":"1.0","transType":"T101","submitTime":"20161208143024","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.queryshopinfo"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb"}
     */

    public HeaderBean header;
    public BodyBean body;

    public ShopInfo(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo, String method, String termSn) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
            this.termSn = termSn;
        }

        /**
         * version : 1.0
         * transType : T101
         * submitTime : 20161208143024
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.queryshopinfo
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//方法名

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
        public BodyBean(String loginSessionId) {
            this.loginSessionId = loginSessionId;
        }

        /**
         * loginSessionId : a393e99a-bc87-49e2-b99a-35318cff88eb
         */

        public String loginSessionId;//登录会话编号

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
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
