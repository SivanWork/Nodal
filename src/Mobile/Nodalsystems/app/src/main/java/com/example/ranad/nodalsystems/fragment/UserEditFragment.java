package com.example.ranad.nodalsystems.fragment;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ranad.nodalsystems.MainActivity;
import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.ResponseData;
import com.example.ranad.nodalsystems.data_holder.UserData;
import com.example.ranad.nodalsystems.interfaces.GroupElementTypeAction;
import com.example.ranad.nodalsystems.interfaces.GroupTypeAction;
import com.example.ranad.nodalsystems.interfaces.SwitchFragment;
import com.example.ranad.nodalsystems.interfaces.UserAction;
import com.example.ranad.nodalsystems.model.GetAllElementTypes;
import com.example.ranad.nodalsystems.model.GetAllGroupTypes;
import com.example.ranad.nodalsystems.model.Login;
import com.example.ranad.nodalsystems.model.USERGETALL;
import com.example.ranad.nodalsystems.model.User;
import com.example.ranad.nodalsystems.model.UserInfo;
import com.example.ranad.nodalsystems.model.UserList;
import com.example.ranad.nodalsystems.restapi.ApiClient;
import com.example.ranad.nodalsystems.restapi.GroupTypeApi;
import com.example.ranad.nodalsystems.restapi.UserApi;
import com.example.ranad.nodalsystems.usage.FragmentSwitch;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserEditFragment extends Fragment implements View.OnClickListener, UserAction, GroupTypeAction, GroupElementTypeAction, Validator.ValidationListener {
    // View view, add_customer, outer_layout;

    protected Validator validator;
    protected boolean validated;

    View view;

    int userId, createdById;
    String createdDate;
    EditText midName;
    @Email
    EditText email;
    @Password
    EditText password;

    @NotEmpty(message = "Data Requried")
    @Pattern(regex = "^[^-\\s][a-zA-Z0-9_\\s-]+$", message = "Space Not Allowed as a First Charactor")
    EditText name, pin, addrs1, addrs2, state, country, city, ftName, lastName, activeFrom, activeTo;
    @Pattern(regex = "[789][0-9]{9}", message = "Please enter valid Phone number")
    EditText  number;
    Button edit, btncancel;
    //    ImageView ivAdd;
    // ListView listView;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    ArrayList<UserData> userData = new ArrayList<>();
    SwitchFragment switchFragment;
    //RecyclerView user_list;
    //UserAdapter userAdapter;
    ////UsersAdapter usersAdapter;
    ProgressDialog progressDialog = null;
    Spinner userType_spinner, userElement_spinner;
    List<UserList> userList = new ArrayList<UserList>();
    private RadioGroup radioStatusGroup;

    public UserEditFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserEditFragment newInstance(SwitchFragment switchFragment) {
        UserEditFragment fragment = new UserEditFragment();
        fragment.Construct(switchFragment);
        return fragment;
    }


    public void Construct(SwitchFragment switchFragment) {
        this.switchFragment = switchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_user, container, false);

        validator = new Validator(this);
        validator.setValidationListener(this);
        progressDialog = new ProgressDialog(getContext());
        userElement_spinner = (Spinner) view.findViewById(R.id.userElement);
        userType_spinner = (Spinner) view.findViewById(R.id.userType);
        name = (EditText) view.findViewById(R.id.user_name);
        password = (EditText) view.findViewById(R.id.password);
        number = (EditText) view.findViewById(R.id.mobileNum);
        email = (EditText) view.findViewById(R.id.email);
//        userType = (EditText) view.findViewById(R.id.userType);
        city = (EditText) view.findViewById(R.id.city);
        country = (EditText) view.findViewById(R.id.country);
        state = (EditText) view.findViewById(R.id.state);
        pin = (EditText) view.findViewById(R.id.pin);
        ftName = (EditText) view.findViewById(R.id.ftName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        midName = (EditText) view.findViewById(R.id.midName);
        addrs1 = (EditText) view.findViewById(R.id.addrs1);
        addrs2 = (EditText) view.findViewById(R.id.adrs2);
        edit = (Button) view.findViewById(R.id.edit);
        edit.setOnClickListener(this);

        activeFrom = (EditText) view.findViewById(R.id.activeFrom);
        activeTo = (EditText) view.findViewById(R.id.activeTo);
        btncancel = (Button) view.findViewById(R.id.btnCancel);

        btncancel.setOnClickListener(this);
        activeTo.setOnClickListener(this);
        activeFrom.setOnClickListener(this);

        List<String> list = new ArrayList<String>();
        list.add("USER");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userType_spinner.setAdapter(dataAdapter);

        radioStatusGroup = (RadioGroup) view.findViewById(R.id.radioStatus);


        List<String> list2 = new ArrayList<String>();


        list2.add("Admin");
        list2.add("Agent");
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list2);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userElement_spinner.setAdapter(dataAdapter2);


        if (getArguments() != null) {
            userId = getArguments().getInt("userid");
            readUser(userId);
        }
        return view;
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.edit:


                validator.validate();

                break;
            case R.id.btnCancel:

                Fragment fragment = new UserFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                MainActivity.setAppTitle(R.string.user_title);
                break;
            case R.id.activeFrom:
                final Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        cal.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        String d = dateFormat.format(cal.getTime());
                        activeFrom.setText(d);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.setButton(TimePickerDialog.BUTTON_POSITIVE, getContext().getString(R.string.ok), datePickerDialog);
                datePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, getContext().getString(R.string.cancel), datePickerDialog);
                datePickerDialog.show();
                break;
            case R.id.activeTo:
                final Calendar c = Calendar.getInstance(TimeZone.getDefault());
                DatePickerDialog datePicker = new DatePickerDialog(getContext(), R.style.AlertDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        c.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                        String d = dateFormat.format(c.getTime());
                        activeTo.setText(d);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker.setButton(TimePickerDialog.BUTTON_POSITIVE, getContext().getString(R.string.ok), datePicker);
                datePicker.setButton(TimePickerDialog.BUTTON_NEGATIVE, getContext().getString(R.string.cancel), datePicker);
                datePicker.show();
                break;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity.setAppTitle(R.string.edit_user);
        /*MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.showUpButton();
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void readUser(int userId) {
        UserApi userApi =
                ApiClient.createService(UserApi.class, Login.getInstance(getContext()).getAuthToken());
        Call<USERGETALL> call = userApi.getUserAPI("http://cellordering.com/api/User/GetUser?userId=" + userId);

        call.enqueue(new Callback<USERGETALL>() {
            @Override
            public void onResponse(Call<USERGETALL> call, Response<USERGETALL> response) {
                fetchUser(response.body().getUser());
            }


            @Override
            public void onFailure(Call<USERGETALL> call, Throwable t) {

            }
        });

    }

    @Override
    public void fetchUser(User user) {
//Log.i("HERE","HERE"+user.getUsername());

        userId = user.getUserId();

        createdById = user.getCreatedById();
        createdDate = user.getCreatedDate();

        name.setText(user.getUsername());
        password.setText(user.getPassword());

        number.setText(user.getMobile());
        email.setText(user.getEmail());

        city.setText(user.getCity());
        country.setText(user.getCountry());
        state.setText(user.getState());
        pin.setText(user.getPin());
        ftName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        midName.setText(user.getMiddleName());
        addrs1.setText(user.getAddress1());
        addrs2.setText(user.getAddress2());

        activeFrom.setText(user.getActiveFrom());
        activeTo.setText(user.getActiveTo());


        int elementpos;
        if (user.getUserElementCode() == "Admin")
            elementpos = 0;
        else
            elementpos = 1;

        userElement_spinner.setSelection(elementpos);

        if (user.isIsActive())
            radioStatusGroup.check(R.id.radioActive);
        else
            radioStatusGroup.check(R.id.radioPassive);

    }

    @Override
    public void switchToEditUser(int user) {

        Log.i("PATTABHI", "CCC" + user);

        Fragment fragment = new UserEditFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public ArrayList<UserData> readAllUsers() {
        return null;
    }

    @Override
    public User getUser() {

        boolean userStatus;
        int selectedId = radioStatusGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.radioActive)
            userStatus = true;
        else userStatus = false;


        User user = new User();

        user.setUserId(userId);

        user.setCreatedById(createdById);
        user.setCreatedDate(createdDate);

        user.setFirstName(ftName.getText().toString());
        user.setLastName(lastName.getText().toString());
        user.setMiddleName(midName.getText().toString());
        user.setActiveFrom(activeFrom.getText().toString());
        user.setActiveTo(activeTo.getText().toString());
        user.setAddress1(addrs1.getText().toString());
        user.setAddress2(addrs2.getText().toString());
        user.setCity(city.getText().toString());
        user.setState(state.getText().toString());
        user.setCountry(country.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setMobile(number.getText().toString());
        user.setIsActive(userStatus);
        user.setPin(pin.getText().toString());
//        user.setCreatedById(Login.getInstance(getContext()).getUser().getUserId());
        //       user.setCreatedDate(getCurrentDate().toString());
        user.setLastUpdatedById(Login.getInstance(getContext()).getUser().getUserId());
        user.setLastUpdatedDate(getCurrentDate().toString());

        user.setUserGroupType(userType_spinner.getSelectedItem().toString());

        user.setUserElementCode(userElement_spinner.getSelectedItem().toString());
        user.setUsername(name.getText().toString());
        //  user.setUserElementCode(userElementCode.getText().toString());

        Log.i("Data", "DDD");
        return user;

    }


    @Override
    public void createUser(User user) {

    }


    @Override
    public void updateUser(User user) {

        UserInfo userInfo = new UserInfo(user);

        UserApi userApi =
                ApiClient.createService(UserApi.class, Login.getInstance(getContext()).getAuthToken());

        Call<ResponseData> call = userApi.updateUserAPI(userInfo);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                dismissProgress();
                if(response.body().isSuccess())
                showAlert("User Update Status", "Success", 2);
                else
                    showAlert("User Update Status", "Fail", 2);
                FragmentSwitch.switchTo(getActivity(), new UserFragment(), R.string.user_title);
            }


            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });


    }

    @Override
    public void deleteUser(User user) {
    }

    public String getCurrentDate() {

        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());

    }

    public String getUTCDate(String strDate) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date(strDate));
    }


    public void showAlert(String title, String msg, int theme) {


        if (theme == 1) theme = R.style.Theme_AppCompat_DayNight_Dialog;
        else theme = R.style.Theme_AppCompat_DayNight_Dialog_Alert;


        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                getContext(), R.style.Theme_AppCompat_DayNight_Dialog);
        alertDialogBuilder.setTitle(title);

        // set dialog message
        alertDialogBuilder
                .setMessage(msg)
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showProgress(String title, String msg, int theme) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

    }

    public void dismissProgress() {
        progressDialog.dismiss();
    }


    @Override
    public GetAllGroupTypes getAllGroupTypes() {
        GroupTypeApi groupTypeApi = ApiClient.createService(GroupTypeApi.class, Login.getInstance(getContext()).getAuthToken());
        Call<GetAllGroupTypes> call = groupTypeApi.getAllGroupTypesAPI();

        call.enqueue(new Callback<GetAllGroupTypes>() {
            @Override
            public void onResponse(Call<GetAllGroupTypes> call, Response<GetAllGroupTypes> response) {


                List<String> list = new ArrayList<String>();
                if (response.body().getGroupTypeList() != null) {
                    for (int i = 0; i < response.body().getGroupTypeList().size(); i++) {

                        list.add(response.body().getGroupTypeList().get(i).getGroupCode());

                    }
                } else {

                    list.add("USER");

                }

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userType_spinner.setAdapter(dataAdapter);


            }

            @Override
            public void onFailure(Call<GetAllGroupTypes> call, Throwable t) {

            }


        });


        return null;
    }

    @Override
    public GetAllElementTypes getAllElementTypes() {
/*
        GroupElementTypeApi groupElementTypeApi = ApiClient.createService(GroupElementTypeApi.class, Login.getInstance(getContext()).getAuthToken());
        Call<GetAllElementTypes> call = groupElementTypeApi.getAllElementTypesAPI();

        call.enqueue(new Callback<GetAllElementTypes>() {
            @Override
            public void onResponse(Call<GetAllElementTypes> call, Response<GetAllElementTypes> response) {
                List<String> list = new ArrayList<String>();
                if (response.body().getGroupElementCodeList() != null) {


                    for (int i = 0; i < response.body().getGroupElementCodeList().size(); i++) {

                        list.add(response.body().getGroupElementCodeList().get(i).getElementCode());
                    }


                } else {

                    for (int i = 0; i < 2 && list.isEmpty(); i++) {

                        list.add("Admin");
                        list.add("Agent");
                    }
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                userElement_spinner.setAdapter(dataAdapter);

            }

            @Override
            public void onFailure(Call<GetAllElementTypes> call, Throwable t) {

            }


        });

*/

        return null;
    }

    @Override
    public void onValidationSucceeded() {
        validated = true;

        showProgress("User Update.", "Processing", 2);
        updateUser(getUser());


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        validated = false;

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getContext());


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
