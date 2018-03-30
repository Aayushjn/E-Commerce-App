package com.example.aayush.onlineshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import misc.Item;
import misc.Objects;

public class ProductActivity extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    private TextView nameTextView, categoryTextView, priceTextView;
    private Spinner sizeSpinner, qtySpinner;
    private ImageView image;
    private Button cartButton;
    public int temp_size;
    public int temp_quantity;

    String name = extras.getString("name");
    String category = extras.getString("category");
    String price = extras.getString("price");
    int size = extras.getInt("size");
    int qty = extras.getInt("quantity");
    int imageId = extras.getInt("image");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        cartButton = findViewById(R.id.cartButton);
        nameTextView = findViewById(R.id.name);
        categoryTextView = findViewById(R.id.category);
        priceTextView = findViewById(R.id.price);
        sizeSpinner = findViewById(R.id.sizeSpinner);
        qtySpinner = findViewById(R.id.qtySpinner);
        image = findViewById(R.id.productImage);
        /*size_selected = findViewById(R.id.tv_size_selected);
        quantity_selected = findViewById(R.id.tv_quantity_selected);*/

        // TODO if NOT clothes category give default size as -1(integer) and quantity as 1(integer)
        // TODO if clothes category give default size as 32(integer) and quantity as 1(integer)

        if(size != -1) {
            List<Integer> size_components = new ArrayList<>();
            size_components.add(32);
            size_components.add(34);
            size_components.add(36);
            size_components.add(38);
            size_components.add(40);

            ArrayAdapter<Integer> size_adapter = new ArrayAdapter<Integer>(this,
                    android.R.layout.simple_spinner_item, size_components);
            size_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sizeSpinner.setAdapter(size_adapter);

            sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position,
                                           long id) {
                    temp_size = ((Integer) parent.getItemAtPosition(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {}
            });
        }
        else{
            sizeSpinner.setVisibility(View.GONE);
        }

        List<Integer> quantity_components = new ArrayList<>();
        quantity_components.add(1);
        quantity_components.add(2);
        quantity_components.add(3);
        quantity_components.add(4);
        quantity_components.add(5);

        ArrayAdapter<Integer> quantity_adapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, quantity_components);
        quantity_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(quantity_adapter);

        qtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp_quantity = ((Integer) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        nameTextView.setText(name);
        categoryTextView.setText(category);
        priceTextView.setText(price);
        image.setImageResource(imageId);
    }

    public void addToCart(View view){
        Item product = new Item(name, category, price, qty, size, 0);
        Objects.Cart cart = new Objects.Cart();
        // TODO add the item to cart
        // TODO update the cart ( the particular product's size) to temp_size
        // TODO update the cart ( the particular product's quantity) to temp_quantity
    }
}
