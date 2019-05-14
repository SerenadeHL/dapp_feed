package com.axonomy.dapp_feed.ui.mvp.totalcount

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.axonomy.dapp_feed.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.activity_total_coin_count.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.*
import me.serenadehl.base.utils.app.SystemUtils


/**
 * 资产基类
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-05 16:59:11
 */
abstract class TotalCountParentActivity<P : IBasePresenter> : MVPBaseActivity<P>() {
    protected var mPage = 0
    protected var mPageSize = 20

    private var mYOffset = 0
    private var mAlphaDistance = 0

    protected val mHeader: View by lazy { layoutInflater.inflate(getHeaderResId(), null, false) }

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
        tv_title.setText(getTitleResId())
        v_header_divider.invisible()
        //设置StatusBar的高度
        v_status_bar.layoutParams.height = getStatusBarHeight()

        ConstraintSet().apply {
            clone(mHeader as ConstraintLayout)
            setMargin(getTopMarginViewId(), ConstraintSet.TOP, getStatusBarHeight() + dimen(R.dimen.L2))
            applyTo(mHeader as ConstraintLayout)
        }

        getAdapter().addHeaderView(mHeader)
        getAdapter().emptyView = generateEmptyView()
        getAdapter().setHeaderAndEmpty(true)

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

                    if (alpha == 1F)
                        v_header_divider.visible()
                    else
                        v_header_divider.invisible()
                }
            })
        }

        getAdapter().setOnLoadMoreListener({ loadData(false) }, getRecyclerView())

        getHeaderData()
        loadData(true)
    }

    private fun generateEmptyView(): View {
        val emptyView = layoutInflater.inflate(R.layout.empty_layout, getRecyclerView(), false)
        val screenHeight = SystemUtils.getScreenHeight(this@TotalCountParentActivity)
        val headerHeight = mHeader.measureAndGetMeasuredHeight()
        val bottomButtonHeight = dimen(R.dimen.L2)
        val bottomButtonMarginBottom = dimen(R.dimen.L6)
        emptyView.layoutParams.height = screenHeight - headerHeight - bottomButtonHeight - bottomButtonMarginBottom
        return emptyView
    }

    abstract fun getTopMarginViewId(): Int

    abstract fun getTitleResId(): Int

    abstract fun getHeaderData()

    protected open fun loadData(refresh: Boolean) {
        if (refresh) mPage = 0 else mPage++
    }

    abstract fun getHeaderResId(): Int

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getAdapter(): BaseQuickAdapter<*, BaseViewHolder>
}
