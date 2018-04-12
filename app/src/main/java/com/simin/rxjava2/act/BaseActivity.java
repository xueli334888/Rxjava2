package com.simin.rxjava2.act;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.simin.rxjava2.BaseApplication;
import com.simin.rxjava2.R;
import com.simin.rxjava2.utils.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：Fengsimin on 2017/12/11 15:16
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    BaseApplication app;

    Toolbar toolbar;
    TextView tv_title;
    TextView tv_left;
    TextView tv_right;
    ImageView iv_right;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = BaseApplication.getSelf();
        setContentView();
    }

    protected void setContentView() {
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initTitleBar();
        initView();
        initData();
    }

    protected void initTitleBar() {
        toolbar = findViewById(R.id.toolbar);
        tv_title = findViewById(R.id.tv_title);
        tv_left = findViewById(R.id.tv_left);
        tv_right = findViewById(R.id.tv_right);
        iv_right = findViewById(R.id.iv_right);

        if (toolbar == null)
            return;

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLeft();
            }
        });

        View.OnClickListener rightClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRight();
            }
        };
        tv_right.setOnClickListener(rightClick);
        iv_right.setOnClickListener(rightClick);
    }

    protected void initView() {
        StatusBarUtil.StatusBarLightMode(this);
    }

    protected void initData() {
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected void setTitleName(@StringRes int resId) {
        tv_title.setText(resId);
    }

    protected void setTitleName(String resValue) {
        tv_title.setText(resValue);
    }

    protected void setLeftText(@StringRes int resId) {
        tv_left.setVisibility(View.VISIBLE);
        tv_left.setText(resId);
    }

    protected void setRightText(@StringRes int resId) {
        tv_right.setVisibility(View.VISIBLE);
        iv_right.setVisibility(View.GONE);
        tv_right.setText(resId);
    }

    protected void setRightRes(@DrawableRes int resId) {
        tv_right.setVisibility(View.GONE);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(resId);
    }

    protected void onClickLeft() {
        finish();
    }

    protected void onClickRight() {

    }
}
