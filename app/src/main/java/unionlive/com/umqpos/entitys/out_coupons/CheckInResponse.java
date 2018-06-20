package unionlive.com.umqpos.entitys.out_coupons;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/19 19:30
 * @describe 验券的接口
 */

public class CheckInResponse {
    /**
     * body : {}
     * header : {"clientTraceNo":"20161219192614_88904045263593241811","hostTime":"20161219192609","hostTraceNo":"cbcd2363-210b-49b2-b665-fb01fab6fb13","returnCode":"0000","returnMessage":"交易成功","sessionId":"3a8ca39f-415f-4d43-b829-f2eac3e18079","signMessage":"","submitTime":"20161219192614","transType":"M001","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public CheckInResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public String termId;//终端编号
        public String mercId;//门店编号
        public String mercName;//门店名称
        public String address;//门店地址
        public String telephone;//电话
        public String title;//终端标题
        public int    isTestMode;//是否测试状态
        public String sessionId;//签到会话编号
        public String operId;//操作员编号
        public String operName;//操作员姓名
        public String transMap;//终端功能位图
        public String shopId;//门店编号

        public BodyBean(String termId, String mercId, String mercName, String address,
                        String telephone, String title, int isTestMode,
                        String sessionId, String operId, String operName, String transMap,
                        String shopId) {
            this.termId = termId;
            this.mercId = mercId;
            this.mercName = mercName;
            this.address = address;
            this.telephone = telephone;
            this.title = title;
            this.isTestMode = isTestMode;
            this.sessionId = sessionId;
            this.operId = operId;
            this.operName = operName;
            this.transMap = transMap;
            this.shopId = shopId;
        }

        @Override
        public String toString() {
            return "{" +
                    "termId:'" + termId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", mercName:'" + mercName + '\'' +
                    ", address:'" + address + '\'' +
                    ", telephone:'" + telephone + '\'' +
                    ", title:'" + title + '\'' +
                    ", isTestMode:" + isTestMode +
                    ", sessionId:'" + sessionId + '\'' +
                    ", operId:'" + operId + '\'' +
                    ", operName:'" + operName + '\'' +
                    ", transMap:'" + transMap + '\'' +
                    ", shopId:'" + shopId + '\'' +
                    '}';
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20161219192614_88904045263593241811
         * hostTime : 20161219192609
         * hostTraceNo : cbcd2363-210b-49b2-b665-fb01fab6fb13
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : 3a8ca39f-415f-4d43-b829-f2eac3e18079
         * signMessage :
         * submitTime : 20161219192614
         * transType : M001
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

        public HeaderBean(String clientTraceNo, String hostTime, String hostTraceNo, String returnCode,
                          String returnMessage, String sessionId, String signMessage, String submitTime,
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
}
