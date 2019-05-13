package com.axonomy.dapp_feed.ui.mvp.transferdetail.transfercoindetail

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.bean.coin.ResultCoinRecordsItemBean
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.ui.mvp.transferdetail.TransferDetailParentActivity
import kotlinx.android.synthetic.main.activity_transfer_detail.*
import me.serenadehl.base.extensions.gone

/**
 * 金币划转详情页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-13 10:12:13
 */
@Route(path = Router.TRANSFER_COIN_DETAIL_ACTIVITY)
class TransferCoinDetailActivity : TransferDetailParentActivity<ITransferCoinDetailPresenter>() {
    @JvmField
    @Autowired(name = RouterParams.DATA)
    var mData: ResultCoinRecordsItemBean? = null

    override fun createPresenter() = TransferCoinDetailPresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_money.text = String.format(getString(R.string.money_with_symbol), mData?.amount)
        tv_detail.text = mData?.data
        tv_time.text = mData?.timeStamp
        tv_serial_number.text = mData?.hash
        tv_category_label.gone()
        tv_category.gone()
        v_divider1.gone()
        when (mData?.confirmed) {
            0 -> {
                tv_status.setText(R.string.ongoing)
            }
            1 -> {
                tv_status.setText(R.string.finished)
            }
        }
    }

}