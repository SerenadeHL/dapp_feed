package com.axonomy.dapp_feed.bean.wallet

import com.google.gson.annotations.SerializedName

data class TronSignBean(
    @SerializedName("signature") var signature: String?
)