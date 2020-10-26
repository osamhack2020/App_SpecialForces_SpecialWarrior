package co.specialforce.view.activity.weight

import android.content.Intent
import android.graphics.Color
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.getWeight.WeightArray
import co.specialforce.data.response.getWeight.WeightMinMaxAvg
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
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

    private fun initChart(){
        val xAxis = weight_chart.xAxis
        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 10f
            setDrawGridLines(false)
            granularity = 1f
            axisMinimum = 2f
            isGranularityEnabled = true
        }
        val formatter = object : ValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                val date = value.toInt()
                return (date/10000).toString() + "-" + ((date/100)%100).toString() +
                        "-" + (date%100).toString()
            }
        }
        xAxis.valueFormatter = formatter
        weight_chart.apply{
            axisRight.isEnabled = false
            axisLeft.axisMaximum = 50f
            // 범례 지정 원할 시 legend 값 apply 필요
        }
        weight_chart.data = lineData
        presenter.getWeight()
    }

    override fun setChartData(weightList: List<WeightArray>?, weightMinMaxAvg: WeightMinMaxAvg?) {
        val values = ArrayList<Entry>()
        if (weightList != null) {
            for(weight in weightList){
                var num = 0
                num += weight.date.substring(0,4).toInt()*10000
                num += weight.date.substring(5,7).toInt()*100
                num += weight.date.substring(8,10).toInt()
                values.add(Entry(num.toFloat(), weight.weight.toFloat()))
            }
        }
        val set = LineDataSet(values, "체중")
        set.color = Color.BLACK
        set.setCircleColor(Color.BLACK)
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set)
        weight_chart.data = LineData(dataSets)

    }
    override fun isViewActive(): Boolean = isViewActive()
}