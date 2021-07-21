package io.github.khoben.alertdialog.sample

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.khoben.alertdialog.Alert
import io.github.khoben.alertdialog.CustomDialogEventListener
import io.github.khoben.alertdialog.LayoutAlign
import io.github.khoben.alertdialog.event.DialogEvent

class MainActivity : AppCompatActivity(R.layout.activity_main), CustomDialogEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.simple_dialog).setOnClickListener {
            Alert.create()
                .title("Hi")
                .message("How do you do?")
                .style(R.style.MaterialAlertDialog_MaterialComponents)
                .header(R.layout.dialog_header)
                .footer(R.layout.dialog_footer)
                .cancellable(false)
                .messageAlignment(LayoutAlign.CENTER)
                .titleAlignment(LayoutAlign.CENTER)
                .show(this)
        }
        findViewById<Button>(R.id.custom_dialog).setOnClickListener {
            Alert.create()
                .title("Hi")
                .message("How do you do?")
                .style(R.style.MaterialAlertDialog_MaterialComponents)
                .withButtonCallback(dialogTag = SAMPLE_DIALOG_TAG)
                .positiveButton("Yes")
                .neutralButton("Cancel")
                .negativeButton("No")
                .buttonsCentered()
                .header(R.layout.dialog_header)
                .footer(R.layout.dialog_footer)
                .titleAlignment(LayoutAlign.RIGHT)
                .messageAlignment(LayoutAlign.RIGHT)
                .cancellable(false)
                .show(this)
        }
    }

    override fun onNegativeClickEvent(event: DialogEvent.NegativeButtonEvent) {
        event.doIfTagMatches(SAMPLE_DIALOG_TAG) {
            Toast.makeText(this, "${event.dialogTag} onNegativeClickEvent", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onNeutralClickEvent(event: DialogEvent.NeutralButtonEvent) {
        event.doIfTagMatches(SAMPLE_DIALOG_TAG) {
            Toast.makeText(this, "${event.dialogTag} onNeutralClickEvent", Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onPositiveClickEvent(event: DialogEvent.PositiveButtonEvent) {
        event.doIfTagMatches(SAMPLE_DIALOG_TAG) {
            Toast.makeText(this, "${event.dialogTag} onPositiveClickEvent", Toast.LENGTH_SHORT)
                .show()
        }
    }

    companion object {
        private const val SAMPLE_DIALOG_TAG = "CUSTOM_DIALOG"
    }
}