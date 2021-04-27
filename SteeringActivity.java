package com.example.mysteeringwheel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SteeringActivity extends Activity {
    ImageView imageView;
    SearchView serch;
    ListView listView;


    String[] number = new String[]{
            "BR 13 PA 0786",
            "KR 13 PA 0152",
            "KR 07 CA 7151",
            "HR 03 FA 0154",            "KR 15 PA 0556",

            "BR 10 GF 5554",
            "KR 16 WD 0103",

    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] image = new int[]{
            R.drawable.car,
            R.drawable.car1,
            R.drawable.car2,
            R.drawable.car3,
            R.drawable.car4,
            R.drawable.car5,
            R.drawable.car6,

    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steering);
        imageView = (ImageView) findViewById(R.id.image);
        serch=(SearchView)findViewById(R.id.searchView);


        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 7; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("txt", "  " + number[i]);
            hm.put("carimage", Integer.toString(image[i]));
            aList.add(hm);
        }


        String[] from = {"carimage", "txt", "cur"};


        int[] to = {R.id.image, R.id.txt};


        final SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.layout, from, to);


        final ListView listView = (ListView) findViewById(R.id.listview);


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // set an Intent to Another Activity
                Intent intent = new Intent(SteeringActivity.this, deskbord.class);
                intent.putExtra("image",image[position]); // put image data in Intent
                startActivity(intent); // start Intent
            }
        });
        serch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

               /* if(listView.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(SteeringActivity.this, "No Match found", Toast.LENGTH_SHORT).show();
                }*/
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                  adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}


