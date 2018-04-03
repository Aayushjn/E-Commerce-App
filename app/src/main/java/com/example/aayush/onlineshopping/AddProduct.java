package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;

import static xdroid.toaster.Toaster.toast;

public class AddProduct extends AppCompatActivity {
    private Bundle extras;

    private String category;
    private int size;

    EditText nameText;
    EditText qtyText;
    EditText priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Spinner catSpin = findViewById(R.id.spinnerCat);
        Spinner sizeSpin = findViewById(R.id.spinnerSize);
        nameText = findViewById(R.id.prodName);
        qtyText = findViewById(R.id.prodQuant);
        priceText = findViewById(R.id.prodPrice);

        extras = getIntent().getExtras();
        List<String> categoryArray = new ArrayList<>();
        categoryArray.add("Clothing");
        categoryArray.add("Books");
        categoryArray.add("Appliances");
        categoryArray.add("Electronics");
        categoryArray.add("Furniture");
        categoryArray.add("Food");
        
        List<Integer> sizeArray = new ArrayList<>();
        sizeArray.add(32);
        sizeArray.add(34);
        sizeArray.add(36);
        sizeArray.add(38);
        sizeArray.add(40);

        ArrayAdapter<String> catAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, categoryArray);
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpin.setAdapter(catAdapter);
        catSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ArrayAdapter<Integer> sizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sizeArray);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpin.setAdapter(sizeAdapter);
        sizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void addProduct(View view){
        float price = Float.parseFloat(priceText.getText().toString());
        int qty = Integer.parseInt(qtyText.getText().toString());
        String name = nameText.getText().toString();

        ProductThread productThread = new ProductThread(getApplicationContext(), extras, name,
                category, size, price, qty);
        productThread.start();
    }
}

class ProductThread extends Thread {
    private float price;
    private int qty;
    private String name;
    private String category;
    private int size;
    private Bundle extras;
    private Context current;

    ProductThread(Context current, Bundle extras, String name, String category, int size,
                  float price, int qty){
        this.current = current;
        this.extras = extras;
        this.name = name;
        this.category = category;
        this.size = size;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public void run() {
        if(Float.toString(price) != null && Integer.toString(qty) != null &&
                name != null && category != null && Integer.toString(size) != null){
            assert extras != null;
            int id = extras.getInt("id");
            Databases.ProductDatabase productDatabase =
                    Databases.ProductDatabase.getProductDatabase(current);
            DAOs.ProductDAO productDAO = productDatabase.productDAO();
            Entities.ProductEntity product = new Entities.ProductEntity(price, qty, category, name,
                    Integer.toString(size), id);

            productDAO.insertProduct(product);

            Intent intent = new Intent(current, VendorHomepage.class);
            intent.putExtra("id", id);
            current.startActivity(intent);
        }
        else{
           toast("Can't leave any options blank");
        }
    }
}
