package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 16:57
 * @describe 商户名称查询
 */

public class QueryShopNameResponse {

    /**
     * header : {"version":"1.0","transType":"T105","submitTime":"20161208165205","sessionId":"","clientTraceNo":"","hostTime":"20161208165225","hostTraceNo":"324239","returnCode":"0000","returnMessage":"交易成功"}
     * body : {"shopName":"宜芝多（延长路店）","addr":"上海黄浦区营业地址"}
     */

    public HeaderBean header;
    public BodyBean body;

    public QueryShopNameResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T105
         * submitTime : 20161208165205
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161208165225
         * hostTraceNo : 324239
         * returnCode : 0000
         * returnMessage : 交易成功
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
                          String returnCode,
                          String returnMessage) {
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
        /**
         * shopName : 宜芝多（延长路店）
         * addr : 上海黄浦区营业地址
         */

        public String shopName;//商户名称
        public String addr;//商户地址

        public BodyBean(String shopName, String addr) {
            this.shopName = shopName;
            this.addr = addr;
        }

        @Override
        public String toString() {
            return "{" +
                    "shopName:'" + shopName + '\'' +
                    ", addr:'" + addr + '\'' +
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
