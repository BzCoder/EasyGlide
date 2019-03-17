
package me.bzcoder.easyglide.config;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.util.Preconditions;

import me.bzcoder.easyglide.progress.GlideApp;
import me.bzcoder.easyglide.progress.GlideRequest;
import me.bzcoder.easyglide.progress.GlideRequests;
import me.bzcoder.easyglide.transformation.BlurTransformation;

/**
 * Glide加载工具
 *
 * @author : BaoZhou
 * @date : 2019/2/27 15:26
 */
public class CommonGlideImageLoaderStrategy {
    public void loadImage(Context ctx, GlideConfigImpl config) {
        Preconditions.checkNotNull(ctx, "Context is required");
        Preconditions.checkNotNull(config, "ImageConfigImpl is required");
        if (TextUtils.isEmpty(config.getUrl())) {
            //throw new NullPointerException("Url is required");
        }
        Preconditions.checkNotNull(config.getImageView(), "ImageView is required");


        GlideRequests requests;
        //如果context是activity则自动使用Activity的生命周期
        requests = GlideApp.with(ctx);
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
            glideRequest.transform(new BlurTransformation(ctx,config.getBlurValue()));
        }
        //glide用它来改变图形的形状
        if (config.getTransformation() != null) {
            glideRequest.transform(config.getTransformation());
        }

        if (config.getPlaceHolderDrawble() != null) {
            glideRequest.placeholder(config.getPlaceHolderDrawble());
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

        glideRequest.into(config.getImageView());
    }



    }


