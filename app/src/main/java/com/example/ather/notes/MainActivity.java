package com.example.ather.notes;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ImageButton addNotebt;
    private Button logoutbt;

    private ImageButton btnLeftDrawer;
    private ImageButton btnRightDrawer;

    private DrawerLayout dlMain;
    private ListView lvLeft;
    private ListView lvRight;
    private ArrayList<String> alLeft;
    private ArrayList<String> alRight;
    private ArrayAdapter<String> aaLeft;
    private ArrayAdapter<String> aaRight;


    SharedPref obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        obj = new SharedPref(getApplicationContext());

        logoutbt = (Button)findViewById(R.id.logoutbt);
        addNotebt = (ImageButton) findViewById(R.id.addNotebt);
        addNotebt.setOnClickListener(this);
        logoutbt.setOnClickListener(this);

        if(obj.checkLogin())
            finish();

        Toolbar toolbar = (Toolbar) findViewById(R.id.tbMain);
        dlMain = (DrawerLayout) findViewById(R.id.dlMain);

        btnLeftDrawer = (ImageButton) toolbar.findViewById(R.id.btnLeftDrawer);
        btnRightDrawer = (ImageButton) toolbar.findViewById(R.id.btnRightDrawer);
        btnLeftDrawer.setOnClickListener(this);
        btnRightDrawer.setOnClickListener(this);

        alLeft = new ArrayList<String>();
        alLeft.add("Inbox");
        alLeft.add("Sent Items");
        alLeft.add("Log Out");
        aaLeft = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alLeft);
        lvLeft = (ListView) findViewById(R.id.lvLeft);
        lvLeft.setAdapter(aaLeft);
        lvLeft.setOnItemClickListener(this);

        alRight = new ArrayList<String>();
        alRight.add("See Online Users");
        alRight.add("See All Users");
        aaRight = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alRight);
        lvRight = (ListView) findViewById(R.id.lvRight);
        lvRight.setAdapter(aaRight);
        lvRight.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.logoutbt)
        {
            obj.logoutUser();
        }

        if (v.getId() == R.id.addNotebt)
        {

        }

        if (v.getId() == R.id.btnLeftDrawer)
        {
            if (dlMain.isDrawerOpen(Gravity.LEFT)) {
                dlMain.closeDrawer(Gravity.LEFT);
            }
            else {
                dlMain.openDrawer(Gravity.LEFT);
            }
        }

        if (v.getId() == R.id.btnRightDrawer)
        {
            if (dlMain.isDrawerOpen(Gravity.RIGHT)) {
                dlMain.closeDrawer(Gravity.RIGHT);
            }
            else {
                dlMain.openDrawer(Gravity.RIGHT);
            }
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lvLeft : {
                Toast.makeText(this, alLeft.get(position), Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.lvRight : {
                Toast.makeText(this, alRight.get(position), Toast.LENGTH_SHORT).show();
                break;
            }
            default : {
                break;
            }
        }
    }
}
