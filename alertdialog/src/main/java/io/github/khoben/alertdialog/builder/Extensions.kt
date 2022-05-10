package io.github.khoben.alertdialog.builder

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
inline fun AppCompatActivity.alert(crossinline block: AlertBuilder.() -> Unit) {
    Alert.create().apply(block).show(this)
}