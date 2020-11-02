package co.specialforce.view.marker

import android.content.Context
import android.graphics.Canvas
import android.widget.TextView
import co.specialforce.R
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight

class SfMarkerView(context: Context?, layoutResource: Int) : MarkerView(context, layoutResource) {

    private var tvContent : TextView = findViewById(R.id.test_marker_view)

    override fun draw(canvas: Canvas?) {
        canvas!!.translate(-(width / 2).toFloat(), -height.toFloat())
        super.draw(canvas)
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        tvContent.text = e?.y.toString()

        super.refreshContent(e, highlight)
    }
}