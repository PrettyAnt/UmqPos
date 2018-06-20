package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 14:02
 * @describe $客户端应用信息查询的返回的密文
 */

public class QueryAppInfoResponse {

    /**
     * header : {"version":"1.0","transType":"M004","submitTime":"20161208135455","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","hostTime":"20161208135519","hostTraceNo":"322675","returnCode":"6010","returnMessage":"appId不存在"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean   body;

    public QueryAppInfoResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String clientTraceNo,
                          String hostTime, String hostTraceNo,
                          String returnCode, String returnMessage) {
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
         * transType : M004
         * submitTime : 20161208135455
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * hostTime : 20161208135519
         * hostTraceNo : 322675
         * returnCode : 6010
         * returnMessage : appId不存在
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
        public String shopName;//商城名称
        public String htmlContent;//富文本内容

        public BodyBean(String shopName, String htmlContent) {
            this.shopName = shopName;
            this.htmlContent = htmlContent;
        }

        @Override
        public String toString() {
            return "{" +
                    "shopName:'" + shopName + '\'' +
                    ", htmlContent:'" + htmlContent + '\'' +
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
