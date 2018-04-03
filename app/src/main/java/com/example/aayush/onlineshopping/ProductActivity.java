package com.example.aayush.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import misc.Cart;
import misc.Item;

public class ProductActivity extends AppCompatActivity {
    private Bundle extras;

    private Cart cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);

    private int temp_size;
    private int temp_quantity;

    private final String name = extras != null ? extras.getString("name") : null;
    private final String category = extras != null ? extras.getString("category") : null;
    private final String price = extras != null ? extras.getString("price") : null;
    private final int size = extras != null ? extras.getInt("size") : -1;
    private final int imageId = extras != null ? extras.getInt("image") : 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        extras = getIntent().getExtras();

        TextView nameTextView = findViewById(R.id.name);
        TextView categoryTextView = findViewById(R.id.category);
        TextView priceTextView = findViewById(R.id.price);
        Spinner sizeSpinner = findViewById(R.id.sizeSpinner);
        Spinner qtySpinner = findViewById(R.id.qtySpinner);
        //ImageView image = findViewById(R.id.productImage);

        if(size != -1) {
            List<Integer> sizeComponents = new ArrayList<>();
            sizeComponents.add(32);
            sizeComponents.add(34);
            sizeComponents.add(36);
            sizeComponents.add(38);
            sizeComponents.add(40);

            ArrayAdapter<Integer> sizeAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, sizeComponents);
            sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sizeSpinner.setAdapter(sizeAdapter);

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

        List<Integer> qtyComponents = new ArrayList<>();
        qtyComponents.add(1);
        qtyComponents.add(2);
        qtyComponents.add(3);
        qtyComponents.add(4);
        qtyComponents.add(5);

        ArrayAdapter<Integer> qtyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, qtyComponents);
        qtyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qtySpinner.setAdapter(qtyAdapter);

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
        //image.setImageResource(imageId);
    }

    public void addToCart(View view){
        Cart userCart;
        Item item = new Item(name, category, Float.parseFloat(price), temp_quantity, temp_size,
                imageId);
        Intent cartIntent = new Intent(this, CartActivity.class);
        if(cart == null) {
            userCart = new Cart();
            userCart.addItem(item);
            cartIntent.putExtra("cart", userCart);
        }
        else{
            cart.getItemList().add(item);
            cartIntent.putExtra("cart", cart);
        }
        Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
        startActivity(cartIntent);
    }
}
