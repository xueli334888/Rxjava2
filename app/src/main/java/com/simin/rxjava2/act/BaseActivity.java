package com.simin.rxjava2.act;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.ViewStub;

import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Fengsimin on 2017/12/11 15:16
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    BaseApplication app;

    ViewStub stub;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = BaseApplication.getSelf();
        setContentView();
    }

    protected void setContentView() {
        setContentView(R.layout.base_activity_layout);
        setContentRes();
        initView();
        initData();
    }

    protected void setContentRes() {
        stub = findViewById(R.id.stub);
        stub.setLayoutResource(getLayoutId());
        stub.inflate();
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected abstract @LayoutRes
    int getLayoutId();
}
