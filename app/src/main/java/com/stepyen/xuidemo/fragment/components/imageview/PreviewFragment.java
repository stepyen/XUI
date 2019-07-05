package com.stepyen.xuidemo.fragment.components.imageview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.activity.PreviewActivity;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date：2019/7/4
 * author：stepyen
 * description：
 */
@Page(name = "预览图片")
public class PreviewFragment extends BaseFragment {
    @BindView(R.id.iv_preview)
    ImageView mIvPreview;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    protected void initViews() {
        mIvPreview.setOnClickListener(v->{
            Intent intent = new Intent(getContext(), PreviewActivity.class);
            startActivity(intent);
        });
    }



}
