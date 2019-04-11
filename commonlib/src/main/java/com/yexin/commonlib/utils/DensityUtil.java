package com.yexin.commonlib.utils;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * author: zengven
 * date: 2018/8/4 12:27
 * desc: 屏幕适配
 */
public class DensityUtil {

    private static float sOriginalDensity;
    private static float sOriginalScaledDensity;

    public static void setCustomDensity(@NonNull final Application application) {
        DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        Logger.e("widthPixels: " + appDisplayMetrics.widthPixels);
        Logger.e("density : " + appDisplayMetrics.density + "  densityDpi: " + appDisplayMetrics.densityDpi + "  scaledDensity: " + appDisplayMetrics.scaledDensity);
        if (sOriginalDensity == 0) {
            sOriginalDensity = appDisplayMetrics.density;
            sOriginalScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sOriginalScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels / 375f; //375是dp,屏幕的dp总宽度
        final int targetDensityDpi = (int) (targetDensity * 160); //
        final float targetScaledDensity = targetDensity * (sOriginalScaledDensity / sOriginalDensity);
        Logger.e("targetDensity: " + targetDensity + " targetDensityDpi: " + targetDensityDpi + " targetScaledDensity: " + targetScaledDensity);

        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
    }
}
