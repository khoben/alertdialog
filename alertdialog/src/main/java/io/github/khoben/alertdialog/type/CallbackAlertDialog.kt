package io.github.khoben.alertdialog.type

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.khoben.alertdialog.event.AlertEvent
import io.github.khoben.alertdialog.event.AlertEventListener

/**
 * Callback alert dialog with configurable positive, negative and neutral button actions
 *
 * Parent should implement AlertEventListener
 */
class CallbackAlertDialog : BaseAlertDialog() {

    override val fragmentDialogTag get() = TAG

    private var listener: AlertEventListener = AlertEventListener.NOOP

    override fun onCreateAlert(
        savedInstanceState: Bundle?,
        alertBuilder: MaterialAlertDialogBuilder
    ): Dialog {
        val callbackTag = arguments?.getString(EXTRA_CALLBACK_TAG, null) ?: fragmentDialogTag

        val title = arguments?.getCharSequence(EXTRA_TITLE, null)
        val message = arguments?.getCharSequence(EXTRA_MESSAGE, null)

        val positiveText = arguments?.getString(EXTRA_POSITIVE_TEXT, null)
        val negativeText = arguments?.getString(EXTRA_NEGATIVE_TEXT, null)
        val neutralText = arguments?.getString(EXTRA_NEUTRAL_TEXT, null)

        return alertBuilder.apply {
            title?.let { setTitle(it) }
            message?.let { setMessage(it) }
            positiveText?.let { text ->
                setPositiveButton(text) { _, _ ->
                    listener.onAlertEvent(AlertEvent.Positive(callbackTag))
                    dismissAllowingStateLoss()
                }
            }
            negativeText?.let { text ->
                setNegativeButton(text) { _, _ ->
                    listener.onAlertEvent(AlertEvent.Negative(callbackTag))
                    dismissAllowingStateLoss()
                }
            }
            neutralText?.let { text ->
                setNeutralButton(text) { _, _ ->
                    listener.onAlertEvent(AlertEvent.Neutral(callbackTag))
                    dismissAllowingStateLoss()
                }
            }
        }.create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = if (parentFragment != null) {
            if (parentFragment is AlertEventListener) {
                parentFragment as AlertEventListener
            } else {
                throw RuntimeException("$parentFragment must implement AlertEventListener")
            }
        } else {
            if (context is AlertEventListener) {
                context
            } else {
                throw RuntimeException("$context must implement AlertEventListener")
            }
        }
    }

    override fun onDetach() {
        listener = AlertEventListener.NOOP
        super.onDetach()
    }

    companion object {
        private val TAG: String = CallbackAlertDialog::class.java.simpleName
    }
}