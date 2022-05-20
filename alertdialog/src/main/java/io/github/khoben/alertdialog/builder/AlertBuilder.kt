package io.github.khoben.alertdialog.builder

import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import io.github.khoben.alertdialog.param.AlertConfig
import io.github.khoben.alertdialog.type.BaseAlertDialog
import io.github.khoben.alertdialog.type.CallbackAlertDialog
import io.github.khoben.alertdialog.type.SimpleAlertDialog

@AlertDslMarker
class AlertBuilder internal constructor(defaultConfig: AlertConfig? = null) {

    private val config = defaultConfig?.copy() ?: AlertConfig()

    /**
     * Alert dialog title
     */
    fun title(block: TitleBuilder.() -> Unit) = apply {
        TitleBuilder(config).apply(block)
        return this
    }

    /**
     * Alert dialog message
     */
    fun message(block: MessageBuilder.() -> Unit) = apply {
        MessageBuilder(config).apply(block)
        return this
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
     * Make the button's layout centered
     */
    fun controlsAlignCenter(controlsAlignCenter: Boolean) = apply {
        config.dialogControlsAlignCenter = controlsAlignCenter
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
     * Set alert dialog cancellable
     */
    fun cancellable(cancellable: Boolean) = apply {
        config.dialogIsCancellable = cancellable
    }

    /**
     * Enable the creation of three possible action buttons with a callback
     * via [AlertEventListener][io.github.khoben.alertdialog.event.AlertEventListener]:
     *
     *  1. [Positive][ButtonCallbackBuilder.positiveButton]
     *  2. [Negative][ButtonCallbackBuilder.negativeButton]
     *  3. [Neutral][ButtonCallbackBuilder.neutralButton]
     *
     *  @param callbackTag Alert dialog callback tag
     */
    fun buttonCallback(callbackTag: String, block: ButtonCallbackBuilder.() -> Unit) = apply {
        ButtonCallbackBuilder(callbackTag, config).apply(block)
        return this
    }

    /**
     * Setup custom view for alert
     *
     * @param layoutRes Custom view layout res id
     */
    fun customView(@LayoutRes layoutRes: Int) = apply {
        config.customLayout = layoutRes
    }

    /**
     * Build & show alert dialog
     */
    fun show(activity: FragmentActivity) = showInternal(activity.supportFragmentManager)

    /**
     * Build & show alert dialog
     */
    fun show(fragment: Fragment) = showInternal(fragment.childFragmentManager)

    private fun showInternal(fragmentManager: FragmentManager): DialogFragment {
        val bundle = bundleOf(
            BaseAlertDialog.EXTRA_TITLE to config.dialogTitle,
            BaseAlertDialog.EXTRA_MESSAGE to config.dialogMessage,
            BaseAlertDialog.EXTRA_CALLBACK_TAG to config.callbackTag,
            BaseAlertDialog.EXTRA_POSITIVE_TEXT to config.positiveText,
            BaseAlertDialog.EXTRA_NEGATIVE_TEXT to config.negativeText,
            BaseAlertDialog.EXTRA_NEUTRAL_TEXT to config.neutralText,
            BaseAlertDialog.EXTRA_HEADER_LAYOUT_RES to config.headerLayoutResource,
            BaseAlertDialog.EXTRA_FOOTER_LAYOUT_RES to config.footerLayoutResource,
            BaseAlertDialog.EXTRA_DIALOG_STYLE to config.dialogStyle,
            BaseAlertDialog.EXTRA_CANCELLABLE to config.dialogIsCancellable,
            BaseAlertDialog.EXTRA_CONTROLS_ALIGN_CENTER to config.dialogControlsAlignCenter,
            BaseAlertDialog.EXTRA_TITLE_ALIGN to config.dialogTitleAlign,
            BaseAlertDialog.EXTRA_MESSAGE_ALIGN to config.dialogMessageAlign,
            BaseAlertDialog.EXTRA_CUSTOM_LAYOUT to config.customLayout
        )

        return if (config.callbackTag != null) {
            CallbackAlertDialog().apply { arguments = bundle }
        } else {
            SimpleAlertDialog().apply { arguments = bundle }
        }.also {
            it.show(fragmentManager, it.fragmentDialogTag)
        }
    }
}