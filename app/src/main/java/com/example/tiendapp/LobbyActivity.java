package com.example.tiendapp;

//Import the necessary libraries for the class
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class LobbyActivity extends AppCompatActivity {

    //Initialize the global variables of the class
    private Button btProducts, btUsers;

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

                Intent i = new Intent(LobbyActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        setContentView(R.layout.activity_lobby);

        //Relate the back with the front
        btProducts = findViewById(R.id.bt_products_lobby);
        btUsers = findViewById(R.id.bt_users_lobby);

        //Set the action to the turn page products button
        btProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turnPage = new Intent(LobbyActivity.this, ProductListActivity.class);
                startActivity(turnPage);
            }
        });

        //Set the action to the turn page users button
        btUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turnPage = new Intent(LobbyActivity.this, UserListActivity.class);
                startActivity(turnPage);
            }
        });
    }
}