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

public class UserFormActivity extends AppCompatActivity {

    private EditText etNameUser, etImage, etEmailUser, etPassword;
    private TextView tvTitleForm;
    private Button btUser;

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

                Intent i = new Intent(UserFormActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        setContentView(R.layout.activity_user_form);

        tvTitleForm = findViewById(R.id.tv_form_user_title);
        etNameUser = findViewById(R.id.et_name_user_form);
        etEmailUser = findViewById(R.id.et_email_user_form);
        etImage = findViewById(R.id.et_image_user_form);
        etPassword = findViewById(R.id.et_password_user_form);
        btUser = findViewById(R.id.bt_add_user_form);

        User oldUser = (User) getIntent().getSerializableExtra("user");

        if (oldUser != null) {
            tvTitleForm.setText(R.string.txt_eng_edit_user_title_form);
            etNameUser.setText(oldUser.getName());
            etEmailUser.setText(oldUser.getEmail());
            etImage.setText(oldUser.getUrlFoto());
            etPassword.setText(R.string.hint_eng_add_user_new_password);

            btUser.setText(R.string.bt_eng_edit_user_button_form);

            btUser.setOnClickListener(view -> {
                oldUser.setName(etNameUser.getText().toString());
                oldUser.setEmail(etEmailUser.getText().toString());
                oldUser.setUrlFoto(etImage.getText().toString());
                oldUser.setPassword(etPassword.getText().toString());

                Intent i = new Intent();
                i.putExtra("user", oldUser);
                setResult(RESULT_OK, i);
                finish();
            });

        }else{
            btUser.setOnClickListener(view -> {
                String name = etNameUser.getText().toString();
                String email = etEmailUser.getText().toString();
                String image = etImage.getText().toString();
                String password = etPassword.getText().toString();

                User user = new User(name, email, image, password);

                Intent i = new Intent();
                i.putExtra("user", user);
                setResult(RESULT_OK, i);
                finish();
            });
        }
    }
}