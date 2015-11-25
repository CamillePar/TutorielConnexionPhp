package com.example.camille.tutorielconnexionphp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
/**
 * Created by Camille on 25/11/2015.
 */
public class SigninActivity extends AsyncTask<String,Void,String>{

    private TextView statusField, roleField;
    private Context context;
    private int byGetOrPost = 0;

    public SigninActivity(Context context,TextView statusField, TextView roleField,int flag) {
        this.statusField = statusField;
        this.roleField = roleField;
        this.context = context;
        byGetOrPost = flag;
    }

    protected void onPreExecute(){}

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
