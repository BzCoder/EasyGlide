package me.bzcoder.easyglide.transformation

import android.content.res.Resources
import android.graphics.*
import androidx.annotation.ColorInt
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest

/**
 * 带边框的圆形
 * @author : BaoZhou
 * @date : 2019/3/22 21:49
 */
class CircleWithBorderTransformation(borderWidth: Int, @ColorInt borderColor: Int) :
    BitmapTransformation() {
    private val mBorderPaint: Paint = Paint()
    private val mBorderWidth: Float = Resources.getSystem().displayMetrics.density * borderWidth
    private val id = javaClass.name
    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(toTransform)!!
    }

    private fun circleCrop(source: Bitmap?): Bitmap? {
        if (source == null) {
            return null
        }
        val size = (source.width.coerceAtMost(source.height) - mBorderWidth / 2).toInt()
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squared = Bitmap.createBitmap(source, x, y, size, size)
        val result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(result)
        val paint = Paint()
        paint.shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        val borderRadius = r - mBorderWidth / 2
        canvas.drawCircle(r, r, borderRadius, mBorderPaint)
        return result
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((id + mBorderWidth * 10).toByteArray(Key.CHARSET))
    }

    init {
        mBorderPaint.isDither = true
        mBorderPaint.isAntiAlias = true
        mBorderPaint.color = borderColor
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.strokeWidth = mBorderWidth
    }
}