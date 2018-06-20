package unionlive.com.umqpos.utils;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2017/1/3 14:44
 * @describe $转换券号
 */

public class FormatStrCode {
    public static String getDivisionString(String sformat, int d) {
        int sl = sformat.length();
        StringBuffer sb = new StringBuffer();
        if (d != 0 && sl != 0) {
            for (int i = 1; i <= (sl % d == 0 ? sl / d : (sl / d + 1)); i++) {
                if (i != (sl % d == 0 ? sl / d : (sl / d + 1))) {
                    sb.append(sformat.substring(i * d - d, d * i));
                    sb.append(" ");
                } else {
                    sb.append(sformat.substring(i * d - d, sl));
                }
            }
            return sb + "";
        } else {
            return sformat;
        }
    }

    /**
     * 得到1234 **** 1111
     *
     * @param sformat
     *            123456781111
     * @return
     */

	/*public String getConcealString(String sformat, int d) {
		int sl = sformat.length();
		StringBuffer sb = new StringBuffer();
		if (d != 0 && sl != 0) {
			sb.append(sformat.substring(0, d));
			int i ;
			for (i=0; i < sl - 2 * d; i++) {
				sb.append("*");
			}
			sb.append(sformat.substring(i+4, sl));
			return getDivisionString(sb + "", d);
		} else {
			return sformat;
		}
	}*/
    public String getConcealString(String sformat, int d) {
        int sl = sformat.length();
        StringBuffer sb = new StringBuffer();
        if (d != 0 && sl != 0) {
            sb.append(sformat.substring(0, d));
            for (int i = 0; i < sl - 2 * d; i++) {
                sb.append("*");
            }
            sb.append(sformat.substring(sl - d, sl));
            return getDivisionString(sb + "", d);
        } else {
            return sformat;
        }
    }
}
