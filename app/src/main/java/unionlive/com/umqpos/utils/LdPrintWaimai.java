package unionlive.com.umqpos.utils;


import android.text.TextUtils;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.utils.PausableHandler;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.entitys.out_waimai.DetialResponse;
import unionlive.com.umqpos.entitys.out_waimai.QueryShopNameResponse;
import unionlive.com.umqpos.event.PrinterErrorEvent;

import static com.landicorp.android.eptapi.device.Printer.ERROR_HARDERR;
import static com.landicorp.android.eptapi.device.Printer.ERROR_OVERHEAT;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDED;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERJAM;

/**
 * 这里是单独给外卖提供的打印方法
 */
public class LdPrintWaimai<T> extends Printer.Progress {
    private Printer printer;
    private boolean  mIsReprint=false;
    private T       t;
    private String  mViewOrderId;
    private String  mChannelName;
    private String  mOrderTime;
    private String  mOrderId;
    private int     mOrderAmount;
    private String  mPayType;
    private String  mRemark;
    private int     mInvoiceType;
    private String  mUserName;
    private String  mUserPhone;
    private String  mUserAddr;
    private String  mPrintInfo;
    private int     mDiscountAmount;
    private String  mDiscountMoney;
    private String  mOrderAmountMoney;
    private String  mInvoiceTitle;
    private int     mOrderSeq;
    private String     mVersionCode;
    private String  mDevicesSN;
    private String  shopId;
    private String  mDeliveryAmount;
    private String  mPackageAmount;
    private String  mSendImmediately;

    public void setT(T t) {
        this.t = t;
    }

    public static LdPrintWaimai getInstance() {
        LdPrintWaimai ldPrintWaimai = null;
        if (ldPrintWaimai == null) {
            ldPrintWaimai = new LdPrintWaimai(Printer.getInstance());
        }
        return ldPrintWaimai;
    }

    private LdPrintWaimai(Printer printer) {
        this.printer = printer;
    }

    public LdPrintWaimai(PausableHandler handler) {
        super(handler);
    }

    //构造打印过程
    @Override
    public void doPrint(Printer printer) throws Exception {
        printer = LdPrintWaimai.this.printer;
        int status = printer.getStatus();
        TitleInfomation.printStatus = status;
        if (status == Printer.ERROR_NONE || status == Printer.ERROR_BUSY) {
            EventBus.getDefault().postSticky(new PrinterErrorEvent(TitleInfomation.printStatus, mIsReprint, true));
            Printer.Format format = new Printer.Format();
            if (t instanceof DetialResponse) {
                printer.setAutoTrunc(false);
                DetialResponse detialResponse = (DetialResponse) this.t;
                DetialResponse.BodyBean bodyData = detialResponse.body;
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                initPrintData(bodyData);
                printer.printMid("---------#" + mOrderSeq + mChannelName + "---------\n");
                //            format.setHzScale(Printer.Format.HZ_SC2x2);
                //            format.setHzSize(Printer.Format.HZ_DOT16x16);
                //            printer.setFormat(format);
                printer.printMid(Fields.shopName + "\n");
                //            printer.printMid("(商户存根)\n");

                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                printer.printText("商户编号: " + TitleInfomation.shopId + "\n");
                printer.printText("下单时间: " + mOrderTime + "\n");
                printer.printText("订 单 号: " + mOrderId + "\n");
                printer.printText("--------------------------------\n");
                printer.printText(getRsultStr("商品名称", 17) + getRsultStr("数量", 9) + getRsultStr("金额", 6) + "\n");

                List<DetialResponse.BodyBean.OrderProdsBean> orderProds = bodyData.orderProds;
                for (int i = 0; i < orderProds.size(); i++) {
                    String prodName = orderProds.get(i).prodName;//商品名称
                    String prodNum = String.valueOf(orderProds.get(i).prodNum);//商品数量
                    String prodPrice = getFormatMoney(orderProds.get(i).prodAmount);//商品金额
                    printer.printText(getRsultStr(prodName, 17)
                            + getRsultStr("x " + prodNum, 7)
                            + getRsultStr(prodPrice, 6)
                            + "\n");
                }
                printer.printText(getRsultStr("餐盒费: ", 24)
                        + getResult(String.valueOf(mPackageAmount), 2, 1) + "\n");
                printer.printText(getRsultStr("配送费: ", 24)
                        + getResult(String.valueOf(mDeliveryAmount), 2, 1) + "\n");
                printer.printText(getRsultStr("优惠: ", 23)
                        + getResult(String.valueOf("-" + mDiscountMoney), 2, 1)
                        + "\n");
                printer.printText(getRsultStr("总  计: ", 12)
                        + getRsultStr(mOrderAmountMoney, 8)
                        + getRsultStr((mPayType == "0" ? "线上支付" : "货到付款"), 6) + "\n");

                printer.printText("--------------------------------\n");
                printer.printText("期望配送时间:" + mSendImmediately + "\n");
                printer.printText("备注: " + mRemark + "\n");
                if (mIsReprint) {
                    printer.printMid("----------此单为重打印----------\n");
                }
                //            printer.printText("发票信息: " + (mInvoiceType == 0 ? "不需要" : "需要") + "\n");
                if (mInvoiceType == 1) {
                    if (!TextUtils.isEmpty(mInvoiceTitle)) {
                        //                    getExchangeLine();
                        printer.println("发票抬头: " + mInvoiceTitle + "\n");
                    }
                }
                printer.printText(mUserName + "\t\t" + mUserPhone + "\n");
                int addrCount = getCharCount(mUserAddr);//得到收货人地址的字节数
                printer.println(mUserAddr);
                printer.printMid("--------#" + mOrderSeq + mChannelName + "End--------\n");
                printer.printText(mPrintInfo + "\n");
                printer.printText("打印时间: " + TimeHelper.getTicksPrintTime() + "\n\n\n");
                // /*********************************************个人存根**************************************************/
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                initPrintData(bodyData);
                printer.printMid("---------#" + mOrderSeq + mChannelName + "---------\n");

                //            format.setHzScale(Printer.Format.HZ_SC2x2);
                //            format.setHzSize(Printer.Format.HZ_DOT16x16);
                //            printer.setFormat(format);
                printer.printMid(Fields.shopName + "\n");
                //            printer.printMid("(个人存根)\n");

                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                printer.printText("商户编号: " + TitleInfomation.shopId + "\n");
                printer.printText("下单时间: " + mOrderTime + "\n");
                printer.printText("订 单 号: " + mOrderId + "\n");
                printer.printText("--------------------------------\n");
                printer.printText(getRsultStr("商品名称", 17) + getRsultStr("数量", 9) + getRsultStr("金额", 6) + "\n");

                for (int i = 0; i < orderProds.size(); i++) {
                    String prodName = orderProds.get(i).prodName;//商品名称
                    String prodNum = String.valueOf(orderProds.get(i).prodNum);//商品数量
                    String prodPrice = getFormatMoney(orderProds.get(i).prodAmount);//商品金额
                    printer.printText(getRsultStr(prodName, 17)
                            + getRsultStr("x " + prodNum, 7)
                            + getRsultStr(prodPrice, 6)
                            + "\n");
                }
                printer.printText(getRsultStr("餐盒费: ", 24)
                        + getResult(String.valueOf(mPackageAmount), 2, 1) + "\n");
                printer.printText(getRsultStr("配送费: ", 24)
                        + getResult(String.valueOf(mDeliveryAmount), 2, 1) + "\n");
                printer.printText(getRsultStr("优惠: ", 23)
                        + getResult(String.valueOf("-" + mDiscountMoney), 2, 1)
                        + "\n");
                printer.printText(getRsultStr("总  计: ", 12)
                        + getRsultStr(mOrderAmountMoney, 8)
                        + getRsultStr((mPayType == "0" ? "线上支付" : "货到付款"), 6) + "\n");

                printer.printText("--------------------------------\n");
                printer.printText("期望配送时间:" + mSendImmediately + "\n");
                printer.printText("备注: " + mRemark + "\n");
                if (mIsReprint) {
                    printer.printMid("----------此单为重打印----------\n");
                }
                //            printer.printText("发票信息: " + (mInvoiceType == 0 ? "不需要" : "需要") + "\n");
                if (mInvoiceType == 1) {
                    if (!TextUtils.isEmpty(mInvoiceTitle)) {
                        //                    getExchangeLine();
                        printer.println("发票抬头: " + mInvoiceTitle + "\n");
                    }
                }
                printer.printText(mUserName + "\t\t" + mUserPhone + "\n");
                printer.println(mUserAddr);
                printer.printMid("--------#" + mOrderSeq + mChannelName + "End--------\n");
                printer.printText(mPrintInfo + "\n");
                printer.printText("打印时间: " + TimeHelper.getTicksPrintTime() + "\n");

            }


            if (t instanceof QueryShopNameResponse) {
                QueryShopNameResponse nameResponse = (QueryShopNameResponse) this.t;
                String shopName = nameResponse.body.shopName;
                String addr = nameResponse.body.addr;
                printer.printMid(shopName + "\n");
                printer.printText("门店编号: " + shopId + "\n");
                printer.printText("地址: " + addr + "\n");
                printer.printText("--------------------------------\n");
                printer.printText("当前软件版本: " + mVersionCode + "\n");
                printer.printText("设备终端编号: " + mDevicesSN + "\n");
                printer.printText("优麦圈服务热线: " + "4008-8848-20\n");
                printer.printText("www.umq.me\n");
                printer.printMid("---------当前为打印测试---------\n");
            }
            printer.feedLine(4);
        } else {
            //            System.out.println("当前的打印机状态为:"+getStatus(printer.getStatus()));
            EventBus.getDefault().postSticky(new PrinterErrorEvent(printer.getStatus(), mIsReprint, true));
        }

    }
    @Override
    public void onFinish(int code) {    // 打印结束
        /**
         * The result is fine.
         */
        TitleInfomation.printStatus = code;
        OrdersDataInfo.getInstance().clear();//存放数据前先清空集合
        OrdersDataInfo.getInstance().put(mOrderId, t);//将订单信息存放在集合中
        EventBus.getDefault().postSticky(new PrinterErrorEvent(code,mIsReprint, true));//通知baseActivity
    }

    @Override
    public void onCrash() {     // 设备服务崩溃处理
    }

    public String getErrorDescription(int code) {
        switch (code) {
            case Printer.ERROR_PAPERENDED:
                return "打印缺纸";
            case Printer.ERROR_HARDERR:
                return "打印机硬件错误";
            case Printer.ERROR_OVERHEAT:
                return "打印头过热";
            case Printer.ERROR_BUFOVERFLOW:
                return "缓冲模式下所操作的位置超出范围";
            case Printer.ERROR_LOWVOL:
                return "低压保护";
            case Printer.ERROR_PAPERENDING:
                return "纸张将用尽";
            case Printer.ERROR_MOTORERR:
                return "打印机芯故障";
            case Printer.ERROR_PENOFOUND:
                return "自动定位没找到对其位置";
            case Printer.ERROR_PAPERJAM:
                return "卡纸";
            case Printer.ERROR_NOBM:
                return "B没有找到黑标";
            case Printer.ERROR_BUSY:
                return "打印机处于忙碌状态";
            case Printer.ERROR_BMBLACK:
                return "黑标探测器检测到黑色信号";
            case Printer.ERROR_WORKON:
                return "打印机电源处于打开状态";
            case Printer.ERROR_LIFTHEAD:
                return "打印头抬起";
            case Printer.ERROR_LOWTEMP:
                return "低压保护";
        }
        return "未知打印异常 (" + code + ")";
    }

    //247
    public String getStatus(int status) {
        String printError;
        switch (status) {
            case ERROR_PAPERENDED:
                printError = "打印缺纸";
                break;
            case ERROR_HARDERR:
                printError = "打印机硬件错误";
                break;
            case ERROR_OVERHEAT:
                printError = "打印头过热";
                break;
            case ERROR_PAPERJAM:
                printError = "打印机卡纸";
                break;
            default:
                printError = "打印机异常:" + status;
                break;
        }
        return printError;
    }

    /**
     * 获取支付总金额
     * "¥"+String.format("%.2f", mOrders.get(position).orderAmount / 100.00);
     *
     * @return
     * @param6
     */
    private String getFormatMoney(int num) {
        return String.format("%.2f", num / 100.00);
    }

    /**
     * 获取支付总金额
     * "¥"+String.format("%.2f", mOrders.get(position).orderAmount / 100.00);
     *
     * @param
     * @return
     */
    private String getFormatMoney(String num) {
        int money = Integer.parseInt(num);
        return String.format("%.2f", money / 100.00);
    }

    /**
     * 小票的每一行可以显示32个字符,按照18-----8------6平分
     *
     * @param str 获取字符的个数(中文x2)
     * @return
     */
    public int getCharCount(String str) {
        int count = 0;
        String regex = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            count++;
        }
        count = str.length() + count;
        //得到的count为汉字的个数,那么其他字符的长度为  str1.length-count,转化为字符的长度为str1.length-count+count*2=str1.length+count
        return count;
    }
    /**
     * 获取对应的字符串
     *
     * @param str
     * @param len 越大，则距离后面的字符距离越大
     * @return
     */
    public String getRsultStr(String str, int len) {
        int charCount = getCharCount(str);
        if (len < charCount) {
            len = charCount;
        }
        int i = len - charCount;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        for (int j = 0; j < i; j++) {
            sb.append("\t");
        }
        return sb.toString();
    }


    private void initPrintData(DetialResponse.BodyBean bodyData) {
        //展示的订单编号
        mViewOrderId = bodyData.viewOrderId;
        mOrderSeq = bodyData.orderSeq;
        //外卖的渠道
        mChannelName = bodyData.channelName;
        //            Fields.ORDER_WAIMAI_SHANGHU_NAME;//商户的名称
        //下单时间
        mOrderTime = bodyData.orderDate + bodyData.orderTime;
        mOrderTime = TimeHelper.orderTime(mOrderTime);//格式化下单时间
        //订单编号
        mOrderId = bodyData.orderId;
        //优惠总金额
        mDiscountMoney = getFormatMoney(bodyData.discountAmount);
        //配送费
        mDeliveryAmount = getFormatMoney(bodyData.deliveryAmount);
        //餐盒费
        mPackageAmount = getFormatMoney(bodyData.packageAmount);
        //订单总金额
        mOrderAmountMoney = getFormatMoney(bodyData.orderAmount);
        //支付类型；0：线上支付；1：货到付款
        mPayType = bodyData.payType;
        //是否立即配送
        mSendImmediately = getSendImmediateTime(bodyData);
        //订单的备注
        mRemark = bodyData.remark;
        //是否需要发票  0不需要 1需要
        mInvoiceType = bodyData.invoiceType;
        //发票抬头
        mInvoiceTitle = bodyData.invoiceTitle;
        //订单用户姓名
        mUserName = bodyData.userName;
        //号码
        mUserPhone = bodyData.userPhone;
        //地址信息
        mUserAddr = bodyData.userAddr;
        //打印结尾信息
        mPrintInfo = bodyData.printInfo;
    }

    private String getSendImmediateTime(DetialResponse.BodyBean bodyData) {
        if (!TextUtils.isEmpty(bodyData.expectDate) && !TextUtils.isEmpty(bodyData.expectTime)) {
            return getFormartTime(bodyData.expectDate, bodyData.expectTime);//期望送达时间.setText(body.);
        } else {
            if (bodyData.sendImmediately.equals("0")) {
                return "非立即送达";
            } else {
                return "立即送达";
            }
        }
    }

    /**
     * 送货时间
     *
     * @param
     * @return
     */
    private String getFormartTime(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "--";
        }
        String orderDate = str1 + str2;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf2.format(d);
    }




    public void setIsReprint(boolean isReprint) {
        mIsReprint = isReprint;
    }


    /**
     * @param str  需要处理的字符串
     * @param size 分配给此字符串的长度
     * @param type 字符串的类型,如果是中文则为2,否则为1
     * @return
     */
    public String getResult(String str, int size, int type) {
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        if (size < str.length()) {
            size = str.length() * type;
        }
        int length = size - str.length() * type;
        for (int j = 0; j < length; j++) {
            sb.append("\t");
        }

        return sb.toString();
    }

    public void setVersionCode(String versionCode) {
        mVersionCode = versionCode;
    }

    public void setDevicesSN(String devicesSN) {
        mDevicesSN = devicesSN;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


}