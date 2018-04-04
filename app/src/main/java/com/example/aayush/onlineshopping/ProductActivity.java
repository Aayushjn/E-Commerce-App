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
    private Cart cart;

    private int temp_quantity;

    private String name;
    private String category;
    private String price;
    private int imageId;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Bundle extras = getIntent().getExtras();
        cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);
        name = extras != null ? extras.getString("name") : null;
        category = extras != null ? extras.getString("category") : null;
        price = extras != null ? extras.getString("price") : null;
        imageId = extras != null ? extras.getInt("image") : 0;
        id = extras != null ? extras.getInt("id") : 0;


        TextView nameTextView = findViewById(R.id.productName);
        TextView categoryTextView = findViewById(R.id.category);
        TextView priceTextView = findViewById(R.id.price);
        Spinner qtySpinner = findViewById(R.id.qtySpinner);
        //ImageView image = findViewById(R.id.productImage);


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
        Item item = new Item(name, category, Float.parseFloat(price), temp_quantity, imageId);
        Intent cartIntent = new Intent(this, CartActivity.class);
        if(cart == null) {
            userCart = new Cart();
            userCart.addItem(item);
            cartIntent.putExtra("id", id);
            cartIntent.putExtra("cart", userCart);
        }
        else{
            cart.getItemList().add(item);
            cartIntent.putExtra("id", id);
            cartIntent.putExtra("cart", cart);
        }
        Toast.makeText(getApplicationContext(), "Item added", Toast.LENGTH_SHORT).show();
        startActivity(cartIntent);
    }
}
