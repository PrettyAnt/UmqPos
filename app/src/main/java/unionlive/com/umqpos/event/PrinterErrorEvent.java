package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/8 21:38
 * @describe ${TODO}
 */
public class PrinterErrorEvent {
    private boolean isPrint;
    private boolean isReprint;
    private int status;//打印机的状态
    public int getStatus() {
        return status;
    }

    public boolean isReprint() {
        return isReprint;
    }

    public boolean isPrint() {
        return isPrint;
    }

    /**
     *
     * @param status 打印机的状态
     * @param isReprint  是否是重打印的小票
     * @param isPrint   是否打印小票还是仅仅为了检测打印机?
     */
    public PrinterErrorEvent(int status, boolean isReprint, boolean isPrint) {
        this.status = status;
        this.isReprint = isReprint;
        this.isPrint = isPrint;
    }
}
