package com.example.tiendapp;

//Import the necessary libraries for the class
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    //Initialize the global variables of the class
    private static final int CODE_ADD_PRODUCT = 100;
    private static final int CODE_DETAIL_PRODUCT = 200;
    private ArrayList<Product> productList;
    private RecyclerView rvProduct;
    private Button btCreateProduct;
    private ProductAdapter myAdapter;

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

                Intent i = new Intent(ProductListActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        setContentView(R.layout.activity_product_list);

        //Relate the back with the front
        rvProduct = findViewById(R.id.rv_product_list);
        btCreateProduct = findViewById(R.id.bt_create_product);

        productList = new ArrayList<>();

        loadingProducts();

        //create adapter to display objects in detail
        myAdapter = new ProductAdapter(productList);
        rvProduct.setAdapter(myAdapter);
        rvProduct.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));
        rvProduct.setHasFixedSize(true);

        //set the click option on each object in the list, waiting for answer
        myAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent turnPage = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                turnPage.putExtra("product", product);
                startActivityForResult(turnPage, CODE_DETAIL_PRODUCT);
            }
        });

        //Set the action to the create button. waiting for answer
        btCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turnPage = new Intent(ProductListActivity.this, ProductFormActivity.class);
                startActivityForResult(turnPage, CODE_ADD_PRODUCT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_ADD_PRODUCT && resultCode == RESULT_OK) {

            if (data != null) {
                Product product = (Product) data.getSerializableExtra("product");
                int id = productList.get(productList.size() - 1).getId() + 1;
                product.setId(id);
                productList.add(product);
                myAdapter.setProductList(productList);
                Toast.makeText(ProductListActivity.this, getString(R.string.txt_eng_toast_list), Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == CODE_DETAIL_PRODUCT && resultCode == RESULT_OK) {
            if (data != null) {
                Product product = (Product) data.getSerializableExtra("product");
                Boolean edit = data.getBooleanExtra("edit", false);
                for (Product element : productList) {
                    if (element.getId() == product.getId()) {
                        int position = productList.indexOf(element);
                        if (edit) {
                            productList.set(position, product);
                        } else {
                            productList.remove(element);
                        }
                        break;
                    }
                }
            }
            myAdapter.setProductList(productList);
        }
    }


    private void loadingProducts() {
        Product a, b, c, d, e;

        a = new Product("chocorramo", "https://metrocolombiafood.vteximg.com.br/arquivos/ids/268417-750-750/7702914596787.jpg?v=637244541306400000", 1300.0);
        a.setId(1);
        productList.add(a);

        b = new Product("lecherita", "https://www.eurosupermercados.com.co/media/catalog/product/cache/5db2c2cd17d64c361c13f6f6460f1cf1/7/7/7702024341338.jpg", 1500.0);
        b.setId(2);
        productList.add(b);

        c = new Product("bombombum", "https://d1.com.co/wp-content/uploads/12003348.jpg", 3500.0);
        c.setId(3);
        productList.add(c);

        d = new Product("gansito", "https://www.larebajavirtual.com/images/productos/sii/F/300x300/ponque_gansito-103248-1564437836.png", 4500.0);
        d.setId(4);
        productList.add(d);

        e = new Product("cocacola", "https://i2.wp.com/mercaenlinea.nyc3.digitaloceanspaces.com/2020/03/coca-cola-p400.jpg?fit=720%2C720&ssl=1", 5500.0);
        e.setId(5);
        productList.add(e);
    }
}