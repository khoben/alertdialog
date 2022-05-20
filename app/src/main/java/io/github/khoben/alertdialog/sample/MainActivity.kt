package io.github.khoben.alertdialog.sample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.khoben.alertdialog.param.LayoutAlign
import io.github.khoben.alertdialog.builder.alert
import io.github.khoben.alertdialog.event.AlertEvent
import io.github.khoben.alertdialog.event.AlertEventListener

class MainActivity : AppCompatActivity(R.layout.activity_main), AlertEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.simple_dialog).setOnClickListener {
            alert {
                title {
                    text("Hi")
                    alignment(LayoutAlign.CENTER)
                }
                message {
                    text("How do you do?")
                    alignment(LayoutAlign.CENTER)
                }
                controlsAlignCenter(true)
                cancellable(false)
            }
        }
        findViewById<Button>(R.id.custom_dialog).setOnClickListener {
            alert {
                title {
                    text("Hi")
                    alignment(LayoutAlign.RIGHT)
                }
                message {
                    text("How do you do?")
                    alignment(LayoutAlign.RIGHT)
                }
                controlsAlignCenter(true)
                buttonCallback(callbackTag = SAMPLE_DIALOG_TAG) {
                    positiveButton("Yes")
                    neutralButton("Cancel")
                    negativeButton("No")
                }
            }
        }
        findViewById<Button>(R.id.custom_view_dialog).setOnClickListener {
            alert {
                title {
                    text("Custom view")
                    alignment(LayoutAlign.CENTER)
                }
                message {
                    text("Message")
                    alignment(LayoutAlign.CENTER)
                }
                customView(R.layout.activity_main)
            }
        }
    }

    override fun onAlertEvent(event: AlertEvent) {
        event.doIfMatches(SAMPLE_DIALOG_TAG) {
            when(event) {
                is AlertEvent.Negative -> {
                    showToast("Negative via ${event.callbackTag}")
                }
                is AlertEvent.Neutral -> {
                    showToast("Neutral via ${event.callbackTag}")
                }
                is AlertEvent.Positive -> {
                    showToast("Positive via ${event.callbackTag}")
                }
            }
        }
    }

    private fun showToast(message: CharSequence) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val SAMPLE_DIALOG_TAG = "CUSTOM_DIALOG"
    }
}