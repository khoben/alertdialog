package io.github.khoben.alertdialog

interface CustomDialogEventListener {
    fun dialogOnNegative(token: String)
    fun dialogOnPositive(token: String)
    fun dialogOnNeutral(token: String)
}