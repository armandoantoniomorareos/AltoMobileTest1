package com.example.android.altomobiletest1;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class RequestData extends AsyncTask<String, Void, String> {

    private ListAdapter adapter;
    private ArrayList<Animal> array;

    /**
     * @param adapter,  ListAdapter to update with the data from json file
     */

    public RequestData(ListAdapter adapter) {
        this.adapter = adapter;
        array = new ArrayList<>();
    }

    @Override
    protected String doInBackground(String... urls) {
        String tmpResponse = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urls[0])
                .build();
        try {
            try (Response response = client.newCall(request).execute()) {
                tmpResponse = response.body().string();
            }
        } catch (IOException e) {
        }
        return tmpResponse;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            if (s != null && !s.equals("")) {
                array = parserData(s);
                for (Animal a : array) {  //create method to add to adapter
                    adapter.add(a);
                }
            } else {
                Toast.makeText(getApplicationContext(),
                        "There was an error getting data from server", Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            Log.v("RequestData", "" + e.getMessage());
        }
    }


    /**
     * Method to parse json file, and populate an ArrayList with the corresponding items
     * @param data receive the json file from internet as String
     * @return ArrayList with the items found into the json file
     */

    private ArrayList<Animal> parserData(String data) throws JSONException {
        ArrayList<Animal> array = new ArrayList<>();
        String name, pictureUrl;
        int life, id;

        JSONArray jarray = new JSONArray(data);
        JSONObject jobj;

        for (int i = 0; i < jarray.length(); i++) {
            jobj = jarray.getJSONObject(i);
            life = jobj.getInt("life");
            name = jobj.getString("name");
            id = jobj.getInt("id");
            pictureUrl = jobj.getString("pictureURL");

            array.add(new Animal(life, name, id, pictureUrl));
        }
        return array;
    }
}
