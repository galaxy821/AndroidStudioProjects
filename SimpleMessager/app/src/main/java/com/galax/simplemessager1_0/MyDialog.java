package com.galax.simplemessager1_0;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyDialog extends Dialog {

    LinearLayout dialogbox;

    private TextView textViewTitle;
    // private TextView textViewContext; // 제목 이외에 내용이 있을시 사용
    private Button buttonYes;
    private Button buttonNo;

    private String textTitle;
    private String textContext;

    private View.OnClickListener YesClickListener;
    private View.OnClickListener NoClickListener;


    public MyDialog(Context context, View.OnClickListener YesClickListener, View.OnClickListener NoClickListener) {  // 내가 만든 커스텀 다이얼로그 생성자
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.YesClickListener = YesClickListener;
        this.NoClickListener = NoClickListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams Ipwindow = new WindowManager.LayoutParams();
        Ipwindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        Ipwindow.dimAmount =0.4f;
        getWindow().setAttributes(Ipwindow);

        setContentView(R.layout.dialogmain);

        dialogbox = (LinearLayout) findViewById(R.id.dialog_box);

        textViewTitle = (TextView) findViewById(R.id.texttitle);
        //textViewContext = (TextView) findViewById(R.id.); --> 제목 이외에 내용이 있을시 사용

        buttonYes =(Button) findViewById(R.id.buttonYes);
        buttonNo =(Button) findViewById(R.id.buttonnNo);

        buttonYes.setOnClickListener(YesClickListener);
        buttonNo.setOnClickListener(NoClickListener);

    }
}
