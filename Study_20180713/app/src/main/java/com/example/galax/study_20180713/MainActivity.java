package com.example.galax.study_20180713;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int value;
    boolean running;
    boolean isRunning;
    Button button;
    TextView textView;
    TextView textView2;
    ProgressBar progressBar;

    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button) findViewById(R.id.button);
        textView=(TextView) findViewById(R.id.textView2);
        textView2=(TextView) findViewById(R.id.textView3);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        handler = new ProgressHandler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("스레드에서 받은 값 : "+value);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        progressBar.setProgress(0);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int i=0; i<20&&isRunning; i++){
                        Thread.sleep(1000);
                        Message msg = handler.obtainMessage();
                        handler.sendMessage(msg);
                    }
                }catch (Exception ex){
                    Log.e("MainActivity", "Exception in processing message.", ex);
                }
            }
        });
        isRunning=true;
        thread2.start();
    }

    @Override
    protected  void onResume(){
        super.onResume();

        running = true;

        Thread thread1 = new BackgroundThread();
        thread1.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        running =false;
        value=0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isRunning=false;
    }

    class BackgroundThread extends Thread{
        public void run(){
            while(running){
                try{
                    Thread.sleep(1000);
                    value++;
                }catch(InterruptedException ex){
                    Log.e("SampleJavaThread", "Exception in thread.", ex);
                }
            }
        }
    }

    public class ProgressHandler extends Handler{
        public void handleMessage(Message msg){
            progressBar.incrementProgressBy(5);
            if(progressBar.getProgress() == progressBar.getMax()){
                textView2.setText("Done");
            }else{
                textView2.setText("working ... "+ progressBar.getProgress());
            }
        }
    }


}
