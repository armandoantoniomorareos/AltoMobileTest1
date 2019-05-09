package com.example.android.altomobiletest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListItem> {

    public ListAdapter(Context context, ArrayList<ListItem> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ListItem item = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }
        TextView days = convertView.findViewById(R.id.day_textview);
        days.setText(item.getMsg());
        return convertView;
    }

}
