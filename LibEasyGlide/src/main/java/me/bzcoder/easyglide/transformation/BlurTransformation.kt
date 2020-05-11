package me.bzcoder.easyglide.transformation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.annotation.RequiresApi
import com.bumptech.glide.load.Key
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.util.Util
import me.bzcoder.easyglide.util.BlurUtils
import java.security.MessageDigest

/**
 * @author sunfusheng on 2018/6/25.
 */
class BlurTransformation @JvmOverloads constructor(private val context: Context, radius: Int = MAX_RADIUS, sampling: Int = DEFAULT_SAMPLING) : BitmapTransformation() {
    private val ID = javaClass.name
    private val radius //模糊半径0～25
            : Int
    private val sampling //取样0～25
            : Int

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun transform(pool: BitmapPool, toTransform: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val width = toTransform.width
        val height = toTransform.height
        val scaledWidth = width / sampling
        val scaledHeight = height / sampling
        var bitmap = pool[scaledWidth, scaledHeight, Bitmap.Config.ARGB_8888]
        val canvas = Canvas(bitmap)
        canvas.scale(1 / sampling.toFloat(), 1 / sampling.toFloat())
        val paint = Paint()
        paint.flags = Paint.FILTER_BITMAP_FLAG
        canvas.drawBitmap(toTransform, 0f, 0f, paint)
        bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            BlurUtils.rsBlur(context, bitmap, radius)
        } else {
            BlurUtils.blur(bitmap, radius)!!
        }
        return bitmap
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is BlurTransformation) {
            val other = obj
            return radius == other.radius && sampling == other.sampling
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(), Util.hashCode(radius, Util.hashCode(sampling)))
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update((ID + radius * 10 + sampling).toByteArray(Key.CHARSET))
    }

    companion object {
        private const val MAX_RADIUS = 25
        private const val DEFAULT_SAMPLING = 1
    }

    init {
        this.radius = if (radius > MAX_RADIUS) MAX_RADIUS else radius
        this.sampling = if (sampling > MAX_RADIUS) MAX_RADIUS else sampling
    }
}