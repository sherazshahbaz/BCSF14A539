package com.example.ather.notes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


public class SharedPref {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    private static final String PREFER_NAME = "UserSession";
    private static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    public SharedPref(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREFER_NAME, 0);
        editor = pref.edit();
    }

    public void createUserLoginSession(String name,String email){

        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public boolean checkLogin(){
        if(!this.isUserLoggedIn()){

            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);

            return true;
        }
        return false;
    }

    public void logoutUser(){

        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    public boolean isUserLoggedIn(){
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public String get_name()
    {
        return pref.getString(KEY_NAME,null);
    }
}
