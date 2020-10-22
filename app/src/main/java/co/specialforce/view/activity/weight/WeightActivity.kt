package co.specialforce.view.activity.weight

import android.content.Intent
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.getWeight.WeightArray
import co.specialforce.data.response.getWeight.WeightMinMaxAvg
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_weight.*

class WeightActivity : BaseActivity(), WeightContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.activity_weight

    override lateinit var presenter: WeightContract.Presenter
    private val lineData = LineData()

    override fun initView() {
        presenter = WeightPresenter(this@WeightActivity)
        presenter.start()

        weight_input_button.setOnClickListener(this)

        initChart()
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

    fun initChart(){
        val xAxis = weight_chart.xAxis
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 10f
            setDrawGridLines(false)
            granularity = 1f
            axisMinimum = 2f
            isGranularityEnabled = true
        }
        weight_chart.apply{
            axisRight.isEnabled = false
            axisLeft.axisMaximum = 50f
            // 범례 지정 원할 시 legend 값 apply 필요
        }
        weight_chart.data = lineData
        presenter.getWeight()
    }

    override fun setChartData(weightList: List<WeightArray>?, weightMinMaxAvg: WeightMinMaxAvg?) {

    }
    override fun isViewActive(): Boolean = isViewActive()
}