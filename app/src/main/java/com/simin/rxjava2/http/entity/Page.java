package com.simin.rxjava2.http.entity;

import com.simin.rxjava2.cons.Constant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：Fengsimin on 2016/11/14 10:07
 */
public class Page implements Serializable {

    private static final long serialVersionUID = -2654425717222496743L;

    public int CURR_PAGE_NUM;
    public int PER_PAGE_SIZE = Constant.Normal.PER_PAGE_SIZE;
    public int PAGE_COUNT;
    public int RECORD_COUNT;

    public Page(){}

    public Page(int currPage) {
        CURR_PAGE_NUM = currPage;
    }

    public Page(int CURR_PAGE_NUM, int PER_PAGE_SIZE) {
        this.CURR_PAGE_NUM = CURR_PAGE_NUM;
        this.PER_PAGE_SIZE = PER_PAGE_SIZE;
    }

    public Map<String, String> toMap() {
        Map<String, String> param = new HashMap<>();
        param.put("CURR_PAGE_NUM", String.valueOf(CURR_PAGE_NUM));
        param.put("PER_PAGE_SIZE", String.valueOf(PER_PAGE_SIZE));
        return param;
    }
}
