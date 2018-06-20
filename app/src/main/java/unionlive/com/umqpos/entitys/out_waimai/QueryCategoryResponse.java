package unionlive.com.umqpos.entitys.out_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/26 11:06
 * @describe T403 返回报文
 */

public class QueryCategoryResponse {
    /**
     * body : {"cates":[{"cateDesc":"","cateId":"101","cateName":"火星菜谱","imgUrl":"","priority":""},{"cateDesc":"","cateId":"102","cateName":"分类2","imgUrl":"","priority":""}]}
     * header : {"clientTraceNo":"20161226102722907","hostTime":"20161226102730","hostTraceNo":"736457","returnCode":"0000","returnMessage":"交易成功","sessionId":"196e57a3-fbd8-424b-a7a8-a3a9501c1133","submitTime":"20161226102722","transType":"T403","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public QueryCategoryResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public List<CatesBean> cates;

        public static class CatesBean {
            /**
             * cateDesc :
             * cateId : 101
             * cateName : 火星菜谱
             * imgUrl :
             * priority :
             */

            public String cateDesc;
            public String cateId;
            public String cateName;
            public String imgUrl;
            public String priority;

            public CatesBean(String cateDesc, String cateId, String cateName, String imgUrl, String priority) {
                this.cateDesc = cateDesc;
                this.cateId = cateId;
                this.cateName = cateName;
                this.imgUrl = imgUrl;
                this.priority = priority;
            }

            @Override
            public String toString() {
                return "{" +
                        "cateDesc:'" + cateDesc + '\'' +
                        ", cateId:'" + cateId + '\'' +
                        ", cateName:'" + cateName + '\'' +
                        ", imgUrl:'" + imgUrl + '\'' +
                        ", priority:'" + priority + '\'' +
                        '}';
            }
        }

        public BodyBean(List<CatesBean> cates) {
            this.cates = cates;
        }

        @Override
        public String toString() {
            return "{" +
                    "cates:" + cates +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20161226102722907
         * hostTime : 20161226102730
         * hostTraceNo : 736457
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : 196e57a3-fbd8-424b-a7a8-a3a9501c1133
         * submitTime : 20161226102722
         * transType : T403
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
                          String returnCode, String returnMessage, String sessionId,
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

    @Override
    public String toString() {
        return "{" +
                "body:" + body +
                ", header:" + header +
                '}';
    }
}
