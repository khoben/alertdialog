package io.github.khoben.alertdialog.type

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.View.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AlertDialogLayout
import androidx.appcompat.widget.DialogTitle
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.DialogFragment
import io.github.khoben.alertdialog.R
import io.github.khoben.alertdialog.param.LayoutAlign


abstract class BaseAlertDialog : DialogFragment() {

    /**
     * Alert dialog tag
     */
    abstract val fragmentDialogTag: String

    /**
     * Alert dialog style
     */
    protected var dialogStyle: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.run {
            dialogStyle = resolveStyle(requireContext(), getInt(EXTRA_DIALOG_STYLE))
            isCancelable = getBoolean(EXTRA_CANCELLABLE)
        }
    }

    /**
     * Create dialog view with custom layout
     *
     * Should be called at [DialogFragment.onCreateDialog]
     */
    @SuppressLint("PrivateResource", "InflateParams")
    protected fun createAlertView(): ViewGroup {
        // Getting default material dialog layout
        val themedInflater = LayoutInflater.from(ContextThemeWrapper(context, dialogStyle))
        val layout = themedInflater.inflate(
            com.google.android.material.R.layout.mtrl_alert_dialog,
            null,
            false
        ) as AlertDialogLayout
        // Remove android:id="@+id/customPanel"
        layout.findViewById<View>(com.google.android.material.R.id.customPanel)
            ?.visibility = GONE

        arguments?.run {
            getInt(EXTRA_HEADER_LAYOUT_RES, -1).let {
                if (it != -1) {
                    layout.addView(themedInflater.inflate(it, null, false), 0)
                }
            }
            getInt(EXTRA_FOOTER_LAYOUT_RES, -1).let {
                if (it != -1) {
                    layout.addView(themedInflater.inflate(it, null, false))
                }
            }
            if (getBoolean(EXTRA_CONTROLS_ALIGN_CENTER, false)) {
                alignControlsCenter(layout)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setTitleAlign(layout, getInt(EXTRA_TITLE_ALIGN, LayoutAlign.LEFT))
                setMessageAlign(layout, getInt(EXTRA_MESSAGE_ALIGN, LayoutAlign.LEFT))
            }
        }

        return layout
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setMessageAlign(alertDialogLayout: AlertDialogLayout, @LayoutAlign align: Int) {
        alertDialogLayout.findViewById<TextView>(android.R.id.message)
            ?.textAlignment = alignment(align)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun setTitleAlign(alertDialogLayout: AlertDialogLayout, @LayoutAlign align: Int) {
        alertDialogLayout.findViewById<DialogTitle>(com.google.android.material.R.id.alertTitle)
            ?.let {
                if (align != LayoutAlign.LEFT &&
                    it.layoutParams.width != ViewGroup.LayoutParams.MATCH_PARENT
                ) {
                    /**
                     * Force width to MATCH_PARENT to title alignment work
                     * (Same as MaterialAlertDialog.MaterialComponents.Title.Panel)
                     */
                    it.updateLayoutParams { width = ViewGroup.LayoutParams.MATCH_PARENT }
                }
                it.textAlignment = alignment(align)
            }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun alignment(@LayoutAlign align: Int): Int {
        return when (align) {
            LayoutAlign.LEFT -> TEXT_ALIGNMENT_VIEW_START
            LayoutAlign.RIGHT -> TEXT_ALIGNMENT_VIEW_END
            LayoutAlign.CENTER -> TEXT_ALIGNMENT_CENTER
            else -> TEXT_ALIGNMENT_VIEW_START
        }
    }

    private fun alignControlsCenter(alertDialogLayout: AlertDialogLayout) {
        getButtonLayout(alertDialogLayout)?.let {
            it.gravity = Gravity.CENTER_HORIZONTAL
            // remove spacer between neutral and negative button
            it.findViewById<Space>(com.google.android.material.R.id.spacer)
                ?.visibility = GONE
        }
    }

    private fun getButtonLayout(alertDialogLayout: AlertDialogLayout): LinearLayout? {
        // get first child of button layout
        val firstChild = alertDialogLayout.findViewById<Button>(android.R.id.button1)
        return firstChild?.parent as? LinearLayout
    }

    @SuppressLint("PrivateResource")
    protected fun resolveStyle(context: Context, @StyleRes style: Int): Int {
        if (style != 0) {
            return style
        }
        val a = context.obtainStyledAttributes(intArrayOf(R.attr.alertStyle))
        val resolvedStyle =
            a.getResourceId(0, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
        a.recycle()
        return resolvedStyle
    }

    companion object {
        const val EXTRA_CALLBACK_TAG = "extra_callback_dialog"
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MESSAGE = "extra_message"

        const val EXTRA_CANCELLABLE = "extra_cancellable"

        const val EXTRA_POSITIVE_TEXT = "extra_positive_text"
        const val EXTRA_NEGATIVE_TEXT = "extra_negative_text"
        const val EXTRA_NEUTRAL_TEXT = "extra_neutral_text"

        const val EXTRA_HEADER_LAYOUT_RES = "extra_header_layout_res"
        const val EXTRA_FOOTER_LAYOUT_RES = "extra_footer_layout_res"

        const val EXTRA_CONTROLS_ALIGN_CENTER = "extra_align_buttons_center"

        const val EXTRA_TITLE_ALIGN = "extra_title_align"
        const val EXTRA_MESSAGE_ALIGN = "extra_message_align"

        const val EXTRA_DIALOG_STYLE = "extra_dialog_style"
    }
}