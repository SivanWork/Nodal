package com.example.ranad.nodalsystems.fragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForgotPasswordDialog extends DialogFragment implements View.OnClickListener {
    View view;
    EditText emailId;
    Button cancel, ok;


    public ForgotPasswordDialog() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ForgotPasswordDialog newInstance() {
        ForgotPasswordDialog fragment = new ForgotPasswordDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.forgot_password_dialog, container, false);
        emailId = (EditText) view.findViewById(R.id.email_id);
        cancel = (Button) view.findViewById(R.id.cancel);
        ok = (Button) view.findViewById(R.id.okay);
        cancel.setOnClickListener(this);
        ok.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(500, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog);
        } else {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog);
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                dismiss();
                break;
            case R.id.okay:
                ApiInterface apiInterface = ApiClient.forgotPassword(getContext());
                Call<Login> c = apiInterface.forgotPassword(emailId.getText().toString());
                c.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {

                        Log.d("response of forgotpwd", response.body().toString());
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

                    }
                });
                break;
        }

    }
}
