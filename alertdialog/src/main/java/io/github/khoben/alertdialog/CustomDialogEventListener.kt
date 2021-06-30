package io.github.khoben.alertdialog

import io.github.khoben.alertdialog.event.DialogEvent

interface CustomDialogEventListener {
    fun onPositiveClickEvent(event: DialogEvent.PositiveButtonEvent) {}
    fun onNegativeClickEvent(event: DialogEvent.NegativeButtonEvent) {}
    fun onNeutralClickEvent(event: DialogEvent.NeutralButtonEvent) {}
}