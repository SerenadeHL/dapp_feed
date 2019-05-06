package com.dong.dapp.ui.mvp.transferdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.R
import com.dong.dapp.bean.cash.ResultCashRecordDetailBean
import com.dong.dapp.bean.coin.ResultCoinRecordsItemBean
import kotlinx.android.synthetic.main.activity_transfer_detail.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.base.mvpbase.IBasePresenter
import me.serenadehl.base.base.mvpbase.IBaseView
import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.base.mvpbase.MVPBasePresenter
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.visible

/**
 * 划转明细页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-24 16:20:51
 */
class TransferDetailActivity : MVPBaseActivity<ITransferDetailPresenter>(), ITransferDetailView {
    private lateinit var mId: String

    private val mC2 by lazy { ContextCompat.getColor(this@TransferDetailActivity, R.color.C2) }

    override fun createPresenter() = TransferDetailPresenter()

    override fun layout() = R.layout.activity_transfer_detail

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.visible()
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        cl_title.setBackgroundColor(mC2)
        tv_title.setText(R.string.transfer_detail)

        mId = intent.getStringExtra("id")

        loadData()
    }

    private fun loadData() {
        mPresenter.getCashRecordDetail(mId)
    }

    override fun getCashRecordDetailSuccess(data: ResultCashRecordDetailBean?) {
        "getCashRecordDetailSuccess-------> $data".log()
        tv_money.text = String.format(getString(R.string.money_with_symbol), data?.amount)
        tv_detail.text = data?.title
        tv_time.text = data?.createAtStr
        tv_serial_number.text = data?.id
        tv_category.text = data?.cate
        when (data?.doneStatus) {
            0 -> {
                tv_status.setText(R.string.ongoing)
            }
            1, 2 -> {
                tv_status.setText(R.string.finished)
            }
            3 -> {
                tv_status.setText(R.string.failed)
            }
        }
    }

    override fun getCashRecordDetailFailed() {
        "getCashRecordDetailFailed------->".log()
    }
}