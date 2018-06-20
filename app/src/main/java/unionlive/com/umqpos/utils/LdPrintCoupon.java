package unionlive.com.umqpos.utils;

import android.text.TextUtils;

import com.landicorp.android.eptapi.device.Printer;

import org.greenrobot.eventbus.EventBus;

import unionlive.com.umqpos.bean.CouponPrintMsg;
import unionlive.com.umqpos.bean.CouponTdCollectMsg;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.event.PrinterErrorEvent;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/26 0:31
 * @describe $这里是单独给验券提供的打印方法
 */

public class LdPrintCoupon<T> extends Printer.Progress{
    private Printer                 printer;
    private T                  t;

    public void setT(T t) {
        this.t = t;
    }

    public LdPrintCoupon(Printer printer) {
        this.printer = printer;
    }

    //构造打印过程
    @Override
    public void doPrint(Printer printer) throws Exception {
        printer = LdPrintCoupon.this.printer;
        int status = printer.getStatus();
        TitleInfomation.printStatus = status;
        EventBus.getDefault().postSticky(new PrinterErrorEvent(printer.getStatus(), false,false));
        Printer.Format format = new Printer.Format();
        if (t instanceof CouponTdCollectMsg) {
            format.setHzScale(Printer.Format.HZ_SC2x2);
            format.setHzSize(Printer.Format.HZ_DOT16x16);
            printer.setFormat(format);
            CouponTdCollectMsg tdCollectMsg = (CouponTdCollectMsg)t;
            printer.printMid("交易汇总单\n");
            format.setHzScale(Printer.Format.HZ_SC1x1);
            format.setHzSize(Printer.Format.HZ_DOT24x24);
            format.setYSpace(4);
            printer.setFormat(format);
            printer.printText(" 商 户 名: " + tdCollectMsg.mercName + "\n");
            printer.printText(" 商 户 号: " + tdCollectMsg.mercId + "\n");
            printer.printText(" 终 端 号: " + tdCollectMsg.devicesSN + "\n");
            printer.printText(" 交易日期: " + tdCollectMsg.hostTime + "\n");
            printer.printText(" 打印时间: " + tdCollectMsg.printTime + "\n");
            printer.printText(" 验券总数: " + tdCollectMsg.count + "笔\n");


        }
        if (t instanceof CouponPrintMsg) {
            format.setHzScale(Printer.Format.HZ_SC2x2);
            format.setHzSize(Printer.Format.HZ_DOT16x16);
            printer.setFormat(format);
            CouponPrintMsg printMsg = (CouponPrintMsg)t;
            printer.printMid(printMsg.title + "\n");
            format.setHzScale(Printer.Format.HZ_SC1x1);
            format.setHzSize(Printer.Format.HZ_DOT24x24);
            format.setYSpace(4);
            printer.setFormat(format);
            printer.printMid("（商户存根）" + "\n");
            if (!TextUtils.isEmpty(printMsg.mercName)) {
                printer.printText(" 商 户 名: " + printMsg.mercName + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.mercId)) {
                printer.printText(" 商 户 号: " + printMsg.mercId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.termId)) {
                printer.printText(" 终端编号: " + printMsg.termId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.shopOperId)) {
                printer.printText(" 操作员编号: " + printMsg.shopOperId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.transName)) {//交易名称
                printer.printText(" 交易类型: " + printMsg.transName + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.prodId)) {
                printer.printText(" 产品编号: " + printMsg.prodId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.batchNum)) {//
                printer.printText(" 批 次 号: " + printMsg.batchNum + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.certificNum)) {//
                printer.printText(" 凭 证 号: " + printMsg.certificNum + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.authCode)) {
                printer.printText(" 授 权 码: " + printMsg.authCode + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.transTime)) {
                printer.printText(" 交易时间: " + printMsg.transTime + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.hostTraceNo)) {
                printer.printText(" 参 考 号: " + printMsg.hostTraceNo + "\n");
            }
            if (!printMsg.flag) {
                printer.printMid(" 重打印\n");
            }
            if (!TextUtils.isEmpty(printMsg.prodName)) {
                printer.printText(" 礼券名称: " + printMsg.prodName + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.expireDate)) {
                printer.printText(" 有 效 期: " + printMsg.expireDate + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.transNum)) {
                printer.printText(" 券  号: " + printMsg.transNum + "\n");
            }

            if (!TextUtils.isEmpty(printMsg.amount)) {
                printer.printText(" 验券次数: " + printMsg.amount + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.prodDesc)) {//描述信息
                printer.printText("-------------------------------\n");
                printer.printText(" " + printMsg.prodDesc + "\n");
            }

            printer.printText("-------------------------------\n");
            printer.printText(" 本人确认此次交易: \n\n");
            printer.printText(" 签  名: \n\n");
            printer.printText("-------------------------------\n");
            //如果是验券交易，则有广告，如果是查询（重打印）,则没有广告
            if (printMsg.flag) {
                if (!TextUtils.isEmpty(printMsg.ad)) {
                    printer.printText(" " + printMsg.amount + "\n");//打印广告
                    printer.printText("-------------------------------\n");

                }
            }
            printer.printText(" umqpos - " + printMsg.versionCode + "\n");
            printer.printText(" " + DevicesInfoUtil.showSN2Tickets() + "\n\n\n\n");

            ///*******************************************************************/
            format.setHzScale(Printer.Format.HZ_SC2x2);
            format.setHzSize(Printer.Format.HZ_DOT16x16);
            printer.setFormat(format);
            printer.printMid(printMsg.title + "\n");
            format.setHzScale(Printer.Format.HZ_SC1x1);
            format.setHzSize(Printer.Format.HZ_DOT24x24);
            format.setYSpace(4);
            printer.setFormat(format);
            printer.printMid("（持卡人存根）" + "\n");
            if (!TextUtils.isEmpty(printMsg.mercName)) {
                printer.printText(" 商 户 名: " + printMsg.mercName + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.mercId)) {
                printer.printText(" 商 户 号: " + printMsg.mercId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.termId)) {
                printer.printText(" 终端编号: " + printMsg.termId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.shopOperId)) {
                printer.printText(" 操作员编号: " + printMsg.shopOperId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.transName)) {//交易名称
                printer.printText(" 交易类型: " + printMsg.transName + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.prodId)) {
                printer.printText(" 产品编号: " + printMsg.prodId + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.batchNum)) {//
                printer.printText(" 批 次 号: " + printMsg.batchNum + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.certificNum)) {//
                printer.printText(" 凭 证 号: " + printMsg.certificNum + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.authCode)) {
                printer.printText(" 授 权 码: " + printMsg.authCode + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.transTime)) {
                printer.printText(" 交易时间: " + printMsg.transTime + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.hostTraceNo)) {
                printer.printText(" 参 考 号: " + printMsg.hostTraceNo + "\n");
            }
            if (!printMsg.flag) {
                printer.printMid(" 重打印\n");
            }
            if (!TextUtils.isEmpty(printMsg.prodName)) {
                printer.printText(" 礼券名称: " + printMsg.prodName + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.expireDate)) {
                printer.printText(" 有 效 期: " + printMsg.expireDate + "\n");
            }
            if (!TextUtils.isEmpty(printMsg.transNum)) {
                printer.printText(" 券  号: " + printMsg.transNum + "\n");
            }

            if (!TextUtils.isEmpty(printMsg.amount)) {
                printer.printText(" 验券次数: " + printMsg.amount + "\n");
                printer.printText("-------------------------------\n");
            }
            if (!TextUtils.isEmpty(printMsg.prodDesc)) {//描述信息

                printer.printText(" " + printMsg.prodDesc + "\n");
                printer.printText("-------------------------------\n");
            }
            //如果是验券交易，则有广告，如果是查询（重打印）,则没有广告
            if (printMsg.flag) {
                if (!TextUtils.isEmpty(printMsg.ad)) {

                    printer.printText(" " + printMsg.amount + "\n");//打印广告
                    printer.printText("-------------------------------\n");

                }
            }
            printer.printText(" umqpos - " + printMsg.versionCode + "\n");
            printer.printText(" " + DevicesInfoUtil.showSN2Tickets() + "\n");


        }

        printer.feedLine(4);
    }

    @Override
    public void onFinish(int arg0) {    // 打印结束
    }

    @Override
    public void onCrash() {     // 设备服务崩溃处理
    }
}
