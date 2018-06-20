package unionlive.com.umqpos.entitys.in_waimai;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 17:28
 * @describe 订单查询接口
 */

public class OrderList {
    /**
     * header : {"version":"1.0","transType":"T201","submitTime":"20161208172943","sessionId":"","clientTraceNo":"","method":"unionlive.order.list"}
     * body : {"loginSessionId":"2cdd575a-ff7f-4be4-b038-012dec19e5a5","status":-1,"channelId":"","pageSize":3,"pageIndex":0,"beginDate":"20160726","endDate":"20160726"}
     */

    public HeaderBean header;
    public BodyBean body;

    public OrderList(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * version : 1.0
         * transType : T201
         * submitTime : 20161208172943
         * sessionId :
         * clientTraceNo :
         * method : unionlive.order.list
         */

        public String version;//报文版本号
        public String transType;//交易类型
        public String submitTime;//交易提交时间
        public String sessionId;//会话编号
        public String clientTraceNo;//客户端交易流水号
        public String method;//方法名
        public String termSn;//方法名

        public HeaderBean(String version, String transType,
                          String submitTime, String sessionId,
                          String clientTraceNo, String method, String termSn) {
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
         * loginSessionId : 2cdd575a-ff7f-4be4-b038-012dec19e5a5
         * status : -1
         * channelId :
         * pageSize : 3
         * pageIndex : 0
         * beginDate : 20160726
         * endDate : 20160726
         */

        public String loginSessionId;//登录会话编号
        public int    status;           //订单状态
        public String channelId;        //外卖平台编号
        public int    pageSize;      //分页大小
        public int    pageIndex;        //分页索引
        public String beginDate;        //开始日期
        public String endDate;          //结束日期

        public BodyBean(String loginSessionId, int status, String channelId,
                        int pageSize, int pageIndex,
                        String beginDate, String endDate) {
            this.loginSessionId = loginSessionId;
            this.status = status;
            this.channelId = channelId;
            this.pageSize = pageSize;
            this.pageIndex = pageIndex;
            this.beginDate = beginDate;
            this.endDate = endDate;
        }

        @Override
        public String toString() {
            return "{" +
                    "loginSessionId:'" + loginSessionId + '\'' +
                    ", status:" + status +
                    ", channelId:'" + channelId + '\'' +
                    ", pageSize:" + pageSize +
                    ", pageIndex:" + pageIndex +
                    ", beginDate:'" + beginDate + '\'' +
                    ", endDate:'" + endDate + '\'' +
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
