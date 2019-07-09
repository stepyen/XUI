package com.stepyen.xuidemo.fragment.expands.rv_nest.item_provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.stepyen.xui.widget.banner.widget.banner.BannerItem;
import com.stepyen.xui.widget.banner.widget.banner.SimpleImageBanner;
import com.stepyen.xui.widget.banner.widget.banner.base.BaseBanner;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.fragment.expands.rv_nest.NormalMultipleEntity;
import com.stepyen.xutil.tip.ToastUtils;

import java.util.List;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */
public class BannerItemProvider extends BaseItemProvider<NormalMultipleEntity, BaseViewHolder> {
    private SimpleImageBanner sib_simple_usage;

    private List<BannerItem> mData;

    public BannerItemProvider() {
        mData = DataProvider.getBannerList();
    }

    @Override
    public int viewType() {
        return NormalMultipleEntity.TYPE_BANNER;
    }

    @Override
    public int layout() {
        return R.layout.vh_rv_nest_banner;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        sib_simple_usage =  helper.itemView.findViewById(R.id.sib_simple_usage);

        sib_simple_usage();
    }

    private void sib_simple_usage() {
        sib_simple_usage.setSource(mData)
                .setOnItemClickL(new BaseBanner.OnItemClickL() {
                    @Override
                    public void onItemClick(int position) {
                        ToastUtils.toast("position--->" + position);
                    }
                })
                .setIsOnePageLoop(false).startScroll();
    }

}
