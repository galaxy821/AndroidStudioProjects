package com.sanghyun.galaxy821.study_20180720_chapter9_1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String databaseName;
    String tableName;
    TextView status;
    boolean databaseCreated = false;
    boolean tableCreated = false;

    EditText editText3;
    EditText editText4;
    EditText editText5;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText databaseNameInput = (EditText) findViewById(R.id.databaseNameInput);
        final EditText tableNameInput = (EditText) findViewById(R.id.tableNameInput);

        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);

        Button createDatabaseBtn = (Button) findViewById(R.id.createDatabaseBtn);
        createDatabaseBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                databaseName = databaseNameInput.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button createTableBtn = (Button) findViewById(R.id.createTableBtn);
        createTableBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tableName = tableNameInput.getText().toString();
                createTable(tableName);
                //int count = insertRecord(tableName);
                //println(count + " records inserted.");
            }
        });

        Button button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText3.getText().toString().trim();
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();

                int age = -1;
                try{
                    age =Integer.parseInt(ageStr);
                }catch (Exception e){

                }

                insertData(name, age, mobile);
            }
        });

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = tableNameInput.getText().toString().trim();
                selectData(title);
            }
        });


        status = (TextView) findViewById(R.id.status);

    }

    private void createDatabase(String name) {
        println("creating database [" + name + "].");

        try {
            db = openOrCreateDatabase(name, Activity.MODE_PRIVATE, null);

            databaseCreated = true;
            println("database is created.");
        } catch(Exception ex) {
            ex.printStackTrace();
            println("database is not created.");
        }
    }

    /*
    public void openDataase(String databaseName){
        DatabaseHelper helper = new DatabaseHelper(this, databaseName, null, 1);
        db = helper.getWritableDatabase();
    }*/

    private void createTable(String name) {

        if(db != null){
            println("테이블 생성됨 [" + name + "].");

            db.execSQL("create table if not exists " + name + "("
                    + " _id integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " age integer, "
                    + " phone text);" );

            tableCreated = true;
        }else{
            println("먼저 데이터베이스를 오픈하시오");
        }

    }

    private int insertRecord(String name) {
        println("inserting records into table " + name + ".");

        int count = 3;
        db.execSQL( "insert into " + name + "(name, age, phone) values ('John', 20, '010-7788-1234');" );
        db.execSQL( "insert into " + name + "(name, age, phone) values ('Mike', 35, '010-8888-1111');" );
        db.execSQL( "insert into " + name + "(name, age, phone) values ('Sean', 26, '010-6677-4321');" );

        return count;
    }

    /**
     * insert records using parameters
     */
    private int insertRecordParam(String name) {
        println("inserting records using parameters.");

        int count = 1;
        ContentValues recordValues = new ContentValues();

        recordValues.put("name", "Rice");
        recordValues.put("age", 43);
        recordValues.put("phone", "010-3322-9811");
        int rowPosition = (int) db.insert(name, null, recordValues);

        return count;
    }

    public void insertData(String name, int age, String phone){
        println("insertData() 호출됨");

        if(db != null){
            String sql ="insert into customer(name, age, phone) values(?, ?, ?)";
            Object[] params = {name, age, phone};

            db.execSQL(sql, params);

            println("데이터 추가함.");
        }else{
            println("먼저 데이터베이스를 오픈하시오");
        }
    }


    public void selectData(String editText){
        println("selectData() 호출됨.");

        if(db != null){
            String sql = "select name, age, phone from " + editText;
            Cursor cursor = db.rawQuery(sql, null);

            println("조회된 데이터 갯수" + cursor.getCount());

            for(int i =0;  i< cursor.getCount(); i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String phone =  cursor.getString(2);

                println("#"+i+" -> "+ name +", "+age+", "+phone) ;
            }
            cursor.close();
        }
    }

    /**
     * update records using parameters
    // */
    private int updateRecordParam(String name) {
        println("updating records using parameters.");

        ContentValues recordValues = new ContentValues();
        recordValues.put("age", 43);
        String[] whereArgs = {"Rice"};

        int rowAffected = db.update(name,
                recordValues,
                "name = ?",
                whereArgs);

        return rowAffected;
    }

    /**
     * delete records using parameters
     */
    private int deleteRecordParam(String name) {
        println("deleting records using parameters.");

        String[] whereArgs = {"Rice"};

        int rowAffected = db.delete(name,
                "name = ?",
                whereArgs);

        return rowAffected;
    }

    private void println(String msg) {
        Log.d("MainActivity", msg);
        status.append("\n" + msg);

    }

    class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            println("onCreate() 호출됨");

            String name ="customer";

            println("테이블 생성됨.");

            sqLiteDatabase.execSQL("create table if not exists " + name + "(" + " _id integer PRIMARY KEY autoincrement, " + " name text, " + " age integer, " + " phone text);" );

            //createTable("customer");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
            println("onUpgrade 호출됨 : "+ oldversion +","+newversion);

            if(newversion>1){
                String tablename ="customor";
                sqLiteDatabase.execSQL("drop table if exists "+tableName);
            }
        }
    }

}
