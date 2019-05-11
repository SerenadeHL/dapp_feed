package com.axonomy.dapp_feed.ui.mvp.me

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.RuntimeData
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.adapter.recyclerview.MeAdapter
import com.axonomy.dapp_feed.bean.me.MeBean
import com.axonomy.dapp_feed.bean.me.OptionBean
import com.axonomy.dapp_feed.bean.me.ResultUserInfoBean
import com.axonomy.dapp_feed.utils.RouterUtils
import kotlinx.android.synthetic.main.fragment_me.view.*
import me.serenadehl.base.base.mvpbase.MVPBaseFragment
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast

/**
 * 我页面Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
@Route(path = Router.ME_FRAGMENT)
class MeFragment : MVPBaseFragment<IMePresenter>(), IMeView {
    private var mUserInfo: ResultUserInfoBean? = null

    private val mAdapter = MeAdapter()

    override fun layout() = R.layout.fragment_me

    override fun createPresenter() = MePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mRootView.rv_me.adapter = mAdapter
        val router = ARouter.getInstance()
        mAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> {//用户信息
                    router.build(Router.PERSONAL_INFO_ACTIVITY)
                        .withParcelable(RouterParams.DATA, mUserInfo)
                        .navigation()
                }
                1 -> {//邀请好友
                    //TODO 跳转到邀请好友页面
                    toast("邀请好友")
                    null
                }
                3 -> {//签到领金币
                    //TODO 跳转到签到领金币页面
                    toast("签到领金币")
                    null
                }
                5 -> {//我的现金资产
                    router.build(Router.TOTAL_CASH_COUNT_ACTIVITY).navigation()
                }
                6 -> {//我的金币资产
                    router.build(Router.TOTAL_COIN_COUNT_ACTIVITY).navigation()
                }
                8 -> {//联系客服
                    //TODO 跳转到联系客服页面
                    toast("联系客服")
                    null
                }
                9 -> {//关于我们
                    //TODO 跳转到关于我们页面
                    RouterUtils.route("")
                }
                10 -> {//设置
                    router.build(Router.SETTINGS_ACTIVITY).navigation()
                }
            }
        }
        mAdapter.setNewData(createOptions())
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        mPresenter.getUserInfo()
    }

    private fun createOptions(): List<MeBean> {
        val options = mutableListOf<MeBean>()
        RuntimeData.mResultCommonConfigurationBean?.menu?.forEach { group ->
            group.forEach {
                val option = OptionBean(
                    R.mipmap.me_user,
                    it.title,
                    it.desc,
                    it.descColor,
                    true,
                    true,
                    it.pathAndroid
                )
                options.add(MeBean(false, option))
            }
            options.add(MeBean(true, null))
        }
        return options
    }

    override fun getUserInfoSuccess(data: ResultUserInfoBean?) {
        "getUserInfoSuccess-------> $data".log()
        mUserInfo = data
        mAdapter.getItem(0)?.option?.apply {
            title = data?.account
            //TODO 根据kycStatus设置不同文字
            text = data?.kycStatus?.toString()
        }
    }

    override fun getUserInfoFailed() {
        "getUserInfoFailed------->".log()
    }

}
