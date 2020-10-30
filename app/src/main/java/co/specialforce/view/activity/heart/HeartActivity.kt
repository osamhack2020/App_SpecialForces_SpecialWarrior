package co.specialforce.view.activity.heart

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Handler
import android.view.View
import android.widget.Toast
import co.specialforce.R
import co.specialforce.base.BaseActivity
import co.specialforce.data.response.getHeart.HeartArray
import co.specialforce.data.response.getHeart.HeartDailyData
import co.specialforce.data.response.getHeart.HeartMinMaxAvg
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_heart.*
import java.text.SimpleDateFormat
import java.util.*

class HeartActivity: BaseActivity(), HeartContract.View, View.OnClickListener {
    override val layoutRes: Int
        get() = R.layout.activity_heart

    override lateinit var presenter: HeartContract.Presenter
    private val lineData = LineData()

    override fun initView() {
        presenter = HeartPresenter(this@HeartActivity)
        presenter.start()

        heart_input_button.setOnClickListener(this)

        initChart()
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.heart_input_button ->{
                val progressDialog = ProgressDialog(this, android.R.style.Theme_Material_Dialog_Alert)
                progressDialog.setMessage("손가락을 기기 센서 위에 접촉해주세요")
                progressDialog.setCancelable(true)
                progressDialog.show()

                val handler = Handler()
                val finishHandler = Handler()
                handler.postDelayed({
                    progressDialog.setMessage("측정중입니다. 잠시만 기다려주세요")
                    finishHandler.postDelayed({
                        val randomHeartRate = Random().nextInt(50)+100
                        presenter.heartInput(randomHeartRate)
                        progressDialog.dismiss()
                        Toast.makeText(this, "현재 심박수는 "+randomHeartRate.toString()+"입니다", Toast.LENGTH_SHORT).show()
                        presenter.getHeart()
                    }, 5000)
                }, 2000)
            }
        }
    }

    private fun initChart(){
        val xAxis = heart_chart.xAxis
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
        heart_chart.data = lineData
        presenter.getHeart()
    }

    override fun setChartData(heartList: List<HeartDailyData>?) {
        val values = ArrayList<Entry>()
        if (heartList != null) {
            for(heart in heartList){
                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                values.add(Entry(dateFormat.parse(heart.date).time.toFloat(),
                    heart.min_max_avg.average
                ))
            }
        }
        val set = LineDataSet(values, "심박수")
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
        heart_chart.data = LineData(dataSets)
        heart_chart.data.notifyDataChanged()
        heart_chart.apply{
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
    }
    override fun isViewActive(): Boolean = isViewActive()
}