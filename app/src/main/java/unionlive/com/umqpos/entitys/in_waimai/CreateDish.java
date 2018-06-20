package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/9 10:12
 * @describe T410 新增菜品
 */

public class CreateDish {
    /**
     * header : {"version":"1.0","transType":"T410","submitTime":"20161209100951","sessionId":"","clientTraceNo":"089f54c7-f013-4b51-8acc-2a1ae05e730a","method":"unionlive.dish.create"}
     * body : {"mercId":"182000899000001","foodName":"香蕉超茄子","cateId":"10125","priority":"11","upc":"","picUrl":"","minOrderNum":"1","packageNum":"1","desc":"自创黑暗料理","availDate":"1111111","availTimeStart":"0900","availTimeEnd":"2100","attrName":"","attrValue":"","isNew":"1","isFeatured":"1","isSpicy":"1","isGum":"1"}
     */

    public HeaderBean header;
    public BodyBean   body;

    public CreateDish(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T410
         * submitTime : 20161209100951
         * sessionId :
         * clientTraceNo : 089f54c7-f013-4b51-8acc-2a1ae05e730a
         * method : unionlive.dish.create
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
         * foodName : 香蕉超茄子
         * cateId : 10125
         * priority : 11
         * upc :
         * picUrl :
         * minOrderNum : 1
         * packageNum : 1
         * desc : 自创黑暗料理
         * availDate : 1111111
         * availTimeStart : 0900
         * availTimeEnd : 2100
         * attrName :
         * attrValue :
         * isNew : 1
         * isFeatured : 1
         * isSpicy : 1
         * isGum : 1
         */

        public String mercId;           //商户ID
        public String foodName;       //食物名称
        public String cateId;         //分类编号
        public int    priority;         //优先级
        public String upc;            //条形码
        public String picUrl;           //图片地址
        public int    minOrderNum;    //最小起订份数
        public int    packageNum;       //餐盒数量
        public String desc;                         //描述
        public String availDate;        //销售限制
        public String availTimeStart;       //起售时间
        public String availTimeEnd;     //截止时间
        public String attrName;             //菜品属性名称
        public String attrValue;            //菜品属性值
        public String isNew;            //是否新菜品
        public String isFeatured;           //是否招牌菜
        public String isSpicy;          //是否辣
        public int    isGum;            //是否配菜

        @Override
        public String toString() {
            return "{" +
                    "mercId:'" + mercId + '\'' +
                    ", foodName:'" + foodName + '\'' +
                    ", cateId:'" + cateId + '\'' +
                    ", priority:" + priority +
                    ", upc:'" + upc + '\'' +
                    ", picUrl:'" + picUrl + '\'' +
                    ", minOrderNum:" + minOrderNum +
                    ", packageNum:" + packageNum +
                    ", desc:'" + desc + '\'' +
                    ", availDate:'" + availDate + '\'' +
                    ", availTimeStart:'" + availTimeStart + '\'' +
                    ", availTimeEnd:'" + availTimeEnd + '\'' +
                    ", attrName:'" + attrName + '\'' +
                    ", attrValue:'" + attrValue + '\'' +
                    ", isNew:'" + isNew + '\'' +
                    ", isFeatured:'" + isFeatured + '\'' +
                    ", isSpicy:'" + isSpicy + '\'' +
                    ", isGum:" + isGum +
                    '}';
        }

        public BodyBean(String mercId, String foodName,
                        String cateId, int priority, String upc,
                        String picUrl, int minOrderNum,
                        int packageNum, String desc,
                        String availDate, String availTimeStart,
                        String availTimeEnd, String attrName,
                        String attrValue, String isNew,
                        String isFeatured, String isSpicy,
                        int isGum) {
            this.mercId = mercId;
            this.foodName = foodName;
            this.cateId = cateId;
            this.priority = priority;
            this.upc = upc;
            this.picUrl = picUrl;
            this.minOrderNum = minOrderNum;
            this.packageNum = packageNum;
            this.desc = desc;
            this.availDate = availDate;
            this.availTimeStart = availTimeStart;
            this.availTimeEnd = availTimeEnd;
            this.attrName = attrName;
            this.attrValue = attrValue;
            this.isNew = isNew;
            this.isFeatured = isFeatured;
            this.isSpicy = isSpicy;
            this.isGum = isGum;
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
