package io.github.khoben.alertdialog.param

import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes

data class AlertConfig(
    @LayoutRes
    var headerLayoutResource: Int = -1,
    @LayoutRes
    var footerLayoutResource: Int = -1,
    var dialogTitle: CharSequence? = null,
    var dialogMessage: CharSequence? = null,
    @StyleRes
    var dialogStyle: Int = 0,
    @LayoutAlign
    var dialogTitleAlign: Int = LayoutAlign.LEFT,
    @LayoutAlign
    var dialogMessageAlign: Int = LayoutAlign.LEFT,
    var dialogControlsAlignCenter: Boolean = false,
    var dialogIsCancellable: Boolean = true,
    var callbackTag: String? = null,
    var positiveText: String? = null,
    var negativeText: String? = null,
    var neutralText: String? = null
)