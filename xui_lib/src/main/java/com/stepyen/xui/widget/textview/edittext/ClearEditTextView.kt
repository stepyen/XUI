package com.stepyen.xui.widget.textview.edittext

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.*
import com.stepyen.xui.R
import com.stepyen.xui.utils.DensityUtils
import com.stepyen.xui.utils.ResUtils
import kotlinx.android.synthetic.main.view_clear_edittext.view.*


/**
 * date：2022/11/27
 * author：stepyen
 * description：
 *
 */
class ClearEditTextView @JvmOverloads constructor(
    private val mContext: Context,
    private val mAttrs: AttributeSet? = null,
    private val mDefStyleAttr: Int = 0
) : FrameLayout(mContext, mAttrs, mDefStyleAttr) {


    init {
        val view = View.inflate(mContext, R.layout.view_clear_edittext, this)
        init(mContext, mAttrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ClearEditText, 0, 0)
        ta.run {

            var icon = getDrawable(R.styleable.ClearEditText_cet_clearIcon)
            var iconWidth = getDimensionPixelSize(
                R.styleable.ClearEditText_cet_clearIconWidth,
                DensityUtils.dp2px(18f)
            )
            var iconHeight = getDimensionPixelSize(
                R.styleable.ClearEditText_cet_clearIconHeight,
                DensityUtils.dp2px(18f)
            )

            recycle()

            if (icon == null) {
                icon = ResUtils.getDrawable(R.drawable.xui_ic_default_clear_btn)
            }
            clearIv.setImageDrawable(icon)
            val lp = clearIv.layoutParams.apply {
                width = iconWidth
                height = iconHeight
            }
            clearIv.layoutParams = lp

            clearIv.setOnClickListener {
                inputEt.setText("")
            }
        }


        initEt()
        setClearIconVisible(false)
    }

    fun getText(): String {
        return inputEt.text.trim().toString()
    }

    fun getEditText(): EditText = inputEt

    fun getIconView(): ImageView = clearIv

    fun setIconPadding(left: Int, top: Int, right: Int, bottom: Int) {
        clearIv.setPadding(left, top, right, bottom)
    }

    fun setIconMargin(left: Int, top: Int, right: Int, bottom: Int) {
        val lp = (clearIv.layoutParams as LinearLayout.LayoutParams).apply {
            setMargins(left, top, right, bottom)
        }
        clearIv.layoutParams = lp
    }

    private fun initEt() {

        inputEt.apply {

            setTextColor(ResUtils.getColor(R.color.black))

            onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->

                /**
                 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
                 */
                if (hasFocus) {
                    setClearIconVisible(this@ClearEditTextView.getText().isNotEmpty())
                } else {
                    setClearIconVisible(false)
                }
            }

            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {}

                /**
                 * 当输入框里面内容发生变化的时候回调的方法
                 */
                override fun onTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int
                ) {
                    setClearIconVisible(this@ClearEditTextView.getText().isNotEmpty())
                }
            })
        }
    }

    private fun setClearIconVisible(visible: Boolean) {

        if (visible) {
            clearIv.visibility = View.VISIBLE
        } else {
            clearIv.visibility = View.GONE
        }

    }

    override fun onDetachedFromWindow() {
        // 释放资源
        // ...

        super.onDetachedFromWindow()
    }
}