package com.simin.rxjava2.utils;

import android.util.Log;

import com.simin.rxjava2.cons.SystemConfig;

/**
 * 控制log输出
 */
public class LogUtil {

    public static boolean isOpenDebugLog = SystemConfig.DEBUG;

    public final static String TAG = "XYDLog";

    public static void d(String log) {
        if (isOpenDebugLog) {
            Log.d(TAG, log);
        }
    }

    public static void d(String tag, String log) {
        if (isOpenDebugLog) {
            Log.d(tag, log);
        }
    }

    public static void sd(String log) {
        if (isOpenDebugLog) {
            System.out.println("Debug Level: " + log);
        }
    }

    public static void w(String log) {
        if (isOpenDebugLog) {
            Log.w(TAG, log);
        }
    }

    public static void w(String tag, String log) {
        if (isOpenDebugLog) {
            Log.w(tag, log);
        }
    }

    public static void sw(String log) {
        if (isOpenDebugLog) {
            System.out.println("Warn Level: " + log);
        }
    }

    public static void e(String log, Throwable t) {
        if (isOpenDebugLog) {
            Log.e(TAG, log, t);
        }
    }

    public static void e(String tag, String log, Throwable t) {
        if (isOpenDebugLog) {
            Log.e(tag, log, t);
        }
    }

    public static void e(String log) {
        if (isOpenDebugLog) {
            Log.e(TAG, log);
        }
    }

    public static void se(String log) {
        if (isOpenDebugLog) {
            System.out.println("Error Level: " + log);
        }
    }

    public static void e(String tag, String log) {
        if (isOpenDebugLog) {
            Log.e(tag, log);
        }
    }

    public static void i(String tag, String log) {
        if (isOpenDebugLog) {
            Log.i(tag, log);
        }

    }

}
