package com.dong.dapp.bean.tronweb

import com.google.gson.annotations.SerializedName

data class TronDAppContractItem(
    @SerializedName("parameter") var tronDAppParameter: TronDAppParameter?,
    @SerializedName("type") var type: String?
)