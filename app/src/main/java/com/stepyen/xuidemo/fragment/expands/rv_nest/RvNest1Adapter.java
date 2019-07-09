package com.stepyen.xuidemo.fragment.expands.rv_nest;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.stepyen.xuidemo.fragment.expands.rv_nest.item_provider.BannerItemProvider;
import com.stepyen.xuidemo.fragment.expands.rv_nest.item_provider.RecycleviewItemProvider;
import com.stepyen.xuidemo.fragment.expands.rv_nest.item_provider.ViewpagerItemProvider;

import java.util.List;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */
public class RvNest1Adapter extends MultipleItemRvAdapter<NormalMultipleEntity, BaseViewHolder> {
    public RvNest1Adapter(@Nullable List<NormalMultipleEntity> data) {
        super(data);
        finishInitialize();
    }

    @Override
    protected int getViewType(NormalMultipleEntity entity) {
        return entity.getType();
    }

    @Override
    public void registerItemProvider() {
        mProviderDelegate.registerProvider(new BannerItemProvider());
        mProviderDelegate.registerProvider(new ViewpagerItemProvider());
        mProviderDelegate.registerProvider(new RecycleviewItemProvider());
    }
}
