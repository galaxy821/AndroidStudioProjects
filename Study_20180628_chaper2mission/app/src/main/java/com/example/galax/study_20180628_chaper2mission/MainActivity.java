package com.example.galax.study_20180628_chaper2mission;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    int buttonUPClicked =0;
    int buttonDOWNClicked =0;
    ScrollView scrollView1;
    ScrollView scrollView2;
    ImageView imageView1;
    ImageView imageVIew2;
    BitmapDrawable bitmap1;
    BitmapDrawable bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView1 = findViewById(R.id.scrollView);
        scrollView2 = findViewById(R.id.scrollView2);

        imageView1 = findViewById(R.id.imageView);
        imageVIew2 = findViewById(R.id.imageView2);

        Resources res = getResources();
        bitmap1 = (BitmapDrawable) res.getDrawable(R.drawable.background);
        bitmap2 = (BitmapDrawable) res.getDrawable(R.drawable.background2);

        int bitmapWidth1 = bitmap1.getIntrinsicWidth();
        int bitmapHeigth1 = bitmap1.getIntrinsicHeight();

        int bitmapWidth2 = bitmap2.getIntrinsicWidth();
        int bitmapHeight2 = bitmap2.getIntrinsicHeight();

        imageView1.setImageDrawable(bitmap1);
        imageView1.getLayoutParams().width = bitmapWidth1;
        imageView1.getLayoutParams().height = bitmapHeigth1;

        imageVIew2.setImageDrawable(bitmap2);
        imageVIew2.getLayoutParams().width = bitmapWidth2;
        imageVIew2.getLayoutParams().height = bitmapHeight2;
    }

    public void onButton1Clicked(View v){
        Resources res = getResources();
        if(buttonUPClicked==0){
            bitmap1 = (BitmapDrawable) res.getDrawable(R.drawable.background2);
            bitmap2 = (BitmapDrawable) res.getDrawable(R.drawable.background);
            int bitmapWidth1 = bitmap1.getIntrinsicWidth();
            int bitmapHeigth1 = bitmap1.getIntrinsicHeight();

            int bitmapWidth2 = bitmap2.getIntrinsicWidth();
            int bitmapHeight2 = bitmap2.getIntrinsicHeight();

            imageView1.setImageDrawable(bitmap1);
            imageView1.getLayoutParams().width = bitmapWidth1;
            imageView1.getLayoutParams().height = bitmapHeigth1;

            imageVIew2.setImageDrawable(bitmap2);
            imageVIew2.getLayoutParams().width = bitmapWidth2;
            imageVIew2.getLayoutParams().height = bitmapHeight2;

            buttonUPClicked=1;
            buttonDOWNClicked=1;
        }

    }

    public void onButton2Clicked(View v){
        Resources res = getResources();
        if(buttonDOWNClicked==1){
            bitmap1 = (BitmapDrawable) res.getDrawable(R.drawable.background);
            bitmap2 = (BitmapDrawable) res.getDrawable(R.drawable.background2);
            int bitmapWidth1 = bitmap1.getIntrinsicWidth();
            int bitmapHeigth1 = bitmap1.getIntrinsicHeight();

            int bitmapWidth2 = bitmap2.getIntrinsicWidth();
            int bitmapHeight2 = bitmap2.getIntrinsicHeight();

            imageView1.setImageDrawable(bitmap1);
            imageView1.getLayoutParams().width = bitmapWidth1;
            imageView1.getLayoutParams().height = bitmapHeigth1;

            imageVIew2.setImageDrawable(bitmap2);
            imageVIew2.getLayoutParams().width = bitmapWidth2;
            imageVIew2.getLayoutParams().height = bitmapHeight2;

            buttonUPClicked=0;
            buttonDOWNClicked=0;
        }
    }
}
