package com.stepyen.xuidemo.fragment;
import com.xuexiang.xpage.AppPageConfig;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.model.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 组件的主要界面
 */
@Page(name = "组件", anim = CoreAnim.none)
public class ComponentsFragment extends BaseHomeFragment {

    @Override
    protected List<PageInfo> getPageContents() {
        return AppPageConfig.getInstance().getComponents();
    }

}
