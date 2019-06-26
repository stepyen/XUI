package com.stepyen.xui.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.stepyen.xui.R;

/**
 * date：2019/2/14
 * author：yfj
 * description：字母索引列表
 * 城市列表页面
 */
public class LetterIndexBar extends View {
    private Context mContext;
    // 默认索引字母
    private String[] mLetters = new String[]{
            "A","B","C","D","E",
            "F","G","H","I","J",
            "K","L","M","N","O",
            "P","Q","R","S","T",
            "U","V","W","X","Y",
            "Z","#"};

    // 字母颜色
    int mLetterColor = 0xff666666;
    // 被选中的字母颜色
    int mSelectLetterColor = 0xff666666;
    //背景
    Drawable mBackground;
    //选中时的背景
    Drawable mSelectBackground;
    // 字母字体大小
    float mLetterSize = 30;
    //是否是粗体字母
    boolean mIsBoldface = false;
    //是否字母是居中显示
    private boolean mIsLetterCenter = true;

    private Paint paint;

    private int width;//控件宽度

    private float itemHeight;//将控件等分每个部分的高度

    private int lastIndex = -1;//上一次点击字母记录的索引

    private TextView mTextDialog;//可以设置一个显示当前索引字母的对话框

    public LetterIndexBar(Context context) {
        this(context, null);
    }

    public LetterIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterIndexBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;

        if (context != null && attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LetterIndexBar, 0, 0);
            mLetterSize = a.getDimension(R.styleable.LetterIndexBar_letterSize, mLetterSize);
            mLetterColor = a.getColor(R.styleable.LetterIndexBar_letterColor, mLetterColor);
            mSelectLetterColor = a.getColor(R.styleable.LetterIndexBar_selectLetterColor, mSelectLetterColor);
            mSelectBackground = a.getDrawable(R.styleable.LetterIndexBar_selectBackground);
            mIsBoldface = a.getBoolean(R.styleable.LetterIndexBar_isBoldface, mIsBoldface);
            mIsLetterCenter = a.getBoolean(R.styleable.LetterIndexBar_isLetterCenter, mIsLetterCenter);
            a.recycle();
        }

        //初始化画笔参数
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);//设置抗锯齿
        paint.setTypeface(mIsBoldface ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        paint.setTextSize(mLetterSize);
        paint.setAntiAlias(true);

        mBackground = getBackground();
    }

    /**
     * 设置字符
     * @param letters
     */
    public void setLetters(String[] letters) {
        if (letters == null)
            letters = new String[]{};

        this.mLetters = letters;
    }

    /**
     * 设置选择提示框
     * @param textDialog
     */
    public void setTextDialog(TextView textDialog) {
        this.mTextDialog = textDialog;
    }


    //初始化宽高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getMeasuredWidth();
        itemHeight = getMeasuredHeight() * 1f / mLetters.length;
    }

    //绘制
    @Override
    protected void onDraw(Canvas canvas) {

        int size = mLetters.length;
        for (int i = 0; i < size; i++) {
            paint.setColor(i == lastIndex ? mSelectLetterColor : mLetterColor);
            // 字母
            String letter = String.valueOf(mLetters[i]);
            // 字母的宽度
            float letterWidth = paint.measureText(letter);
            // 字母的高度
            int  letterHeight = getTextHeight(letter);
            // 字母的x坐标
            float x = mIsLetterCenter? (width-letterWidth)/2: getPaddingLeft() + (width-letterWidth) / 2;
            //字母的y坐标 = item高度一半 + 字母高度的一半 + 位置 * item高度
            float y = getPaddingTop() + itemHeight / 2 + letterHeight/ 2 + i * itemHeight;

            canvas.drawText(letter, x, y, paint);
        }
    }

    /**
     * 获取文本的高度
     *
     * @param text 需要获取高度的文本
     * @return 文本的高度
     */
    private int getTextHeight(String text) {
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();
    }

    //触摸监听，需要有自己的处理，返回true
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                //获取按下的位置
                float y = event.getY();
                //获取按下位置与每个格子高度的余数，这个余数就是每个字母的索引
                int index = (int) (y / itemHeight);
                if (lastIndex != index) {
                    if (index >= 0 && index < mLetters.length) {//索引安全性检查
                        String letter = String.valueOf(mLetters[index]);
                        if (listener != null) {
                            listener.onTouchIndex(letter);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(letter);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                    }

                    setBackground(mSelectBackground);
                }
                lastIndex = index;

                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                if (listener != null) {
                    listener.onActionUp();
                }
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                setBackground(mBackground);
                break;
        }
        //引发重绘
        invalidate();
        return true;
    }


    private onTouchIndexListener listener;

    public void setOnTouchIndexListener(onTouchIndexListener listener) {
        this.listener = listener;
    }

    public interface onTouchIndexListener {
        /**
         * 触摸控件
         * @param text
         */
        void onTouchIndex(String text);

        /**
         * 手指抬起回调
         */
        void onActionUp();
    }

}
