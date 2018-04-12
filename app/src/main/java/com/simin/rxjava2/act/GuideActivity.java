package com.simin.rxjava2.act;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.simin.rxjava2.R;

import butterknife.BindView;

/**
 * 作者：Fengsimin on 2018/3/20 11:26
 */

public class GuideActivity extends BaseActivity {

    @BindView(R.id.iv1)
    ImageView iv1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        super.initView();
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setAnimator();
                setLogoAnim();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
    }

    protected void setAnimator() {
        /*Animator animator = AnimatorInflater.loadAnimator(app, R.animator.alpha_in);
        animator.setTarget(iv1);
        animator.start();*/
        Animation animation = AnimationUtils.loadAnimation(app, R.anim.anim_logo);
        iv1.startAnimation(animation);
    }

    protected void setLogoAnim() {
        ObjectAnimator translation = ObjectAnimator.ofFloat(iv1, "translationY", 0, 300, 0, 300, 0, 300, 0, 300, 0);  // 平移动画

        //ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv1, "scaleX", 1, 1.5f, 1, 1.4f, 1, 1.3f, 1, 1.2f, 1);
        //ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv1, "scaleY", 1, 1.5f, 1, 1.4f, 1, 1.3f, 1, 1.2f, 1);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(iv1, "scaleX", 1, 0.1f, 1, 0.1f, 1, 0.1f, 1, 0.1f, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(iv1, "scaleY", 1, 1.5f, 1, 1.5f, 1, 1.5f, 1, 1.5f, 1);
        iv1.setPivotX(50);
        iv1.setPivotY(10);
        //ObjectAnimator rotate = ObjectAnimator.ofFloat(iv1, "rotation", 0f, 360f);  // 旋转动画
        //ObjectAnimator alpha = ObjectAnimator.ofFloat(iv1, "alpha", 1f, 0f, 1f);  // 透明度动画 // 步骤2：创建组合
        AnimatorSet set = new AnimatorSet();
        set.play(translation).with(scaleY).with(scaleX);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(3000);
        set.start();
    }
}
