package com.example.mpandroidchart

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class MainActivity : AppCompatActivity() {
    private var mChart: LineChart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mChart = findViewById(R.id.line_chart)

        // Grid背景色
        mChart!!.setDrawGridBackground(true)

        // no description text
        mChart!!.description.isEnabled = true

        // Grid縦軸を破線
        val xAxis = mChart!!.xAxis
        xAxis.enableGridDashedLine(10f, 10f, 0f)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        val leftAxis = mChart!!.axisLeft
        // Y軸最大最小設定
        leftAxis.axisMaximum = 150f
        leftAxis.axisMinimum = 0f
        // Grid横軸を破線
        leftAxis.enableGridDashedLine(10f, 10f, 0f)
        leftAxis.setDrawZeroLine(true)

        // 右側の目盛り
        mChart!!.axisRight.isEnabled = false

        // add data
        setData()
        mChart!!.animateX(2500)
        //mChart.invalidate();

        // dont forget to refresh the drawing
        // mChart.invalidate();
    }

    private fun setData() {
        // Entry()を使ってLineDataSetに設定できる形に変更してarrayを新しく作成
        val data = intArrayOf(116, 111, 112, 121, 102, 83,
                99, 101, 74, 105, 120, 112,
                109, 102, 107, 93, 82, 99, 110)
        val values: ArrayList<Map.Entry<*, *>> = ArrayList()
        for (i in data.indices) {
            values.add(MutableMap.MutableEntry<Any?, Any?>(i, data[i], null, null))
        }
        val set1: LineDataSet
        if (mChart!!.data != null &&
                mChart!!.data.dataSetCount > 0) {
            set1 = mChart!!.data.getDataSetByIndex(0) as LineDataSet
            set1.setValues(values)
            mChart!!.data.notifyDataChanged()
            mChart!!.notifyDataSetChanged()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(values, "DataSet")
            set1.setDrawIcons(false)
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.valueTextSize = 0f
            set1.setDrawFilled(true)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f
            set1.fillColor = Color.BLUE
            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the datasets

            // create a data object with the datasets
            val lineData = LineData(dataSets)

            // set data
            mChart!!.data = lineData
        }
    }
}