package com.simin.rxjava2.http.entity;

import com.alibaba.fastjson.JSON;
import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.http.encrypt.ApiEncrypt;
import com.simin.rxjava2.utils.LogUtil;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * "keys":{"TIMESTAMP":"xxx","SIGN":"xxx","TOKENCODE":"xxx"}
 */
public class Keys<T> implements Serializable {

    private static final long serialVersionUID = -562005098855409569L;

    public String TIMESTAMP = System.currentTimeMillis() + "";
    public String SIGN = "SIGN";
    public String TOKENCODE = BaseApplication.getSelf().getTokenCode();

    public Keys(String input, String inputlist, String page) {
        Map<String, Object> param = new HashMap<>();
        param.put("input", input);
        if (inputlist != null) {
            param.put("inputlist", inputlist);
        }
        if (page != null) {
            param.put("page", page);
        }
        SIGN = ApiEncrypt.sign(param);

    }

    public Keys(T input, List<T> inputlist, Page page) {
        try {
            String inputStr = new String(JSON.toJSONString(input).getBytes(), "utf-8");
            String inputlistStr = new String(JSON.toJSONString(inputlist).getBytes(), "utf-8");
            String pageStr = new String(JSON.toJSONString(page).getBytes(), "utf-8");
            SIGN = new String(JSON.toJSONString(new Keys(inputStr, inputlistStr, pageStr)).getBytes(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.e(e.getMessage());
        }
    }
}
