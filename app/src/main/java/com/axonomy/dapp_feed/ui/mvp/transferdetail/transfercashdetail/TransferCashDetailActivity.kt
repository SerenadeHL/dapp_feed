package com.axonomy.dapp_feed.ui.mvp.transferdetail.transfercashdetail

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.cash.ResultCashRecordDetailBean
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.ui.mvp.transferdetail.TransferDetailParentActivity
import kotlinx.android.synthetic.main.activity_transfer_detail.*
import me.serenadehl.base.extensions.log

/**
 * 现金划转详情页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 10:08:26
 */
@Route(path = Router.TRANSFER_CASH_DETAIL_ACTIVITY)
class TransferCashDetailActivity :TransferDetailParentActivity<ITransferCashDetailPresenter>(), ITransferCashDetailView {
    @JvmField
    @Autowired(name = RouterParams.ID)
    var mId: String? = null

    override fun createPresenter() = TransferCashDetailPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
    }

    private fun loadData() {
        mPresenter.getCashRecordDetail(mId ?: "")
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