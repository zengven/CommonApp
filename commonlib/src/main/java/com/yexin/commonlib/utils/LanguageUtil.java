package com.yexin.commonlib.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import android.text.TextUtils;

import java.util.Locale;

/**
 * author: zengven
 * date: 2019/1/8 11:39
 * desc: 语言工具类
 */

public class LanguageUtil {

    private LanguageUtil() {
        throw new RuntimeException("don't touch me !!!");
    }

    /**
     * 获取当前应用语言
     *
     * @param context
     * @return
     */
    public static String getConfigLanguage(Context context) {
        Configuration configuration = context.getResources().getConfiguration();
        Locale locale = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            LocaleList locales = configuration.getLocales();
            locale = locales.get(0);
        } else {
            locale = configuration.locale;
        }
        if (locale == null) {
            return "";
        }
        return locale.getLanguage();
    }

    /**
     * 判断当前语言是否为简体中文
     *
     * @param context
     * @return
     */
    public static boolean isZH(Context context) {
        String language = getConfigLanguage(context);
        return TextUtils.equals(language, "zh");
    }
}
