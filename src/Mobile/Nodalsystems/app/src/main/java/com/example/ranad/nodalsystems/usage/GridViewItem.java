package com.example.ranad.nodalsystems.usage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.example.ranad.nodalsystems.R;

/**
 * Created by Rana D on 1/20/2018.
 */

public class GridViewItem extends RelativeLayout {

    boolean height;
    public GridViewItem(Context context) {
        super(context);
    }

    public GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GridViewItem, 0, 0);
        try {
            height = a.getBoolean(R.styleable.GridViewItem_heightbase, false);
        }finally {
            a.recycle();
        }
    }

    public GridViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle); TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GridViewItem, 0, 0);
        try {
            height = a.getBoolean(R.styleable.GridViewItem_heightbase, false);
        }finally {
            a.recycle();
        }

    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width

        if (height){
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
        }else {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        }
    }

}
