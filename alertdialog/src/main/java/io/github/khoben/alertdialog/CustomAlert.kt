package io.github.khoben.alertdialog

import android.os.Bundle
import androidx.annotation.StyleRes
import io.github.khoben.alertdialog.Const.DEFAULT_STYLE
import io.github.khoben.alertdialog.Const.EXTRA_BODY
import io.github.khoben.alertdialog.Const.EXTRA_DIALOG_STYLE
import io.github.khoben.alertdialog.Const.EXTRA_DIALOG_TAG
import io.github.khoben.alertdialog.Const.EXTRA_NEGATIVE_TEXT
import io.github.khoben.alertdialog.Const.EXTRA_NEUTRAL_TEXT
import io.github.khoben.alertdialog.Const.EXTRA_POSITIVE_TEXT
import io.github.khoben.alertdialog.Const.EXTRA_TITLE
import io.github.khoben.alertdialog.type.CustomAlertDialog
import io.github.khoben.alertdialog.type.SimpleAlertDialog

object CustomAlert {
    class Builder(
        private val dialogTag: String? = null,
        private val title: CharSequence,
        private val body: CharSequence,
    ) {
        private var positiveText: String? = null
        private var negativeText: String? = null
        private var neutralText: String? = null

        private var dialogStyle: Int = DEFAULT_STYLE

        /**
         * Set dialog style res
         */
        fun setDialogStyle(@StyleRes dialogStyle: Int) = apply {
            this.dialogStyle = dialogStyle
        }

        /**
         * Enable positive button with [buttonText] text
         */
        fun enablePositive(buttonText: String) = apply {
            positiveText = buttonText
        }

        /**
         * Enable negative button with [buttonText] text
         */
        fun enableNegative(buttonText: String) = apply {
            negativeText = buttonText
        }

        /**
         * Enable neutral button with [buttonText] text
         */
        fun enableNeutral(buttonText: String) = apply {
            neutralText = buttonText
        }

        /**
         * Create instance of [CustomAlertDialog]
         */
        fun build(): CustomAlertDialog {
            return CustomAlertDialog().apply {
                arguments = Bundle().apply {
                    putCharSequence(EXTRA_TITLE, title)
                    putCharSequence(EXTRA_BODY, body)
                    putString(EXTRA_DIALOG_TAG, dialogTag)

                    putString(EXTRA_POSITIVE_TEXT, positiveText)
                    putString(EXTRA_NEGATIVE_TEXT, negativeText)
                    putString(EXTRA_NEUTRAL_TEXT, neutralText)

                    putInt(EXTRA_DIALOG_STYLE, dialogStyle)
                }
            }
        }
    }

    /**
     * Create custom alert dialog with three possible action buttons:
     *  1. Positive ([Builder.enablePositive])
     *  2. Negative ([Builder.enableNegative])
     *  3. Neutral ([Builder.enableNeutral])
     */
    fun custom(
        tag: String? = null,
        title: CharSequence,
        body: CharSequence,
    ) = Builder(tag, title, body)

    /**
     * Create simple alert dialog with single ok button that dismisses it
     */
    fun simple(
        title: CharSequence,
        body: CharSequence,
        @StyleRes dialogStyle: Int = DEFAULT_STYLE
    ): SimpleAlertDialog {
        return SimpleAlertDialog().apply {
            arguments = Bundle().apply {
                putCharSequence(EXTRA_TITLE, title)
                putCharSequence(EXTRA_BODY, body)
                putInt(EXTRA_DIALOG_STYLE, dialogStyle)
            }
        }
    }
}