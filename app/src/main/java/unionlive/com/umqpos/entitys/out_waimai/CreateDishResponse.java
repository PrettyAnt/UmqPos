package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 10:19
 * @describe T410-新增菜品返回报文
 */

public class CreateDishResponse {

    /**
     * header : {"version":"1.0","transType":"T410","submitTime":"20161209100951","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","hostTime":"20161209101005","hostTraceNo":"327369","returnCode":"0000","returnMessage":"交易成功"}
     * body : {"foodId":"10130"}
     */

    public HeaderBean header;
    public BodyBean body;

    public CreateDishResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T410
         * submitTime : 20161209100951
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * hostTime : 20161209101005
         * hostTraceNo : 327369
         * returnCode : 0000
         * returnMessage : 交易成功
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
        /**
         * foodId : 10130
         */

        public String foodId;//菜品ID

        public BodyBean(String foodId) {
            this.foodId = foodId;
        }

        @Override
        public String toString() {
            return "{" +
                    "foodId:'" + foodId + '\'' +
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
