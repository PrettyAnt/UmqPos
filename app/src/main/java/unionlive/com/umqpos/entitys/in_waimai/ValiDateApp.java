package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/2 13:18
 * @describe 客户端应用校验
 */

public class ValiDateApp {
    public ValiDateApp(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    /**
     * header : {"version":"1.0","transType":"T100","submitTime":"20161208092448","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.system.validateapp"}
     * body : {"appId":"182000899000001","appVersionNo":"1","appVersion":"1.0","deviceType":"clientz","deviceOS":"clientz","deviceOSVersion":"44","deviceModel":"clientz"}
     */

    public HeaderBean header;
    public BodyBean body;

    public static class HeaderBean {
        public HeaderBean(String version, String transType, String submitTime, String sessionId, String clientTraceNo, String method) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
        }

        /**
         * version : 1.0
         * transType : T100
         * submitTime : 20161208092448
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.system.validateapp
         */

        public String version;      //报文版本号
        public String transType;    //交易类型
        public String submitTime;   //交易提交时间
        public String sessionId;    //会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;       //方法名

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", method:'" + method + '\'' +
                    '}';
        }
    }

    public static class BodyBean {
        public BodyBean(String appId, int appVersionNo, String appVersion,
                        String deviceId, String deviceType, String deviceOS,
                        String deviceOSVersion, String deviceModel, String browserType,
                        String browserVersion, String clientMAC, String longitude,
                        String latitude, String country, String countryCode, String region,
                        String city, String street0, String street1, String street2,
                        String mmc, String mnc, String lac, String cellID, String apMAC, String apSSID) {
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
            this.clientMAC = clientMAC;
            this.longitude = longitude;
            this.latitude = latitude;
            this.country = country;
            this.countryCode = countryCode;
            this.region = region;
            this.city = city;
            this.street0 = street0;
            this.street1 = street1;
            this.street2 = street2;
            this.mmc = mmc;
            this.mnc = mnc;
            this.lac = lac;
            this.cellID = cellID;
            this.apMAC = apMAC;
            this.apSSID = apSSID;
        }

        /**
         * appId : 182000899000001
         * appVersionNo : 1
         * appVersion : 1.0
         * deviceType : clientz
         * deviceOS : clientz
         * deviceOSVersion : 44
         * deviceModel : clientz
         */

        public String appId;            //应用编号
        public int appVersionNo;        //应用版本序号
        public String appVersion;       //应用版本号
        public String deviceId;       //设备编号
        public String deviceType;       //设备类型
        public String deviceOS;         //设备系统
        public String deviceOSVersion;  //设备系统版本
        public String deviceModel;      //设备型号
        public String browserType;      //浏览器类型
        public String browserVersion;   //浏览器版本
        public String clientMAC;        //客户端MAC地址
        public String longitude;        //设备当前地理经度
        public String latitude;          //设备当前地理纬度
        public String country;          //设备当前所在国家
        public String countryCode;      //设备当前所在国家代码
        public String region;           //设备当前所在地区（省份）
        public String city;             //设备当前所在城市
        public String street0;          //街道地址0
        public String street1;          //街道地址1
        public String street2;          //街道地址2
        public String mmc;               //移动运营商国家代码
        public String mnc;             //移动运营商网络代码
        public String lac;            //移动运营商区域代码
        public String cellID;         //移动运营商基站ID
        public String apMAC;          //无线AP MAC地址
        public String apSSID;         //无线AP SSID

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
                    ", clientMAC:'" + clientMAC + '\'' +
                    ", longitude:'" + longitude + '\'' +
                    ", latitude:'" + latitude + '\'' +
                    ", country:'" + country + '\'' +
                    ", countryCode:'" + countryCode + '\'' +
                    ", region:'" + region + '\'' +
                    ", city:'" + city + '\'' +
                    ", street0:'" + street0 + '\'' +
                    ", street1:'" + street1 + '\'' +
                    ", street2:'" + street2 + '\'' +
                    ", mmc:'" + mmc + '\'' +
                    ", mnc:'" + mnc + '\'' +
                    ", lac:'" + lac + '\'' +
                    ", cellID:'" + cellID + '\'' +
                    ", apMAC:'" + apMAC + '\'' +
                    ", apSSID:'" + apSSID + '\'' +
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
