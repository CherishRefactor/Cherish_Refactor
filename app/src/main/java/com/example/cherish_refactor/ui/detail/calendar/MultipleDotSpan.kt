package com.example.cherish_refactor.ui.detail.calendar

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan


class MultipleDotSpan(
    private val radius: Float,
    private val color: Int
) : LineBackgroundSpan {
    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        charSequence: CharSequence,
        start: Int,
        end: Int,
        lineNum: Int
    ) {
        var leftMost = 0
        val circleX = ((left + right) / 2 - leftMost).toFloat()
        val circleY = bottom + radius + 30
        val circleRadius = 10f
        paint.color = color
        canvas.drawCircle(circleX, circleY, circleRadius, paint)
        leftMost += 20
    }

}