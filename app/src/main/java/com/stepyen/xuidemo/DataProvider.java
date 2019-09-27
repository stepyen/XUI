package com.stepyen.xuidemo;

import android.support.v4.view.ViewPager;

import com.stepyen.xui.widget.banner.transform.DepthTransformer;
import com.stepyen.xui.widget.banner.transform.FadeSlideTransformer;
import com.stepyen.xui.widget.banner.transform.FlowTransformer;
import com.stepyen.xui.widget.banner.transform.RotateDownTransformer;
import com.stepyen.xui.widget.banner.transform.RotateUpTransformer;
import com.stepyen.xui.widget.banner.transform.ZoomOutSlideTransformer;
import com.stepyen.xui.widget.banner.widget.banner.BannerItem;

import java.util.ArrayList;
import java.util.List;

/**
 * date：2019/6/24
 * author：stepyen
 * description：演示数据
 */
public class DataProvider {

    public static String[] titles = new String[]{
            "伪装者:胡歌演绎'痞子特工'",
            "无心法师:生死离别!月牙遭虐杀",
            "花千骨:尊上沦为花千骨",
            "综艺饭:胖轩偷看夏天洗澡掀波澜",
            "碟中谍4:阿汤哥高塔命悬一线,超越不可能",
    };


    public static String[] urls = new String[]{//640*360 360/640=0.5625
            "https://s2.ax1x.com/2019/08/13/m9C1jH.jpg",
            "https://s2.ax1x.com/2019/09/27/uuIr7D.jpg",
            "https://s2.ax1x.com/2019/09/27/uuID0O.jpg",
            "https://s2.ax1x.com/2019/09/27/uuIBnK.jpg",
            "https://s2.ax1x.com/2019/09/27/uuI1mT.jpg"
    };

    // 引导页
    public static List<Object> getUsertGuides() {
        List<Object> list = new ArrayList<>();
        list.add(R.drawable.guide_img_1);
        list.add(R.drawable.guide_img_2);
        list.add(R.drawable.guide_img_3);
        list.add(R.drawable.guide_img_4);
        return list;
    }

    public static String[] dpiItems = new String[]{
            "480 × 800",
            "1080 × 1920",
            "720 × 1280",
    };

    public static ArrayList<String> getTags() {
        ArrayList<String> data = new ArrayList<>();
        data.add("冰肌玉骨");
        data.add("山清水秀");
        data.add("草长莺飞");
        data.add("春和景明");
        data.add("秀外慧中");
        data.add("万紫千红");
        data.add("百花齐放");
        data.add("春意盎然");
        data.add("湖光山色");
        data.add("鸟语花香");
        data.add("星月");
        data.add("情动");
        data.add("绝影");
        data.add("化羽");
        data.add("醉梦");
        data.add("波澜");
        data.add("沧澜");
        data.add("鸿鹄");
        data.add("日照香炉生紫烟");
        data.add("山岚");
        data.add("春华");
        data.add("星雨");
        data.add("浩瀚");
        data.add("风萧");
        data.add("浮波");
        data.add("沧澜");
        data.add("鸿鹄");
        data.add("日照香炉生紫烟");
        data.add("鸟语花香");
        data.add("湖光山色");
        data.add("沉鱼落雁闭月羞花");
        data.add("逐风");
        data.add("风和日丽");
        data.add("姹紫嫣红");
        data.add("遥看瀑布挂前川");
        data.add("春暖花开");
        data.add("草长莺飞");
        data.add("春和景明");
        data.add("眉目如画");
        return data;
    }


    public static List<BannerItem> getBannerList() {
        ArrayList<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];

            list.add(item);
        }

        return list;
    }

    public static Class<? extends ViewPager.PageTransformer> transformers[] = new Class[]{
            DepthTransformer.class,
            FadeSlideTransformer.class,
            FlowTransformer.class,
            RotateDownTransformer.class,
            RotateUpTransformer.class,
            ZoomOutSlideTransformer.class,
    };


    public static String[] getTabTitles() {
        return new String[]{"首页", "消息", "订单", "我的"};
    }
}
