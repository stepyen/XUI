package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.ComponentContainerFragment;
import com.stepyen.xuidemo.fragment.components.layout.TagLayoutFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/25
 * author：stepyen
 * description：
 */
@Page(name = "通用布局", extra = R.drawable.ic_widget_imageview)
public class LayoutFragment extends ComponentContainerFragment {

    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                TagLayoutFragment.class
        };
    }
}
