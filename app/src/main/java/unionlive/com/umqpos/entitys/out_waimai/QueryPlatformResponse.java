package unionlive.com.umqpos.entitys.out_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 17:06
 * @describe //FIXME 商户外卖平台查询
 */

public class QueryPlatformResponse {
    /**
     * body : {"channels":[{"allAutoOrder":1,"businessStatus":0,"channelId":"189021000009002","channelName":"百度外卖","logoPic":"http://d.umq.me/shopimg/20160825161434200489.png","salesPlatform":""},{"allAutoOrder":1,"businessStatus":0,"channelId":"189021000009002","channelName":"百度外卖","logoPic":"http://d.umq.me/shopimg/20160825161434200489.png","salesPlatform":""}]}
     * header : {"clientTraceNo":"","hostTime":"20161221205007","hostTraceNo":"735488","returnCode":"0000","returnMessage":"交易成功","sessionId":"","submitTime":"20161221205004","transType":"T106","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public QueryPlatformResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public List<ChannelsBean> channels;

        public static class ChannelsBean {
            /**
             * allAutoOrder : 1
             * businessStatus : 0
             * channelId : 189021000009002
             * channelName : 百度外卖
             * logoPic : http://d.umq.me/shopimg/20160825161434200489.png
             * salesPlatform :
             */

            public int autoOrder;
            public int    businessStatus;//营业状态
            public String channelId;
            public String channelName;
            public String logoPic;
            public String salesPlatform;

            public ChannelsBean(int autoOrder, int businessStatus, String channelId,
                                String channelName, String logoPic, String salesPlatform) {
                this.autoOrder = autoOrder;
                this.businessStatus = businessStatus;
                this.channelId = channelId;
                this.channelName = channelName;
                this.logoPic = logoPic;
                this.salesPlatform = salesPlatform;
            }

            @Override
            public String toString() {
                return "{" +
                        "allAutoOrder:" + autoOrder +
                        ", businessStatus:" + businessStatus +
                        ", channelId:'" + channelId + '\'' +
                        ", channelName:'" + channelName + '\'' +
                        ", logoPic:'" + logoPic + '\'' +
                        ", salesPlatform:'" + salesPlatform + '\'' +
                        '}';
            }
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo :
         * hostTime : 20161221205007
         * hostTraceNo : 735488
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId :
         * submitTime : 20161221205004
         * transType : T106
         * version : 1.0
         */

        public String clientTraceNo;
        public String hostTime;
        public String hostTraceNo;
        public String returnCode;
        public String returnMessage;
        public String sessionId;
        public String submitTime;
        public String transType;
        public String version;

        public HeaderBean(String clientTraceNo, String hostTime, String hostTraceNo,
                          String returnCode, String returnMessage,
                          String sessionId, String submitTime, String transType, String version) {
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
            this.sessionId = sessionId;
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
