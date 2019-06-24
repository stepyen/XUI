package com.stepyen.xui.widget.linearlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.stepyen.xui.R;

/**
 * 2019/6/22
 * <p>
 * <p>
 * 自动换行布局
 * <p>
 * 功能
 * 1、指定最大行数、列数；不指定时，为不限制，子view会自动换行
 * 2、指定横纵向间距
 * 3、子view设置的margin不做处理
 */
public class AutoNewLineLayout extends LinearLayout {
    public static final int DEFAULT_MAX_ROW = -1; // 最大行数，默认值
    public static final int DEFAULT_MAX_COLUMN = -1; // 最大列数，默认值

    /**
     * 最大行数, 默认为 -1 时，表示不限行数
     */
    private int mMaxRow = DEFAULT_MAX_ROW;

    /**
     * 最大列数, 默认为 -1 时，表示不限列数
     */
    private int mMaxColumn = DEFAULT_MAX_COLUMN;

    /**
     * 竖直方向间距
     */
    private int mVerticalSpace = 0;
    /**
     * 水平方向间距
     */
    private int mHorizontalSpace = 0;


    public AutoNewLineLayout(Context context) {
        this(context, null);
    }

    public AutoNewLineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoNewLineLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setOrientation(HORIZONTAL);
        initAttrs(context, attrs, defStyle);


    }

    private void initAttrs(Context context, AttributeSet attrs, int defStyle) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AutoNewLineLayout, 0, 0);
        mMaxRow = ta.getInteger(R.styleable.AutoNewLineLayout_max_row, DEFAULT_MAX_ROW);
        mMaxColumn = ta.getInteger(R.styleable.AutoNewLineLayout_max_column, DEFAULT_MAX_COLUMN);
        mVerticalSpace = (int) ta.getDimension(R.styleable.AutoNewLineLayout_vertical_space, 0);
        mHorizontalSpace = (int) ta.getDimension(R.styleable.AutoNewLineLayout_horizontal_space, 0);
        ta.recycle();
    }

    /**
     * 设置最大行数
     *
     * @param maxRow
     */
    public void setMaxRow(int maxRow) {
        if (maxRow < DEFAULT_MAX_ROW) {
            maxRow = DEFAULT_MAX_ROW;
        }
        mMaxRow = maxRow;
    }

    /**
     * 设置最大列数
     *
     * @param maxColumn
     */
    public void setMaxColumn(int maxColumn) {
        if (maxColumn < DEFAULT_MAX_ROW) {
            maxColumn = DEFAULT_MAX_ROW;
        }
        mMaxColumn = maxColumn;
    }

    /**
     * 设置纵向间距
     *
     * @param verticalSpace
     */
    public void setVerticalSpace(int verticalSpace) {
        if (verticalSpace < 0) {
            verticalSpace = 0;
        }
        mVerticalSpace = verticalSpace;
    }

    /**
     * 设置横向间距
     *
     * @param horizontalSpace
     */
    public void setHorizontalSpace(int horizontalSpace) {
        if (horizontalSpace < 0) {
            horizontalSpace = 0;
        }
        mHorizontalSpace = horizontalSpace;
    }

    /**
     * 目的在于计算出控件的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + getPaddingBottom();
        int size = getChildCount();
        int rows = 1;
        int column = 0;
        int tempWidth = 0;  // 临时宽度
        boolean isNewLine = false;

        for (int i = 0; i < size; i++) {
            View view = getChildAt(i);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();
            if (i == 0) {
                height += viewHeight;
            }

            tempWidth = tempWidth + viewWidth;


            column = column + 1;
            if (i != 0 && mMaxColumn != DEFAULT_MAX_COLUMN) {
                isNewLine = column % mMaxColumn == 1;
            }

            if (tempWidth > width || isNewLine) {
                // 新一行的宽
                tempWidth = viewWidth;

                // 尝试换行，发现超过了最大行数就结束循环
                if (mMaxRow != -1 && rows + 1 > mMaxRow) {
                    break;
                }
                rows++;

                height = height + viewHeight + mVerticalSpace;

                isNewLine = false;
            }

            tempWidth = tempWidth + mHorizontalSpace;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // 可以绘制的宽度
        int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        int size = getChildCount();
        int tempWidth = 0;
        int tempHeight = 0;
        int rows = 1;
        int column = 0;
        boolean isNewLine = false;

        for (int i = 0; i < size; i++) {
            View view = getChildAt(i);
            int viewWidth = view.getMeasuredWidth();
            int viewHeight = view.getMeasuredHeight();

            tempWidth = tempWidth + viewWidth;

            column = column + 1;
            if (i != 0 && mMaxColumn != DEFAULT_MAX_COLUMN) {
                isNewLine = column % mMaxColumn == 1;
            }
            if (tempWidth > width|| isNewLine) {
                tempWidth = viewWidth;
                // 尝试换行，发现超过了最大行数就结束循环
                if (mMaxRow != -1 && rows + 1 > mMaxRow) {
                    break;
                }
                rows++;
                tempHeight = tempHeight + viewHeight + mVerticalSpace;

                isNewLine = false;
            }

            int left = getPaddingLeft() + tempWidth - viewWidth;
            int top = getPaddingTop() + tempHeight;
            int right = getPaddingLeft() + tempWidth;
            int bottom = getPaddingTop() + tempHeight + viewHeight;

            view.layout(left, top, right, bottom);

            tempWidth += mHorizontalSpace;
        }
    }
}
