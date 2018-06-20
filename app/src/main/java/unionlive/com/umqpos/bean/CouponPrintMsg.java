package unionlive.com.umqpos.bean;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/29 16:37
 * @describe $给打印提供的实体类
 */

public class CouponPrintMsg {
   public int versionCode;//应用程序的版本号
   public String certificNum;//批次号
   public String  batchNum;//凭证号
   public String  hostTraceNo;//后台交易流水号
   public String  mercName;//门店名称
   public String  mercId;//门店编号
   public String  shopOperId;//操作员编号
   public String  termId;//终端编号
   public String  prodId;//产品编号
   public String  authCode;//授权码
   public String  transTime;//交易时间
   public String  expireDate;//券有效期
   public String  prodName;//产品名称
   public String  transNum;//券号
   public String  prodDesc;//产品描述
   public String  printTime;//打印时间
   public String  amount;//使用次数
   public String  title;//门店名称
   public boolean flag;
   public String  transName;//交易名称
   public String  ad;//广告信息

    public CouponPrintMsg(String title, boolean flag, String mercId, String shopOperId,
                          String termId, String prodId, String authCode, String transTime,
                          String expireDate, String prodName, String transNum, String prodDesc,
                          String printTime, String amount, String mercName, String transName,
                          String ad, String hostTraceNo, String batchNum, String certificNum, int versionCode) {
        this.mercName = mercName;
        this.mercId = mercId;
        this.shopOperId = shopOperId;
        this.termId = termId;
        this.prodId = prodId;
        this.authCode = authCode;
        this.transTime = transTime;
        this.expireDate = expireDate;
        this.prodName = prodName;
        this.transNum = transNum;
        this.prodDesc = prodDesc;
        this.printTime = printTime;
        this.amount = amount;
        this.title = title;
        this.flag = flag;
        this.transName = transName;
        this.ad = ad;
        this.hostTraceNo = hostTraceNo;
        this.batchNum = batchNum;
        this.certificNum = certificNum;
        this.versionCode=versionCode;
    }

}
