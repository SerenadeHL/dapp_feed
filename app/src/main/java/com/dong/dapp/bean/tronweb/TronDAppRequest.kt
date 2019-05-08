package com.dong.dapp.bean.tronweb

import com.google.gson.annotations.SerializedName

data class TronDAppRequest(
    @SerializedName("signature") var signature: List<String>? ,
    @SerializedName("txID") var txID: String? ,
    @SerializedName("raw_data") var tronDAppRawData: TronDAppRawData?,
    @SerializedName("host") var host: String? ,
    @SerializedName("href") var href: String?
)