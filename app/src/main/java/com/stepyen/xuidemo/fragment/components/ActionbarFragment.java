package com.stepyen.xuidemo.fragment.components;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

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
        actionBar.addAction(TitleBar.Action.newBuilder()
                .text("放进我的店")
                .build());
        actionBar.addAction(TitleBar.Action.newBuilder()
                .text("分享")
                .textColor(R.color.xui_config_color_red)
                .textSize(R.dimen.title_small)
                .drawable(R.drawable.ic_share)
                .padding(5)
                .clickListener(v->{
                    ToastUtils.toast("分享");
                })
                .build());


        linearLayout.addView(actionBar);

    }
}
