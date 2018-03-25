package dbs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Aayush on 13-Mar-18.
 */

public class Entities {
    @Entity(tableName = "products", indices = {@Index(value = {"pId", "vendor"}, unique = true)},
            foreignKeys = @ForeignKey(entity = VendorEntity.class, parentColumns = "id",
            childColumns = "vendor", onDelete = CASCADE))
    class ProductEntity{
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "pId")
        private int pId;

        @ColumnInfo(name = "cost")
        private float cost;

        @ColumnInfo(name = "quantity")
        private int quantity;

        @ColumnInfo(name = "category")
        private String category;

        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "size")
        private String size;

        @ColumnInfo(name = "vendor")
        private int vendId;

        ProductEntity(int pId, float cost, int quantity, String category, String name, String size,
                      int vendId) {
            this.pId = pId;
            this.cost = cost;
            this.quantity = quantity;

            this.category = category;
            this.name = name;
            this.size = size;
            this.vendId = vendId;
        }

        public void setpId(int pId) {
            this.pId = pId;
        }

        public int getPId() {
            return pId;
        }

        public float getCost() {
            return cost;
        }

        public void setCost(float cost) {
            this.cost = cost;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getVendId() {
            return vendId;
        }

        public void setVendId(int vendId) {
            this.vendId = vendId;
        }
    }

    @Entity
    class PersonEntity {
        @ColumnInfo(name = "address")
        private String address;
        @PrimaryKey
        @ColumnInfo(name = "emailId")
        @NonNull
        private String emailId;
        @ColumnInfo(name = "name")
        private String name;
        @ColumnInfo(name = "password")
        private String password;
        @ColumnInfo(name = "salt")
        private String salt;

        PersonEntity(String address, String emailId, String name, String password, String salt) {
            this.address = address;
            this.emailId = emailId;
            this.name = name;
            this.password = password;
            this.salt = salt;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(@NonNull String emailId) {
            this.emailId = emailId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }


    @Entity(tableName = "users", indices = @Index(value = {"id"}, unique = true))
    class UserEntity extends PersonEntity {
        @ColumnInfo(name = "id")
        private int id;

        UserEntity(int id, String address, String emailId, String name, String password,
                   String salt) {
            super(address, emailId, name, password, salt);
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    @Entity(tableName = "vendors", indices = {@Index(value = {"id"}, unique = true)},
            foreignKeys = @ForeignKey(entity = ProductEntity.class, parentColumns = "pId",
            childColumns = "product", onDelete = CASCADE))
    class VendorEntity extends PersonEntity{
        @ColumnInfo(name = "id")
        private int id;
        @ColumnInfo(name = "product")
        private ArrayList<Integer> prodIds;

        VendorEntity(int id, String address, String emailId, String name, String password,
                     String salt) {
            super(address, emailId, name, password, salt);
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ArrayList<Integer> getProdIds() {
            return prodIds;
        }

        public void setProdIds(ArrayList<Integer> prodIds) {
            this.prodIds = prodIds;
        }
    }

    @Entity(tableName = "cards", indices = {@Index(value = {"id", "id"}, unique = true)},
            foreignKeys = {@ForeignKey(entity = UserEntity.class, parentColumns = "id",
                    childColumns = "id", onDelete = CASCADE),
                    @ForeignKey(entity = VendorEntity.class, parentColumns = "id",
                            childColumns = "id", onDelete = CASCADE)})
    class PaymentEntity{
        @ColumnInfo(name = "id")
        private int id;

        @PrimaryKey
        @ColumnInfo(name = "card")
        private long cardNumber;

        @ColumnInfo(name = "pin")
        private String pin;

        @ColumnInfo(name = "amount")
        private float amount;
        @ColumnInfo(name = "salt")
        private String salt;

        PaymentEntity(int id, long cardNumber, String pin, float amount, String salt) {
            this.id = id;
            this.cardNumber = cardNumber;
            this.pin = pin;
            this.amount = amount;
            this.salt = salt;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }
    }
}
