package com.anwesh.uiprojects.concopaquesquaresview

/**
 * Created by anweshmishra on 01/03/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Color

val nodes : Int = 5
val squares : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val sizeFactor : Float = 2.8f
val strokeFactor : Int = 90
val strokeColor : Int = Color.parseColor("#212121")
val fillColor : Int = Color.parseColor("#4527A0")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i / n)
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.inverse() + scaleFactor() * b.inverse()
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawCOSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sizeGap : Float = size / squares
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    for (j in 0..(squares - 1)) {
        val k : Int = squares - 1 - j
        val efSize : Float = sizeGap * (k + 1)
        val sc : Float = sc1.divideScale(k, squares)
        val rect : RectF = RectF(-efSize, -efSize, efSize, efSize)
        save()
        paint.alpha = (255 * sc).toInt()
        paint.style = Paint.Style.FILL
        paint.color = fillColor
        drawRect(rect, paint)
        paint.style = Paint.Style.STROKE
        paint.color = strokeColor
        drawRect(rect, paint)
        restore()
    }
}