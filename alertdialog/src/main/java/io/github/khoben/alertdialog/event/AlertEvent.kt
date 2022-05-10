package io.github.khoben.alertdialog.event

sealed class AlertEvent(val dialogTag: String) {
    class PositiveButtonEvent(dialogTag: String) : AlertEvent(dialogTag)
    class NegativeButtonEvent(dialogTag: String) : AlertEvent(dialogTag)
    class NeutralButtonEvent(dialogTag: String) : AlertEvent(dialogTag)

    /**
     * Run [block] if tags match
     */
    inline fun doIfMatches(expectedTag: String, crossinline block: () -> Unit) {
        if (dialogTag == expectedTag) {
            block()
        }
    }
}