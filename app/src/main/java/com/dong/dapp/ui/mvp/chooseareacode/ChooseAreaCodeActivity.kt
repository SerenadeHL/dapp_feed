package com.dong.dapp.ui.mvp.chooseareacode

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.ArrayMap
import android.webkit.WebMessagePort
import com.dong.dapp.Constant
import com.dong.dapp.R
import com.dong.dapp.adapter.recyclerview.ChooseAreaCodeAdapter
import com.dong.dapp.bean.areacode.ResultAreaCodeBean
import com.dong.dapp.bean.areacode.ResultAreaCodeItemBean
import com.dong.dapp.ui.mvp.login.LoginActivity
import com.dong.dapp.widget.SideBar
import com.dong.dapp.widget.TitleItemDecoration
import com.dong.dapp.widget.TopSmoothScroller
import kotlinx.android.synthetic.main.activity_choose_area_code.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.log

/**
 *
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-26 16:49:36
 */
class ChooseAreaCodeActivity : MVPBaseActivity<IChooseAreaCodePresenter>(), IChooseAreaCodeView {
    private val mAdapter by lazy { ChooseAreaCodeAdapter() }
    private val mIndexMap by lazy { ArrayMap<Int, String>() }
    private val mC2 by lazy { ContextCompat.getColor(this@ChooseAreaCodeActivity, R.color.C2) }
    private val mTopSmoothScroller by lazy { TopSmoothScroller(this@ChooseAreaCodeActivity) }

    override fun layout() = R.layout.activity_choose_area_code

    override fun createPresenter() = ChooseAreaCodePresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setText(R.string.choose_area_code)
        cl_title.setBackgroundColor(mC2)

        val decoration = initTitleDecoration()
        rv_area_codes.apply {
            adapter = mAdapter
            addItemDecoration(decoration)
        }

        mAdapter.setOnItemClickListener { _, _, position ->
            val item = mAdapter.getItem(position)
            setResult(Activity.RESULT_OK, Intent().apply { putExtra(Constant.AREA_CODE, item?.code) })
            finish()
        }

        sb_side_bar.setTextDialog(tv_dialog)
        sb_side_bar.setOnTouchingLetterChangedListener(object : SideBar.OnTouchingLetterChangedListener {
            override fun onTouchingLetterChanged(s: String) {
                "当前选中------> $s".log()
                val index = mIndexMap.values.indexOf(s)
                if (index < 0) return//区号表不存在该字母
                mTopSmoothScroller.targetPosition = mIndexMap.keyAt(index)
                rv_area_codes.layoutManager?.startSmoothScroll(mTopSmoothScroller)
            }
        })

        mPresenter.getAreaCode()
    }

    private fun initTitleDecoration(): RecyclerView.ItemDecoration {
        return TitleItemDecoration(this@ChooseAreaCodeActivity, object : TitleItemDecoration.TitleDecorationCallback {
            override fun getGroupId(position: Int): Int {
                return mIndexMap.values.indexOf(getGroupName(position))
            }

            override fun getGroupName(position: Int): String {
                var groupTitle = ""

                val lastIndex = mIndexMap.keys.size - 1
                for (index in lastIndex downTo 0) {
                    if (position >= mIndexMap.keyAt(index)) {
                        groupTitle = mIndexMap.valueAt(index)
                        break
                    }
                }
                return groupTitle
            }
        }).apply {
            setTitleTextSize(14)
            setTitleBackgroundColor(R.color.C3)
            setTitleTextColor(R.color.C6)
            setTitleHeightResId(R.dimen.dp_group_height)
            setTitlePaddingLeft(R.dimen.L7)
        }
    }

    override fun setAreaCode(data: List<ResultAreaCodeBean>) {
        val total = arrayListOf<ResultAreaCodeItemBean>()
        data.forEach {
            mIndexMap[total.size] = it.index
            total.addAll(it.items)
        }
        mAdapter.setTitleIndex(mIndexMap.keys)
        mAdapter.setNewData(total)
    }
}