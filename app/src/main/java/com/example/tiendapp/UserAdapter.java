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

public class UserAdapter extends RecyclerView.Adapter {

    private ArrayList<User> UserList;

    private OnItemClickListener onItemClickListener;

    public UserAdapter(ArrayList<User> userList) {
        this.UserList = userList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setUserList(ArrayList<User> userList) {
        UserList = userList;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        ImageView ivUser;
        TextView tvUserName, tvUserEmail;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUser = itemView.findViewById(R.id.iv_user_item);
            tvUserName = itemView.findViewById(R.id.tv_user_name_item);
            tvUserEmail = itemView.findViewById(R.id.tv_user_email_item);

        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myUserVist = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new UserViewHolder(myUserVist);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final User user = UserList.get(position);
        final UserViewHolder myUserHolder = (UserViewHolder) holder;

        myUserHolder.tvUserName.setText(user.getName());
        myUserHolder.tvUserEmail.setText(user.getEmail());
        Glide.with(myUserHolder.itemView.getContext()).load(user.getUrlFoto()).into(myUserHolder.ivUser);

        myUserHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(User user);
    }
}
