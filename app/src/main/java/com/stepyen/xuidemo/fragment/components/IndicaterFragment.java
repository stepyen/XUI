package com.stepyen.xuidemo.fragment.components;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stepyen.xui.widget.tabbar.TabSegment;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.stepyen.xutil.resource.ResUtils;
import com.stepyen.xutil.tip.ToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * date：2019/7/8
 * author：stepyen
 * description：
 */

@Page(name = "指示器", extra = R.drawable.ic_widget_imageview)
public class IndicaterFragment extends BaseFragment {
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;
    @BindView(R.id.tabSegment)
    TabSegment mTabSegment;

    private Map<String, View> mPageMap = new HashMap<>();
    String[] titles;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_indicater;
    }

    @Override
    protected void initViews() {
        titles = DataProvider.getTabTitles();

        mTabSegment.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_home_normal),
                ResUtils.getDrawable(R.drawable.ic_home_check),
                titles[0], false));

        mTabSegment.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_order_normal),
                ResUtils.getDrawable(R.drawable.ic_order_check),
                titles[1], false));


        mTabSegment.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_message_normal),
                ResUtils.getDrawable(R.drawable.ic_message_check),
                titles[2], false));

        mTabSegment.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_my_normal),
                ResUtils.getDrawable(R.drawable.ic_my_check),
                titles[3], false));

        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(0, false);
        mTabSegment.setupWithViewPager(mContentViewPager, false);

    }


    private View getPageView(String page) {
        View view = mPageMap.get(page);
        if (view == null) {
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(String.format("这个是%s页面的内容", page));
            view = textView;
            mPageMap.put(page, view);
        }
        return view;
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return DataProvider.getTabTitles().length;
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {

            View view = getPageView(titles[position]);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };


}