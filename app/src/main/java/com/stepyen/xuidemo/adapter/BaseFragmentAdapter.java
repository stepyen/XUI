package com.stepyen.xuidemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * date：2019/5/27
 * author：stepyen
 * description：FragmentPagerAdapter 使用于fragment较少的
 */
public class BaseFragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragmentList = new ArrayList<>();
    private CharSequence[] mTitles;
    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        setFragments(fragments);
    }

    public BaseFragmentAdapter(FragmentManager fm, T[] fragments) {
        super(fm);
        setFragments(Arrays.asList(fragments));
    }

    public BaseFragmentAdapter(FragmentManager fm, List<T> fragments, CharSequence[] titles) {
        super(fm);
        setFragments(fragments);
        this.mTitles = titles;
    }

    public BaseFragmentAdapter(FragmentManager fm, T[] fragments, CharSequence[] titles) {
        super(fm);
        setFragments(Arrays.asList(fragments));
        this.mTitles = titles;
    }

    public void addFragment(T fragment) {
        if (fragment != null) {
            mFragmentList.add(fragment);
        }
    }

    public void setFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            mFragmentList.clear();
            mFragmentList.addAll(fragments);
        }
    }

    public void addFragments(List<T> fragments) {
        if (fragments != null && fragments.size() > 0) {
            mFragmentList.addAll(fragments);
        }
    }

    @Override
    public T getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && position < mTitles.length) {
            return mTitles[position];
        }
        return super.getPageTitle(position);
    }

    public List<T> getFragmentLists() {
        return mFragmentList;
    }


}
