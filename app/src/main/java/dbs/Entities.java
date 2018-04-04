package dbs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Aayush on 13-Mar-18.
 */

public class Entities {
    @Entity(tableName = "products")
    public static class ProductEntity {
        private static int counter = 0;
        @PrimaryKey
        @ColumnInfo(name = "prodId")
        private int prodId;
        @ColumnInfo(name = "cost")
        private float cost;
        @ColumnInfo(name = "quantity")
        private int quantity;
        @ColumnInfo(name = "category")
        private String category;
        @ColumnInfo(name = "name")
        private String name;
        @ColumnInfo(name = "vendor")
        private int vendId;

        public ProductEntity(float cost, int quantity, String category, String name, int vendId) {
            setProdId(++counter);
            this.cost = cost;
            this.quantity = quantity;
            this.category = category;
            this.name = name;
            this.vendId = vendId;
        }

        public static int getCounter() {
            return counter;
        }

        public static void setCounter(int counter) {
            ProductEntity.counter = counter;
        }

        public int getProdId(){
            return prodId;
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

        public int getVendId() {
            return vendId;
        }

        public void setVendId(int vendId) {
            this.vendId = vendId;
        }

        public void setProdId(int prodId) {
            this.prodId = prodId;
        }
    }

    @Entity
    static class PersonEntity {
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

        PersonEntity(String address, @NonNull String emailId, String name, String password,
                     String salt) {
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

        @NonNull
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


    @Entity(tableName = "users")
    public static class UserEntity extends PersonEntity {
        static int counter = 0;

        @ColumnInfo(name = "id")
        private int id;

        public UserEntity(){
            super("", "", "", "", "");
            setId(++counter);
        }

        public UserEntity(int id, String address, String emailId, String name, String password,
                          String salt) {
            super(address, emailId, name, password, salt);
            this.id = id;
        }

        public static int getCounter() {
            return counter;
        }

        public static void setCounter(int counter) {
            UserEntity.counter = counter;
        }

        public int getId() {
            return id;
        }

        void setId(int id){
            this.id = id;
        }
    }

    @Entity(tableName = "vendors")
    public static class VendorEntity extends PersonEntity {
        static int counter = 0;

        @ColumnInfo(name = "id")
        private int id;

        public VendorEntity(){
            super("", "", "", "", "");
            this.id = ++counter;
        }

        public VendorEntity(int id, String address, String emailId, String name, String password,
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
    }

    @Entity(tableName = "cards")
    public static class PaymentEntity {
        @ColumnInfo(name = "amount")
        float amount;
        @ColumnInfo(name = "id")
        private int id;
        @PrimaryKey
        @ColumnInfo(name = "card")
        private long cardNumber;
        @ColumnInfo(name = "pin")
        private String pin;
        @ColumnInfo(name = "salt")
        private String salt;

        public PaymentEntity(int id, long cardNumber, String pin, float amount, String salt) {
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
