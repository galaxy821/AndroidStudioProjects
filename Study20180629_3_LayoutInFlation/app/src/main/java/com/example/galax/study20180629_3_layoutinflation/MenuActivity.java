package com.example.galax.study20180629_3_layoutinflation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    int clicknum=0;
    LinearLayout container;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        container = findViewById(R.id.linearLayout);
        button = findViewById(R.id.button);
    }

    public void onButtonClicked(View v){
        if(clicknum==0){
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.subl, container, true);

            CheckBox checkBox = container.findViewById(R.id.checkBox);
            checkBox.setText("로딩되었어요!");
            button.setText("원래대로 돌리기");
            clicknum=1;
        }
        else if(clicknum==1){
            container.removeAllViews();
            button.setText("추가하기");
            clicknum=0;
        }
    }
}
