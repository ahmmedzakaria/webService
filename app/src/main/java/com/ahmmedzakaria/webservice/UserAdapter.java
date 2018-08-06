package com.ahmmedzakaria.webservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter {
    private ArrayList<User> userList;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> userList) {
        super(context, R.layout.user_layout, userList);
        this.context = context;
        this.userList = userList;
    }

    static class ViewHolder {
        TextView nameTV,userNameTV, emailTV;
        ImageView imageView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.name);
            viewHolder.userNameTV = (TextView) convertView.findViewById(R.id.user_name);
            viewHolder.emailTV = (TextView) convertView.findViewById(R.id.email);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nameTV.setText(userList.get(position).getName());
        viewHolder.userNameTV.setText(userList.get(position).getUserName());
        viewHolder.emailTV.setText(userList.get(position).getEmail());

        return convertView;
    }
}
