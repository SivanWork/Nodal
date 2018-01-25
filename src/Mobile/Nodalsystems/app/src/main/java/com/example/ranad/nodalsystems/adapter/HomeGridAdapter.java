package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.HomeGridElement;

import java.util.ArrayList;

/**
 * Created by Rana D on 1/20/2018.
 */

public class HomeGridAdapter extends BaseAdapter {
    ArrayList<HomeGridElement> homeGridElements;
    Context context;
    LayoutInflater layoutInflater;

   public HomeGridAdapter(Context context, ArrayList<HomeGridElement> homeGridElements){
        this.context = context;
        this.homeGridElements = homeGridElements;
       layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return homeGridElements.size();
    }

    @Override
    public Object getItem(int i) {
        return homeGridElements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        v = layoutInflater.inflate(R.layout.home_grid, null);
        View home_background = (View) v.findViewById(R.id.icon_background);
        ImageView home_icon = (ImageView) v.findViewById(R.id.home_icon);
        TextView home_title = (TextView) v.findViewById(R.id.home_title);

        //home_background.setBackgroundColor(ContextCompat.getColor(context,homeGridElements.get(i).getBackground()));
        //home_icon.setImageDrawable(context.getResources().getDrawable(homeGridElements.get(i).getIcon()));
        home_icon.setImageResource(homeGridElements.get(i).getIcon());
        home_title.setText(homeGridElements.get(i).getTitle());
        return v;
    }
}
