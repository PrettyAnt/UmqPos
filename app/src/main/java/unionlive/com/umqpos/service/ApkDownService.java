package unionlive.com.umqpos.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import java.io.File;

import unionlive.com.umqpos.utils.FileUtil;


/**
 * 检测安装更新文件的助手类
 */
public class ApkDownService extends Service {

    /**
     * 安卓系统下载类
     **/
    DownloadManager manager;

    /**
     * 接收下载完的广播
     **/
    DownloadCompleteReceiver receiver;

    /**
     * 初始化下载器
     **/
    private void initDownManager(String url) {
//        JSONObject obj = UrlParamUtil.getUrlParams(url);
//        String downUrl = ModelUtil.getStringValue(obj, "download");
        String apkName = url.substring(url.lastIndexOf("/") + 1, url.length());

        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        receiver = new DownloadCompleteReceiver(apkName);

        //设置下载地址
        DownloadManager.Request down = new DownloadManager.Request(Uri.parse(url));

        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

        // 下载时，通知栏显示途中
        if (Build.VERSION.SDK_INT >= 11) {
            down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        }

        // 显示下载界面
        down.setVisibleInDownloadsUi(true);

        // 设置下载后文件存放的位置
        down.setDestinationUri(Uri.fromFile(new File(FileUtil.getApkExternalFile(apkName))));

        // 将下载请求放入队列
        manager.enqueue(down);

        //注册下载广播
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra("url"))) {
            // 调用下载
            initDownManager(intent.getStringExtra("url"));
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // 注销下载广播
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }

    // 接受下载完成后的intent
    class DownloadCompleteReceiver extends BroadcastReceiver {

        String apkName = "";

        public DownloadCompleteReceiver(String apkName) {
            this.apkName = apkName;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //判断是否下载完成的广播
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {

                if (!TextUtils.isEmpty(apkName)) {
                    installAPK(context, Uri.fromFile(new File(FileUtil.getApkExternalFile(apkName))));
                }

                //停止服务并关闭广播
                ApkDownService.this.stopSelf();

            }
        }

        /**
         * 安装apk文件
         */
        private void installAPK(Context context, Uri apk) {
//            // 通过Intent安装APK文件
//            Intent intents = new Intent();
//            intents.setAction("android.intent.action.VIEW");
//            intents.addCategory("android.intent.category.DEFAULT");
//            intents.setType("application/vnd.android.package-archive");
//            intents.setData(apk);
//            intents.setDataAndType(apk, "application/vnd.android.package-archive");
//            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intents);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri contentUri = FileProvider.getUriForFile(context, "yuer.shopkv.com.shopkvyuer.file", new File(FileUtil.getApkExternalFile(apkName)));
                intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(apk, "application/vnd.android.package-archive");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }
            if (context.getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
                context.startActivity(intent);
            }
            // 如果不加上这句的话在apk安装完成之后点击单开会崩溃
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}