package co.specialforce.view.activity.weight

import android.content.Intent
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseActivity
import kotlinx.android.synthetic.main.activity_weight.*

class WeightActivity : BaseActivity(), WeightContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.activity_weight

    override lateinit var presenter: WeightContract.Presenter

    override fun initView() {
        presenter = WeightPresenter(this@WeightActivity)
        presenter.start()

        weight_input_button.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.weight_input_button -> {
                startActivityForResult(Intent(this,
                    WeightDialogActivity::class.java),7777)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==7777){
            if(resultCode==1){ // result = OK
                val result : Float? = data?.getFloatExtra("Result", Float.MIN_VALUE)
                presenter.weightInput(result!!)
            }
        }
    }

    override fun isViewActive(): Boolean = isViewActive()
}