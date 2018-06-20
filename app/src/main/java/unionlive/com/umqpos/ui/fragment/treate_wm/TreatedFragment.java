package unionlive.com.umqpos.ui.fragment.treate_wm;

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
 * @describe 这是已处理的界面
 */
public class TreatedFragment extends BaseFragment {
    private ArrayList<FragmentInfo> mShowItems = new ArrayList<>();
    private ViewPager mViewPager;

    private AllTreateFragment     mAllTreateFragment;
    private BaiduTreateFragment   mBaiduOrderFoodFragent;
    private MeituanTreateFragment mMeituanTreateFragment;
    private ElemeTreateFragment   mElemeTreateFragment;
    private PagerSlidingTab       mPst_treate_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_treated, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.vp_orderfood_treated);
        mPst_treate_title = (PagerSlidingTab) view.findViewById(R.id.pst_orderfood_treate_title);
        mPst_treate_title.setTabPaddingLeftRight(70);
        initTitle();//初始化标题
    }

    private void initTitle() {
        //外卖-已处理-全部
        mAllTreateFragment = new AllTreateFragment();
        //外卖-已处理-百度
        mBaiduOrderFoodFragent = new BaiduTreateFragment();
        //外卖-已处理-美团
        mMeituanTreateFragment = new MeituanTreateFragment();
        //外卖-已处理-饿了么
        mElemeTreateFragment = new ElemeTreateFragment();
        //        String[] titles = getResources().getStringArray(R.array.tab_names);
        ArrayList<String> title = new ArrayList<>();

        String[] titles = {"全部",
                TitleInfomation.channelName_platform_baidu,
                TitleInfomation.channelName_platform_meituan,
                TitleInfomation.channelName_platform_eleme};
        mShowItems.add(new FragmentInfo(titles[0], mAllTreateFragment));
        if (!TextUtils.isEmpty(titles[1])) {
            mShowItems.add(new FragmentInfo(titles[1], mBaiduOrderFoodFragent));
        }
        if (!TextUtils.isEmpty(titles[2])) {
            mShowItems.add(new FragmentInfo(titles[2], mMeituanTreateFragment));
        }
        if (!TextUtils.isEmpty(titles[3])) {
            mShowItems.add(new FragmentInfo(titles[3], mElemeTreateFragment));
        }
        OrderFoodFragmentPagerAdapter orderFoodFragmentPagerAdapter
                = new OrderFoodFragmentPagerAdapter(getChildFragmentManager(), mShowItems);
        mViewPager.setAdapter(orderFoodFragmentPagerAdapter);//设置适配器
        mPst_treate_title.setViewPager(mViewPager);

    }
}
