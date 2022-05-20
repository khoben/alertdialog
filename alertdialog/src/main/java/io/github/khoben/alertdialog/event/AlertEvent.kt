package io.github.khoben.alertdialog.event

sealed class AlertEvent(val callbackTag: String) {
    class Positive(callbackTag: String) : AlertEvent(callbackTag)
    class Negative(callbackTag: String) : AlertEvent(callbackTag)
    class Neutral(callbackTag: String) : AlertEvent(callbackTag)

    /**
     * Run [block] if tags match
     */
    inline fun doIfMatches(expectedTag: String, crossinline block: () -> Unit) {
        if (callbackTag == expectedTag) {
            block()
        }
    }
}