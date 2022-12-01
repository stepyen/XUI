package com.stepyen.xuidemo.fragment.expands.rv_nest;



import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * date：2019/7/9
 * author：stepyen
 * description：
 */


@Page(name = "rv嵌套实现1")
public class RvNest1Fragment extends BaseFragment {
    @BindView(R.id.rv_nest)
    RecyclerView mRvNest;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rv_nest_1;
    }

    @Override
    protected void initViews() {
        RvNest1Adapter multipleItemAdapter = new RvNest1Adapter(getNormalMultipleEntities());
        mRvNest.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvNest.setAdapter(multipleItemAdapter);
//        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
//                int type = mData.get(position).type;
//                if (type == NormalMultipleEntity.SINGLE_TEXT) {
//                    return MultipleItem.TEXT_SPAN_SIZE;
//                } else if (type == NormalMultipleEntity.SINGLE_IMG) {
//                    return MultipleItem.IMG_SPAN_SIZE;
//                } else {
//                    return MultipleItem.IMG_TEXT_SPAN_SIZE;
//                }
//            }
//        });
    }

    public static List<NormalMultipleEntity> getNormalMultipleEntities() {
        List<NormalMultipleEntity> list = new ArrayList<>();
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TYPE_BANNER));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TYPE_VIEWPAGER));
        list.add(new NormalMultipleEntity(NormalMultipleEntity.TYPE_RECYCLEVIEW));
        return list;
    }
}
