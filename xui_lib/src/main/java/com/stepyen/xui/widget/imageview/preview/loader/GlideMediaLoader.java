

package com.stepyen.xui.widget.imageview.preview.loader;

import android.content.Context;
import android.graphics.Bitmap;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.stepyen.xui.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Glide多媒体加载
 */
public class GlideMediaLoader implements IMediaLoader {

    private RequestOptions mRequestOptions;

    public GlideMediaLoader() {
        this(new RequestOptions()
                .error(R.drawable.xui_ic_no_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL));
    }

    public GlideMediaLoader(RequestOptions requestOptions) {
        mRequestOptions = requestOptions;
    }

    /**
     * 加载图片
     *
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    @Override
    public void displayImage(@NonNull Fragment fragment, @NonNull String path, ImageView imageView, @NonNull final ISimpleTarget simpleTarget) {
        Glide.with(fragment)
                .asBitmap()
                .apply(mRequestOptions)
                .load(path)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        simpleTarget.onLoadFailed(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        simpleTarget.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 加载gif 图
     *
     * @param path         图片你的路径
     * @param imageView
     * @param simpleTarget 图片加载状态回调
     */
    @Override
    public void displayGifImage(@NonNull Fragment fragment, @NonNull String path, ImageView imageView, @NonNull final ISimpleTarget simpleTarget) {
        Glide.with(fragment)
                .asGif()
                .apply(mRequestOptions)
                .load(path)
                .listener(new RequestListener<GifDrawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                        simpleTarget.onLoadFailed(null);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        simpleTarget.onResourceReady();
                        return false;
                    }
                })
                .into(imageView);
    }

    /**
     * 停止
     *
     **/
    @Override
    public void onStop(@NonNull Fragment fragment) {
        Glide.with(fragment).onStop();
    }

    /**
     * 停止
     *
     * @param c 容器
     **/
    @Override
    public void clearMemory(@NonNull Context c) {
        Glide.get(c).clearMemory();
    }


    /**
     * @return 获取glide请求参数
     */
    public static RequestOptions getRequestOptions() {
        return new RequestOptions()
                .placeholder(R.drawable.xui_ic_default_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }
}
