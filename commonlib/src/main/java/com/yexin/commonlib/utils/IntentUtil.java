package com.yexin.commonlib.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.io.Serializable;
import java.util.List;

/**
 * author: zengven
 * date: 2018/3/22
 * Desc:
 */
public class IntentUtil {

    public static String getStringExtra(Intent intent, String name) {
        if (intent != null && intent.hasExtra(name)) {
            return intent.getStringExtra(name);
        } else {
            return "";
        }
    }

    public static Double getDoubleExtra(Intent intent, String name) {
        if (intent != null && intent.hasExtra(name)) {
            return intent.getDoubleExtra(name, 0);
        } else {
            return 0d;
        }
    }

    public static int getIntExtra(Intent intent, String name) {
        if (intent != null && intent.hasExtra(name)) {
            return intent.getIntExtra(name, 0);
        } else {
            return 0;
        }
    }

    public static boolean getBooleanExtra(Intent intent, String name) {
        if (intent != null && intent.hasExtra(name)) {
            return intent.getBooleanExtra(name, false);
        } else {
            return false;
        }
    }

    public static Serializable getSerializableExtra(Intent intent, String name) {
        if (intent != null && intent.hasExtra(name)) {
            return intent.getSerializableExtra(name);
        } else {
            return null;
        }
    }

    /**
     * 判断隐式intent是否可用
     *
     * @param context
     * @param intent
     * @return
     */
    public static boolean isAvailable(Context context, Intent intent) {
        if (intent == null || context == null)
            return false;
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> activities = pm.queryIntentActivities(intent, 0);
        return activities.size() > 0;
    }
}
