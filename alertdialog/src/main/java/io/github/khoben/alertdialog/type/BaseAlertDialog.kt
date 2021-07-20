package io.github.khoben.alertdialog.type

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import io.github.khoben.alertdialog.Const

abstract class BaseAlertDialog : DialogFragment() {
    abstract val DIALOG_TAG: String

    /**
     * Alert dialog layout with custom header
     */
    protected val dialogView: ViewGroup by lazy { initDialogView() }

    /**
     * Set cancelable on outside click or not
     *
     * @param isCancellable Dialog cancelable or not
     */
    fun cancelable(isCancellable: Boolean) = apply { isCancelable = isCancellable }

    /**
     * Show alert dialog with [activity]
     *
     * @param activity Parent activity
     */
    fun show(activity: AppCompatActivity) = show(activity.supportFragmentManager, DIALOG_TAG)

    /**
     * Show alert dialog with [fragment]
     *
     * @param fragment Parent fragment
     */
    fun show(fragment: Fragment) = show(fragment.childFragmentManager, DIALOG_TAG)

    /**
     * Init dialog view with custom header
     *
     * Should be called at [DialogFragment.onCreateDialog]
     */
    private fun initDialogView(): ViewGroup {
        // Getting default material dialog layout
        val layout = LayoutInflater.from(context).inflate(
            androidx.appcompat.R.layout.abc_alert_dialog_material,
            null
        ) as AlertDialogLayout
        // Remove android:id="@+id/customPanel"
        layout.findViewById<View>(androidx.appcompat.R.id.customPanel).isVisible = false
        val headerLayoutRes = arguments?.getInt(Const.EXTRA_HEADER_LAYOUT_RES, -1)
        if (headerLayoutRes != null && headerLayoutRes != -1) {
            layout.addView(
                layoutInflater.inflate(headerLayoutRes, null, false), 0
            )
        }
        return layout
    }
}