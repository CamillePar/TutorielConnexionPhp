package com.example.camille.tutorielconnexionphp;



import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Created by Camille on 25/11/2015.
 */
public class SigninActivity extends AsyncTask<String,Void,String> {

    private TextView statusField, roleField;
    private Context context;
    private int byGetOrPost = 0;

    public SigninActivity(Context context, TextView statusField, TextView roleField, int flag) {
        this.statusField = statusField;
        this.roleField = roleField;
        this.context = context;
        byGetOrPost = flag;
    }

    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... arg0) {
        if (byGetOrPost == 0) {

            try {

                System.out.println("in get method");


                String username = (String) arg0[0];
                String password = (String) arg0[1];
                String link = "http://myphpmysqlweb.hostei.com/login.php?username=" + username + "& password=" + password;

                URL url = new URL(link);

                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'GET' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return response.toString();

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        } else {
            try {
                String username = (String) arg0[0];
                String password = (String) arg0[1];

                String link = "http://myphpmysqlweb.hostei.com/loginpost.php";
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
                data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line = null;

                // Read Server Response
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                return sb.toString();

            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }


        }
    }
}