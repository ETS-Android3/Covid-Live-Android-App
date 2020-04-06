package com.deysofts.covidlive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class CovidLive extends AppCompatActivity {

    TextView textView_total_cases,textView_new_cases,textView_total_deaths,textView_total_recovered,header;
    DatabaseReference reff;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        header=(TextView)findViewById(R.id.header);
        header.setText(text);
        textView_total_cases=(TextView)findViewById(R.id.textView7);
        textView_new_cases=(TextView)findViewById(R.id.textView9);
        textView_total_deaths=(TextView)findViewById(R.id.textView8);
        textView_total_recovered=(TextView)findViewById(R.id.textView10);
        reff=FirebaseDatabase.getInstance().getReference().child("Data");
        try {

            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String total_cases = dataSnapshot.child("Total Cases").getValue().toString();
                    String new_cases = dataSnapshot.child("New Cases").getValue().toString();
                    String total_deaths = dataSnapshot.child("Total Deaths").getValue().toString();
                    String total_recovered = dataSnapshot.child("Total Recovered").getValue().toString();
                    textView_total_cases.setText(total_cases);
                    textView_total_recovered.setText(total_recovered);
                    textView_new_cases.setText(new_cases);
                    textView_total_deaths.setText(total_deaths);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    textView_total_cases.setText("Updating");
                    textView_total_recovered.setText("Updating");
                    textView_new_cases.setText("Updating");
                    textView_total_deaths.setText("Updating");

                }
            });
        }catch (Exception e)
        {
            textView_total_cases.setText("Updating");
            textView_total_recovered.setText("Updating");
            textView_new_cases.setText("Updating");
            textView_total_deaths.setText("Updating");
        }


    }




}






