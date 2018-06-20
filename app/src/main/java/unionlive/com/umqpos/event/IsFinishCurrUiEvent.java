package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/22 10:15
 * @describe ${TODO}
 */
public class IsFinishCurrUiEvent {
    private String flag ="";
    private boolean isFinish = false;
    private String titleInfo;

    /**
     *
     * @param uiFlag
     * @param isFinish
     * @param titleInfo 对话框的标题
     */
    public IsFinishCurrUiEvent(String uiFlag, boolean isFinish,String titleInfo) {
        this.flag = uiFlag;
        this.isFinish = isFinish;
        this.titleInfo = titleInfo;
    }

    public String getFlag() {
        return flag;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public String getTitleInfo() {
        return titleInfo;
    }

}
