package com.semosu.semosu_app;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.Console;



public class SplashActivity extends AppCompatActivity{

    Animation aniTop, aniBottom;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView topV =  findViewById(R.id.topImage);
        ImageView bottomV =  findViewById(R.id.bottomImage);
        final ConstraintLayout CL =  findViewById(R.id.Const_Rayout);
        Drawable CL_bgColor = CL.getBackground();

        aniTop = AnimationUtils.loadAnimation(this,R.anim.fromtop);

        topV.setAnimation(aniTop);
        aniBottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);

        bottomV.setAnimation(aniBottom);


        final ObjectAnimator backgroundColorAnimator = ObjectAnimator.ofObject(CL,
                "backgroundColor",
                new ArgbEvaluator(),
                0xFFFFFFF,
                0xffffffff);
        backgroundColorAnimator.setDuration(1000);
        backgroundColorAnimator.start();

        Thread splashTread;

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 2500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashActivity.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashActivity.this.finish();

                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashActivity.this.finish();
                }

            }
        };
        splashTread.start();

    }
}
