package unionlive.com.umqpos.bean;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/29 22:10
 * @describe ${TODO}
 */
public class CouponTdCollectMsg {
    public String mercName;
    public String mercId;
    public String devicesSN;
    public String hostTime;
    public String printTime;
    public int count;

    public CouponTdCollectMsg(String mercName, String mercId, String devicesSN, String hostTime, String printTime, int count) {
                this.mercName=mercName;
                this.mercId=mercId;
                this.devicesSN=devicesSN;
                this.hostTime=hostTime;
                this.printTime=printTime;
                this.count=count    ;

    }
}
