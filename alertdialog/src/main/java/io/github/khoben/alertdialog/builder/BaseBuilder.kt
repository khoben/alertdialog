package io.github.khoben.alertdialog.builder

import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.github.khoben.alertdialog.DialogConfig
import io.github.khoben.alertdialog.LayoutAlign

abstract class BaseBuilder internal constructor(defaultConfig: DialogConfig? = null) {

    protected val config = defaultConfig?.copy() ?: DialogConfig()

    /**
     * Set alert dialog title
     */
    fun title(title: CharSequence) = apply {
        config.dialogTitle = title
    }

    /**
     * Set alert dialog message
     */
    fun message(message: CharSequence) = apply {
        config.dialogMessage = message
    }

    /**
     * Set header layout for alertdialog, it will be placed above title
     */
    fun header(@LayoutRes headerLayoutRes: Int) = apply {
        config.headerLayoutResource = headerLayoutRes
    }

    /**
     * Set footer layout for alertdialog, it will be placed below button row
     */
    fun footer(@LayoutRes footerLayoutRes: Int) = apply {
        config.footerLayoutResource = footerLayoutRes
    }

    /**
     * Make button's layout centered
     */
    fun buttonsCentered() = apply {
        config.dialogButtonsIsCentered = true
    }

    /**
     * Set title alignment
     */
    @RequiresApi(android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun titleAlignment(@LayoutAlign alignment: Int) = apply {
        config.dialogTitleAlign = alignment
    }

    /**
     * Set message alignment
     */
    @RequiresApi(android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun messageAlignment(@LayoutAlign alignment: Int) = apply {
        config.dialogMessageAlign = alignment
    }

    /**
     * Set dialog style, [dialogStyle] must be inherited from
     * `ThemeOverlay.MaterialComponents.MaterialAlertDialog`:
     *
     * ```xml
     * <style name="MyDialogStyle" parent="ThemeOverlay.MaterialComponents.MaterialAlertDialog"/>
     * ```
     */
    fun style(@StyleRes dialogStyle: Int) = apply {
        config.dialogStyle = dialogStyle
    }

    /**
     * Set alert dialog cancellable or not
     */
    fun cancellable(isCancellable: Boolean) = apply {
        config.dialogIsCancellable = isCancellable
    }

    /**
     * Enable creation of three possible action buttons with callback
     * via [io.github.khoben.alertdialog.CustomDialogEventListener]:
     *  1. Positive ([CustomBuilder.positiveButton])
     *  2. Negative ([CustomBuilder.negativeButton])
     *  3. Neutral ([CustomBuilder.neutralButton])
     *
     *  @param dialogTag Alert dialog callback tag
     */
    fun withButtonCallback(dialogTag: String? = null): CustomBuilder {
        return CustomBuilder(dialogTag).copyFrom(config)
    }

    /**
     * Build & show alert dialog
     */
    fun show(activity: AppCompatActivity) = showInternal(activity.supportFragmentManager)

    /**
     * Build & show alert dialog
     */
    fun show(fragment: Fragment) = showInternal(fragment.childFragmentManager)

    protected abstract fun showInternal(fragmentManager: FragmentManager): DialogFragment
}