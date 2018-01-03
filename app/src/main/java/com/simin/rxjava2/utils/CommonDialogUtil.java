package com.simin.rxjava2.utils;

import android.app.DialogFragment;
import android.content.Context;

import com.simin.rxjava2.R;
import com.simin.rxjava2.dialog.CustomProgressDialog;

/**
 * 作者：Fengsimin on 2017/12/12 14:12
 */

public class CommonDialogUtil {

    private CustomProgressDialog mProgressDialog;

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context, String msg) {
        if (mProgressDialog == null) {
            /*mProgressDialog = new CustomProgressDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .setMessage(msg)
                    .build();*/
        }
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context) {
        if (mProgressDialog == null) {
            /*mProgressDialog = new CustomProgressDialog.Builder(context)
                    .setTheme(R.style.ProgressDialogStyle)
                    .build();*/
        }
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
