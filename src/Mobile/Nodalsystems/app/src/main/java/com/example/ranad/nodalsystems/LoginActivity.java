package com.example.ranad.nodalsystems;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.ranad.nodalsystems.model.Users;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ApiInterface;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static EditText userName;
    public static EditText pwd;
    Button login;
    TextView forgot_pwd;
    LinearLayout main_contenier;
    public static String user = "";

    String auth_token, userid, email, mobile, city, country, adrs1, adrs2, fn, ln, mn, role;
    ProgressDialog progressDialog=null;

    //SharedPreferences userPref = getApplicationContext().getSharedPreferences("UserPref", 0); // 0 - for private mode

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        userName = (EditText) findViewById(R.id.user_name);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        forgot_pwd = (TextView) findViewById(R.id.forgot_pwd);
        forgot_pwd.setOnClickListener(this);
        main_contenier = (LinearLayout) findViewById(R.id.main_contenier);
        login.setOnClickListener(this);



    }

    /*public  String getUserType() {
      return   userPref.getString("userType", null);

    }
*/
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgot_pwd:
                ChangePassword changePassword = new ChangePassword();
                changePassword.show(getSupportFragmentManager(), "Change Password");

                break;
            case R.id.login:
                showProgress("User Login","Processing...",2);
                ApiInterface loginService =
                        ApiClient.createService(ApiInterface.class, userName.getText().toString(), pwd.getText().toString());
                Call<Login> call = loginService.basicLogin();
                call.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        try {

                            if (response.isSuccessful()) {
                                dismissProgress();
                                Log.d("response", response.body().toString());
                                response.body().saveLogin(LoginActivity.this);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                // error response, no access to resource?

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
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
    public void showProgress(String title, String msg, int theme) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);

        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }
    public void dismissProgress() {
        progressDialog.dismiss();
    }
}
