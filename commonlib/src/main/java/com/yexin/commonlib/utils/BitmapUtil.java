package com.yexin.commonlib.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * author: zengven
 * date: 2018/8/8 14:57
 * desc: TODO
 */

public class BitmapUtil {

    private BitmapUtil() {
        throw new RuntimeException("don't new instance !!!");
    }

    /**
     * drawable 转换成bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawable2Bitmap(@NonNull Drawable drawable) {
        Bitmap bitmap = null;
        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.draw(canvas);
        }
        return bitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    /**
     * bitmap 转 drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable bitmap2Drawable(@NonNull Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    /**
     * @param resid
     * @return
     */
    public static Drawable bitmap2Drawable(@NonNull Context context, @DrawableRes int resid) {
        return context.getResources().getDrawable(resid);
    }
}
