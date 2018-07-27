package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

public class ExecuteFunction extends Activity{

    private static final int PICK_FROM_CAMERA =0;
    private static final int PICK_FROM_GALLERY =1;
    private static final int CROP_FROM_IMAGE =2;

    private Uri mImageCaotureUri;

    public ExecuteFunction(){    }

    public void exeGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_GALLERY);
    }

    public void exeCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String url = "temp_"+String.valueOf(System.currentTimeMillis())+"jpg";
        mImageCaotureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaotureUri);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK)
            return;

        switch(requestCode){
            case PICK_FROM_GALLERY:
              //  mlmageCaptureUri = dataa.getData();
        }
    }
}
