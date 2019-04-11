package com.yexin.commonlib.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.webkit.MimeTypeMap;

import java.io.File;

/**
 * author: zengven
 * date: 2019/1/23 15:59
 * desc: uri 工具类
 */

public class UriUtil {

    private UriUtil() {
    }

    /**
     * 获取本地文件uri
     *
     * @param context
     * @param file
     */
    public static Uri getFileUri(Context context, File file) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //见AndroidManifest Provider authorities
            uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 获取mime type ,不稳定
     *
     * @param context
     * @param url
     * @return
     */
    public static String getMIMEType(Context context, String url) {
        if (url == null)
            return "";
        String fileExt = MimeTypeMap.getFileExtensionFromUrl(url);//文件扩展名
        String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExt);
        Logger.e("mime type: " + mimeType);
        return mimeType;
    }
}
