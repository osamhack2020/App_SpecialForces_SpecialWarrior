package co.specialforce.view.activity.weight

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.Window
import co.specialforce.R
import kotlinx.android.synthetic.main.activity_weight_dialog.*

class WeightDialogActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_weight_dialog)

        weight_dialog_picker.minValue=40
        weight_dialog_picker.maxValue=120
        weight_dialog_picker.value=70
        weight_dialog_picker.wrapSelectorWheel = false

        weight_dialog_input_button.setOnClickListener(this)
        weight_dialog_cancel_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.weight_dialog_input_button -> {
                val intent = Intent()
                intent.putExtra("Result", weight_dialog_picker.value.toFloat())
                setResult(1, intent)
                finish()
            }
            R.id.weight_dialog_cancel_button -> finish()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.action != MotionEvent.ACTION_OUTSIDE
    }
}
