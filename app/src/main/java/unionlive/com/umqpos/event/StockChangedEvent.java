package unionlive.com.umqpos.event;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
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
