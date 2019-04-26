package com.dong.dapp.bean.tronweb

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

data class TronDAppRawData(
    @SerializedName("contract") var contract: List<TronDAppContractItem>?,
    @SerializedName("ref_block_bytes") var refBlockBytes: String?,
    @SerializedName("ref_block_hash") var refBlockHash: String?,
    @SerializedName("expiration") var expiration: Long = 0,
    @SerializedName("fee_limit") var feeLimit: Int = 0,
    @SerializedName("timestamp") var timestamp: Long = 0
) : BaseBean()