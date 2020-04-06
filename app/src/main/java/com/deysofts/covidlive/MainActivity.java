package com.deysofts.covidlive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText name;
    Button next;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";
    private String text;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.editText);
        //Typeface font=Typeface.createFromAsset(getAssets(),"font/futur.tff");
      //  name.setTypeface(font);
        next=(Button)findViewById(R.id.button);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        if(text.length()!=0)
        {

            Intent intent = new Intent(MainActivity.this, CovidLive.class);
            startActivity(intent);
        }
        //nextClick();
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                name.getText().toString();
                SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences2.edit();

                editor.putString(TEXT, name.getText().toString());
                editor.apply();

                Intent intent = new Intent(MainActivity.this, CovidLive.class);
                startActivity(intent);
                new doit().execute();



            }
        });




    }



    public class doit extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {
            final String TAG="j";
            final String url =
                    "https://www.worldometers.info/coronavirus/";

            try {
                final Document document = Jsoup.connect(url).get();

                for (Element row : document.select(
                        "table.tablesorter.full tr")) {
                    if (row.select("td:nth-of-type(1)").text().equals("")) {
                        continue;
                    }
                    else {
                        final String country =
                                row.select("td:nth-of-type(1)").text();
                        final String total_cases =
                                row.select("td:nth-of-type(2)").text();
                        final String new_cases =
                                row.select("td.right:nth-of-type(3)").text();
                       // final String tempPrice1 =
                              //  tempPrice.replace(",", "");
//                    final double price = Double.parseDouble(tempPrice1);

                        Log.i(TAG,"country + \" \" + total_cases + \" \" + new_cases");
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        protected void onPostExecutive(Void aVoid)
        {
            super.onPostExecute(aVoid);
            //todo

        }
    }

    @Override
    protected void onStop() {

        super.onStop();
        this.finishAffinity();

    }

}
