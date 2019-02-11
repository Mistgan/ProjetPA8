package com.efrei.coursorama.projetpa8;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Info_produit extends AppCompatActivity {


    final String link = "http://192.168.43.122:8888/PA8/test.php";
    public String id;
    public String name;
    public String type;
    public String price;
    public String url;
    public String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_produit);

        Intent intent = getIntent();
        String Postion = intent.getStringExtra("Position_value");

        //TextView txt_text = findViewById(R.id.test_bdd);
        //ImageView imageView = findViewById(R.id.image_view);


        Http_Request request = new Http_Request();
        request.execute();

        //image = url.replace("\\/", "/");
        //url = "http://www.niffylux.com/components/com_virtuemart/shop_image/product/Pommes_bicolore__4b74127d10dad.jpg";


    }

    public class Http_Request extends AsyncTask<String, String, String>
    {


        @Override
        protected String doInBackground(String... strings) {
            String current = "";
            Log.i("DEBUG", "doInBackground");
            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(link);

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("DEBUG", s);
            Log.i("DEBUG", "OnPostExecute");
            try {
                Log.i("DEBUG", "try");
                // JSON Parsing of data

                JSONObject jsonObject = new JSONObject(s);

                Log.i("DEBUG","jsonObject: "+ jsonObject);

                JSONArray oneObject = (JSONArray) jsonObject.get("products");
                Log.i("DEBUG","jsonArray: "+ oneObject);

                JSONObject element = (JSONObject) oneObject.get(0);

                // Pulling items from the array
                id = element.getString("id");
                name = element.getString("name");
                type = element.getString("type");
                price = element.getString("price");
                url = element.getString("link");
                // display the data in UI
                Log.i("DEBUG","Elements: "+ id + " " + name + " " + type + " " + price + " " + url);
                ImageView imageView = findViewById(R.id.image_view);
                TextView txt_text = findViewById(R.id.test_bdd);
                Picasso.with(com.efrei.coursorama.projetpa8.Info_produit.this).load(url).into(imageView);
                txt_text.setText(name);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}



