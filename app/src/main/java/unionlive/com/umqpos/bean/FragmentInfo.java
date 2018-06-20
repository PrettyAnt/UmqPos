package unionlive.com.umqpos.bean;

import android.support.v4.app.Fragment;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/15 15:23
 * @describe 初始化fragment的信息,将商家的fragment添加到ViewPager
 */
public class FragmentInfo {
    //标题
    public String   title;
    //fragment
    public Fragment fragment;

    public FragmentInfo(String title, Fragment fragment) {
        this.title = title;
        this.fragment = fragment;
    }
}
