package unionlive.com.umqpos.entitys.out_waimai;

import java.util.List;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 17:52
 * @describe 订单详情查询 // FixMe
 */

public class DetialResponse {
    /**
     * body : {"channelId":"","orderId":"","orderProds":[{"prodAmount":0,"prodId":"","prodName":"","prodNum":0,"prodPrice":0}]}
     * header : {"clientTraceNo":"","hostTime":"20161221205007","hostTraceNo":"735488","returnCode":"0000","returnMessage":"交易成功","sessionId":"","submitTime":"20161221205004","transType":"T106","version":"1.0"}
     */

    public BodyBean body;
    public HeaderBean header;

    public DetialResponse(BodyBean body, HeaderBean header) {
        this.body = body;
        this.header = header;
    }

    public static class BodyBean {
        public String orderId;//订单编号
        public String channelId;//渠道编号
        public String channelName;//渠道名称
        public String channelOrderId;//渠道订单号
        public String viewOrderId;//展示订单号
        public String orderDate;//订单日期
        public String orderTime;//订单时间
        public int orderAmount;//订单金额
        public String userName;//订单用户姓名
        public String userPhone;//用户手机号
        public String userAddr;//用户地址
        public String distance;//用户距离  //
        public int status;//订单状态
        public String sendImmediately;//是否立即送达
        public String expectDate;//期望送达日期
        public String expectTime;//期望送达时间
        public String deliveryAmount;//配送费
        public String packageAmount;//餐盒费
        public String discountAmount;//优惠总金额
        public int shopAmount;//商户实收金额
        // public int orderAmount;//订单总金额
        public String userAmount;//用户支付金额
        public String payType;//支付方式
        public int invoiceType;//是否需要发票
        public String invoiceTitle;//发票抬头
        public String deliveryType;//配送类型
        public String shipperName;//配送人姓名
        public String shipperPhone;//配送人电话
        public String deliveryDate;//配送日期
        public String deliveryTime;//配送时间
        public String cancelType;//取消订单类型
        public String cancelReason;//取消原因
        public String completeDate;//订单完成日期
        public String completeTime;//订单完成时间
        public String remark;//备注
        public int orderType;//订单类型
        public String comfirmDate;//确认接单日期
        public String comfirmTime;//确认接单时间
        public String logisticsRate;//物流承担费用
        public int orderSeq;//今日订单序号
        //        public int invoiceType;//是否需要发票
        //        public String invoiceTitle;//发票抬头


        public String cancelDate;//取消接单日期
        public String cancelTime;//取消接单时间
        public String printInfo;//打印结尾信息
        public List<OrderProdsBean> orderProds;

        public BodyBean(String orderId, String channelId, String channelName,
                        String channelOrderId, String viewOrderId, String orderDate,
                        String orderTime, int orderAmount, String userName, String userPhone,
                        String userAddr, String distance, int status, String sendImmediately, String expectDate,
                        String expectTime, String deliveryAmount, String packageAmount, String discountAmount, int shopAmount,
                        String userAmount, String payType, int invoiceType, String invoiceTitle, String deliveryType, String shipperName,
                        String shipperPhone, String deliveryDate, String deliveryTime, String cancelType, String cancelReason,
                        String completeDate, String completeTime, String remark, int orderType, String comfirmDate, String comfirmTime,
                        String logisticsRate, int orderSeq,
                        String cancelDate, String cancelTime, String printInfo, List<OrderProdsBean> orderProds) {
            this.orderId = orderId;
            this.channelId = channelId;
            this.channelName = channelName;
            this.channelOrderId = channelOrderId;
            this.viewOrderId = viewOrderId;
            this.orderDate = orderDate;
            this.orderTime = orderTime;
            this.orderAmount = orderAmount;
            this.userName = userName;
            this.userPhone = userPhone;
            this.userAddr = userAddr;
            this.distance = distance;
            this.status = status;
            this.sendImmediately = sendImmediately;
            this.expectDate = expectDate;
            this.expectTime = expectTime;
            this.deliveryAmount = deliveryAmount;
            this.packageAmount = packageAmount;
            this.discountAmount = discountAmount;
            this.shopAmount = shopAmount;
            this.userAmount = userAmount;
            this.payType = payType;
            this.invoiceType = invoiceType;
            this.invoiceTitle = invoiceTitle;
            this.deliveryType = deliveryType;
            this.shipperName = shipperName;
            this.shipperPhone = shipperPhone;
            this.deliveryDate = deliveryDate;
            this.deliveryTime = deliveryTime;
            this.cancelType = cancelType;
            this.cancelReason = cancelReason;
            this.completeDate = completeDate;
            this.completeTime = completeTime;
            this.remark = remark;
            this.orderType = orderType;
            this.comfirmDate = comfirmDate;
            this.comfirmTime = comfirmTime;
            this.logisticsRate = logisticsRate;
            this.orderSeq = orderSeq;
            this.cancelDate = cancelDate;
            this.cancelTime = cancelTime;
            this.printInfo = printInfo;
            this.orderProds = orderProds;
        }

        @Override
        public String toString() {
            return "{" +
                    "orderId:'" + orderId + '\'' +
                    ", channelId:'" + channelId + '\'' +
                    ", channelName:'" + channelName + '\'' +
                    ", channelOrderId:'" + channelOrderId + '\'' +
                    ", viewOrderId:'" + viewOrderId + '\'' +
                    ", orderDate:'" + orderDate + '\'' +
                    ", orderTime:'" + orderTime + '\'' +
                    ", orderAmount:" + orderAmount +
                    ", userName:'" + userName + '\'' +
                    ", userPhone:'" + userPhone + '\'' +
                    ", userAddr:'" + userAddr + '\'' +
                    ", distance:" + distance +
                    ", status:" + status +
                    ", sendImmediately:" + sendImmediately +
                    ", expectDate:'" + expectDate + '\'' +
                    ", expectTime:'" + expectTime + '\'' +
                    ", deliveryAmount:" + deliveryAmount +
                    ", packageAmount:" + packageAmount +
                    ", discountAmount:" + discountAmount +
                    ", shopAmount:" + shopAmount +
                    ", userAmount:" + userAmount +
                    ", payType:" + payType +
                    ", invoiceType:" + invoiceType +
                    ", invoiceTitle:'" + invoiceTitle + '\'' +
                    ", deliveryType:" + deliveryType +
                    ", shipperName:'" + shipperName + '\'' +
                    ", shipperPhone:'" + shipperPhone + '\'' +
                    ", deliveryDate:'" + deliveryDate + '\'' +
                    ", deliveryTime:'" + deliveryTime + '\'' +
                    ", cancelType:" + cancelType +
                    ", cancelReason:'" + cancelReason + '\'' +
                    ", completeDate:'" + completeDate + '\'' +
                    ", completeTime:'" + completeTime + '\'' +
                    ", remark:'" + remark + '\'' +
                    ", orderType:" + orderType +
                    ", comfirmDate:'" + comfirmDate + '\'' +
                    ", comfirmTime:'" + comfirmTime + '\'' +
                    ", logisticsRate:" + logisticsRate +
                    ", orderSeq:" + orderSeq +
                    ", cancelDate:'" + cancelDate + '\'' +
                    ", cancelTime:'" + cancelTime + '\'' +
                    ", printInfo:'" + printInfo + '\'' +
                    ", orderProds:" + orderProds +
                    '}';
        }

        public static class OrderProdsBean {
            /**
             * prodAmount : 0
             * prodId :
             * prodName :
             * prodNum : 0
             * prodPrice : 0
             */

            public int prodAmount;
            public String prodId;
            public String prodName;
            public int    prodNum;
            public int    prodPrice;

            public OrderProdsBean(int prodAmount, String prodId,
                                  String prodName, int prodNum, int prodPrice) {
                this.prodAmount = prodAmount;
                this.prodId = prodId;
                this.prodName = prodName;
                this.prodNum = prodNum;
                this.prodPrice = prodPrice;
            }

            @Override
            public String toString() {
                return "{" +
                        "prodAmount:" + prodAmount +
                        ", prodId:'" + prodId + '\'' +
                        ", prodName:'" + prodName + '\'' +
                        ", prodNum:" + prodNum +
                        ", prodPrice:" + prodPrice +
                        '}';
            }
        }
    }

    public static class HeaderBean {
        /**
         * clientTraceNo :
         * hostTime : 20161221205007
         * hostTraceNo : 735488
         * returnCode : 0000
         * returnMessage : 交易成功
         * sessionId :
         * submitTime : 20161221205004
         * transType : T106
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

        public HeaderBean(String clientTraceNo, String hostTime,
                          String hostTraceNo, String returnCode,
                          String returnMessage, String sessionId,
                          String submitTime, String transType, String version) {
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
