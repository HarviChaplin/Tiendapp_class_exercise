package com.example.tiendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.security.PublicKey;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {

    private ArrayList<Product> ProductList;


    private OnItemClickListener onItemClickListener;

    public ProductAdapter(ArrayList<Product> ProductList) {
        this.ProductList = ProductList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setProductList(ArrayList<Product> productList) {
        ProductList = productList;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvProductName, tvProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_product_item);
            tvProductName = itemView.findViewById(R.id.tv_product_item_name);
            tvProductPrice = itemView.findViewById(R.id.tv_product_item_price);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myProductVist = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);

        return new ProductViewHolder(myProductVist);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Product product = ProductList.get(position);
        final ProductViewHolder myProductHolder = (ProductViewHolder) holder;

        myProductHolder.tvProductName.setText(product.getName());
        myProductHolder.tvProductPrice.setText(String.valueOf(product.getPrice()));
        Glide.with(myProductHolder.itemView.getContext()).load(product.getUrlImage()).into(myProductHolder.ivProduct);

        myProductHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(product);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }


}
