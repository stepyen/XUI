package com.stepyen.xuidemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.github.chrisbanes.photoview.PhotoView;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * date：2019/7/4
 * author：stepyen
 * description：
 */
public class PreviewActivity extends BaseActivity {
    public static final String KEY_URL = "KEY_URL";
    @Nullable
    @BindView(R.id.photo_view)
    PhotoView mPhotoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);

    }


}
