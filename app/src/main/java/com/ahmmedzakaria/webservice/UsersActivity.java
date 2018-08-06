package com.ahmmedzakaria.webservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<User> userList;
    UserAdapter adapter;
    String url2 = "https://jsonplaceholder.typicode.com/users";
    String url="https://jsonplaceholder.typicode.com/todos/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<>();
        getUsers();

        adapter = new UserAdapter(UsersActivity.this, userList);
        listView.setAdapter(adapter);
       // adapter.notifyDataSetChanged();
        listView.setOnItemClickListener((adapterView, view,  i, l)->{
            String name = userList.get(i).getName();
            Toast toast = Toast.makeText(UsersActivity.this, name, Toast.LENGTH_SHORT);
            toast.show();
        });
    }


    void getUsers() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url2, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast t = Toast.makeText(UsersActivity.this, response.length(), Toast.LENGTH_SHORT);
//                t.show();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        User user = new User();
                        String id = object.getString("id");
                        String name = object.getString("name");
                        String username = object.getString("username");
                        String email = object.getString("email");

                        user.setId(id);
                        user.setName(name);
                        user.setUserName(username);
                        user.setEmail(email);
                        userList.add(user);
                        //String name = userList.get(0).getName();
//                        Toast toast = Toast.makeText(UsersActivity.this, userList.get(i).getName(), Toast.LENGTH_SHORT);
//                        toast.show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();
            }
        });

        AppController.getInstance().addToRequestQueue(request);
    }
}