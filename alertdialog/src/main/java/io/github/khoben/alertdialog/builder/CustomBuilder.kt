package io.github.khoben.alertdialog.builder

import android.os.Bundle
import androidx.fragment.app.FragmentManager
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

    internal fun copyFrom(builder: BaseBuilder) = apply {
        headerLayoutResource = builder.headerLayoutResource
        footerLayoutResource = builder.footerLayoutResource
        dialogTitle = builder.dialogTitle
        dialogMessage = builder.dialogMessage
        dialogStyle = builder.dialogStyle
        dialogTitleAlign = builder.dialogTitleAlign
        dialogMessageAlign = builder.dialogMessageAlign
        dialogButtonsIsCentered = builder.dialogButtonsIsCentered
        dialogIsCancellable = builder.dialogIsCancellable
    }

    override fun showInternal(fragmentManager: FragmentManager): CustomAlertDialog {
        return CustomAlertDialog().apply {
            arguments = Bundle().apply {
                putCharSequence(Const.EXTRA_TITLE, dialogTitle)
                putCharSequence(Const.EXTRA_MESSAGE, dialogMessage)
                putString(Const.EXTRA_DIALOG_TAG, dialogTag)

                putString(Const.EXTRA_POSITIVE_TEXT, positiveText)
                putString(Const.EXTRA_NEGATIVE_TEXT, negativeText)
                putString(Const.EXTRA_NEUTRAL_TEXT, neutralText)

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