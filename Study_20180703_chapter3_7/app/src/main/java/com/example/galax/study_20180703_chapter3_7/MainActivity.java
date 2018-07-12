package com.example.galax.study_20180703_chapter3_7;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int check =0;
    TextView textView;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView =(TextView)findViewById(R.id.textView3);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Intent intent = getIntent();
        processIntent(intent);

    }

    private void processIntent(Intent intent) {
        if(intent != null){
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");
            check = intent.getIntExtra("check", 0);

            if(check==1){
                textView.setText("");
                Toast.makeText(getApplication(),"메시지가 왔습니다!\n보낸이 : "+sender+"\n시간 : "+receivedDate, Toast.LENGTH_LONG).show();
                Drawable drawable = getResources().getDrawable(R.drawable.messagebox);

                LinearLayout.LayoutParams paramsdef = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramsdef.setMargins(50, 30, 50, 30);

                LinearLayout.LayoutParams params1 =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params1.setMargins(50, 20, 50, 0);

                LinearLayout.LayoutParams params2 =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params2.setMargins(50, 0, 50, 10);

                LinearLayout.LayoutParams params3 =
                        new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                params3.setMargins(50, 0, 50, 20);

                LinearLayout linearLayout2 = new LinearLayout(this);
                linearLayout2.setBackground(drawable);
                linearLayout2.setOrientation(LinearLayout.VERTICAL);
                linearLayout2.setLayoutParams(paramsdef);

                TextView textView1 = new TextView(this);
                textView1.setText(sender);
                textView1.setTextSize(10);
                textView1.setLayoutParams(params1);
                linearLayout2.addView(textView1);

                TextView textView2 = new TextView(this);
                textView2.setText(contents);
                textView2.setTextSize(20);
                textView2.setLayoutParams(params2);
                linearLayout2.addView(textView2);

                TextView textView3 = new TextView(this);
                textView3.setTextSize(10);
                textView3.setText(receivedDate);
                textView3.setLayoutParams(params3);
                linearLayout2.addView(textView3);

                linearLayout.addView(linearLayout2);
            }
            else{
                textView.setText("메시지가 비어있습니다!");
            }
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
        super.onNewIntent(intent);
    }

    public  void ButtonClicked1(View view){
        if(check==1){
            linearLayout.removeAllViews();
            textView.setText("메시지를 모두 확인했습니다!");
        }
    }
}
