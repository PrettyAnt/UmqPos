package unionlive.com.umqpos.ui.fragment.manage_wm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.OrderFoodFragmentPagerAdapter;
import unionlive.com.umqpos.bean.FragmentInfo;
import unionlive.com.umqpos.utils.PagerSlidingTab;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/14 10:44
 * @describe 这是处理管理界面的activity
 */
public class ManageFragment extends Fragment {
    private ArrayList<FragmentInfo> mShowItems = new ArrayList<>();
    private ViewPager mViewPager;
    private PagerSlidingTab mPst_orderfood_manage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_manage, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_orderfood_manage);
        mPst_orderfood_manage = (PagerSlidingTab) view.findViewById(R.id.pst_orderfood_manage_title);
        mPst_orderfood_manage.setTabPaddingLeftRight(70);//设置标题的左右间距
        initTitle();
    }

    private void initTitle() {
        OrderStatisticsFragment orderStatisticsFragment = new OrderStatisticsFragment();
        StockManageFragment stockManageFragment = new StockManageFragment();
        SettingFragment settingFragment = new SettingFragment();
        String[] titles = getResources().getStringArray(R.array.tab_manage_names);
        mShowItems.add(new FragmentInfo(titles[0], orderStatisticsFragment));
        mShowItems.add(new FragmentInfo(titles[1], stockManageFragment));
        mShowItems.add(new FragmentInfo(titles[2], settingFragment));
        OrderFoodFragmentPagerAdapter orderFoodFragmentPagerAdapter = new OrderFoodFragmentPagerAdapter(getChildFragmentManager(), mShowItems);
        mViewPager.setAdapter(orderFoodFragmentPagerAdapter);
        mPst_orderfood_manage.setViewPager(mViewPager);
    }
}
