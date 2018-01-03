package com.simin.rxjava2.act;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.simin.rxjava2.R;
import com.simin.rxjava2.http.DefautObserver;
import com.simin.rxjava2.http.HttpApiHelper;
import com.simin.rxjava2.http.model.User;
import com.simin.rxjava2.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 作者：Fengsimin on 2017/12/11 15:22
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    protected void login() {
        Map<String, String> params = new HashMap<>();
        params.put("USERMOBILE", "13000000000");
        params.put("PASSWORD", "abcd");
        params.put("REGISTERID", "1a0018970a91d97b8c5");
        HttpApiHelper.getHelper(this).login(params, new DefautObserver<User>(null) {

            @Override
            public void onDataBack(User data) {
                //todo 登录后保存Token
                ToastUtil.show("登录成功");
                textView.setText("登录成功");
            }

        });
    }
}
