package io.github.khoben.alertdialog.param

@androidx.annotation.IntDef(
    LayoutAlign.LEFT, LayoutAlign.RIGHT, LayoutAlign.CENTER
)
@Retention(AnnotationRetention.SOURCE)
annotation class LayoutAlign {
    companion object {
        const val LEFT = 0
        const val RIGHT = 1
        const val CENTER = 2
    }
}