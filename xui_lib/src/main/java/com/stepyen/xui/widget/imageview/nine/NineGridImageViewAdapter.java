

package com.stepyen.xui.widget.imageview.nine;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * 九宫图适配器
 */
public abstract class NineGridImageViewAdapter<T> {

    /**
     * 图片加载
     *
     * @param context
     * @param imageView
     * @param t         图片信息
     */
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    /**
     * 图片点击
     *
     * @param context
     * @param imageView
     * @param index     索引
     * @param list      数据集合
     */
    protected void onItemImageClick(Context context, ImageView imageView, int index, List<T> list) {
    }

    /**
     * 图片长按
     *
     * @param context
     * @param imageView
     * @param index     索引
     * @param list      数据集合
     * @return
     */
    protected boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<T> list) {
        return false;
    }

    /**
     * 构建图片
     *
     * @param context
     * @return
     */
    protected ImageView generateImageView(Context context) {
        GridImageView imageView = new GridImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }



}