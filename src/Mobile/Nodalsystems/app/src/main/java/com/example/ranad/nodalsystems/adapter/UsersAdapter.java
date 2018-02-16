package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.UserData;
import com.example.ranad.nodalsystems.fragment.UserFragment;
import com.example.ranad.nodalsystems.interfaces.UserAction;

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

        ImageView edit, delete;


        public UserViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            userName = (TextView) itemView.findViewById(R.id.username);

            name = (TextView) itemView.findViewById(R.id.name);
            mobile = (TextView) itemView.findViewById(R.id.mobile);
            isActive = (TextView) itemView.findViewById(R.id.isActive);
            userElementCode = (TextView) itemView.findViewById(R.id.elementCode);

            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);
        }

        public void bindData(final ArrayList<UserData> userData, final int position) {
            Log.i("dFGFH", "PPPP" + userData.get(position).getId());
            userName.setText(userData.get(position).getUserName());


            name.setText(userData.get(position).getFirstName() + " " + userData.get(position).getLastName());
            mobile.setText(userData.get(position).getMobile().toString());

            if (userData.get(position).isActive())
                isActive.setText("Active");
            else
                isActive.setText("Passive");

            userElementCode.setText(userData.get(position).getUserElementCode());

            //   DialogUtils.alertDialog(get,"TI","",2);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                /*    User user=new User();
                    user.setUserId(userData.get(position).getId());
*/
                    userAction.readUser(userData.get(position).getId());

//                    userAction.getUser();
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {


                    // Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                  /*  FragmentTransaction transection=getFragmentManager().beginTransaction();

                    UserEditFragment userEditFragment=new UserEditFragment();

                    Bundle bundle=new Bundle();
                    bundle.putInt("userId",userData.get(position).getId());
                    userEditFragment.setArguments(bundle); //data being send to SecondFragment
                    transection.replace(R.id.main_fragment, mfragment);
                    transection.commit();
*/


                    userAction.switchToEditUser(position);
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
