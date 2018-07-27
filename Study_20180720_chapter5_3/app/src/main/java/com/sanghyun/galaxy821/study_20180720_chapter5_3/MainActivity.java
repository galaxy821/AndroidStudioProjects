package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabItem;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Mainfragment fragment2;

    //int clicked = 0;
    Button button;
    ListView listView;
    //ListAdapter adapter;
    //AddDialog dialog;
    //SelectDialog dialogWindow;

    //Button deleteButton;

    //Intent intent;

    //SQLiteDatabase addressList=null;

    //ExecuteFunction exe = new ExecuteFunction();

    //public ArrayList<ListView_item> items = new ArrayList<ListView_item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.mainlayout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);

        fragment2 = new Mainfragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        Log.d("SINGER1", "singer id ="+R.drawable.singer);
        Log.d("SINGER2", "singer id ="+R.drawable.singer2);
        Log.d("SINGER3", "singer id ="+R.drawable.singer3);
        Log.d("SINGER4", "singer id ="+R.drawable.singer4);
        Log.d("SINGER5", "singer id ="+R.drawable.singer5);

        //TableLayout tabs = (TableLayout) findViewById(R.id.tabs);
        TabItem item2 = (TabItem) findViewById(R.id.button_address);
        TabItem item1 = (TabItem) findViewById(R.id.button_dial);
        TabItem item3 = (TabItem) findViewById(R.id.button_memory);
        /*
        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/
        /*
        button = (Button) findViewById(R.id.addbutton);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new ListAdapter();

        intent = new Intent(MainActivity.this, NewActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(clicked==0){
                    ListView_item item = (ListView_item) adapter.getItem(position);
                    intent.putExtra("name", item.getName());
                    startActivity(intent);
                }
                else if(clicked==1){
                    ListView_item item = (ListView_item) adapter.getItem(position);
                    String deleteName= item.getName().trim();
                    items.remove(position);
                    clicked=0;
                    listView.setAdapter(adapter);
                    deleteData(deleteName);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AddDialog(MainActivity.this, addbuttonListener, ImageClickListener);
                dialog.setCancelable(true);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
            }
        });

        deleteButton = (Button)  findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = 1;
            }
        });*/
    }

    /*
    private  View.OnClickListener addbuttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if(47<=dialog.getEditImage()&&51>=dialog.getEditImage()){
                dialog.dismiss();
                String editname = dialog.getEditName();
                String editmobile = dialog.getEditMobile();
                int editage = dialog.getEditAge();
                int image = dialog.getEditImage()+2131099700;
                adapter.addItem(new ListView_item(editname, editmobile, editage, image));
                //Toast.makeText(getApplicationContext(), "데이터입력", Toast.LENGTH_LONG).show();
                listView.setAdapter(adapter);
                insertData(editname, editmobile, editage, image);
            }else{
                Toast.makeText(getApplicationContext(), "잘못된 입력 값 입니다.", Toast.LENGTH_LONG).show();
            }
        }
    };

    private View.OnClickListener ImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialogWindow = new SelectDialog(MainActivity.this, galleryButtonListener, cameraButtonListener, cancelButtonListener);
            dialogWindow.setCancelable(true);
            dialogWindow.getWindow().setGravity(Gravity.CENTER);
            dialogWindow.show();
        }
    };

    private  View.OnClickListener galleryButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            exe.exeGallery();
        }
    };

    private  View.OnClickListener cameraButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            exe.exeCamera();
        }
    };

    private  View.OnClickListener cancelButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialogWindow.dismiss();
        }
    };*/

    /*
    class ListAdapter extends BaseAdapter{ //커스텀리스트뷰 어뎁터

        public void addItem(ListView_item item){
            items.add(item);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ListView_layout view = new ListView_layout(getApplicationContext());
            ListView_item item = items.get(position);
            view.setName(item.getName());
            view.setMoblie(item.getMobile());
            view.setAge(item.getAge());
            view.setImageView(item.getResid());

            return view;
        }
    }*/

     //=====================데이터베이트================================================================================//
/*
    private void createDatabase() { //데이터베이스 생성

        try {
            addressList = openOrCreateDatabase("addressList.db", Activity.MODE_PRIVATE, null);
            //Toast.makeText(getApplicationContext(), "데이터베이스 생성", Toast.LENGTH_LONG).show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    private void createTable() { //테이블 생성

        if(addressList != null){

            addressList.execSQL("create table if not exists " + "addressList" + "(" + " _id integer PRIMARY KEY autoincrement, " + " name text, "+" mobile text, " + " age integer, " + " image integer);" );
            //Toast.makeText(getApplicationContext(), "테이블 생성", Toast.LENGTH_LONG).show();
        }else{
            Log.d("error","database open fail!");
        }

    }

    public void insertData(String name, String mobile, int age, int image){ //데이터 삽입

       // if(addressList != null){
            String sql ="insert into addressList(name, mobile, age, image) values(?, ?, ?, ?)";
            Object[] params = {name, mobile, age, image};

            addressList.execSQL(sql, params);
            //Toast.makeText(getApplicationContext(), "데이터 추가", Toast.LENGTH_LONG).show();
        //}else{
       // }
    }

    public void selectData(){ //데이터 조회

        if(addressList != null){
            String sql = "select name, mobile, age, image from " + "addressList";
            Cursor cursor = addressList.rawQuery(sql, null);

            for(int i =0;  i< cursor.getCount(); i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                String phone =  cursor.getString(1);
                int age = cursor.getInt(2);
                int image = cursor.getInt(3);

                //adapter.addItem(new ListView_item(name, phone, age, image));

                //listView.setAdapter(adapter);
            }
            cursor.close();
        }
    }

    public void deleteData(String deletename){ //특정 데이터 삭제
        if(addressList != null){
            addressList.execSQL("DELETE FROM addressList WHERE name = " + "'"+deletename +"'"+";");
        } else{
            Log.d("error","database open fail!");
        }
    }
*/
    //=================================================================================================================//

}