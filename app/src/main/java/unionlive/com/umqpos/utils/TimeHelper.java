package unionlive.com.umqpos.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/7 21:14
 * @describe $获取当前时间  因为这里获取的时间是系统时间,不是实时的网络时间,实际
 * 情况最好肯定是网络时间了,但由于在联迪设备中通过LoctionManager获取不到时间,在android
 * 设备中正常获得.具体问题未知,后期再更新吧
 * <p>
 * 考虑到通过位置服务获取时间会有不少代码,为了代码明了,单独放在一个类里面吧
 */

public class TimeHelper {
    /**
     * 获取交易提交时间
     *
     * @return
     */
    public static String getSubmitTime() {
        long l = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(l);
        return sdf.format(date);
    }

    /**
     * 获取yyyyMMdd的时间
     * @return
     */
    public static String currentDay() {//获取今天的时间
        long l = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(l);
        return sdf.format(date);
    }

    public static String exceptDay(int i) {//获取开始的时间
        long l = System.currentTimeMillis();
        switch (i) {
            case 0://表示今天
                break;
            case 1:
                l = l - 86400000;//昨天
                break;
            case 2:
                l = l - 172800000;//前天
                break;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(l);
        return sdf.format(date);
    }

    /**
     * 获取yyyy-MM-dd HH:mm格式的时间
     * @return
     */
    public static String getPrintTime() {
        long l = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(l);
        return sdf.format(date);
    }

    /**
     * 获取yyyy-MM-dd HH:mm:ss格式的时间
     * @return
     */
    public static String getTicksPrintTime() {
        long l = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(l);
        return sdf.format(date);
    }

    public static String OrderTime(String orderDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }

    public static String transTime(String orderDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }

    /**
     * 下单时间   格式 ："yyyy-MM-dd HH:mm:ss"
     * @param orderDate
     * @return
     */
    public static String orderTime(String orderDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }
    public static String expireDate(String expireDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        Date d = null;
        try {
            d = sdf1.parse(expireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }

    /**
     * 将yyyyMMddHHmmss格式的时间转化为yyyyMMdd
     * @param orderDate
     * @return
     */
    public static String transTimeFormat(String orderDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        Date d = null;
        try {
            d = sdf1.parse(orderDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }

    /**
     *
     * @return
     */
    public static int daysOfTwo(String beforeDate) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date fDate = null;
        long l = System.currentTimeMillis();
        Date currentDate = new Date(l);
        try {
            fDate = sdf1.parse(beforeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(currentDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    /**
     * 得到批次号
     * @param submitTime
     * @return
     */
    public static String getBatchNum(String submitTime) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyMMdd");
        Date d = null;
        try {
            d = sdf1.parse(submitTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }

    /**
     * 得到凭证号
     * @param submitTime
     * @return
     */
    public static String getCertificNum(String submitTime) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmmss");
        Date d = null;
        try {
            d = sdf1.parse(submitTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //        Date date = new Date(Long.parseLong(orderDate));
        return sdf2.format(d);
    }
}
