package unionlive.com.umqpos.entitys.in_coupons;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/9 10:50
 * @describe 验券--------查询交易的基本信息
 */

public class QueryTransBasic {

    public HeaderBean header;
    public BodyBean body;

    public QueryTransBasic(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {

        public String version;//报文版本号
        public String transType;//报文类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String serialNo;//终端硬件序列号
        public String clientTraceNo;//客户端交易流水号
        public String signMessage;//签名数据

        public HeaderBean(String version, String transType, String submitTime, String sessionId,
                          String serialNo, String clientTraceNo, String signMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.serialNo = serialNo;
            this.clientTraceNo = clientTraceNo;
            this.signMessage = signMessage;
        }

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", serialNo:'" + serialNo + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", signMessage:'" + signMessage + '\'' +
                    '}';
        }
    }

    public static class BodyBean {

        public String sessionId;//签到会话编号
        public String termId;//终端编号
        public String mercId;//门店编号
        public String operId;//操作员编号
        public String sortBy;//排序依据
        public int pageSize;//分页大小
        public int pageIndex;//分页索引

        public BodyBean(String sessionId, String termId, String mercId,
                        String operId, String sortBy, int pageSize, int pageIndex) {
            this.sessionId = sessionId;
            this.termId = termId;
            this.mercId = mercId;
            this.operId = operId;
            this.sortBy = sortBy;
            this.pageSize = pageSize;
            this.pageIndex = pageIndex;
        }

        @Override
        public String toString() {
            return "{" +
                    "sessionId:'" + sessionId + '\'' +
                    ", termId:'" + termId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", operId:'" + operId + '\'' +
                    ", sortBy:'" + sortBy + '\'' +
                    ", pageSize:" + pageSize +
                    ", pageIndex:" + pageIndex +
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
