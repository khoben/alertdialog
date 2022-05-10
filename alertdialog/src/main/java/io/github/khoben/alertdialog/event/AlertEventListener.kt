package io.github.khoben.alertdialog.event

interface AlertEventListener {
    fun onAlertPositiveClick(event: AlertEvent.PositiveButtonEvent) {}
    fun onAlertNegativeClick(event: AlertEvent.NegativeButtonEvent) {}
    fun onAlertNeutralClick(event: AlertEvent.NeutralButtonEvent) {}

    object NOOP : AlertEventListener
}