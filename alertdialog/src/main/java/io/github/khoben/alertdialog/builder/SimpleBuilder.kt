package io.github.khoben.alertdialog.builder

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import io.github.khoben.alertdialog.DialogConfig
import io.github.khoben.alertdialog.Const
import io.github.khoben.alertdialog.type.SimpleAlertDialog

class SimpleBuilder internal constructor(defaultConfig: DialogConfig? = null) : BaseBuilder(defaultConfig) {
    override fun showInternal(fragmentManager: FragmentManager): SimpleAlertDialog {
        return SimpleAlertDialog().apply {
            arguments = Bundle().apply {
                putCharSequence(Const.EXTRA_TITLE, config.dialogTitle)
                putCharSequence(Const.EXTRA_MESSAGE, config.dialogMessage)
                putInt(Const.EXTRA_HEADER_LAYOUT_RES, config.headerLayoutResource)
                putInt(Const.EXTRA_FOOTER_LAYOUT_RES, config.footerLayoutResource)
                putInt(Const.EXTRA_DIALOG_STYLE, config.dialogStyle)
                putBoolean(Const.EXTRA_CANCELLABLE, config.dialogIsCancellable)
                putBoolean(Const.EXTRA_BUTTONS_CENTERED, config.dialogButtonsIsCentered)

                putInt(Const.EXTRA_TITLE_ALIGN, config.dialogTitleAlign)
                putInt(Const.EXTRA_MESSAGE_ALIGN, config.dialogMessageAlign)
            }
        }.also {
            it.show(fragmentManager, it.DIALOG_TAG)
        }
    }
}