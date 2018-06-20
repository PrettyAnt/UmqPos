package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 18:43
 * @describe 订单数量查询返回的报文
 */

public class QueryOrderNumResponse {
    /**
     * header : {"version":"1.0","transType":"T204","submitTime":"20161208184322","sessionId":"","clientTraceNo":"","hostTime":"20161208184322","hostTraceNo":"325874","returnCode":"6091","returnMessage":"您的账号在其它终端登录"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryOrderNumResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T204
         * submitTime : 20161208184322
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161208184322
         * hostTraceNo : 325874
         * returnCode : 6091
         * returnMessage : 您的账号在其它终端登录
         */

        public String version;//报文版本号
        public String transType; //交易类型
        public String submitTime;//交易时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String hostTime;    //后台处理时间
        public String hostTraceNo;    //后台交易流水号
        public String returnCode;//返回码
        public String returnMessage;//返回码描述

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo,
                          String hostTime, String hostTraceNo,
                          String returnCode, String returnMessage) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.hostTime = hostTime;
            this.hostTraceNo = hostTraceNo;
            this.returnCode = returnCode;
            this.returnMessage = returnMessage;
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
                    '}';
        }
    }

    public static class BodyBean {
        public int wNum;//未处理数量
        public int yNum;//已处理数量

        public BodyBean(int wNum, int yNum) {
            this.wNum = wNum;
            this.yNum = yNum;
        }

        @Override
        public String toString() {
            return "{" +
                    "wNum:" + wNum +
                    ", yNum:" + yNum +
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
