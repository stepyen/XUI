package com.stepyen.xuidemo.fragment.components;

import android.view.View;
import android.widget.LinearLayout;

import com.stepyen.xui.utils.ResUtils;
import com.stepyen.xui.widget.actionbar.TitleBar;
import com.stepyen.xui.widget.actionbar.TitleUtils;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "ActionBar", extra = R.drawable.ic_widget_imageview)
public class ActionbarFragment extends BaseFragment {
    private LinearLayout linearLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_actionbar;
    }

    @Override
    protected void initViews() {

        linearLayout = findViewById(R.id.ll_parent);

        TitleBar actionBar = TitleUtils.initTitleBarDynamic(getContext(), "ActionBar", v -> {
            getActivity().finish();
        });
        actionBar.setBackgroundColor(ResUtils.getColor(R.color.white));
        actionBar.setActionTextColor(ResUtils.getColor(R.color.black));

        actionBar.addAction(new TitleBar.TextAction("放进我的店") {
            @Override
            public void performAction(View view) {
                ToastUtils.toast("放进我的店");
            }

            @Override
            public int getDrawable() {
                return R.drawable.ic_add_store;
            }


            @Override
            public int leftPadding() {
                return 20;
            }

            @Override
            public int rightPadding() {
                return 20;
            }

        });

        actionBar.addAction(new TitleBar.TextAction("分享") {
            @Override
            public void performAction(View view) {
                ToastUtils.toast("分享");
            }

            @Override
            public int getDrawable() {
                return R.drawable.ic_share;
            }

            @Override
            public int leftPadding() {
                return 20;
            }

            @Override
            public int rightPadding() {
                return 20;
            }

        });


        linearLayout.addView(actionBar);

    }
}
