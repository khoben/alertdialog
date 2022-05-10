package io.github.khoben.alertdialog.builder

import androidx.annotation.RequiresApi
import io.github.khoben.alertdialog.param.AlertConfig
import io.github.khoben.alertdialog.param.LayoutAlign

@AlertDslMarker
class MessageBuilder internal constructor(private val config: AlertConfig) {

    /**
     * Title text
     */
    fun text(text: CharSequence) = apply {
        config.dialogMessage = text
    }

    /**
     * Message alignment
     */
    @RequiresApi(android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun alignment(@LayoutAlign alignment: Int) = apply {
        config.dialogMessageAlign = alignment
    }

}