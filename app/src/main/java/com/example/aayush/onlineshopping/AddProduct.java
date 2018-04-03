package com.example.aayush.onlineshopping;

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

public class AddProduct extends AppCompatActivity {
    private Bundle extras;

    private String category;
    private String size;
    private String name;
    private float price;
    private int qty;

    private final Spinner catSpin = findViewById(R.id.spinnerCat);
    private final Spinner sizeSpin = findViewById(R.id.spinnerSize);
    private final EditText nameText = findViewById(R.id.prodName);
    private final EditText qtyText = findViewById(R.id.prodQuant);
    private final EditText priceText = findViewById(R.id.prodPrice);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        extras = getIntent().getExtras();
        List<String> categoryArray = new ArrayList<>();
        categoryArray.add("Clothing");
        categoryArray.add("Books");
        categoryArray.add("Appliances");
        categoryArray.add("Electronics");
        categoryArray.add("Furniture");
        categoryArray.add("Food");
        
        List<String> sizeArray = new ArrayList<>();
        sizeArray.add("S");
        sizeArray.add("M");
        sizeArray.add("L");
        sizeArray.add("XL");

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

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sizeArray);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSpin.setAdapter(sizeAdapter);
        sizeSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        price = Float.parseFloat(priceText.getText().toString());
        qty = Integer.parseInt(qtyText.getText().toString());
        name = nameText.getText().toString();
    }

    public void addProduct(View view){
        if(Float.toString(price) != null && Integer.toString(qty) != null &&
                name != null && category != null && size == null){
            assert extras != null;
            int id = extras.getInt("id");
            Databases.ProductDatabase productDatabase =
                    Databases.ProductDatabase.getProductDatabase(getApplicationContext());
            DAOs.ProductDAO productDAO = productDatabase.productDAO();
            Entities.ProductEntity product = new Entities.ProductEntity(price, qty, category, name,
                    size, id);

            productDAO.insertProduct(product);

            Intent intent = new Intent(this, VendorHomepage.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Can't leave any options blank",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
