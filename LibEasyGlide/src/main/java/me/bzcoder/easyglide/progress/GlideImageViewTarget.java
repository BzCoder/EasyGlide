package me.bzcoder.easyglide.progress;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

public class GlideImageViewTarget extends DrawableImageViewTarget {
    String url;

    public GlideImageViewTarget(ImageView view, String url) {
        super(view);
        this.url = url;
    }

    @Override
    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {
        OnProgressListener onProgressListener = ProgressManager.getProgressListener(url);
        if (onProgressListener != null) {
            onProgressListener.onProgress(false, 100, 0, 0);
            ProgressManager.removeListener(url);
        }
        super.onLoadFailed(errorDrawable);
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        OnProgressListener onProgressListener = ProgressManager.getProgressListener(url);
        if (onProgressListener != null) {
            onProgressListener.onProgress(true, 100, 0, 0);
            ProgressManager.removeListener(url);
        }
        super.onResourceReady(resource, transition);
    }



}