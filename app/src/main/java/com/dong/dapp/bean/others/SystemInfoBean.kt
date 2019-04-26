package com.dong.dapp.bean.others

import com.dong.dapp.bean.BaseBean
import com.google.gson.annotations.SerializedName

data class SystemInfoBean(
    @SerializedName("os") var os: String,
    @SerializedName("os_ver") var osVer: String,
    @SerializedName("app_ver") var appVer: String,
    @SerializedName("dist") var dist: String,
    @SerializedName("imei") var imei: String,
    @SerializedName("mac_addr") var macAddr: String,
    @SerializedName("network") var network: String,
    @SerializedName("screen_width") var screenWidth: String,
    @SerializedName("screen_height") var screenHeight: String,
    @SerializedName("device_brand") var deviceBrand: String,
    @SerializedName("device_model") var deviceModel: String
) : BaseBean()