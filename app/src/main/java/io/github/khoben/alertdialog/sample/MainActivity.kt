package io.github.khoben.alertdialog.sample

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.khoben.alertdialog.CustomAlert
import io.github.khoben.alertdialog.CustomDialogEventListener
import io.github.khoben.alertdialog.event.DialogEvent
import io.github.khoben.alertdialog.postConfig

class MainActivity : AppCompatActivity(R.layout.activity_main), CustomDialogEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<Button>(R.id.simple_dialog).setOnClickListener {
            CustomAlert.simple("Hi", "How do you do?")
                .postConfig {
                    isCancelable = false
                }.show(supportFragmentManager, "Test")
        }
        findViewById<Button>(R.id.custom_dialog).setOnClickListener {
            CustomAlert.custom(SAMPLE_DIALOG_TAG, "Hi", "How do you do?")
                .setDialogStyle(R.style.MaterialAlertDialog_MaterialComponents)
                .enablePositive("+")
                .enableNeutral("=")
                .enableNegative("-")
                .build()
                .postConfig {
                    isCancelable = false
                }.show(supportFragmentManager, "Test")
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