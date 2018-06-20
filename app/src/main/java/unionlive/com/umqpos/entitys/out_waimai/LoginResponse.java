package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 14:25
 * @describe 商户登录返回的密文
 */

public class LoginResponse {

    /**
     * header : {"version":"1.0","transType":"T100","submitTime":"20161208142050","sessionId":"","clientTraceNo":"","hostTime":"20161208142115","hostTraceNo":"322848","returnCode":"0000","returnMessage":"交易成功"}
     * body : {"loginSessionId":"0b2bb349-0840-40d2-8c7b-a569e5ac8809"}
     */

    public HeaderBean header;
    public BodyBean body;

    public LoginResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
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

        /**
         * version : 1.0
         * transType : T100
         * submitTime : 20161208142050
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161208142115
         * hostTraceNo : 322848
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
        public BodyBean(String loginSessionId) {
            this.loginSessionId = loginSessionId;
        }

        /**
         * loginSessionId : 0b2bb349-0840-40d2-8c7b-a569e5ac8809
         */

        public String loginSessionId;//本次登录会话编号

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
