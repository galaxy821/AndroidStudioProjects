package com.example.galax.study_20180702_chapter3_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button =(Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent passIntent = getIntent();
        processIntent(passIntent);
    }

    private  void processIntent(Intent intent){
        TextView textView = findViewById(R.id.textView);
        if(intent != null){
            ArrayList<String> names = (ArrayList<String>)intent.getSerializableExtra("names");
            if(names != null){
                Toast.makeText(getApplicationContext(),"전달받은 이름 리스트 개수 : "+names.size(), Toast.LENGTH_LONG).show();
            }

            SimpleDate data =  (SimpleDate) intent.getParcelableExtra("data");
            if(data != null){
                Toast.makeText(getApplicationContext(),"전달받은 SimpleData : "+ data.message, Toast.LENGTH_LONG).show();
                textView.setText("전달 받은 데이터\nNumber : "+data.number+ "\nMessage : "+data.message);
            }
        }
    }
}
