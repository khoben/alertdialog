package io.github.khoben.alertdialog

import androidx.annotation.StyleRes

internal object Const {
    @StyleRes
    val DEFAULT_STYLE = R.style.MaterialAlertDialog_MaterialComponents

    const val EXTRA_DIALOG_TAG = "extra_tag_dialog"
    const val EXTRA_TITLE = "extra_title"
    const val EXTRA_BODY = "extra_body"

    const val EXTRA_POSITIVE_TEXT = "extra_positive_text"
    const val EXTRA_NEGATIVE_TEXT = "extra_negative_text"
    const val EXTRA_NEUTRAL_TEXT = "extra_neutral_text"

    const val EXTRA_HEADER_LAYOUT_RES = "extra_header_layout_res"

    const val EXTRA_DIALOG_STYLE = "extra_dialog_style"
}