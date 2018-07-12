package com.example.galax.study_20180702_chaper3_3_2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.editText);
                String filename = editText.getText().toString()+".pdf";
                if(filename.length()>0){
                    openPDF(filename.trim());
                }else{
                    Toast.makeText(getApplication(), "파일명을 입력해주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openPDF(String filename) {
        String sdcardFolder = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file;
        String filepath = sdcardFolder + File.separator + filename;
        file = new File(filepath);

        if(file.exists()){
            Uri uri = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");

            try{
                startActivity(intent);
            }
            catch(ActivityNotFoundException ex){
                Toast.makeText(this, "PDF 파일을 보기 위한 PDF 뷰어 앱이 없습니다!", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "PDF 파일이 없습니다!", Toast.LENGTH_LONG).show();
        }
    }
}
