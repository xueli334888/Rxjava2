package com.simin.rxjava2.http;

import com.alibaba.fastjson.JSON;
import com.simin.rxjava2.act.BaseActivity;
import com.simin.rxjava2.http.entity.Response;
import com.simin.rxjava2.http.entity.Header;
import com.simin.rxjava2.http.entity.Keys;
import com.simin.rxjava2.http.model.FileBody;
import com.simin.rxjava2.http.model.Image;
import com.simin.rxjava2.http.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 作者：Fengsimin on 2017/12/13 13:13
 */

public class HttpApiHelper {


    /**
     * ------------------------------------接口调用start---------------------------------------
     */

    public void login(Map<String, String> params, DefautObserver<User> observer) {
        HttpApi.getInstance().apiService
                .login(checkParams(params, null, null))
                .compose(activity.<Response<User>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void uploadFile(String url, String filePath, Map<String, String> params, DefautObserver<Image> observer) {
        File file = new File(filePath);
        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), imageBody);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        HttpApi.getInstance().apiService
                .uploadFile(url, parts)
                .compose(activity.<Response<Image>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void uploadFiles(String url, List<String> files, Map<String, String> params, DefautObserver<Image> observer) {
        Map<String, RequestBody> bodys = new HashMap<>();
        for (String path : files) {
            File f = new File(path);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), f);
            bodys.put("file", requestBody);
        }
        for (Map.Entry<String, String> entry : params.entrySet()) {
            bodys.put(entry.getKey(), RequestBody.create(null, entry.getValue()));
        }
        HttpApi.getInstance().apiService
                .uploadFiles(url, bodys)
                .compose(activity.<Response<Image>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * ------------------------------------接口调用end---------------------------------------
     */

    private Map<String, String> checkParams(Map<String, String> params, List list, Map<String, String> pageParams) {
        if (params == null) {
            params = new HashMap<>();
        }

        if (list == null)
            list = new ArrayList();

        Map<String, String> requestParams = new HashMap<>();
        try {
            String header = new String(JSON.toJSONString(new Header()).getBytes(), "utf-8");
            String input = new String(JSON.toJSONString(params).getBytes(), "utf-8");
            String inputlist = new String(JSON.toJSONString(list).getBytes(), "utf-8");

            requestParams.put("header", header);
            requestParams.put("input", input);
            requestParams.put("inputlist", inputlist);

            if (pageParams != null) {
                String page = new String(JSON.toJSONString(pageParams).getBytes(), "utf-8");
                String keys = new String(JSON.toJSONString(new Keys(input, inputlist, page)).getBytes(), "utf-8");
                requestParams.put("page", page);
                requestParams.put("keys", keys);
            } else {
                String keys = new String(JSON.toJSONString(new Keys(input, inputlist, null)).getBytes(), "utf-8");
                requestParams.put("keys", keys);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //retrofit的params的值不能为null，此处做下校验，防止出错
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (entry.getValue() == null) {
                requestParams.put(entry.getKey(), "");
            }
        }
        return requestParams;
    }

    private static HttpApiHelper helper;
    private BaseActivity activity;

    public static HttpApiHelper getHelper(BaseActivity activity) {
        if (helper == null) {
            synchronized (HttpApiHelper.class) {
                if (helper == null)
                    helper = new HttpApiHelper(activity);
                else helper.setActivity(activity);
            }
        } else {
            helper.setActivity(activity);
        }
        return helper;
    }

    public HttpApiHelper(BaseActivity activity) {
        this.activity = activity;
    }

    public void setActivity(BaseActivity activity) {
        this.activity = activity;
    }
}
