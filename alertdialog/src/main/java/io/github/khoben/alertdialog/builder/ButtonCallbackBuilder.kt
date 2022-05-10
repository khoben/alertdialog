package io.github.khoben.alertdialog.builder

import io.github.khoben.alertdialog.event.AlertEventListener
import io.github.khoben.alertdialog.param.AlertConfig

@AlertDslMarker
class ButtonCallbackBuilder internal constructor(
    private val config: AlertConfig,
    private val callbackTag: String
) {
    init {
        config.callbackTag = callbackTag
    }

    /**
     * Enable positive button with [positiveBtnText] text
     *
     * OnClick callback: [AlertEventListener.onAlertPositiveClick]
     * with [callbackTag] as event parameter
     */
    fun positiveButton(positiveBtnText: String) = apply {
        config.positiveText = positiveBtnText
    }

    /**
     * Enable negative button with [negativeBtnText] text
     *
     * OnClick callback: [AlertEventListener.onAlertNegativeClick]
     * with [callbackTag] as event parameter
     */
    fun negativeButton(negativeBtnText: String) = apply {
        config.negativeText = negativeBtnText
    }

    /**
     * Enable neutral button with [neutralBtnText] text
     *
     * OnClick callback: [AlertEventListener.onAlertNeutralClick]
     * with [callbackTag] as event parameter
     */
    fun neutralButton(neutralBtnText: String) = apply {
        config.neutralText = neutralBtnText
    }
}