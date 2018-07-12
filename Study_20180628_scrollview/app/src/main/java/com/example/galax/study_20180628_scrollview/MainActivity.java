package com.example.galax.study_20180628_scrollview;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;
    int chicked_check =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ScrollView)findViewById(R.id.scrollView);
        imageView = (ImageView)findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.kobukki);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width =bitmapWidth;
        imageView.getLayoutParams().height=bitmapHeight;
    }
    public void onButton1Clicked(View v){
        if(chicked_check==0){
            chicked_check=1;
            changeImage(chicked_check);
        }
        else{
            chicked_check=0;
            changeImage(chicked_check);
        }

    }

    private void changeImage(int chicked_check){
        Resources res =getResources();
        if(chicked_check==1){
            bitmap =(BitmapDrawable) res.getDrawable(R.drawable.kobukki2);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width =bitmapWidth;
            imageView.getLayoutParams().height=bitmapHeight;
        }
        else{
            bitmap =(BitmapDrawable) res.getDrawable(R.drawable.kobukki);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width =bitmapWidth;
            imageView.getLayoutParams().height=bitmapHeight;
        }
    }
}