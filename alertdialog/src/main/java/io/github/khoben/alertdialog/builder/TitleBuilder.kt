package io.github.khoben.alertdialog.builder

import androidx.annotation.RequiresApi
import io.github.khoben.alertdialog.param.AlertConfig
import io.github.khoben.alertdialog.param.LayoutAlign

@AlertDslMarker
class TitleBuilder internal constructor(private val config: AlertConfig) {

    /**
     * Title text
     */
    fun text(text: CharSequence) = apply {
        config.dialogTitle = text
    }

    /**
     * Title alignment
     *
     * If [materialAlertDialogTitleTextStyle]'s width wasn't set to match_parent,
     * it will be forced to match_parent at runtime.
     */
    @RequiresApi(android.os.Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun alignment(@LayoutAlign alignment: Int) = apply {
        config.dialogTitleAlign = alignment
    }
}