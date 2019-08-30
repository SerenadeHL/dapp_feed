package com.axonomy.dapp_feed.ui.fragment

import android.content.*
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.RuntimeData
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.adapter.recyclerview.MeAdapter
import com.axonomy.dapp_feed.bean.me.MeBean
import com.axonomy.dapp_feed.bean.me.OptionBean
import com.axonomy.dapp_feed.utils.DialogUtils
import com.axonomy.dapp_feed.utils.RouterUtils
import kotlinx.android.synthetic.main.fragment_me.view.*
import me.serenadehl.base.base.BaseFragment
import me.serenadehl.base.extensions.copyToClipboard
import me.serenadehl.base.extensions.toast
import org.bouncycastle.asn1.x500.style.RFC4519Style.title

/**
 * 我页面Fragment
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-4-10 19:56:56
 */
@Route(path = Router.ME_FRAGMENT)
class MeFragment : BaseFragment() {
    private val mAdapter = MeAdapter()

    override fun layout() = R.layout.fragment_me

    override fun onViewCreated(savedInstanceState: Bundle?) {
        mRootView.rv_me.adapter = mAdapter
        mAdapter.setOnItemClickListener { _, _, position ->
            RouterUtils.route(mAdapter.getItem(position)?.option?.path) { methodName, paramsMap ->
                when (methodName) {
                    RouterUtils.SERVICE_CELL_CLICK -> {
                        serviceCellClick(paramsMap[RouterUtils.SERVICE])
                    }
                    else -> {
                    }
                }
            }
        }
        mAdapter.setNewData(createOptions())
    }

    override fun onResume() {
        super.onResume()
        setUserInfo()
        setIncomeInfo()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            setUserInfo()
            setIncomeInfo()
            mAdapter.notifyDataSetChanged()
        }
    }

    /**
     * 设置收益信息
     */
    private fun setIncomeInfo() {
        //设置金币收益
        mAdapter.data.find { it.option?.id == 4 }?.option?.text =
            String.format(getString(R.string.today_revenue), RuntimeData.mTodayCoinRevenue)

        //设置现金收益
        mAdapter.data.find { it.option?.id == 5 }?.option?.text =
            String.format(getString(R.string.today_revenue_with_symbol), RuntimeData.mTodayCashRevenue)
    }

    /**
     * 设置个人信息
     */
    private fun setUserInfo() {
        mAdapter.data.find { it.option?.id == 0 }?.option?.apply {
            title = RuntimeData.mResultUserInfoBean?.account
            text = when (RuntimeData.mResultUserInfoBean?.kycStatus) {
                4 -> getString(R.string.kyc_identified)
                3 -> getString(R.string.kyc_identifing)
                else -> getString(R.string.kyc_unidentified)
            }
        }
    }

    /**
     * 构造菜单选项
     */
    private fun createOptions(): List<MeBean> {
        val options = mutableListOf<MeBean>()
        RuntimeData.mResultCommonConfigurationBean?.menu?.forEach { group ->
            group.withIndex().forEach {
                val showDivider = it.index != group.size - 1
                val showRightArrow = it.value.style == 2
                val option = OptionBean(
                    it.value.icon,
                    it.value.title,
                    it.value.desc,
                    it.value.descColor,
                    showRightArrow,
                    showDivider,
                    it.value.pathAndroid,
                    it.value.id
                )
                options.add(MeBean(false, option))
            }
            options.add(MeBean(true, null))
        }
        return options
    }

    /**
     * 联系客服
     */
    private fun serviceCellClick(service: String?) {
        service?.copyToClipboard(requireActivity())
        toast(String.format(getString(R.string.wechat_number_copied), service))
        DialogUtils.show(
            requireActivity(),
            R.string.open_wechat,
            DialogInterface.OnClickListener { _, _ ->
                openWeChat()
            })
    }

    private fun openWeChat() {
        val intent = Intent()
        intent.action = Intent.ACTION_MAIN
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.component = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
        startActivity(intent)
    }
}
