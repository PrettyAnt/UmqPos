package unionlive.com.umqpos.event;

/**
 * @Created by Administrator
 * @CreateTime 2017/2/3 14:14
 * @Describe ${TODO}
 * @UpdateCreater $Author
 * @UpdateTime $Date
 * @UpdateDescribe ${TODO}
 */
public class TitleStatus {
    private int platformType;//用来区分外卖平台  0：百度，1：美团，2：饿了么，3：微信，4：...
    private int businessStatus;//平台的营业状态 0：开始营业，1：停止营业
    private int autoOrder;//平台的接单状态0：开始接单， 1：关闭接单

    public TitleStatus(int platformType, int businessStatus, int autoOrder) {
        this.platformType = platformType;
        this.businessStatus = businessStatus;
        this.autoOrder = autoOrder;
    }

    public int getPlatformType() {
        return platformType;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public int getAutoOrder() {
        return autoOrder;
    }
}
