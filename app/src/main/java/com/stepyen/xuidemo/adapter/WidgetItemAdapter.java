package com.stepyen.xuidemo.adapter;

import com.stepyen.xuidemo.adapter.base.BaseRecyclerAdapter;
import com.xuexiang.xpage.model.PageInfo;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.adapter.base.RecyclerViewHolder;

import java.util.List;

public class WidgetItemAdapter extends BaseRecyclerAdapter<PageInfo> {

    public WidgetItemAdapter(List<PageInfo> list) {
        super(list);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return R.layout.layout_widget_item;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, PageInfo item) {
        holder.getTextView(R.id.item_name).setText(item.getName());
        if (item.getExtra() != 0) {
            holder.getImageView(R.id.item_icon).setImageResource(item.getExtra());
        }
    }

}
