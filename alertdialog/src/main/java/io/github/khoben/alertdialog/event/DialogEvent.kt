package io.github.khoben.alertdialog.event

sealed class DialogEvent(val dialogTag: String) {
    fun doIfTagMatches(expectedTag: String, action: () -> Unit) {
        if (expectedTag == dialogTag) {
            action()
        }
    }
    class PositiveButtonEvent(dialogTag: String) : DialogEvent(dialogTag)
    class NegativeButtonEvent(dialogTag: String) : DialogEvent(dialogTag)
    class NeutralButtonEvent(dialogTag: String) : DialogEvent(dialogTag)
}