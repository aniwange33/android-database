package com.example.aniwangeta.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText usernameEt, passwordEt;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEt=(EditText)findViewById(R.id.user_id);
        passwordEt=(EditText)findViewById(R.id.password);
    }
    public void onLogin(View view){
        username=usernameEt.getText().toString();
        password=passwordEt.getText().toString();
        String type="login";
        String device ="android";
        BackgroundTask bt=new BackgroundTask(this);
        bt.execute(type,username,password, device);
    }
}
