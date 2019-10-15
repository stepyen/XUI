package com.stepyen.xuidemo.fragment.components.textview;

import android.support.v7.util.DiffUtil;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.daidingkang.SnapUpCountDownTimerView;
import com.stepyen.xui.utils.ResUtils;
import com.stepyen.xui.widget.textview.supertextview.OptionItemTitleValue;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xuidemo.fragment.components.textview.badge.BadgeView;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/28
 * author：stepyen
 * description：
 */
@Page(name = "一些TextView")
public class OtherTextViewFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_other_text_view;
    }

    @Override
    protected void initViews() {
        // 暂时先放在这，倒计时后面再弄
        SnapUpCountDownTimerView rushBuyCountDownTimerView = (SnapUpCountDownTimerView) findViewById(R.id.RushBuyCountDownTimerView);
        rushBuyCountDownTimerView.setTime(0, 1, 3);//设置小时，分钟，秒。注意不能大于正常值，否则会抛出异常
        rushBuyCountDownTimerView.start();//开始倒计时


        OptionItemTitleValue optionItemTitleValue = findViewById(R.id.optv_1);
        optionItemTitleValue
                .setTitle("证件图片：")
                .setValue("查看")
                .setValueClick(v->{
                    ToastUtils.toast("查看图片");
                });

        ImageView badgeIv = findViewById(R.id.iv_badge);
        new BadgeView(getContext())
                .bindTarget(badgeIv)
                .setBadgeNumber(5)
                .setBadgeGravity(Gravity.TOP | Gravity.END)
                .setBadgeTextColor(ResUtils.getColor(R.color.white))
                .setBadgeBackgroundColor(ResUtils.getColor(R.color.xui_config_color_red));

    }
}
