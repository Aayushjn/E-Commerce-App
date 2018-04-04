package com.example.aayush.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;

import static xdroid.toaster.Toaster.toast;

public class AddProduct extends AppCompatActivity {
    private Bundle extras;

    private String category;

    EditText nameText;
    EditText qtyText;
    EditText priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Spinner catSpin = findViewById(R.id.spinnerCat);
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
    }

    public void addProduct(View view){
        float price = Float.parseFloat(priceText.getText().toString());
        int qty = Integer.parseInt(qtyText.getText().toString());
        String name = nameText.getText().toString();

        ProductThread productThread = new ProductThread(this, extras, name,
                category, price, qty);
        productThread.start();
    }
}

class ProductThread extends Thread {
    private float price;
    private int qty;
    private String name;
    private String category;
    private Bundle extras;
    private Activity current;

    ProductThread(Activity current, Bundle extras, String name, String category, float price,
                  int qty){
        this.current = current;
        this.extras = extras;
        this.name = name;
        this.category = category;
        this.price = price;
        this.qty = qty;
    }

    @Override
    public void run() {
        if(Float.toString(price) != null && Integer.toString(qty) != null &&
                name != null && category != null){
            assert extras != null;
            int id = extras.getInt("id");
            Databases.ProductDatabase productDatabase =
                    Databases.ProductDatabase.getProductDatabase(current);
            DAOs.ProductDAO productDAO = productDatabase.productDAO();
            Entities.ProductEntity product = new Entities.ProductEntity(price, qty, category, name,
                    id);

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
