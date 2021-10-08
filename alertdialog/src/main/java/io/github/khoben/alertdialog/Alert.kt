package io.github.khoben.alertdialog

import io.github.khoben.alertdialog.builder.SimpleBuilder

object Alert {

    private var defaultConfig: DialogConfig? = null

    fun defaultConfig(block: DialogConfig.() -> Unit) {
        defaultConfig = DialogConfig().apply(block)
    }

    /**
     * Create alert dialog
     */
    fun create() = SimpleBuilder(defaultConfig)
}