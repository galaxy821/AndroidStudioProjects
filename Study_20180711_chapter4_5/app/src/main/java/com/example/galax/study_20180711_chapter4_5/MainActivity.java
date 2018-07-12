package com.example.galax.study_20180711_chapter4_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.*;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    boolean isPageOpen = false;

    Animation translateLeftAnim;
    Animation translateRightAnim;

    LinearLayout page;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        page = (LinearLayout) findViewById(R.id.page);

        button = (Button) findViewById(R.id.button);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim =  AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);
    }

    public void onButton1Clicked(View view){
        if(isPageOpen){
            page.startAnimation(translateRightAnim);
        }else{
            page.setVisibility(View.VISIBLE);
            page.startAnimation(translateLeftAnim);
        }
    }

    private class SlidingPageAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationEnd(Animation animation){
            if(isPageOpen){
                page.setVisibility(View.INVISIBLE);

                button.setText("Open");
                isPageOpen = false;
            }else{
                button.setText("Close");
                isPageOpen = true;
            }
        }
    }

}
