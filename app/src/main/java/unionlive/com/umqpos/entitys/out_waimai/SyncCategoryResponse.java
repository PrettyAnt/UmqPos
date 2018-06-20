package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 10:07
 * @describe 同步菜品分类的返回报文
 */

public class SyncCategoryResponse {
    /**
     * header : {"version":"1.0","transType":"T404","submitTime":"20161209100005","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","hostTime":"20161209100004","hostTraceNo":"327139","returnCode":"0000","returnMessage":"交易成功"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public SyncCategoryResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T404
         * submitTime : 20161209100005
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * hostTime : 20161209100004
         * hostTraceNo : 327139
         * returnCode : 0000
         * returnMessage : 交易成功
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
                          String submitTime, String sessionId,
                          String clientTraceNo,
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
