package com.dong.dapp.bean.others

import com.google.gson.annotations.SerializedName

/**
 * 系统信息Bean
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-30 10:16:17
 */
data class SystemInfoBean(
    @SerializedName("os") var os: String,//操作系统
    @SerializedName("os_ver") var osVer: String,//操作系统版本
    @SerializedName("app_ver") var appVer: String,//App版本
    @SerializedName("dist") var dist: String,//渠道
    @SerializedName("imei") var imei: String,//设备IMEI码
    @SerializedName("mac_addr") var macAddr: String,//设备MAC地址
    @SerializedName("network") var network: String,//网络类型{-1,0,2,3,4,5}分别表示{无法取到网络状态，wifi,2G,3G,4G,5G}
    @SerializedName("screen_width") var screenWidth: String,//屏幕宽度
    @SerializedName("screen_height") var screenHeight: String,//屏幕高度
    @SerializedName("device_brand") var deviceBrand: String,//手机品牌
    @SerializedName("device_model") var deviceModel: String//手机型号
)