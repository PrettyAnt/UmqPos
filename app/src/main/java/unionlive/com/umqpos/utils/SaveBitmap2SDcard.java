package unionlive.com.umqpos.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Looper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 创建时间    2016/10/25 14:54
 * @describe 将小票的图片保存到SDcard
 */

public class SaveBitmap2SDcard {

    private static File mapName;

    private SaveBitmap2SDcard(){}

    /**
     * 将小票保存在sd卡中
     * @param context
     * @param bitmap
     */
    public  static void saveBitmap(final Context context, Bitmap bitmap) {
        //将文件名以当前时间的形式保存
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String date = formatter.format(currentTime);
        File sdDir=null;
        FileOutputStream fos=null;
        //判断SDcard是否挂载
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sdDir  = Environment.getExternalStorageDirectory();//获取跟目录
            mapName = new File(sdDir,"umq");
            try {
                //如果mapName文件不存在,就创建
                if (!mapName.exists()) {
                    mapName.mkdirs();
                }
//                mapName.createNewFile();
                fos = new FileOutputStream(mapName +"/IMG"+date+".png");
                //将文件以png的格式保存
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Looper.prepare();
//                        MyToast.show(context,"小票成功保存在:"+mapName,true);
                        Looper.loop();
                    }
                }.start();

                flashPic(context);//刷新一下图片路径,否则图片不能及时显示在相册
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
//            Toast.makeText(context, "sd卡没有挂载", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 此方法是用来刷新相册,让小票及时显示在相册
     * @param context
     */
    public static void flashPic(Context context){
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(mapName);
        intent.setData(uri);
        context.sendBroadcast(intent);
    }
}
