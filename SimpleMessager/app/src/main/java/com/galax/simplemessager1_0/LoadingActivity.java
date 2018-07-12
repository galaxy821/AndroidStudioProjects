package com.galax.simplemessager1_0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class LoadingActivity extends Activity {

    private Handler handler;
    Animation scalein;
    android.support.v7.widget.AppCompatImageView imageView;

    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_loading);

        handler = new Handler();
        imageView =(android.support.v7.widget.AppCompatImageView) findViewById(R.id.imageView2);
        imageView.setVisibility(View.GONE);

        scalein = AnimationUtils.loadAnimation(this, R.anim.scalemeic);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 3000);

        imageView.startAnimation(scalein);
        imageView.setVisibility(View.VISIBLE);

        scalein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
