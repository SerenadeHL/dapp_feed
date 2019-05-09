package com.dong.dapp.widget

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Message
import android.support.v4.content.FileProvider
import android.support.v7.app.AlertDialog
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.dong.dapp.R
import com.dong.dapp.bean.update.ResultUpdateInfoBean
import com.dong.dapp.utils.MD5Util
import me.serenadehl.base.extensions.md5
import java.io.File

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-01-18 14:48:04
 */
class UpdateDialog(activity: Activity, info: ResultUpdateInfoBean) {
    private lateinit var mDialog: AlertDialog
    private lateinit var btnUpdate: Button
    private var mDownloaded = false

    private val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
    private val name = "dapp_feed.apk"
    private val apk = File(dir, name)

    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                DOWNLOAD_SUCCESS -> {
                    btnUpdate.setText(R.string.install_immediately)
                    btnUpdate.setBackgroundResource(R.drawable.update_btn_bg)
                    btnUpdate.isEnabled = true
                    mDownloaded = true
                    install(activity, apk)
                    removeCallbacksAndMessages(null)
                }
                DOWNLOAD_FAILED -> {
                    btnUpdate.setText(R.string.download_failed)
                    btnUpdate.setBackgroundResource(R.drawable.update_btn_bg)
                    btnUpdate.isEnabled = true

                    removeCallbacksAndMessages(null)
                }
                DOWNLOADING -> {
                    btnUpdate.text = "正在安装...${msg.arg1}%"
                }
            }
        }
    }

    companion object {
        const val DOWNLOAD_SUCCESS = 1
        const val DOWNLOADING = 2
        const val DOWNLOAD_FAILED = 3
    }

    init {
        val cancelable = info.updateType != 0//是否可取消

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_update, null)

        mDialog = AlertDialog.Builder(activity, R.style.UpdateDialog)
            .setView(view)
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
        mDownloaded = if (apk.exists()) MD5Util.getMD5(apk) == info.key else false

        //如果已经存在的不是最新版本则删除本地apk
        if (!mDownloaded && apk.exists()) apk.delete()

        //更新文案
        view.findViewById<TextView>(R.id.tv_content).text = SpannableString("功能介绍：${info.content}").apply {
            setSpan(StyleSpan(Typeface.BOLD), 0, 5, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        //关闭按钮，是否强制更新
        view.findViewById<ImageView>(R.id.iv_close).apply {
            visibility = if (cancelable) View.VISIBLE else View.GONE
            setOnClickListener {
                mDialog.dismiss()
            }
        }
        //版本号
        view.findViewById<TextView>(R.id.tv_version_name).text = "版本号：${info.name}"
        //安装包大小
        view.findViewById<TextView>(R.id.tv_apk_size).text = "安装包：${info.size}"
        //下载按钮
        btnUpdate = view.findViewById(R.id.btn_update)
        btnUpdate.apply {
            setText(if (mDownloaded) R.string.install_immediately else R.string.update_immediately)
            setOnClickListener {
                //如果已经下载了直接安装
                if (mDownloaded) {
                    install(activity, apk)
                    return@setOnClickListener
                }

                //开始下载，改变按钮状态
                this@apply.text = "正在安装...0%"
                this@apply.setBackgroundResource(R.drawable.updating_btn_bg)
                this@apply.isEnabled = false
                //下载apk
                AppUpdateUtil.download(info.apk, dir, name, object : DownloadUtil.OnDownloadListener {
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
        }

        //图片
        PictureUtils.showTopCornerRound(info.imglist, view.findViewById(R.id.iv_img), 12f, R.mipmap.new_version_img)
    }

    /**
     * 安装apk
     */
    private fun install(activity: Activity, apkFile: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val contentUri = FileProvider.getUriForFile(
                activity
                , "${BuildConfig.APPLICATION_ID}.fileProvider"
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
}
