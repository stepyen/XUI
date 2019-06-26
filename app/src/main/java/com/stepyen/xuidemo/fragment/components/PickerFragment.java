package com.stepyen.xuidemo.fragment.components;
import android.graphics.drawable.Drawable;
import com.stepyen.xui.utils.shape.ShapeBuilder;
import com.stepyen.xui.widget.picker.NumberPicker;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;
import butterknife.BindView;

/**
 * date：2019/6/25
 * author：stepyen
 * description：选择器
 */
@Page(name = "选择器", extra = R.drawable.ic_widget_imageview)
public class PickerFragment extends BaseFragment {

    @BindView(R.id.number_picker)
    NumberPicker mNumberPicker;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picker;
    }

    @Override
    protected void initViews() {
        initNumberPicker();

    }

    private void initNumberPicker() {
        mNumberPicker.setNumberLimitListen(new NumberPicker.OnNumberLimitListen() {
            @Override
            public void isMinimum() {
                ToastUtils.toast("已经是最小了");
            }

            @Override
            public void isMaximum() {
                ToastUtils.toast("已经是最大了");
            }
        });


        ShapeBuilder.create(getContext())
                .solid(R.color.white)
                .radius(22.5f)
                .stroke(0.5f, R.color.xui_gray_7)
                .build(mNumberPicker);

        Drawable drawable = ShapeBuilder.create(getContext())
                .solid(R.color.white)
                .stroke(0.5f, R.color.xui_gray_7)
                .build();


        mNumberPicker.setValueViewBackground(drawable);
    }

}
