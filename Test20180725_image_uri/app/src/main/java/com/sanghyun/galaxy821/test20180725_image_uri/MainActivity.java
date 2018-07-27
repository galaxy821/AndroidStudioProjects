package com.sanghyun.galaxy821.test20180725_image_uri;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView imageView;

    //Bitmap imageBit = null;

    byte[] bytebitbit;
    Bitmap bitmap=null;

    final int REQ_CODE_SELECT_IMAGE=100; /////////////////////////

    SQLiteDatabase imageData=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

        createDatabase();
        createTable();

        if(imageData!= null){
            Toast.makeText(getApplicationContext(), "hello world!", Toast.LENGTH_LONG).show();
            bytebitbit = selectData();
            if(bytebitbit!= null){
                bitmap=byteArrayToBitmap(bytebitbit);
                //imageView.setImageBitmap(bitmap);
                imageView.setImageBitmap(blur(getApplication(), bitmap));
            }
        }

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 처리로직
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        //Toast.makeText(getBaseContext(), "resultCode : "+resultCode, Toast.LENGTH_SHORT).show();

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    String name = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.imageView);

                    //이미지를 데이터베이스에 저장
                    byte[] byteArray = bitmapToByteArray(image_bitmap);
                    insertData(name, byteArray);

                    //배치해놓은 ImageView에 set
                    //image.setImageBitmap(image_bitmap);
                    image.setImageBitmap(blur(getApplication(), image_bitmap));


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName.trim();
    }

    private void createDatabase() { //데이터베이스 생성

        try {
            imageData = openOrCreateDatabase("imageData.db", Activity.MODE_PRIVATE, null);
            //Toast.makeText(getApplicationContext(), "데이터베이스 생성", Toast.LENGTH_LONG).show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void createTable() { //테이블 생성

        if(imageData != null){

            imageData.execSQL("create table if not exists " + "imagedata" + "(" + " _id integer PRIMARY KEY autoincrement, " + " name text,"+" image BLOB);" );
            //Toast.makeText(getApplicationContext(), "테이블 생성", Toast.LENGTH_LONG).show();
        }else{
            Log.d("error","database open fail!");
        }

    }

    public void insertData(String name,byte[] image)throws SQLiteException { //데이터 삽입

        //if(imageData != null){
            String sql ="insert into imagedata(name, image) values(?, ?)";
            Object[] params = {name, image};

            imageData.execSQL(sql, params);
            //Toast.makeText(getApplicationContext(), "데이터 추가", Toast.LENGTH_LONG).show();
        //}else{
        //}
    }

    public byte[] selectData(){ //데이터 조회
        Bitmap bitmap=null;
        byte[] bytebit=null;
        //String name;

        if(imageData != null){
            String sql = "select name, image from " + "imagedata";
            Cursor cursor = imageData.rawQuery(sql, null);

            for(int i=0; i<cursor.getCount(); i++){
                cursor.moveToNext();
                //name = cursor.getString(0);
                bytebit = cursor.getBlob(1);
                //Toast.makeText(getApplicationContext(), "사진 파일 명 : "+name, Toast.LENGTH_LONG).show();
            }

            //while(cursor.moveToNext()){

                //bitmap=byteArrayToBitmap(image);
                //imageView.setImageBitmap(imageBit);
            //}
            cursor.close();
        }
        return bytebit;
    }

    public byte[] bitmapToByteArray(Bitmap image){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public Bitmap byteArrayToBitmap(byte[] byteArray){
        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        //byteArray=null;
        return bitmap;
    }

    public static Bitmap blur(Context ct, Bitmap sentBitmap) {

        if (Build.VERSION.SDK_INT > 16) {
            Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

            final RenderScript rs = RenderScript.create(ct);
            final Allocation input = Allocation.createFromBitmap(rs, sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                    Allocation.USAGE_SCRIPT);
            final Allocation output = Allocation.createTyped(rs, input.getType());
            final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setRadius(20);
            script.setInput(input);
            script.forEach(output);
            output.copyTo(bitmap);
            return bitmap;
        }
        return sentBitmap;
    }
}
