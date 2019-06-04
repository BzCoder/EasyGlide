package me.bzcoder.easyglide;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.util.Preconditions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.bzcoder.easyglide.config.GlideConfigImpl;
import me.bzcoder.easyglide.progress.EasyGlideApp;
import me.bzcoder.easyglide.progress.GlideImageViewTarget;
import me.bzcoder.easyglide.progress.GlideRequest;
import me.bzcoder.easyglide.progress.GlideRequests;
import me.bzcoder.easyglide.progress.OnProgressListener;
import me.bzcoder.easyglide.progress.ProgressManager;
import me.bzcoder.easyglide.transformation.BlurTransformation;
import me.bzcoder.easyglide.transformation.BorderTransformation;
import me.bzcoder.easyglide.transformation.CircleWithBorderTransformation;
import me.bzcoder.easyglide.transformation.GrayscaleTransformation;
import me.bzcoder.easyglide.transformation.RoundedCornersTransformation;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * EasyGlide工具类
 * 更多转换可见 ：https://github.com/wasabeef/glide-transformations
 *
 * @author : BaoZhou
 * @date : 2019/3/18 21:01
 */
public class EasyGlide {
    public static int placeHolderImageView = R.color.transparent;
    public static int circlePlaceholderImageView = R.color.transparent;


    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, placeHolderImageView, null, null);
    }

    public static void loadImage(Context context, String url, ImageView imageView, RequestListener requestListener) {
        loadImage(context, url, imageView, placeHolderImageView, null, requestListener);
    }

    public static void loadImage(Context context, String url, ImageView imageView, OnProgressListener onProgressListener) {
        loadImage(context, url, imageView, placeHolderImageView, onProgressListener, null);
    }

    public static void loadImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context, url, imageView, placeHolder, null, null);
    }

    public static void loadImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder, OnProgressListener onProgressListener, RequestListener requestListener) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .isCropCenter(true)
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .progressListener(onProgressListener)
                        .requestListener(requestListener)
                        .build());
    }

    public static void loadResizeXYImage(Context context, String url, int resizeX, int resizeY, ImageView imageView) {
        loadResizeXYImage(context, url, resizeX, resizeY, imageView, placeHolderImageView);
    }

    public static void loadResizeXYImage(Context context, String url, int resizeX, int resizeY, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .isCropCenter(true)
                        .isCrossFade(true)
                        .resize(resizeX, resizeX)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }


    public static void loadCircleImage(Context context, String url, ImageView imageView) {
        loadCircleImage(context, url, imageView, circlePlaceholderImageView);
    }

    public static void loadCircleImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .isCropCircle(true)
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }

    public static void loadGrayImage(Context context, String url, ImageView imageView) {
        loadGrayImage(context, url, imageView, placeHolderImageView);
    }

    public static void loadGrayImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(new CenterCrop(), new GrayscaleTransformation())
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }


    public static void loadBlurImage(Context context, String url, ImageView imageView) {
        loadBlurImage(context, url, 10, imageView, placeHolderImageView);
    }

    public static void loadBlurImage(Context context, String url, int radius, ImageView imageView) {
        loadBlurImage(context, url, radius, imageView, placeHolderImageView);
    }

    public static void loadBlurImage(Context context, String url, int radius, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(new CenterCrop(), new BlurTransformation(context, radius))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }


    public static void loadRoundCornerImage(Context context, String url, ImageView imageView) {
        loadRoundCornerImage(context, url, 40, 0, imageView, placeHolderImageView);
    }

    public static void loadRoundCornerImage(Context context, String url, int radius, int margin, ImageView imageView) {
        loadRoundCornerImage(context, url, radius, margin, imageView, placeHolderImageView);
    }

    public static void loadRoundCornerImage(Context context, String url, int radius, int margin, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(new CenterCrop(), new RoundedCornersTransformation(radius, margin))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }

    public static void loadCircleWithBorderImage(Context context, String url, ImageView imageView) {
        loadCircleWithBorderImage(context, url, 2, Color.parseColor("#ACACAC"), imageView, placeHolderImageView);
    }

    public static void loadCircleWithBorderImage(Context context, String url, int borderWidth, @ColorInt int borderColor, ImageView imageView) {
        loadCircleWithBorderImage(context, url, borderWidth, borderColor, imageView, placeHolderImageView);
    }

    public static void loadCircleWithBorderImage(Context context, String url, int borderWidth, @ColorInt int borderColor, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(new CircleWithBorderTransformation(borderWidth, borderColor))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }


    public static void loadBorderImage(Context context, String url, ImageView imageView) {
        loadBorderImage(context, url, 2, Color.parseColor("#ACACAC"), imageView, placeHolderImageView);
    }

    public static void loadBorderImage(Context context, String url, int borderWidth, @ColorInt int borderColor, ImageView imageView) {
        loadBorderImage(context, url, borderWidth, borderColor, imageView, placeHolderImageView);
    }

    public static void loadBorderImage(Context context, String url, int borderWidth, @ColorInt int borderColor, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(new BorderTransformation(borderWidth, borderColor))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }

    /**
     * 提供了一下如下变形类，支持叠加使用
     * BlurTransformation
     * GrayScaleTransformation
     * RoundedCornersTransformation
     * CircleCrop
     * CenterCrop
     */
    public static void loadImageWithTransformation(Context context, String url, ImageView imageView, BitmapTransformation... bitmapTransformations) {
        loadImageWithTransformation(context, url, imageView, R.color.transparent, bitmapTransformations);
    }

    public static void loadImageWithTransformation(Context context, String url, ImageView imageView, @DrawableRes int placeHolder, BitmapTransformation... bitmapTransformations) {
        loadImage(context,
                GlideConfigImpl
                        .builder()
                        .url(url)
                        .transformation(bitmapTransformations)
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }


    /**
     * 加载本地图片
     *
     * @param context
     * @param drawableId
     * @param imageView
     */
    public static void loadImage(Context context, @RawRes @DrawableRes @Nullable Integer drawableId, ImageView imageView) {
        loadImage(context, GlideConfigImpl
                .builder()
                .drawableId(drawableId)
                .isCropCenter(true)
                .imageView(imageView)
                .build());
    }

    /**
     * 预加载
     *
     * @param context
     * @param url
     */
    public static void preloadImage(Context context, String url) {
        Glide.with(context).load(url).preload();
    }

    public static void loadImage(Context context, GlideConfigImpl config) {
        Preconditions.checkNotNull(context, "Context is required");
        Preconditions.checkNotNull(config, "ImageConfigImpl is required");

        Preconditions.checkNotNull(config.getImageView(), "ImageView is required");
        GlideRequests requests;
        requests = EasyGlideApp.with(context);
        GlideRequest<Drawable> glideRequest = null;
        if (config.getDrawableId() != 0) {
            glideRequest = requests.load(config.getDrawableId());
        } else {
            glideRequest = requests.load(config.getUrl());
        }


        //缓存策略
        switch (config.getCacheStrategy()) {
            case 0:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
            case 1:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
                break;
            case 2:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                break;
            case 3:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA);
                break;
            case 4:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                break;
            default:
                glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                break;
        }
        if (config.isCrossFade()) {
            DrawableCrossFadeFactory factory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
            glideRequest.transition(withCrossFade(factory));
        }

        if (config.isImageRadius()) {
            glideRequest.transform(new RoundedCorners(config.getImageRadius()));
        }

        if (config.isBlurImage()) {
            glideRequest.transform(new BlurTransformation(context, config.getBlurValue()));
        }
        //glide用它来改变图形的形状
        if (config.getTransformation() != null) {
            glideRequest.transform(config.getTransformation());
        }

        if (config.getPlaceHolderDrawable() != null) {
            glideRequest.placeholder(config.getPlaceHolderDrawable());
        }
        //设置占位符
        if (config.getPlaceholder() != 0) {
            glideRequest.placeholder(config.getPlaceholder());
        }
        //设置错误的图片
        if (config.getErrorPic() != 0) {
            glideRequest.error(config.getErrorPic());
        }
        //设置请求 url 为空图片
        if (config.getFallback() != 0) {
            glideRequest.fallback(config.getFallback());
        }

        if (config.getResizeX() != 0 && config.getResizeY() != 0) {
            glideRequest.override(config.getResizeX(), config.getResizeY());
        }

        if (config.isCropCenter()) {
            glideRequest.centerCrop();
        }

        if (config.isCropCircle()) {
            glideRequest.circleCrop();
        }

        if (config.decodeFormate() != null) {
            glideRequest.format(config.decodeFormate());
        }

        if (config.isFitCenter()) {
            glideRequest.fitCenter();
        }

        if (config.getRequestListener() != null) {
            glideRequest.addListener(config.getRequestListener());
        }

        if (config.getOnProgressListener() != null) {
            ProgressManager.addListener(config.getUrl(), config.getOnProgressListener());
        }

        glideRequest.into(new GlideImageViewTarget(config.getImageView(), config.getUrl()));

    }


    /**
     * 清除本地缓存
     */
    public static void clearDiskCache(final Context context) {
        Observable.just(0)
                .observeOn(Schedulers.io())
                .subscribe(integer -> Glide.get(context).clearDiskCache());
    }

    /**
     * 清除内存缓存
     */
    public static void clearMemory(final Context context) {
        Observable.just(0)
                .observeOn(Schedulers.io())
                .subscribe(integer -> Glide.get(context).clearDiskCache());
    }

    /**
     * 取消图片加载
     */
    public static void clearImage(final Context context, ImageView imageView) {
        EasyGlideApp.get(context).getRequestManagerRetriever().get(context).clear(imageView);
    }


    /**
     * 下载图片，并在媒体库中显示
     *
     * @param context
     * @param imgUrl
     */
    @SuppressLint("CheckResult")
    public static void downloadImageToGallery(final Context context, final String imgUrl) {
        String extension = MimeTypeMap.getFileExtensionFromUrl(imgUrl);
        Observable.create((ObservableOnSubscribe<File>)
                emitter -> {
                    // Glide提供了一个download() 接口来获取缓存的图片文件，
                    // 但是前提必须要设置diskCacheStrategy方法的缓存策略为
                    // DiskCacheStrategy.ALL或者DiskCacheStrategy.SOURCE，
                    // 还有download()方法需要在子线程里进行
                    File file = Glide.with(context).download(imgUrl).submit().get();
                    String fileParentPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/easyGlide/Image";
                    File appDir = new File(fileParentPath);
                    if (!appDir.exists()) {
                        appDir.mkdirs();
                    } //获得原文件流
                    FileInputStream fis = new FileInputStream(file);
                    //保存的文件名
                    String fileName = "easyGlide_" + System.currentTimeMillis() + "." + extension;
                    //目标文件
                    File targetFile = new File(appDir, fileName);
                    //输出文件流
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    // 缓冲数组
                    byte[] b = new byte[1024 * 8];
                    while (fis.read(b) != -1) {
                        fos.write(b);
                    }
                    fos.flush();
                    fis.close();
                    fos.close();
                    //扫描媒体库
                    String mimeTypes = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                    MediaScannerConnection.scanFile(context, new String[]{targetFile.getAbsolutePath()},
                            new String[]{mimeTypes}, null);
                    emitter.onNext(targetFile);
                }).subscribeOn(Schedulers.io())
                //发送事件在io线程
                .observeOn(AndroidSchedulers.mainThread())
                //最后切换主线程提示结果
                .subscribe(file -> Toast.makeText(context, R.string.easy_glide_save_succss, Toast.LENGTH_SHORT).show(),
                        throwable -> Toast.makeText(context, R.string.easy_glide_save_failed, Toast.LENGTH_SHORT).show());
    }


}
