package unionlive.com.umqpos.event;

import java.util.List;

import unionlive.com.umqpos.bean.DishFoodType;
import unionlive.com.umqpos.ui.fragment.manage_wm.StockManageFragment;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/31 15:56
 * @describe ${TODO}
 */
public class CateIdEvent {


    public StockManageFragment getStockManageFragment() {
        return stockManageFragment;
    }

    private StockManageFragment stockManageFragment;
    private int                 position;

    public List<DishFoodType> getDishFoodTypeList() {
        return dishFoodTypeList;
    }

    private List<DishFoodType> dishFoodTypeList;

    public CateIdEvent(List<DishFoodType> dishFoodTypeList, int position, StockManageFragment stockManageFragment) {
        this.dishFoodTypeList = dishFoodTypeList;
        this.position=position;
        this.stockManageFragment = stockManageFragment;
    }

    public int getPosition() {
        return position;
    }
}
