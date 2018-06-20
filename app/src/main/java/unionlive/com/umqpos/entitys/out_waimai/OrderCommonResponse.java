package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/2 16:30
 * @describe ${TODO}
 */

public class OrderCommonResponse {
    /**
     * body : {}
     * header : {"clientTraceNo":"20170102162725_63659018787055412539","hostTime":"20170102162725","hostTraceNo":"750546","returnCode":"0000","returnMessage":"交易成功","sessionId":"ae861065-a99e-4600-9a38-6eff9421fb98","submitTime":"20170102162725","transType":"T421","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public static class BodyBean {
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20170102162725_63659018787055412539
         * hostTime : 20170102162725
         * hostTraceNo : 750546
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : ae861065-a99e-4600-9a38-6eff9421fb98
         * submitTime : 20170102162725
         * transType : T421
         * version : 1.0
         */

        public String clientTraceNo;
        public String hostTime;
        public String hostTraceNo;
        public String returnCode;
        public String returnMessage;
        public String sessionId;
        public String submitTime;
        public String transType;
        public String version;

        public HeaderBean(String clientTraceNo, String hostTime, String hostTraceNo, String returnCode, String returnMessage, String sessionId,
                          String submitTime, String transType, String version) {
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
            this.sessionId = sessionId;
            this.submitTime = submitTime;
            this.transType = transType;
            this.version = version;
        }

        @Override
        public String toString() {
            return "{" +
                    "clientTraceNo:'" + clientTraceNo + '\'' +
                    ", hostTime:'" + hostTime + '\'' +
                    ", hostTraceNo:'" + hostTraceNo + '\'' +
                    ", returnCode:'" + returnCode + '\'' +
                    ", returnMessage:'" + returnMessage + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", version:'" + version + '\'' +
                    '}';
        }
    }

    public OrderCommonResponse(BodyBean body, HeaderBean header) {
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
