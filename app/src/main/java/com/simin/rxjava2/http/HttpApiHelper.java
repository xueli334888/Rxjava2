package com.simin.rxjava2.http;

import com.alibaba.fastjson.JSON;
import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.act.BaseActivity;
import com.simin.rxjava2.http.entity.Response;
import com.simin.rxjava2.http.entity.Header;
import com.simin.rxjava2.http.entity.Keys;
import com.simin.rxjava2.http.progress.DownloadProgressHandler;
import com.simin.rxjava2.http.progress.ProgressHelper;
import com.simin.rxjava2.interfaces.CallBack;
import com.simin.rxjava2.utils.FileUtil;
import com.simin.rxjava2.utils.LogUtil;
import com.simin.rxjava2.utils.UrlUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 作者：Fengsimin on 2017/12/13 13:13
 */

public class HttpApiHelper {


    /**
     * ------------------------------------接口调用start---------------------------------------
     */

    public void login(Map<String, String> params, DefautObserver observer) {
        HttpApi.getInstance().apiService
                .login(checkParams(params, null, null))
                .compose(activity.<Response>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void post(PostConfig config) {
        try {
            Method m = HttpApi.getInstance().apiService.getClass().getDeclaredMethod(config.method, Map.class);
            //ParameterizedType type = (ParameterizedType) config.t.getClass().getGenericSuperclass();
            Observable observable = (Observable) m.invoke(HttpApi.getInstance().apiService, config.params);
            observable.compose(activity.<Response>bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(config.observer);
        } catch (NoSuchMethodException e) {
            LogUtil.d(e.toString());
        } catch (IllegalAccessException e) {
            LogUtil.d(e.toString());
        } catch (InvocationTargetException e) {
            LogUtil.d(e.toString());
        }
    }

    public void uploadFile(String url, String filePath, Map<String, String> params, DefautObserver observer) {
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
                .compose(activity.<Response>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void uploadFiles(String url, List<String> files, Map<String, String> params, DefautObserver observer) {
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
                .compose(activity.<Response>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void downloadFile(String url, DownloadProgressHandler handler, final CallBack<File> callBack) {
        String fileName = UrlUtil.getFileName(url);
        final File file = FileUtil.getAppFile(BaseApplication.getSelf(), fileName);
        ProgressHelper.setProgressHandler(handler);
        Call<ResponseBody> mCommCall = HttpApi.getInstance().apiService.downloadFile(url);
        mCommCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                FileUtil.saveFile(response, file);
                if (callBack != null)
                    callBack.onSuccess(file);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callBack != null)
                    callBack.onFailure(t);
            }
        });
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

    private void setActivity(BaseActivity activity) {
        this.activity = activity;
    }
}
