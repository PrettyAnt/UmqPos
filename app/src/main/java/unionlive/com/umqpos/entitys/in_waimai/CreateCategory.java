package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 9:39
 * @describe 新增菜品分类  同步外卖平台
 */

public class CreateCategory {
    /**
     * header : {"version":"1.0","transType":"T401","submitTime":"20161209092444","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.dish.category.create"}
     * body : {"mercId":"182000899000001","cateName":"饮料","cateDesc":"分类描述","priority":1,"imgUrl":""}
     */

    public HeaderBean header;
    public BodyBean body;

    public CreateCategory(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T401
         * submitTime : 20161209092444
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.dish.category.create
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
         * mercId : 182000899000001
         * cateName : 饮料
         * cateDesc : 分类描述
         * priority : 1
         * imgUrl :
         */

        public String mercId;   //商户ID
        public String cateName; //分类名称
        public String cateDesc; //分类描述
        public String imgUrl;   //图片地址
        public int    priority; //优先级

        public BodyBean(String mercId, String cateName,
                        String cateDesc, String imgUrl,
                        int priority) {
            this.mercId = mercId;
            this.cateName = cateName;
            this.cateDesc = cateDesc;
            this.imgUrl = imgUrl;
            this.priority = priority;
        }

        @Override
        public String toString() {
            return "{" +
                    "mercId:'" + mercId + '\'' +
                    ", cateName:'" + cateName + '\'' +
                    ", cateDesc:'" + cateDesc + '\'' +
                    ", imgUrl:'" + imgUrl + '\'' +
                    ", priority:" + priority +
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
