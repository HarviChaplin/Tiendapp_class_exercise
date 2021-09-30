package com.example.tiendapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import java.util.Stack;

public class UserListActivity extends AppCompatActivity {

    private static final int CODE_ADD_USER = 101;
    private static final int CODE_DETAIL_USER = 201;
    private ArrayList<User> userList;
    private RecyclerView rvUser;
    private Button btRegisterUser;
    private UserAdapter myUserAdapter;

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

                Intent i = new Intent(UserListActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
        setContentView(R.layout.activity_user_list);

        rvUser = findViewById(R.id.rv_product_user_list);
        btRegisterUser = findViewById(R.id.bt_create_user);

        userList = new ArrayList<>();
        loadingUsers();

        myUserAdapter = new UserAdapter(userList);
        rvUser.setAdapter(myUserAdapter);
        rvUser.setLayoutManager(new GridLayoutManager(UserListActivity.this, 2));
        rvUser.setHasFixedSize(true);

        myUserAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(User user) {
                Intent turnPage = new Intent(UserListActivity.this, UserDetailActivity.class);
                turnPage.putExtra("user", user);
                startActivityForResult(turnPage, CODE_DETAIL_USER);
            }
        });

        btRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent turnPage = new Intent(UserListActivity.this, UserFormActivity.class);
                startActivityForResult(turnPage, CODE_ADD_USER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODE_ADD_USER && resultCode == RESULT_OK){

            if (data != null) {
                User user = (User) data.getSerializableExtra("user");
                int id = userList.get(userList.size() - 1).getId() + 1;
                user.setId(id);
                userList.add(user);
                myUserAdapter.setUserList(userList);
                Toast.makeText(UserListActivity.this, getString(R.string.txt_eng_toast_user_list), Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == CODE_DETAIL_USER && resultCode == RESULT_OK) {
            if (data != null) {
                User user = (User) data.getSerializableExtra("user");
                Boolean edit = data.getBooleanExtra("edit", false);
                for(User element : userList){
                    if(element.getId() == user.getId()){
                        int position = userList.indexOf(element);
                        if (edit) {
                            userList.set(position, user);
                        }else {
                            userList.remove(element);
                        }
                        break;
                    }
                }
            }
            myUserAdapter.setUserList(userList);
        }
    }

    private void loadingUsers() {
        User a, b, c;

        a = new User("Juan","Juan@gmail.com","no hay", "Juan");
        a.setId(1);
        userList.add(a);
        b = new User("Mario","Mario@gmail.com","no hay", "Mario");
        b.setId(2);
        userList.add(b);
        c = new User("Camila","Camila@gmail.com","no hay", "Camila");
        c.setId(3);
        userList.add(c);
    }
}