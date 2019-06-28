package com.stepyen.xuidemo.fragment.components;

import com.daidingkang.SnapUpCountDownTimerView;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xuidemo.base.ComponentContainerFragment;
import com.stepyen.xuidemo.fragment.components.textview.OtherTextViewFragment;
import com.stepyen.xuidemo.fragment.components.textview.SuperTextViewFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "文字", extra = R.drawable.ic_widget_imageview)
public class TextViewFragment extends ComponentContainerFragment {

    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{OtherTextViewFragment.class, SuperTextViewFragment.class};
    }
}
