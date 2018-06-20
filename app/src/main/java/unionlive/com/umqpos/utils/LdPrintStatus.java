package unionlive.com.umqpos.utils;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import org.greenrobot.eventbus.EventBus;

import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.event.PrinterErrorEvent;

import static com.landicorp.android.eptapi.device.Printer.ERROR_HARDERR;
import static com.landicorp.android.eptapi.device.Printer.ERROR_OVERHEAT;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDED;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERJAM;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/26 0:31
 * @describe $这里得到联迪设备的打印状态
 */

public class LdPrintStatus extends Printer.Progress{
    private Printer                 printer;


    private LdPrintStatus(Printer printer) {
        this.printer = printer;
    }
    private static LdPrintStatus getInstance(){
        LdPrintStatus ldPrintStatus = null;
        if (ldPrintStatus == null) {
            ldPrintStatus = new LdPrintStatus(Printer.getInstance());
//            System.out.println("LdPrintStatus_创建了" + Fields.count + "次对象");
//            Fields.count++;
        }
        return ldPrintStatus;
    }
    public static void startPrint(){
        try {
            getInstance().start();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    //构造打印过程
    @Override
    public void doPrint(Printer printer) throws Exception {//仅仅刷新状态栏，不做任何操作
        printer = LdPrintStatus.this.printer;
        TitleInfomation.printStatus = printer.getStatus();//得到联迪设备的打印机状态
//        boolean isPrint = false;
        EventBus.getDefault().postSticky(new PrinterErrorEvent(printer.getStatus(), false,false));

    }

    @Override
    public void onFinish(int arg0) {    // 打印结束
    }

    @Override
    public void onCrash() {     // 设备服务崩溃处理
    }
    //247
    public static String getStatus(int status) {
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
}
