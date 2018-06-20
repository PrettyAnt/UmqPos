package unionlive.com.umqpos.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;
import com.landicorp.android.eptapi.utils.SystemInfomation;

import org.json.JSONException;
import org.json.JSONObject;

import cn.weipass.pos.sdk.impl.WeiposImpl;
import unionlive.com.umqpos.content.Fields;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 创建时间    2016/10/14 17:03
 * @describe ${ 这是统一设备管理类,里面包含绑定设备,解绑设备,获取设备SN等方法
 * 进入设备前,需要对设备类型进行进行判断,在对相应的设备进行绑定,否则会出现异常!!!
 * 这里必须先调用DevicesInfoUtil.bindDevicesInfo(Context context)方法进行绑定设备
 * }
 */

public class DevicesInfoUtil {

    public static  String  appId;
    public static  int     appVersionNo;//应用版本序号
    public static  String  appVersion;//应用版本号
    public static  String  deviceId;//设备的唯一编号 // FIXMe
    public static  String  deviceType;    //设备类型 01表示PC； 02表示POS；03表示手机；04表示平板电脑；99未知设备
    public static  String  deviceOs;//设备操作系统
    public static  String  deviceOSVersion;//设备操作系统版本号
    public static  String  deviceModel;//设备型号 如:iphone 4s
    private static Context mContext;

    public DevicesInfoUtil(Context context) {
        this.mContext = context;
    }

    /**
     * 应用编号
     *
     * @return
     */
    public String getAppId() {
        return "11800107";//Todo
    }

    /**
     * @return 应用版本序号
     */
    public int getAppVersionNo() {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return -1;
        }
    }

    /**
     * @return 应用版本号
     */
    public String getAppVersion() {
        PackageManager manager = mContext.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "0.0.0";
        }

    }

    /**
     * @return 设备编号
     */
    public String getDeviceId() {
        if (TextUtils.isEmpty(deviceId)) {
            return "";
        }
        return deviceId;
    }

    /**
     * @return 设备类型 01表示PC； 02表示POS；03表示手机；04表示平板电脑；99未知设备
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * @return 设备操作系统
     */
    public String getDeviceOs() {
        return "Android";
    }

    /**
     * @return 设备操作系统版本号
     */
    public String getDeviceOSVersion() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    /**
     * @return 设备型号 如:iphone 4s
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }


    public static int devicesIdInfo;    //具体到联迪还是旺pos设备 打印机设备 1代表联迪Pos  2代表旺Pos  3代表手机
    private static final String LIANDI_SN = "APOS A8";
    private static String enWpos;
    //    private static SystemInfomation.DeviceInfo liandiInfo;//获取联迪的设备信息
    //    SystemInfomation.getDeviceInfo().getTerminalType()等价于Build.MODEL,得到的结果都是"APOS A8"
    //     但是未绑定设备的情况下直接调用SystemInfomation.getDeviceInfo().getTerminalType()会报异常,所以
    //必须先通过系统的方法Build.MODEL获取设备类型再进行绑定
    private static String liandiType = Build.MODEL;//联迪的设备类型

    //获取wpos的设备信息 返回的是json   必须先绑定设备才能获取设备信息,否则会报异常
    //    private static String deviceInfo = WeiposImpl.as().getDeviceInfo();

    private DevicesInfoUtil() {
    }

    /**
     * 获取设备的SN号,这里的设备SN号需要发送给服务器
     * liandiInfo.getTerminalType():获取联迪的终端类型
     * <p>
     * 业务要求:
     * 旺POS：			WPOS + 商户编号，例如：WPOS180009000000134
     * 其他Android手机：	ANDROID + 商户编号，例如：ANDROID180009000003233
     * 联迪A8： LANDI + 硬件n，例如：LANDI28632342
     *
     * @param context
     * @return
     */
    public static String getDevicesSN(Context context) {
        if (TextUtils.equals(liandiType, LIANDI_SN)) {
            //            Toast.makeText(context, "当前设备为联迪", Toast.LENGTH_LONG).show();
            //如果是联迪应该获取的是硬件虚拟号
            System.out.println("LANDI" + SystemInfomation.getDeviceInfo().getSerialNo());
            return "LANDI" + SystemInfomation.getDeviceInfo().getSerialNo();
//            return "LANDI50498965";//
        } else if (WeiposImpl.IsWeiposDevice()) {
            //            Toast.makeText(context, "当前设备为wpos", Toast.LENGTH_LONG).show();
            //如果是wpos获取的应该是商户编号
            return "WPOS" + SPUtils.get(context, Fields.shopId, ""); //FIXME
        } else {
            //            Toast.makeText(context, "当前设备为手机", Toast.LENGTH_LONG).show();
            //如果是手机获取的是商户编号
//            return "ANDROID" + SPUtils.get(context, Fields.shopId, "");//FIXME
            return "LANDI50905703";
        }
    }

    /**
     * 获取当前软件的版本号
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        int versionCode = packInfo.versionCode;
//        String version = packInfo.versionName;
        return versionCode;
    }

    /**
     * 获取当前软件的版本名
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        // 获取packagemanager的实例
        PackageManager packageManager = context.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String versionName = packInfo.versionName;
        return versionName;
    }

    /**
     * 这是设备的SN号,显示在凭条上
     *
     * @return
     */
    public static String showSN2Tickets() {
        if (TextUtils.equals(liandiType, LIANDI_SN)) {
            //FIXME 这里只适配了联迪A8

            //            Toast.makeText(context, "当前设备为联迪", Toast.LENGTH_LONG).show();
            return SystemInfomation.getDeviceInfo().getTerminalType()+"-LANDI"+SystemInfomation.getDeviceInfo().getSerialNo();//如果是联迪应该获取的是硬件虚拟号
        } else if (WeiposImpl.IsWeiposDevice()) {
            //            Toast.makeText(context, "当前设备为wpos", Toast.LENGTH_LONG).show();
            String deviceInfo = WeiposImpl.as().getDeviceInfo();//获取wpos的设备信息 返回的是json
            try {
                JSONObject jsonObject = new JSONObject(deviceInfo);
                enWpos = jsonObject.getString("en");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return enWpos;
        } else {
            //            Toast.makeText(context, "当前设备为手机", Toast.LENGTH_LONG).show();
            return /*new Allocation(context).getSerialNo()*/""; //FIXME
        }
    }

    public static String showDeviceName(Context context) {
        if (TextUtils.equals(liandiType, LIANDI_SN)) {

            return SystemInfomation.getDeviceInfo().getSerialNo();//如果是联迪应该获取的是硬件虚拟号
        } else if (WeiposImpl.IsWeiposDevice()) {
            //            Toast.makeText(context, "当前设备为wpos", Toast.LENGTH_LONG).show();
            String deviceInfo = WeiposImpl.as().getDeviceInfo();//获取wpos的设备信息 返回的是json
            try {
                JSONObject jsonObject = new JSONObject(deviceInfo);
                enWpos = jsonObject.getString("en");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return enWpos;
        } else {
            //            Toast.makeText(context, "当前设备为手机", Toast.LENGTH_LONG).show();
            return "";
        }
    }

    /**
     * 绑定设备
     *
     * @param context
     */
    public static void bindDevicesInfo(Context context) {
        if (TextUtils.equals(liandiType, LIANDI_SN)) {
            bindDeviceService(context);//绑定联迪
            devicesIdInfo = 1;
            deviceType = "03";//pos设备
        } else if (WeiposImpl.IsWeiposDevice()) {
            SdkTools.initSdk(context);//绑定WPOS
            devicesIdInfo = 2;
            deviceType = "03";//pos设备
            //            initWposPrinter();//初始化打印设备
        } else {
            //            Toast.makeText(context, "当前设备为手机", Toast.LENGTH_LONG).show();
            //在此绑定其他设备
            devicesIdInfo = 3;
            deviceType = "04";//android手机
        }
    }


    /**
     * 解绑设备
     *
     * @param context
     */
    public static void unbindDevicesInfo(Context context) {
        //        Toast.makeText(context, "执行了destroy方法", Toast.LENGTH_SHORT).show();
        if (TextUtils.equals(liandiType, LIANDI_SN)) {
            DeviceService.logout();
        } else if (WeiposImpl.IsWeiposDevice()) {
            WeiposImpl.as().destroy();
        } else {
            // 此为其他设备
        }
    }

    /**
     * 绑定联迪
     *
     * @param context
     */
    private static void bindDeviceService(Context context) {
        try {
            DeviceService.login(context);
        } catch (ServiceOccupiedException e) {
            e.printStackTrace();
        } catch (ReloginException e) {
            e.printStackTrace();
        } catch (UnsupportMultiProcess unsupportMultiProcess) {
            unsupportMultiProcess.printStackTrace();
        } catch (RequestException e) {
            e.printStackTrace();
        }
    }

    /**
     * 外卖终端的 唯一termSn
     *
     * @return
     */
    public static String getTermSn() {
        String serialNo = "";
        if (TextUtils.equals(liandiType, LIANDI_SN)) {
            //FIXME 这里只适配了联迪A8
            try {
                 serialNo = SystemInfomation.getDeviceInfo().getSerialNo();
            } catch (NullPointerException e) {

            }
//            System.out.println("----------------"+serialNo);
            //            Toast.makeText(context, "当前设备为联迪", Toast.LENGTH_LONG).show();
            return "LANDI" + serialNo;//如果是联迪应该获取的是硬件虚拟号
//            return "LANDI50498965";//如果是联迪应该获取的是硬件虚拟号
        } else if (WeiposImpl.IsWeiposDevice()) {
            //            Toast.makeText(context, "当前设备为wpos", Toast.LENGTH_LONG).show();
            String deviceInfo = WeiposImpl.as().getDeviceInfo();//获取wpos的设备信息 返回的是json
            try {
                JSONObject jsonObject = new JSONObject(deviceInfo);
                enWpos = jsonObject.getString("en");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return enWpos;
        } else {
            //            Toast.makeText(context, "当前设备为手机", Toast.LENGTH_LONG).show();
            return "LANDI50905703";/*new Allocation(context).getSerialNo()*/ //FIXME"LANDI50905703"
        }
    }
}
