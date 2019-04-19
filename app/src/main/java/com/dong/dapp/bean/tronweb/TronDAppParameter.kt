package com.dong.dapp.bean.tronweb

import com.google.gson.annotations.SerializedName

data class TronDAppParameter(
    @SerializedName("value") var value: TronDAppPackValue?,
    @SerializedName("type_url") var typeUrl: String?
)