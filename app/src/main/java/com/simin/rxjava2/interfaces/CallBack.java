package com.simin.rxjava2.interfaces;

import java.io.File;

import retrofit2.Call;

/**
 * 作者：Fengsimin on 2018/1/13 16:44
 */

public interface CallBack<T> {

    void onSuccess(File file);

    void onFailure(Throwable t);

    abstract class Stub<T> implements CallBack<T> {
        @Override
        public void onSuccess(File file) {

        }

        @Override
        public void onFailure(Throwable t) {

        }
    }
}
