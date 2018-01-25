package com.example.ranad.nodalsystems;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ranad.nodalsystems.fragment.HomeFragment;
import com.example.ranad.nodalsystems.fragment.OrderFragment;

public class LoginActivity extends AppCompatActivity {
    public static EditText userName;
    public static EditText pwd;
    Button login;
    LinearLayout main_contenier;
    public static String user="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.user_name);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        main_contenier = (LinearLayout)findViewById(R.id.main_contenier);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserType();
                Log.d("user", user);
                if (user.equals("Admin") || user.equals("customer")){
               Intent intent = new Intent(LoginActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter valid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public static String getUserType(){
        if (userName.getText().toString().equals("admin")  && pwd.getText().toString().equals("admin@123")){
            user = "Admin";
        }else if (userName.getText().toString().equals("testuser")  && pwd.getText().toString().equals("user@123")){
            user = "customer";
        }
            return user;
    }

}
