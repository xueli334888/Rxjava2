package com.simin.rxjava2.http.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Fengsimin on 2017/12/18 13:49
 * <p>
 * 多文件上传时用
 */

public class FileBody {

    String path;
    Map<String, String> param;

    public FileBody(String path, Map<String, String> param) {
        this.path = path;
        this.param = param;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
        this.param = param;
    }
}
