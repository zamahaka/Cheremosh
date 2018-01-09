package org.zamahaka.cheremosh.ui.drawable

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.RequiresApi
import kotlin.math.PI
import kotlin.math.tan

class BottomAngleDrawable(
        private val drawable: Drawable,
        private val angle: Int
) : Drawable() {


    override fun draw(canvas: Canvas) {
        val width = canvas.width.toFloat()
        val height = canvas.height.toFloat()

        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, height - width * tan(angle.toDouble() * (PI / 180f)).toFloat())
            lineTo(0f, height)
            close()
        }
        canvas.clipPath(path)

        drawable.draw(canvas)
    }

    override fun setAlpha(alpha: Int) {
        drawable.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        drawable.colorFilter = colorFilter
    }

    override fun getOpacity() = PixelFormat.TRANSLUCENT

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        drawable.setBounds(left, top, right, bottom)
        super.setBounds(left, top, right, bottom)
    }

    override fun setBounds(bounds: Rect?) {
        drawable.bounds = bounds
        super.setBounds(bounds)
    }

    override fun setColorFilter(color: Int, mode: PorterDuff.Mode?) {
        drawable.setColorFilter(color, mode)
        super.setColorFilter(color, mode)
    }

    override fun setState(stateSet: IntArray?): Boolean {
        drawable.state = stateSet
        return super.setState(stateSet)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setTint(tintColor: Int) {
        drawable.setTint(tintColor)
        super.setTint(tintColor)
    }

    override fun setVisible(visible: Boolean, restart: Boolean): Boolean {
        drawable.setVisible(visible, restart)
        return super.setVisible(visible, restart)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setTintMode(tintMode: PorterDuff.Mode?) {
        drawable.setTintMode(tintMode)
        super.setTintMode(tintMode)
    }

    override fun scheduleSelf(what: Runnable?, `when`: Long) {
        drawable.scheduleSelf(what, `when`)
        super.scheduleSelf(what, `when`)
    }

    override fun clearColorFilter() {
        drawable.clearColorFilter()
        super.clearColorFilter()
    }

    override fun getMinimumHeight() = drawable.minimumHeight

    override fun invalidateSelf() {
        drawable.invalidateSelf()
        super.invalidateSelf()
    }

    override fun setDither(dither: Boolean) {
        drawable.setDither(dither)
        super.setDither(dither)
    }

    override fun setFilterBitmap(filter: Boolean) {
        drawable.isFilterBitmap = filter
        super.setFilterBitmap(filter)
    }

}