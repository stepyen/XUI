package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.ComponentContainerFragment;
import com.stepyen.xuidemo.fragment.components.imageview.RadiusImageViewFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/24
 * author：stepyen
 * description：
 */

@Page(name = "图片", extra = R.drawable.ic_widget_imageview)
public class ImageViewFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[]{
                RadiusImageViewFragment.class,
//                PhotoPickerFragment.class,
//                PreviewFragment.class
        };
    }
}


