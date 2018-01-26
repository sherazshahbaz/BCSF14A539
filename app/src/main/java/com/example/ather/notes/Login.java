package com.example.ather.notes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText password;
    private  EditText email;
    private Button login;
    private Button registrationbt;

    boolean check_email,check_password;

    DatabaseHelper helper;
    SharedPref obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        helper=new DatabaseHelper(this,null,1);
        obj = new SharedPref(getApplicationContext());

        email = (EditText) findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);
        registrationbt = (Button)findViewById(R.id.regbt);

        login.setOnClickListener(this);
        registrationbt.setOnClickListener(this);

        check_email =false;
        check_password = false;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.login)
        {
            Cursor c = helper.get_users();
            c.moveToFirst();
            for(int  i=0; i<c.getCount();i++)
            {

                if(c.getString(2).equals(email.getText().toString()) && c.getString(3).equals(password.getText().toString()) )
                {
                    check_email = false;
                    check_password = false;

                    obj.createUserLoginSession(c.getString(1),c.getString(2));

                    Intent k = new Intent(this, MainActivity.class);
                    k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    k.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    k.putExtra("Username",c.getString(1));
                    startActivity(k);
                    finish();


                }
                else {

                    if(!(c.getString(2).equals(email.getText().toString()) && !c.getString(3).equals(password.getText().toString()))){
                        check_email = true;
                        check_password = true;
                    }
                    else if (c.getString(2).equals(email.getText().toString()))
                        check_email = true;
                    else if (c.getString(3).equals(password.getText().toString()))
                        check_password = true;

                }

                c.moveToNext();

            }

            if(check_email == true && check_password== true)
            {
                Toast.makeText(this, "Incorrect Email & Password.", Toast.LENGTH_SHORT).show();
                check_password = false;
                check_email = false;
            }
            else if(check_password== true) {
                Toast.makeText(this, "Incorrect Email.", Toast.LENGTH_SHORT).show();
                check_password = false;
                check_email = false;
            }
            else if(check_email == true) {
                Toast.makeText(this, "Incorrect Password.", Toast.LENGTH_SHORT).show();
                check_email = false;
                check_password = false;
            }
        }

        if(v.getId() == R.id.regbt)
        {
            Intent i = new Intent(this, Registration.class);
            startActivity(i);
        }

    }
}
