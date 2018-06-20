package unionlive.com.umqpos.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import unionlive.com.umqpos.BuildConfig;
import unionlive.com.umqpos.content.Fields;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/16 19:59
 * @describe ${TODO}
 */
public class FileUtil {
    public static String getApkExternalFile(String filename) {
        return getExternalFile(filename, "apk");
    }

    private static String getExternalFile(String filename, String path) {
        String extSdCard = getExtSDCard();
        File tempF = new File(extSdCard);
        if (!tempF.exists()) {
            extSdCard = getSDPath();
        }
        if (extSdCard != null) {// SD卡可用
            File f = new File(extSdCard + "/" + Fields.SD_CACHE_URL + "/");
            if (!f.exists()) {
                f.mkdir();
            }
            if (!f.exists()) {
                extSdCard = getSDPath();
                File ff = new File(extSdCard + "/" + Fields.SD_CACHE_URL + "/");
                if (!ff.exists()) {
                    ff.mkdir();
                }
            }
            f = new File(extSdCard + "/" + Fields.SD_CACHE_URL + "/" + path + "/");
            if (!f.exists()) {
                f.mkdir();
            }
            if (TextUtils.isEmpty(filename)) {
                return extSdCard + "/" + Fields.SD_CACHE_URL + "/" + path + "/";
            } else {
                return extSdCard + "/" + Fields.SD_CACHE_URL + "/" + path + "/" + filename;
            }
        } else {
            return null;
        }
    }
    private static String getExtSDCard() {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("mount");
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            String line;
            String mount = "";
            BufferedReader br = new BufferedReader(isr);
            while ((line = br.readLine()) != null) {
                if (line.contains("secure"))
                    continue;
                if (line.contains("asec"))
                    continue;

                if (line.contains("fat")) {
                    String columns[] = line.split(" ");
                    if (columns.length > 1) {
                        mount = mount.concat(columns[1]);
                    }
                }
            }
            return mount;
        } catch (FileNotFoundException e) {
//            LogUtil.i("FileNotFoundException", e.getMessage());
            if (BuildConfig.DEBUG)
                Log.d("FileUtil", "e:" + e);
        } catch (IOException e) {
//            LogUtil.i("IOException", e.getMessage());
            if (BuildConfig.DEBUG)
                Log.d("FileUtil", "e:" + e);
        }
        return null;
    }


    private static String getSDPath() {
        String sdDir = "";
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);// 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().toString();// 获取跟目录
        }
        return sdDir;
    }

}
