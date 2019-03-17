package me.bzcoder.easyglide;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import me.bzcoder.easyglide.progress.OnProgressListener;
import me.bzcoder.easyglide.transformation.CircleTransformation;

public class EasyGlide {
    private static EasyGlide easyGlideInstance;
    private boolean enableState = false;
    private float pressedAlpha = 0.4f;
    private float unableAlpha = 0.3f;
    private GlideImageLoader imageLoader;



}
