package com.simin.rxjava2.act;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simin.rxjava2.R;
import com.simin.rxjava2.act.fragment.FragmentA;
import com.simin.rxjava2.act.fragment.FragmentB;
import com.simin.rxjava2.act.fragment.FragmentC;
import com.simin.rxjava2.act.fragment.FragmentD;
import com.simin.rxjava2.http.DefautObserver;
import com.simin.rxjava2.http.HttpApiHelper;
import com.simin.rxjava2.http.model.User;
import com.simin.rxjava2.http.progress.DownloadProgressHandler;
import com.simin.rxjava2.interfaces.CallBack;
import com.simin.rxjava2.utils.LogUtil;
import com.simin.rxjava2.utils.PermissionsChecker;
import com.simin.rxjava2.utils.ToastUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import sakura.bottomtabbar.BottomTabBar;

/**
 * 作者：Fengsimin on 2017/12/11 15:22
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.download)
    Button download;
    @BindView(R.id.loadProgress)
    TextView loadProgress;

    @BindView(R.id.BottomTabBar)
    BottomTabBar bottomTabBar;

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        setTitleName("主页");
        setRightText(R.string.more);


        new PermissionsChecker(this).lacsPermissions();
        bottomTabBar.initFragmentorViewPager(getSupportFragmentManager())
                .addReplaceLayout(R.id.contentPanel)
                .addTabItem("草莓", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher_round), FragmentA.class)
                .addTabItem("凤梨", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher_round), FragmentB.class)
                .addTabItem("樱桃", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher_round), FragmentC.class)
                .addTabItem("香蕉", getResources().getDrawable(R.mipmap.ic_launcher), getResources().getDrawable(R.mipmap.ic_launcher_round), FragmentD.class)
                .commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ProgressHelper.setProgressHandler(handler);
                login();
            }

        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "FILE/APP/ZYDMobile.apk";
                HttpApiHelper.getHelper(MainActivity.this).downloadFile(url, handler, new CallBack.Stub<File>() {
                    @Override
                    public void onSuccess(File file) {

                    }
                });
            }
        });
    }

    @Override
    protected void initData() {
        mPermissionsChecker = new PermissionsChecker(this);
        // 缺少权限时, 进入权限配置页面
        if (!mPermissionsChecker.lacksPermissions()) {
            PermissionsActivity.startActivityForResult(this, 101, PermissionsChecker.permissions);
        }
    }

    protected void login() {
        Map<String, String> params = new HashMap<>();
        params.put("USERMOBILE", "15210934587");
        params.put("PASSWORD", "1");
        params.put("REGISTERID", "1a0018970a91d97b8c5");
        HttpApiHelper.getHelper(this).login(params, new DefautObserver<User>(null) {
            @Override
            public void onDataBack(User data) {
                LogUtil.d(data.toString());
            }
        });
    }

    DownloadProgressHandler handler = new DownloadProgressHandler() {
        @Override
        protected void onProgress(long progress, long total, boolean done) {
            int currentPro = (int) ((100 * progress) / total);
            loadProgress.setText(String.valueOf(currentPro));
            if (done) {
                ToastUtil.show("下载完成！");
                loadProgress.setText("100");
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == 101 && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }
    }
}
