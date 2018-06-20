package unionlive.com.umqpos.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/2 13:11
 * @describe ${TODO}
 */

public interface HttpApi {
    //   统一访问网络的接口  "japi-waimai/gateway.do/"

    /**
     * 外卖的接口
     *
     * @param inputJson 上送的密文
     * @param channelId 渠道Id
     * @return
     */
    @FormUrlEncoded
    @POST("gateway.do/")
    Call<String> waiMaiNetMethod(@Field(value = "inputJson", encoded = true) String inputJson,
                                 @Field(value = "channelId", encoded = true) String channelId);

    /**
     * 外卖的接口
     *
     * @param inputJson 上送的密文
     * @param channelId 渠道Id
     * @return
     */
    @FormUrlEncoded
    @POST("japi-waimai/gateway.do/")
    Call<String> waiMaiNetMethod_uat(@Field(value = "inputJson", encoded = true) String inputJson,
                                     @Field(value = "channelId", encoded = true) String channelId);

    /**
     * 外卖的接口
     *
     * @param inputJson 上送的密文
     * @param channelId 渠道Id
     * @return
     */
    @FormUrlEncoded
    @POST("japi-waimai/gateway.do/")
    Call<String> waiMaiNetMethod_pro(@Field(value = "inputJson", encoded = true) String inputJson,
                                     @Field(value = "channelId", encoded = true) String channelId);


    /*******
     * 下面是验券的方法
     ************/
    //    http://d.umq.me/umqservice/PosService.asmx?aid=
    //    http://u.umq.me/umqservice/PosService.asmx?aid=
    //    http://umq.cc/posservice/PosService.asmx?aid=

    /***************dev版*********************/
    //手机客户端应用校验
    @FormUrlEncoded
    @POST("PosService.asmx/ValidateApp")
    Call<String> ValidateApp(@Field(value = "inputJson", encoded = true) String inputJson,
                             @Field(value = "aid", encoded = true) String aid);

    //手机客户端应用版本查询
    @FormUrlEncoded
    @POST("PosService.asmx/QueryAppVersion")
    Call<String> QueryAppVersion(@Field(value = "inputJson", encoded = true) String inputJson,
                                     @Field(value = "aid", encoded = true) String aid);
    //客户端错误报告
    @FormUrlEncoded
    @POST("PosService.asmx/ReportException")
    Call<String> ReportException(@Field(value = "inputJson", encoded = true) String inputJson,
                                     @Field(value = "aid", encoded = true) String aid);

    //手机客户端应用信息查询
    @FormUrlEncoded
    @POST("PosService.asmx/QueryAppInfo")
    Call<String> QueryAppInfo(@Field(value = "inputJson", encoded = true) String inputJson,
                                  @Field(value = "aid", encoded = true) String aid);

   //签到
   @FormUrlEncoded
   @POST("PosService.asmx/CheckIn")
   Call<String> CheckIn(@Field(value = "inputJson", encoded = true) String inputJson,
                                 @Field(value = "aid", encoded = true) String aid);

    //2.6电子券兑换
    @FormUrlEncoded
    @POST("PosService.asmx/PurchaseCoupons")
    Call<String> PurchaseCoupons(@Field(value = "inputJson", encoded = true) String inputJson,
                             @Field(value = "aid", encoded = true) String aid);

    //交易基本信息查询
    @FormUrlEncoded
    @POST("PosService.asmx/QueryTransBasic")
    Call<String> QueryTransBasic(@Field(value = "inputJson", encoded = true) String inputJson,
                                 @Field(value = "aid", encoded = true) String aid);
    //交易明细信息查询
    @FormUrlEncoded
    @POST("PosService.asmx/QueryTransDetails")
    Call<String> QueryTransDetails(@Field(value = "inputJson", encoded = true) String inputJson,
                                 @Field(value = "aid", encoded = true) String aid);

    @FormUrlEncoded
    @POST("heartbeatPack.do")
    Call<String> CheckDevice(@FieldMap Map<String, String> params);
}
