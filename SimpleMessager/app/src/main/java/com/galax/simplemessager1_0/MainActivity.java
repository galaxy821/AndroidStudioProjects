package com.galax.simplemessager1_0;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyDialog dialog;
    Animation scaleanim;
    Animation scalein;
    Animation alpha;

    int check =0;
    TextView textView;
    LinearLayout linearLayout;
    Button button;
    int visiButton=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        textView =(TextView)findViewById(R.id.textView3);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        Intent intent = getIntent();
        processIntent(intent);

        button.setVisibility(View.GONE);

        int permissionCheck1 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_MMS);
        if(permissionCheck1 != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS}, 1);
        }
        if(permissionCheck2 != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_MMS}, 1);
        }

        scaleanim = AnimationUtils.loadAnimation(this, R.anim.scale);
        scalein = AnimationUtils.loadAnimation(this, R.anim.scalein);
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

        scaleanim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                LayoutInflater inflater = getLayoutInflater();          /* 커스텀 토스트 만들기 57~70줄 */

                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text =(TextView) layout.findViewById(R.id.textBox);

                Toast toast = new Toast(getApplicationContext());
                text.setText("메시지 모두 지웠습니다!");

                toast.setGravity(Gravity.CENTER, 0, 500);
                toast.setDuration((Toast.LENGTH_SHORT));

                toast.setView(layout);
                toast.show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }});

        scalein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }});

        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }});

    }

    private void processIntent(Intent intent) {
        if(intent != null){
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");
            check = intent.getIntExtra("check", 0);

            if(check==1){

                LayoutInflater inflater = getLayoutInflater();          /* 커스텀 토스트 만들기 57~70줄 */

                View layout = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

                TextView text =(TextView) layout.findViewById(R.id.textBox);

                Toast toast = new Toast(this);
                text.setText("메시지가 왔습니다!\n보낸이 : "+sender+"\n시간 : "+receivedDate);

                toast.setGravity(Gravity.CENTER, 0, 500);
                toast.setDuration((Toast.LENGTH_SHORT));

                toast.setView(layout);
                toast.show();

               // Toast.makeText(getApplicationContext(), "메시지가 왔습니다!\n보낸이 : "+sender+"\n시간 : "+receivedDate, Toast.LENGTH_LONG).show(); // 일반적인 토스트 메시지 만들기

                textView.setText("");

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

                if(visiButton>0) {
                    visiButton++;
                }

            }
            else{
                textView.setText("메시지가 비어있습니다!");
            }
            if(visiButton==0&&check==1) {
                button.startAnimation(scalein);
                button.setVisibility(View.VISIBLE);
                visiButton++;
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
            textView.setText("메시지가 비어있습니다!");
            button.startAnimation(scaleanim);
            check=0;
            button.setVisibility(View.GONE); //View.GONE --> 아예 사라지게 하는것 버튼 동작하지 않는다. //View.VISIBLE -->평상시 모습 //View.INVISIBLE -->투명하게하는 효과
            visiButton=0;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            showMessage();
        }
        return true;
    }

    private void showMessage() {
        dialog = new MyDialog(MainActivity.this, YesButtonListener, NoButtonListener);
        dialog.setCancelable(true);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.show();
    }

    private  View.OnClickListener YesButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private  View.OnClickListener NoButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    };

}
