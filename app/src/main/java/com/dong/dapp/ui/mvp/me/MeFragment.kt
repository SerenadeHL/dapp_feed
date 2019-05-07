package com.dong.dapp.ui.mvp.me

import com.dong.dapp.R
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.dong.dapp.adapter.recyclerview.MeAdapter
import com.dong.dapp.bean.me.MeBean
import com.dong.dapp.bean.me.OptionBean
import com.dong.dapp.bean.me.ResultUserInfoBean
import com.dong.dapp.ui.activity.PersonalInfoActivity
import com.dong.dapp.ui.activity.SettingsActivity
import com.dong.dapp.ui.mvp.totalcount.totalcashcount.TotalCashCountActivity
import com.dong.dapp.ui.mvp.totalcount.totalcoincount.TotalCoinCountActivity
import com.dong.dapp.utils.LoginUtils
import kotlinx.android.synthetic.main.fragment_me.view.*

import me.serenadehl.base.base.mvpbase.MVPBaseFragment
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.startActivity
import me.serenadehl.base.extensions.toast

/**
 * 我页面Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
class MeFragment : MVPBaseFragment<IMePresenter>(), IMeView {
    private var mUserInfo: ResultUserInfoBean? = null

    private val mAdapter = MeAdapter()

    override fun layout() = R.layout.fragment_me

    override fun createPresenter() = MePresenter()

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mRootView.rv_me.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            when (position) {
                0 -> {//用户信息
                    startActivity<PersonalInfoActivity>("data" to mUserInfo)
                }
                1 -> {//邀请好友
                    //TODO 跳转到邀请好友页面
                    toast("邀请好友")
                }
                3 -> {//签到领金币
                    //TODO 跳转到签到领金币页面
                    toast("签到领金币")
                }
                5 -> {//我的现金资产
                    startActivity<TotalCashCountActivity>()
                }
                6 -> {//我的金币资产
                    startActivity<TotalCoinCountActivity>()
                }
                8 -> {//联系客服
                    //TODO 跳转到联系客服页面
                    toast("联系客服")
                }
                9 -> {//关于我们
                    //TODO 跳转到关于我们页面
                    toast("关于我们")
                }
                10 -> {//设置
                    startActivity<SettingsActivity>()
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
        return listOf(
            MeBean(
                false, OptionBean(
                    R.mipmap.me_user,
                    null,
                    null,
                    ContextCompat.getColor(requireActivity(), R.color.C7),
                    true,
                    true
                )
            ),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_active,
                    "邀请好友",
                    null,
                    ContextCompat.getColor(requireActivity(), R.color.C7),
                    true,
                    false
                )
            ),
            MeBean(true, null),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_sign,
                    "签到领金币",
                    null,
                    ContextCompat.getColor(requireActivity(), R.color.C7),
                    true,
                    false
                )
            ),
            MeBean(true, null),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_my_cash,
                    "我的现金资产",
                    "今日收益：￥23.56",
                    ContextCompat.getColor(requireActivity(), R.color.color_FF6B00),
                    true,
                    true
                )
            ),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_my_coin,
                    "我的金币资产",
                    "今日收益：￥20.56",
                    ContextCompat.getColor(requireActivity(), R.color.color_FF6B00),
                    true,
                    false
                )
            ),
            MeBean(true, null),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_customer,
                    "联系客服",
                    null,
                    ContextCompat.getColor(requireActivity(), R.color.color_FF6B00),
                    true,
                    true
                )
            ),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_about_us,
                    "关于我们",
                    null,
                    ContextCompat.getColor(requireActivity(), R.color.color_FF6B00),
                    true,
                    true
                )
            ),
            MeBean(
                false, OptionBean(
                    R.mipmap.me_settings,
                    "设置",
                    null,
                    ContextCompat.getColor(requireActivity(), R.color.color_FF6B00),
                    true,
                    false
                )
            )
        )
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
