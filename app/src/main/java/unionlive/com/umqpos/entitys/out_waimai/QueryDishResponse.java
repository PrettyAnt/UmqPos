package unionlive.com.umqpos.entitys.out_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/26 18:09
 * @describe ${TODO}
 */

public class QueryDishResponse {

    /**
     * body : {"foodName":[{"cateId":"101","foodId":"1001","foodName":"茄子炒鸡蛋","norms":[{"channel":[{"channelId":"189021000009002","price":"680","realStock":"12","status":"1","stock":""}],"sizeId":"11","sizeName":"大杯"},{"channel":[{"channelId":"189021000009002","price":"1111","realStock":"188","status":"1","stock":""}],"sizeId":"22","sizeName":"中杯"}],"pic":"","priority":""},{"cateId":"101","foodId":"1003","foodName":"茄子炒鸡蛋2","norms":[],"pic":"","priority":""},{"cateId":"101","foodId":"1004","foodName":"茄子炒鸡蛋4","norms":[],"pic":"","priority":""}]}
     * header : {"clientTraceNo":"20161230185135_68856028173944630674","hostTime":"20161230185207","hostTraceNo":"747401","returnCode":"0000","returnMessage":"交易成功","sessionId":"4923320b-865f-437f-abb7-8a4a5c41bc6d","submitTime":"20161230185135","transType":"T412","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public QueryDishResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public List<FoodsBean> foods;

        public BodyBean(List<FoodsBean> foods) {
            this.foods = foods;
        }

        public static class FoodsBean {
            /**
             * cateId : 101
             * foodId : 1001
             * foodName : 茄子炒鸡蛋
             * norms : [{"channel":[{"channelId":"189021000009002","price":"680","realStock":"12","status":"1","stock":""}],"sizeId":"11","sizeName":"大杯"},{"channel":[{"channelId":"189021000009002","price":"1111","realStock":"188","status":"1","stock":""}],"sizeId":"22","sizeName":"中杯"}]
             * pic :
             * priority :
             */

            public String cateId;
            public String          foodId;
            public String          foodName;
            public String          pic;
            public String          priority;
            public List<NormsBean> norms;

            public FoodsBean(String cateId, String foodId, String foodName,
                             String pic, String priority, List<NormsBean> norms) {
                this.cateId = cateId;
                this.foodId = foodId;
                this.foodName = foodName;
                this.pic = pic;
                this.priority = priority;
                this.norms = norms;
            }

            public static class NormsBean {
                /**
                 * channel : [{"channelId":"189021000009002","price":"680","realStock":"12","status":"1","stock":""}]
                 * sizeId : 11
                 * sizeName : 大杯
                 */

                public String sizeId;
                public String            sizeName;
                public List<ChannelBean> channel;

                public NormsBean(String sizeId, String sizeName, List<ChannelBean> channel) {
                    this.sizeId = sizeId;
                    this.sizeName = sizeName;
                    this.channel = channel;
                }

                public static class ChannelBean {
                    /**
                     * channelId : 189021000009002
                     * price : 680
                     * realStock : 12
                     * status : 1
                     * stock :
                     */

                    public String channelId;
                    public String price;
                    public String realStock;
                    public String status;
                    public String stock;//最大库存

                    public ChannelBean(String channelId, String price, String realStock, String status, String stock) {
                        this.channelId = channelId;
                        this.price = price;
                        this.realStock = realStock;
                        this.status = status;
                        this.stock = stock;
                    }

                    @Override
                    public String toString() {
                        return "{" +
                                "channelId:'" + channelId + '\'' +
                                ", price:'" + price + '\'' +
                                ", realStock:'" + realStock + '\'' +
                                ", status:'" + status + '\'' +
                                ", stock:'" + stock + '\'' +
                                '}';
                    }
                }

                @Override
                public String toString() {
                    return "{" +
                            "sizeId:'" + sizeId + '\'' +
                            ", sizeName:'" + sizeName + '\'' +
                            ", channel:" + channel +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "{" +
                        "cateId:'" + cateId + '\'' +
                        ", foodId:'" + foodId + '\'' +
                        ", foodName:'" + foodName + '\'' +
                        ", pic:'" + pic + '\'' +
                        ", priority:'" + priority + '\'' +
                        ", norms:" + norms +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "{" +
                    "foodName:" + foods +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20161230185135_68856028173944630674
         * hostTime : 20161230185207
         * hostTraceNo : 747401
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : 4923320b-865f-437f-abb7-8a4a5c41bc6d
         * submitTime : 20161230185135
         * transType : T412
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

        public HeaderBean(String clientTraceNo, String hostTime, String hostTraceNo, String returnCode,
                          String returnMessage, String sessionId, String submitTime,
                          String transType, String version) {
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
