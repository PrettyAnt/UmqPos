package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 19:42
 * @describe 同步更新百度门店
 */

public class SyncBaidu {
    /**
     * header : {"version":"1.0","transType":"T305","submitTime":"20161208194122","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.shop.sync"}
     * body : {"channelId":"1001","shopId":"182000899000001","category1":"餐饮","category2":"糕点饮品","category3":"面包糕点"}
     */

    public HeaderBean header;
    public BodyBean body;

    public SyncBaidu(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T305
         * submitTime : 20161208194122
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.shop.sync
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo, String method) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
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
                    '}';
        }
    }

    public static class BodyBean {
        /**
         * channelId : 1001
         * shopId : 182000899000001
         * category1 : 餐饮
         * category2 : 糕点饮品
         * category3 : 面包糕点
         */

        public String channelId;//外卖渠道编号
        public String shopId;   //门店ID
        public String category1;//主营分类
        public String category2;//第一副营
        public String category3;//第二幅营

        public BodyBean(String channelId,
                        String shopId,
                        String category1,
                        String category2,
                        String category3) {
            this.channelId = channelId;
            this.shopId = shopId;
            this.category1 = category1;
            this.category2 = category2;
            this.category3 = category3;
        }

        @Override
        public String toString() {
            return "{" +
                    "channelId:'" + channelId + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    ", category1:'" + category1 + '\'' +
                    ", category2:'" + category2 + '\'' +
                    ", category3:'" + category3 + '\'' +
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
