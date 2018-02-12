package com.example.aniwangeta.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Aniwange.TA on 2/12/2018.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context ctx;
    BackgroundTask(Context ctx)
    {
        this.ctx =ctx;
    }
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");
    }
    @Override
    protected String doInBackground(String... params) {
       // String reg_url = "http://10.0.2.2/webapp/register.php";
        String login_url = "http://192.168.43.219/miwok_login/login.php";
        String type = params[0];
        /*if (type.equals("login")) {
            String user_name = params[1];
            String user_pass = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&" +
                           URLEncoder.encode("user_pass", "UTF-8") + "=" + URLEncoder.encode(user_pass, "UTF-8");
                        bufferedWriter.write(data);
                  bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "login Success";
            } catch(MalformedURLException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        */

         if(type.equals("login"))

        {
            String login_name = params[1];
            String login_pass = params[2];
            String device=params[3];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8")+"&"+URLEncoder.encode("device","UTF-8")+"="+URLEncoder.encode(device,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null)
                {
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("login Success"))
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
        else
        {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }

}
