package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;

public class VendorItemPage extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_item_page);

        String name = extras.getString("productName");
        String category = extras.getString("productCategory");
        float price = extras.getFloat("price");
        int quantity = extras.getInt("quantity");
        TextView pName = findViewById(R.id.productName);
        pName.setText(name);
        TextView pCategory = findViewById(R.id.productCategory);
        pCategory.setText(category);
        TextView pPrice = findViewById(R.id.productPrice);
        pPrice.setText(String.valueOf(price));
        TextView pQuantity = findViewById(R.id.productQuantity);
        pQuantity.setText(quantity);
    }

    public void deleteProduct(View view){
        Databases.ProductDatabase productDB;
        DAOs.ProductDAO productDAO;
        Context current = getApplicationContext();
        productDB = Databases.ProductDatabase.getProductDatabase(current);
        productDAO = productDB.productDAO();

        Entities.ProductEntity prod =
                productDAO.getProductByCategory(extras.getString("productName"),
                        extras.getString("productCategory"));
        productDAO.deleteProduct(prod);
        Intent reload = new Intent(this, VendorHomepage.class);
        reload.putExtra("id", extras.getIntArray("id"));
        startActivity(reload);
    }

    public void updateQuantity(View view){
        Databases.ProductDatabase productDB;
        DAOs.ProductDAO productDAO;
        Context current=getApplicationContext();
        productDB = Databases.ProductDatabase.getProductDatabase(current);
        productDAO = productDB.productDAO();
        Entities.ProductEntity prod =
                productDAO.getProductByCategory(extras.getString("productName"),
                        extras.getString("productCatagory"));
        TextView quantity = findViewById(R.id.productQuantity);
        prod.setQuantity(Integer.parseInt(quantity.getText().toString()));
        productDAO.updateProduct(prod);
        Intent reload = new Intent(this, VendorItemPage.class);
        reload.putExtra("id", extras.getIntArray("id"));
        startActivity(reload);
    }
}
