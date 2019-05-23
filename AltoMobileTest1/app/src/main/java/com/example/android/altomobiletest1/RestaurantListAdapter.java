package com.example.android.altomobiletest1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class RestaurantListAdapter extends ArrayAdapter<Product> {

    public RestaurantListAdapter(Context context, ArrayList<Product> products) {
        super(context, 0, products);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Product p = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.restaurant_item, parent, false);
        }
        TextView productName = convertView.findViewById(R.id.product_name_textview);
        TextView subproducts = convertView.findViewById(R.id.subproduts_textview);

        productName.setText(p.getName());
        if(p.subproducts.size() == 0){
            //Log.v("RestaurantListAdaptor", "---------> no tiene subproducts" );
            subproducts.setVisibility(View.GONE);
        }else{
            subproducts.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
