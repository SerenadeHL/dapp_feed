package com.dong.dapp.bean.wallet

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

data class TronSignBean(
    @SerializedName("signature") var signature: String?
): BaseBean()