package com.stepyen.xuidemo.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stepyen.xui.widget.linearlayout.AutoNewLineLayout;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseTestActivity;
import com.stepyen.xutil.display.DensityUtils;
import com.stepyen.xutil.shape.ShapeBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date：2019/6/24
 * author：stepyen
 * description：布局
 */
public class LayoutActivity extends BaseTestActivity {
    @BindView(R.id.autonew_layout)
    AutoNewLineLayout mAutonewLayout;

    @Override
    public void addView() {
        addTextView("自动换行");
        addView(R.layout.activity_layout);
    }

    @Override
    public void initView() {

        initTag(mAutonewLayout,DataProvider.getTags());
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

            TextView tv = new TextView(this);
            tv.setText(text);
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(DensityUtils.dip2px(3),
                    DensityUtils.dip2px(1) ,
                    DensityUtils.dip2px(3),
                    DensityUtils.dip2px(1));
            ShapeBuilder.create(this)
                    .radius(2)
                    .solid(R.color.gray_7)
                    .build(tv);

            viewParent.addView(tv);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
