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

public class UserDetailActivity extends AppCompatActivity {

    private static final int CODE_EDIT_USER = 301;
    private TextView tvDetailTitle, tvEmailText;
    private ImageView ivDetailImage;
    private Button btDeteleUser, btEditUser;
    private User user;

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

                Intent i = new Intent(UserDetailActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        setContentView(R.layout.activity_user_detail);

        tvDetailTitle = findViewById(R.id.tv_title_user_detail);
        tvEmailText = findViewById(R.id.tv_email_user_detail);
        ivDetailImage = findViewById(R.id.iv_image_user_detail);
        btDeteleUser = findViewById(R.id.bt_delete_user_detail);
        btEditUser = findViewById(R.id.bt_edit_user_detail);

        user = (User) getIntent().getSerializableExtra("user");

        loadData();

        btDeteleUser.setOnClickListener(view -> {
            Intent i = new Intent();
            i.putExtra("user", user);
            setResult(RESULT_OK, i);
            finish();
        });

        btEditUser.setOnClickListener(view -> {
            Intent turnPage = new Intent(UserDetailActivity.this, UserFormActivity.class);
            turnPage.putExtra("user", user);
            startActivityForResult(turnPage, CODE_EDIT_USER);
        });
    }

    private void loadData() {
        tvDetailTitle.setText(user.getName());
        tvEmailText.setText(user.getEmail());
        Glide.with(UserDetailActivity.this).load(user.getUrlFoto()).into(ivDetailImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CODE_EDIT_USER && resultCode == RESULT_OK) {
            if (data != null) {
                user = (User) data.getSerializableExtra("user");
                loadData();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("user", user);
        i.putExtra("edit", true);
        setResult(RESULT_OK, i);
        super.onBackPressed();
    }
}