package com.example.tiendapp;

//Import the necessary libraries for the class
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Initialize the global variables of the class
    private EditText etCorreo;
    private EditText etPassword;
    private Button btIniciar;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relate the back with the front
        etCorreo = findViewById(R.id.et_start_email_main);
        btIniciar = findViewById(R.id.bt_login_main);
        etPassword = findViewById(R.id.et_start_password_main);
        preferences = getSharedPreferences(getString(R.string.txt_preferences_name), MODE_PRIVATE);

        //validate login
        boolean log= preferences.getBoolean(getString(R.string.txt_login_preference), false);
        if(log){
            Intent turnPage = new Intent(MainActivity.this, LobbyActivity.class);
            startActivity(turnPage);
        }

        //Set the action to the Login button
        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = "123";
                String psword = "123";
                String usr2 = etCorreo.getText().toString();
                String psword2 = etPassword.getText().toString();
                if (usr2.equals(usr) && psword2.equals(psword)) {
                    Intent turnPage = new Intent(MainActivity.this, LobbyActivity.class);
                    startActivity(turnPage);
                    SharedPreferences.Editor edit = preferences.edit();
                    edit.putBoolean(getString(R.string.txt_login_preference), true);
                    edit.apply();
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "incorrect data", Toast.LENGTH_LONG).show();
                    etCorreo.setText("");
                    etPassword.setText("");
                }
            }
        });
    }
}