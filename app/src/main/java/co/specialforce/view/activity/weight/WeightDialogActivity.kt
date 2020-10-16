package co.specialforce.view.activity.weight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.specialforce.R
import kotlinx.android.synthetic.main.activity_weight_dialog.*

class WeightDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_dialog)
        weight_dialog_picker.minValue = 20
        weight_dialog_picker.maxValue = 150
        weight_dialog_picker.value = 70
    }
}
