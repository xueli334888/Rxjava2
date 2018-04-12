package com.simin.rxjava2.act.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.simin.rxjava2.R;
import com.simin.rxjava2.act.ZxingActivity;

import butterknife.BindView;

/**
 * 作者：Fengsimin on 2018/1/16 14:28
 */

public class FragmentA extends BaseFragment {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_x;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        textView.setText("FragmentA");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ZxingActivity.class));
            }
        });
    }

}
