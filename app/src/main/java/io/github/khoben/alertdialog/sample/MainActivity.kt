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
                .messageAlignment(LayoutAlign.CENTER)
                .titleAlignment(LayoutAlign.CENTER)
                .buttonsCentered()
                .show(this)
        }
        findViewById<Button>(R.id.custom_dialog).setOnClickListener {
            Alert.create()
                .title("Hi")
                .message("How do you do?")
                .withButtonCallback(dialogTag = SAMPLE_DIALOG_TAG)
                .positiveButton("Yes")
                .neutralButton("Cancel")
                .negativeButton("No")
                .titleAlignment(LayoutAlign.RIGHT)
                .messageAlignment(LayoutAlign.RIGHT)
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