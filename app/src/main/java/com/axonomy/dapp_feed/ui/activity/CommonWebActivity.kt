package com.axonomy.dapp_feed.ui.activity

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.axonomy.dapp_feed.R
import com.axonomy.dapp_feed.constant.Router
import com.axonomy.dapp_feed.constant.RouterParams
import com.axonomy.dapp_feed.utils.DownloadUtil
import com.tencent.smtt.sdk.TbsReaderView
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_common_web.*
import kotlinx.android.synthetic.main.title_layout.*
import me.serenadehl.base.base.BaseActivity
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.log
import java.io.File

/**
 * Web网页
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-05-09 13:17:25
 */
@Route(path = Router.COMMON_WEB_ACTIVITY)
class CommonWebActivity : BaseActivity() {

    @JvmField
    @Autowired(name = RouterParams.URL)
    var mUrl: String? = null

    @JvmField
    @Autowired(name = RouterParams.TITLE)
    var mTitle: String? = null

    private lateinit var mTbsReaderView: TbsReaderView

    private val mC2 by lazy { ContextCompat.getColor(this@CommonWebActivity, R.color.C2) }

    override fun layout() = R.layout.activity_common_web

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        //设置状态栏
        setupStatusBar()
        setStatusBarColor(mC2, true)
        //返回按钮
        iv_back.setOnClickListener { finish() }
        //设置标题栏
        tv_title.text = mTitle
        cl_title.setBackgroundColor(mC2)

        if (mUrl?.endsWith(".pdf", true) == true) {
            initPDFReader()
            return
        }

        //TODO 配置WebView
        wv_web.webViewClient = WebViewClient()
        wv_web.webChromeClient = WebChromeClient()

        wv_web.loadUrl(mUrl)
    }

    private fun initPDFReader() {
        mTbsReaderView = TbsReaderView(this@CommonWebActivity, null)
        val matchConstraint = dimen(R.dimen.match_constraint)
        val params = ConstraintLayout.LayoutParams(matchConstraint, matchConstraint).apply {
            startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
            bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            topToBottom = R.id.cl_title
        }
        mTbsReaderView.layoutParams = params
        mRootView.addView(mTbsReaderView)
        mTbsReaderView.preOpen("pdf", false)


        val dir = filesDir.absolutePath
        val name = mUrl?.substringAfterLast("/")

        val pdf = File(dir, name)
        if (pdf.exists()) {
            openPDF(pdf)
        } else {
            DownloadUtil.get()
                .download(mUrl, dir, name, object : DownloadUtil.OnDownloadListener {
                    override fun onDownloadSuccess() {
                        "onDownloadSuccess--------->".log()
                        runOnUiThread { openPDF(pdf) }
                    }

                    override fun onDownloading(progress: Int) {
                        "onDownloading---------> $progress".log()
                    }

                    override fun onDownloadFailed() {
                        "onDownloadFailed--------->".log()
                    }

                })
        }
    }

    private fun openPDF(file: File) {
        val bundle = Bundle()
        bundle.putString("filePath", file.absolutePath)//存放pdf 的文件
        bundle.putString("tempPath", file.parent)//存放临时文件的目录。运行后，会在path2的目录下生成.tbs...的文件
        mTbsReaderView.openFile(bundle)
    }

    override fun onDestroy() {
        try {
            mTbsReaderView.onStop()
            (wv_web.parent as ViewGroup).removeView(wv_web)
            wv_web.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

}