package com.axonomy.dapp_feed.bean.tronweb

import com.google.gson.annotations.SerializedName

data class TronDAppContractItem(
    @SerializedName("parameter") var tronDAppParameter: TronDAppParameter?,
    @SerializedName("type") var type: String?
)