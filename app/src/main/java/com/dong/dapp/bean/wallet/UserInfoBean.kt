package com.dong.dapp.bean.wallet

import com.google.gson.annotations.SerializedName

data class UserInfoBean(
    @SerializedName("public_key") var publicKey: String?,
    @SerializedName("tron_account") var tronAccount: String?
)
