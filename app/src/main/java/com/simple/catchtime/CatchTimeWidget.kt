package com.simple.catchtime

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.TypedValue
import android.widget.RemoteViews
import java.util.*

/**
 * Created by simple on 2018/3/8.
 */
class CatchTimeWidget : AppWidgetProvider() {


    /**
     * 每次窗口小部件被更新都调用一次该方法
     */
    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val width =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,400f,context?.resources?.displayMetrics)
        val height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,50f,context?.resources?.displayMetrics)

        appWidgetIds?.map {
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val remoteViews = RemoteViews(context?.packageName, R.layout.widget_catch_time)

            val ratio = MainActivity.calcTime()
            val drawable = CatchTimeDrawable(context!!, ratio, width, height)
            val bitmap = Bitmap.createBitmap(width.toInt(), height.toInt(),
                    Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.draw(canvas)
            remoteViews.setImageViewBitmap(R.id.imageView, bitmap)

            val year = Calendar.getInstance().get(Calendar.YEAR)
            remoteViews.setTextViewText(R.id.textView, "${year}年已经过去$ratio%了")
            appWidgetManager?.updateAppWidget(it, remoteViews)
        }
    }

    /**
     * 接收窗口小部件点击时发送的广播
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

    }

    /**
     * 每删除一次窗口小部件就调用一次
     */
    override fun onDeleted(context: Context?, appWidgetIds: IntArray?) {
        super.onDeleted(context, appWidgetIds)
    }

    /**
     * 当最后一个该窗口小部件删除时调用该方法
     */
    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
    }

    /**
     * 当该窗口小部件第一次添加到桌面时调用该方法
     */
    override fun onEnabled(context: Context?) {
        super.onEnabled(context)
    }

    /**
     * 当小部件大小改变时
     */
    override fun onAppWidgetOptionsChanged(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetId: Int, newOptions: Bundle?) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }

    /**
     * 当小部件从备份恢复时调用该方法
     */
    override fun onRestored(context: Context?, oldWidgetIds: IntArray?, newWidgetIds: IntArray?) {
        super.onRestored(context, oldWidgetIds, newWidgetIds)
    }
}