package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 19:33
 * @describe 商户关闭自动接单
 */

public class CloseOrderAuto {
    /**
     * header : {"version":"1.0","transType":"T304","submitTime":"20161208193307","sessionId":"","clientTraceNo":"","method":"unionlive.merchant.closeorderauto"}
     * body : {"loginSessionId":"a393e99a-bc87-49e2-b99a-35318cff88eb","channelId":""}
     */

    public HeaderBean header;
    public BodyBean body;

    public CloseOrderAuto(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T304
         * submitTime : 20161208193307
         * sessionId :
         * clientTraceNo :
         * method : unionlive.merchant.closeorderauto
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//终端号

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId, String clientTraceNo, String method, String termSn) {
            this.version = version;
            this.transType = transType;
            this.submitTime = submitTime;
            this.sessionId = sessionId;
            this.clientTraceNo = clientTraceNo;
            this.method = method;
            this.termSn = termSn;
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
                    ", termSn:'" + termSn + '\'' +
                    '}';
        }
    }

    public static class BodyBean {
        /**
         * loginSessionId : a393e99a-bc87-49e2-b99a-35318cff88eb
         * channelId :
         */

        public String loginSessionId;//登录会话编号
        public String channelId;//渠道ID
        public String updateType;//修改类型

        public BodyBean(String loginSessionId, String channelId,String updateType) {
            this.loginSessionId = loginSessionId;
            this.channelId = channelId;
            this.updateType = updateType;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", channelId:'" + channelId + '\'' +
                    ", updateType:'" + updateType + '\'' +
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
