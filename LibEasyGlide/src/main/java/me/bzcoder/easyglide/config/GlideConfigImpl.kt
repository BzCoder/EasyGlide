package me.bzcoder.easyglide.config

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.request.RequestListener
import me.bzcoder.easyglide.progress.OnProgressListener

/**
 * Glide配置类
 * @author : BaoZhou
 * @date : 2020/5/9 2:49 PM
 */
class GlideConfigImpl private constructor(builder: Builder) : ImageConfig() {
    /**
     * 0 对应DiskCacheStrategy.all
     * 1 对应DiskCacheStrategy.NONE
     * 2 对应DiskCacheStrategy.SOURCE
     * 3 对应DiskCacheStrategy.RESULT
     */
    val cacheStrategy: Int
    val fallback: Int
    val transformation: Array<out BitmapTransformation>?
    val imageViews: Array<out ImageView>?
    val isClearMemory: Boolean
    val isClearDiskCache: Boolean
    val placeHolderDrawable: Drawable?
    val resizeX: Int
    val isCropCenter: Boolean
    val isCropCircle: Boolean
    val isFitCenter: Boolean
    val formatType: DecodeFormat?
    val resizeY: Int
    val imageRadius: Int
    val blurValue: Int
    val isCrossFade: Boolean
    var onProgressListener: OnProgressListener?
    var requestListener: RequestListener<Drawable?>?


    val isBlurImage: Boolean
        get() = blurValue > 0

    fun isImageRadius(): Boolean {
        return imageRadius > 0
    }

    class Builder {
        var resizeX = 0
        var url: String? = null
        var drawableId = 0
        var imageView: ImageView? = null
        var placeholder = 0
        var placeholderDrawable: Drawable? = null
        var errorPic = 0
        var fallback = 0
        var cacheStrategy = 0
        var imageRadius = 0
        var blurValue = 0
        var transformation: Array<out BitmapTransformation>? = null
        var imageViews: Array<out ImageView>? = null
        var isClearMemory = false
        var isClearDiskCache = false
        var isCropCenter = false
        var isCropCircle = false
        var isCrossFade = false
        var formatType: DecodeFormat? = null
        var isFitCenter = false
        var resizeY = 0
        var onProgressListener: OnProgressListener? = null
        var requestListener: RequestListener<Drawable?>? = null
        fun url(url: String?): Builder {
            this.url = url
            return this
        }

        fun drawableId(drawableId: Int): Builder {
            this.drawableId = drawableId
            return this
        }

        fun placeholder(placeholder: Int): Builder {
            this.placeholder = placeholder
            return this
        }

        fun errorPic(errorPic: Int): Builder {
            this.errorPic = errorPic
            return this
        }

        fun fallback(fallback: Int): Builder {
            this.fallback = fallback
            return this
        }

        fun imageView(imageView: ImageView?): Builder {
            this.imageView = imageView
            return this
        }

        fun cacheStrategy(cacheStrategy: Int): Builder {
            this.cacheStrategy = cacheStrategy
            return this
        }

        fun imageRadius(imageRadius: Int): Builder {
            this.imageRadius = imageRadius
            return this
        }

        fun blurValue(blurValue: Int): Builder { //blurValue 建议设置为 15
            this.blurValue = blurValue
            return this
        }

        fun isCrossFade(isCrossFade: Boolean): Builder {
            this.isCrossFade = isCrossFade
            return this
        }

        fun transformation(vararg transformation: BitmapTransformation): Builder {
            this.transformation = transformation
            return this
        }

        fun imageViews(vararg imageViews: ImageView): Builder {
            this.imageViews = imageViews
            return this
        }

        fun isClearMemory(isClearMemory: Boolean): Builder {
            this.isClearMemory = isClearMemory
            return this
        }

        fun isClearDiskCache(isClearDiskCache: Boolean): Builder {
            this.isClearDiskCache = isClearDiskCache
            return this
        }

        fun placeholderDrawable(placeholderDrawable: Drawable?): Builder {
            this.placeholderDrawable = placeholderDrawable
            return this
        }

        fun resize(resizeX: Int, resizeY: Int): Builder {
            this.resizeX = resizeX
            this.resizeY = resizeY
            return this
        }

        fun isCropCenter(isCropCenter: Boolean): Builder {
            this.isCropCenter = isCropCenter
            return this
        }

        fun isCropCircle(isCropCircle: Boolean): Builder {
            this.isCropCircle = isCropCircle
            return this
        }

        fun setDecodeFormat(decodeFormat: DecodeFormat?): Builder {
            formatType = decodeFormat
            return this
        }

        fun isFitCenter(isFitCenter: Boolean): Builder {
            this.isFitCenter = isFitCenter
            return this
        }

        fun progressListener(onProgressListener: OnProgressListener?): Builder {
            this.onProgressListener = onProgressListener
            return this
        }

        fun requestListener(requestListener: RequestListener<Drawable?>?): Builder {
            this.requestListener = requestListener
            return this
        }

        fun build(): GlideConfigImpl {
            return GlideConfigImpl(this)
        }
    }

    companion object {
        fun builder(): Builder {
            return Builder()
        }
    }

    init {
        url = builder.url
        drawableId = builder.drawableId
        imageView = builder.imageView
        placeholder = builder.placeholder
        placeHolderDrawable = builder.placeholderDrawable
        errorPic = builder.errorPic
        fallback = builder.fallback
        cacheStrategy = builder.cacheStrategy
        transformation = builder.transformation
        imageViews = builder.imageViews
        isClearMemory = builder.isClearMemory
        isClearDiskCache = builder.isClearDiskCache
        resizeX = builder.resizeX
        resizeY = builder.resizeY
        isCropCenter = builder.isCropCenter
        isCropCircle = builder.isCropCircle
        formatType = builder.formatType
        isFitCenter = builder.isFitCenter
        isCrossFade = builder.isCrossFade
        imageRadius = builder.imageRadius
        blurValue = builder.blurValue
        onProgressListener = builder.onProgressListener
        requestListener = builder.requestListener
    }
}