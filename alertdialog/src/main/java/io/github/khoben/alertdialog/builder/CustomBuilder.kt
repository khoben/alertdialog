package io.github.khoben.alertdialog.builder

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import io.github.khoben.alertdialog.DialogConfig
import io.github.khoben.alertdialog.Const
import io.github.khoben.alertdialog.CustomDialogEventListener
import io.github.khoben.alertdialog.type.CustomAlertDialog

class CustomBuilder internal constructor(
    private val dialogTag: String? = null
) : BaseBuilder() {
    private var positiveText: String? = null
    private var negativeText: String? = null
    private var neutralText: String? = null

    /**
     * Enable positive button with [positiveBtnText] text
     *
     * OnClick callback: [CustomDialogEventListener.onPositiveClickEvent]
     * with [dialogTag] as event parameter
     */
    fun positiveButton(positiveBtnText: String) = apply {
        positiveText = positiveBtnText
    }

    /**
     * Enable negative button with [negativeBtnText] text
     *
     * OnClick callback: [CustomDialogEventListener.onNegativeClickEvent]
     * with [dialogTag] as event parameter
     */
    fun negativeButton(negativeBtnText: String) = apply {
        negativeText = negativeBtnText
    }

    /**
     * Enable neutral button with [neutralBtnText] text
     *
     * OnClick callback: [CustomDialogEventListener.onNeutralClickEvent]
     * with [dialogTag] as event parameter
     */
    fun neutralButton(neutralBtnText: String) = apply {
        neutralText = neutralBtnText
    }

    internal fun copyFrom(_config: DialogConfig) = apply {
        config.headerLayoutResource = _config.headerLayoutResource
        config.footerLayoutResource = _config.footerLayoutResource
        config.dialogTitle = _config.dialogTitle
        config.dialogMessage = _config.dialogMessage
        config.dialogStyle = _config.dialogStyle
        config.dialogTitleAlign = _config.dialogTitleAlign
        config.dialogMessageAlign = _config.dialogMessageAlign
        config.dialogButtonsIsCentered = _config.dialogButtonsIsCentered
        config.dialogIsCancellable = _config.dialogIsCancellable
    }

    override fun showInternal(fragmentManager: FragmentManager): CustomAlertDialog {
        return CustomAlertDialog().apply {
            arguments = Bundle().apply {
                putCharSequence(Const.EXTRA_TITLE, config.dialogTitle)
                putCharSequence(Const.EXTRA_MESSAGE, config.dialogMessage)
                putString(Const.EXTRA_DIALOG_TAG, dialogTag)

                putString(Const.EXTRA_POSITIVE_TEXT, positiveText)
                putString(Const.EXTRA_NEGATIVE_TEXT, negativeText)
                putString(Const.EXTRA_NEUTRAL_TEXT, neutralText)

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