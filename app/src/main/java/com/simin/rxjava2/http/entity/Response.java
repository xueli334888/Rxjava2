package com.simin.rxjava2.http.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：Fengsimin on 2017/12/11 17:46
 */

public class Response<T> {

    private T data;
    private List<T> datalist;
    private Page page;
    @SerializedName("return")
    private Return rtn;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDatalist() {
        return datalist;
    }

    public void setDatalist(List<T> datalist) {
        this.datalist = datalist;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Return getRtn() {
        return rtn;
    }

    public void setRtn(Return rtn) {
        this.rtn = rtn;
    }
}
