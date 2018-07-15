package com.example.galax.study_21080713_chapter7_4;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ProgressBar progress;
    BackgroundTask task;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button executeButton =(Button) findViewById(R.id.executeButton);
        Button cancelButton =(Button) findViewById(R.id.cancelButton);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new BackgroundTask();
                task.execute(100);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{
        @Override
        protected void onPreExecute() {
            value=0;
            progress.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {

            while(isCancelled()==false){
                value++;
                if(value>=100){
                    break;
                }else{
                    publishProgress(value);
                }

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException ex){ }
            }
            return value;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            progress.setProgress(0);
            textView.setText("Finished");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.setProgress(values[0].intValue());
            textView.setText("Current Value : "+ values[0].toString());

        }

        @Override
        protected void onCancelled() {
            progress.setProgress(0);
            textView.setText("Cancelled");
        }
    }
}
