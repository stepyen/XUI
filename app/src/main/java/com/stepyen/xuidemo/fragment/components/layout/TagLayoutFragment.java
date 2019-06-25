package com.stepyen.xuidemo.fragment.components.layout;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stepyen.xui.widget.linearlayout.AutoNewLineLayout;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.display.DensityUtils;
import com.stepyen.xutil.shape.ShapeBuilder;
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
    @BindView(R.id.autonew_layout)
    AutoNewLineLayout mAutonewLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tag;
    }

    @Override
    protected void initViews() {
        initTag(mAutonewLayout, DataProvider.getTags());
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
}
