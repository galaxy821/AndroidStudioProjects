package com.example.galax.study_20180707_chapter3_7_3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    int check =0;
    TextView textView;
    LinearLayout linearLayout;

    Toolbar toolbar;

    FirstFragment fragment1;
    SecondFragment fragment2;
    ThirdFragment fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =(TextView)findViewById(R.id.textView3);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Intent intent = getIntent();
        processIntent(intent);

        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        fragment3 = new ThirdFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();

        TabLayout tabs =(TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("통화기록"));
        tabs.addTab(tabs.newTab().setText("스팸기록"));
        tabs.addTab(tabs.newTab().setText("연락처"));

        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : "+ position);

                Fragment selected = null;
                if(position ==0){
                    selected = fragment1;
                }else if(position ==1){
                    selected = fragment2;
                }else if(position ==2){
                    selected = fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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


}
