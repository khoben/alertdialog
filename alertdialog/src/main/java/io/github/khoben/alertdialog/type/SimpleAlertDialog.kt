package io.github.khoben.alertdialog.type

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Simple alert dialog with one positive button that dismisses itself
 */
class SimpleAlertDialog : BaseAlertDialog() {

    override val fragmentDialogTag get() = TAG

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getCharSequence(EXTRA_TITLE, null)
        val message = arguments?.getCharSequence(EXTRA_MESSAGE, null)

        return MaterialAlertDialogBuilder(requireContext(), dialogStyle)
            .setView(createAlertView())
            .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                dismissAllowingStateLoss()
            }
            .apply {
                title?.let { setTitle(it) }
                message?.let { setMessage(it) }
            }
            .create()
    }

    companion object {
        private val TAG: String = SimpleAlertDialog::class.java.simpleName
    }
}