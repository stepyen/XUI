

package com.stepyen.xuidemo.fragment.components;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.ComponentContainerFragment;
import com.stepyen.xuidemo.fragment.components.banner.RecyclerViewBannerFragment;
import com.stepyen.xuidemo.fragment.components.banner.ViewPagerBannerFragment;
import com.xuexiang.xpage.annotation.Page;


@Page(name = "轮播条", extra = R.drawable.ic_widget_banner)
public class BannerFragment extends ComponentContainerFragment {
    /**
     * 获取页面的类集合[使用@Page注解进行注册的页面]
     *
     * @return
     */
    @Override
    protected Class[] getPagesClasses() {
        return new Class[] {
                ViewPagerBannerFragment.class,
                RecyclerViewBannerFragment.class
        };
    }
}
