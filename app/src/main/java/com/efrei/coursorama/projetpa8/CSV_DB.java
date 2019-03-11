package com.efrei.coursorama.projetpa8;

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

public class CSV_DB extends AppCompatActivity {

    final String link = "http://192.168.43.122:8888/PA8/openfoodfact.php";
    public String id;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csv__db);
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
                // display the data in UI
                Log.i("DEBUG","Elements: "+ id + " " + name);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

}
