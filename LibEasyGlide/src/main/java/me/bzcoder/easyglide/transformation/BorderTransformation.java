package me.bzcoder.easyglide.transformation;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * 带边框
 *
 * @author : BaoZhou
 * @date : 2019/3/22 21:49
 */
public class BorderTransformation extends BitmapTransformation {
    private Paint mBorderPaint;
    private float mBorderWidth;
    private final String ID = getClass().getName();


    public BorderTransformation(int borderWidth, @ColorInt int borderColor) {
        mBorderWidth = Resources.getSystem().getDisplayMetrics().density * borderWidth;
        mBorderPaint = new Paint();
        mBorderPaint.setDither(true);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(borderColor);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (toTransform == null) {
            return null;
        }
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();

        Bitmap bitmap = pool.get(width, height, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();

        //绘制原图像
        canvas.drawBitmap(toTransform, null, new Rect(0, 0, width, height), paint);

        //描绘边框
        paint.setAntiAlias(true);
        if (mBorderPaint != null) {
            canvas.drawRect(0 + mBorderWidth/2, 0 + mBorderWidth/2, width - mBorderWidth/2, height - mBorderWidth/2, mBorderPaint);
        }
        return bitmap;
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + mBorderWidth * 10).getBytes(CHARSET));
    }
}