package com.simin.rxjava2.utils;

import android.widget.Toast;

import com.simin.rxjava2.BaseApplication;

/**
 * 作者：Fengsimin on 2017/12/12 13:21
 */

public class ToastUtil {

    public static void show(String msg) {
        Toast.makeText(BaseApplication.getSelf(), msg, Toast.LENGTH_LONG).show();
    }

    public static void show(int msgId) {
        Toast.makeText(BaseApplication.getSelf(), msgId, Toast.LENGTH_LONG).show();
    }
}
