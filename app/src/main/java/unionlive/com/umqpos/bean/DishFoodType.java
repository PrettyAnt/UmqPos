package unionlive.com.umqpos.bean;

import java.util.List;

import unionlive.com.umqpos.entitys.out_waimai.QueryDishResponse;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/30 19:31
 * @describe ${TODO}
 */

public class DishFoodType {

    public String                                                           sizeName;
    public String                                                           sizeId;
    public String                                                           foodName;
    public String                                                           pic;
    public String                                                           cateId;
    public List<QueryDishResponse.BodyBean.FoodsBean.NormsBean.ChannelBean> channel;
    public String                                                           foodId;

    @Override
    public String toString() {
        return "{" +
                "sizeName:'" + sizeName + '\'' +
                ", sizeId:'" + sizeId + '\'' +
                ", foodName:'" + foodName + '\'' +
                ", pic:'" + pic + '\'' +
                ", cateId:'" + cateId + '\'' +
                ", channel:" + channel +
                ", foodId:" + foodId +
                '}';
    }
}
