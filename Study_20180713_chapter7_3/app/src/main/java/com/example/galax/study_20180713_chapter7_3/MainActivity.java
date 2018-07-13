package com.example.galax.study_20180713_chapter7_3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MainHandler mainHandler;
    ProcessThread processThread;

    Button button;
    EditText editTextUp;
    EditText editTextDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        editTextUp = (EditText) findViewById(R.id.editText);
        editTextDown = (EditText) findViewById(R.id.editText2);

        mainHandler = new MainHandler();

        processThread = new ProcessThread();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = editTextUp.getText().toString();
                Message message = Message.obtain(); //Message 객체 생성
                message.obj=string;

                processThread.handler.sendMessage(message);
            }
        });

        processThread.start();
    }

    class ProcessThread extends Thread{
        ProcessHandler handler;

        public ProcessThread(){
            handler = new ProcessHandler();
        }

        public void run(){
            Looper.prepare();
            Looper.loop();
        }
    }

    class ProcessHandler extends Handler{
        public void handleMessage(Message msg){
            Message resulting = Message.obtain();
            resulting.obj = msg.obj + "SANG HYUN!!";

            mainHandler.sendMessage(resulting);
        }
    }

    class MainHandler extends Handler{
        public void handleMessage(Message msg){
            String str =(String) msg.obj;
            editTextDown.setText(str);
        }
    }
}
