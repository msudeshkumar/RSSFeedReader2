package com.tech.msudesh_kumar.rssfeedreader2;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        /*TextView tvr = toolbar.findViewById(R.id.toolbar_title_textview);
        tvr.setText("Categories");*/

        mDB = new MyDatabase(this);
        mDB.open();

        FragmentManager frm = getSupportFragmentManager();
        FragmentTransaction frt = frm.beginTransaction();
        Categories_fragment cf = new Categories_fragment();
        frt.replace(R.id.main_container,cf);
        //frt.addToBackStack(null);
        frt.commit();

        //b1 = findViewById(R.id.main_button1);
        /*b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager frm = getSupportFragmentManager();
                FragmentTransaction frt = frm.beginTransaction();
                Categories_fragment cf = new Categories_fragment();
                frt.replace(R.id.main_container,cf);
                frt.addToBackStack(null);
                frt.commit();

            }
        });*/

        /*ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDB.close();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        //MenuItem m1 = menu.findItem(R.id.action_search);
        *//*m1.setVisible(false);*//*
        *//*m1.setEnabled(false);*//*

        return super.onCreateOptionsMenu(menu);
    }*/

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Back!!", Toast.LENGTH_SHORT).show();
    }*/
}
