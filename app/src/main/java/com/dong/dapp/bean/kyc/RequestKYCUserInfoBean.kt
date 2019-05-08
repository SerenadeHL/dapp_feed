package com.dong.dapp.bean.kyc

import com.google.gson.annotations.SerializedName

data class RequestKYCUserInfoBean(
    @SerializedName("name")    var name: String?,//姓名
    @SerializedName("id_no")   var idNo: String?,//身份证号
    @SerializedName("front_photo")   var frontPhoto: String?,//身份证人面像照片地址
    @SerializedName("back_photo")   var backPhoto: String?,//身份证国徽面照片地址
    @SerializedName("birthday")   var birthday: String?,//出生日期 1970-01-01
    @SerializedName("info")   var info: RequestInfoBean?
)