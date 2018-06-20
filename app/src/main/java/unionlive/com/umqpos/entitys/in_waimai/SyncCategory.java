package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 10:03
 * @describe $菜品分类同步  同步菜品分类到外卖平台
 */

public class SyncCategory {
    /**
     * header : {"version":"1.0","transType":"T404","submitTime":"20161209100005","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.dish.category.sync"}
     * body : {"channelId":"1003","shopId":"182000899000001","cateId":"10125"}
     */

    public HeaderBean header;
    public BodyBean body;

    public SyncCategory(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T404
         * submitTime : 20161209100005
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.dish.category.sync
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
         * channelId : 1003
         * shopId : 182000899000001
         * cateId : 10125
         */

        public String channelId;//外卖渠道ID
        public String shopId;   //门店户ID
        public String cateId;   //分类ID

        public BodyBean(String channelId, String shopId, String cateId) {
            this.channelId = channelId;
            this.shopId = shopId;
            this.cateId = cateId;
        }

        @Override
        public String toString() {
            return "{" +
                    "channelId:'" + channelId + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    ", cateId:'" + cateId + '\'' +
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
