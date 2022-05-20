package io.github.khoben.alertdialog.event

interface AlertEventListener {
    fun onAlertEvent(event: AlertEvent)

    object NOOP : AlertEventListener {
        override fun onAlertEvent(event: AlertEvent) = Unit
    }
}