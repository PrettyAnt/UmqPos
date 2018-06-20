package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 13:55
 * @describe 客户端应用信息查询上送报文的实体类
 */

public class QueryAppInfo {

    /**
     * header : {"version":"1.0","transType":"M004","submitTime":"20161208135455","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.system.queryappinfo"}
     * body : {"appId":"182000899000001","htmlType":"1"}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryAppInfo(HeaderBean header, BodyBean body) {
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
         * transType : M004
         * submitTime : 20161208135455
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.system.queryappinfo
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
        public BodyBean(String appId, String htmlType) {
            this.appId = appId;
            this.htmlType = htmlType;
        }

        /**
         * appId : 182000899000001
         * htmlType : 1
         */

        public String appId;//应用编号
        public String htmlType;//富文本类型

        @Override
        public String toString() {
            return "{" +
                    "appId:'" + appId + '\'' +
                    ", htmlType:'" + htmlType + '\'' +
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
