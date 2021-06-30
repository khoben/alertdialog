package io.github.khoben.alertdialog

import androidx.fragment.app.DialogFragment

inline fun DialogFragment.postConfig(block: DialogFragment.() -> Unit): DialogFragment {
    block()
    return this
}