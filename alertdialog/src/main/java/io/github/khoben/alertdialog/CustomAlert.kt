package io.github.khoben.alertdialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object CustomAlert {
    @StyleRes
    private val DEFAULT_STYLE = R.style.MaterialAlertDialog_MaterialComponents

    private const val EXTRA_TOKEN = "extra_token"
    private const val EXTRA_TITLE = "extra_title"
    private const val EXTRA_BODY = "extra_body"

    private const val EXTRA_POSITIVE_TEXT = "extra_positive_text"
    private const val EXTRA_NEGATIVE_TEXT = "extra_negative_text"
    private const val EXTRA_NEUTRAL_TEXT = "extra_neutral_text"

    private const val EXTRA_DIALOG_STYLE = "extra_dialog_style"

    class Builder {
        private var positiveText: String? = null
        private var negativeText: String? = null
        private var neutralText: String? = null

        private var dialogStyle: Int = DEFAULT_STYLE

        fun setDialogStyle(@StyleRes dialogStyle: Int) = apply {
            this.dialogStyle = dialogStyle
        }

        fun setPositive(text: String) = apply {
            positiveText = text
        }

        fun setNegative(text: String) = apply {
            negativeText = text
        }

        fun setNeutral(text: String) = apply {
            neutralText = text
        }

        fun dialogOkBtn(
            title: CharSequence,
            body: CharSequence,
            @StyleRes dialogStyle: Int = DEFAULT_STYLE
        ): DialogOkBtn {
            return DialogOkBtn().apply {
                arguments = Bundle().apply {
                    putCharSequence(EXTRA_TITLE, title)
                    putCharSequence(EXTRA_BODY, body)
                    putInt(EXTRA_DIALOG_STYLE, dialogStyle)
                }
            }
        }

        fun build(
            title: CharSequence,
            body: CharSequence,
            token: String? = null,
        ): CustomAlertDialog {
            return CustomAlertDialog().apply {
                arguments = Bundle().apply {
                    putCharSequence(EXTRA_TITLE, title)
                    putCharSequence(EXTRA_BODY, body)
                    putString(EXTRA_TOKEN, token)

                    putString(EXTRA_POSITIVE_TEXT, positiveText)
                    putString(EXTRA_NEGATIVE_TEXT, negativeText)
                    putString(EXTRA_NEUTRAL_TEXT, neutralText)

                    putInt(EXTRA_DIALOG_STYLE, dialogStyle)
                }
            }
        }
    }

    class CustomAlertDialog : DialogFragment() {
        private var listener: CustomDialogEventListener? = null
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val title = arguments?.getCharSequence(EXTRA_TITLE, "")
            val body = arguments?.getCharSequence(EXTRA_BODY, "")
            val token = arguments?.getString(EXTRA_TOKEN, null)

            val positiveText = arguments?.getString(EXTRA_POSITIVE_TEXT, null)
            val negativeText = arguments?.getString(EXTRA_NEGATIVE_TEXT, null)
            val neutralText = arguments?.getString(EXTRA_NEUTRAL_TEXT, null)

            val dialogStyle =
                arguments?.getInt(
                    EXTRA_DIALOG_STYLE,
                    DEFAULT_STYLE
                )!!

            return MaterialAlertDialogBuilder(requireContext(), dialogStyle)
                .setTitle(title)
                .setMessage(body)
                .setCancelable(false)
                .also { builder ->
                    positiveText?.let { text ->
                        builder.setPositiveButton(text) { _, _ ->
                            dismissAllowingStateLoss()
                            token?.let { listener?.dialogOnPositive(it) }
                        }
                    }
                    negativeText?.let { text ->
                        builder.setNegativeButton(text) { _, _ ->
                            dismissAllowingStateLoss()
                            token?.let { listener?.dialogOnNegative(it) }
                        }
                    }
                    neutralText?.let { text ->
                        builder.setNeutralButton(text) { _, _ ->
                            dismissAllowingStateLoss()
                            token?.let { listener?.dialogOnNeutral(it) }
                        }
                    }
                }
                .create()
        }

        override fun onAttach(context: Context) {
            super.onAttach(context)
            listener = if (parentFragment != null) {
                if (parentFragment is CustomDialogEventListener) {
                    parentFragment as CustomDialogEventListener
                } else {
                    throw RuntimeException("$parentFragment must implement CustomDialogEventListener")
                }
            } else {
                if (context is CustomDialogEventListener) {
                    context
                } else {
                    throw RuntimeException("$context must implement CustomDialogEventListener")
                }
            }
        }

        override fun onDetach() {
            listener = null
            super.onDetach()
        }

        companion object {
            val TAG: String = CustomAlertDialog::class.java.simpleName
        }
    }

    class DialogOkBtn : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val title = arguments?.getCharSequence(EXTRA_TITLE, "")
            val body = arguments?.getCharSequence(EXTRA_BODY, "")
            val dialogStyle =
                arguments?.getInt(EXTRA_DIALOG_STYLE, DEFAULT_STYLE)!!

            return MaterialAlertDialogBuilder(requireContext(), dialogStyle)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(body)
                .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                    dismissAllowingStateLoss()
                }.create()
        }
        
        companion object {
            val TAG: String = DialogOkBtn::class.java.simpleName
        }
    }
}