package unionlive.com.umqpos.entitys.out_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/8 17:35
 * @describe //FIXME 这个还要根据具体返回的json来处理
 */

public class OrderTreateListResponse {
    /**
     * body : {"count":0,"orders":[{"cancelDate":"","cancelPeason":"","cancelTime":"","channelId":"","channelName":"","channelOrderId":"","comfirmDate":"","comfirmTime":"","distance":0,"expectDate":"","expectTime":"","invoiceTitle":"","invoiceType":0,"orderAmount":0,"orderDate":"","orderId":"123","orderSeq":0,"orderTime":"","payType":0,"status":0,"userAddr":"","userName":"","viewOrderId":""}],"pageIndex":0,"pageNum":0,"pageSize":0}
     * header : {"clientTraceNo":"20161220134523_61679303608034553481","hostTime":"20161220134550","hostTraceNo":"360091","returnCode":"0000","returnMessage":"交易成功","sessionId":"ced107c3-a32d-4ff2-9afe-fd99eb630996","submitTime":"20161220134523","transType":"T201","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public static class BodyBean {
        /**
         * count : 0
         * orders : [{"cancelDate":"","cancelPeason":"","cancelTime":"","channelId":"","channelName":"","channelOrderId":"","comfirmDate":"","comfirmTime":"","distance":0,"expectDate":"","expectTime":"","invoiceTitle":"","invoiceType":0,"orderAmount":0,"orderDate":"","orderId":"123","orderSeq":0,"orderTime":"","payType":0,"status":0,"userAddr":"","userName":"","viewOrderId":""}]
         * pageIndex : 0
         * pageNum : 0
         * pageSize : 0
         */

        public int count;
        public int              pageIndex;
        public int              pageNum;
        public int              pageSize;

        public BodyBean(int count, int pageIndex, int pageNum, int pageSize, List<OrdersBean> orders) {
            this.count = count;
            this.pageIndex = pageIndex;
            this.pageNum = pageNum;
            this.pageSize = pageSize;
            this.orders = orders;
        }

        @Override
        public String toString() {
            return "{" +
                    "count:" + count +
                    ", pageIndex:" + pageIndex +
                    ", pageNum:" + pageNum +
                    ", pageSize:" + pageSize +
                    ", orders:" + orders +
                    '}';
        }

        public List<OrdersBean> orders;

        public static class OrdersBean {
            /**
             * cancelDate :
             * cancelPeason :
             * cancelTime :
             * channelId :
             * channelName :
             * channelOrderId :
             * comfirmDate :
             * comfirmTime :
             * distance : 0
             * expectDate :
             * expectTime :
             * invoiceTitle :
             * invoiceType : 0
             * orderAmount : 0
             * orderDate :
             * orderId : 123
             * orderSeq : 0
             * orderTime :
             * payType : 0
             * status : 0
             * userAddr :
             * userName :
             * viewOrderId :
             */

            public String cancelDate;
            public String cancelPeason;
            public String cancelTime;
            public String channelId;
            public String channelName;
            public String channelOrderId;
            public String comfirmDate;
            public String comfirmTime;
            public String    distance;
            public String expectDate;
            public String expectTime;
            public String invoiceTitle;
            public int    invoiceType;
            public int    orderAmount;
            public String orderDate;
            public String orderId;
            public int    orderSeq;
            public String orderTime;
            public String    payType;
            public int    status;
            public String userAddr;
            public String userName;
            public String userPhone;
            public String viewOrderId;
            public String sendImmediately;//是否立即送达0---非立即送达，1----立即送达
            public OrdersBean(String cancelDate, String cancelPeason,
                              String cancelTime, String channelId,
                              String channelName, String channelOrderId,
                              String comfirmDate, String comfirmTime, String distance,
                              String expectDate, String expectTime, String invoiceTitle,
                              int invoiceType, int orderAmount, String orderDate,
                              String orderId,
                              int orderSeq, String orderTime,
                              String payType, int status, String userAddr,
                              String userName, String userPhone, String viewOrderId,String sendImmediately) {
                this.cancelDate = cancelDate;
                this.cancelPeason = cancelPeason;
                this.cancelTime = cancelTime;
                this.channelId = channelId;
                this.channelName = channelName;
                this.channelOrderId = channelOrderId;
                this.comfirmDate = comfirmDate;
                this.comfirmTime = comfirmTime;
                this.distance = distance;
                this.expectDate = expectDate;
                this.expectTime = expectTime;
                this.invoiceTitle = invoiceTitle;
                this.invoiceType = invoiceType;
                this.orderAmount = orderAmount;
                this.orderDate = orderDate;
                this.orderId = orderId;
                this.orderSeq = orderSeq;
                this.orderTime = orderTime;
                this.payType = payType;
                this.status = status;
                this.userAddr = userAddr;
                this.userName = userName;
                this.userPhone = userPhone;
                this.viewOrderId = viewOrderId;
                this.sendImmediately = sendImmediately;

            }

            @Override
            public String toString() {
                return "{" +
                        "cancelDate:'" + cancelDate + '\'' +
                        ", cancelPeason:'" + cancelPeason + '\'' +
                        ", cancelTime:'" + cancelTime + '\'' +
                        ", channelId:'" + channelId + '\'' +
                        ", channelName:'" + channelName + '\'' +
                        ", channelOrderId:'" + channelOrderId + '\'' +
                        ", comfirmDate:'" + comfirmDate + '\'' +
                        ", comfirmTime:'" + comfirmTime + '\'' +
                        ", distance:" + distance +
                        ", expectDate:'" + expectDate + '\'' +
                        ", expectTime:'" + expectTime + '\'' +
                        ", invoiceTitle:'" + invoiceTitle + '\'' +
                        ", invoiceType:" + invoiceType +
                        ", orderAmount:" + orderAmount +
                        ", orderDate:'" + orderDate + '\'' +
                        ", orderId:'" + orderId + '\'' +
                        ", orderSeq:" + orderSeq +
                        ", orderTime:'" + orderTime + '\'' +
                        ", payType:" + payType +
                        ", status:" + status +
                        ", userAddr:'" + userAddr + '\'' +
                        ", userName:'" + userName + '\'' +
                        ", userPhone:'" + userPhone + '\'' +
                        ", viewOrderId:'" + viewOrderId + '\'' +
                        ", sendImmediately:'" + sendImmediately + '\'' +
                        '}';
            }
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo : 20161220134523_61679303608034553481
         * hostTime : 20161220134550
         * hostTraceNo : 360091
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId : ced107c3-a32d-4ff2-9afe-fd99eb630996
         * submitTime : 20161220134523
         * transType : T201
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
                          String returnCode, String returnMessage,
                          String sessionId, String submitTime,
                          String transType, String version) {
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
