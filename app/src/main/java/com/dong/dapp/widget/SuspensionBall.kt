package com.dong.dapp.widget

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
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
    private val mStatus = STATUS_HIDE//状态，默认是收起状态
    private val mAlpha = 0.5F//收起状态下透明度
    private val mAnimatorDuration = 500L//收起和显示动画时常

    private lateinit var mListener: Listener//显示状态下点击监听


    //横向显示动画
    private val mHorizontalShowAnimatorSet by lazy {
        AnimatorSet()
            .setDuration(mAnimatorDuration)
            .apply {
                playTogether(
                    ObjectAnimator.ofFloat(this@SuspensionBall, "translationX", -mWidth / 2F, 0F),
                    ObjectAnimator.ofFloat(this@SuspensionBall, "alpha", mAlpha, 1F)
                )
            }
    }

    //纵向显示动画
    private val mVerticalShowAnimatorSet by lazy {
        AnimatorSet()
            .setDuration(mAnimatorDuration)
            .apply {
                playTogether(
                    ObjectAnimator.ofFloat(this@SuspensionBall, "translationY", -mHeight / 2F, 0F),
                    ObjectAnimator.ofFloat(this@SuspensionBall, "alpha", mAlpha, 1F)
                )
            }
    }

    //横向隐藏动画
    private val mHorizontalHideAnimatorSet by lazy {
        AnimatorSet()
            .setDuration(mAnimatorDuration)
            .apply {
                playTogether(
                    ObjectAnimator.ofFloat(this@SuspensionBall, "translationX", 0F, -mWidth / 2F),
                    ObjectAnimator.ofFloat(this@SuspensionBall, "alpha", 1F, mAlpha)
                )
            }
    }

    //纵向隐藏动画
    private val mVerticalHideAnimatorSet by lazy {
        AnimatorSet()
            .setDuration(mAnimatorDuration)
            .apply {
                playTogether(
                    ObjectAnimator.ofFloat(this@SuspensionBall, "translationY", 0F, -mHeight / 2F),
                    ObjectAnimator.ofFloat(this@SuspensionBall, "alpha", 1F, mAlpha)
                )
            }
    }

    companion object {
        const val HIDE = 1//隐藏
        const val GO_EDGE = 2//移动到边界

        const val STATUS_HIDE = 3//收起状态
        const val STATUS_SHOW = 4//显示状态
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
        mWidth = measuredWidth
        mHeight = measuredHeight
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
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
                        }
                        STATUS_HIDE -> {//收起状态下点击变成显示状态
                            changeStatus(true)
                        }
                    }
                } else {
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
     * 切换显示和收起状态
     */
    private fun changeStatus(show: Boolean) {
        when {
            x < 0F -> mHorizontalShowAnimatorSet.start()
            x == 0F -> mHorizontalHideAnimatorSet.start()
            y < 0F -> mVerticalShowAnimatorSet.start()
            y == 0F -> mVerticalHideAnimatorSet.start()
        }
    }

    /**
     * 移动悬浮球到边界
     */
    private fun move(xDistance: Float) {
        val animator = ObjectAnimator.ofFloat(this@SuspensionBall, "translationX", x, x + xDistance)
            .apply {
                interpolator = AccelerateInterpolator()
                duration = mAnimatorDuration
            }
        AnimatorSet().apply {
            interpolator = AccelerateInterpolator()
            playSequentially(animator, mHorizontalHideAnimatorSet)
            start()
        }
    }


    private fun generateHorizontalAnimator() {

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