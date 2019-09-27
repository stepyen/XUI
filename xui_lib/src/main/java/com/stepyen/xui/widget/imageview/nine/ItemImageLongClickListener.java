

package com.stepyen.xui.widget.imageview.nine;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

/**
 * 九宫图长按
 */
public interface ItemImageLongClickListener<T> {
    boolean onItemImageLongClick(Context context, ImageView imageView, int index, List<T> list);
}
