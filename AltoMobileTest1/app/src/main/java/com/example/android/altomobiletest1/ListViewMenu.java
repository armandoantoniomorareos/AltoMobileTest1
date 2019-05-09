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

    private ArrayList<ListItem> list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_menu);

        list = new ArrayList<>();
        initArray();

        ListAdapter adapter = new ListAdapter(this, list);
        listView = findViewById(R.id.list);

        LayoutInflater inflater = this.getLayoutInflater();
        View header = inflater.inflate(R.layout.listview_header, listView, false);

        listView.addHeaderView(header, null, false);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    String msg = ((ListItem) listView.getItemAtPosition(position)).getMsg();
                    Toast.makeText(ListViewMenu.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //Private method to help to initialize elements of the array
    private void initArray(){
        String tmp = "Day ";
        for(int i=1; i<=31;i++){
            list.add(new ListItem(tmp+i));
        }
    }
}
