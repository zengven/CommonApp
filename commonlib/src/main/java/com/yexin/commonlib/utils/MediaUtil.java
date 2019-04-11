package com.yexin.commonlib.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;

import com.yexin.commonlib.bean.MediaStoreImageBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * author: zengven
 * date: 2019/1/21 11:42
 * desc: 本地图片获取工具类
 */

public class MediaUtil {

    private MediaUtil() {
    }

    /**
     * 刷新media store
     *
     * @param context
     * @param paths
     * @param mimeTypes
     */
    public static void refreshMediaStore(Context context, String[] paths, String[] mimeTypes) {
        MediaScannerConnection.scanFile(context, paths, mimeTypes, new MediaScannerConnection.MediaScannerConnectionClient() {
            @Override
            public void onMediaScannerConnected() {

            }

            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });
    }

    /**
     * 获取media store 所有图片信息
     *
     * @param context
     */
    public static void getAllImageBucket(Context context) {
        // TODO: 2019/1/21
    }

    /**
     * 获取media store指定bucket name下所有文件信息
     *
     * @param context
     * @param bucketName 其实就是文件夹名
     */
    public static List<MediaStoreImageBean> getImageBucketByName(Context context, String bucketName) {
        ArrayList<MediaStoreImageBean> imageList = new ArrayList<>();
        ContentResolver contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{
                MediaStore.Images.Media.BUCKET_ID,  //文件所在目录id
                MediaStore.Images.Media.DATA,       //文件所在路径
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME,    //文件所在目录名称
                MediaStore.Images.Media.ORIENTATION,    //方向
                MediaStore.Images.Media.DATE_ADDED,     //加入mediastore时间
                MediaStore.Images.Media.DATE_MODIFIED,  //修改时间
                MediaStore.Images.Media.DATE_TAKEN,     //
                MediaStore.Images.Media.DESCRIPTION,    //描述
                MediaStore.Images.Media.PICASA_ID,      //
                MediaStore.Images.Media.IS_PRIVATE,     //是否是私人文件
                MediaStore.Images.Media.LATITUDE,       //拍照所在地纬度
                MediaStore.Images.Media.LONGITUDE,      //拍照所在地精度
                MediaStore.Images.Media.MINI_THUMB_MAGIC,
        };
        Cursor cursor = contentResolver.query(uri, projection, MediaStore.Images.Media.BUCKET_DISPLAY_NAME + "=?", new String[]{bucketName}, MediaStore.Images.Media.DATE_ADDED + " DESC"); //DESC降序排列,ASC升序排列
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                MediaStoreImageBean mediaStoreImageBean = new MediaStoreImageBean();
                mediaStoreImageBean.bucketId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_ID));
                mediaStoreImageBean.data = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                mediaStoreImageBean.bucketDisplayName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME));
                mediaStoreImageBean.orientation = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION));
                mediaStoreImageBean.dateAdded = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_ADDED));
                mediaStoreImageBean.dateModified = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_MODIFIED));
                mediaStoreImageBean.dateTaken = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media.DATE_TAKEN));
                mediaStoreImageBean.description = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DESCRIPTION));
                mediaStoreImageBean.picasaId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.PICASA_ID));
                mediaStoreImageBean.isPrivate = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.IS_PRIVATE));
                mediaStoreImageBean.latitude = cursor.getDouble(cursor.getColumnIndex(MediaStore.Images.Media.LATITUDE));
                mediaStoreImageBean.longitude = cursor.getDouble(cursor.getColumnIndex(MediaStore.Images.Media.LONGITUDE));
                mediaStoreImageBean.miniThumbMagic = cursor.getInt(cursor.getColumnIndex(MediaStore.Images.Media.MINI_THUMB_MAGIC));
                imageList.add(mediaStoreImageBean);
            }
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return imageList;
    }

    /**
     * 刪除media store文件
     *
     * @param context
     * @param file
     */
    public static void delete(Context context, File file) {
        if (file == null)
            return;
        context.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.DATA + "=?", new String[]{file.getAbsolutePath()});
    }
}
