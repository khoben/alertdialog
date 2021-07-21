package io.github.khoben.alertdialog.builder

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import io.github.khoben.alertdialog.Const
import io.github.khoben.alertdialog.type.SimpleAlertDialog

class SimpleBuilder internal constructor() : BaseBuilder() {
    override fun showInternal(fragmentManager: FragmentManager): SimpleAlertDialog {
        return SimpleAlertDialog().apply {
            arguments = Bundle().apply {
                putCharSequence(Const.EXTRA_TITLE, dialogTitle)
                putCharSequence(Const.EXTRA_MESSAGE, dialogMessage)
                putInt(Const.EXTRA_HEADER_LAYOUT_RES, headerLayoutResource)
                putInt(Const.EXTRA_FOOTER_LAYOUT_RES, footerLayoutResource)
                putInt(Const.EXTRA_DIALOG_STYLE, dialogStyle)
                putBoolean(Const.EXTRA_CANCELLABLE, dialogIsCancellable)
                putBoolean(Const.EXTRA_BUTTONS_CENTERED, dialogButtonsIsCentered)

                putInt(Const.EXTRA_TITLE_ALIGN, dialogTitleAlign)
                putInt(Const.EXTRA_MESSAGE_ALIGN, dialogMessageAlign)
            }
        }.also {
            it.show(fragmentManager, it.DIALOG_TAG)
        }
    }
}