package com.example.ranad.nodalsystems.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranad.nodalsystems.LoginActivity;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.ChangePasswordData;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.Logout;
import com.example.ranad.nodalsystems.model.UserInfo;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ApiInterface;
import com.example.ranad.nodalsystems.restapi.UserApi;
import com.example.ranad.nodalsystems.usage.DialogUtils;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.example.ranad.nodalsystems.usage.NetworkChecker;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends DialogFragment implements View.OnClickListener, Validator.ValidationListener  {
    View view;
    Context context;
    @NotEmpty
    EditText new_pwd, renter_pwd;
    Button cancel, change;
    protected Validator validator;
    protected boolean validated;


    public ChangePassword() {
        // Required empty public constructor
    }

    public static ChangePassword newInstance() {
        ChangePassword fragment = new ChangePassword();
        return fragment;
    }

    public void construct() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

       /* WindowManager.LayoutParams w = new WindowManager.LayoutParams();
        w.copyFrom(getDialog().getWindow().getAttributes());
        w.width = WindowManager.LayoutParams.MATCH_PARENT;
        w.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(w);*/

        validator = new Validator(this);
        validator.setValidationListener(this);


        new_pwd = (EditText) view.findViewById(R.id.new_pwd);
        renter_pwd = (EditText) view.findViewById(R.id.renter_pwd);
        change = (Button) view.findViewById(R.id.change);
        cancel = (Button) view.findViewById(R.id.cancel);
        change.setOnClickListener(this);
        cancel.setOnClickListener(this);

        return view;
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
    public void onResume() {
        super.onResume();
        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

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
                FragmentSwitch.switchTo(getActivity(),new HomeFragment(),R.string.home_title);
                break;
            case R.id.change:

                validator.validate();

                break;
        }

    }

    @Override
    public void onValidationSucceeded() {

        if(NetworkChecker.isConnected(getContext())) {
           final ProgressDialog progressDialog= DialogUtils.progressDialog(getActivity(),"Password Change","Processing...");

            if (new_pwd.getText().toString().equals(renter_pwd.getText().toString())) {
                //Toast.makeText(getContext(), "Changed Password", Toast.LENGTH_SHORT).show();
                progressDialog.show();
                ChangePasswordData changePasswordData = new ChangePasswordData();
                changePasswordData.setPassword(new_pwd.getText().toString());
                changePasswordData.setUserId(Login.getInstance(getContext()).getUser().getUserId());
                ApiInterface changePasswordApi =
                        ApiClient.createService(ApiInterface.class, Login.getInstance(getContext()).getAuthToken());

                Call<ChangePasswordData> call = changePasswordApi.changePassword(changePasswordData);
                call.enqueue(new Callback<ChangePasswordData>() {
                    @Override
                    public void onResponse(Call<ChangePasswordData> call, Response<ChangePasswordData> response) {

                        Log.i("CREATERES", "RESPONSE" + response.body().toString());
                        DialogUtils.dismissProgress(progressDialog);
                        // DialogUtils.alertDialog(getContext(),"Password Change","Password hasbeen changed successfully...",2);
                        Logout.logout(getContext());


                        // showAlert("User Creation.", "Success!", 1);
                        //   FragmentSwitch.switchTo(getActivity(), new UserFragment(), R.string.user_title);
//                userAdapter.notifyDataSetChanged();
                    }


                    @Override
                    public void onFailure(Call<ChangePasswordData> call, Throwable t) {

                    }
                });

            }
            else {
                DialogUtils.alertDialog(getContext(),"Change Password","Confirm Password not matching",2);

            }
        }
        else
        {
NetworkChecker.noNetworkDialog(getContext(),getActivity(),2);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());


            // Display error messag
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
