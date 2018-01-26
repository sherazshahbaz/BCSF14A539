package com.example.ather.notes;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class Registration extends AppCompatActivity implements View.OnClickListener {

    private EditText useret;
    private EditText emailet;
    private EditText passwordet;
    private EditText dateet;
    private Spinner gender;
    private Button signupbt;
    private ImageView mImageView;


    SharedPref obj;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        obj = new SharedPref(getApplicationContext());

        helper=new DatabaseHelper(this,null,1);

        useret = (EditText)findViewById(R.id.useret);
        emailet = (EditText)findViewById(R.id.emailet);
        passwordet = (EditText)findViewById(R.id.passwordet);
        dateet = (EditText)findViewById(R.id.dateet);
        gender=(Spinner) findViewById(R.id.gender);
        signupbt = (Button)findViewById(R.id.regbt);
        mImageView = (ImageView) findViewById(R.id.imageView);

        signupbt.setOnClickListener(this);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position==0)
                    mImageView.setImageResource(R.drawable.male);
                else if (position==1)
                    mImageView.setImageResource(R.drawable.female);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.regbt)
        {
            if(gender.getSelectedItem().equals("Male")) {
                helper.insert_user(useret.getText().toString(), emailet.getText().toString(), passwordet.getText().toString(), dateet.getText().toString(),1 );

                obj.createUserLoginSession(useret.getText().toString(),emailet.getText().toString());

                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

                Toast.makeText(this, "You are Registered Successfully.", Toast.LENGTH_SHORT);
            }
            else
            {
                helper.insert_user(useret.getText().toString(), emailet.getText().toString(), passwordet.getText().toString(), dateet.getText().toString(),0 );

                obj.createUserLoginSession(useret.getText().toString(),emailet.getText().toString());



                Intent i = new Intent(this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                Toast.makeText(this, "You are Registered Successfully.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onStart()
    {
        super.onStart();
        dateet.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft=getFragmentManager().beginTransaction();
                    dialog.show(ft,"DatePicker");
                }
            }
        });
    }
}
