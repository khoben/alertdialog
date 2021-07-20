package io.github.khoben.alertdialog.type

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import io.github.khoben.alertdialog.Const

/**
 * Simple alert dialog with single positive button that dismisses it
 */
class SimpleAlertDialog : BaseAlertDialog() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = arguments?.getCharSequence(Const.EXTRA_TITLE, "")
        val body = arguments?.getCharSequence(Const.EXTRA_BODY, "")
        val dialogStyle =
            arguments?.getInt(Const.EXTRA_DIALOG_STYLE, Const.DEFAULT_STYLE)!!

        return MaterialAlertDialogBuilder(requireContext(), dialogStyle)
            .setView(dialogView)
            .setTitle(title)
            .setCancelable(false)
            .setMessage(body)
            .setPositiveButton(getString(android.R.string.ok)) { _, _ ->
                dismissAllowingStateLoss()
            }.create()
    }

    override val DIALOG_TAG: String
        get() = TAG

    companion object {
        val TAG: String = SimpleAlertDialog::class.java.simpleName
    }
}