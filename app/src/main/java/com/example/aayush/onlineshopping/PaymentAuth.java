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
import misc.Cart;

import static xdroid.toaster.Toaster.toast;
import static xdroid.toaster.Toaster.toastLong;

public class PaymentAuth extends AppCompatActivity {
    private Bundle extras;
    private final Cart cart = (Cart) (extras != null ? extras.getSerializable("cart") : null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_auth);
        setTitle("Authorize Payment");

        extras = getIntent().getExtras();

        TextView amt = findViewById(R.id.payAmt);
        TextView card = findViewById(R.id.userCard);

        assert extras != null;
        OnCreateThread onCreateThread = new OnCreateThread(getApplicationContext(), cart,
                extras.getInt("id"), amt, card);
        onCreateThread.start();
    }

    public void cancelPay(View view){
        Intent back = new Intent(this, CategoryPage.class);
        startActivity(back);
    }

    public void confirmPay(View view){
        assert extras != null;
        PaymentThread paymentThread = new PaymentThread(getApplicationContext(), cart, extras);
        paymentThread.start();
    }
}

class PaymentThread extends Thread {
    private Context current;
    private Cart cart;
    private Bundle extras;
    private int userId;

    PaymentThread(Context current, Cart cart, Bundle extras){
        this.current = current;
        this.cart = cart;
        this.extras = extras;
        this.userId = extras.getInt("id");
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

        assert cart != null;
        int cartQuantity = cart.getItemList().size();
        float userBalance = payAcc.getAmountById(userId);

        assert extras != null;
        float cartValue = extras.getFloat("cartValue");

        if (userBalance < cartValue){
            Intent exit = new Intent(current, CategoryPage.class);
            exit.putExtra("userId", userId);
            toast("Insufficient funds");
            current.startActivity(exit);
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

        toastLong("Payment Complete!");
        Intent paymentDone = new Intent(current, CategoryPage.class);
        paymentDone.putExtra("userId", userId);
        current.startActivity(paymentDone);
    }
}

class OnCreateThread extends Thread {
    private Context current;
    private Cart cart;
    private int userId;
    private TextView amt;
    private TextView card;

    OnCreateThread(Context current, Cart cart, int id, TextView amt, TextView card){
        this.current = current;
        this.cart = cart;
        this.userId = id;
        this.amt = amt;
        this.card = card;
    }

    @Override
    public void run() {
        Databases.UserDatabase userDB = Databases.UserDatabase.getUserDatabase(current);
        DAOs.UserDAO userDAO = userDB.userDAO();
        Entities.UserEntity user = userDAO.getUserById(userId);
        Databases.PaymentDatabase paymentDB = Databases.PaymentDatabase.getPaymentDatabase(current);
        DAOs.PaymentDAO paymentDAO = paymentDB.paymentDAO();
        Entities.PaymentEntity pay = paymentDAO.getCardDetailsById(userId);
        assert cart != null;
        amt.setText(String.valueOf(cart.getTotalCost()));
        amt.setText(user.getEmailId());
        card.setText(String.valueOf(pay.getCardNumber()));
    }
}
