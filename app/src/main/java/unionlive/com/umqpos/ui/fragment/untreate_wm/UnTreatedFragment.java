package unionlive.com.umqpos.ui.fragment.untreate_wm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import unionlive.com.umqpos.R;
import unionlive.com.umqpos.adapter.OrderFoodFragmentPagerAdapter;
import unionlive.com.umqpos.bean.FragmentInfo;
import unionlive.com.umqpos.bean.TitleInfomation;
import unionlive.com.umqpos.ui.BaseFragment;
import unionlive.com.umqpos.utils.PagerSlidingTab;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/14 10:44
 * @describe 这是未处理的界面
 */
public class UnTreatedFragment extends BaseFragment {
    private ArrayList<FragmentInfo> mShowItems = new ArrayList<>();
    private ViewPager mViewPager;
    private PagerSlidingTab mPst_orderfood_untreate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_untreated, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_orderfood_untreated);
        mPst_orderfood_untreate = (PagerSlidingTab) view.findViewById(R.id.pst_orderfood_untreate_title);

        mPst_orderfood_untreate.setTabPaddingLeftRight(70);
        initTitle();//初始化标题
    }

    private void initTitle() {
        AllUntreateFragment allUntreateFragment = new AllUntreateFragment();
        BaiduUntreateFragment baiduUntreateFragment = new BaiduUntreateFragment();
        MeituanUntreateFragment meituanUntreateFragment = new MeituanUntreateFragment();
        ElemeUntreateFragment elemeUntreateFragment = new ElemeUntreateFragment();
//        String[] titles = getResources().getStringArray(R.array.tab_names);
        String[] titles = {"全部",
                TitleInfomation.channelName_platform_baidu,
                TitleInfomation.channelName_platform_meituan,
                TitleInfomation.channelName_platform_eleme};

            mShowItems.add(new FragmentInfo(titles[0], allUntreateFragment));
        if (!TextUtils.isEmpty(titles[1])) {
            mShowItems.add(new FragmentInfo(titles[1], baiduUntreateFragment));
        }
        if (!TextUtils.isEmpty(titles[2])) {
            mShowItems.add(new FragmentInfo(titles[2], meituanUntreateFragment));
        }
        if (!TextUtils.isEmpty(titles[3])) {
            mShowItems.add(new FragmentInfo(titles[3], elemeUntreateFragment));
        }
        OrderFoodFragmentPagerAdapter orderFoodFragmentPagerAdapter = new OrderFoodFragmentPagerAdapter(getChildFragmentManager(),mShowItems);
        mViewPager.setAdapter(orderFoodFragmentPagerAdapter);//设置适配器
        mPst_orderfood_untreate.setViewPager(mViewPager);
    }

}
