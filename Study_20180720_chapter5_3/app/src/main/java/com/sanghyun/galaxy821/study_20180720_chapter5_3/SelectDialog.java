package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SelectDialog extends Dialog {

    Button galleryButton;
    Button cameraButton;
    Button cancelButton;

    private View.OnClickListener galleryClickListener;
    private View.OnClickListener cameraClickListener;
    private View.OnClickListener cancelClickListener;

    public SelectDialog(Context context, View.OnClickListener galleryClickListener, View.OnClickListener cameraClickListener, View.OnClickListener cancelClickListener) {  // 내가 만든 커스텀 다이얼로그 생성자

        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.galleryClickListener = galleryClickListener;
        this.cameraClickListener = cameraClickListener;
        this.cancelClickListener = cancelClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams Ipwindow = new WindowManager.LayoutParams();
        Ipwindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        Ipwindow.dimAmount =0.4f;
        getWindow().setAttributes(Ipwindow);

        setContentView(R.layout.selectimage);

        galleryButton = (Button) findViewById(R.id.galleryButton);
        cameraButton = (Button) findViewById(R.id.cameraButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
    }

}
