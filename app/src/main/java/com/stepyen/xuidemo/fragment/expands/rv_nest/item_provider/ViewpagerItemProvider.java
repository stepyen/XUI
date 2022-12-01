package com.stepyen.xuidemo.fragment.expands.rv_nest.item_provider;

import android.content.Context;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.widget.tabbar.TabSegment;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.fragment.components.IndicaterFragment;
import com.stepyen.xuidemo.fragment.expands.rv_nest.NormalMultipleEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */
public class ViewpagerItemProvider extends BaseItemProvider<NormalMultipleEntity, BaseViewHolder> {
    ViewPager mContentViewPager2;
    TabSegment mTabSegment2;

    @Override
    public int viewType() {
        return NormalMultipleEntity.TYPE_VIEWPAGER;
    }

    @Override
    public int layout() {
        return R.layout.vh_rv_viewpager;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        mTabSegment2 =  helper.itemView.findViewById(R.id.tabSegment2);
        mContentViewPager2 =  helper.itemView.findViewById(R.id.contentViewPager2);

        initTabs2();
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

        mContentViewPager2.setAdapter(new Adapter(mContext, titles));
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
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtils.dp2px(200));
            container.addView(view, params);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

}
