package com.stepyen.xuidemo.fragment.components;

import com.daidingkang.SnapUpCountDownTimerView;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

/**
 * date：2019/6/26
 * author：stepyen
 * description：
 */
@Page(name = "文字", extra = R.drawable.ic_widget_imageview)
public class TextViewFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_textview;

    }

    @Override
    protected void initViews() {
        // 暂时先放在这，倒计时后面再弄   
        SnapUpCountDownTimerView rushBuyCountDownTimerView = (SnapUpCountDownTimerView) findViewById(R.id.RushBuyCountDownTimerView);
        rushBuyCountDownTimerView.setTime(0,1,3);//设置小时，分钟，秒。注意不能大于正常值，否则会抛出异常
        rushBuyCountDownTimerView.start();//开始倒计时
    }
}
