package com.example.beerview.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.AppCompatButton
import android.text.SpannableString
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.util.AttributeSet
import com.example.beerview.R

class ProgressButton @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {


    private val progressDrawable = CircularProgressDrawable(context).apply {
        setStyle(CircularProgressDrawable.DEFAULT)
        setColorSchemeColors(Color.WHITE)
        /*  centerRadius = context.resources.getDimension(R.dimen.indicator_radius)
          strokeWidth = context.resources.getDimension(R.dimen.indicator_stroke)*/
        val size = (centerRadius + strokeWidth).toInt() * 2
        setBounds(0, 0, size, size)
    }


    private val drawableSpan = object : DynamicDrawableSpan() {
        override fun getDrawable(): Drawable = progressDrawable

        override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {

            val rect = drawable.bounds

            return fm?.let {
                val fontMetrics = paint.fontMetrics
                val lineHeight = fontMetrics.bottom - fontMetrics.top
                val drHegith = Math.max(lineHeight, (rect.bottom - rect.top).toFloat())
                val centerY = fontMetrics.top + lineHeight / 2
                it.apply {
                    ascent = ((centerY - drHegith) / 2).toInt()
                    descent = ((centerY + drHegith) / 2).toInt()
                    top = ascent
                    bottom = descent
                }
            }?.run { rect.width() } ?: super.getSize(paint, text, start, end, fm)
        }

        override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
            super.draw(canvas, text, start, end, x, top, y, bottom, paint)
        }
    }

    private val spannableString by lazy {
        SpannableString("Loading.... ").apply {
            this.length
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    private val callback = object : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        }

        override fun invalidateDrawable(who: Drawable) {
            invalidate()
        }

        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        }
    }

    init {

        setOnClickListener {
            progressDrawable.start()
            progressDrawable.callback = callback
            text = spannableString
        }
    }

}