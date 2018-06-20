package unionlive.com.umqpos.entitys.out_coupons;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/15 19:01
 * @describe ${TODO}M002的返回报文
 */

public class QueryAppVersionResponse {
    /**
     * body : {}
     * header : {"clientTraceNo":"20170115185500_76129642866729079219","hostTime":"20170115185437","hostTraceNo":"505ae6f6-ff57-4ebe-83b4-70862a8c1656","returnCode":"0000","returnMessage":"交易成功","sessionId":"1d7a9f14-fabb-4562-9978-24fe00a2778d","signMessage":"","submitTime":"20170115185500","transType":"M002","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public QueryAppVersionResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public String latestVersion;//最新版本号
        public String downloadUrl;//下载链接
        public String forceUpdate;//是否强制升级
        public String description;//版本描述信息

        public BodyBean(String latestVersion, String downloadUrl, String forceUpdate, String description) {
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

    public static class HeaderBean {
        /**
         * clientTraceNo : 20170115185500_76129642866729079219
         * hostTime : 20170115185437
         * hostTraceNo : 505ae6f6-ff57-4ebe-83b4-70862a8c1656
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : 1d7a9f14-fabb-4562-9978-24fe00a2778d
         * signMessage :
         * submitTime : 20170115185500
         * transType : M002
         * version : 1.0
         */

        public String clientTraceNo;
        public String hostTime;
        public String hostTraceNo;
        public String returnCode;
        public String returnMessage;
        public String sessionId;
        public String signMessage;
        public String submitTime;
        public String transType;
        public String version;

        public HeaderBean(String clientTraceNo, String hostTime, String hostTraceNo,
                          String returnCode, String returnMessage, String sessionId,
                          String signMessage, String submitTime, String transType, String version) {
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
            this.sessionId = sessionId;
            this.signMessage = signMessage;
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
                    ", signMessage:'" + signMessage + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", version:'" + version + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "body:" + body +
                ", header:" + header +
                '}';
    }
}
