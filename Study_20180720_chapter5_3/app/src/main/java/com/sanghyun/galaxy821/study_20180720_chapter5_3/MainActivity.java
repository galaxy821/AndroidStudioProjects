package com.sanghyun.galaxy821.study_20180720_chapter5_3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
    Dial_fragment fragment1;
    Mainfragment fragment2;
    Setting_fragment fragment3;

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

        fragment1 = new Dial_fragment();
        fragment2 = new Mainfragment();
        fragment3 = new Setting_fragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
        Log.d("SINGER1", "singer id ="+R.drawable.singer);
        Log.d("SINGER2", "singer id ="+R.drawable.singer2);
        Log.d("SINGER3", "singer id ="+R.drawable.singer3);
        Log.d("SINGER4", "singer id ="+R.drawable.singer4);
        Log.d("SINGER5", "singer id ="+R.drawable.singer5);

        TabItem item2 = (TabItem) findViewById(R.id.button_address);
        TabItem item1 = (TabItem) findViewById(R.id.button_dial);
        TabItem item3 = (TabItem) findViewById(R.id.button_setting);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position ==0){
                    selected = fragment1;
                }else if(position ==1){
                    selected = fragment2;
                }else if(position ==2){
                    selected = fragment3;
                }

                //getFragmentManager().beginTransaction().addToBackStack(null).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}