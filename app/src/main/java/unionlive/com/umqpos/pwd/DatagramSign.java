package unionlive.com.umqpos.pwd;


import android.util.Base64;

/**
 * 优麦圈接口签名规则
 */
public class DatagramSign {

    private static final String CHARSET = "UTF-8";

    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    /*
     对称加密（DES）+验签（MD5）方式方法如下：
     加密步骤：
        1. 【加密报文】通过双方约定的密钥K1使用DES加密报文明文S1，得到密文字节数组B1;
        2. 【加密报文】取得密文字节数组的Base64表示形式S2;
        3. 【计算签名】将双方约定的密钥K2（可以等于K1）拼接在S2的前部，得到S3;
        4. 【计算签名】计算S3的MD5，并取得Hex字符串的小写形式M1;
        5. 【拼接结果】将M1和S2拼接得到最终密文报文：R

     解密步骤：
        1. 【拆解密文】取得密文报文字符串S1的前32个字符，得到签名M1，取得32个字符后的内容，得到S2;
        2. 【验证签名】将双方约定的密钥K1拼接在S2的前部，得到S3;
        3. 【验证签名】计算S3的MD5，并取得Hex字符串的小写形式M2l
        4. 【验证签名】判断M1是否等于M2，不相等则验签失败，否则继续如下解密步骤；
        5. 【解密报文】取得S2的反Base64后的密文字节数组B1;
        6. 【解密报文】通过双方约定的密钥K2（可以等于K1）使用DES解密密文字节数组B1，得到明文报文：R
     */

    //加密
    public static String desAndMd5Encrypt(String v,String padding,String macSecret,String secret){
        try {
            byte[] B1 = DesHelper.encrypt(v.getBytes(CHARSET),padding,secret);
//            String s2 = Base64.getEncoder().encodeToString(B1);//FIXME
            String s2 = Base64.encodeToString(B1, Base64.NO_WRAP);
            String s3 = macSecret+s2;
            String M1 = MD5.code32ToLowerCase(s3);
            String R = M1+s2;
            return R;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密
    public static String desAndMd5Decoder(String v,String padding,String macSecret,String secret){
        try {
            String M1 = v.substring(0,32);
            String S2 = v.substring(32);
            String S3 = macSecret+S2;
            String M2 = MD5.code32ToLowerCase(S3);
            if(!M2.equals(M1)){ return null; }
            byte[] B1 = Base64.decode(S2.getBytes(CHARSET), Base64.NO_WRAP);
//            byte[] B1 = Base64.getDecoder().decode(S2.getBytes(CHARSET));//TODO
            return new String(DesHelper.decrypt(B1,padding,secret),CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //扩展其他签名加密算法
    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓




    //↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓




}
