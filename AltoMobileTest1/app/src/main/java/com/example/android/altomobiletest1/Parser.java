package com.example.android.altomobiletest1;


/*
* This class is not needed
* */
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Parser {
    private String url;
    private RestaurantListAdapter adapter;
    private ArrayList<Product> array;

    public Parser(String url, RestaurantListAdapter adapter){
        this.url = url;
        this.adapter = adapter;
        try {
            getData();
        }catch(IOException e){
            Log.v("Parser", " ----->"+e.getMessage());
        }
    }

    private void getData() throws IOException {
        OkHttpClient client  =new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).build();

        try(Response response = client.newCall(request).execute()){
            parser(response.body().toString());
        }

    }

    private void parser(String jsonFile){
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Product>>(){}.getType();
        if(jsonFile !=null && !jsonFile.equals("")){
            array = gson.fromJson(jsonFile, type);
            adapter.addAll(array);
        }
    }
}
