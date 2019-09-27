/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stepyen.xui.widget.imageview.preview.enitity;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 图片预览实体类
 */
public class ImageViewInfo implements IPreviewInfo {

    private String mUrl;  //图片地址
    private Rect mBounds; // 记录坐标
    private String mVideoUrl;

    private String mDescription = "描述信息";

    private ImageViewInfo(String url) {
        mUrl = url;
    }

    public static ImageViewInfo newBuilder(String url) {
        return new ImageViewInfo(url);
    }


    public String getDescription() {
        return mDescription;
    }

    public ImageViewInfo setDescription(String description) {
        mDescription = description;
        return this;
    }

    @Override
    public String getUrl() {//将你的图片地址字段返回
        return mUrl;
    }

    public ImageViewInfo setUrl(String url) {
        mUrl = url;
        return this;
    }

    @Override
    public Rect getBounds() {//将你的图片显示坐标字段返回
        return mBounds;
    }

    @Nullable
    @Override
    public String getVideoUrl() {
        return mVideoUrl;
    }

    public ImageViewInfo setBounds(Rect bounds) {
        mBounds = bounds;
        return this;
    }

    /**
     * 通过设置view记录坐标
     * @param view
     */
    public ImageViewInfo setBoundsByView(View view) {
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        mBounds = rect;
        return this;
    }


    public ImageViewInfo setVideoUrl(String videoUrl) {
        mVideoUrl = videoUrl;
        return this;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mUrl);
        dest.writeParcelable(mBounds, flags);
        dest.writeString(mDescription);
        dest.writeString(mVideoUrl);
    }

    protected ImageViewInfo(Parcel in) {
        mUrl = in.readString();
        mBounds = in.readParcelable(Rect.class.getClassLoader());
        mDescription = in.readString();
        mVideoUrl = in.readString();
    }

    public static final Parcelable.Creator<ImageViewInfo> CREATOR = new Parcelable.Creator<ImageViewInfo>() {
        @Override
        public ImageViewInfo createFromParcel(Parcel source) {
            return new ImageViewInfo(source);
        }

        @Override
        public ImageViewInfo[] newArray(int size) {
            return new ImageViewInfo[size];
        }
    };
}
