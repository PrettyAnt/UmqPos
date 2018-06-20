package unionlive.com.umqpos.bean;

import java.util.HashMap;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/15 10:53
 * @describe ${TODO}
 */

public class CancleReason {
    private static HashMap<Integer, String> cancleReason = new HashMap<>();
    /*    String[] reasonCancle = {"不在配送范围", "餐厅已打烊",
                        "美食已售完", "菜品价格发生变化", "用户取消订单",
                        "重复订单", "餐厅太忙", "联系不上用户", "假订单"};*/

    public static HashMap<Integer,String> getCancleReasonInstance() {
        if (cancleReason == null) {
            cancleReason = new HashMap<>();
        }
        getcancleReason();
        return cancleReason;
    }
    private static void getcancleReason(){
        cancleReason.clear();
        cancleReason.put(1, "不在配送范围");
        cancleReason.put(2, "餐厅已打烊");
        cancleReason.put(3, "美食已售完");
        cancleReason.put(4, "菜品价格发生变化");
        cancleReason.put(5, "用户取消订单");
        cancleReason.put(6, "重复订单");
        cancleReason.put(7, "餐厅太忙");
        cancleReason.put(8, "联系不上用户");
        cancleReason.put(9, "假订单");
    }

//    /**
//     * 得到订单信息
//     * @return
//     */
//    public static Object getOrderData(int num) {
//        HashMap hashMap = getCancleReasonInstance();
//        Set set = hashMap.keySet();
//        Object data = null;
//        if (set != null) {
//            Iterator iterator = set.iterator();
//            while (iterator.hasNext()) {
//                int next = (int) iterator.next();//得到订单号
//                String reason = (String) hashMap.get(num);//得到订单信息
//
//            }
//        }
//        return data;
//    }
}
