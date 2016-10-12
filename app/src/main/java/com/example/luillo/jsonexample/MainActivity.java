package com.example.luillo.jsonexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String user = "{\"firstName\":\"John\", \"lastName\":\"Doe\"}";

        try {
            JSONObject jsonUser = new JSONObject(user);

            String firstName = jsonUser.getString("firstName");

            Log.e("JSON","FirstName " + firstName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
