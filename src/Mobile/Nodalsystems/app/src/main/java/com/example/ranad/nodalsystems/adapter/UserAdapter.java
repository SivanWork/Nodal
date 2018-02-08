package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.UserData;

import java.util.ArrayList;

/**
 * Created by Kavya V on 07-02-2018.
 */

public class UserAdapter extends BaseAdapter implements View.OnClickListener  {
    LayoutInflater layoutInflater;
    ArrayList<UserData> userData;
    Context context;

    public UserAdapter(Context context, ArrayList<UserData> userData){
        this.context = context;
        this.userData = userData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.edit:
                Toast.makeText(context, "unable to edit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(context, "unable to delete", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public int getCount() {
        return userData.size();
    }

    @Override
    public Object getItem(int i) {
        return this.userData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        v = layoutInflater.inflate(R.layout.user_list_item, null);
        TextView name = (TextView) v.findViewById(R.id.user_name);
        ImageView edit = (ImageView) v.findViewById(R.id.edit);
        ImageView delete = (ImageView) v.findViewById(R.id.delete);

        name.setText("User 1");

        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        return v;
    }
}
