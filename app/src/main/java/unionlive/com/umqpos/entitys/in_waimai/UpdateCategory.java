package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 9:49
 * @describe 更新菜品分类  -同步外卖平台
 */

public class UpdateCategory {
    /**
     * header : {"version":"1.0","transType":"T402","submitTime":"20161209094847","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.dish.category.update"}
     * body : {"mercId":"182000899000001","cateId":"10125","cateName":"饮料套餐","cateDesc":"分类描述","priority":1,"imgUrl":""}
     */

    public HeaderBean header;
    public BodyBean body;

    public UpdateCategory(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T402
         * submitTime : 20161209094847
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.dish.category.update
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名

        public HeaderBean(String version, String transType, String submitTime,
                          String sessionId, String clientTraceNo, String method) {
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
         * mercId : 182000899000001
         * cateId : 10125
         * cateName : 饮料套餐
         * cateDesc : 分类描述
         * priority : 1
         * imgUrl :
         */

        public String mercId;  //商户ID         是商户Id,不是门店ID
        public String cateId;  //分类ID
        public String cateName;//分类名称
        public String cateDesc;//分类描述
        public int    priority;//优先级
        public String imgUrl;  //图片地址

        public BodyBean(String mercId, String cateId, String cateName,
                        String cateDesc, int priority, String imgUrl) {
            this.mercId = mercId;
            this.cateId = cateId;
            this.cateName = cateName;
            this.cateDesc = cateDesc;
            this.priority = priority;
            this.imgUrl = imgUrl;
        }

        @Override
        public String toString() {
            return "{" +
                    "mercId:'" + mercId + '\'' +
                    ", cateId:'" + cateId + '\'' +
                    ", cateName:'" + cateName + '\'' +
                    ", cateDesc:'" + cateDesc + '\'' +
                    ", priority:" + priority +
                    ", imgUrl:'" + imgUrl + '\'' +
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
