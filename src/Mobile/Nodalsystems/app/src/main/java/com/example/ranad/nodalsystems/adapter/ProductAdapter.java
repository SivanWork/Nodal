package com.example.ranad.nodalsystems.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.ranad.nodalsystems.R;
import com.example.ranad.nodalsystems.data_holder.ProductData;
import com.example.ranad.nodalsystems.fragment.ProductFragment;
import com.example.ranad.nodalsystems.interfaces.ProductAction;

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


        public ProductViewHolder(final Context context, View itemView) {
            super(itemView);
            this.itemView = itemView;
            productName = (TextView) itemView.findViewById(R.id.product_name);
            price = (TextView) itemView.findViewById(R.id.product_price);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);

        }

        public void bindData(final ProductData productData, final int position) {
            productName.setText(productData.getProductName());
            price.setText(productData.getDealerPrice() + "");

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productAction.readProduct(productData.getProductId());

                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    productAction.switchToEditProduct(productData.getProductId());
                }
            });
        }

        @Override
        public View makeView() {
            return null;
        }
    }
}
