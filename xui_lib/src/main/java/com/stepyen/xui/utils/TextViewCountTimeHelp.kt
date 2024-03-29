package com.stepyen.xui.utils

import kotlin.jvm.JvmOverloads
import android.widget.TextView


/**
 *
 * 验证码计时帮助类
 */
class TextViewCountTimeHelp @JvmOverloads constructor(
    private val mTv: TextView?,

    /**
     * 倒计时总时间
     */
    private val mCountDownTime: Long,

    /**
     * 倒计时间期
     */
    private val mInterval: Long = 1000
) {
    /**
     * 倒计时
     */
    private var mTimeHelp: CountTimeHelp? = null
    private var mListener: OnCountDownListener? = null
    private var mHintString: CharSequence = "发送验证码"

    /**
     * 构造方法
     * @param mTv        需要显示倒计时的 TextView
     * @param mCountDownTime 需要进行倒计时的最大值,单位是毫秒
     * @param mInterval      倒计时的间隔，单位是毫秒
     */
    init {

        mTv?.text?.takeIf { it.isNotEmpty() }?.let {
            mHintString = it
        }


        initCountDownTimer()
    }


    /**
     * 初始化倒计时器
     */
    private fun initCountDownTimer() {

        mTimeHelp = CountTimeHelp.newCountDownHelp(mCountDownTime)

        mTimeHelp?.setPeriod(mInterval)

        mTimeHelp?.setOnCountListener(object : CountTimeHelp.OnCountListener {
            override fun onCount(time: Long, hour: Long, minute: Long, second: Float) {

                if (mListener?.onCountDown(second) == true) {

                } else {
                    configText(second)
                }
            }

            override fun onFinish() {

                mTv?.post {
                    mTv.isEnabled = true
                    mTimeHelp?.reset()

                    if (mListener?.onFinished() == true) {

                    } else {
                        mTv.text = mHintString
                    }
                }
            }
        })
    }

    private fun configText(second: Float) {
        mTv?.let {
            val text = mListener?.getCountDownText(second)
            if (text.isNullOrEmpty()) {
                mTv.text = "${second.toInt()}s"
            } else {
                mTv.text = text
            }
        }
    }

    /**
     * 开始倒计时
     */
    fun start() {
        mTv?.isEnabled = false
        configText(mCountDownTime/1000f)
        mTimeHelp?.start()
    }

    /**
     * 设置倒计时的监听器
     *
     * @param listener
     */
    fun setOnCountDownListener(listener: OnCountDownListener?): TextViewCountTimeHelp {
        mListener = listener
        return this
    }

    /**
     * 计时时监听接口
     *
     * @author xx
     */
    interface OnCountDownListener {
        /**
         * 开始倒计时
         */
        fun onStart()

        /**
         * 正在倒计时
         * @param time 剩余的时间
         *
         * @return 是否不使用默认的倒计时提示文本
         */
        fun onCountDown(time: Float): Boolean

        fun getCountDownText(time: Float): String

        /**
         * 倒计时结束
         *
         *  @return 是否不使用默认的倒计时提示文本
         */
        fun onFinished(): Boolean
    }


    fun onStop() {
        mTimeHelp?.stop()
    }

    fun onDestory() {
        mTimeHelp?.destory()
    }
}