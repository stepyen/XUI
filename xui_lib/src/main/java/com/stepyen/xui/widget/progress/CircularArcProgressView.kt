package com.stepyen.xui.widget.progress
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.stepyen.xui.R

/**
 * date：2019/10/25
 * author：stepyen
 * description：进度条
 *
 * 1、指定背景、进度条背景颜色
 * 2、进度百分比，是否显示、指定具体数值
 *
 */
class CircularArcProgressView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    @ColorInt
    private var bgColor: Int = Color.BLACK
    @ColorInt
    private var progressColor: Int = Color.RED
    @ColorInt
    private var progressTextColor: Int = Color.WHITE
    private var isShowProgressText: Boolean = false

    /**
     * Set percent to show the progress.
     */
    var percent: Float = 0f
        set(value) {
            var percent = value

            if (percent < 0f) {
                percent = 0f
            } else if (percent > 1f) {
                percent = 1f
            }

            if (percent != field) {
                field = percent
                invalidate()
            }
        }

    init {
        attrs?.let { set ->
            context.obtainStyledAttributes(set, R.styleable.CircularArcProgressView).apply {
                bgColor = getColor(R.styleable.CircularArcProgressView_capv_background_color, Color.BLACK)
                progressColor = getColor(R.styleable.CircularArcProgressView_capv_progress_color, Color.RED)
                progressTextColor = getColor(R.styleable.CircularArcProgressView_capv_progress_text_color, Color.WHITE)
                getFloat(R.styleable.CircularArcProgressView_capv_percent, 0f).let { percent = it }
                isShowProgressText = getBoolean(R.styleable.CircularArcProgressView_capv_is_show_progress_text, false)
                recycle()
            }
        }
    }

    private val backgroundPaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        color = bgColor
    }

    private val progressPaint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        color = progressColor
        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    private val progressTextPaint by lazy {
        TextPaint().apply {
            isAntiAlias = true
            isDither = true
            style = Paint.Style.FILL
            color = progressTextColor
        }
    }

    private val backgroundRectF = RectF()
    private val progressRectF = RectF()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val halfHeight = height / 2f
        val saveCount = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)

        // Draw background.
        backgroundRectF.apply {
            left = paddingStart.toFloat()
            top = paddingTop.toFloat()
            right = width - paddingEnd.toFloat()
            bottom = height - paddingBottom.toFloat()
        }
        canvas.drawRoundRect(backgroundRectF, halfHeight, halfHeight, backgroundPaint)

        // Draw progress.
        progressRectF.apply {
            left = -backgroundRectF.width() + percent * width
            top = backgroundRectF.top
            right = progressRectF.left + backgroundRectF.width()
            bottom = backgroundRectF.bottom
        }
        canvas.drawRoundRect(progressRectF, halfHeight, halfHeight, progressPaint)

        canvas.restoreToCount(saveCount)

        if (isShowProgressText && percent >= 0.1f) {
            progressTextPaint.run {
                textSize = halfHeight
                fontMetrics.let {
                    val progressText = " ${percent * 100}%"
                    canvas.drawText(
                            progressText,
                            percent * width - progressTextPaint.measureText(progressText) - height / 5f,
                            halfHeight - it.descent + (it.descent - it.ascent) / 2f,
                            progressTextPaint
                    )
                }
            }
        }
    }

    /**
     * Start animator.
     *
     * @param timeInterpolator the interpolator to be used by this animation. The default value is
     * android.view.animation.AccelerateInterpolator.
     *
     * @param duration the length of the animation.
     */
    @JvmOverloads
    fun startAnimator(
            timeInterpolator: TimeInterpolator? = AccelerateInterpolator(),
            duration: Long
    ) =
            with(ObjectAnimator.ofFloat(this, "percent", 0f, percent)) {
                interpolator = timeInterpolator
                this.duration = duration
                start()
            }

}