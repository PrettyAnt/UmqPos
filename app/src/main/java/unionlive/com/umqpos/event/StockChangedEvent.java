package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/7 10:31
 * @describe ${TODO}
 */
public class StockChangedEvent {
    private boolean isStockChanged;
    public StockChangedEvent(boolean isStockChanged) {
        this.isStockChanged = isStockChanged;
    }

    public boolean isStockChanged() {
        return isStockChanged;
    }
}
