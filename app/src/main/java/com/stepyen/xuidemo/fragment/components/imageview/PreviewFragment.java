package com.stepyen.xuidemo.fragment.components.imageview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stepyen.xui.widget.imageview.nine.ItemImageClickListener;
import com.stepyen.xui.widget.imageview.nine.NineGridImageView;
import com.stepyen.xui.widget.imageview.nine.NineGridImageViewAdapter;
import com.stepyen.xui.widget.imageview.preview.PreviewBuilder;
import com.stepyen.xui.widget.imageview.preview.enitity.IPreviewInfo;
import com.stepyen.xui.widget.imageview.preview.enitity.ImageViewInfo;
import com.stepyen.xui.widget.imageview.preview.loader.GlideMediaLoader;
import com.stepyen.xuidemo.DataProvider;
import com.stepyen.xuidemo.R;
import com.stepyen.xuidemo.base.BaseFragment;
import com.xuexiang.xpage.annotation.Page;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * date：2019/7/4
 * author：stepyen
 * description：
 */
@Page(name = "预览图片")
public class PreviewFragment extends BaseFragment {
    @BindView(R.id.iv_preview)
    ImageView iv_preview;
    @BindView(R.id.ngl_images)
    NineGridImageView<ImageViewInfo> ngl_images;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_preview;
    }

    @Override
    protected void initViews() {
        initImageView();
        initNineGridImageView();
    }

    private void initImageView() {
        iv_preview.setOnClickListener(v -> {
            ImageViewInfo imageViewInfo = ImageViewInfo.newBuilder("")
                    .setBoundsByView(iv_preview);

            PreviewBuilder.from(getActivity())
                    .setImg(imageViewInfo)
                    .start();
        });
    }

    private void initNineGridImageView() {
        NineGridImageViewAdapter<ImageViewInfo> mAdapter = new NineGridImageViewAdapter<ImageViewInfo>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, ImageViewInfo imageViewInfo) {
                Glide.with(imageView.getContext())
                        .load(imageViewInfo.getUrl())
                        .apply(GlideMediaLoader.getRequestOptions())
                        .into(imageView);
            }
        };

        ngl_images.setAdapter(mAdapter);
        ngl_images.setItemImageClickListener(new ItemImageClickListener<ImageViewInfo>() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List<ImageViewInfo> list) {
                computeBoundsBackward(list);//组成数据
                PreviewBuilder.from((Activity) context)
                        .setImgs(list)
                        .setCurrentIndex(index)
                        .setType(PreviewBuilder.IndicatorType.Dot)
                        .start();//启动
            }
        });
        ngl_images.setImagesData(getImgUrlList(), NineGridImageView.STYLE_GRID);
    }


    private void computeBoundsBackward(List<ImageViewInfo> list) {
        for (int i = 0; i < ngl_images.getChildCount(); i++) {
            View itemView = ngl_images.getChildAt(i);
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView;
                list.get(i).setBoundsByView(thumbView);
            }
        }
    }


    private List<ImageViewInfo> getImgUrlList() {
        ArrayList<ImageViewInfo> data = new ArrayList<ImageViewInfo>();
        int size = DataProvider.urls.length;
        for (int i = 0; i < size; i++) {
            data.add(ImageViewInfo.newBuilder(DataProvider.urls[i]));
        }
        return data;
    }


}
