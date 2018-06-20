package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 17:14
 * @describe 商户名称与LOGO查询返回报文
 */

public class QueryMercLogoResponse {

    /**
     * header : {"version":"1.0","transType":"T107","submitTime":"20161208171034","sessionId":"","clientTraceNo":"","hostTime":"20161208171659","hostTraceNo":"324648","returnCode":"0000","returnMessage":"交易成功"}
     * body : {"appId":"01806661","mercName":"宜芝多（延长路店）","mercLogo":"https://img.waimai.baidu.com/pb/36217f2d092346a87131479e5a93533d71@s_0,w_297,h_180,q_100"}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryMercLogoResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T107
         * submitTime : 20161208171034
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161208171659
         * hostTraceNo : 324648
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
         * appId : 01806661
         * mercName : 宜芝多（延长路店）
         * mercLogo : https://img.waimai.baidu.com/pb/36217f2d092346a87131479e5a93533d71@s_0,w_297,h_180,q_100
         */

        public String appId;//应用的id
        public String mercName;//商户名称
        public String mercLogo;//商户logo的url

        public BodyBean(String appId, String mercName, String mercLogo) {
            this.appId = appId;
            this.mercName = mercName;
            this.mercLogo = mercLogo;
        }

        @Override
        public String toString() {
            return "{" +
                    "appId:'" + appId + '\'' +
                    ", mercName:'" + mercName + '\'' +
                    ", mercLogo:'" + mercLogo + '\'' +
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
