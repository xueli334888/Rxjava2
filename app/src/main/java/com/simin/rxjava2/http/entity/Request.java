package com.simin.rxjava2.http.entity;

import java.util.List;

/**
 * 作者：Fengsimin on 2017/12/12 18:17
 */

public class Request<T> {

    private Header header = new Header();
    private Keys keys;
    private T input;
    private List<T> inputlist;
    private Page page;

    public Request(T input) {
        this.input = input;
        setKeys(null);
    }

    public Request(T input, Page page) {
        this.input = input;
        this.page = page;
        setKeys(null);
    }

    public Request(List<T> inputlist) {
        this.inputlist = inputlist;
        setKeys(null);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Keys getKeys() {
        return keys;
    }

    public void setKeys(Keys keys) {
        keys = new Keys(input, inputlist, page);
    }

    public T getInput() {
        return input;
    }

    public void setInput(T input) {
        this.input = input;
    }

    public List<T> getInputlist() {
        return inputlist;
    }

    public void setInputlist(List<T> inputlist) {
        this.inputlist = inputlist;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
