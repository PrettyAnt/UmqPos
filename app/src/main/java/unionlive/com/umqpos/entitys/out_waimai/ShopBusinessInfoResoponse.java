package unionlive.com.umqpos.entitys.out_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 15:11
 * @describe 商铺营业情况查询 返回报文---->会返回今日已处理的订单和销售总量
 */
// FIXME 根据具体情况处理
public class ShopBusinessInfoResoponse {

    /**
     * body : {"channel":[{"channelId":"189021000009002","orderAmount":0,"orderCount":0}],"total":{"orderAmount":0,"orderCount":0}}
     * header : {"clientTraceNo":"20170110132335_32947018810156474627","hostTime":"20170110132348","hostTraceNo":"773283","returnCode":"0000","returnMessage":"交易成功","sessionId":"e2a255d0-9703-4779-83da-92fcf85b1f46","submitTime":"20170110132335","transType":"T102","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public static class BodyBean {
        /**
         * channel : [{"channelId":"189021000009002","orderAmount":0,"orderCount":0}]
         * total : {"orderAmount":0,"orderCount":0}
         */

        public TotalBean total;
        public List<ChannelBean> channel;

        public static class TotalBean {
            /**
             * orderAmount : 0
             * orderCount : 0
             */
            public int orderAmount;
            public int orderCount;

            public TotalBean(int orderAmount, int orderCount) {
                this.orderAmount = orderAmount;
                this.orderCount = orderCount;
            }

            @Override
            public String toString() {
                return "{" +
                        "orderAmount:" + orderAmount +
                        ", orderCount:" + orderCount +
                        '}';
            }
        }

        public static class ChannelBean {
            /**
             * channelId : 189021000009002
             * orderAmount : 0
             * orderCount : 0
             */

            public String channelId;
            public int orderAmount;
            public int orderCount;

            public ChannelBean(String channelId, int orderAmount, int orderCount) {
                this.channelId = channelId;
                this.orderAmount = orderAmount;
                this.orderCount = orderCount;
            }

            @Override
            public String toString() {
                return "{" +
                        "channelId:'" + channelId + '\'' +
                        ", orderAmount:" + orderAmount +
                        ", orderCount:" + orderCount +
                        '}';
            }
        }

        public BodyBean(TotalBean total, List<ChannelBean> channel) {
            this.total = total;
            this.channel = channel;
        }

        @Override
        public String toString() {
            return "{" +
                    "total:" + total +
                    ", channel:" + channel +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20170110132335_32947018810156474627
         * hostTime : 20170110132348
         * hostTraceNo : 773283
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : e2a255d0-9703-4779-83da-92fcf85b1f46
         * submitTime : 20170110132335
         * transType : T102
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

        public HeaderBean(String clientTraceNo, String hostTime,
                          String hostTraceNo, String returnCode,
                          String returnMessage, String sessionId,
                          String submitTime, String transType, String version) {
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

    public ShopBusinessInfoResoponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    @Override
    public String toString() {
        return "{" +
                "body:" + body +
                ", header:" + header +
                '}';
    }
}
