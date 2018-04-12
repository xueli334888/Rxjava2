package com.simin.rxjava2.http;

import java.util.Map;

/**
 * 作者：Fengsimin on 2018/1/31 18:07
 */

public class PostConfig<T> {

    public String method;
    public Map<String, String> params;
    public T t;
    public DefautObserver observer;

    public PostConfig(String method, Map<String, String> params, T t, DefautObserver observer) {
        this.method = method;
        this.params = params;
        this.t = t;
        this.observer = observer;
    }
}
