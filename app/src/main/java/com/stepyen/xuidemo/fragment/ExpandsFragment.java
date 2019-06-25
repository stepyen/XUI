
package com.stepyen.xuidemo.fragment;
import com.xuexiang.xpage.AppPageConfig;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xpage.model.PageInfo;

import java.util.ArrayList;
import java.util.List;

@Page(name = "拓展", anim = CoreAnim.none)
public class ExpandsFragment extends BaseHomeFragment {

    @Override
    protected List<PageInfo> getPageContents() {
        return AppPageConfig.getInstance().getExpands();
    }
}
