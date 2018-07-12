package com.example.galax.study_20180702_chapter3_4;
/*
액티비티를 위한 플래그와 부가 데이터

*플래그*
* 액티비티는 스태형태로 저장된다.

*부가 데이터*
* 한 화면에서 다른 화면을 띄울때 데이터를 전달해야만 하는 경우
* 가장 간단한 방법은 별도의 클래스를 만든다음 그 안에 클래스 변수를 만들고 두개의 화면에서 모두 그 변수를 참조하게 하는 방법
* BUT!! 안드로이드는 다른 앱에서 내가 만든 화면을 띄울 수도 있기때문에 변수 공유하는 방식으로 데이터를 전달하는 것이 불가능할 수도 있다.
* -->> 그래서 액티비티를 띄울때 전달되는 인텐트 안에 부가 데이터를 넣어 전달하는 방법을 권장
*
* 인텐트 안에는 번들 객체가 들어 있는데, 해시테이블과 유사해서 putExtra()와 get000Extra와 같은 메소드로 데이터를 넣거나 빼낼 수 있다
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU =101;
    public static final String KEY_SIMPLE_DATA ="data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClicked(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        ArrayList<String> names = new ArrayList<String>();
        names.add("박상현");
        names.add("윤연경");

        intent.putExtra("names", names);

        SimpleDate data = new SimpleDate(100, "Hello");
        intent.putExtra("data", data);

        startActivityForResult(intent, 101);


    }
}
