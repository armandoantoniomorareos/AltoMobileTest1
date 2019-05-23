package com.example.android.altomobiletest1;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewResturant extends AppCompatActivity {

    private ArrayList<Product> listProducts;
    private ListView listView;
    private Button cartButton;
    private RestaurantListAdapter adapter;
    private TextView totalView;
    private static final String URL_PRODUCTS = "https://altomobile.blob.core.windows.net/training/menu.json";
    private static final int ERROR_CODE = 0;
    private double total = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_resturant);
        cartButton = findViewById(R.id.cart_button);
        totalView = findViewById(R.id.total_view);
        listProducts = new ArrayList<>();
        adapter = new RestaurantListAdapter(this, listProducts);
        //Parser p = new Parser(URL_PRODUCTS, adapter);
        listView = findViewById(R.id.restaurant_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Product product = ((Product) listView.getItemAtPosition(position));
                total += product.getPrice();
                updateTotal();
                enableCartButton();
                if (product.getListSubproducts().size() > 0) {
                    openSubproductsActivity(product.getListSubproducts());
                } else {
                    //add to total
                    //Toast.makeText(ListViewResturant.this, "No subproducts", Toast.LENGTH_SHORT).show();
                }
            }
        });

        new RequestData(adapter).execute(URL_PRODUCTS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reset_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset_values:
                total = 0.0;
                updateTotal();
                disableCartButton();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * @param list
     */

    public void openSubproductsActivity(ArrayList<Subproducts> list) {
        Intent i = new Intent(this, SubproductActivity.class);
        i.putParcelableArrayListExtra("subproducts", list);
        startActivityForResult(i, ERROR_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        total += data.getDoubleExtra("extra", 0.0);
        ;
        updateTotal();
    }

    /**
     * @param v
     */

    public void launchCustomDialog(View v) {
        String msg = String.format(getResources().getString(R.string.custom_dialog_message), total);
        CustomDialog d = new CustomDialog(this, msg);
        d.show();
    }

    /**
     *
     */

    private void updateTotal() {
        TextView totalView = findViewById(R.id.total_view);
        totalView.setText(String.format("$%.2f", total));
    }

    /**
     * Enable cart button
     */
    private void enableCartButton() {
        if(totalView.getVisibility() == View.INVISIBLE)
            totalView.setVisibility(View.VISIBLE);
        cartButton.setEnabled(true);
        cartButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.enabledCartButton));
        cartButton.setText("Pay");
    }

    private void disableCartButton() {
        if(totalView.getVisibility() == View.VISIBLE)
            totalView.setVisibility(View.INVISIBLE);
        totalView.setVisibility(View.INVISIBLE);
        cartButton.setEnabled(false);
        cartButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.disableCartButton));
        cartButton.setText("EMPTY CART");
    }
}
