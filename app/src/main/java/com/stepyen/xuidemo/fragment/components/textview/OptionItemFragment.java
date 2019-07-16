package com.stepyen.xuidemo.fragment.components.textview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.stepyen.xui.widget.radiogruop.RadioGroupPlus;
import com.stepyen.xui.widget.textview.ShapeTextView;
import com.stepyen.xui.widget.textview.supertextview.OptionItem;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * date：2019/6/28
 * author：stepyen
 * description：
 */
@Page(name = "列表项", extra = R.drawable.ic_widget_imageview)
public class OptionItemFragment extends BaseFragment {
    @BindView(R.id.ll_super_text_parent)
    LinearLayout mLlSuperTextParent;
    @BindView(R.id.stv_test_click)
    OptionItem mStvTestClick;
    @BindView(R.id.radiogroupplus_super_text)
    RadioGroupPlus mRadiogroupplusSuperText;
    @BindView(R.id.superbtn_comfrim)
    ShapeTextView mSuperbtnComfrim;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_option_item;
    }

    @Override
    protected void initViews() {
        mStvTestClick.setOnSuperTextViewClickListener(new OptionItem.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(OptionItem optionItem) {
                ToastUtils.toast("整个View");
            }
        }).setLeftTvClickListener(new OptionItem.OnLeftTvClickListener() {
            @Override
            public void onClickListener() {
                ToastUtils.toast("左边文字");
            }
        });



    }

    @OnClick(R.id.superbtn_comfrim)
    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.superbtn_comfrim:
                comfirmPay();
                break;
        }
    }


    private void comfirmPay() {
        int checkedRadioButtonId = mRadiogroupplusSuperText.getCheckedRadioButtonId();
        switch (checkedRadioButtonId) {
            case R.id.RadioButton_id_alipay:
                ToastUtils.toast("支付宝支付");
                break;
            case R.id.RadioButton_id_wechat:
                ToastUtils.toast("微信支付");
                break;
            case R.id.RadioButton_id_bank:
                ToastUtils.toast("银联支付");
                break;
        }
    }



}
