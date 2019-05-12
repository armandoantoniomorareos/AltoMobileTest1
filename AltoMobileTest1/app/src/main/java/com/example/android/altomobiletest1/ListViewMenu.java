package com.example.android.altomobiletest1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewMenu extends AppCompatActivity {

    public ArrayList<Animal> list;
    public ListView listView;
    public ListAdapter adapter;
    private static final String url = "https://flavioruben.herokuapp.com/data.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_menu);

        list = new ArrayList<>();
        adapter = new ListAdapter(this, list);

        listView = findViewById(R.id.list);

        LayoutInflater inflater = this.getLayoutInflater();
        View header = inflater.inflate(R.layout.listview_header, listView, false);

        listView.addHeaderView(header, null, false);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    int animalLife = ((Animal) listView.getItemAtPosition(position)).getLife();
                    Toast.makeText(ListViewMenu.this, "Life: " + animalLife, Toast.LENGTH_SHORT).show();
                }
            }
        });

        new RequestData(adapter).execute(url);
    }
}
