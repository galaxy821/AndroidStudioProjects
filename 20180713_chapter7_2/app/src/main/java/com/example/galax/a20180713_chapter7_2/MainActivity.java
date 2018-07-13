package com.example.galax.a20180713_chapter7_2;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView= (TextView) findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        String title ="원격 요청";
        String message ="데이터를 요청하시겠습니까?";
        String titleButtonYes ="Yes";
        String titleButtonNo ="아니오";

        AlertDialog dialog = makeRequestDailog(title, message, titleButtonYes, titleButtonNo);
        dialog.show();

        textView.setText("원격 데이터 요청 중...");
    }

    private AlertDialog makeRequestDailog(CharSequence title, CharSequence message, CharSequence titleButtonYes, CharSequence titleButtonNo) {
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(message);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RequestHandler handler = new RequestHandler();
                handler.sendEmptyMessageDelayed(0, 20);
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        });

        return requestDialog.show();
    }

    class RequestHandler extends Handler{
        public void handleMessage(Message msg){
            for(int i=0; i<10; i++){
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException ex){}
            }
            textView.setText("원격 데이터 요청 완료");
        }
    }

}
