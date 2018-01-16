package com.simin.rxjava2.act.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.simin.rxjava2.R;

import butterknife.BindView;

/**
 * 作者：Fengsimin on 2018/1/16 14:28
 */

public class FragmentB extends BaseFragment {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_x;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        textView.setText("FragmentB");
    }

}
