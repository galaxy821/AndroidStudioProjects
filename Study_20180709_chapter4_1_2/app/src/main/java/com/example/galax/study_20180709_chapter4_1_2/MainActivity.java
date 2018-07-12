package com.example.galax.study_20180709_chapter4_1_2;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    TextView textView;

    String name;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate 호출됨", Toast.LENGTH_LONG).show();

        textView =(TextView) findViewById(R.id.textView);

        View view = findViewById(R.id.view);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();

                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if(action == MotionEvent.ACTION_DOWN) {
                    println("손가락이 눌림 : " + curX + ", " + curY);
                }else if(action == MotionEvent.ACTION_MOVE){
                    println("손가락이 움직임 : "+curX+", "+curY);
                }else if(action == MotionEvent.ACTION_UP){
                    println("손가락을 뗌 : "+curX+", "+curY);
                }
                return true;
            }
        });

        final GestureDetector detector;
        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                println("onDown() 호출됨.");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {
                println("onShowPress()호출됨");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                println("onSingleTapUp() 호출됨");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onScroll() 호출됨 : "+v+", "+v1);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                println("onLongPress()호출됨");
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                println("onFling() 호출됨 : "+v+", "+v1);
                return true;
            }
        });

        View view2 = (View) findViewById(R.id.view2);
        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });


        editText = (EditText) findViewById(R.id.editText);

        Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = editText.getText().toString();
                Toast.makeText(getApplicationContext(), "입력된 값을 변수에 저장했습니다 : "+name, Toast.LENGTH_LONG).show();;
            }
        });

        if(savedInstanceState != null){
            name = savedInstanceState.getString("name");
            Toast.makeText(getApplicationContext(), "값을 복원했습니다 : "+name, Toast.LENGTH_LONG).show();;
        }
    }

        @Override
        protected void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            outState.putString("name", name);
        }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "시스템 [Back]버튼이 눌렸습니다.", Toast.LENGTH_LONG).show();
            finish();

            return true;
        }
        return  false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop 호출됨", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy 호출됨", Toast.LENGTH_LONG).show();
    }

    public void println(String data){
        textView.append(data+"\n");
    }
}
