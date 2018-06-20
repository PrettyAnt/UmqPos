package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 19:38
 * @describe 商户关闭自动接单的返回报文
 */

public class CloseOrderAutoResponse {
    /**
     * header : {"version":"1.0","transType":"T303","submitTime":"20161208192315","sessionId":"","clientTraceNo":"","hostTime":"20161208192334","hostTraceNo":"326154","returnCode":"6011","returnMessage":"channelId 参数不能为空"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public CloseOrderAutoResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T303
         * submitTime : 20161208192315
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161208192334
         * hostTraceNo : 326154
         * returnCode : 6011
         * returnMessage : channelId 参数不能为空
         */

        public String version;
        public String transType;
        public String submitTime;
        public String sessionId;
        public String clientTraceNo;
        public String hostTime;
        public String hostTraceNo;
        public String returnCode;
        public String returnMessage;

        public HeaderBean(String version, String transType,
                          String submitTime,
                          String sessionId,
                          String clientTraceNo, String hostTime,
                          String hostTraceNo, String returnCode,
                          String returnMessage) {
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
        @Override
        public String toString() {
            return "{}";
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
