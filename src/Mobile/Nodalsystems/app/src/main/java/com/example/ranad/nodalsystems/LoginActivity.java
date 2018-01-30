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
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranad.nodalsystems.fragment.ChangePassword;
import com.example.ranad.nodalsystems.fragment.HomeFragment;
import com.example.ranad.nodalsystems.fragment.OrderFragment;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static EditText userName;
    public static EditText pwd;
    Button login;
    TextView forgot_pwd;
    LinearLayout main_contenier;
    public static String user="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = (EditText) findViewById(R.id.user_name);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        forgot_pwd = (TextView) findViewById(R.id.forgot_pwd);
        forgot_pwd.setOnClickListener(this);
        main_contenier = (LinearLayout)findViewById(R.id.main_contenier);
        login.setOnClickListener(this);
    }

    public static String getUserType(){
        if (userName.getText().toString().equals("admin")  && pwd.getText().toString().equals("admin@123")){
            user = "Admin";
        }else if (userName.getText().toString().equals("testuser")  && pwd.getText().toString().equals("user@123")){
            user = "customer";
        }
        return user;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgot_pwd:
                ChangePassword changePassword = new ChangePassword();
                changePassword.show(getSupportFragmentManager(), "Change Password");

                break;
            case R.id.login:
               /* getUserType();
                Log.d("user", user);
                if (user.equals("Admin") || user.equals("customer")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter valid username or password", Toast.LENGTH_SHORT).show();
                }*/

                ApiInterface loginService =
                        ApiClient.createService(ApiInterface.class, userName.getText().toString(), pwd.getText().toString());
                Call<Login> call = loginService.basicLogin();
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if (response.isSuccessful()) {
                            // user object available
                            Log.d("response", response.body().toString());

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // error response, no access to resource?
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

                break;
        }
    }
}
