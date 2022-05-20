## AlertDialog - Customizable alert dialog with retained callback actions

#### [Sample app (apk)](https://github.com/khoben/alertdialog/releases/latest/download/sample.apk)

### Usage
#### Setup alert dialog config (optional)

Should be called once at Application.OnCreate() or smth:
```kotlin
Alert.defaultConfig {
   dialogStyle = R.style.MaterialAlertDialog_MaterialComponents
   headerLayoutResource = R.layout.dialog_header
   footerLayoutResource = R.layout.dialog_footer
   dialogIsCancellable = false
}
```
#### Create AlertDialog

```kotlin
private val SAMPLE_DIALOG_TAG = "SAMPLE_DIALOG_TAG"

...

alert { // build and show alert
    style(R.style.MaterialAlertDialog_MaterialComponents)
    header(R.layout.dialog_header)
    footer(R.layout.dialog_footer)
    title { 
        text("Hi")
        alignment(LayoutAlign.RIGHT)
    }
    message {
        text("How do you do?")
        alignment(LayoutAlign.RIGHT)
    }
    controlsAlignCenter(true) // make button layout centered
    buttonCallback(callbackTag = SAMPLE_DIALOG_TAG) { // enable callback
        positiveButton("Yes")
        neutralButton("Cancel")
        negativeButton("No")
    }
    customView(R.layout.activity_main) // set custom view
    cancellable(false)
}
```
<table>
    <td>
        <p align="center"><img src="./Readme.md-images/1.png"> <br>Simple</p>
    </td>
     <td>
        <p align="center"><img src="./Readme.md-images/3.png"><br>With header</p>
    </td>
     <td>
        <p align="center"><img src="./Readme.md-images/4.png"><br>Full equipment</p>
    </td>
</table>

Listen alert dialog button click events:
```kotlin
// Implement AlertEventListener to catch button click events from alert dialog buttons
class MainActivity : AppCompatActivity(R.layout.activity_main), 
AlertEventListener {

    ...

    // from AlertEventListener
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
}
```

### Installation
1. Add the JitPack repository to your build file:
    ```bash
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency

    Latest version is⠀[![](https://jitpack.io/v/khoben/alertdialog.svg)](https://jitpack.io/#khoben/alertdialog)

    ```bash
    dependencies {
        implementation 'com.github.khoben:alertdialog:<latest_version>'
    }
    ```

