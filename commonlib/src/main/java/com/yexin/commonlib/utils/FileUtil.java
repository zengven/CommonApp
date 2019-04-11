package com.yexin.commonlib.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * author: zengven
 * date: 2017/8/3 14:57
 * desc: 文件工具类
 */
public class FileUtil {

    public static final String EXTERNAL_ROOT_PATH = Environment.getExternalStorageDirectory().getPath();
    public static final String FLODER_PATH = "BingGallery";
    public static final String EXTERNAL_PATH = EXTERNAL_ROOT_PATH + File.separator + FLODER_PATH;


    /**
     * 文件扩展后缀
     */
    public static class FileSuffix {
        public static final String LOG = ".log";
        public static final String TXT = ".txt";
        public static final String JAVA = ".java";
        public static final String APK = ".apk";
        public static final String JPG = ".jpg";
    }

    /**
     * 当前文件所在的文件夹
     */
    public static class Folder {
        public static final String FILE = "file";
        public static final String IMAGE = "image";
        public static final String LOG = "log";
        public static final String PUSH = "push";
        public static final String APK = "apk";
    }

    /**
     * create image file
     *
     * @param context
     * @return
     */
    public static File createImgFile(Context context) {
        String state = Environment.getExternalStorageState();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 已挂载
            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//            File camera = new File(pic, "camera");
            if (!pic.exists()) {
                if (!pic.mkdirs()) {
                    return null;
                }
            }
            return new File(pic, File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            File cacheDir = context.getCacheDir();
            return new File(cacheDir, File.separator + "IMG_" + timeStamp + ".jpg");
        }
    }

    /**
     * 保存文件,如果当前文件存在,则覆盖当前文件内容
     *
     * @param content  需要保存的内容
     * @param fileName {@link FileSuffix} 可以自定义文件扩展名,若不指定,默认文件扩展名为txt
     * @param folder   {@link Folder} 指定文件夹,可以不指定
     */
    public static void writeText(String content, String fileName, String folder) {
        saveText(content, fileName, folder, false);
    }

    /**
     * 如文件不存在,创建并保存,如存在,则在末尾添加
     *
     * @param content  需要保存的内容
     * @param fileName {@link FileSuffix} 可以自定义文件扩展名,若不指定,默认文件扩展名为txt
     * @param folder   {@link Folder} 指定文件夹,可以不指定
     */
    public static void appendText(String content, String fileName, String folder) {
        saveText(content, fileName, folder, true);
    }

    /**
     * 如文件不存在,创建并保存,如存在,则在末尾添加
     *
     * @param content  需要保存的内容
     * @param fileName {@link FileSuffix} 可以自定义文件扩展名,若不指定,默认文件扩展名为txt
     * @param folder   {@link Folder} 指定文件夹,可以不指定
     * @param append   是否在文件末尾追加写入
     */
    public static void saveText(String content, String fileName, String folder, boolean append) {
        String state = Environment.getExternalStorageState();
        if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {
            File parentFile = new File(EXTERNAL_PATH);
            File dirFile = null;
            if (!TextUtils.isEmpty(folder)) {
                dirFile = new File(parentFile, folder);
            } else {
                dirFile = parentFile;
            }
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            if (TextUtils.isEmpty(fileName)) {
                throw new IllegalArgumentException("you must input file name ");
            }
            File file = null;
            if (fileName.contains(".")) {
                file = new File(dirFile, fileName);
            } else {
                file = new File(dirFile, fileName + FileSuffix.TXT);
            }
            BufferedWriter bw = null;
            try {
                FileWriter fw = new FileWriter(file, append);
                bw = new BufferedWriter(fw);
                bw.write(content);
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建外部存储目标文件
     *
     * @param folder   子文件夹名{@link Folder},未输入则保存在一级目录BingGallery下
     * @param fileName 文件名{@link FileSuffix}
     */
    public static File createExternalFile(String folder, String fileName) {
        String state = Environment.getExternalStorageState();
        if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {
            File parentFile = new File(FileUtil.EXTERNAL_PATH);
            File dirFile = null;
            if (!TextUtils.isEmpty(folder)) {
                dirFile = new File(parentFile, folder);
            } else {
                dirFile = parentFile;
            }
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            if (TextUtils.isEmpty(fileName)) {
                throw new IllegalArgumentException("you must input file name ");
            }
            File file = new File(dirFile, fileName);
            return file;
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param inputStream 需要保存的输入流
     * @param fileName    {@link FileSuffix} 必须指定扩展名
     * @param folder      {@link Folder} 指定文件夹,可以不指定
     * @return file       返回当前保存的file
     */
    public static File saveFile(InputStream inputStream, String fileName, String folder) {
        return saveFile(inputStream, fileName, folder, null);
    }

    /**
     * 保存文件
     *
     * @param inputStream 需要保存的输入流
     * @param fileName    {@link FileSuffix} 必须指定扩展名
     * @param folder      {@link Folder} 指定文件夹,可以不指定
     * @param callback    下载结果回调
     * @return file       返回当前保存的file
     */
    public static File saveFile(InputStream inputStream, String fileName, String folder, Callback callback) {
        String state = Environment.getExternalStorageState();
        if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {
            File parentFile = new File(EXTERNAL_PATH);
            File dirFile = null;
            if (!TextUtils.isEmpty(folder)) {
                dirFile = new File(parentFile, folder);
            } else {
                dirFile = parentFile;
            }
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            if (TextUtils.isEmpty(fileName)) {
                throw new IllegalArgumentException("you must input file name ");
            }
            File file = new File(dirFile, fileName);
            return saveFile(inputStream, file, callback);
        }
        return null;
    }

    /**
     * 保存文件
     *
     * @param inputStream 需要保存的输入流
     * @param file        保存本地File
     * @param callback    保存回调
     * @return
     */
    public static File saveFile(InputStream inputStream, File file, Callback callback) {
        if (file.exists()) {
            file.delete();
        }
        BufferedOutputStream bos = null;
        byte[] buffer = new byte[1024];
        int length = 0;
        int currentLength = 0;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            while ((length = inputStream.read(buffer)) != -1) { //-1,输入流已经读完
                bos.write(buffer, 0, length);
                bos.flush();
                if (callback != null) {
                    callback.onProgress(currentLength += length);
                }
            }
            if (callback != null) {
                callback.onSuccess(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (callback != null) {
                callback.onFailure(e);
            }
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 删除指定文件夹下指定时间段外所有文件
     *
     * @param context
     * @param folderPath
     * @param timeSlot   ms
     */
    public static void deleteFile(Context context, String folderPath, long timeSlot) {
        String state = Environment.getExternalStorageState();
        if (!TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {
            return;
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + folderPath;
        Logger.e(" path : " + path);
        File rootFile = new File(path);
        File[] files = rootFile.listFiles();
        if (files == null) {
            return;
        }
        if (files.length > 0) {
            for (File file : files) {
                long lastModified = file.lastModified();
                if (System.currentTimeMillis() - lastModified > timeSlot) {
                    deleteFile(file);
                }
            }
        } else {
            rootFile.delete();
        }
    }

    /**
     * 删除文件或文件夹下所有文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file == null) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File childFile : files) {
                    deleteFile(childFile);
                }
            }
            file.delete();
        } else {
            file.delete();
        }
    }

    /**
     * 下载回调接口
     */
    public interface Callback {
        void onFailure(Exception e);

        void onSuccess(File file);

        void onProgress(long currentLength);
    }

    /**
     * 文件大小转换
     *
     * @param size
     */
    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1fGB", (float) (size / gb));
        } else if (size >= mb) {
            float f = (float) (size / mb);
            return String.format(f > 100 ? "%.0fMB" : "%.1fMB", (float) (size / mb));
        } else if (size >= kb) {
            float f = (float) (size / kb);
            return String.format(f > 100 ? "%.0fKB" : "%.1fKB", (float) (size / kb));
        } else {
            return String.format("%d B", size);
        }
    }
}
