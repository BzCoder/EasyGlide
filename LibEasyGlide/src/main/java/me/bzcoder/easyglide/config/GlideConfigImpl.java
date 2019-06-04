package me.bzcoder.easyglide.config;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestListener;

import me.bzcoder.easyglide.config.ImageConfig;
import me.bzcoder.easyglide.progress.OnProgressListener;

/**
 * @author : BaoZhou
 * @date : 2019/6/4 8:32
 */
public class GlideConfigImpl extends ImageConfig {

    /**
     * 0 对应DiskCacheStrategy.all
     * 1 对应DiskCacheStrategy.NONE
     * 2 对应DiskCacheStrategy.SOURCE
     * 3 对应DiskCacheStrategy.RESULT
     */

    private int cacheStrategy;
    private int fallback;
    private BitmapTransformation[] transformation;
    private ImageView[] imageViews;
    private boolean isClearMemory;
    private boolean isClearDiskCache;
    private Drawable placeholderDrawable;
    private int resizeX;
    private boolean isCropCenter;
    private boolean isCropCircle;
    private boolean isFitCenter;
    private DecodeFormat formatType;
    private int resizeY;
    private int imageRadius;
    private int blurValue;
    private boolean isCrossFade;
    private OnProgressListener onProgressListener;
    private RequestListener requestListener;

    private GlideConfigImpl(Builder builder) {
        this.url = builder.url;
        this.drawableId = builder.drawableId;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.placeholderDrawable = builder.placeholderDrawable;
        this.errorPic = builder.errorPic;
        this.fallback = builder.fallback;
        this.cacheStrategy = builder.cacheStrategy;
        this.transformation = builder.transformation;
        this.imageViews = builder.imageViews;
        this.isClearMemory = builder.isClearMemory;
        this.isClearDiskCache = builder.isClearDiskCache;
        this.resizeX = builder.resizeX;
        this.resizeY = builder.resizeY;
        this.isCropCenter = builder.isCropCenter;
        this.isCropCircle = builder.isCropCircle;
        this.formatType = builder.formatType;
        this.isFitCenter = builder.isFitCenter;
        this.isCrossFade = builder.isCrossFade;
        this.imageRadius = builder.imageRadius;
        this.blurValue = builder.blurValue;
        this.onProgressListener = builder.onProgressListener;
        this.requestListener = builder.requestListener;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public BitmapTransformation[] getTransformation() {
        return transformation;
    }

    public ImageView[] getImageViews() {
        return imageViews;
    }

    public boolean isClearMemory() {
        return isClearMemory;
    }

    public boolean isClearDiskCache() {
        return isClearDiskCache;
    }

    public int getFallback() {
        return fallback;
    }

    public Drawable getPlaceHolderDrawable() {
        return placeholderDrawable;
    }

    public int getResizeX() {
        return resizeX;
    }

    public int getResizeY() {
        return resizeY;
    }

    public boolean isCropCenter() {
        return isCropCenter;
    }

    public boolean isCropCircle() {
        return isCropCircle;
    }

    public DecodeFormat decodeFormate() {
        return formatType;
    }

    public boolean isFitCenter() {
        return isFitCenter;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public int getBlurValue() {
        return blurValue;
    }

    public boolean isBlurImage() {
        return blurValue > 0;
    }

    public int getImageRadius() {
        return imageRadius;
    }

    public boolean isImageRadius() {
        return imageRadius > 0;
    }

    public OnProgressListener getOnProgressListener() {
        return onProgressListener;
    }

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }


    public RequestListener getRequestListener() {
        return requestListener;
    }

    public void setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
    }


    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private int resizeX;
        private String url;
        private int drawableId;
        private ImageView imageView;
        private int placeholder;
        private Drawable placeholderDrawable;
        private int errorPic;
        private int fallback;
        private int cacheStrategy;
        private int imageRadius;
        private int blurValue;
        private BitmapTransformation[] transformation;
        private ImageView[] imageViews;
        private boolean isClearMemory;
        private boolean isClearDiskCache;
        private boolean isCropCenter;
        private boolean isCropCircle;
        private boolean isCrossFade;
        private DecodeFormat formatType;
        private boolean isFitCenter;
        private int resizeY;
        private OnProgressListener onProgressListener;
        private RequestListener requestListener;

        private Builder() {
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder drawableId(int drawableId) {
            this.drawableId = drawableId;
            return this;
        }

        public Builder placeholder(int placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        public Builder errorPic(int errorPic) {
            this.errorPic = errorPic;
            return this;
        }

        public Builder fallback(int fallback) {
            this.fallback = fallback;
            return this;
        }

        public Builder imageView(ImageView imageView) {
            this.imageView = imageView;
            return this;
        }

        public Builder cacheStrategy(int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public Builder imageRadius(int imageRadius) {
            this.imageRadius = imageRadius;
            return this;
        }

        public Builder blurValue(int blurValue) { //blurValue 建议设置为 15
            this.blurValue = blurValue;
            return this;
        }

        public Builder isCrossFade(boolean isCrossFade) {
            this.isCrossFade = isCrossFade;
            return this;
        }

        public Builder transformation(BitmapTransformation... transformation) {
            this.transformation = transformation;
            return this;
        }

        public Builder imageViews(ImageView... imageViews) {
            this.imageViews = imageViews;
            return this;
        }

        public Builder isClearMemory(boolean isClearMemory) {
            this.isClearMemory = isClearMemory;
            return this;
        }

        public Builder isClearDiskCache(boolean isClearDiskCache) {
            this.isClearDiskCache = isClearDiskCache;
            return this;
        }

        public Builder placeholderDrawble(Drawable placeholderDrawble) {
            this.placeholderDrawable = placeholderDrawble;
            return this;
        }

        public Builder resize(int resizeX, int resizeY) {
            this.resizeX = resizeX;
            this.resizeY = resizeY;
            return this;
        }

        public Builder isCropCenter(boolean isCropCenter) {
            this.isCropCenter = isCropCenter;
            return this;
        }

        public Builder isCropCircle(boolean isCropCircle) {
            this.isCropCircle = isCropCircle;
            return this;
        }

        public Builder setDecodeFormate(DecodeFormat decodeFormat) {
            formatType = decodeFormat;
            return this;
        }

        public Builder isFitCenter(boolean isFitCenter) {
            this.isFitCenter = isFitCenter;
            return this;
        }

        public Builder progressListener(OnProgressListener onProgressListener) {
            this.onProgressListener = onProgressListener;
            return this;
        }

        public Builder requestListener(RequestListener requestListener) {
            this.requestListener = requestListener;
            return this;
        }


        public GlideConfigImpl build() {
            return new GlideConfigImpl(this);
        }
    }
}
