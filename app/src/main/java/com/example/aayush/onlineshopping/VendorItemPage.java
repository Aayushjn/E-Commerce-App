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
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_item_page);

        extras = getIntent().getExtras();

        assert extras != null;
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
        DeleteThread deleteThread = new DeleteThread(getApplicationContext(), extras);
        deleteThread.start();
    }

    public void updateQuantity(View view){
        TextView quantity = findViewById(R.id.productQuantity);

        UpdateThread updateThread = new UpdateThread(getApplicationContext(), extras, quantity);
        updateThread.start();
    }
}

class DeleteThread extends Thread {
    private Context current;
    private Bundle extras;

    DeleteThread(Context current, Bundle extras){
        this.current = current;
        this.extras = extras;
    }

    @Override
    public void run() {
        Databases.ProductDatabase productDB;
        DAOs.ProductDAO productDAO;
        productDB = Databases.ProductDatabase.getProductDatabase(current);
        productDAO = productDB.productDAO();

        assert extras != null;
        Entities.ProductEntity prod =
                productDAO.getProductByCategoryAndName(extras.getString("productName"),
                        extras.getString("productCategory"));
        productDAO.deleteProduct(prod);
        Intent reload = new Intent(current, VendorHomepage.class);
        reload.putExtra("id", extras.getIntArray("id"));
        current.startActivity(reload);
    }
}

class UpdateThread extends Thread {
    private Context current;
    private Bundle extras;
    private TextView quantity;

    UpdateThread(Context current, Bundle extras, TextView quantity){
        this.current = current;
        this.extras = extras;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        Databases.ProductDatabase productDB;
        DAOs.ProductDAO productDAO;
        productDB = Databases.ProductDatabase.getProductDatabase(current);
        productDAO = productDB.productDAO();

        assert extras != null;
        Entities.ProductEntity prod =
                productDAO.getProductByCategoryAndName(extras.getString("productName"),
                        extras.getString("productCategory"));

        prod.setQuantity(Integer.parseInt(quantity.getText().toString()));
        productDAO.updateProduct(prod);
        Intent reload = new Intent(current, VendorItemPage.class);
        reload.putExtra("id", extras.getIntArray("id"));
        current.startActivity(reload);
    }
}
