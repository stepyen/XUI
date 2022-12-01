package com.stepyen.xuidemo.fragment.components;

import android.content.Context;

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
import com.xuexiang.xpage.annotation.Page;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * date：2019/7/8
 * author：stepyen
 * description：
 */

@Page(name = "指示器", extra = R.drawable.ic_widget_imageview)
public class IndicaterFragment extends BaseFragment {

    @BindView(R.id.contentViewPager1)
    ViewPager mContentViewPager1;
    @BindView(R.id.tabSegment1)
    TabSegment mTabSegment1;
    @BindView(R.id.contentViewPager2)
    ViewPager mContentViewPager2;
    @BindView(R.id.tabSegment2)
    TabSegment mTabSegment2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_indicater;
    }

    @Override
    protected void initViews() {
        initTabs1();
        initTabs2();

    }

    private void initTabs1() {
        List<String> titles = Arrays.asList(DataProvider.getTabTitles());

        mTabSegment1.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_home_normal),
                ResUtils.getDrawable(R.drawable.ic_home_check),
                titles.get(0), false));

        mTabSegment1.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_order_normal),
                ResUtils.getDrawable(R.drawable.ic_order_check),
                titles.get(1), false));


        mTabSegment1.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_message_normal),
                ResUtils.getDrawable(R.drawable.ic_message_check),
                titles.get(2), false));

        mTabSegment1.addTab(new TabSegment.Tab(
                ResUtils.getDrawable(R.drawable.ic_my_normal),
                ResUtils.getDrawable(R.drawable.ic_my_check),
                titles.get(3), false));

        mContentViewPager1.setAdapter(new Adapter(getContext(), titles));
        mContentViewPager1.setCurrentItem(0, false);
        mTabSegment1.setupWithViewPager(mContentViewPager1, false);
    }

    private void initTabs2() {
        List<String> titles = DataProvider.getTags();

        mTabSegment2.addTab(new TabSegment.Tab(titles.get(0)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(1)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(2)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(3)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(4)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(5)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(6)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(7)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(8)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(9)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(10)));
        mTabSegment2.addTab(new TabSegment.Tab(titles.get(11)));


        mContentViewPager2.setAdapter(new Adapter(getContext(), titles));
        mContentViewPager2.setCurrentItem(0, false);
        mTabSegment2.setupWithViewPager(mContentViewPager2, false);

    }

    private static class Adapter extends PagerAdapter {

        private Map<String, View> mPageMap = new HashMap<>();
        List<String> titles;
        private Context mContext;
        public Adapter(Context context, List<String> titles) {
            this.mContext = context;
            this.titles = titles;
        }

        private View getPageView(String page) {
            View view = mPageMap.get(page);
            if (view == null) {
                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER);
                textView.setText(String.format("这个是%s页面的内容", page));
                view = textView;
                mPageMap.put(page, view);
            }
            return view;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {

            View view = getPageView(titles.get(position));
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