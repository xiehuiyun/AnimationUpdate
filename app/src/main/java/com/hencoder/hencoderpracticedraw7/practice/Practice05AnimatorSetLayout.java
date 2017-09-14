package com.hencoder.hencoderpracticedraw7.practice;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw7.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Practice05AnimatorSetLayout extends RelativeLayout {
    View view;
    Button animateBt;

    public Practice05AnimatorSetLayout(Context context) {
        super(context);
    }

    public Practice05AnimatorSetLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice05AnimatorSetLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setTranslationX(-200f);

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "translationX", -200, 200);
                ObjectAnimator animator3 = ObjectAnimator.ofFloat(view, "rotation", 0, 1080);
                animator3.setDuration(1000);

                AnimatorSet animatorSet = new AnimatorSet();
                // 用 AnimatorSet 的方法来让三个动画协作执行
                // 要求 1： animator1 先执行，animator2 在 animator1 完成后立即开始
                // 要求 2： animator2 和 animator3 同时开始
                animatorSet.playSequentially(animator1,animator2);// 两个动画依次执行
                animatorSet.playTogether(animator2,animator3);// 一起执行
                /*
                playSequentially()，就可以让两个动画依次播放
                使用 AnimatorSet.play(animatorA).with/before/after(animatorB)的方式来精确配置各个 Animator 之间的关系
                 */

                animatorSet.start();
            }
        });
    }
}
