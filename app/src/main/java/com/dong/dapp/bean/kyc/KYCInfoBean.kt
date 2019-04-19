package com.dong.dapp.bean.kyc

import android.graphics.PointF
import com.dong.dapp.bean.wallet.BaseBean
import com.megvii.faceidiol.sdk.manager.LegalityStruct

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-18 16:43:54
 */
data class KYCInfoBean(
    var biz_token: String?,//bizToken
    var id_info: KYCUserInfoBean?//用户信息
) : BaseBean() {
    data class KYCUserInfoBean(
        var name: String?,//姓名
        var id_no: String?,//身份证号
        var front_photo: String?,//身份证人面像照片地址
        var back_photo: String?,//身份证国徽面照片地址
        var birthday: String?,//出生日期 1970-01-01
        var info: InfoBean?
    )

    data class InfoBean(
        var userIDCardName: String?,//姓名
        var userIDCardGender: String?,//性别
        var userIDCardNationality: String?,//民族
        var userBirthYear: String?,//出生年
        var userBirthMonth: String?,//出生月
        var userBirthDay: String?,//出生日
        var userIDCardNumber: String?,//身份证号
        var userAddress: String?,//住址
        var userIssueBy: String?,//签发机关
        var userValidDateStart: String?,//有效日期起始时间
        var userValidDateEnd: String?,//有效日期结束时间
        var idcardPortraitRect: Array<PointF>?,//人像面证件区域
        var idcardPortraitCompleteType: Int?,//人像面完整性
        var idcardPortraitLegalityItem: LegalityStruct?,//人像面五种分类结果
        var idcardEmblemRect: Array<PointF>?,//国徽面证件区域
        var idcardEmblemCompleteType: Int?,//国徽面完整性
        var idcardEmblemLegalityItem: LegalityStruct?//国徽面五种分类结果
    )
}