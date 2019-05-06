package com.dong.dapp.bean.me

import com.dong.dapp.bean.BaseBean

/**
 * 我页面ItemBean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-22 10:44:43
 */
data class MeBean(
    var isDivider: Boolean,//是否是分割线类型
    var option: OptionBean?//选项
) : BaseBean()