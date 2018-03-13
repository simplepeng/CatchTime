package com.simple.catchtime

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.util.Log

/**
 * Created by simple on 2018/3/9.
 */
class CatchTimeDrawable(context: Context, ratio: Long, width: Float, height: Float) : Drawable() {

    private val TAG = "CatchTimeDrawable"

    private val mContext = context
    private val mRatio = ratio
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBgRect = RectF()
    private val mLoseRect = RectF()
    private val mRadius = 3f
    private val mWidth = width
    private val mHeight = height

    init {
        bounds.set(0, 0, mWidth.toInt(), mHeight.toInt())
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        mPaint.colorFilter = colorFilter
    }

    override fun draw(canvas: Canvas?) {
        mBgRect.set(bounds)
        Log.d(TAG, "totalWidth == ${bounds.right - bounds.left}")
        val loseWidth = (bounds.right - bounds.left) / 100 * mRatio
        Log.d(TAG, "loseWidth == $loseWidth")
        mLoseRect.set(mBgRect.left, mBgRect.top, loseWidth.toFloat(), mBgRect.bottom)
        canvas?.let {
            mPaint.color = ContextCompat.getColor(mContext, R.color.colorPrimary)
            it.drawRoundRect(mBgRect, mRadius, mRadius, mPaint)
            mPaint.color = ContextCompat.getColor(mContext, R.color.colorAccent)
            it.drawRoundRect(mLoseRect, mRadius, mRadius, mPaint)
        }
    }


}