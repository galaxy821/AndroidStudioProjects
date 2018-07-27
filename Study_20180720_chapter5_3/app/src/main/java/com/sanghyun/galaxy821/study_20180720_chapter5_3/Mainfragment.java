package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mainfragment extends Fragment {

    int clicked = 0;
    Button button;
    ListView listView;
    ListAdapter adapter;
    AddDialog dialog;
    SelectDialog dialogWindow;

    Button deleteButton;

    Intent intent;

    SQLiteDatabase addressList=null;

    ExecuteFunction exe = new ExecuteFunction();

    public ArrayList<ListView_item> items = new ArrayList<ListView_item>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.activity_main, container, false);

        button = (Button) view.findViewById(R.id.addbutton);

        listView = (ListView) view.findViewById(R.id.listView);

        adapter = new ListAdapter();

        intent = new Intent(getActivity(), NewActivity.class);

        createDatabase(); //데이터베이스 생성
        createTable(); //데이블 생성
        if(addressList!=null){
            selectData();
            Toast.makeText(getActivity(), "데이터 조회 완료", Toast.LENGTH_LONG).show();
        }

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
                dialog = new AddDialog(getActivity(), addbuttonListener, ImageClickListener);
                dialog.setCancelable(true);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.show();
            }
        });

        deleteButton = (Button) view.findViewById(R.id.deletebutton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = 1;
            }
        });


        return view;
    }

    class ListAdapter extends BaseAdapter { //커스텀리스트뷰 어뎁터

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
            ListView_layout view = new ListView_layout(getActivity());
            ListView_item item = items.get(position);
            view.setName(item.getName());
            view.setMoblie(item.getMobile());
            view.setAge(item.getAge());
            view.setImageView(item.getResid());

            return view;
        }
    }

    private  View.OnClickListener addbuttonListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            if(2<=dialog.getEditImage()&&6>=dialog.getEditImage()){
                dialog.dismiss();
                String editname = dialog.getEditName();
                String editmobile = dialog.getEditMobile();
                int editage = dialog.getEditAge();
                //int image = dialog.getEditImage()+2131099700;
                int image = dialog.getEditImage()+2131165300;
                adapter.addItem(new ListView_item(editname, editmobile, editage, image));
                //Toast.makeText(getApplicationContext(), "데이터입력", Toast.LENGTH_LONG).show();
                listView.setAdapter(adapter);
                insertData(editname, editmobile, editage, image);
            }else{
                Toast.makeText(getActivity(), "잘못된 입력 값 입니다.", Toast.LENGTH_LONG).show();
            }
        }
    };

    private View.OnClickListener ImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialogWindow = new SelectDialog(getActivity(), galleryButtonListener, cameraButtonListener, cancelButtonListener);
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
    };

    //=====================데이터베이트================================================================================//

    private void createDatabase() { //데이터베이스 생성

        try {
            addressList = getActivity().openOrCreateDatabase("addressList.db", Activity.MODE_PRIVATE, null);
            Toast.makeText(getActivity(), "데이터베이스 생성", Toast.LENGTH_LONG).show();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    private void createTable() { //테이블 생성

        if(addressList != null){

            addressList.execSQL("create table if not exists " + "addressList" + "(" + " _id integer PRIMARY KEY autoincrement, " + " name text, "+" mobile text, " + " age integer, " + " image integer);" );
            Toast.makeText(getActivity(), "테이블 생성", Toast.LENGTH_LONG).show();
        }else{
            Log.d("error","database open fail!");
        }

    }

    public void insertData(String name, String mobile, int age, int image){ //데이터 삽입

        // if(addressList != null){
        String sql ="insert into addressList(name, mobile, age, image) values(?, ?, ?, ?)";
        Object[] params = {name, mobile, age, image};

        addressList.execSQL(sql, params);
        Toast.makeText(getActivity(), "데이터 추가", Toast.LENGTH_LONG).show();
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

                Toast.makeText(getActivity(), "name : "+name+" phone : "+phone+" age : "+age+" image : "+image,Toast.LENGTH_LONG).show();
                adapter.addItem(new ListView_item(name, phone, age, image));

                listView.setAdapter(adapter);
            }
            cursor.close();
            Toast.makeText(getActivity(), "데이터 조회", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteData(String deletename){ //특정 데이터 삭제
        if(addressList != null){
            addressList.execSQL("DELETE FROM addressList WHERE name = " + "'"+deletename +"'"+";");
        } else{
            Log.d("error","database open fail!");
        }
    }

    //=================================================================================================================//
}
