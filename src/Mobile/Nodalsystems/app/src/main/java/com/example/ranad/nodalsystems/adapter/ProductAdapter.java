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
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.fragment.ProductFragment;
import com.example.ranad.nodalsystems.interfaces.ProductAction;
import com.example.ranad.nodalsystems.usage.DialogUtils;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    ArrayList<ProductData> productData;
    InputMethodManager inputMethodManager;
    ProductAction productAction;
    private Context context;

    public ProductAdapter(ArrayList<ProductData> productData, Context context, ProductFragment productAction) {
        this.context = context;
        this.productData = productData;
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        this.productAction = productAction;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listing, parent, false);
        return new ProductAdapter.ProductViewHolder(this.context, view);
    }

    public void updateList(List<ProductData> list) {
        productData = (ArrayList<ProductData>) list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        ProductData productData = this.productData.get(position);
        holder.bindData(productData, position);
    }

    @Override
    public int getItemCount() {
        return this.productData.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements ViewSwitcher.ViewFactory {
        View itemView;
        TextView productName, price;

        ImageView edit, delete;
        String message;
        LinearLayout linearLayout;


        public ProductViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            productName = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);

        }

        public void bindData(final ProductData productData, final int position) {
            productName.setText(productData.getProductName());
            price.setText(productData.getDealerPrice() + "");
            if (productData.getIsActive()) {
                delete.setImageResource(R.drawable.delete);
                linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            } else {
                delete.setImageResource(R.drawable.delete_undo);
                linearLayout.setBackgroundColor(Color.parseColor("#A9A9A9"));
            }
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (productData.getIsActive()) {
                        message = "Are you sure? Do you want to Deactivate Product.";
                        // DialogUtils.alertDialog(context, "Confirmation", "Are you sure? Do you want to Deactivate User.", 1);
                    } else {
                        message = "Are you sure? Do you want to Activate Product.";
                        //  DialogUtils.alertDialog(context, "Confirmation", "Are you sure? Do you want to Activate User.", 1);
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog);
                    builder.setTitle("Confirmation");
                    builder.setMessage(message);
                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                            productAction.readProduct(productData.getProductId());
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
                    if (productData.getIsActive()) {
                        productAction.switchToEditProduct(productData.getProductId());
                    } else {
                        DialogUtils.alertDialog(context, "Intimation", "Can't change Inactive product information", 1);
                    }
                }
            });
        }

        @Override
        public View makeView() {
            return null;
        }
    }
}
