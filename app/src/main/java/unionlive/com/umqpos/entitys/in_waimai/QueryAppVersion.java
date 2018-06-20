package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/7 19:47
 * @describe 客户端应版本查询上送报文的实体类
 */

public class QueryAppVersion {

    public HeaderBean header;
    public BodyBean   body;

    public QueryAppVersion(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {

        /**
         * version : 1.0
         * transType : T100
         * submitTime : 20161207194538
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.system.queryappversion
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String serialNo;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String signMessage;//签名数据

        public HeaderBean(String version, String transType, String submitTime, String sessionId, String serialNo, String clientTraceNo, String signMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.serialNo = serialNo;
            this.clientTraceNo = clientTraceNo;
            this.signMessage = signMessage;
        }

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", serialNo:'" + serialNo + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", signMessage:'" + signMessage + '\'' +
                    '}';
        }
    }

    public static class BodyBean {


        /**
         * appId : 182000899000001
         * appVersionNo : 1
         * appVersion : 1.0
         * deviceId :
         * deviceType : clientz
         * deviceOS : clientz
         * deviceOSVersion : 44
         * deviceModel : clientz
         * browserType :
         * browserVersion :
         */

        public String appId;  //应用编号
        public int appVersionNo;  //应用版本序号
        public String appVersion;  //应用版本号
        public String deviceId;//设备编号
        public String deviceType;  //设备类型
        public String deviceOS;  //设备系统
        public String deviceOSVersion;  //设备系统版本
        public String deviceModel;  //设备型号
        public String browserType;  //浏览器类型
        public String browserVersion;  //浏览器版本

        public BodyBean(String appId, int appVersionNo, String appVersion, String deviceId, String deviceType,
                        String deviceOS, String deviceOSVersion, String deviceModel, String browserType, String browserVersion) {
            this.appId = appId;
            this.appVersionNo = appVersionNo;
            this.appVersion = appVersion;
            this.deviceId = deviceId;
            this.deviceType = deviceType;
            this.deviceOS = deviceOS;
            this.deviceOSVersion = deviceOSVersion;
            this.deviceModel = deviceModel;
            this.browserType = browserType;
            this.browserVersion = browserVersion;
        }

        @Override
        public String toString() {
            return "{" +
                    "appId:'" + appId + '\'' +
                    ", appVersionNo:" + appVersionNo +
                    ", appVersion:'" + appVersion + '\'' +
                    ", deviceId:'" + deviceId + '\'' +
                    ", deviceType:'" + deviceType + '\'' +
                    ", deviceOS:'" + deviceOS + '\'' +
                    ", deviceOSVersion:'" + deviceOSVersion + '\'' +
                    ", deviceModel:'" + deviceModel + '\'' +
                    ", browserType:'" + browserType + '\'' +
                    ", browserVersion:'" + browserVersion + '\'' +
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
