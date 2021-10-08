package io.github.khoben.alertdialog

import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes

data class DialogConfig(
    @LayoutRes
    var headerLayoutResource: Int = -1,
    @LayoutRes
    var footerLayoutResource: Int = -1,
    var dialogTitle: CharSequence? = null,
    var dialogMessage: CharSequence? = null,
    @StyleRes
    var dialogStyle: Int = Const.DEFAULT_STYLE,
    @LayoutAlign
    var dialogTitleAlign: Int = Const.DEFAULT_ALIGN,
    @LayoutAlign
    var dialogMessageAlign: Int = Const.DEFAULT_ALIGN,
    var dialogButtonsIsCentered: Boolean = false,
    var dialogIsCancellable: Boolean = true,
)