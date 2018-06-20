package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/9 9:20
 * @describe 绑定饿了么餐厅ID 饿了么专用
 */

public class BindingResponse {
    /**
     * header : {"version":"1.0","transType":"T321","submitTime":"20161209091712","sessionId":"","clientTraceNo":"","hostTime":"20161209091726","hostTraceNo":"326615","returnCode":"6016","returnMessage":"Eleme WMAPI ErrorMsg path:/restaurant/binding/  errno:1004  error:tp_restaurant_id 182000899000001 has existed"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public BindingResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T321
         * submitTime : 20161209091712
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161209091726
         * hostTraceNo : 326615
         * returnCode : 6016
         * returnMessage : Eleme WMAPI ErrorMsg path:/restaurant/binding/  errno:1004  error:tp_restaurant_id 182000899000001 has existed
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
                          String clientTraceNo, String hostTime,
                          String hostTraceNo,
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
