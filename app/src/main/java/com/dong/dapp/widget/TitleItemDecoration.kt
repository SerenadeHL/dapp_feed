package com.dong.dapp.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.IntegerRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.sp

/**
 * d
 *
 * @author: Endeavor
 * @date: 2018/9/25
 */

class TitleItemDecoration(val context: Context, private val callback: TitleDecorationCallback) :
    RecyclerView.ItemDecoration() {

    private var mTitleHeight = 0
    private val mPaint = Paint().apply { isAntiAlias = true }
    private val mTextPaint = Paint().apply { isAntiAlias = true }
    private val mTextRect = Rect()
    private var mTitlePaddingLeft = 0

    /**
     * 设置标题左间距
     */
    fun setTitlePaddingLeft(@DimenRes paddingLeft: Int) {
        mTitlePaddingLeft = context.dimen(paddingLeft)
    }

    /**
     * 设置标题高度
     */
    fun setTitleHeightResId(@DimenRes height: Int) {
        mTitleHeight = context.dimen(height)
    }

    /**
     * 设置标题背景颜色
     */
    fun setTitleBackgroundColor(@ColorRes color: Int) {
        mPaint.color = ContextCompat.getColor(context, color)
    }

    /**
     * 设置标题颜色
     */
    fun setTitleTextColor(@ColorRes color: Int) {
        mTextPaint.color = ContextCompat.getColor(context, color)
    }

    /**
     * 设置标题字体大小
     */
    fun setTitleTextSize(sp: Int) {
        mTextPaint.textSize = context.sp(sp).toFloat()
    }

    // 这个方法用于给item隔开距离，类似直接给item设padding
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition

        if (isFirst(position)) {
            outRect.top = mTitleHeight
        } else {
            outRect.top = 0
        }
    }

    // 这个方法用于给getItemOffsets()隔开的距离填充图形,
    // 在item绘制之前时被调用，将指定的内容绘制到item view内容之下；
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        // 获取当前屏幕可见 item 数量，而不是 RecyclerView 所有的 item 数量
        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val params = view
                .layoutParams as RecyclerView.LayoutParams
            val position = params.viewLayoutPosition

            if (isFirst(position)) {
                val top = (view.top - mTitleHeight).toFloat()
                val bottom = view.top.toFloat()
                canvas.drawRect(left.toFloat(), top, right.toFloat(), bottom, mPaint)

                val groupName = callback.getGroupName(position)
                mTextPaint.getTextBounds(groupName, 0, groupName.length, mTextRect)
                val x = (view.paddingLeft + mTitlePaddingLeft).toFloat()
                val y = top + ((mTitleHeight - mTextRect.height()) / 2).toFloat() + mTextRect.height().toFloat()
                canvas.drawText(callback.getGroupName(position), x, y, mTextPaint)
            }
        }
    }

    // 在item被绘制之后调用，将指定的内容绘制到item view内容之上
    // 这个方法可以将内容覆盖在item上，可用于制作悬停效果，角标等（这里只实现悬停效果）
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val position = (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (position <= -1 || position >= parent.adapter!!.itemCount - 1) {
            // 越界检查
            return
        }

        val firstVisibleView = parent.findViewHolderForAdapterPosition(position)!!.itemView

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        var top = parent.paddingTop
        var bottom = top + mTitleHeight


        // 如果当前屏幕上第二个显示的item是下一组的的第一个，并且第一个被title覆盖，则开始移动上个title。
        // 原理就是不断改变title所在矩形的top与bottom的值。
        if (isFirst(position + 1) && firstVisibleView.bottom < mTitleHeight) {
            if (mTitleHeight <= firstVisibleView.height) {
                val d = firstVisibleView.height - mTitleHeight
                top = firstVisibleView.top + d
            } else {
                val d = mTitleHeight - firstVisibleView.height
                top = firstVisibleView.top - d// 这里有bug,mTitleHeight过高时 滑动有问题
            }
            bottom = firstVisibleView.bottom
        }
        c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)

        val groupName = callback.getGroupName(position)
        mTextPaint.getTextBounds(groupName, 0, groupName.length, mTextRect)
        val x = (left + firstVisibleView.paddingLeft + mTitlePaddingLeft).toFloat()
        val y = (top + (mTitleHeight - mTextRect.height()) / 2 + mTextRect.height()).toFloat()
        c.drawText(groupName, x, y, mTextPaint)
    }

    /**
     * 判断是否是同一组的第一个item
     */
    private fun isFirst(position: Int): Boolean {
        return position == 0 || callback.getGroupId(position) != callback.getGroupId(position - 1)
    }

    interface TitleDecorationCallback {

        fun getGroupId(position: Int): Int

        fun getGroupName(position: Int): String
    }
}