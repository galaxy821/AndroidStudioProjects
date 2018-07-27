package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddDialog extends Dialog {

    Button button;
    Button buttonImage;

    EditText editTextName;
    EditText editTextMobile;
    EditText editTextAge;
    EditText editTextImage;

    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;

    LinearLayout dialogbox;

    private View.OnClickListener AddClickListener;
    private View.OnClickListener ImageClickListener;

    public AddDialog(Context context, View.OnClickListener AddClickListener, View.OnClickListener ImageClickListener) {  // 내가 만든 커스텀 다이얼로그 생성자
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.AddClickListener = AddClickListener;
        this.ImageClickListener = ImageClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams Ipwindow = new WindowManager.LayoutParams();
        Ipwindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        Ipwindow.dimAmount =0.4f;
        getWindow().setAttributes(Ipwindow);

        setContentView(R.layout.addlayout);

        dialogbox = (LinearLayout) findViewById(R.id.addLayout);

        textView1 = (TextView)findViewById(R.id.nametextView);
        textView2 = (TextView)findViewById(R.id.mobiletextView);
        textView3 = (TextView)findViewById(R.id.agetextView);
        textView4 = (TextView)findViewById(R.id.imagetextView);

        button =(Button) findViewById(R.id.button_add);
        button.setOnClickListener(AddClickListener);

        buttonImage = (Button) findViewById(R.id.button_image);
        buttonImage.setOnClickListener(ImageClickListener);

        editTextName = (EditText) findViewById(R.id.nameeditText);
        editTextMobile = (EditText) findViewById(R.id.mobileeditText);
        editTextAge = (EditText) findViewById(R.id.ageeditText);
        editTextImage =(EditText) findViewById(R.id.imageeditText);
    }

    public String getEditName(){
        return editTextName.getText().toString().trim();
    }

    public String getEditMobile(){
        return editTextMobile.getText().toString().trim();
    }

    public int getEditAge(){
        return  Integer.parseInt(editTextAge.getText().toString());
    }

    public int getEditImage(){
        return Integer.parseInt(editTextImage.getText().toString());
    }
}
