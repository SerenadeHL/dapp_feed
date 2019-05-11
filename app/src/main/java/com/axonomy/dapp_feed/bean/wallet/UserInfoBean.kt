package com.axonomy.dapp_feed.bean.wallet

import com.google.gson.annotations.SerializedName

data class UserInfoBean(
    @SerializedName("public_key") var publicKey: String?,
    @SerializedName("tron_account") var tronAccount: String?
)
