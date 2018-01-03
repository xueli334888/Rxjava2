package com.simin.rxjava2.utils;

import android.content.Context;
import android.os.Environment;

import com.simin.rxjava2.cons.Constant;

import java.io.File;
import java.io.IOException;

/**
 * 获取各种文件路径
 * 删除文件
 */
public class FileUtil {

    /**
     * 获取根路径
     *
     * @param context
     * @return
     */
    public static File getAppDir(Context context) {
        String dirPath = "";
        //SD卡是否存在
        boolean isSdCardExists = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        boolean isRootDirExists = Environment.getExternalStorageDirectory().exists();
        if (isSdCardExists && isRootDirExists) {
            dirPath = String.format("%s/%s/", Environment.getExternalStorageDirectory().getAbsolutePath(), Constant.FilePath.ROOT_PATH);
        } else {
            dirPath = String.format("%s/%s/", context.getFilesDir().getAbsolutePath(), Constant.FilePath.ROOT_PATH);
        }

        File appDir = new File(dirPath);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        return appDir;
    }

    //获取录音存放路径
    public static File getAppAudioRecordDir(Context context) {
        File appDir = getAppDir(context);
        File recordDir = new File(appDir.getAbsolutePath(), Constant.FilePath.RECORD_DIR);
        if (!recordDir.exists()) {
            recordDir.mkdir();
        }
        return recordDir;
    }

    //获取录像存放路径
    public static File getAppVedioRecordDir(Context context) {
        File appDir = getAppDir(context);
        File recordDir = new File(appDir.getAbsolutePath(), Constant.FilePath.VEDIO_DIR);
        if (!recordDir.exists()) {
            recordDir.mkdir();
        }
        return recordDir;
    }

    //获取图片存放路径
    public static File getAppImageDir(Context context) {
        File appDir = getAppDir(context);
        File dir = new File(appDir, Constant.FilePath.IMAGE_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public static File getTempImageFile(Context context) {
        File tempImageFile = new File(getAppImageDir(context), "tempImage.jpg");
        try {
            if (!tempImageFile.exists()) {
                tempImageFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempImageFile;
    }

    public static File getAppImageFile(Context context, String fileName) {
        File tempImageFile = new File(getAppImageDir(context), fileName);
        try {
            if (!tempImageFile.exists()) {
                tempImageFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempImageFile;
    }

    public static File getAppDbDir(Context context) {
        File appDir = getAppDir(context);
        File dir = new File(appDir, Constant.FilePath.DB_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public static File getApkFile(Context context) {
        File appDir = getAppDir(context);
        File dir = new File(appDir.getAbsolutePath(), Constant.DownLoad.DOWNLOAD_FILENAME);
        try {
            if (!dir.exists()) {
                dir.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dir;
    }

    public static File getFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists())
                return file.delete();
        } catch (NullPointerException e) {
            return false;
        } finally {
            return false;
        }
    }

    public static boolean deleteFile(File file) {
        try {
            if (file.exists())
                return file.delete();
        } catch (NullPointerException e) {
            return false;
        } finally {
            return false;
        }
    }
}
