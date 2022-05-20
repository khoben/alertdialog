package io.github.khoben.alertdialog.builder

import io.github.khoben.alertdialog.param.AlertConfig

@AlertDslMarker
class ButtonCallbackBuilder internal constructor(
    callbackTag: String,
    private val config: AlertConfig
) {
    init {
        config.callbackTag = callbackTag
    }

    /**
     * Enable positive button with [positiveBtnText] text
     */
    fun positiveButton(positiveBtnText: String) = apply {
        config.positiveText = positiveBtnText
    }

    /**
     * Enable negative button with [negativeBtnText] text
     */
    fun negativeButton(negativeBtnText: String) = apply {
        config.negativeText = negativeBtnText
    }

    /**
     * Enable neutral button with [neutralBtnText] text
     */
    fun neutralButton(neutralBtnText: String) = apply {
        config.neutralText = neutralBtnText
    }
}