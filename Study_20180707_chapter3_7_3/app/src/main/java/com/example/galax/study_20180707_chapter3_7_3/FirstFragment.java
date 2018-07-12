package com.example.galax.study_20180707_chapter3_7_3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirstFragment extends Fragment {

    int check=0;
    LinearLayout linearLayout;
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        return rootview;
    }


    public  void ButtonClicked1(View view){
        if(check==1){
            linearLayout.removeAllViews();
            textView.setText("메시지를 모두 확인했습니다!");
        }
    }
}
