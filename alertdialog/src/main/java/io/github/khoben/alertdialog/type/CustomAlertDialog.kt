package io.github.khoben.alertdialog.type

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.khoben.alertdialog.Const
import io.github.khoben.alertdialog.CustomDialogEventListener
import io.github.khoben.alertdialog.event.DialogEvent

/**
 * Custom alert dialog with configurable positive, negative and neutral button actions
 *
 * Parent should implement CustomDialogEventListener
 */
class CustomAlertDialog : BaseAlertDialog() {

    private var listener: CustomDialogEventListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialogTag = arguments?.getString(Const.EXTRA_DIALOG_TAG, null)

        val title = arguments?.getCharSequence(Const.EXTRA_TITLE, null)
        val message = arguments?.getCharSequence(Const.EXTRA_MESSAGE, null)

        val positiveText = arguments?.getString(Const.EXTRA_POSITIVE_TEXT, null)
        val negativeText = arguments?.getString(Const.EXTRA_NEGATIVE_TEXT, null)
        val neutralText = arguments?.getString(Const.EXTRA_NEUTRAL_TEXT, null)

        val dialogIsCancellable = arguments?.getBoolean(Const.EXTRA_CANCELLABLE, true)!!
        isCancelable = dialogIsCancellable

        val dialogStyle =
            arguments?.getInt(
                Const.EXTRA_DIALOG_STYLE,
                Const.DEFAULT_STYLE
            )!!

        return MaterialAlertDialogBuilder(requireContext(), dialogStyle)
            .setView(dialogView)
            .apply {
                if (title != null) setTitle(title)
                if (message != null) setMessage(message)
            }
            .setCancelable(dialogIsCancellable)
            .also { builder ->
                positiveText?.let { text ->
                    builder.setPositiveButton(text) { _, _ ->
                        dismissAllowingStateLoss()
                        dialogTag?.let {
                            listener?.onPositiveClickEvent(
                                DialogEvent.PositiveButtonEvent(
                                    it
                                )
                            )
                        }
                    }
                }
                negativeText?.let { text ->
                    builder.setNegativeButton(text) { _, _ ->
                        dismissAllowingStateLoss()
                        dialogTag?.let {
                            listener?.onNegativeClickEvent(
                                DialogEvent.NegativeButtonEvent(
                                    it
                                )
                            )
                        }
                    }
                }
                neutralText?.let { text ->
                    builder.setNeutralButton(text) { _, _ ->
                        dismissAllowingStateLoss()
                        dialogTag?.let {
                            listener?.onNeutralClickEvent(
                                DialogEvent.NeutralButtonEvent(
                                    it
                                )
                            )
                        }
                    }
                }
            }
            .create()
    }

    override val DIALOG_TAG: String
        get() = TAG

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