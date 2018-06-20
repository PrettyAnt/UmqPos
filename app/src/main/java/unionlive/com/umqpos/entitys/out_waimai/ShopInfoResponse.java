package unionlive.com.umqpos.entitys.out_waimai;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/8 14:35
 * @describe 商铺基本信息查询
 */

public class ShopInfoResponse {

    /**
     * header : {"version":"1.0","transType":"T101","submitTime":"20161208143024","sessionId":"","clientTraceNo":"","hostTime":"20161208143049","hostTraceNo":"322971","returnCode":"6014","returnMessage":"无结果"}
     * body : {}
     */

    public HeaderBean header;
    public BodyBean body;

    public ShopInfoResponse(HeaderBean header, BodyBean body) {
        this.header = header;
        this.body = body;
    }

    public static class HeaderBean {
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

        /**
         * version : 1.0
         * transType : T101
         * submitTime : 20161208143024
         * sessionId :
         * clientTraceNo :
         * hostTime : 20161208143049
         * hostTraceNo : 322971
         * returnCode : 6014
         * returnMessage : 无结果
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
        public String shopId;//商户编号
        public String shopName;//商户名称
        public String tel;//商户电话
        public String addr;//商户地址
        public String shopLogo;//商户logo
        public String contact;//商户联系人
        public String deliveryType;//商户配送方式
        public String customerManager;//客户经理姓名
        public String managerPhone;//客户经理电话

        public BodyBean(String shopId, String shopName,
                        String tel, String addr, String shopLogo,
                        String contact, String deliveryType,
                        String customerManager,
                        String managerPhone) {
            this.shopId = shopId;
            this.shopName = shopName;
            this.tel = tel;
            this.addr = addr;
            this.shopLogo = shopLogo;
            this.contact = contact;
            this.deliveryType = deliveryType;
            this.customerManager = customerManager;
            this.managerPhone = managerPhone;
        }

        @Override
        public String toString() {
            return "{" +
                    "shopId:'" + shopId + '\'' +
                    ", shopName:'" + shopName + '\'' +
                    ", tel:'" + tel + '\'' +
                    ", addr:'" + addr + '\'' +
                    ", shopLogo:'" + shopLogo + '\'' +
                    ", contact:'" + contact + '\'' +
                    ", deliveryType:'" + deliveryType + '\'' +
                    ", customerManager:'" + customerManager + '\'' +
                    ", managerPhone:'" + managerPhone + '\'' +
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
