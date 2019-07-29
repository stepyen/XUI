package com.stepyen.xuidemo.fragment.components;

import android.os.Handler;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.widget.dialog.BottomMenuDialog;
import com.stepyen.xui.widget.dialog.ListDialog;
import com.stepyen.xui.widget.dialog.MiniLoadingDialog;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xuidemo.base.BaseTestFragment;
import com.stepyen.xutil.tip.ToastUtils;
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

        addView("中间列表对话框", v -> {
            new ListDialog(getContext())
                    .addTextView("我是学员",v1->{
                        ToastUtils.toast("我是学员");
                    })
                    .addTextView("我是教练",v1->{
                        ToastUtils.toast("我是教练");
                    },false).show();
        });

        addView("底部列表对话框", v -> {
            new ListDialog(getContext())
                    .setGravity(Gravity.BOTTOM)
                    .setWindowSize((int) (DensityUtils.getDisplayMetrics().widthPixels*0.9), ViewGroup.LayoutParams.WRAP_CONTENT)
                    .setWindowAnimations(R.style.Animation_Bottom_Rising)
                    .setDimAmount(0)
                    .setCancleVisibility(true)
                    .addTextView("我是学员",v1->{
                        ToastUtils.toast("我是学员");
                    })
                    .addTextView("我是教练",v1->{
                        ToastUtils.toast("我是教练");
                    },false).show();
        });


        addView("MiniLoadingDialog", v -> {
            MiniLoadingDialog miniLoadingDialog = new MiniLoadingDialog(getContext());
            miniLoadingDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    miniLoadingDialog.dismiss();
                }
            }, 3000);
        });
    }
}
