package com.example.taqi.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> usernameList = new ArrayList<>();
    private ArrayList<String> passwordList = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<String> usernameList, ArrayList<String> passwordList, Context context) {
        this.usernameList = usernameList;
        this.passwordList = passwordList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = null;
        View view = layoutInflater.from(context).inflate(R.layout.listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.username.setText(usernameList.get(i));
        viewHolder.password.setText(passwordList.get(i));

    }

    @Override
    public int getItemCount() {
        return usernameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username,password;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tvUsername);
            password = itemView.findViewById(R.id.tvPassword);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
