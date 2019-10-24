package com.stepyen.xui.widget.linearlayout

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.stepyen.xui.utils.DensityUtils
import com.stepyen.xui.utils.ResUtils

/**
 * date：2019-10-11
 * author：stepyen
 * description：评分星星✨
 *
 * 1、单个View，宽、高、默认样式、选中样式
 * 2、View间距
 * 3、设置总个数、设置选中个数，获取选中个数
 *
 */
class StarLayout : LinearLayout {
    var mContext: Context

    var allNumber = 5
    private var number = 0

    var defaultStarWidth = DensityUtils.dp2px(12f)
        set(value) {
            field = value
            checkStarWidth = value
        }
    var defaultStarHeight = DensityUtils.dp2px(12f)
        set(value) {
            field = value
            checkStarHeight = value
        }
    var defaultStartDrawable = 0

    private var checkStarWidth = defaultStarWidth
    private var checkStarHeight = defaultStarHeight
    var checkStartDrawable = 0

    var starSpace: Int = DensityUtils.dp2px(5f)

    constructor( context: Context) : this(context, null)
    constructor( context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor( context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        this.mContext = context
        orientation = LinearLayout.HORIZONTAL
    }

    fun initView() {
        for (i in 0 until allNumber) {
            val imageView = ImageView(mContext).apply {
                layoutParams =
                    LinearLayout.LayoutParams(defaultStarWidth, defaultStarHeight).apply {
                        leftMargin = if (i == 0) 0 else starSpace
                    }

                setImageDrawable(ResUtils.getDrawable(defaultStartDrawable))

                setOnClickListener {
                    setNumber(i + 1)
                    starClickListen?.click(i + 1)
                }
            }

            addView(imageView)
        }
    }



    fun getNumber(): Int = number


    fun setNumber(number: Int) {
        this.number = number
        for (i in 0 until allNumber) {
            (getChildAt(i) as ImageView).apply {
                if (i < number) {
                    setImageDrawable(ResUtils.getDrawable(checkStartDrawable))
                    layoutParams = layoutParams.apply {
                        width = checkStarWidth
                        height = checkStarHeight
                    }
                } else {
                    setImageDrawable(ResUtils.getDrawable(defaultStartDrawable))
                    layoutParams = layoutParams.apply {
                        width = defaultStarWidth
                        height = defaultStarHeight
                    }
                }
            }
        }
    }

    interface OnStarClickListen {
        fun click(number: Int)
    }

    var starClickListen: OnStarClickListen?=null

}