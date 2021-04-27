package com.example.mysteeringwheel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

public class deskbord extends AppCompatActivity {

    private TextView mValueView,mValueView1,mValueView2,mValueView3;
    private Firebase mRef,mRef1,mRef2,mRef3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskbord);

        mValueView=(TextView)findViewById(R.id.text);
        mValueView1=(TextView)findViewById(R.id.text2);
        mValueView2=(TextView)findViewById(R.id.text3);
        mValueView3=(TextView)findViewById(R.id.text4);

        mRef =new Firebase("https://my-steering-wheel.firebaseio.com/Temp");
        mRef1 =new Firebase("https://my-steering-wheel.firebaseio.com/Pulse");
        mRef3 =new Firebase("https://my-steering-wheel.firebaseio.com/Drowsiness");
        mRef2 =new Firebase("https://my-steering-wheel.firebaseio.com/Alcohal");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =dataSnapshot.getValue(String.class);
                mValueView.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =dataSnapshot.getValue(String.class);
                mValueView1.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =dataSnapshot.getValue(String.class);
                mValueView2.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =dataSnapshot.getValue(String.class);
                mValueView3.setText(value);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
    public void time(View view){
        Intent omIntent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.timeanddate.com/worldclock/india/bangalore"));
        startActivity(omIntent);
    }
    public void location(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Map.class));
        finish();
    }

    public void OnClick(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
