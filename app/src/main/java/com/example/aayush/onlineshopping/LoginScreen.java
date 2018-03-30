package com.example.aayush.onlineshopping;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dbs.DAOs;
import dbs.Databases;
import dbs.Entities;
import misc.PasswordOps;

public class LoginScreen extends AppCompatActivity {
    Bundle extras = getIntent().getExtras();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
    }

    public void loginClick(View view){
        Context current = getApplicationContext();
        int source = extras.getInt("source");

        EditText email = findViewById(R.id.email);
        EditText loginPassword = findViewById(R.id.loginPassword);

        String emailId = email.getText().toString();
        String password = loginPassword.getText().toString();
        String errorMessage = null;

        if(emailId == null || password == null){
            errorMessage = "Fields cannot be blank!";
        }

        if(source == 1){
            Databases.VendorDatabase db = Databases.VendorDatabase.getVendorDatabase(current);
            DAOs.VendorDAO vendorAcc;
            vendorAcc = db.vendorDAO();
            Entities.VendorEntity vendor;
            vendor = vendorAcc.getVendorByEmail(emailId);
            if(vendor == null){
                errorMessage = "Invalid Username/Password";
            }
            else{
                byte[] salt = vendor.getSalt().getBytes();
                String hashedPW = PasswordOps.getSecurePassword(password, salt);
                if(!vendor.getPassword().equals(hashedPW)){
                    errorMessage = "Invalid Username/Password";
                }
                else{
                    Intent nextPage=new Intent(this, VendorHomepage.class);
                    int vendID = vendor.getId();
                    nextPage.putExtra("vendID",vendID);
                    startActivity(nextPage);
                }
            }
        }
        else{
            Databases.UserDatabase db = Databases.UserDatabase.getUserDatabase(current);
            DAOs.UserDAO userAcc;
            userAcc = db.userDAO();
            Entities.UserEntity user = userAcc.getUserByEmail(emailId);

            if(user == null){
                errorMessage = "Invalid Username/Password";
            }
            else{
                byte[] salt = user.getSalt().getBytes();
                String hashedPW = PasswordOps.getSecurePassword(password, salt);
                if(!user.getPassword().equals(hashedPW)){
                    errorMessage = "Invalid Username/Password";
                }
                else{
                    Intent nextPage = new Intent(this, CategoryPage.class);
                    int vendID = user.getId();
                    nextPage.putExtra("vendID", vendID);
                    startActivity(nextPage);
                }
            }
        }

        Toast.makeText(current,errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(this, WelcomeScreen.class);
        startActivity(goBack);
    }

    public void registerClick(View view) {
        int source = extras.getInt("source");
        Intent regPage = new Intent(this, RegistrationScreen.class);
        regPage.putExtra("source", source);
        startActivity(regPage);
    }
}
