package me.bzcoder.easyglide;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.util.Preconditions;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import me.bzcoder.easyglide.config.GlideConfigImpl;
import me.bzcoder.easyglide.progress.EasyGlideApp;
import me.bzcoder.easyglide.progress.GlideImageViewTarget;
import me.bzcoder.easyglide.progress.GlideRequest;
import me.bzcoder.easyglide.progress.GlideRequests;
import me.bzcoder.easyglide.progress.OnProgressListener;
import me.bzcoder.easyglide.progress.ProgressManager;
import me.bzcoder.easyglide.transformation.BlurTransformation;
import me.bzcoder.easyglide.transformation.GrayscaleTransformation;
import me.bzcoder.easyglide.transformation.RoundedCornersTransformation;

/**
 * EasyGlide工具类
 * 更多转换可见 ：https://github.com/wasabeef/glide-transformations
 *
 * @author : BaoZhou
 * @date : 2019/3/18 21:01
 */
public class EasyGlide {
    static int placeHolderImageView = R.color.transparent;
    static int circlePlaceholderImageView = R.color.transparent;

    public static void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, placeHolderImageView, null);
    }

    public static void loadImage(Context context, String url, ImageView imageView, OnProgressListener onProgressListener) {
        loadImage(context, url, imageView, placeHolderImageView, onProgressListener);
    }

    public static void loadImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder) {
        loadImage(context, url, imageView, placeHolder, null);
    }

    public static void loadImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder, OnProgressListener onProgressListener) {
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
                        .transformation(new GrayscaleTransformation())
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
                        .transformation(new BlurTransformation(context, radius))
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
                        .transformation(new RoundedCornersTransformation(radius, margin))
                        .isCrossFade(true)
                        .errorPic(placeHolder)
                        .placeholder(placeHolder)
                        .imageView(imageView)
                        .build());
    }


    /**
     * 提供了一下如下变形类，支持叠加使用
     * BlurTransformation
     * GrayscaleTransformation
     * RoundedCornersTransformation
     * CircleCrop
     * CenterCrop
     */
    public static void loadImageWithTransformation(Context context, String url, ImageView imageView,BitmapTransformation... bitmapTransformations) {
        loadImageWithTransformation(context,url,imageView,R.color.transparent, bitmapTransformations);
    }

    public static void loadImageWithTransformation(Context context, String url, ImageView imageView, @DrawableRes int placeHolder,BitmapTransformation... bitmapTransformations) {
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
        if (TextUtils.isEmpty(config.getUrl())) {
            //throw new NullPointerException("Url is required");
        }
        Preconditions.checkNotNull(config.getImageView(), "ImageView is required");
        GlideRequests requests;
        requests = EasyGlideApp.with(context);
        GlideRequest<Drawable> glideRequest;
        glideRequest = requests.load(config.getUrl());
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
            glideRequest.transition(DrawableTransitionOptions.withCrossFade());
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

        if (config.getOnProgressListener() != null) {
            ProgressManager.addListener(config.getUrl(), config.getOnProgressListener());
        }

        glideRequest.into(new GlideImageViewTarget(config.getImageView(), config.getUrl()));
    }


    /**
     * 清除本地缓存
     */
    public static  void clearDiskCache(final Context context){
        Observable.just(0)
                .observeOn(Schedulers.io())
                .subscribe(integer -> Glide.get(context).clearDiskCache());
    }

    /**
     * 清除内存缓存
     */
    public static  void clearMemory(final Context context){
        Observable.just(0)
                .observeOn(Schedulers.io())
                .subscribe(integer -> Glide.get(context).clearDiskCache());
    }

    /**
     */
    public static  void clearImage(final Context context,ImageView imageView){
        EasyGlideApp.get(context).getRequestManagerRetriever().get(context).clear(imageView);
    }


}
