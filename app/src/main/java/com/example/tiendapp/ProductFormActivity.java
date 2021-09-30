package com.example.tiendapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProductFormActivity extends AppCompatActivity {
    private EditText etNameProduct, etPriceProduct, etDetailProduct, etImage;
    private TextView tvTitleForm;
    private Button btProduct;

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

                Intent i = new Intent(ProductFormActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        setContentView(R.layout.activity_product_form);

        tvTitleForm = findViewById(R.id.tv_form_title);
        etNameProduct = findViewById(R.id.et_product_name_form);
        etPriceProduct = findViewById(R.id.et_product_price_form);
        etDetailProduct = findViewById(R.id.et_product_detail_form);
        etImage = findViewById(R.id.et_image_product);
        btProduct = findViewById(R.id.bt_add_product_form);

        Product oldProduct = (Product) getIntent().getSerializableExtra("product");

        if (oldProduct != null) {
            tvTitleForm.setText(R.string.txt_eng_edit_product_title_form);
            etNameProduct.setText(oldProduct.getName());
            etPriceProduct.setText(String.valueOf(oldProduct.getPrice()));
            etDetailProduct.setText(oldProduct.getDetail());
            etImage.setText(oldProduct.getUrlImage());

            btProduct.setText(R.string.bt_eng_edit_product_button_form);

            btProduct.setOnClickListener(view -> {
                oldProduct.setName(etNameProduct.getText().toString());
                oldProduct.setDetail(etDetailProduct.getText().toString());
                oldProduct.setUrlImage(etImage.getText().toString());
                oldProduct.setPrice(Double.parseDouble(etPriceProduct.getText().toString()));

                Intent i = new Intent();
                i.putExtra("product", oldProduct);
                setResult(RESULT_OK, i);
                finish();
            });
        } else {
            btProduct.setOnClickListener(view -> {
                String name = etNameProduct.getText().toString();
                double price = Double.parseDouble(etPriceProduct.getText().toString());
                String image = etImage.getText().toString();
                String detail = etDetailProduct.getText().toString();
                Product product = new Product(name, image, price);
                if (!detail.equals("")) {
                    product.setDetail(detail);
                }
                Intent i = new Intent();
                i.putExtra("product", product);
                setResult(RESULT_OK, i);
                finish();
            });
        }
    }
}