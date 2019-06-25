package com.stepyen.xui.widget.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.stepyen.xui.R;
import com.stepyen.xui.utils.DensityUtils;
import com.stepyen.xui.utils.shape.ShapeBuilder;

/**
 * date：2018/10/27
 * author：stepyen
 * description：数量选择器
 * <p>
 * 如：
 * - 7 +
 */
public class NumberPicker extends LinearLayout {
    private static final int DEFAULT_MIN_MUM = 1;  // 最小值
    private static final int DEFAULT_MAX_MUM = Integer.MAX_VALUE;  // 最大值
    private static final int DEFAULT_MIDDLE_WIDTH = DensityUtils.dp2px(20);  // 中间宽度
    /**
     * 最小值
     */
    private int mMinNum = DEFAULT_MIN_MUM;
    /**
     * 最大值
     */
    private int mMaxNum = DEFAULT_MAX_MUM;
    /**
     * 数量
     */
    private int mNumber = mMinNum;

    private int mMiddleWidth = DEFAULT_MIDDLE_WIDTH;


    TextView mTvSub;
    TextView mTvValue;
    TextView mTvAdd;

    public NumberPicker(Context context) {

        this(context, null);
    }

    public NumberPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setOrientation(HORIZONTAL);

        initAttrs(context, attrs, defStyle);

        initView(context);

    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyle) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberPicker, 0, 0);
        mMiddleWidth = (int) ta.getDimension(R.styleable.NumberPicker_middle_width, DEFAULT_MIDDLE_WIDTH);
        mMinNum = ta.getInteger(R.styleable.NumberPicker_min_number, DEFAULT_MIN_MUM);
        mMaxNum = ta.getInteger(R.styleable.NumberPicker_max_number, DEFAULT_MAX_MUM);

        ta.recycle();
    }


    private void initView(Context context) {

        mTvSub = new TextView(context);
        mTvSub.setGravity(Gravity.CENTER);
        mTvSub.setText("-");
        mTvSub.setTextSize(18);

        mTvValue = new TextView(context);
        mTvValue.setGravity(Gravity.CENTER);
        mTvValue.setText(mMinNum + "");
        mTvValue.setTextSize(18);

        mTvAdd = new TextView(context);
        mTvAdd.setGravity(Gravity.CENTER);
        mTvAdd.setText("+");
        mTvAdd.setTextSize(18);

        addView(mTvSub);
        addView(mTvValue);
        addView(mTvAdd);

        mTvSub.setOnClickListener(v->{
            sub();
        });
        mTvAdd.setOnClickListener(v->{
            add();
        });

        ShapeBuilder.create(context).stroke(1, R.color.black).build(this);
        ShapeBuilder.create(context).stroke(1, R.color.black).build(mTvValue);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int otherWidth = (getMeasuredWidth() - mMiddleWidth) / 2;

        LinearLayout.LayoutParams tvSubLp = (LayoutParams) mTvSub.getLayoutParams();
        tvSubLp.width =otherWidth;
        tvSubLp.height = LayoutParams.MATCH_PARENT;
        mTvSub.setLayoutParams(tvSubLp);

        LinearLayout.LayoutParams tvValueLp = (LayoutParams) mTvValue.getLayoutParams();
        tvValueLp.width =mMiddleWidth;
        tvValueLp.height = LayoutParams.MATCH_PARENT;
        mTvValue.setLayoutParams(tvValueLp);

        LinearLayout.LayoutParams tvAddLp = (LayoutParams) mTvAdd.getLayoutParams();
        tvAddLp.width =otherWidth;
        tvAddLp.height = LayoutParams.MATCH_PARENT;
        mTvAdd.setLayoutParams(tvAddLp);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    /**
     * 设置中间宽度
     *
     * @param middleWidth
     */
    public void setMiddleWidth(int middleWidth) {
        if (middleWidth < 0) {
            middleWidth = DEFAULT_MIDDLE_WIDTH;
        }

        mMiddleWidth = middleWidth;
    }

    /**
     * 获取数量
     *
     * @return
     */
    public int getNumber() {
        return mNumber;
    }

    /**
     * 设置数量
     * @param number
     */
    public void setNumber(int number) {
        this.mNumber = number;
        mTvValue.setText(number + "");
    }

    /**
     * 设置最小值
     *
     * @param minNum
     */
    public void setMinNum(int minNum) {
        if (minNum < mMinNum) {
            minNum = mMinNum;
        }
        mMinNum = minNum;
    }

    /**
     * 设置最大值
     *
     * @param maxNum
     */
    public void setMaxNum(int maxNum) {
        mMaxNum = maxNum;
    }

    /**
     * 设置减这个view的背景
     *
     * @param drawable
     */
    public void setSubViewBackground(Drawable drawable) {
        mTvSub.setBackground(drawable);
    }

    /**
     * 设置中间值这个view的背景
     *
     * @param drawable
     */
    public void setValueViewBackground(Drawable drawable) {
        mTvValue.setBackground(drawable);
    }

    /**
     * 设置加这个view的背景
     *
     * @param drawable
     */
    public void setAddViewBackground(Drawable drawable) {
        mTvAdd.setBackground(drawable);
    }

    private void sub() {
        if (mNumber == mMinNum) {
            if (mNumberLimitListen != null) {
                mNumberLimitListen.isMinimum();
                return;
            }
        }

        mNumber--;
        mTvValue.setText(mNumber + "");
    }

    private void add() {
        if (mNumber == mMaxNum) {
            if (mNumberLimitListen != null) {
                mNumberLimitListen.isMaximum();
                return;
            }
        }

        mNumber++;
        mTvValue.setText(mNumber + "");
    }

    public interface OnNumberLimitListen {
        void isMinimum();

        void isMaximum();
    }

    public OnNumberLimitListen mNumberLimitListen;

    public void setNumberLimitListen(OnNumberLimitListen numberLimitListen) {
        mNumberLimitListen = numberLimitListen;
    }
}
