package com.dong.dapp.network

import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import me.serenadehl.base.extensions.log
import me.serenadehl.base.extensions.toast
import me.serenadehl.base.utils.app.AppManager

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2018-02-21 16:24:40
 */
abstract class BaseObserver<T> : Observer<BaseResponse<T>> {

    override fun onError(e: Throwable) {
        e.printStackTrace()
        when (e) {
            is SocketTimeoutException -> AppManager.instance.currentActivity.toast("请求超时！")
            is ConnectException -> AppManager.instance.currentActivity.toast("网络中断，请检查您的网络状态！")
            is UnknownHostException -> AppManager.instance.currentActivity.toast("网络错误，请检查您的网络状态！")
            else -> {
                "=====RxJava=====error===========>".log()
            }
        }
        error(e)
    }

    open fun error(error: Throwable) {

    }

    override fun onSubscribe(@NonNull d: Disposable) {
        subscribe(d)
    }

    open fun subscribe(d: Disposable){

    }

    override fun onNext(t: BaseResponse<T>) {
        next(t.data)
    }

    abstract fun next(@NonNull data: T?)

    override fun onComplete() {
        complete()
    }

    open fun complete() {

    }
}
