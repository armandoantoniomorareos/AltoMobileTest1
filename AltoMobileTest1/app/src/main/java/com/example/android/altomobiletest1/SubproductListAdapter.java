package com.example.android.altomobiletest1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class SubproductListAdapter extends ArrayAdapter<Subproducts> {

   private RadioButton currentRadio, prevRadio;

    public SubproductListAdapter(Context context, ArrayList<Subproducts> subproductsList) {
        super(context, 0, subproductsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Subproducts sub = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subproduc_item_view, parent, false);
        }
        TextView subproducNameView = convertView.findViewById(R.id.subproduct_name);
        TextView subproductExtraView = convertView.findViewById(R.id.subproduct_extra);
        RadioButton radio = convertView.findViewById(R.id.radiobutton);

        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radio = v.findViewById(R.id.radiobutton);

                if (SubproductActivity.enableButton == true) {
                    SubproductActivity.enableSelectButton();
                    SubproductActivity.enableButton = false;
                }

                if(RadioStatus.currentRadio == null){
                    RadioStatus.currentRadio = radio;
                    RadioStatus.currentRadio.setChecked(true);
                }else{
                    RadioStatus.prevRadio = RadioStatus.currentRadio;
                    RadioStatus.currentRadio = radio;
                    RadioStatus.prevRadio.setChecked(false);
                    RadioStatus.currentRadio.setChecked(true);
                    //RadioStatus.currentRadio
                }

                /*if(currentRadio == null){
                    currentRadio = radio;
                    currentRadio.setChecked(true);
                }else{
                    prevRadio = currentRadio;
                    currentRadio = radio;
                    prevRadio.setChecked(false);
                    currentRadio.setChecked(true);
                }*/
            }
        });

        subproducNameView.setText(sub.getName());
        if(sub.getExtra() != 0.0f) {
            subproductExtraView.setText("+$" + sub.getExtra());
        }else{
            subproductExtraView.setVisibility(View.GONE);
        }
        return convertView;
    }

}
