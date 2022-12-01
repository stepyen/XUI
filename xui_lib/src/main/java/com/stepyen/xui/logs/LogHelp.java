package com.stepyen.xui.logs;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * date：2019-06-25
 * author：stepyen
 * description: 日志打印
 */
public class LogHelp {


    /**
     * 打印view的相关信息
     */
    public static void printView(String tag, View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nwidth: " + view.getWidth());
        sb.append("\nheight: " + view.getHeight());
        sb.append("\nmeasuredWidth: " + view.getMeasuredWidth());
        sb.append("\nmeasuredHeight: " + view.getMeasuredHeight());
        sb.append("\n----");
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp != null) {
            String lpWidth = "";
            if (lp.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
                lpWidth = "ViewGroup.LayoutParams.WRAP_CONTENT，值为-2";
            } else if (lp.width == ViewGroup.LayoutParams.FILL_PARENT || lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
                lpWidth = "ViewGroup.LayoutParams.MATCH_PARENT，值为-1";
            } else {
                lpWidth = lp.width + "";
            }
            String lpHeight = "";
            if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                lpHeight = "ViewGroup.LayoutParams.WRAP_CONTENT，值为-2";
            } else if (lp.height == ViewGroup.LayoutParams.FILL_PARENT || lp.width == ViewGroup.LayoutParams.MATCH_PARENT) {
                lpHeight = "ViewGroup.LayoutParams.MATCH_PARENT，值为-1";
            } else {
                lpHeight = lp.height + "";
            }

            sb.append("\nLayoutParams width: " + lpWidth);

            sb.append("\nLayoutParams height: " + lpHeight);
        }
        sb.append("\n----");
        sb.append("\nleft: " + view.getLeft());
        sb.append("\ntop: " + view.getTop());
        sb.append("\nright: " + view.getRight());
        sb.append("\nbottom: " + view.getBottom());
        sb.append("\n----");
        sb.append("\nx: " + view.getX());
        sb.append("\ny: " + view.getY());
        sb.append("\n----");

        Log.i(tag, "\nprintView: " + sb.toString());
    }


    /**
     * 打印Intent的相关信息
     * <p>
     * 代码：
     * Intent intent = new Intent();
     * intent.putExtra("key", "value");
     * <p>
     * Bundle bundle = new Bundle();
     * bundle.putString("Bundlekey", "Bundlevalue");
     * <p>
     * intent.putExtra("Bundle", bundle);
     * <p>
     * 打印
     * Intent : Intent { (has extras) }
     * Intent extras : Bundle[{key=value, Bundle=Bundle[{Bundlekey=Bundlevalue}]}]
     */
    public static void printIntent(String tag, Intent intent) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nIntent : " + intent.toString());

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            sb.append("\nIntent extras : " + bundle.toString());
            for (String key : bundle.keySet()) {
                sb.append("\nBundle Content:  " + "Key=" + key + ", value=" + bundle.get(key));
            }
        }

        Log.i(tag, "\nprintIntent: " + sb.toString());
    }


}
