package com.dong.dapp.bean.kyc

import android.graphics.PointF
import com.google.gson.annotations.SerializedName
import com.megvii.faceidiol.sdk.manager.LegalityStruct

data class RequestInfoBean(
    @SerializedName("userIDCardName") var userIDCardName: String?,//姓名
    @SerializedName("userIDCardGender") var userIDCardGender: String?,//性别
    @SerializedName("userIDCardNationality") var userIDCardNationality: String?,//民族
    @SerializedName("userBirthYear") var userBirthYear: String?,//出生年
    @SerializedName("userBirthMonth") var userBirthMonth: String?,//出生月
    @SerializedName("userBirthDay") var userBirthDay: String?,//出生日
    @SerializedName("userIDCardNumber") var userIDCardNumber: String?,//身份证号
    @SerializedName("userAddress") var userAddress: String?,//住址
    @SerializedName("userIssueBy") var userIssueBy: String?,//签发机关
    @SerializedName("userValidDateStart") var userValidDateStart: String?,//有效日期起始时间
    @SerializedName("userValidDateEnd") var userValidDateEnd: String?,//有效日期结束时间
    @SerializedName("idcardPortraitRect") var idcardPortraitRect: Array<PointF>?,//人像面证件区域
    @SerializedName("idcardPortraitCompleteType") var idcardPortraitCompleteType: Int?,//人像面完整性
    @SerializedName("idcardPortraitLegalityItem") var idcardPortraitLegalityItem: LegalityStruct?,//人像面五种分类结果
    @SerializedName("idcardEmblemRect") var idcardEmblemRect: Array<PointF>?,//国徽面证件区域
    @SerializedName("idcardEmblemCompleteType") var idcardEmblemCompleteType: Int?,//国徽面完整性
    @SerializedName("idcardEmblemLegalityItem") var idcardEmblemLegalityItem: LegalityStruct?//国徽面五种分类结果
)