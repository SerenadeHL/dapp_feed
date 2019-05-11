package com.axonomy.dapp_feed.bean.tronweb

import com.google.gson.annotations.SerializedName

data class TronDAppPackValue(
    @SerializedName("data") var data: String,
    @SerializedName("owner_address") var ownerAddress: String,
    @SerializedName("contract_address") var contractAddress: String,
    @SerializedName("to_address") var toAddress: String,
    @SerializedName("call_value") var callValue: Long = 0,
    @SerializedName("amount") var amount: Long = 0
)