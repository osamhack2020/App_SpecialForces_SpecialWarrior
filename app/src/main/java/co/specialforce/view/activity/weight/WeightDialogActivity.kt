package co.specialforce.view.activity.weight

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import co.specialforce.R
import kotlinx.android.synthetic.main.activity_weight_dialog.*

class WeightDialogActivity : Activity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_dialog)

        weight_dialog_picker.minValue=40
        weight_dialog_picker.maxValue=120
        weight_dialog_picker.value=70
        weight_dialog_picker.wrapSelectorWheel = true

//
//        val list = ArrayList<String>()
//        for(i in 40 until 120){
//            for(j in 0 until 9){
//                list.add("$i.$j")
//            }
//        }
//
//        weight_dialog_picker.displayedValues = list.toTypedArray()
//        weight_dialog_picker.wrapSelectorWheel = false

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
