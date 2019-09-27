

package com.stepyen.xui.widget.imageview.nine;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * 九宫图条目点击
 */
public interface ItemImageClickListener<T> {
    void onItemImageClick(Context context, ImageView imageView, int index, List<T> list);
}
