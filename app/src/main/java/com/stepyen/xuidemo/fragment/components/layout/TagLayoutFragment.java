package com.stepyen.xuidemo.fragment.components.layout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stepyen.xui.widget.linearlayout.StarLayout;
import com.stepyen.xui.widget.linearlayout.flow_tag.FlowTagLayout;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.display.DensityUtils;
import com.stepyen.xutil.resource.ResUtils;
import com.stepyen.xutil.shape.ShapeBuilder;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;
import java.util.List;
import butterknife.BindView;

/**
 * date：2019/6/25
 * author：stepyen
 * description：
 */
@Page(name = "标签布局")
public class TagLayoutFragment extends BaseFragment {
    @BindView(R.id.ftl_tag_none)
    FlowTagLayout ftl_tag_none;
    @BindView(R.id.ftl_tag_single_select)
    FlowTagLayout ftl_tag_single_select;
    @BindView(R.id.ftl_tag_multiple_select)
    FlowTagLayout ftl_tag_multiple_select;
    @BindView(R.id.starlayout)
    StarLayout starLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void initViews() {
        initTag(ftl_tag_none, DataProvider.getTags());
        initSingleSelect();
        initMultipleSelect();
        initStarLayout();
    }

    private void initStarLayout() {
        starLayout.setAllNumber(5);
        starLayout.setDefaultStarWidth(DensityUtils.dip2px(34f));
        starLayout.setDefaultStarHeight(DensityUtils.dip2px(30f));
        starLayout.setCheckStartDrawable(R.drawable.ic_star_check);
        starLayout.setDefaultStartDrawable(R.drawable.ic_star_default);
        starLayout.initView();

        starLayout.setNumber(3);

    }


    private void initTag(LinearLayout viewParent, List<String> data) {
        if (data == null) {
            return;
        }
        int size = data.size();
        for (int i = 0; i < size; i++) {
            String text = data.get(i).trim();
            if (TextUtils.isEmpty(text)) {
                continue;
            }

            TextView tv = new TextView(getContext());
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(DensityUtils.dip2px(3),
                    DensityUtils.dip2px(1),
                    DensityUtils.dip2px(3),
                    DensityUtils.dip2px(1));
            ShapeBuilder.create(getContext())
                    .radius(2)
                    .solid(R.color.xui_gray_6)
                    .build(tv);

            viewParent.addView(tv);
        }
    }

    private void initSingleSelect() {

    }

    private void initMultipleSelect() {
        ftl_tag_multiple_select.setItems(DataProvider.getTags().subList(0, 5));
        ftl_tag_multiple_select.setOnTagClickListener(new FlowTagLayout.OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {
                ToastUtils.toast("点击了标签");
            }
        });

    }

}
