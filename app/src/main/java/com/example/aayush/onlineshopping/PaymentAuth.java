package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;

public class PaymentAuth extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_auth);

        //TODO: Write the yucky on create db opening code I cant do this its too repetitive.
        TextView amt = findViewById(R.id.payAmt);
        amt.setText(cartValue);
        TextView em = findViewById(R.id.userEmail);
        amt.setText(user.emailId);
        TextView card = findViewById(R.id.userCard);
        card.setText(userent.cardNumber);
    }

    public void cancelPay(View view){
        Intent back = new Intent(this, CategoryPage.class);
        startActivity(back);
    }

    public void confirmPay(View view){
        Databases.PaymentDatabase paymentDB;
        Databases.VendorDatabase vendorDB;
        Databases.UserDatabase userDB;
        DAOs.PaymentDAO payAcc;
        DAOs.VendorDAO vendAcc;
        DAOs.UserDAO userAcc;
        Context current = getApplicationContext();
        paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        vendorDB = Databases.VendorDatabase.getVendorDatabase(current);
        userDB = Databases.UserDatabase.getUserDatabase(current);
        payAcc = paymentDB.paymentDAO();
        vendAcc = vendorDB.vendorDAO();
        userAcc = userDB.userDAO();

        ArrayList<Entities.ProductEntity> products = extras.getParcelableArrayList("cart");
        int userId = extras.getInt("userId");

        Entities.UserEntity user = userAcc.getUserById(userId);
        Entities.ProductEntity prod;
        Entities.VendorEntity vendor = null;

        int cartQuantity = 0;
        if (products != null) {
            cartQuantity = products.size();
        }
        float userBalance = payAcc.getAmountById(userId);
        float cartValue = extras.getFloat("cartValue");

        if (userBalance < cartValue){
            Intent exit = new Intent(this, CategoryPage.class);
            exit.putExtra("userId", userId);
            Toast.makeText(current,"Insufficient Funds", Toast.LENGTH_LONG).show();
            startActivity(exit);
        }

        float uAmount = 0, vAmount;
        for(int i = 0;i < cartQuantity;i++){
            vAmount = 0;
            prod = products.get(i);
            vendor = vendAcc.getVendorById(prod.getVendId());
            uAmount -= prod.getCost() * prod.getQuantity();
            vAmount += prod.getCost() * prod.getQuantity();
            payAcc.updateAmountById(vAmount, vendor.getId());
        }
        payAcc.updateAmountById(uAmount, userId);

        Toast.makeText(current,"PAYMENT COMPLETE", Toast.LENGTH_LONG).show();
        Intent paymentDone = new Intent(this, CategoryPage.class);
        paymentDone.putExtra("userId", userId);
        startActivity(paymentDone);
    }
}
