package com.axonomy.dapp_feed.bean.tronweb

import com.google.gson.annotations.SerializedName

data class TronDAppParameter(
    @SerializedName("value") var value: TronDAppPackValue?,
    @SerializedName("type_url") var typeUrl: String?
)