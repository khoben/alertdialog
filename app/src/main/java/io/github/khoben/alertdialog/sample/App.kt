package io.github.khoben.alertdialog.sample

import android.app.Application
import io.github.khoben.alertdialog.Alert

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Alert.defaultConfig {
            headerLayoutResource = R.layout.dialog_header
            footerLayoutResource = R.layout.dialog_footer
        }
    }
}