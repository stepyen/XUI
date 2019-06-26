package com.stepyen.xuidemo.fragment.components;

import android.widget.Toast;

import com.stepyen.xui.widget.dialog.BottomMenuDialog;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xuidemo.base.BaseTestFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "对话框", extra = R.drawable.ic_widget_imageview)
public class DialogFragment extends BaseTestFragment {

    @Override
    protected void initViews() {
        super.initViews();
        addView("底部对话框", v -> {

            new BottomMenuDialog(getContext())
                    .addMenu("拍照", dialog -> {
                        showToast("拍照");
                        dialog.dismiss();
                    })
                    .addMenu("图集", dialog -> {
                        showToast("图集");
                        dialog.dismiss();
                    })
                    .builder()
                    .show();
        });
    }
}
