package unionlive.com.umqpos.entitys.out_coupons;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/9 11:24
 * @describe ${TODO}
 */

public class PurchaseCouponsResponse {

    public HeaderBean header;
    public BodyBean body;

    public PurchaseCouponsResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String hostTime;//后台处理时间
        public String hostTraceNo;//后台交易流水号
        public String returnCode;//返回码
        public String returnMessage;//返回码描述
        public String signMessage;//签名数据

        public HeaderBean(String version, String transType, String submitTime, String sessionId, String clientTraceNo,
                          String hostTime, String hostTraceNo, String returnCode, String returnMessage, String signMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
            this.signMessage = signMessage;
        }

        @Override
        public String toString() {
            return "{" +
                    "version:'" + version + '\'' +
                    ", transType:'" + transType + '\'' +
                    ", submitTime:'" + submitTime + '\'' +
                    ", sessionId:'" + sessionId + '\'' +
                    ", clientTraceNo:'" + clientTraceNo + '\'' +
                    ", hostTime:'" + hostTime + '\'' +
                    ", hostTraceNo:'" + hostTraceNo + '\'' +
                    ", returnCode:'" + returnCode + '\'' +
                    ", returnMessage:'" + returnMessage + '\'' +
                    ", signMessage:'" + signMessage + '\'' +
                    '}';
        }
    }

    public static class BodyBean {
        public String sessionId;//签到会话编号
        public String termId;//终端编号
        public String mercId;//门店编号
        public String operId;//操作员编号
        public String couponsNo;//券号
        public int useTimes;//使用次数
        public String mercName;//门店名称
        public String authCode;//授权码
        public String prodId;//产品编号
        public String prodName;//产品名称
        public String prodDesc;//产品简述
        public int availTimes;//剩余的可用次数
        public String ad;//广告
        public String title;//凭条标题
        public String transName;//交易名称
        public String expireDate;//券有效期

        public BodyBean(String sessionId, String termId, String mercId,
                        String operId, String couponsNo, int useTimes, String mercName,
                        String authCode, String prodId, String prodName,
                        String prodDesc, int availTimes, String ad, String title, String transName, String expireDate) {
            this.sessionId = sessionId;
            this.termId = termId;
            this.mercId = mercId;
            this.operId = operId;
            this.couponsNo = couponsNo;
            this.useTimes = useTimes;
            this.mercName = mercName;
            this.authCode = authCode;
            this.prodId = prodId;
            this.prodName = prodName;
            this.prodDesc = prodDesc;
            this.availTimes = availTimes;
            this.ad = ad;
            this.title = title;
            this.transName = transName;
            this.expireDate = expireDate;
        }

        @Override
        public String toString() {
            return "{" +
                    "sessionId:'" + sessionId + '\'' +
                    ", termId:'" + termId + '\'' +
                    ", mercId:'" + mercId + '\'' +
                    ", operId:'" + operId + '\'' +
                    ", couponsNo:'" + couponsNo + '\'' +
                    ", useTimes:" + useTimes +
                    ", mercName:'" + mercName + '\'' +
                    ", authCode:'" + authCode + '\'' +
                    ", prodId:'" + prodId + '\'' +
                    ", prodName:'" + prodName + '\'' +
                    ", prodDesc:'" + prodDesc + '\'' +
                    ", availTimes:" + availTimes +
                    ", ad:'" + ad + '\'' +
                    ", title:'" + title + '\'' +
                    ", transName:'" + transName + '\'' +
                    ", expireDate:'" + expireDate + '\'' +
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
