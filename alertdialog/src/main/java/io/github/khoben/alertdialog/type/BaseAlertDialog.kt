package io.github.khoben.alertdialog.type

import android.os.Build
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import io.github.khoben.alertdialog.Const
import io.github.khoben.alertdialog.LayoutAlign

abstract class BaseAlertDialog : DialogFragment() {
    abstract val DIALOG_TAG: String

    /**
     * Alert dialog layout with custom header
     */
    protected val dialogView: ViewGroup by lazy { initDialogView() }

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
        val footerLayoutRes = arguments?.getInt(Const.EXTRA_FOOTER_LAYOUT_RES, -1)
        if (footerLayoutRes != null && footerLayoutRes != -1) {
            layout.addView(
                layoutInflater.inflate(footerLayoutRes, null, false)
            )
        }

        val isButtonsCentered = arguments?.getBoolean(Const.EXTRA_BUTTONS_CENTERED, false)
        if (isButtonsCentered == true) {
            makeButtonsCentered(layout)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val titleAlign =
                arguments?.getInt(Const.EXTRA_TITLE_ALIGN, LayoutAlign.LEFT) ?: LayoutAlign.LEFT
            val messageAlign =
                arguments?.getInt(Const.EXTRA_MESSAGE_ALIGN, LayoutAlign.LEFT) ?: LayoutAlign.LEFT
            setTitleAlign(layout, titleAlign)
            setMessageAlign(layout, messageAlign)
        }

        return layout
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setMessageAlign(view: ViewGroup, @LayoutAlign align: Int) {
        view.findViewById<TextView>(android.R.id.message).let {
            it.textAlignment = getAlignment(align)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setTitleAlign(view: ViewGroup, @LayoutAlign align: Int) {
        view.findViewById<TextView>(androidx.appcompat.R.id.alertTitle).let {
            it.textAlignment = getAlignment(align)
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun getAlignment(@LayoutAlign align: Int): Int {
        return when (align) {
            LayoutAlign.LEFT -> TEXT_ALIGNMENT_VIEW_START
            LayoutAlign.RIGHT -> TEXT_ALIGNMENT_VIEW_END
            LayoutAlign.CENTER -> TEXT_ALIGNMENT_CENTER
            else -> TEXT_ALIGNMENT_VIEW_START
        }
    }

    private fun makeButtonsCentered(view: ViewGroup) {
        val positiveButton = getButton(view, AlertDialog.BUTTON_POSITIVE)
        val buttonBarLayout = positiveButton?.parent as? LinearLayout
        buttonBarLayout?.gravity = Gravity.CENTER_HORIZONTAL
        val spacer = buttonBarLayout?.findViewById<Space>(androidx.appcompat.R.id.spacer)
        spacer?.isVisible = false
    }

    private fun getButton(view: ViewGroup, which: Int): Button? {
        return when (which) {
            AlertDialog.BUTTON_POSITIVE -> view.findViewById(android.R.id.button1)
            AlertDialog.BUTTON_NEGATIVE -> view.findViewById(android.R.id.button2)
            AlertDialog.BUTTON_NEUTRAL -> view.findViewById(android.R.id.button3)
            else -> null
        }
    }
}