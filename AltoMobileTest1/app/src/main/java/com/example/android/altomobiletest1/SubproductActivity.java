package com.example.android.altomobiletest1;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubproductActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textview;
    private static Button select;
    private ArrayList<Subproducts> array;
    private RadioButton prevSelected, currentSelected;
    private double extra_price = 0.0;
    private int selectedPosition = 0, previousPosition = -1;
    public static boolean enableButton = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subproduct);

        Bundle b = getIntent().getExtras();
        array = b.getParcelableArrayList("subproducts");
        select = findViewById(R.id.select_subproduct_button);
        SubproductListAdapter adapter = new SubproductListAdapter(this, array);
        ListView listView = findViewById(R.id.subproducts_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RadioButton radio = view.findViewById(R.id.radiobutton);
                if (enableButton == true) {
                    enableSelectButton();
                    enableButton = false;
                }

                if(RadioStatus.currentRadio == null){
                    RadioStatus.currentRadio = radio;
                    RadioStatus.currentRadio.setChecked(true);
                }else{
                    RadioStatus.prevRadio = RadioStatus.currentRadio;
                    RadioStatus.currentRadio = radio;
                    RadioStatus.prevRadio.setChecked(false);
                    RadioStatus.currentRadio.setChecked(true);
                }

                extra_price = ((Subproducts) listView.getItemAtPosition(position)).getExtra();

            }
        });

        select = findViewById(R.id.select_subproduct_button);
        select.setOnClickListener(this);
    }

    public void returnRestaurantActivity() {
        Intent result = new Intent();
        result.putExtra("extra", extra_price);
        setResult(1, result);
        enableButton = true;
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.select_subproduct_button) {
            returnRestaurantActivity();
        }
    }

    /**
     * Detects when back key is pressed
     *
     * @param keycode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            returnRestaurantActivity();
        }
        return super.onKeyDown(keycode, event);
    }

    public static void enableSelectButton() {
        select.setEnabled(true);
        select.setBackgroundColor(Color.parseColor("#ff33b5e5"));
    }
}
