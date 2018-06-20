package unionlive.com.umqpos.entitys.in_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/31 14:07
 * @describe ${TODO}
 */

public class UpDateStock {
    /**
     * header : {"version":"1.0","transType":"T421","submitTime":"20161231140622","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.dish.update.stock"}
     * body : {"channelId":"189021000009002","shopId":"182000899000001","foodId":"10126","norms":[{"id":"1","stock":"191"}]}
     */

    public HeaderBean header;
    public BodyBean body;

    public UpDateStock(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T421
         * submitTime : 20161231140622
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.dish.update.stock
         */

        public String version;
        public String transType;
        public String submitTime;
        public String sessionId;
        public String clientTraceNo;
        public String method;
        public String termSn;

        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String clientTraceNo, String method, String termSn) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
            this.termSn = termSn;
        }

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", method:'" + method + '\'' +
                    ", termSn:'" + termSn + '\'' +
                    '}';
        }
    }

    public static class BodyBean {
        /**
         * channelId : 189021000009002
         * shopId : 182000899000001
         * foodId : 10126
         * norms : [{"id":"1","stock":"191"}]
         */

        public String loginSessionId;
        public String channelId;
        public String          shopId;
        public String          foodId;
        public List<NormsBean> norms;

        public static class NormsBean {
            /**
             * id : 1
             * stock : 191
             */

            public String id;
            public String stock;
            public String price;
            public String maxStock;

            public NormsBean(String id, String stock, String price,String maxStock) {
                this.id = id;
                this.stock = stock;
                this.price = price;
                this.maxStock = maxStock;
            }

            @Override
            public String toString() {
                return "{" +
                        "id:'" + id + '\'' +
                        ", stock:'" + stock + '\'' +
                        ", price:'" + price + '\'' +
                        ", price:'" + maxStock + '\'' +
                        '}';
            }
        }

        public BodyBean(String loginSessionId, String channelId, String shopId, String foodId, List<NormsBean> norms) {
            this.loginSessionId = loginSessionId;
            this.channelId = channelId;
            this.shopId = shopId;
            this.foodId = foodId;
            this.norms = norms;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", channelId:'" + channelId + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    ", foodId:'" + foodId + '\'' +
                    ", norms:" + norms +
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
