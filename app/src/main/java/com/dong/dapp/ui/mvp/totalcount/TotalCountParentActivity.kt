package com.dong.dapp.ui.mvp.totalcount

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.dong.dapp.R
import kotlinx.android.synthetic.main.activity_total_coin_count.v_status_bar
import kotlinx.android.synthetic.main.activity_total_coin_count.v_top_bg
import kotlinx.android.synthetic.main.app_recycle_header_total_cash.view.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.invisible

/**
 * 资产基类
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-05 16:59:11
 */
abstract class TotalCountParentActivity<P : IBasePresenter> : MVPBaseActivity<P>() {
    protected var mPage = 0
    protected var mPageSize = 3

    private var mYOffset = 0
    private var mAlphaDistance = 0

    protected val mHeader:View by lazy { layoutInflater.inflate(getHeaderResId(), null, false) }

    protected val mC2 by lazy { ContextCompat.getColor(this@TotalCountParentActivity, R.color.C2) }
    protected val mC6 by lazy { ContextCompat.getColor(this@TotalCountParentActivity, R.color.C6) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setStatusBarTranslucent(false)
        //返回按钮
        iv_back.setImageResource(R.mipmap.white_left_arrow)
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setTextColor(mC2)
        //设置标题栏
        tv_title.setText(getTitleResId())
        v_header_divider.invisible()
        //设置StatusBar的高度
        v_status_bar.layoutParams.height = getStatusBarHeight()

        getAdapter().addHeaderView(mHeader)
        getRecyclerView().apply {
            adapter = this@TotalCountParentActivity.getAdapter()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (mAlphaDistance <= 0) {
                        mAlphaDistance = mHeader.measuredHeight - getStatusBarHeight() - dimen(R.dimen.L2) * 2
                    }

                    //记录滑动距离
                    mYOffset += dy

                    //计算透明度
                    val alpha = 1F - if (mYOffset < mAlphaDistance) {
                        (mAlphaDistance - mYOffset).toFloat() / mAlphaDistance.toFloat()
                    } else {
                        0F
                    }
                    //设置透明度
                    v_top_bg.alpha = alpha

                    if (alpha > 0.5F) {
                        setStatusBarTranslucent(true)
                        iv_back.setImageResource(R.mipmap.black_left_arrow)
                        tv_title.setTextColor(mC6)
                    } else {
                        setStatusBarTranslucent(false)
                        iv_back.setImageResource(R.mipmap.white_left_arrow)
                        tv_title.setTextColor(mC2)
                    }
                }
            })
        }

        getAdapter().setOnLoadMoreListener({ loadData(false) }, getRecyclerView())

        getHeaderData()
        loadData(true)
    }

    abstract fun getTitleResId(): Int

    abstract fun getHeaderData()

    protected open fun loadData(refresh: Boolean){
        if (refresh) mPage = 0 else mPage++
    }

    abstract fun getHeaderResId(): Int

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getAdapter(): BaseQuickAdapter<*, BaseViewHolder>
}
