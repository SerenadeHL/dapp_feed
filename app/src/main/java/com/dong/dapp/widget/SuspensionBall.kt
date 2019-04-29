package com.dong.dapp.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.animation.AccelerateInterpolator
import com.dong.dapp.utils.SystemUtils
import me.serenadehl.base.extensions.log


/**
 * 悬浮球View
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-25 11:55:16
 */
class SuspensionBall : AppCompatImageView {
    private var mTouchSlop = 0//最小滑动距离
    private var mScreenWidth = 0//屏幕宽度
    private var mScreenHeight = 0//屏幕高度
    private var mWidth = 0//View宽度
    private var mHeight = 0//View高度
    private var mTouchX = 0F//ACTION_DOWN的x
    private var mTouchY = 0F//ACTION_DOWN的y
    private var mTouchRawX = 0F//ACTION_DOWN的屏幕X坐标
    private var mTouchRawY = 0F//ACTION_DOWN的屏幕Y坐标
    private var mStatus = STATUS_HIDE//状态，默认是收起状态
    private val mAlpha = 0.5F//收起状态下透明度
    private val mAnimatorDuration = 300L//收起和显示动画时常
    private val mShowWaitTime = 1000L//显示等待时常，超过变为收起状态
    private val mVibrator by lazy { context.getSystemService(VIBRATOR_SERVICE) as Vibrator }//振动器
    private val mDefaultVibrateTime = 50L//默认震动时长

    private lateinit var mListener: Listener//显示状态下点击监听

    private val mHandler = Handler()

    private val mFadeOutAnimator = ObjectAnimator.ofFloat(this, "alpha", 1F, mAlpha)
    private val mShowInAnimator = ObjectAnimator.ofFloat(this, "alpha", mAlpha, 1F)

    companion object {
        const val STATUS_HIDE = 1//收起状态
        const val STATUS_SHOW = 2//显示状态
        const val STATUS_MOVING = 3//移动状态
    }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        mScreenWidth = SystemUtils.getScreenWidth()
        mScreenHeight = SystemUtils.getScreenHeight()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mWidth == 0 && measuredWidth != 0) {
            mWidth = measuredWidth
            mHeight = measuredHeight
            hide()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //正在移动到边界的时候不允许触摸
        if (mStatus == STATUS_MOVING) return true

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                //移除延迟收起动画
                mHandler.removeCallbacksAndMessages(null)
                "移除延迟收起动画".log()

                mTouchX = event.x
                mTouchY = event.y
                mTouchRawX = event.rawX
                mTouchRawY = event.rawY
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (mStatus == STATUS_HIDE) return true//如果是收起状态，不响应拖拽事件

                var nowX = event.rawX - mTouchX
                var nowY = event.rawY - mTouchY
                nowX = when {
                    nowX < 0 -> 0F
                    nowX + width > mScreenWidth -> (mScreenWidth - width).toFloat()
                    else -> nowX
                }
                nowY = when {
                    nowY < 0 -> 0F
                    nowY + height > mScreenHeight -> (mScreenHeight - height).toFloat()
                    else -> nowY
                }
                x = nowX
                y = nowY
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                if (Math.abs(event.rawX - mTouchRawX) < mTouchSlop && Math.abs(event.rawY - mTouchRawY) < mTouchSlop) {//如果滑动距离小于最小滑动距离则认为是点击
                    when (mStatus) {
                        STATUS_SHOW -> {//显示状态下点击触发监听
                            if (::mListener.isInitialized) mListener.onClick()

                            delayHide()
                        }
                        STATUS_HIDE -> {//收起状态下点击变成显示状态
                            show()
                        }
                    }
                } else {
                    if (mStatus == STATUS_HIDE) return true//如果是收起状态，不响应拖拽事件

                    val viewCenterX = x + mWidth / 2F
                    val screenCenterX = mScreenWidth / 2F
                    if (viewCenterX < screenCenterX) {
                        //左
                        move(-x)
                    } else {
                        //右
                        move(mScreenWidth - x - mWidth)
                    }
                }
                return true
            }
            else -> return super.onTouchEvent(event)
        }
    }

    /**
     * 收起
     */
    private fun hide() {
        mStatus = STATUS_MOVING
        val animator = if (x < mWidth / 2F)
            ObjectAnimator.ofFloat(this, "translationX", x, -mWidth / 2F)
        else
            ObjectAnimator.ofFloat(this, "translationX", x, mScreenWidth - mWidth / 2F)
        val animatorSet = AnimatorSet().setDuration(mAnimatorDuration)
        animatorSet.playTogether(animator, mFadeOutAnimator)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                mStatus = STATUS_HIDE
            }
        })
        animatorSet.start()
    }

    /**
     * 延迟收起
     */
    private fun delayHide() {
        "延迟收起".log()
        mHandler.postDelayed({
            hide()
        }, mShowWaitTime)
    }

    /**
     * 显示
     */
    private fun show() {
        "显示".log()
        //震动
        vibrate()
        mStatus = STATUS_MOVING
        val animator = if (x < 0)
            ObjectAnimator.ofFloat(this, "translationX", x, 0F)
        else
            ObjectAnimator.ofFloat(this, "translationX", x, (mScreenWidth - mWidth).toFloat())
        val animatorSet = AnimatorSet().setDuration(mAnimatorDuration)
        animatorSet.playTogether(animator, mShowInAnimator)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                mStatus = STATUS_SHOW
                delayHide()
            }
        })
        animatorSet.start()
    }

    /**
     * 震动效果
     */
    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mVibrator.vibrate(VibrationEffect.createOneShot(mDefaultVibrateTime, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            mVibrator.vibrate(mDefaultVibrateTime)
        }
    }

    /**
     * 移动悬浮球到边界
     */
    private fun move(xDistance: Float) {
        mStatus = STATUS_MOVING
        ObjectAnimator.ofFloat(this, "translationX", x, x + xDistance)
            .apply {
                interpolator = AccelerateInterpolator()
                duration = mAnimatorDuration
                addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        mStatus = STATUS_SHOW
                        delayHide()
                    }
                })
                start()
            }
    }

    /**
     * 设置显示状态下点击监听
     */
    fun setListener(listener: Listener) {
        mListener = listener
    }

    interface Listener {
        fun onClick()
    }
}