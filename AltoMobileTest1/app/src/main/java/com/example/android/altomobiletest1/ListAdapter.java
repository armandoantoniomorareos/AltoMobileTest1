package com.example.android.altomobiletest1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Animal> {

    protected ImageView img;

    public ListAdapter(Context context, ArrayList<Animal> list) {

        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animal item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, parent, false);
        }
        TextView animalView = convertView.findViewById(R.id.animal_textview);
        img = convertView.findViewById(R.id.img_item);
        if (item.getPictureURL().equals("")) {
            img.setImageResource(R.drawable.no_img);
        } else {
            new DownloadImage(img).execute(item.getPictureURL());
        }
        animalView.setText(item.getName());

        return convertView;
    }

}


