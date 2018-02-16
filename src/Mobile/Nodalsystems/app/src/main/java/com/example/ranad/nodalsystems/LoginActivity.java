package com.example.ranad.nodalsystems;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ranad.nodalsystems.fragment.ForgotPasswordDialog;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ApiInterface;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener {

    @NotEmpty
    public static EditText userName;
    @NotEmpty
    public static EditText pwd;
    protected Validator validator;
    protected boolean validated;
    Button login;
    TextView forgot_pwd;
    LinearLayout main_contenier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        validator = new Validator(this);
        validator.setValidationListener(this);


        userName = (EditText) findViewById(R.id.user_name);
        pwd = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        forgot_pwd = (TextView) findViewById(R.id.forgot_pwd);
        forgot_pwd.setOnClickListener(this);
        main_contenier = (LinearLayout) findViewById(R.id.main_contenier);
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgot_pwd:
                ForgotPasswordDialog forgotPasswordDialog = new ForgotPasswordDialog();
                forgotPasswordDialog.show(getSupportFragmentManager(), "Forgot Password");
                break;
            case R.id.login:

                if (!NetworkChecker.isConnected(getApplicationContext())) {
                    NetworkChecker.noNetworkDialogLogin(this, 2);
                } else {

                    validator.validate();
                }

                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        //Log.i("CCCC",getApplicationContext()+"");

        validated = true;
        final ProgressDialog progressDialog = DialogUtils.progressDialog(this, "Login Status.", "Processing...");
        progressDialog.show();

        ApiInterface loginService =
                ApiClient.createService(ApiInterface.class, userName.getText().toString(), pwd.getText().toString());
        Call<Login> call = loginService.basicLogin();
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {


                    if (response.isSuccessful() && response.body().getUser().getActive()) {
                        DialogUtils.dismissProgress(progressDialog);
                        Log.d("response", response.body().toString());
                        response.body().saveLogin(LoginActivity.this);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        DialogUtils.alertDialog(LoginActivity.this, "Login Error.", "Invalid UserName or passoword", 2);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DialogUtils.dismissProgress(progressDialog);
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getApplicationContext());


            // Display error messages
            if (view instanceof Spinner) {
                Spinner sp = (Spinner) view;
                view = ((LinearLayout) sp.getSelectedView()).getChildAt(0);        // we are actually interested in the text view spinner has
            }

            if (view instanceof TextView) {
                TextView et = (TextView) view;
                et.setError(message);
            }
        }

    }


}
