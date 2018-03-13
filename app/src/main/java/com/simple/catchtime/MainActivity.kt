package com.simple.catchtime

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.TypedValue
import kotlinx.android.synthetic.main.widget_catch_time.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_catch_time)

        val ratio = calcTime()
        Log.i(TAG, "ratio == $ratio")

        val width =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,400f,resources.displayMetrics)
        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,50f,resources.displayMetrics)

        val bitmap = Bitmap.createBitmap(width.toInt(), height.toInt(),
                Bitmap.Config.ARGB_8888)
        val drawable = CatchTimeDrawable(this, ratio, width, height)

        val canvas = Canvas(bitmap)
        drawable.draw(canvas)

        val year = Calendar.getInstance().get(Calendar.YEAR)
        textView.text = "${year}年已经过去$ratio%了"
        imageView.setImageBitmap(bitmap)
    }

    companion object {

        val TAG = "MainActivity"

        fun calcTime(): Long {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
            val time = sdf.format(Date())
            Log.d(TAG, "time == $time")
            val year = Calendar.getInstance().get(Calendar.YEAR)
            val startTime = "$year-1-1"
            Log.d(TAG, "startTime == $startTime")
            val endTime: String = if (year % 4 == 0) {
                "$year-12-31"
            } else {
                "$year-12-30"
            }
            val totalTime = sdf.parse(endTime).time - sdf.parse(startTime).time
            val loseTime = Date().time - sdf.parse(startTime).time
            val ratio = (loseTime * 100 / totalTime)
            Log.i(TAG, "ratio == $ratio")
            return ratio
//        val catchTimeRatio = 100 - ratio
//        return "${year}已经过去$ratio%..."
        }
    }


}
