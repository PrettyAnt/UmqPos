package unionlive.com.umqpos.event;

import unionlive.com.umqpos.ui.BaseFragment;

/**
 * @author chenyu   Email:981214993@qq.com
 * @version 2016/12/29 16:02
 * @describe ${TODO}
 */
public class FlashUiEvent {


    public int          flag;
    public BaseFragment expectFragment;
    public BaseFragment currentFragment;
    public boolean      isBack;
    public String       title;

    public FlashUiEvent(BaseFragment expectFragment, BaseFragment currentFragment, boolean isBack, String title, int flag) {
        this.expectFragment = expectFragment;
        this.currentFragment=currentFragment;
        this.isBack=isBack;
        this.title = title;
        this.flag = flag;
    }

    public BaseFragment getExpectFragment() {
        return expectFragment;
    }

    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public boolean isBack() {
        return isBack;
    }

    public String getTitle() {
        return title;
    }
    public int getFlag() {
        return flag;
    }
}
