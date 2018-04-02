package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;
import misc.Cart;

public class PaymentAuth extends AppCompatActivity {
    final Bundle extras = getIntent().getExtras();

    final int userId = extras != null ? extras.getInt("id") : 0;
    final Cart cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_auth);
        setTitle("Authorize Payment");

        Databases.UserDatabase userDB = Databases.UserDatabase.getUserDatabase(this);
        DAOs.UserDAO userDAO = userDB.userDAO();
        Entities.UserEntity user = userDAO.getUserById(userId);
        Databases.PaymentDatabase paymentDB = Databases.PaymentDatabase.getPaymentDatabase(this);
        DAOs.PaymentDAO paymentDAO = paymentDB.paymentDAO();
        Entities.PaymentEntity pay = paymentDAO.getCardDetailsById(userId);
        TextView amt = findViewById(R.id.payAmt);
        assert cart != null;
        amt.setText(String.valueOf(cart.getTotalCost()));
        TextView em = findViewById(R.id.userEmail);
        amt.setText(user.getEmailId());
        TextView card = findViewById(R.id.userCard);
        card.setText(String.valueOf(pay.getCardNumber()));
    }

    public void cancelPay(View view){
        Intent back = new Intent(this, CategoryPage.class);
        startActivity(back);
    }

    public void confirmPay(View view){
        Databases.PaymentDatabase paymentDB;
        Databases.VendorDatabase vendorDB;
        Databases.UserDatabase userDB;
        Databases.ProductDatabase productDB;
        DAOs.PaymentDAO payAcc;
        DAOs.VendorDAO vendAcc;
        DAOs.UserDAO userAcc;
        DAOs.ProductDAO productAcc;
        Context current = getApplicationContext();
        paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        vendorDB = Databases.VendorDatabase.getVendorDatabase(current);
        productDB = Databases.ProductDatabase.getProductDatabase(current);
        payAcc = paymentDB.paymentDAO();
        vendAcc = vendorDB.vendorDAO();
        productAcc = productDB.productDAO();
        Entities.ProductEntity prod;
        Entities.VendorEntity vendor;

        assert cart != null;
        int cartQuantity = cart.getItemList().size();
        float userBalance = payAcc.getAmountById(userId);

        assert extras != null;
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
            String category = cart.getItemList().get(i).getCategory();
            String name = cart.getItemList().get(i).getName();
            prod = productAcc.getProductByCategoryAndName(category, name);
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
