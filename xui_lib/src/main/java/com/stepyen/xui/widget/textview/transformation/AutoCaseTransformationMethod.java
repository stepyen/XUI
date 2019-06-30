package com.stepyen.xui.widget.textview.transformation;

import android.text.method.ReplacementTransformationMethod;

/**
 * date：2018/6/26
 * author：yfj
 * description：小写 转为 大写
 *  使用
 *  editText.setTransformationMethod(new AutoCaseTransformationMethod());
 */
public class AutoCaseTransformationMethod extends ReplacementTransformationMethod {

    /**
     * 获取用户输入的字符
     * @return
     */
    @Override
    protected char[] getOriginal() {
        return new char[]{'a', 'b', 'c', 'd', 'e',
                'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's',
                't', 'u', 'v', 'w', 'x', 'y', 'z'};
    }

    /**
     * 替换用户输入的字符
     * @return
     */
    @Override
    protected char[] getReplacement() {
        return new char[]{ 'A', 'B', 'C', 'D', 'E',
                'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    }
}
