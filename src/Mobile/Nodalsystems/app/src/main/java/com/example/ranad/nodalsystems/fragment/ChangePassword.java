package com.example.ranad.nodalsystems.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ranad.nodalsystems.App;
import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.ChangePasswordData;
import com.example.ranad.nodalsystems.data_holder.ResponseData;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends Fragment implements View.OnClickListener {
    View view;
    Context context;
    EditText new_pwd, renter_pwd;
    Button cancel, change;


    public ChangePassword() {
        // Required empty public constructor
    }

    public void construct() {

    }

    public static ChangePassword newInstance() {
        ChangePassword fragment = new ChangePassword();
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
        view = inflater.inflate(R.layout.fragment_change_password, container, false);

       /* WindowManager.LayoutParams w = new WindowManager.LayoutParams();
        w.copyFrom(getDialog().getWindow().getAttributes());
        w.width = WindowManager.LayoutParams.MATCH_PARENT;
        w.gravity = Gravity.CENTER;
        getDialog().getWindow().setAttributes(w);*/

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
       /* Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.changepwd_title);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

   /* @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_DeviceDefault_Dialog);
        } else {
            setStyle(STYLE_NO_TITLE, android.R.style.Theme_Material_Dialog);
        }
        return super.onCreateDialog(savedInstanceState);
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                getActivity().getFragmentManager().popBackStack();
                break;
            case R.id.change:
                if (new_pwd.getText().toString().equals(renter_pwd.getText().toString())) {
                    Toast.makeText(getContext(), "Changed Password", Toast.LENGTH_SHORT).show();
                    ChangePasswordData changePasswordData = new ChangePasswordData();
                    changePasswordData.setUserId(Login.getInstance(getContext()).getUser().getUserId());
                    changePasswordData.setPassword(renter_pwd.getText().toString()) ;
                    ApiInterface apiInterface =
                            ApiClient.createService(ApiInterface.class, Login.getInstance(getContext()).getAuthToken());
                    Call<ChangePasswordData> call = apiInterface.changePassword(changePasswordData);
                    call.enqueue(new Callback<ChangePasswordData>() {
                        @Override
                        public void onResponse(Call<ChangePasswordData> call, Response<ChangePasswordData> response) {
                            Log.d("response", response.body().toString());
                            if (response.code() == 200){
                                ResponseData responseData = new ResponseData();
                                if (responseData.isSuccess()){
                                    Toast.makeText(getContext(), "Password changedd succesfully..!", Toast.LENGTH_SHORT).show();
                                    getActivity().getFragmentManager().popBackStack();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangePasswordData> call, Throwable t) {
                            Toast.makeText(getContext(), "Unable to change Password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
        }

    }
}
