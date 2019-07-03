package com.stepyen.xuidemo.fragment.components.banner;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.stepyen.xui.widget.actionbar.TitleBar;
import com.stepyen.xui.widget.banner.anim.select.ZoomInEnter;
import com.stepyen.xui.widget.banner.widget.banner.SimpleGuideBanner;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import static com.stepyen.xuidemo.fragment.components.banner.UserGuideFragment.POSITION;


@Page(name = "启动引导页", params = {POSITION})
public class UserGuideFragment extends BaseFragment {

    public final static String POSITION = "position";

    private Class<? extends ViewPager.PageTransformer> transformerClass;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_guide;
    }

    @Override
    protected void initArgs() {
        Bundle args = getArguments();
        if (args != null) {
            int position = args.getInt(POSITION, -1);
            transformerClass = position != -1 ? DataProvider.transformers[position] : null;
        }
    }

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected void initViews() {
        sgb();
    }



    @Override
    protected void initListeners() {

    }


    private void sgb() {
        SimpleGuideBanner sgb = findViewById(R.id.sgb);

        sgb
                .setIndicatorWidth(6)
                .setIndicatorHeight(6)
                .setIndicatorGap(12)
                .setIndicatorCornerRadius(3.5f)
                .setSelectAnimClass(ZoomInEnter.class)
                .setTransformerClass(transformerClass)
                .barPadding(0, 10, 0, 10)
                .setSource(DataProvider.getUsertGuides())
                .startScroll();

        sgb.setOnJumpClickListener(new SimpleGuideBanner.OnJumpClickListener() {
            @Override
            public void onJumpClick() {
                popToBack();
            }
        });
    }
}
