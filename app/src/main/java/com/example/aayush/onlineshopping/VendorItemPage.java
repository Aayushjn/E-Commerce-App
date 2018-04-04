package com.example.aayush.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;

import static xdroid.toaster.Toaster.toast;

public class VendorItemPage extends AppCompatActivity {
    private Bundle extras;
    TextView pQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_item_page);

        extras = getIntent().getExtras();

        assert extras != null;
        String name = extras.getString("name");
        String category = extras.getString("category");
        float price = extras.getFloat("price");
        int quantity = extras.getInt("quantity");
        TextView pName = findViewById(R.id.productName);
        pName.setText(name);
        TextView pCategory = findViewById(R.id.productCategory);
        pCategory.setText(category);
        TextView pPrice = findViewById(R.id.productPrice);
        pPrice.setText(String.valueOf(price));
        pQuantity = findViewById(R.id.productQuantity);
        pQuantity.setText(String.valueOf(quantity));
    }

    public void deleteProduct(View view){
        DeleteThread deleteThread = new DeleteThread(this, extras);
        deleteThread.start();
    }

    public void updateQuantity(View view){
        UpdateThread updateThread = new UpdateThread(this, extras, pQuantity);
        updateThread.start();
    }
}

class DeleteThread extends Thread {
    private Activity current;
    private Bundle extras;

    DeleteThread(Activity current, Bundle extras){
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
                productDAO.getProductByCategoryAndName(extras.getString("category"),
                        extras.getString("name"));
        productDAO.deleteProduct(prod);
        toast("Product deleted");
        Intent reload = new Intent(current, VendorHomepage.class);
        reload.putExtra("id", extras.getInt("id"));
        current.startActivity(reload);
    }
}

class UpdateThread extends Thread {
    private Activity current;
    private Bundle extras;
    private TextView quantity;

    UpdateThread(Activity current, Bundle extras, TextView quantity){
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
                productDAO.getProductByCategoryAndName(extras.getString("category"),
                        extras.getString("name"));

        prod.setQuantity(Integer.parseInt(quantity.getText().toString()));
        productDAO.updateProduct(prod);
        toast("Product updated");
        Intent reload = new Intent(current, VendorItemPage.class);
        reload.putExtra("id", extras.getInt("id"));
        current.startActivity(reload);
    }
}
