package com.stepyen.xui.utils;

import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.stepyen.xui.R;

/**
 * date：2019/1/24
 * author：stepyen
 * description：金额处理工具类
 */
public class MoneyUtil {

    public static void setMoneyStyle(Context context, TextView tv, String money) {
        setMoneyStyle(context,tv, money, false);
    }

    public static void setMoneyStyle(Context context, TextView tv, String money, boolean isSymbolScale) {
        setMoneyStyle(context,tv, money, isSymbolScale, "");
    }


    public static void setMoneyStyle(Context context, TextView tv, String money, boolean isSymbolScale, String prefix) {
        tv.setText(getMoney(context, money,isSymbolScale,prefix));

        // 暂时注释 先使用原生的字体
//        tv.setTypeface(TypeFaceUtil.getNumberTypeFace(context));
    }


    public static CharSequence getMoney(Context context, String money) {
        return  getMoney(context, money,false,"");
    }

    public static CharSequence getMoney(Context context, String money, boolean isSymbolScale) {
        return  getMoney(context, money,isSymbolScale,"");
    }

    /**
     * 设置金额 小数点后面数字变小
     *
     * 样式： 22.00
     * @param money 传入金额
     * @param isSymbolScale ￥符号是否缩放
     * @param prefix 添加前缀  如："-","+"
     * @return
     */
    public static CharSequence getMoney(Context context, String money, boolean isSymbolScale, String prefix) {
        money = "￥"+save2DecimalsMoney(context, money);  //  22.00
        SpannableString spannableString = new SpannableString(money);
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(0.8f);
        spannableString.setSpan(sizeSpan, money.length() -2, money.length() , Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        if (isSymbolScale) {
            RelativeSizeSpan sizeSpan2 = new RelativeSizeSpan(0.8f);
            spannableString.setSpan(sizeSpan2, 0, 1 , Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }

        if (!TextUtils.isEmpty(prefix)) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(prefix);
            spannableStringBuilder.append(spannableString);
            return spannableStringBuilder;
        }

        return spannableString;
    }

    /**
     * 金额保留两位小数
     * @param context
     * @param money
     * @return
     */
    public static String save2DecimalsMoney(Context context, String money) {
        if (TextUtils.isEmpty(money) ) {
            return "";
        }
        try {
            return save2DecimalsMoney(context, Double.valueOf(money));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    /**
     * 金额保留两位小数
     * @param context
     * @param money
     * @return
     */
    public static String save2DecimalsMoney(Context context, double money) {
        return String.format(context.getString(R.string.common_money), money);
    }

}
