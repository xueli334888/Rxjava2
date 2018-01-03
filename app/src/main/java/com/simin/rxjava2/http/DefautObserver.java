package com.simin.rxjava2.http;

import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.R;
import com.simin.rxjava2.http.entity.Response;
import com.simin.rxjava2.http.entity.Page;
import com.simin.rxjava2.interfaces.DialogI;
import com.simin.rxjava2.utils.LogUtil;
import com.simin.rxjava2.utils.ToastUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 作者：Fengsimin on 2017/12/11 18:25
 */

public class DefautObserver<T> implements Observer<Response<T>> {

    DialogI progress;

    public DefautObserver(DialogI progress) {
        this.progress = progress;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Response<T> response) {

        if (progress != null)
            progress.dismiss();
        switch (response.getRtn().rtncode) {
            case HTTP_SUCCESS:
                onSuccess();
                onDataBack(response.getData());
                onDataListBack(response.getDatalist(), response.getPage());
                break;
            case TOKEN_OUTTIME://token错误重新请求或重新登录
                onTokenInconnect();
                break;
            default:
                onFail(response);
                break;
        }
    }

    public void onTokenInconnect() {
        BaseApplication.getSelf().setLogin(false);
        // TODO: 2017/12/13 跳转登录页
    }

    private void refreshToken() {
        // TODO: 2017/12/13 刷新token
    }

    public void onTokenUpdateSuccess() {
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("Retrofit", e.toString());

        if (e instanceof HttpException) {     //   HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(int reason) {
        switch (reason) {
            case CONNECT_ERROR:
                ToastUtil.show(R.string.connect_error);
                break;

            case CONNECT_TIMEOUT:
                ToastUtil.show(R.string.connect_timeout);
                break;

            case BAD_NETWORK:
                ToastUtil.show(R.string.bad_network);
                break;

            case PARSE_ERROR:
                ToastUtil.show(R.string.parse_error);
                break;

            case UNKNOWN_ERROR:
            default:
                ToastUtil.show(R.string.unknown_error);
                break;
        }
    }

    @Override
    public void onComplete() {

    }

    public void onDataBack(T data) {

    }

    public void onDataListBack(List<T> data, Page page) {

    }

    public void onSuccess() {

    }

    protected void onFail(Response response) {
        String message = response.getRtn().rtnmsg;
        if (TextUtils.isEmpty(message)) {
            ToastUtil.show("response_return_error");
        } else {
            ToastUtil.show(message);
        }
    }

    final static int BAD_NETWORK = 0x1;
    final static int CONNECT_ERROR = 0x2;
    final static int CONNECT_TIMEOUT = 0x3;
    final static int PARSE_ERROR = 0x4;
    final static int UNKNOWN_ERROR = 0x5;

    final static String HTTP_SUCCESS = "00000";
    final static String TOKEN_OUTTIME = "2001";
}
