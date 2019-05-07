package com.dong.dapp.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.LinearLayout
import com.dong.dapp.R
import com.dong.dapp.utils.PopupWindowUtils.ButtonConfig.Companion.BLUE_NO_SOLID
import com.dong.dapp.utils.PopupWindowUtils.ButtonConfig.Companion.BLUE_SOLID
import com.dong.dapp.utils.PopupWindowUtils.ButtonConfig.Companion.ERROR
import kotlinx.android.synthetic.main.popup_common_bottom.view.*
import me.serenadehl.base.extensions.dimen
import me.serenadehl.base.extensions.gone
import me.serenadehl.base.extensions.visible
import razerdp.basepopup.BasePopupWindow
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig
import razerdp.widget.QuickPopup

/**
 * 作者：Serenade
 * 邮箱：SerenadeHL@163.com
 * 创建时间：2019-04-19 17:01:15
 */
object PopupWindowUtils {
    /**
     * 通用底部弹窗
     */
    fun bottomPopupWindow(
        context: Context,
        title: String,
        content: String?,
        dismiss: () -> Unit,
        vararg buttonConfigs: ButtonConfig
    ) {
        val showAnimation = AnimationSet(true).apply {
            duration = 200
            addAnimation(
                TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0F, Animation.RELATIVE_TO_PARENT, 0F,
                    Animation.RELATIVE_TO_PARENT, 1F, Animation.RELATIVE_TO_PARENT, 0F
                )
            )
            addAnimation(AlphaAnimation(0F, 1F))
        }
        val dismissAnimation = AnimationSet(true).apply {
            duration = 200
            addAnimation(
                TranslateAnimation(
                    Animation.RELATIVE_TO_PARENT, 0F, Animation.RELATIVE_TO_PARENT, 0F,
                    Animation.RELATIVE_TO_PARENT, 0F, Animation.RELATIVE_TO_PARENT, 1F
                )
            )
            addAnimation(AlphaAnimation(1F, 0F))
        }

        val config = QuickPopupConfig()
            .gravity(Gravity.BOTTOM or Gravity.CENTER_VERTICAL)
            .withShowAnimation(showAnimation)
            .withDismissAnimation(dismissAnimation)
            .dismissListener(object : BasePopupWindow.OnDismissListener() {
                override fun onDismiss() {
                    dismiss()
                }
            })

        val quickPopup = QuickPopupBuilder
            .with(context)
            .contentView(R.layout.popup_common_bottom)
            .config(config)
            .show()

        quickPopup.contentView.tv_title.text = title

        if (TextUtils.isEmpty(content)) {
            quickPopup.contentView.tv_content.gone()
        } else {
            quickPopup.contentView.tv_content.visible()
            quickPopup.contentView.tv_content.text = content
        }

        buttonConfigs.withIndex().forEach {
            generateButton(quickPopup, context, it.value, it.index > 0)
        }
    }

    /**
     * 生成Button
     */
    private fun generateButton(quickPopup: QuickPopup, context: Context, config: ButtonConfig, marginTop: Boolean) {
        quickPopup.contentView.ll_btn_container.addView(Button(context).apply {
            text = config.text
            setTextColor(
                ContextCompat.getColor(
                    context, when (config.type) {
                        BLUE_SOLID -> R.color.C2
                        BLUE_NO_SOLID -> R.color.C1
                        else -> ERROR
                    }
                )
            )
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, context.dimen(R.dimen.L2))
            setBackgroundResource(
                when (config.type) {
                    BLUE_SOLID -> R.drawable.round_rect_solid_dark_blue_bg
                    BLUE_NO_SOLID -> R.drawable.round_rect_no_solid_dark_blue_bg
                    else -> ERROR
                }
            )
            setOnClickListener {
                config.listener(it)
                if (config.dismiss) quickPopup.dismiss()
            }
            if (marginTop) {
                (layoutParams as LinearLayout.LayoutParams).topMargin = context.dimen(R.dimen.L7)
            }
        })
    }

    /**
     * @param text Button文本
     * @param type BLUE_SOLID为纯蓝色背景 BLUE_NO_SOLID为蓝色框背景
     * @param dismiss 是否让PopupWindow消失
     * @param listener 点击事件
     */
    data class ButtonConfig(
        val text: String,
        val type: Int,
        val dismiss: Boolean,
        val listener: (view: View) -> Unit
    ) {
        companion object {
            const val BLUE_SOLID = 0
            const val BLUE_NO_SOLID = 1
            const val ERROR = -1
        }
    }
}