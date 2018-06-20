package unionlive.com.umqpos.net;

import android.app.Dialog;
import android.content.Context;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import unionlive.com.umqpos.content.Fields;
import unionlive.com.umqpos.event.CoubonsDecoderEvent;
import unionlive.com.umqpos.event.InitUiEvent;
import unionlive.com.umqpos.event.NetErrorEvent;
import unionlive.com.umqpos.ui.dialog.WeiboDialogUtils;
import unionlive.com.umqpos.ui.dialog.CancleOrderDialog;
import unionlive.com.umqpos.utils.TimerTaskHelper;

import static unionlive.com.umqpos.pwd.DatagramSign.desAndMd5Decoder;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/7 23:03
 * @describe 网络框架
 */
public class NetUtil {
    private static Retrofit retrofit = null;
    private static String baseUrl;

    /**
     * 这是外卖的访问方法，调用的是外卖的接口
     *
     * @param <T>        传入类的类型
     * @param encryptStr 通过加密的方法得到的密文
     * @param channelId  渠道Id   fixme 此处的channelId不同于外卖的channelId !
     * @param t          需要传入的类，这个主要是方便EventBus的使用，因为我们需要将从服务器得到的报文发送出去，使用泛型类优化了代码
     * @param context    上下文
     */
    public static <T> void waiMaiNetWorkUtil(String encryptStr,
                                             String channelId,
                                             final Class<T> t,
                                             final Context context,
                                             final int OrderType) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        final Dialog loadingDialog = WeiboDialogUtils.createLoadingDialog(context, "加载中...");
        retrofit = new Retrofit
                .Builder()
                .baseUrl(getWaiMaiBaseUrl())//获取baseUrl
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<String> stringCall = httpApi.waiMaiNetMethod(encryptStr, channelId);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        String strResponse = response.body().toString();
                        String decoderStr = desAndMd5Decoder(strResponse,//
                                Fields.desAndMd5Decoder_padding, Fields.desAndMd5Decoder_macSeret,
                                Fields.desAndMd5Decoder_seret);//解密后的数据 "DES/CBC/PKCS5Padding"
                                                System.out.println("外卖得到的报文: \n\n\n" + decoderStr + "\n\n\n\n");
                        T javaBeanStr = new Gson().fromJson(decoderStr, t);
                        EventBus.getDefault().postSticky(new CoubonsDecoderEvent(javaBeanStr, OrderType));
                        WeiboDialogUtils.closeDialog(loadingDialog);
                        break;
                    default:
                        CancleOrderDialog.isNetErrorDialog(context, "HTTP异常:" + response.code(), loadingDialog);
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                CancleOrderDialog.isNetErrorDialog(context, "网络连接超时", loadingDialog);
                EventBus.getDefault().postSticky(new NetErrorEvent(true));
            }
        });
    }


    /**
     * 用来处理子线程访问网络的方法，上述的方法中有更新UI操作，所以另起一个方法`~
     *
     * @param <T>        传入类的类型
     * @param encryptStr 通过加密的方法得到的密文
     * @param channelId  渠道Id   fixme 此处的channelId不同于外卖的channelId !
     * @param t          需要传入的类，这个主要是方便EventBus的使用，因为我们需要将从服务器得到的报文发送出去，使用泛型类优化了代码
     * @param context    上下文
     */
    public static <T> void GTNetWorkUtil(String encryptStr,
                                         String channelId,
                                         final Class<T> t,
                                         final Context context,
                                         final int OrderType) {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(getWaiMaiBaseUrl())//获取baseUrl
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<String> stringCall = httpApi.waiMaiNetMethod(encryptStr, channelId);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        String strResponse = response.body().toString();
                        String decoderStr = desAndMd5Decoder(strResponse,//
                                Fields.desAndMd5Decoder_padding, Fields.desAndMd5Decoder_macSeret,
                                Fields.desAndMd5Decoder_seret);//解密后的数据 "DES/CBC/PKCS5Padding"
                        System.out.println("外卖得到的报文: \n\n\n" + decoderStr + "\n\n\n\n");
                        T javaBeanStr = new Gson().fromJson(decoderStr, t);
                        EventBus.getDefault().postSticky(new CoubonsDecoderEvent(javaBeanStr, OrderType));
                        break;
                    default:
                        //                        CancleOrderDialog.isNetErrorDialog(context, "网络连接超时", null);
                        break;
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println("onFailure" + t);
                //                MyToast.show(context, "请检查网络连接状态", true);
            }
        });
    }

    /**
     * 用来处理子线程访问网络的方法，同步网络方法
     *
     * @param <T>        传入类的类型
     * @param encryptStr 通过加密的方法得到的密文
     * @param channelId  渠道Id   fixme 此处的channelId不同于外卖的channelId !
     * @param t          需要传入的类，这个主要是方便EventBus的使用，因为我们需要将从服务器得到的报文发送出去，使用泛型类优化了代码
     * @param context    上下文
     */
    public static <T> void NetWorkUtil(String encryptStr,
                                       String channelId,
                                       final Class<T> t,
                                       final Context context,
                                       final int OrderType) {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(getWaiMaiBaseUrl())//获取baseUrl
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<String> stringCall = httpApi.waiMaiNetMethod(encryptStr, channelId);
        try {
            Response<String> response = stringCall.execute();
            switch (response.code()) {
                case 200:
                    String strResponse = response.body().toString();
                    String decoderStr = desAndMd5Decoder(strResponse,//
                            Fields.desAndMd5Decoder_padding, Fields.desAndMd5Decoder_macSeret,
                            Fields.desAndMd5Decoder_seret);//解密后的数据 "DES/CBC/PKCS5Padding"
                    System.out.println("同步外卖得到的报文: \n\n\n" + decoderStr + "\n\n\n\n");
                    T javaBeanStr = new Gson().fromJson(decoderStr, t);
                    EventBus.getDefault().postSticky(new CoubonsDecoderEvent(javaBeanStr, OrderType));
                    break;
                default:
                    //
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用来处理子线程访问网络的方法，同步网络方法
     *
     * @param <T>        传入类的类型
     * @param encryptStr 通过加密的方法得到的密文
     * @param channelId  渠道Id   fixme 此处的channelId不同于外卖的channelId !
     * @param t          需要传入的类，这个主要是方便EventBus的使用，因为我们需要将从服务器得到的报文发送出去，使用泛型类优化了代码
     */
    public static <T> T getData(String encryptStr,
                                String channelId,
                                final Class<T> t
    ) {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(getWaiMaiBaseUrl())//获取baseUrl
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<String> stringCall = httpApi.waiMaiNetMethod(encryptStr, channelId);
        T javaBeanStr = null;
        try {
            Response<String> response = stringCall.execute();
            switch (response.code()) {
                case 200:
                    String strResponse = response.body().toString();
                    String decoderStr = desAndMd5Decoder(strResponse,//
                            Fields.desAndMd5Decoder_padding, Fields.desAndMd5Decoder_macSeret,
                            Fields.desAndMd5Decoder_seret);//解密后的数据 "DES/CBC/PKCS5Padding"
                    //                    System.out.println("同步外卖得到的报文: \n\n\n" + decoderStr + "\n\n\n\n");
                    javaBeanStr = new Gson().fromJson(decoderStr, t);
                    //                    EventBus.getDefault().postSticky(new CoubonsDecoderEvent(javaBeanStr, OrderType));
                    break;
                default:
                    //
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaBeanStr;
    }

    /**
     * 这是验券的访问网络的方法，调用的是验券的接口
     *
     * @param encryptStr
     * @param aid
     * @param t
     * @param context
     * @param <T>
     */
    public static <T> void couponsNetWorkUtil(String encryptStr, String aid, final Class<T> t,
                                              final Context context, String MethodType, final int OrderType) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        final Dialog loadingDialog = WeiboDialogUtils.createLoadingDialog(context, "加载中...");
        retrofit = new Retrofit
                .Builder()
                .baseUrl(getCouponsBaseUrl())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<String> stringCall = null;
        switch (MethodType) {//根据字段的类型调用不同的方法
            case "ValidateApp":
                stringCall = httpApi.ValidateApp(encryptStr, aid);
                break;
            case "QueryAppVersion":
                stringCall = httpApi.QueryAppVersion(encryptStr, aid);
                break;
            case "ReportException":
                stringCall = httpApi.ReportException(encryptStr, aid);
                break;
            case "QueryAppInfo":
                stringCall = httpApi.QueryAppInfo(encryptStr, aid);
                break;
            case "CheckIn":
                stringCall = httpApi.CheckIn(encryptStr, aid);
                break;
            case "PurchaseCoupons":
                stringCall = httpApi.PurchaseCoupons(encryptStr, aid);
                break;
            case "QueryTransBasic":
                stringCall = httpApi.QueryTransBasic(encryptStr, aid);
                break;
            case "QueryTransDetails":
                stringCall = httpApi.QueryTransDetails(encryptStr, aid);
                break;
        }

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                switch (response.code()) {
                    case 200:
                        String strResponse = response.body().toString();
                        String decoderStr = desAndMd5Decoder(strResponse,
                                "DES/CBC/PKCS5Padding", "boyasafe", "boyasafe");//解密后的数据  01808801  259E3D0F4C9249D2
                        System.out.println("验券得到的报文: \n\n\n" + decoderStr + "\n\n\n\n");
                        Gson gson = new Gson();
                        T javaBeanStr = gson.fromJson(decoderStr, t);//得到解密后的javaBean
                        EventBus.getDefault().postSticky(new CoubonsDecoderEvent(javaBeanStr, OrderType));
                        WeiboDialogUtils.closeDialog(loadingDialog);
                        break;
                    default:
                        CancleOrderDialog.isNetErrorDialog(context, "HTTP异常:" + response.code(), loadingDialog);
                        break;
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                CancleOrderDialog.isNetErrorDialog(context, "网络连接超时", loadingDialog);
            }
        });
    }

    /**
     * 心跳包的检测
     *
     * @param params
     */
    public static void checkDevice(HashMap<String, String> params) {
        retrofit = new Retrofit
                .Builder()
                .baseUrl(getWaiMaiBaseUrl())//获取baseUrl
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        HttpApi httpApi = retrofit.create(HttpApi.class);
        Call<String> stringCall = httpApi.CheckDevice(params);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //                System.out.println("请求体："+response.body() + "返回码:" + response.code());
                switch (response.code()) {
                    case 200:
                        TimerTaskHelper.count = 0;
                        EventBus.getDefault().postSticky(new InitUiEvent(2));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //                System.out.println("心跳包异常" + t);
                EventBus.getDefault().postSticky(new InitUiEvent(1));
            }
        });
    }

    /**
     * 外卖
     * 根据不同环境获取相应的baseUrl
     *
     * @return
     */
    public static String getWaiMaiBaseUrl() {
        switch (EnvironmentType.ENVIRONMENT_VARIABLE) {
            case 0:
                baseUrl = "http://d.umq.me/japi-waimai/";//测试环境的BaseUrl
                break;
            case 1:
                baseUrl = "http://u.umq.me/japi-waimai/";//uat环境的BaseUrl
                //                baseUrl = "http://u.umq.me/japi-waimai/";//uat环境的BaseUrl
                //http://d.umq.me/
                break;
            case 2:
                baseUrl = "http://umq.cc/japi-waimai/";//pro环境的BaseUrl
                break;
        }
        return baseUrl;
    }

    /**
     * 验券
     * 根据不同环境获取相应的baseUrl
     *
     * @return
     */
    private static String getCouponsBaseUrl() {
        switch (EnvironmentType.ENVIRONMENT_VARIABLE) {
            case 0:
                baseUrl = "http://d.umq.me/umqservice/";//测试环境的BaseUrl
                break;
            case 1:
                baseUrl = "http://u.umq.me/umqservice/";//uat环境的BaseUrl
                //http://d.umq.me/
                break;
            case 2:
                baseUrl = "http://umq.cc/posservice/";//pro环境的BaseUrl
                break;
        }
        return baseUrl;
    }
}
