package com.simin.rxjava2.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.cons.SystemConfig;
import com.simin.rxjava2.utils.LogUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：Fengsimin on 2017/12/11 16:16
 */

public class HttpApi {

    public HttpApiService apiService;
    private final long DEFAULT_TIMEOUT = 20;

    private HttpApi() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    LogUtil.d(text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    LogUtil.d(message);
                }
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        File cacheFile = new File(BaseApplication.getSelf().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                //.addNetworkInterceptor(new HttpCacheInterceptor())
                //.cache(cache)
                .build();


        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(SystemConfig.getBaseServiceUrl())
                .build();
        apiService = retrofit.create(HttpApiService.class);
    }

    private static volatile HttpApi httpApi;

    public static HttpApi getInstance() {
        synchronized (HttpApi.class) {
            if (httpApi == null) {
                httpApi = new HttpApi();
            }
        }
        return httpApi;
    }
}
