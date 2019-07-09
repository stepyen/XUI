package com.stepyen.xuidemo.fragment.expands;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.ComponentContainerFragment;
import com.stepyen.xuidemo.fragment.components.imageview.PreviewFragment;
import com.stepyen.xuidemo.fragment.components.imageview.RadiusImageViewFragment;
import com.stepyen.xuidemo.fragment.expands.rv_nest.RvNest1Fragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */
@Page(name = "rv嵌套", extra = R.drawable.ic_widget_banner)
public class RvNestFragment extends ComponentContainerFragment {

    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                RvNest1Fragment.class,
        };
    }
}
