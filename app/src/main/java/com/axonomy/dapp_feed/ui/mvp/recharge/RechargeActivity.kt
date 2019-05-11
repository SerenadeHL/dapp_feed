package com.axonomy.dapp_feed.ui.mvp.recharge

import com.axonomy.dapp_feed.R
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.utils.PayUtils
import kotlinx.android.synthetic.main.activity_recharge.*
import kotlinx.android.synthetic.main.title_layout.*

import me.serenadehl.base.base.mvpbase.MVPBaseActivity
import me.serenadehl.base.extensions.getStatusBarHeight
import me.serenadehl.base.extensions.invisible
import me.serenadehl.base.extensions.log

/**
 * 充值页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-5-7 16:14:11
 */
@Route(path = Router.RECHARGE_ACTIVITY)
class RechargeActivity : MVPBaseActivity<IRechargePresenter>(), IRechargeView {
    private val mC2 by lazy { ContextCompat.getColor(this@RechargeActivity, R.color.C2) }

    override fun layout() = R.layout.activity_recharge

    override fun createPresenter() = RechargePresenter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //设置状态栏
        setStatusBarTranslucent(false)
        //返回按钮
        iv_back.setImageResource(R.mipmap.white_left_arrow)
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.setTextColor(mC2)
        tv_title.setText(R.string.recharge_game_coins)
        v_header_divider.invisible()
        //设置StatusBar的高度
        v_status_bar.layoutParams.height = getStatusBarHeight()

        btn_pay.setOnClickListener {
            //TODO 获取订单
            val order =
                "format=json&timestamp=2019-05-11+10%3A23%3A15&charset=utf-8&app_id=2019042564288575&biz_content=%22%7B%5C%22out_trade_no%5C%22%3A%5C%22bb6b8908739311e99eea00163e10b855%5C%22%2C%5C%22product_code%5C%22%3A%5C%22trx-5%5C%22%2C%5C%22seller_id%5C%22%3A%5C%222088431981538912%5C%22%2C%5C%22timeout_express%5C%22%3A%5C%2210m%5C%22%2C%5C%22total_amount%5C%22%3A%5C%225.0000000000%5C%22%7D%22&sign=iWYIy3THffk7ROdiM9YpyByZCy0xxljmLdHzFPvl4I878XSXlWIPJUoa4KASZlfqzqQjWrVvDAD3fnc4ve8BxmqSCartIJh99DVNfUYp7PoV34KhWOGPdNK6g%2FgBoMy%2Bsy3uW3u8%2FlVYBtfn3PCSbI%2F%2B0iyVWq4GmF4fAhXaOcbyfWs%2FYKWimkknmGg4y4VceaqMQKaAY0P3srhmUH%2Bc%2B09O%2B770rCi9tjpuZpNUI2yb01w6my2hUIOXTKNV8c4rlImXOfTVT8KWodOL8gDnMqyiD54bzpgoMcjyN1YcvbiDqx%2FY4NxTFgSMuTK%2BcfizCTI0tW5NWJKcUUGJxX82vg%3D%3D&version=1.0&sign_type=RSA2&method=alipay.trade.app.pay"
            PayUtils.alipay(this@RechargeActivity, order) {
                "resultStatus-------> ${it["resultStatus"]}".log()
                if (it["resultStatus"] == "9000") {
                    ARouter.getInstance()
                        .build(Router.RECHARGE_SUCCESS_ACTIVITY)
                        .navigation()
                }
            }
        }
    }

}
