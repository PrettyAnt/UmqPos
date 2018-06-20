package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/7 23:41
 * @describe 客户端应版本查询响应密文的实体类
 */

public class QueryAppVersionResponse {

    public QueryAppVersionResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    /**
     * header : {"version":"1.0","transType":"T100","submitTime":"20161207234022","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","hostTime":"20161207234037","hostTraceNo":"320114","returnCode":"6014","returnMessage":"无结果"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public static class HeaderBean {
        public HeaderBean(String version, String transType, String submitTime, String sessionId, String clientTraceNo,
                          String hostTime, String hostTraceNo, String returnCode, String returnMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
        }

        /**
         * version : 1.0
         * transType : T100
         * submitTime : 20161207234022
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * hostTime : 20161207234037
         * hostTraceNo : 320114
         * returnCode : 6014
         * returnMessage : 无结果
         */

        public String version;//报文版本号
        public String transType; //交易类型
        public String submitTime;//交易时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String hostTime;    //后台处理时间
        public String hostTraceNo;    //后台交易流水号
        public String returnCode;//返回码
        public String returnMessage;//返回码描述

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", hostTime:'" + hostTime + '\'' +
                    ", hostTraceNo:'" + hostTraceNo + '\'' +
                    ", returnCode:'" + returnCode + '\'' +
                    ", returnMessage:'" + returnMessage + '\'' +
                    '}';
        }
    }

    public static class BodyBean {
        public BodyBean(String latestVersion, String downloadUrl, String forceUpdate, String description) {
            this.latestVersion = latestVersion;
            this.downloadUrl = downloadUrl;
            this.forceUpdate = forceUpdate;
            this.description = description;
        }

        public String latestVersion;
        public String downloadUrl;
        public String forceUpdate;
        public String description;

        @Override
        public String toString() {
            return "{" +
                    "latestVersion:'" + latestVersion + '\'' +
                    ", downloadUrl:'" + downloadUrl + '\'' +
                    ", forceUpdate:'" + forceUpdate + '\'' +
                    ", DESCRIPTION:'" + description + '\'' +
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
