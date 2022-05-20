package io.github.khoben.alertdialog.builder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import io.github.khoben.alertdialog.Alert

/**
 * Build and show alert dialog
 */
@AlertDslMarker
inline fun Fragment.alert(crossinline block: AlertBuilder.() -> Unit) {
    Alert.create().apply(block).show(this)
}

/**
 * Build and show alert dialog
 */
@AlertDslMarker
inline fun FragmentActivity.alert(crossinline block: AlertBuilder.() -> Unit) {
    Alert.create().apply(block).show(this)
}