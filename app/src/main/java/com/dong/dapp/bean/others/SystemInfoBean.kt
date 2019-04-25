package com.dong.dapp.bean.others

import com.dong.dapp.bean.BaseBean

data class SystemInfoBean(
    var os: String,
    var os_ver: String,
    var app_ver: String,
    var dist: String,
    var imei: String,
    var mac_addr: String,
    var network: String,
    var screen_width: String,
    var screen_height: String,
    var device_brand: String,
    var device_model: String
) : BaseBean()