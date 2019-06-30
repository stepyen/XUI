package com.stepyen.xui.widget.textview.textwatcher;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * date：2018/9/3
 * author：yfj
 * description：电话号码输入监听
 *
 * 如：123-1234-1234
 */

public class PhoneNumberTextWatcher implements TextWatcher{
    private char mInterval = ' ';   // 分割符
    private TextView mTextView;

    public PhoneNumberTextWatcher(TextView textView) {
        mTextView = textView;
    }

    public PhoneNumberTextWatcher(TextView textView, char interval) {
        mTextView = textView;
        mInterval = interval;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s == null || s.length() == 0) return;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (i != 3 && i != 8 && s.charAt(i) == mInterval) {
                continue;
            }

            sb.append(s.charAt(i));
            if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != mInterval) {
                sb.insert(sb.length() - 1, mInterval);
            }

        }

        if (!TextUtils.isEmpty(sb.toString().trim()) && !sb.toString().equals(s.toString())) {
            mTextView.setText(sb.toString());
            //移动光标到最后面
            if (mTextView instanceof EditText) {
                ((EditText) mTextView).setSelection(sb.length());
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }


}
