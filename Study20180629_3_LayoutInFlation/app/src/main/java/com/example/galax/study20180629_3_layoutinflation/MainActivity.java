package com.example.galax.study20180629_3_layoutinflation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //전체화면 불러오는 메소드 setContentView
        setContentView(R.layout.activity_menu);// 이 메소드 이후에 xml 요소 참조해야된다  // R.layout.activity_main는 int 형 값
        //부분화면 불러오는 메소드 LayoutInflater()
    }

}
