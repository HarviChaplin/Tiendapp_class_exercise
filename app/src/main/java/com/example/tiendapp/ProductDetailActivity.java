package com.example.tiendapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetailActivity extends AppCompatActivity {

    private static final int CODE_EDIT_PRODUCT = 300;
    private TextView tvDetailTitle, tvPriceText, tvDetailText;
    private ImageView ivDetailImage;
    private Button btDeleteproduct, btEditProduct;
    private Product product;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_option:
                SharedPreferences preferences = getSharedPreferences(getString(R.string.txt_preferences_name), MODE_PRIVATE);
                SharedPreferences.Editor edit = preferences.edit();
                edit.remove(getString(R.string.txt_login_preference));
                edit.apply();

                Intent i = new Intent(ProductDetailActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);


        tvDetailTitle = findViewById(R.id.tv_detail_title);
        tvPriceText = findViewById(R.id.tv_price_text);
        tvDetailText = findViewById(R.id.tv_detail_text);
        ivDetailImage = findViewById(R.id.iv_detail_image);
        btDeleteproduct = findViewById(R.id.bt_delete_product);
        btEditProduct = findViewById(R.id.bt_edit_product);

        product = (Product) getIntent().getSerializableExtra("product");

        loadData();



        btDeleteproduct.setOnClickListener(view -> {
            Intent i = new Intent();
            i.putExtra("product", product);
            setResult(RESULT_OK, i);
            finish();
        });

        btEditProduct.setOnClickListener(view -> {
            Intent turnPage = new Intent(ProductDetailActivity.this, ProductFormActivity.class);
            turnPage.putExtra("product", product);
            startActivityForResult(turnPage, CODE_EDIT_PRODUCT);
        });
    }


    private void loadData() {
        tvDetailTitle.setText(getString(R.string.txt_eng_detail_title, product.getName()));
        tvPriceText.setText(String.valueOf(product.getPrice()) + "$");
        tvDetailText.setText(product.getDetail());
        Glide.with(ProductDetailActivity.this).load(product.getUrlImage()).into(ivDetailImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_EDIT_PRODUCT && resultCode == RESULT_OK) {
            if (data != null) {
            product = (Product) data.getSerializableExtra("product");
            loadData();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("product", product);
        i.putExtra("edit", true);
        setResult(RESULT_OK, i);
        super.onBackPressed();
    }
}