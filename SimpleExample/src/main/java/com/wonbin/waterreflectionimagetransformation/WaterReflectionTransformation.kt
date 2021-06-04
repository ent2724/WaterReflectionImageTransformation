package com.wonbin.waterreflectionimagetransformation

import android.graphics.*
import android.graphics.Shader.TileMode
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.nio.charset.Charset
import java.security.MessageDigest


class WaterReflectionTransformation() : BitmapTransformation() {
    private val ID = "com.ha.util.WaterReflectionTransformation"
    private val ID_BYTES = ID.toByteArray(Charset.forName("UTF-8"))

    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val originWidth = toTransform.width
        val originHeight = toTransform.height

        val matrix = Matrix()
        matrix.preScale(1.toFloat(), (-1).toFloat())

        val bitmapReflection = Bitmap.createBitmap(toTransform, 0, originHeight / 2, originWidth, originHeight / 2, matrix, false)

        val bitmapOriginAndReflection = Bitmap.createBitmap(originWidth, originHeight + originHeight / 2, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmapOriginAndReflection)
        canvas.drawBitmap(toTransform, 0.toFloat(), 0.toFloat(), null)
        val defaultPaint = Paint()
        canvas.drawRect(0.toFloat(), originHeight.toFloat(), originWidth.toFloat(), (originHeight + 5).toFloat(), defaultPaint)
        canvas.drawBitmap(bitmapReflection, 0.toFloat(), (originHeight + 5).toFloat(), null)

        val paint = Paint()
        val shader = LinearGradient(0.toFloat(), toTransform.height.toFloat(), 0.toFloat(),
            (bitmapOriginAndReflection.height + 5).toFloat(), 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        canvas.drawRect(0.toFloat(), originHeight.toFloat(), originWidth.toFloat(), (bitmapOriginAndReflection.height + 5).toFloat(), paint)

        return bitmapOriginAndReflection
    }

    override fun equals(other: Any?): Boolean {
        return other is WaterReflectionTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }
}