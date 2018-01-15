package com.simin.rxjava2.http;

import com.simin.rxjava2.http.entity.Response;
import com.simin.rxjava2.http.model.Image;
import com.simin.rxjava2.http.model.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * 作者：Fengsimin on 2017/12/11 16:31
 */

public interface HttpApiService {

    @FormUrlEncoded
    @POST("TSnsyhZYDAppApi/personalDatum/login")
    Observable<Response<User>> login(@FieldMap Map<String, String> param);

    @Multipart
    @POST()
    Observable<Response<String>> uploadFile(@Url String url, @Part List<MultipartBody.Part> partList);

    @Multipart
    @POST()
    Observable<Response<String>> uploadFiles(@Url String url, @PartMap() Map<String, RequestBody> maps);

    @POST()
    Call<ResponseBody> downloadFile(@Url String url);
}
