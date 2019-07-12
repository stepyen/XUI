package com.stepyen.xuidemo.fragment.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepyen.xui.utils.ResUtils;
import com.stepyen.xui.widget.spinner.MaterialSpinner;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.display.SnackbarUtils;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date：2019/7/12
 * author：stepyen
 * description：
 */
@Page(name = "下拉框", extra = R.drawable.ic_widget_imageview)
public class SpinnerFragment extends BaseFragment {
    @BindView(R.id.spinner1)
    MaterialSpinner mSpinner1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_spinner;
    }

    @Override
    protected void initViews() {
        mSpinner1.setItems(ResUtils.getStringArray(R.array.sort_mode_entry));
        mSpinner1.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner spinner, int position, long id, Object item) {
                ToastUtils.toast("Clicked " + item);
            }
        });
    }

}
