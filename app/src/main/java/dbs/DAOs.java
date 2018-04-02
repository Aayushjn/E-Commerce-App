package dbs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

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
        void insertCard(Entities.PaymentEntity card);

        @Update
        void updateCard(Entities.PaymentEntity card);

        @Delete
        void deleteCard(Entities.PaymentEntity card);
    }

    @Dao
    public interface ProductDAO{
        @Query("SELECT * FROM products WHERE pId = :pId")
        Entities.ProductEntity getProductById(int pId);

        @Query("SELECT * FROM products WHERE category = :category")
        Cursor getProductsByCategory(String category);

        @Query("SELECT * FROM products WHERE vendor = :vendId")
        Cursor getVendorProducts(int vendId);

        @Query("SELECT * FROM products WHERE category = :category AND name = :name")
        Entities.ProductEntity getProductByCategoryAndName(String category, String name);

        @Query("SELECT vendor FROM products WHERE pId = :pId")
        int getVendorIdByProductId(int pId);

        @Query("SELECT DISTINCT category FROM products WHERE vendor = :vendId")
        Cursor getDistinctCategories(int vendId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        long insertProduct(Entities.ProductEntity product);

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

        @Query("SELECT 1 FROM users WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM users WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT 1 FROM users WHERE emailId = :emailId")
        int isUnique(String emailId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        long insertUser(Entities.UserEntity user);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateUser(Entities.UserEntity user);

        @Delete
        void deleteUser(Entities.UserEntity user);
    }

    @Dao
    public interface VendorDAO{
        @Query("SELECT * FROM vendors WHERE id = :id")
        Entities.VendorEntity getVendorById(int id);

        @Query("SELECT * FROM vendors WHERE emailId = :emailId")
        Entities.VendorEntity getVendorByEmail(String emailId);

        @Query("SELECT 1 FROM vendors WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM vendors WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT 1 FROM vendors WHERE emailId = :emailId")
        int isUnique(String emailId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        long insertVendor(Entities.VendorEntity vendor);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateVendor(Entities.VendorEntity vendor);

        @Delete
        void deleteVendor(Entities.VendorEntity vendor);
    }
}
