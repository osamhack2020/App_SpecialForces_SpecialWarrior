package co.specialforce.view.activity.weight

import android.content.Intent
import android.graphics.Color
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.getWeight.WeightArray
import co.specialforce.data.response.getWeight.WeightMinMaxAvg
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_weight.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
            axisMinimum = SimpleDateFormat("yyyy-MM-dd").parse("2020-10-17").time.toFloat()
            isGranularityEnabled = true
        }
        xAxis.valueFormatter = object : IndexAxisValueFormatter(){
            override fun getFormattedValue(value: Float): String {
                return SimpleDateFormat("yyyy-MM-dd").format(Date(value.toLong()))
            }

            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return SimpleDateFormat("yyyy-MM-dd").format(Date(value.toLong()))
            }
        }
        weight_chart.data = lineData
        presenter.getWeight()
    }

    override fun setChartData(weightList: List<WeightArray>?, weightMinMaxAvg: WeightMinMaxAvg?) {
        val values = ArrayList<Entry>()
        if (weightList != null) {
            for(weight in weightList){
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                values.add(Entry(dateFormat.parse(weight.date).time.toFloat(), weight.weight))
            }
        }
        val set = LineDataSet(values, "체중")
        set.apply{
            axisDependency = YAxis.AxisDependency.LEFT
            color = Color.BLACK
            setCircleColor(Color.BLACK)
            valueTextSize = 10f
            lineWidth = 2f
            circleRadius = 3f
            fillAlpha = 0
            fillColor = Color.BLACK
            setDrawValues(true)
            isHighlightEnabled = false
            mode = LineDataSet.Mode.CUBIC_BEZIER
        }
        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(set)
        weight_chart.data = LineData(dataSets)
        weight_chart.data.notifyDataChanged()
        weight_chart.apply{
            notifyDataSetChanged()
            axisRight.isEnabled = false
            moveViewToX(data.entryCount.toFloat())
            setMaxVisibleValueCount(7)
//            setVisibleXRangeMaximum(4f)
//            moveViewToX(7f)
//            setVisibleXRangeMaximum(7f)
            moveViewToX(data.entryCount.toFloat())
            setPinchZoom(false)
            animateY(1000)
            isDoubleTapToZoomEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
            setExtraOffsets(8f, 16f, 8f, 16f)
        }
    }
    override fun isViewActive(): Boolean = isViewActive()

}