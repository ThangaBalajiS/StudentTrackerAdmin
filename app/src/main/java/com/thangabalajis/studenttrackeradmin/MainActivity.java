package com.thangabalajis.studenttrackeradmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> ar;
    ListView lv;
    EditText etSearch;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        ar = new ArrayList<>();
        try {
            Parse.initialize(new Parse.Configuration.Builder(this)
                    .applicationId("myAppIdstudentlocation")
                    .server("http://studentlocation.herokuapp.com/parse/")
                    .build()
            );
        } catch (Exception e) {

        }

        lv = findViewById(R.id.listView);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    for (ParseObject obj : scoreList){
                        ar.add(String.valueOf(obj.get("rollno")));

                    }

                    adapter = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1, ar);

                    lv.setAdapter(adapter);

                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {

                            Log.i("Hello!", "Y u no see me?");
                            Intent i = new Intent(getApplicationContext(),Details.class);
                            i.putExtra("rollno",ar.get(position));
                            startActivity(i);

                        }

                    });



                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }

        });

        etSearch.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                // you can call or do what you want with your EditText here


            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.this.adapter.getFilter().filter(s);
            }
        });


    }
}
