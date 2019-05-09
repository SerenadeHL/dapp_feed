package com.dong.dapp.constant

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-07 19:01:43
 */
object Router {

    //=========================================Activity=========================================
    const val SPLASH_ACTIVITY = "/ui/mvp/splash/SplashActivity"//启动页
    const val MAIN_ACTIVITY = "/ui/mvp/main/MainActivity"//主页

    const val LOGIN_ACTIVITY = "/ui/mvp/login/LoginActivity"//登录页
    const val CHOOSE_AREA_CODE_ACTIVITY = "/ui/mvp/chooseareacode/ChooseAreaCodeActivity"//选择区号页

    const val DAPP_WEB_ACTIVITY = "/ui/mvp/web/DAppWebActivity"//DAppWebView页
    const val COMMON_WEB_ACTIVITY = "/ui/activity/CommonWebActivity"//普通WebView页

    const val SETTINGS_ACTIVITY = "/ui/activity/SettingsActivity"//设置页
    const val PERSONAL_INFO_ACTIVITY = "/ui/activity/PersonalInfoActivity"//个人信息页
    const val KYC_ACTIVITY = "/ui/mvp/kyc/KYCActivity"//KYC页

    const val TOTAL_CASH_COUNT_ACTIVITY = "/ui/mvp/totalcount/totalcashcount/TotalCashCountActivity"//现金资产页
    const val TOTAL_COIN_COUNT_ACTIVITY = "/ui/mvp/totalcount/totalcoincount/TotalCoinCountActivity"//金币资产页
    const val TRANSFER_CASH_ACTIVITY = "/ui/mvp/transfer/transfercash/TransferCashActivity"//划转现金页
    const val TRANSFER_COIN_ACTIVITY = "/ui/mvp/transfer/transfercoin/TransferCoinActivity"//划转金币页
    const val RECHARGE_ACTIVITY = "/ui/mvp/recharge/RechargeActivity"//充值页
    const val TRANSFER_DETAIL_ACTIVITY = "/ui/mvp/transferdetail/TransferDetailActivity"//划转详情页
    const val TRANSFER_SUCCESS_ACTIVITY = "/ui/activity/TransferSuccessActivity"//划转成功页
    const val RECHARGE_SUCCESS_ACTIVITY = "/ui/activity/RechargeSuccessActivity"//划转成功页

    //=========================================Fragment=========================================
    const val GAME_SQUARE_FRAGMENT = "/ui/mvp/gamesquare/GameSquareFragment"//游戏广场页

    const val ME_FRAGMENT = "/ui/mvp/me/MeFragment"//我页
}