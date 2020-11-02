package co.specialforce.view.activity.sleep

import android.content.Intent
import android.graphics.Color
import android.view.View
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.getSleep.SleepArray
import co.specialforce.data.response.getSleep.SleepMinMaxAvg
import co.specialforce.view.marker.SfMarkerView
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_sleep.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SleepActivity: BaseActivity(), SleepContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.activity_sleep

    override lateinit var presenter : SleepContract.Presenter
    private val lineData = LineData()

    override fun initView() {
        presenter = SleepPresenter(this@SleepActivity)
        presenter.start()

        sleep_input_button.setOnClickListener(this)

        initChart()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.sleep_input_button -> {
                startActivityForResult(Intent(this,
                    SleepDialogActivity::class.java),7777)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==7777){
            if(resultCode==1){ // result = OK
                val result : Int? = data?.getIntExtra("Result", Int.MIN_VALUE)
                presenter.sleepInput(result!!)
                presenter.getSleep()
            }
        }
    }

    private fun initChart(){
        val xAxis = sleep_chart.xAxis
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
                return SimpleDateFormat("MM-dd").format(Date(value.toLong()))
            }

            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return SimpleDateFormat("MM-dd").format(Date(value.toLong()))
            }
        }
        sleep_chart.data = lineData
        presenter.getSleep()
    }

    override fun setChartData(sleepList: List<SleepArray>?, sleepMinMaxAvg: SleepMinMaxAvg?) {
        val values = ArrayList<Entry>()
        if (sleepList != null) {
            for(sleep in sleepList){
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                values.add(Entry(dateFormat.parse(sleep.date).time.toFloat(), sleep.sleep_time))
            }
        }
        val set = LineDataSet(values, "수면시간")
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
        sleep_chart.data = LineData(dataSets)
        sleep_chart.data.notifyDataChanged()
        sleep_chart.apply{
            notifyDataSetChanged()
            axisRight.isEnabled = false
            moveViewToX(data.entryCount.toFloat())
            setMaxVisibleValueCount(7)
            moveViewToX(data.entryCount.toFloat())
            setPinchZoom(false)
            animateY(1000)
            isDoubleTapToZoomEnabled = false
            legend.isEnabled = false
            description.isEnabled = false
            setExtraOffsets(8f, 16f, 8f, 16f)
        }
        sleep_min_text.text = sleepMinMaxAvg?.min?.toInt().toString()+"분"
        sleep_max_text.text = sleepMinMaxAvg?.max?.toInt().toString()+"분"
        sleep_avg_text.text = sleepMinMaxAvg?.average?.toInt().toString()+"분"
        val marker = SfMarkerView(this, R.layout.marker_textview)
        marker.chartView = sleep_chart
        sleep_chart.marker = marker
    }
    override fun isViewActive(): Boolean = isViewActive()
}