package io.github.khoben.alertdialog

import androidx.annotation.IntDef
import androidx.annotation.StyleRes

internal object Const {
    @StyleRes
    val DEFAULT_STYLE = R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog

    @LayoutAlign
    val DEFAULT_ALIGN = LayoutAlign.LEFT

    const val EXTRA_DIALOG_TAG = "extra_tag_dialog"
    const val EXTRA_TITLE = "extra_title"
    const val EXTRA_MESSAGE = "extra_message"

    const val EXTRA_CANCELLABLE = "extra_cancellable"

    const val EXTRA_POSITIVE_TEXT = "extra_positive_text"
    const val EXTRA_NEGATIVE_TEXT = "extra_negative_text"
    const val EXTRA_NEUTRAL_TEXT = "extra_neutral_text"

    const val EXTRA_HEADER_LAYOUT_RES = "extra_header_layout_res"
    const val EXTRA_FOOTER_LAYOUT_RES = "extra_footer_layout_res"

    const val EXTRA_BUTTONS_CENTERED = "extra_buttons_centered"

    const val EXTRA_TITLE_ALIGN = "extra_title_align"
    const val EXTRA_MESSAGE_ALIGN = "extra_message_align"

    const val EXTRA_DIALOG_STYLE = "extra_dialog_style"
}

@IntDef(
    LayoutAlign.LEFT, LayoutAlign.RIGHT, LayoutAlign.CENTER
)
@Retention(AnnotationRetention.SOURCE)
annotation class LayoutAlign {
    companion object {
        const val LEFT = 0
        const val RIGHT = 1
        const val CENTER = 2
    }
}