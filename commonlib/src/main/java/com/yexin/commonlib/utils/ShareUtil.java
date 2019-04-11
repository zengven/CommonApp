package com.yexin.commonlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

import java.io.File;

/**
 * author: zengven
 * date: 2019/1/23 14:27
 * desc: 分享工具类
 */

public class ShareUtil {

    private ShareUtil() {
    }

    public static void shareText(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, "Hello word from share ");
        intent.setType("text/plain");
        context.startActivity(Intent.createChooser(intent, "share to..."));
    }

    /**
     * 分享文件
     *
     * @param context
     * @param path
     */
    public static void shareFile(Context context, String path) {
        if (TextUtils.isEmpty(path)) {
            return;
        }
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        Uri uri = UriUtil.getFileUri(context, file);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        String mimeType = UriUtil.getMIMEType(context, path);
        intent.setType(mimeType);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(Intent.createChooser(intent, "分享"));
    }
}
