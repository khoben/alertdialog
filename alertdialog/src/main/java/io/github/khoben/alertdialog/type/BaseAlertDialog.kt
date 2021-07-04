package io.github.khoben.alertdialog.type

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

abstract class BaseAlertDialog: DialogFragment() {
    abstract val DIALOG_TAG: String

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
}