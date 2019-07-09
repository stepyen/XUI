package com.stepyen.xuidemo.activity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseActivity;
import com.stepyen.xuidemo.fragment.ComponentsFragment;
import com.stepyen.xuidemo.fragment.ExpandsFragment;
import com.stepyen.xuidemo.fragment.UtilitysFragment;
import butterknife.BindView;
/**
 *
 * 慢慢来，整理自己需要的
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        openPage(ComponentsFragment.class);
        initTab();
    }

    private void initTab() {
        TabLayout.Tab component = mTabLayout.newTab();
        component.setText("组件");
        component.setIcon(R.drawable.selector_icon_tabbar_component);
        mTabLayout.addTab(component);

        TabLayout.Tab util = mTabLayout.newTab();
        util.setText("工具");
        util.setIcon(R.drawable.selector_icon_tabbar_util);
        mTabLayout.addTab(util);

        TabLayout.Tab expand = mTabLayout.newTab();
        expand.setText("拓展");
        expand.setIcon(R.drawable.selector_icon_tabbar_expand);
        mTabLayout.addTab(expand);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        switchPage(ComponentsFragment.class);
                        break;
                    case 1:
                        switchPage(UtilitysFragment.class);
                        break;
                    case 2:
                        switchPage(ExpandsFragment.class);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
