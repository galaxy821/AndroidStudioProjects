package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ListView_layout extends LinearLayout {
    TextView textView1;
    TextView textView2;
    TextView textVIew3;
    ImageView imageView;

    public ListView_layout(Context context){
        super(context);
        init(context);
    }

    public ListView_layout(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.listview_layout, this, true);

        textView1 = (TextView) findViewById(R.id.textVIew1);
        textView2 = (TextView) findViewById(R.id.textVIew2);
        textVIew3 = (TextView) findViewById(R.id.textVIew3);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void setName(String name){
        textView1.setText(name);
    }

    public void setMoblie(String moblie){
        textView2.setText(moblie);
    }

    public void setAge(int age){
        textVIew3.setText(String.valueOf(age));
    }

    public void setImageView(int resid){
        imageView.setImageResource(resid);
    }
}
