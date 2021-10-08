package io.github.khoben.alertdialog.event

sealed class DialogEvent(val dialogTag: String) {
    class PositiveButtonEvent(dialogTag: String) : DialogEvent(dialogTag)
    class NegativeButtonEvent(dialogTag: String) : DialogEvent(dialogTag)
    class NeutralButtonEvent(dialogTag: String) : DialogEvent(dialogTag)

    inline fun doIfTagMatches(expectedTag: String, crossinline action: () -> Unit) {
        if (expectedTag == dialogTag) {
            action()
        }
    }
}