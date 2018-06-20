package unionlive.com.umqpos.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import unionlive.com.umqpos.bean.FragmentInfo;

/**
 * @author chenyu   Email:981214993@qq.com  T:15921892585
 * @version 2016/12/15 14:17
 * @describe ${TODO}
 */

public class OrderFoodFragmentPagerAdapter extends FragmentPagerAdapter {
    private final ArrayList<FragmentInfo> mShowItems;
    public OrderFoodFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<FragmentInfo> showItems) {
        super(fragmentManager);
        this.mShowItems = showItems;
    }
    @Override
    public Fragment getItem(int position) {
        return mShowItems.get(position).fragment;
    }
    @Override
    public int getCount() {
        return mShowItems.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mShowItems.get(position).title;
    }
}
