package com.ahmmedzakaria.webservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<User> userList;
    UserAdapter adapter;
    //String url="https://jsonplaceholder.typicode.com/todos/";
    String url="http://nationalappsbangladesh.com/mobsvc/ContentFile.php";
    List<String> nameList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        userList = new ArrayList<>();
        getUsers();

        adapter = new UserAdapter(MainActivity.this, userList);
        listView.setAdapter(adapter);
        // adapter.notifyDataSetChanged();
        listView.setOnItemClickListener((adapterView, view,  i, l)->{
            String name = userList.get(i).getName();
            Toast toast = Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT);
            toast.show();
        });
        
    }


    private void getUsers() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("contentfilelist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("Id");
                        User user = new User();
                        user.setUserName(name);
                        user.setName("user");
                        user.setEmail("email");

                        userList.add(user);
                       // nameList.add(name);
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    error.getStackTrace();
                if (error instanceof NoConnectionError) {

                }

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }


    public void viewUsers(View view) {
        Intent intent =new Intent(MainActivity.this,UsersActivity.class);
        startActivity(intent);
    }
}
