package com.thangabalajis.studenttrackeradmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Details extends AppCompatActivity {

    double lat = 13.0;
    double longg = 79.0;
    String name = "user";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent i = getIntent();
        final String rollno = i.getStringExtra("rollno");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.whereEqualTo("rollno", rollno);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    ParseObject item = scoreList.get(0);
                    ((TextView) findViewById(R.id.name)).setText(String.valueOf(item.get("name")));
                    ((TextView) findViewById(R.id.phone)).setText(String.valueOf(item.get("phone")));
                    ((TextView) findViewById(R.id.rollno)).setText(rollno);

                    lat = Double.parseDouble(String.valueOf(item.get("lat")));
                    longg = Double.parseDouble(String.valueOf(item.get("long")));
                    name = String.valueOf(item.get("name"));
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    public void loacion(View view){
        Intent i = new Intent(this,MapsActivity.class);
        i.putExtra("lat",lat);
        i.putExtra("long",longg);
        i.putExtra("name",name);
        startActivity(i);
    }
}
