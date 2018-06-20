package unionlive.com.umqpos.entitys.out_coupons;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/28 13:46
 * @describe ${TODO}
 */

public class QueryTransDetailsResponse {
    /**
     * body : {"transDetail":[{"amount":"1","authCode":"621847","expireDate":"20170331","hostTime":"20161229111609","hostTrace":"742839","mercId":"189990000060001","mercName":"优方科技","no":"1810400924005015","operId":"15903020686","prodCate":7,"prodDesc":"","prodId":"102144","prodName":"爱茜茜里抢元宝演示","termId":"00005327","transName":"券号兑换","transTime":"20161229111702"}]}
     * header : {"clientTraceNo":"20161229124837_59401808767945553149","hostTime":"20161229124845","hostTraceNo":"ae3b7800-5336-46f5-93ea-acd77357763e","returnCode":"0000","returnMessage":"交易成功","sessionId":"7bfbec55-ff72-447d-bfec-deff8f4d6c7d","signMessage":"","submitTime":"20161229124837","transType":"M202","version":"1.0"}
     */

    public BodyBean   body;
    public HeaderBean header;

    public QueryTransDetailsResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public List<TransDetailBean> transDetail;
        public int                   pageSize;
        public int                   pageNum;
        public int                   pageIndex;
        public int                   count;

        public static class TransDetailBean {
            /**
             * amount : 1
             * authCode : 621847
             * expireDate : 20170331
             * hostTime : 20161229111609
             * hostTrace : 742839
             * mercId : 189990000060001
             * mercName : 优方科技
             * no : 1810400924005015
             * operId : 15903020686
             * prodCate : 7
             * prodDesc :
             * prodId : 102144
             * prodName : 爱茜茜里抢元宝演示
             * termId : 00005327
             * transName : 券号兑换
             * transTime : 20161229111702
             */

            public String amount;//
            public String authCode;//
            public String expireDate;//
            public String hostTime;
            public String hostTrace;
            public String mercId;//
            public String mercName;//
            public String no;//
            public String operId;//
            public int    prodCate;
            public String prodDesc;//
            public String prodId;//
            public String prodName;//
            public String termId;
            public String transName;
            public String transTime;//

            public TransDetailBean(String amount, String authCode, String expireDate,
                                   String hostTime, String hostTrace, String mercId, String mercName,
                                   String no, String operId, int prodCate,
                                   String prodDesc, String prodId, String prodName, String termId,
                                   String transName, String transTime) {
                this.amount = amount;
                this.authCode = authCode;
                this.expireDate = expireDate;
                this.hostTime = hostTime;
                this.hostTrace = hostTrace;
                this.mercId = mercId;
                this.mercName = mercName;
                this.no = no;
                this.operId = operId;
                this.prodCate = prodCate;
                this.prodDesc = prodDesc;
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
                        ", expireDate:'" + expireDate + '\'' +
                        ", hostTime:'" + hostTime + '\'' +
                        ", hostTrace:'" + hostTrace + '\'' +
                        ", mercId:'" + mercId + '\'' +
                        ", mercName:'" + mercName + '\'' +
                        ", no:'" + no + '\'' +
                        ", operId:'" + operId + '\'' +
                        ", prodCate:" + prodCate +
                        ", prodDesc:'" + prodDesc + '\'' +
                        ", prodId:'" + prodId + '\'' +
                        ", prodName:'" + prodName + '\'' +
                        ", termId:'" + termId + '\'' +
                        ", transName:'" + transName + '\'' +
                        ", transTime:'" + transTime + '\'' +
                        '}';
            }
        }

        public BodyBean(List<TransDetailBean> transDetail, int pageSize,
                        int pageNum, int pageIndex, int count) {
            this.transDetail = transDetail;
            this.pageSize = pageSize;
            this.pageNum = pageNum;
            this.pageIndex = pageIndex;
            this.count = count;
        }

        @Override
        public String toString() {
            return "{" +
                    "transDetail:" + transDetail +
                    ", pageSize:" + pageSize +
                    ", pageNum:" + pageNum +
                    ", pageIndex:" + pageIndex +
                    ", count:" + count +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20161229124837_59401808767945553149
         * hostTime : 20161229124845
         * hostTraceNo : ae3b7800-5336-46f5-93ea-acd77357763e
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : 7bfbec55-ff72-447d-bfec-deff8f4d6c7d
         * signMessage :
         * submitTime : 20161229124837
         * transType : M202
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

        public HeaderBean(String clientTraceNo, String hostTime,
                          String hostTraceNo, String returnCode, String returnMessage,
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
