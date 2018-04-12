package com.simin.rxjava2.act;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.simin.rxjava2.R;
import com.simin.rxjava2.utils.LogUtil;
import com.simin.rxjava2.zxing.Encoding;

import butterknife.BindView;

/**
 * 作者：Fengsimin on 2018/1/29 13:16
 */

public class ZxingActivity extends BaseActivity {

    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;

    String source = "https://mail.qq.com/cgi-bin/frame_html?sid=juzGXMZTr9u1U0wW&r=4bbded0bf54ee4f5fd55edf72e59c7b0";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing;
    }

    @Override
    protected void initData() {
        try {
            Bitmap bitmap1 = Encoding.createQRCode(source, 147);
            //Bitmap bitmap1 = QRCodeUtil.createQRCodeBitmap(source, 145);
            LogUtil.d("渲染后图片width" + bitmap1.getWidth());
            //LogUtil.d("无渲染图片width" + bitmap2.getWidth());
            iv1.setImageBitmap(bitmap1);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
