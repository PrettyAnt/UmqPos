package unionlive.com.umqpos.entitys.out_coupons;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/9 11:53
 * @describe ${TODO}
 */

public class QueryTransBasicResponse {

    /**
     * body : {"count":0,"pageIndex":"0","pageNum":0,"pageSize":"0","transInfo":[{"amount":"1","authCode":"620810","hostTime":"20161228112925","hostTrace":"740433","mercId":"189990000060001","mercName":"优方科技","no":"1811004020609510","operId":"15903020686","prodCate":7,"prodId":"102561","prodName":"限制券测试","termId":"00005327","transName":"券号兑换","transTime":"20161228112838"}]}
     * header : {"clientTraceNo":"20161228135521_40163917554438468748","hostTime":"20161228135434","hostTraceNo":"56687709-2ff6-4660-b1b6-d2f4bb23163a","returnCode":"0000","returnMessage":"交易成功","sessionId":"ebf8e870-6eea-4bc6-ae09-04c59822d389","signMessage":"","submitTime":"20161228135521","transType":"M201","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public QueryTransBasicResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        /**
         * count : 0
         * pageIndex : 0
         * pageNum : 0
         * pageSize : 0
         * transInfo : [{"amount":"1","authCode":"620810","hostTime":"20161228112925","hostTrace":"740433","mercId":"189990000060001","mercName":"优方科技","no":"1811004020609510","operId":"15903020686","prodCate":7,"prodId":"102561","prodName":"限制券测试","termId":"00005327","transName":"券号兑换","transTime":"20161228112838"}]
         */

        public int count;
        public String              pageIndex;
        public int                 pageNum;
        public String              pageSize;
        public List<TransInfoBean> transInfo;

        public BodyBean(int count, String pageIndex, int pageNum, String pageSize, List<TransInfoBean> transInfo) {
            this.count = count;
            this.pageIndex = pageIndex;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.transInfo = transInfo;
        }

        public static class TransInfoBean {
            /**
             * amount : 1
             * authCode : 620810
             * hostTime : 20161228112925
             * hostTrace : 740433
             * mercId : 189990000060001
             * mercName : 优方科技
             * no : 1811004020609510
             * operId : 15903020686
             * prodCate : 7
             * prodId : 102561
             * prodName : 限制券测试
             * termId : 00005327
             * transName : 券号兑换
             * transTime : 20161228112838
             */

            public String amount;
            public String authCode;
            public String hostTime;
            public String hostTrace;
            public String mercId;
            public String mercName;
            public String no;
            public String operId;
            public int    prodCate;
            public String prodId;
            public String prodName;
            public String termId;
            public String transName;
            public String transTime;

            public TransInfoBean(String amount, String authCode, String hostTime, String hostTrace,
                                 String mercId, String mercName, String no,
                                 String operId, int prodCate, String prodId, String prodName,
                                 String termId, String transName, String transTime) {
                this.amount = amount;
                this.authCode = authCode;
                this.hostTime = hostTime;
                this.hostTrace = hostTrace;
                this.mercId = mercId;
                this.mercName = mercName;
                this.no = no;
                this.operId = operId;
                this.prodCate = prodCate;
                this.prodId = prodId;
                this.prodName = prodName;
                this.termId = termId;
                this.transName = transName;
                this.transTime = transTime;
            }

            @Override
            public String toString() {
                return "{" +
                        "amount:'" + amount + '\'' +
                        ", authCode:'" + authCode + '\'' +
                        ", hostTime:'" + hostTime + '\'' +
                        ", hostTrace:'" + hostTrace + '\'' +
                        ", mercId:'" + mercId + '\'' +
                        ", mercName:'" + mercName + '\'' +
                        ", no:'" + no + '\'' +
                        ", operId:'" + operId + '\'' +
                        ", prodCate:" + prodCate +
                        ", prodId:'" + prodId + '\'' +
                        ", prodName:'" + prodName + '\'' +
                        ", termId:'" + termId + '\'' +
                        ", transName:'" + transName + '\'' +
                        ", transTime:'" + transTime + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "{" +
                    "count:" + count +
                    ", pageIndex:'" + pageIndex + '\'' +
                    ", pageNum:" + pageNum +
                    ", pageSize:'" + pageSize + '\'' +
                    ", transInfo:" + transInfo +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20161228135521_40163917554438468748
         * hostTime : 20161228135434
         * hostTraceNo : 56687709-2ff6-4660-b1b6-d2f4bb23163a
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : ebf8e870-6eea-4bc6-ae09-04c59822d389
         * signMessage :
         * submitTime : 20161228135521
         * transType : M201
         * version : 1.0
         */

        public String clientTraceNo;
        public String hostTime;
        public String hostTraceNo;
        public String returnCode;
        public String returnMessage;
        public String sessionId;
        public String signMessage;
        public String submitTime;
        public String transType;
        public String version;

        public HeaderBean(String clientTraceNo, String hostTime, String hostTraceNo,
                          String returnCode, String returnMessage,
                          String sessionId, String signMessage, String submitTime,
                          String transType, String version) {
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
            this.sessionId = sessionId;
            this.signMessage = signMessage;
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
                    ", signMessage:'" + signMessage + '\'' +
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
