package com.simin.rxjava2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.simin.rxjava2.BaseApplication;

/**
 * 作者：Fengsimin on 2017/12/11 17:34
 */

public class NetUtil {

    /**
     * 描述：判断网络是否有效.
     *
     * @return true, if is network available
     */
    public static boolean isConnected() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) BaseApplication.getSelf().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        LogUtil.d("连网了连网了");
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
