package com.stepyen.xui.widget.textview.supertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stepyen.xui.R;
import com.stepyen.xui.utils.ResUtils;

import java.sql.ResultSet;

import javax.xml.transform.Result;

/**
 * date：2019-07-14
 * author：stepyen
 * description：
 */
public class OptionItemTitleValue extends RelativeLayout {
    private TextView mTitleTv;
    private TextView mValueTv;


    public OptionItemTitleValue(Context context) {
        this(context, null);
    }

    public OptionItemTitleValue(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OptionItemTitleValue(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        View view = View.inflate(context, R.layout.view_option_item_title_value, null);
        addView(view);
        mTitleTv = view.findViewById(R.id.tv_option_title);
        mValueTv = view.findViewById(R.id.tv_option_value);


        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.OptionItemTitleValue, defStyle, 0);

        String title = ta.getString(R.styleable.OptionItemTitleValue_opkv_title);
        int titleColor = ta.getColor(R.styleable.OptionItemTitleValue_opkv_title_color, ResUtils.getColor(R.color.black));
        int titleSize = ta.getDimensionPixelSize(R.styleable.OptionItemTitleValue_opkv_title_size, (int) ResUtils.getDimens(R.dimen.xui_config_size_title));

        String value = ta.getString(R.styleable.OptionItemTitleValue_opkv_value);
        int valueColor = ta.getColor(R.styleable.OptionItemTitleValue_opkv_value_color, ResUtils.getColor(R.color.black));
        int valueSize = ta.getDimensionPixelSize(R.styleable.OptionItemTitleValue_opkv_value_size, (int) ResUtils.getDimens(R.dimen.xui_config_size_title));

        int titleValueSpace = ta.getDimensionPixelSize(R.styleable.OptionItemTitleValue_opkv_title_value_space, 20);


        mTitleTv.setText(title);
        mTitleTv.setTextSize(titleSize);
        mTitleTv.setTextColor(titleColor);

        mValueTv.setText(value);
        mValueTv.setTextSize(valueSize);
        mValueTv.setTextColor(valueColor);



        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mValueTv.getLayoutParams();
        lp.leftMargin = titleValueSpace;
        mValueTv.setLayoutParams(lp);

        ta.recycle();

    }


    public void setTitle(CharSequence charSequence) {
        mTitleTv.setText(charSequence);
    }


    public void setValue(CharSequence charSequence) {
        mValueTv.setText(charSequence);
    }

    public void setValueClick(OnClickListener clickListener) {
        if (clickListener != null) {
            mValueTv.setOnClickListener(clickListener);
        }
    }
    
}
