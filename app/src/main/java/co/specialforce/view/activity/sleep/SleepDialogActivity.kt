package co.specialforce.view.activity.sleep

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import co.specialforce.R
import kotlinx.android.synthetic.main.activity_sleep_dialog.*

class SleepDialogActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_sleep_dialog)

        sleep_dialog_hour_picker.minValue=0
        sleep_dialog_hour_picker.maxValue=23
        sleep_dialog_hour_picker.value=8
        sleep_dialog_hour_picker.wrapSelectorWheel = false

        sleep_dialog_minute_picker.minValue=0
        sleep_dialog_minute_picker.maxValue=60
        sleep_dialog_minute_picker.value=30
        sleep_dialog_minute_picker.wrapSelectorWheel = false

        sleep_dialog_input_button.setOnClickListener(this)
        sleep_dialog_cancel_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.sleep_dialog_input_button -> {
                val intent = Intent()
                intent.putExtra("Result", sleep_dialog_hour_picker.value*60 + sleep_dialog_minute_picker.value)
                setResult(1, intent)
                finish()
            }
            R.id.sleep_dialog_cancel_button -> finish()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.action != MotionEvent.ACTION_OUTSIDE
    }
}