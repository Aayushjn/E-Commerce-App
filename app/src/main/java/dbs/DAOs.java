package dbs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.AbstractQueue;
import java.util.ArrayList;

/**
 * Created by Aayush on 19-Mar-18.
 */

public class DAOs {
    @Dao
    public interface PaymentDAO{
        @Query("SELECT * FROM cards WHERE id = :id")
        Entities.PaymentEntity getCardDetailsById(int id);

        @Query("SELECT * FROM cards WHERE card = :cardNo")
        Entities.PaymentEntity getCardDetailsByCardNumber(long cardNo);

        @Query("SELECT amount FROM cards WHERE id = :id")
        float getAmountById(int id);

        @Query("UPDATE cards SET amount = :amount WHERE id = :id")
        void updateAmountById(float amount, int id);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        int insertCard(Entities.PaymentEntity card);

        @Update
        void updateCard(Entities.PaymentEntity card);

        @Delete
        void deleteCard(Entities.PaymentEntity card);
    }

    @Dao
    public interface ProductDAO{
        @Query("SELECT * FROM products WHERE pId = :pId")
        Entities.ProductEntity getProductById(int pId);

        @Query("SELECT * FROM products WHERE name = :name AND category = :category")
        Entities.ProductEntity getProductByCategory(String name, String category);

        @Query("SELECT vendor FROM products WHERE pId = :pId")
        int getVendorIdByProductId(int pId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        int insertProduct(Entities.ProductEntity product);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateProduct(Entities.ProductEntity product);

        @Delete
        void deleteProduct(Entities.ProductEntity product);
    }

    @Dao
    public interface UserDAO{
        @Query("SELECT * FROM users WHERE id = :id")
        Entities.UserEntity getUserById(int id);

        @Query("SELECT * FROM users WHERE emailId = :emailId")
        Entities.UserEntity getUserByEmail(String emailId);

        @Query("SELECT cards.* FROM cards INNER JOIN users ON users.id = cards.id WHERE" +
                " users.id = :id")
        Entities.PaymentEntity getPaymentDetailsById(int id);

        @Query("SELECT 1 FROM users WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM users WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT 1 FROM users WHERE emailId = :emailId")
        int isUnique(String emailId);

        @Query("SELECT 1 FROM users, cards, products WHERE users.id = :id AND cards.id = :id AND " +
                "products.cost < cards.amount")
        int checkSufficientFunds(int id);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        int insertUser(Entities.UserEntity user);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateUser(Entities.UserEntity user);

        @Delete
        void deleteUser(Entities.UserEntity user);
    }

    @Dao
    public interface VendorDAO{
        @Query("SELECT * FROM vendors WHERE id = :id")
        Entities.VendorEntity getVendorById(int id);

        @Query("SELECT * FROM cards WHERE vendors.id = :id AND cards.id = :id")
        Entities.PaymentEntity getPaymentDetailsById(int id);

        @Query("SELECT * FROM vendors WHERE emailId = :emailId")
        Entities.VendorEntity getVendorByEmail(String emailId);

        @Query("SELECT 1 FROM vendors WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM vendors WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT product from vendors WHERE id = :id")
        ArrayList<Integer> getProductIdsByVendorId(int id);

        @Query("SELECT 1 FROM vendors WHERE emailId = :emailId")
        int isUnique(String emailId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        int insertVendor(Entities.VendorEntity vendor);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateVendor(Entities.VendorEntity vendor);

        @Delete
        void deleteVendor(Entities.VendorEntity vendor);
    }
}
