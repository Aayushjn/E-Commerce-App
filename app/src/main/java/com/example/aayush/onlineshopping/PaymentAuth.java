package com.example.aayush.onlineshopping;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;
import misc.Cart;
import misc.PasswordOps;

import static xdroid.toaster.Toaster.toast;

public class PaymentAuth extends AppCompatActivity {
    EditText pinText;
    private Bundle extras;
    private Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_auth);
        setTitle("Authorize Payment");

        extras = getIntent().getExtras();
        cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);

        TextView amt = findViewById(R.id.payAmt);
        TextView card = findViewById(R.id.userCard);
        TextView email = findViewById(R.id.userEmail);
        pinText = findViewById(R.id.enterPIN);

        assert extras != null;
        OnCreateThread onCreateThread = new OnCreateThread(this, cart,
                extras.getInt("id"), amt, card, email);
        onCreateThread.start();
    }

    public void cancelPay(View view){
        Intent back = new Intent(this, CategoryPage.class);
        back.putExtra("id", extras.getInt("id"));
        toast("Payment canceled");
        startActivity(back);
    }

    public void confirmPay(View view){
        String pin = pinText.getText().toString();

        assert extras != null;
        PaymentThread paymentThread = new PaymentThread(this, cart, extras, pin);
        paymentThread.start();
    }
}

class PaymentThread extends Thread {
    private Activity current;
    private Cart cart;
    private Bundle extras;
    private int userId;
    private String pin;

    PaymentThread(Activity current, Cart cart, Bundle extras, String pin){
        this.current = current;
        this.cart = cart;
        this.extras = extras;
        this.userId = extras.getInt("id");
        this.pin = pin;
    }

    @Override
    public void run() {
        Databases.PaymentDatabase paymentDB;
        Databases.VendorDatabase vendorDB;
        Databases.ProductDatabase productDB;
        DAOs.PaymentDAO payAcc;
        DAOs.VendorDAO vendAcc;
        DAOs.ProductDAO productAcc;
        paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        vendorDB = Databases.VendorDatabase.getVendorDatabase(current);
        productDB = Databases.ProductDatabase.getProductDatabase(current);
        payAcc = paymentDB.paymentDAO();
        vendAcc = vendorDB.vendorDAO();
        productAcc = productDB.productDAO();
        Entities.ProductEntity prod;
        Entities.VendorEntity vendor;

        Cursor cursor = payAcc.getCardDetailsById(userId);
        String salt = null;
        String pinUser = null;

        assert cart != null;
        int cartQuantity = cart.getItemList().size();
        float userBalance = payAcc.getAmountById(userId);

        assert extras != null;
        float cartValue = cart.getTotalCost();

        if(cursor.moveToFirst()){
            salt = cursor.getString(cursor.getColumnIndex("salt"));
            pinUser = cursor.getString(cursor.getColumnIndex("pin"));
            cursor.close();
        }

        pin = PasswordOps.getSecurePassword(pin, salt != null ? salt.getBytes() : new byte[0]);

        if(!pin.equals(pinUser)){
            Intent intent = new Intent(current, CategoryPage.class);
            toast("PIN doesn't match");
            intent.putExtra("id", userId);
            current.startActivity(intent);
        }
        else if (userBalance < cartValue){
            Intent exit = new Intent(current, CategoryPage.class);
            exit.putExtra("userId", userId);
            toast("Insufficient funds");
            current.startActivity(exit);
        }
        else {
            float vAmount;
            for (int i = 0; i < cartQuantity; i++) {
                vAmount = 0;
                String category = cart.getItemList().get(i).getCategory();
                String name = cart.getItemList().get(i).getName();
                prod = productAcc.getProductByCategoryAndName(category, name);
                vendor = vendAcc.getVendorById(prod.getVendId());
                userBalance -= prod.getCost() * prod.getQuantity();
                vAmount += prod.getCost() * prod.getQuantity();
                payAcc.updateAmountById(vAmount, vendor.getId());
            }
            payAcc.updateAmountById(userBalance, userId);

            Intent paymentDone = new Intent(current, CategoryPage.class);
            paymentDone.putExtra("userId", userId);
            current.startActivity(paymentDone);
        }
        toast("Payment Complete!");
    }
}

class OnCreateThread extends Thread {
    private Activity current;
    private Cart cart;
    private int userId;
    private TextView amt;
    private TextView card;
    private TextView emailId;

    OnCreateThread(Activity current, Cart cart, int id, TextView amt, TextView card, TextView emailId){
        this.current = current;
        this.cart = cart;
        this.userId = id;
        this.amt = amt;
        this.card = card;
        this.emailId = emailId;
    }

    @Override
    public void run() {
        Databases.UserDatabase userDB = Databases.UserDatabase.getUserDatabase(current);
        DAOs.UserDAO userDAO = userDB.userDAO();
        Entities.UserEntity user = userDAO.getUserById(userId);
        Databases.PaymentDatabase paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        DAOs.PaymentDAO paymentDAO = paymentDB.paymentDAO();
        Cursor pay = paymentDAO.getCardDetailsById(userId);
        long cardNo = 0;

        if(pay.moveToFirst()){
            cardNo = pay.getLong(pay.getColumnIndex("card"));
            pay.close();
        }
        else{
            toast("Is null");
        }
        assert cart != null;
        amt.setText(String.valueOf(cart.getTotalCost()));
        emailId.setText(user.getEmailId());
        card.setText(String.valueOf(cardNo));
    }
}
