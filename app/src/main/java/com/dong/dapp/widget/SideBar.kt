package com.dong.dapp.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.R.color
import android.R.attr.radius
import com.dong.dapp.R
import me.serenadehl.base.extensions.*


class SideBar : View {
    // 触摸事件
    private lateinit var mOnTouchingLetterChangedListener: OnTouchingLetterChangedListener
    private var mChosen = -1// 选中
    private var mPaint: Paint = Paint()
    private var mChosenColor = 0
    private var mDefaultColor = 0
    private var mDefaultBackgroundColor = 0
    private var mTouchingBackgroundColor = 0

    private lateinit var mTextDialog: TextView

    fun setTextDialog(mTextDialog: TextView) {
        this.mTextDialog = mTextDialog
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        readAttributeSet(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        readAttributeSet(context, attrs)
    }

    /**
     * 读取自定义属性
     */
    private fun readAttributeSet(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SideBar)
        mPaint.textSize = typedArray.getDimension(R.styleable.SideBar_text_size, dimen(R.dimen.F5).toFloat())
        mPaint.typeface = Typeface.DEFAULT_BOLD
        mPaint.isAntiAlias = true

        mChosenColor = typedArray.getColor(R.styleable.SideBar_chosen_color, Color.BLACK)
        mDefaultColor = typedArray.getColor(R.styleable.SideBar_default_color, Color.BLACK)
        mDefaultBackgroundColor = typedArray.getColor(R.styleable.SideBar_default_background_color, Color.WHITE)
        mTouchingBackgroundColor = typedArray.getColor(R.styleable.SideBar_touching_background_color, Color.WHITE)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measureWidth(minimumWidth, widthMeasureSpec)
//        val height = measureHeight(minimumHeight, heightMeasureSpec)
        setMeasuredDimension(width, heightMeasureSpec);
    }

    /**
     * 测量宽度
     */
    private fun measureWidth(defaultWidth: Int, measureSpec: Int): Int {
        var width = defaultWidth
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.AT_MOST -> {
                width = mPaint.textSize.toInt() + paddingLeft + paddingRight
            }
            MeasureSpec.EXACTLY -> {
                width = specSize
            }
            MeasureSpec.UNSPECIFIED -> {
                width = Math.max(defaultWidth, specSize)
            }
        }
        return width
    }

    /**
     * 测量高度
     */
    private fun measureHeight(defaultHeight: Int, measureSpec: Int): Int {
        var height = defaultHeight

        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        when (specMode) {
            MeasureSpec.AT_MOST -> {
                height = (-mPaint.ascent() + mPaint.descent()).toInt() * sLetters.size + paddingTop + paddingBottom
            }
            MeasureSpec.EXACTLY -> {
                height = specSize
            }
            MeasureSpec.UNSPECIFIED -> {
                height = Math.max(defaultHeight, specSize)
            }
        }
        return height
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 获取焦点改变背景颜色.
        val height = height// 获取对应高度
        val width = width // 获取对应宽度

        var singleHeight = height.toFloat() / sLetters.size// 获取每一个字母的高度
        singleHeight = (height.toFloat() - singleHeight / 2) / sLetters.size
        for (i in sLetters.indices) {
            // 选中的状态
            if (i == mChosen) {
                mPaint.color = mChosenColor
                mPaint.isFakeBoldText = true
            } else {
                mPaint.color = mDefaultColor
                mPaint.isFakeBoldText = false
            }
            // x坐标等于中间-字符串宽度的一半.
            val xPos = width / 2 - mPaint.measureText(sLetters[i]) / 2
            val yPos = singleHeight * i + singleHeight
            canvas.drawText(sLetters[i], xPos, yPos, mPaint)
        }

    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val y = event.y// 点击y坐标
        val oldChoose = mChosen
        val c = (y / height * sLetters.size).toInt()// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.

        when (action) {
            MotionEvent.ACTION_UP -> {
                setBackgroundColor(mDefaultBackgroundColor)
                mChosen = -1//重置状态
                invalidate()
                if (::mTextDialog.isInitialized) {
                    mTextDialog.invisible()
                }
            }

            else -> {
                setBackgroundColor(mTouchingBackgroundColor)
                if (oldChoose != c) {
                    if (c >= 0 && c < sLetters.size) {
                        if (::mOnTouchingLetterChangedListener.isInitialized) {
                            mOnTouchingLetterChangedListener.onTouchingLetterChanged(sLetters[c])
                        }

                        if (::mTextDialog.isInitialized){
                            mTextDialog.text = sLetters[c]
                            mTextDialog.visible()
                        }

                        mChosen = c
                        invalidate()
                    }
                }
            }
        }
        return true
    }

    /**
     * 向外公开的方法
     */
    fun setOnTouchingLetterChangedListener(mOnTouchingLetterChangedListener: OnTouchingLetterChangedListener) {
        this.mOnTouchingLetterChangedListener = mOnTouchingLetterChangedListener
    }

    /**
     * 接口
     *
     * @author coder
     */
    interface OnTouchingLetterChangedListener {
        fun onTouchingLetterChanged(s: String)
    }

    companion object {
        // 26个字母
        var sLetters = arrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )
    }

}