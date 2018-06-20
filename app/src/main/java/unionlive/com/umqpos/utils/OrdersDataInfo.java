package unionlive.com.umqpos.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import static com.landicorp.android.eptapi.device.Printer.ERROR_HARDERR;
import static com.landicorp.android.eptapi.device.Printer.ERROR_OVERHEAT;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDED;
import static com.landicorp.android.eptapi.device.Printer.ERROR_PAPERJAM;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2017/2/14 15:12
 * @describe ${TODO}
 */

public class OrdersDataInfo {
    //    = new HashMap<>();
    private static HashMap<String, Object> orderData = null;
    //用来存放订单号
    public static HashMap getInstance() {
        if (orderData == null) {
            orderData = new HashMap<>();
        }
        return orderData;
    }
    /**
     * 得到订单号
     * @return
     */
    public static String getOrderId() {
        HashMap hashMap = getInstance();
        Set set = hashMap.keySet();
        String orderId = "";
        if (set != null) {
            Iterator iterator = set.iterator();

            while (iterator.hasNext()) {
                orderId = (String) iterator.next();//得到订单号
                Object data = hashMap.get(orderId);//得到订单信息

            }
        }
        return orderId;
    }

    /**
     * 得到订单信息
     * @return
     */
    public static Object getOrderData() {
        HashMap hashMap = getInstance();
        Set set = hashMap.keySet();
        String orderId = "";
        Object data = null;
        if (set != null) {
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                orderId = (String) iterator.next();//得到订单号
                 data = hashMap.get(orderId);//得到订单信息

            }
        }
        return data;
    }

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
