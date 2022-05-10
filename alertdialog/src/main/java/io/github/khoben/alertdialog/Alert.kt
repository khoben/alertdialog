package io.github.khoben.alertdialog

import io.github.khoben.alertdialog.builder.AlertBuilder
import io.github.khoben.alertdialog.param.AlertConfig

object Alert {

    private var defaultConfig: AlertConfig? = null

    /**
     * Init default config
     */
    fun defaultConfig(block: AlertConfig.() -> Unit) {
        defaultConfig = AlertConfig().apply(block)
    }

    /**
     * Create alert dialog
     */
    fun create() = AlertBuilder(defaultConfig)
}