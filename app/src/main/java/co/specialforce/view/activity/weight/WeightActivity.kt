package co.specialforce.view.activity.weight

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

            }
        }
    }

    override fun isViewActive(): Boolean = isViewActive()
}