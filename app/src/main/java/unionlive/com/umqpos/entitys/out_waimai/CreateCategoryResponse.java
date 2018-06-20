package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/9 9:43
 * @describe 新增菜品分类的返回报文
 */

public class CreateCategoryResponse {

    /**
     * header : {"version":"1.0","transType":"T321","submitTime":"20161209091712","sessionId":"","clientTraceNo":"","hostTime":"20161209091726","hostTraceNo":"326615","returnCode":"6016","returnMessage":"Eleme WMAPI ErrorMsg path:/restaurant/binding/  errno:1004  error:tp_restaurant_id 182000899000001 has existed"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public CreateCategoryResponse(HeaderBean header, BodyBean body) {
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

        public String version;//报文版本号
        public String transType; //交易类型
        public String submitTime;//交易时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String hostTime;    //后台处理时间
        public String hostTraceNo;    //后台交易流水号
        public String returnCode;//返回码
        public String returnMessage;//返回码描述

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
        public int cateId;//分类ID

        public BodyBean(int cateId) {
            this.cateId = cateId;
        }

        @Override
        public String toString() {
            return "{" +
                    "cateId:" + cateId +
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
