package unionlive.com.umqpos.entitys.out_coupons;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/2 13:18
 * @describe 客户端应用校验返回的密文
 */

public class CouponsResponse {

    /**
     * header : {"version":"1.0","transType":"T100","submitTime":"20161208103730","sessionId":"944093cc-6b5b-421e-8003-f03bf6e79735","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","hostTime":"20161208103728","hostTraceNo":"320579","returnCode":"0000","returnMessage":"交易成功"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public CouponsResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T100
         * submitTime : 20161208103730
         * sessionId : 944093cc-6b5b-421e-8003-f03bf6e79735
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * hostTime : 20161208103728
         * hostTraceNo : 320579
         * returnCode : 0000
         * returnMessage : 交易成功
         */

        public String version;      //报文版本号
        public String transType;     //交易类型
        public String submitTime;   //交易时间
        public String sessionId;    //会话编号
        public String clientTraceNo;//客户端交易流水号
        public String hostTime;         //后台处理时间
        public String hostTraceNo;      //后台交易流水号
        public String returnCode;   //返回码
        public String returnMessage;//返回码描述
        public String signMessage;//签名数据

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
                    ", signMessage:'" + signMessage + '\'' +
                    '}';
        }

        public HeaderBean(String version,
                          String transType,
                          String submitTime,
                          String sessionId,
                          String clientTraceNo,
                          String hostTime,
                          String hostTraceNo,
                          String returnCode,
                          String returnMessage,
                          String signMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
            this.signMessage = signMessage;
        }
    }

    public static class BodyBean {
        public String latestVersion;//最新版本号
        public String downloadUrl;//下载链接
        public String forceUpdate;//是否强制升级
        public String description;//版本描述信息

        public BodyBean(String latestVersion,
                        String downloadUrl,
                        String forceUpdate,
                        String description) {
            this.latestVersion = latestVersion;
            this.downloadUrl = downloadUrl;
            this.forceUpdate = forceUpdate;
            this.description = description;
        }

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
