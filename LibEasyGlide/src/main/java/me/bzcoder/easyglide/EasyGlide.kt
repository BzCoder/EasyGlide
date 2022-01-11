package me.bzcoder.easyglide

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.os.Environment
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import android.webkit.MimeTypeMap
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.bumptech.glide.util.Preconditions
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.bzcoder.easyglide.config.GlideConfigImpl
import me.bzcoder.easyglide.progress.*
import me.bzcoder.easyglide.transformation.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * EasyGlide工具类
 * 更多转换可见 ：https://github.com/wasabeef/glide-transformations
 * @author : BaoZhou
 * @date : 2020/5/9 9:58 AM
 */

object EasyGlide {
    var placeHolderImageView = R.color.transparent
    var circlePlaceholderImageView = R.color.transparent

    /**
     * 加载本地图片
     *
     * @param context
     * @param drawableId
     * @param imageView
     */
    fun ImageView.loadImage(context: Context, @RawRes @DrawableRes drawableId: Int) {
        loadImage(context, GlideConfigImpl
                .builder()
                .drawableId(drawableId)
                .isCropCenter(true)
                .imageView(this)
                .build())
    }

    @JvmOverloads
    fun ImageView.loadImage(context: Context, url: String?, @DrawableRes placeHolder: Int = placeHolderImageView, onProgressListener: OnProgressListener? = null, requestListener: RequestListener<Drawable?>? = null) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .isCropCenter(true)
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .progressListener(onProgressListener)
                        .requestListener(requestListener)
                        .build())
    }

    /**
     * 加载本地图片
     * @param context
     * @param drawableId
     * @param resizeX
     * @param resizeY
     * @param imageView
     */
    @JvmOverloads
    fun ImageView.loadResizeXYImage(context: Context, url: String?, resizeX: Int, resizeY: Int, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .isCropCenter(true)
                        .isCrossFade(true)
                        .resize(resizeX, resizeY)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    @JvmOverloads
    fun ImageView.loadCircleImage(context: Context, url: String?, @DrawableRes placeHolder: Int = circlePlaceholderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .isCropCircle(true)
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    @JvmOverloads
    fun ImageView.loadGrayImage(context: Context, url: String?, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(CenterCrop(), GrayscaleTransformation())
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    @JvmOverloads
    fun ImageView.loadBlurImage(context: Context, url: String?, radius: Int = 10, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(CenterCrop(), BlurTransformation(context, radius))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    @JvmOverloads
    fun ImageView.loadRoundCornerImage(context: Context, url: String?, radius: Int = 40, margin: Int = 0, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(CenterCrop(), RoundedCornersTransformation(radius, margin))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    @JvmOverloads
    fun ImageView.loadCircleWithBorderImage(context: Context, url: String?, borderWidth: Int = 2, @ColorInt borderColor: Int = 0xACACAC, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(CircleWithBorderTransformation(borderWidth, borderColor))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    @JvmOverloads
    fun ImageView.loadBorderImage(context: Context, url: String?, borderWidth: Int = 2, @ColorInt borderColor: Int = 0xACACAC, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(BorderTransformation(borderWidth, borderColor))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }

    /**
     * 提供了一下如下变形类，支持叠加使用
     * BlurTransformation
     * GrayScaleTransformation
     * RoundedCornersTransformation
     * CircleCrop
     * CenterCrop
     */
    fun ImageView.loadImageWithTransformation(context: Context, url: String?, vararg bitmapTransformations: BitmapTransformation, @DrawableRes placeHolder: Int = placeHolderImageView) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(*bitmapTransformations)
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(this)
                        .build())
    }


    /**
     * 预加载
     *
     * @param context
     * @param url
     */
    fun preloadImage(context: Context, url: String?) {
        Glide.with(context).load(url).preload()
    }

    fun loadImage(context: Context, config: GlideConfigImpl) {
        Preconditions.checkNotNull(context, "Context is required")
        Preconditions.checkNotNull(config, "ImageConfigImpl is required")
        Preconditions.checkNotNull(config.imageView, "ImageView is required")
        val glideRequest = if (config.drawableId != 0) {
            EasyGlideApp.with(context).load(config.drawableId)
        } else {
            EasyGlideApp.with(context).load(config.url)
        }
        glideRequest.apply {
            when (config.cacheStrategy) {
                0 -> diskCacheStrategy(DiskCacheStrategy.ALL)
                1 -> diskCacheStrategy(DiskCacheStrategy.NONE)
                2 -> diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                3 -> diskCacheStrategy(DiskCacheStrategy.DATA)
                4 -> diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                else -> diskCacheStrategy(DiskCacheStrategy.ALL)
            }
            if (config.isCrossFade) {
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                transition(DrawableTransitionOptions.withCrossFade(factory))
            }
            if (config.isCrossFade) {
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                transition(DrawableTransitionOptions.withCrossFade(factory))
            }
            if (config.isImageRadius()) {
                transform(RoundedCorners(config.imageRadius))
            }
            if (config.isBlurImage) {
                transform(BlurTransformation(context, config.blurValue))
            }
            //glide用它来改变图形的形状
            if (config.transformation != null) {
                transform(*config.transformation)
            }
            if (config.placeHolderDrawable != null) {
                placeholder(config.placeHolderDrawable)
            }
            //设置占位符
            if (config.placeholder != 0) {
                placeholder(config.placeholder)
            }
            //设置错误的图片
            if (config.errorPic != 0) {
                error(config.errorPic)
            }
            //设置请求 url 为空图片
            if (config.fallback != 0) {
                fallback(config.fallback)
            }
            if (config.resizeX != 0 && config.resizeY != 0) {
                override(config.resizeX, config.resizeY)
            }
            if (config.isCropCenter) {
                centerCrop()
            }
            if (config.isCropCircle) {
                circleCrop()
            }
            if (config.formatType != null) {
                format(config.formatType)
            }
            if (config.isFitCenter) {
                fitCenter()
            }
            if (config.requestListener != null) {
                addListener(config.requestListener)
            }
            into(GlideImageViewTarget(config.imageView, config.url))
        }

        if (config.onProgressListener != null && !config.url.isNullOrBlank()) {
            ProgressManager.addListener(config.url!!, config.onProgressListener)
        }


    }

    /**
     * 清除本地缓存
     */
    @SuppressLint("CheckResult")
    fun clearDiskCache(context: Context) {
        Observable.just(0)
                .observeOn(Schedulers.io())
                .subscribe { Glide.get(context).clearDiskCache() }
    }

    /**
     * 清除内存缓存
     */
    @SuppressLint("CheckResult")
    fun clearMemory(context: Context) {
        Observable.just(0)
                .observeOn(Schedulers.io())
                .subscribe { Glide.get(context).clearDiskCache() }
    }

    /**
     * 取消图片加载
     */
    fun clearImage(context: Context, imageView: ImageView?) {
        EasyGlideApp.get(context).requestManagerRetriever[context].clear(imageView!!)
    }

    /**
     * 下载图片，并在媒体库中显示
     * @param context
     * @param imgUrl
     */
    @SuppressLint("CheckResult")
    fun downloadImageToGallery(context: Context, imgUrl: String?) {
        val extension = MimeTypeMap.getFileExtensionFromUrl(imgUrl)
        Observable.create(ObservableOnSubscribe { emitter: ObservableEmitter<File?> ->
            // Glide提供了一个download() 接口来获取缓存的图片文件，
            // 但是前提必须要设置diskCacheStrategy方法的缓存策略为
            // DiskCacheStrategy.ALL或者DiskCacheStrategy.SOURCE，
            // 还有download()方法需要在子线程里进行
            val file = Glide.with(context).download(imgUrl).submit().get()
            val fileParentPath = Environment.getExternalStorageDirectory().absolutePath + "/easyGlide/Image"
            val appDir = File(fileParentPath)
            if (!appDir.exists()) {
                appDir.mkdirs()
            }
            //获得原文件流
            val fis = FileInputStream(file)
            //保存的文件名
            val fileName = "easyGlide_" + System.currentTimeMillis() + "." + extension
            //目标文件
            val targetFile = File(appDir, fileName)
            //输出文件流
            val fos = FileOutputStream(targetFile)
            // 缓冲数组
            val b = ByteArray(1024 * 8)
            while (fis.read(b) != -1) {
                fos.write(b)
            }
            fos.flush()
            fis.close()
            fos.close()
            //扫描媒体库
            val mimeTypes = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            MediaScannerConnection.scanFile(context, arrayOf(targetFile.absolutePath), arrayOf(mimeTypes), null)
            emitter.onNext(targetFile)
        }).subscribeOn(Schedulers.io()) //发送事件在io线程
                .observeOn(AndroidSchedulers.mainThread()) //最后切换主线程提示结果
                .subscribe({ Toast.makeText(context, R.string.easy_glide_save_succss, Toast.LENGTH_SHORT).show() }
                ) { Toast.makeText(context, R.string.easy_glide_save_failed, Toast.LENGTH_SHORT).show() }
    }
}
