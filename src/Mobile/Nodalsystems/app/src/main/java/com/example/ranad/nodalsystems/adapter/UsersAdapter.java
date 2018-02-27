package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.UserData;
import com.example.ranad.nodalsystems.fragment.UserFragment;
import com.example.ranad.nodalsystems.interfaces.UserAction;
import com.example.ranad.nodalsystems.usage.DialogUtils;

import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {
    ArrayList<UserData> userData;
    Context context;
    InputMethodManager inputMethodManager;
    UserAction userAction;

    public UsersAdapter(List<UserData> userData, Context context, UserFragment userAction) {
        this.context = context;
        this.userData = (ArrayList<UserData>) userData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.userAction = (UserAction) userAction;
    }

    public void updateList(List<UserData> list) {
        userData = (ArrayList<UserData>) list;
        notifyDataSetChanged();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.total_user_listing, parent, false);
        return new UsersAdapter.UserViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(UsersAdapter.UserViewHolder holder, int position) {
        holder.bindData(userData, position);
    }

    @Override
    public int getItemCount() {
        return this.userData.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory {
        View itemView;
        TextView userName, name, mobile, isActive, userElementCode;

        ImageView edit, delete,usericon;
        String message;
        LinearLayout linearLayout;

        public UserViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            userName = (TextView) itemView.findViewById(R.id.username);

            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            isActive = (TextView) itemView.findViewById(R.id.isActive);
            userElementCode = (TextView) itemView.findViewById(R.id.elementCode);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            usericon = (ImageView) itemView.findViewById(R.id.usericon);
        }

        public void bindData(final ArrayList<UserData> userData, final int position) {
            //  Log.e("dFGFH", "PPPP" + userData.size());
            userName.setText(userData.get(position).getUserName());


            name.setText(userData.get(position).getFirstName() + " " + userData.get(position).getLastName());
            mobile.setText(userData.get(position).getMobile());
            if (userData.get(position).isActive()) {
                isActive.setText("Active");
                delete.setImageResource(R.drawable.delete);
                usericon.setImageResource(R.drawable.user_active);
               // linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));

            } else {
                isActive.setText("InActive");
                delete.setImageResource(R.drawable.delete_undo);
                usericon.setImageResource(R.drawable.user_inactive);
               // linearLayout.setBackgroundColor(Color.parseColor("#A9A9A9"));

            }

            userElementCode.setText(userData.get(position).getUserElementCode());

            //   DialogUtils.alertDialog(get,"TI","",2);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (userData.get(position).isActive()) {
                        message = "Are you sure? Do you want to Deactivate User.";
                        // DialogUtils.alertDialog(context, "Confirmation", "Are you sure? Do you want to Deactivate User.", 1);
                    } else {
                        message = "Are you sure? Do you want to Activate User.";
                        //  DialogUtils.alertDialog(context, "Confirmation", "Are you sure? Do you want to Activate User.", 1);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog);
                    builder.setTitle("Confirmation");
                    builder.setMessage(message);
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            userAction.readUser(userData.get(position).getId());
                            dialog.dismiss();
                        }

                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

            edit.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    if (userData.get(position).isActive()) {
                        userAction.switchToEditUser(position);
                    } else {
                        DialogUtils.alertDialog(context, "Intimation", "Can't change Inactive user information", 1);
                    }
                }
            });


        }

        @Override
        public View makeView() {
            return null;
        }/*        @Override
        public void onClick(View view) {
            if (view.getId()==edit.getId()){
                Log.i("EDIT","EDIT"+userData.get().getId());
                User user = new User();
              //  user.setUserId(Integer.parseInt(userData.get(view.getId()).getId()));
               // updateUser(user);
            }
        }*/

/*        @Override
        public void onClick(View view) {
            if (view.getId()==edit.getId()){
                Log.i("EDIT","EDIT"+userData.get().getId());
                User user = new User();
              //  user.setUserId(Integer.parseInt(userData.get(view.getId()).getId()));
               // updateUser(user);
            }
        }*/
    }
}
