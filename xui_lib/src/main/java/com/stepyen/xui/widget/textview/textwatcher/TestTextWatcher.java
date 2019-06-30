package com.stepyen.xui.widget.textview.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.TextView;

/**
 * date：2018/9/3
 * author：yfj
 * description：如：123-1234-1234
 */

public class TestTextWatcher implements TextWatcher{

    private static final String TAG = "TestTextWatcher";

    private TextView mTextView;

    public TestTextWatcher(TextView textView) {
        mTextView = textView;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d(TAG, "beforeTextChanged: "+s);
        Log.d(TAG, "beforeTextChanged: "+start);
        Log.d(TAG, "beforeTextChanged: "+count);
        Log.d(TAG, "beforeTextChanged: "+after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "onTextChanged: "+s);
        Log.d(TAG, "onTextChanged: "+start);
        Log.d(TAG, "onTextChanged: "+before);
        Log.d(TAG, "onTextChanged: "+count);
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d(TAG, "afterTextChanged: "+ s.toString());
    }


}
