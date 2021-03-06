package com.example.galax.study_20180713;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;


public class SampleThreadRunnableActivity extends AppCompatActivity {

        ProgressBar bar;
        TextView textView;
        boolean isRunning = false;

        Handler handler;
        ProgressRunnable runnable;


        public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        bar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView3);
        handler = new Handler();
        runnable = new ProgressRunnable();

    }
        public void onStart() {
        super.onStart();
        bar.setProgress(0);
        Thread thread1 = new Thread(new Runnable() {

            public void run() {
                try {
                    for (int i = 0; i < 20 && isRunning; i++) {
                        Thread.sleep(1000);
                        handler.post(runnable);
                    }
                } catch (Exception ex) {

                    Log.e("SampleThreadActivity", "Exception in processing message.", ex);
                }
            }
        });

        isRunning = true;
        thread1.start();

    }
        public void onStop() {
           super.onStop();
            isRunning = false;
        }

        public class ProgressRunnable implements Runnable {
            public void run() {
                bar.incrementProgressBy(5);
                if (bar.getProgress() == bar.getMax()) {
                    textView.setText("Runnable Done");
                } else {
                    textView.setText("Runnable Working ..." + bar.getProgress());
                }
            }
        }
}
