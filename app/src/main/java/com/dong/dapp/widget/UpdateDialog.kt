package com.dong.dapp.widget

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.support.annotation.RequiresApi
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.dong.dapp.R
import com.dong.dapp.bean.update.ResultUpdateInfoBean
import com.dong.dapp.utils.DownloadUtil
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.dialog_update.view.*
import me.serenadehl.base.extensions.gone
import me.serenadehl.base.extensions.md5
import me.serenadehl.base.extensions.visible
import java.io.File


/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-01-18 14:48:04
 */
class UpdateDialog(val activity: AppCompatActivity, val info: ResultUpdateInfoBean) {
    private lateinit var mDialog: AlertDialog

    private lateinit var mView: View
    private var mDownloaded = false

    private val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
    private val name = "dapp_feed.apk"
    private val apk = File(dir, name)

    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                DOWNLOAD_SUCCESS -> {
                    mView.btn_update.setText(R.string.install_immediately)
                    mView.btn_update.isEnabled = true
                    mDownloaded = true
                    installApk(apk)
                    removeCallbacksAndMessages(null)
                }
                DOWNLOAD_FAILED -> {
                    mView.btn_update.setText(R.string.download_failed)
                    mView.btn_update.isEnabled = true
                    mDownloaded = false
                    removeCallbacksAndMessages(null)
                }
                DOWNLOADING -> {
                    mView.btn_update.text =
                        String.format(activity.getString(R.string.downloading_with_progress), msg.arg1)
                }
            }
        }
    }

    companion object {
        const val DOWNLOAD_SUCCESS = 1
        const val DOWNLOADING = 2
        const val DOWNLOAD_FAILED = 3

        const val REQUEST_INSTALL_PERMISSION_CODE = 10086
    }

    init {
        val cancelable = info.updateType != 0//是否可取消

        mView = LayoutInflater.from(activity).inflate(R.layout.dialog_update, null)

        mDialog = AlertDialog.Builder(activity, R.style.UpdateDialog)
            .setView(mView)
            .setCancelable(false)
            .setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
                    if (cancelable && mDialog.isShowing) {
                        mDialog.dismiss()
                        true
                    } else {
                        activity.onKeyDown(keyCode, event)
                        false
                    }
                } else {
                    false
                }
            }
            .create()

        //判断本地是否已经下载过了
        mDownloaded = apk.exists() && apk.md5() == info.md5
        //如果已经存在的不是最新版本则删除本地apk
        if (!mDownloaded && apk.exists()) apk.delete()

        //更新文案
        mView.tv_description.text = info.content
        //设置已下载情况下按钮文案
        mView.btn_update.setText(if (mDownloaded) R.string.install_immediately else R.string.experience_immediately)

        //关闭按钮、更新按钮，是否强制更新
        if (cancelable) {
            mView.btn_cancel.visible()
            mView.btn_update.setBackgroundResource(R.drawable.update_experience_immediately_single_corner_btn_bg)
        } else {
            mView.btn_cancel.gone()
            mView.btn_update.setBackgroundResource(R.drawable.update_experience_immediately_double_corner_btn_bg)
        }
        //关闭按钮
        mView.btn_cancel.setOnClickListener { mDialog.dismiss() }
        //更新按钮
        mView.btn_update.setOnClickListener {
            RxPermissions(activity)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted) {
                        //如果已经下载了直接安装
                        if (mDownloaded) {
                            installApk(apk)
                            return@subscribe
                        }
                        //开始下载，改变按钮状态
                        mView.btn_update.apply {
                            text =
                                String.format(activity.getString(R.string.downloading_with_progress), "0")
                            isEnabled = false
                        }
                        downloadApk()
                    }
                }
        }
    }

    /**
     * 下载apk
     */
    private fun downloadApk() {
        DownloadUtil.get().download(info.url, dir, name, object : DownloadUtil.OnDownloadListener {
            override fun onDownloadSuccess() {
                mHandler.sendMessage(Message.obtain().apply {
                    what = DOWNLOAD_SUCCESS
                })
            }

            override fun onDownloading(progress: Int) {
                mHandler.sendMessage(Message.obtain().apply {
                    what = DOWNLOADING
                    arg1 = progress
                })
            }

            override fun onDownloadFailed() {
                mHandler.sendMessage(Message.obtain().apply {
                    what = DOWNLOAD_FAILED
                })
            }
        })
    }

    /**
     * 安装apk
     */
    @SuppressLint("CheckResult")
    private fun installApk(apkFile: File) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val installAllowed = activity.packageManager.canRequestPackageInstalls()
            if (installAllowed) {
                installApkCompat(apkFile)
            } else {
                startInstallPermissionSettingActivity(activity)
            }

        } else {
            installApkCompat(apkFile)
        }
    }

    /**
     * 开启设置安装未知来源应用权限界面
     * @param context
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun startInstallPermissionSettingActivity(activity: AppCompatActivity) {
        val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, Uri.parse("package:${activity.packageName}"))
        activity.startActivityForResult(intent, REQUEST_INSTALL_PERMISSION_CODE)
    }

    private fun installApkCompat(apkFile: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val contentUri = FileProvider.getUriForFile(
                activity
                , "com.dong.dapp.fileProvider"
                , apkFile
            )
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive")
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive")
        }
        activity.startActivity(intent)
    }

    fun show() = mDialog.show()

    fun dismiss() = mDialog.dismiss()

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_INSTALL_PERMISSION_CODE && resultCode == Activity.RESULT_OK) {
            installApk(apk)
        }
    }
}
