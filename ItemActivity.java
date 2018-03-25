package com.example.dell.logindemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private TextView name, category, price;
    private Spinner size, quantity;
    private TextView size_selected, quantity_selected;
    //private TextView color;
    private ImageView image;
    private Button add_to_cart;
    public int temp_size;
    public int temp_quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        add_to_cart = (Button) findViewById(R.id.btn_add_to_cart);
        name = (TextView) findViewById(R.id.tv_name);
        category = (TextView) findViewById(R.id.tv_category);
        price = (TextView) findViewById(R.id.tv_price);
        //color = (TextView) findViewById(R.id.tv_color);
        size = (Spinner) findViewById(R.id.spn_size);
        quantity = (Spinner) findViewById(R.id.spn_quantity);
        image = (ImageView) findViewById(R.id.iv_image);
        size_selected = (TextView) findViewById(R.id.tv_size_selected);
        quantity_selected = (TextView) findViewById(R.id.tv_quantity_selected);


        // receive data
        // TODO if NOT clothes category give default size as -1(integer) and quantity as 1(integer)
        // TODO if clothes category give default size as 32(integer) and quantity as 1(integer)
        Intent intent = getIntent();
        String str_name = intent.getExtras().getString("name");
        String str_category = intent.getExtras().getString("category");
        String str_price = intent.getExtras().getString("price");
        //String str_color = intent.getExtras().getString("color");
        int int_size = intent.getExtras().getInt("size");
        int int_quantity = intent.getExtras().getInt("quantity");
        int int_image = intent.getExtras().getInt("image");

        // size spinner
        // if clothes category
        if(int_size != -1) {
            List<Integer> size_components = new ArrayList<>();
            size_components.add(32);
            size_components.add(34);
            size_components.add(36);
            size_components.add(38);
            size_components.add(40);

            ArrayAdapter<Integer> size_adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, size_components);
            size_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            size.setAdapter(size_adapter);

            size.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    temp_size = ((Integer) parent.getItemAtPosition(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        else{
            // if not clothes category the spinner is not made visible
            size.setVisibility(View.GONE);
        }

        // quantity spinner
        List<Integer> quantity_components = new ArrayList<>();
        quantity_components.add(1);
        quantity_components.add(2);
        quantity_components.add(3);
        quantity_components.add(4);
        quantity_components.add(5);

        ArrayAdapter<Integer> quantity_adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, quantity_components);
        quantity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantity.setAdapter(quantity_adapter);

        quantity.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp_quantity = ((Integer) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // setting values
        name.setText(str_name);
        category.setText(str_category);
        price.setText(str_price);
        //color.setText(str_color);
        //size.setText(String.valueOf(int_size));
        //quantity.setText(String.valueOf(int_quantity));
        if(int_size != -1) {
            size_selected.setText(String.valueOf(temp_size));
        }
        else{
            size_selected.setText("  ");
        }
        quantity_selected.setText(String.valueOf(temp_quantity));
        image.setImageResource(int_image);
    }

    public void addToCart(View view){
        // TODO add the item to cart
        // TODO update the cart ( the particular product's size) to temp_size
        // TODO update the cart ( the particular product's quantity) to temp_quantity
    }
}
