package unionlive.com.umqpos.utils;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/2/10 16:41
 * @describe ${TODO}
 */

public class OrderIdQueue {
    private static int queueSize = 1000;
    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(queueSize);
    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }
    public static String getOrderId(){
        String orderId = "";
        try {
            orderId = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();//未找到订单号
        }
        return orderId;
    }
    public static void putMessage(String orderId) {
        try {
            queue.put(orderId);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
