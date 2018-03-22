package dbs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.example.aayush.onlineshopping.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aayush on 19-Mar-18.
 */

public class DAOs {
    @Dao
    public interface ProductDAO{
        @Query("SELECT * FROM productentity WHERE pId = :pId")
        Entities.ProductEntity getProductById(int pId);

        @Query("SELECT vendor FROM productentity WHERE pId = :pId")
        int getVendorIdByProductId(int pId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertProduct(Entities.ProductEntity product);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateProduct(Entities.ProductEntity product);

        @Delete
        void deleteProduct(Entities.ProductEntity product);
    }

    @Dao
    public interface UserDAO{
        @Query("SELECT * FROM userentity WHERE id = :id")
        Entities.UserEntity getUserById(int id);

        @Query("SELECT 1 FROM userentity WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM userentity WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT 1 FROM userentity, paymententity, productentity WHERE userentity.id = :id " +
                "AND paymententity.id = :id AND productentity.cost < paymententity.amount")
        int checkSufficientFunds(int id);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertUser(Entities.UserEntity user);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateUser(Entities.UserEntity user);

        @Delete
        void deleteUser(Entities.UserEntity user);
    }

    @Dao
    public interface VendorDAO{
        @Query("SELECT * FROM vendorentity WHERE id = :id")
        Entities.VendorEntity getVendorById(int id);

        @Query("SELECT 1 FROM vendorentity WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM vendorentity WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT product from vendorentity WHERE id = :id")
        Cursor getProductIdsByVendorId(int id);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertVendor(Entities.VendorEntity vendor);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateVendor(Entities.VendorEntity vendor);

        @Delete
        void deleteVendor(Entities.VendorEntity vendor);
    }

    @Dao
    public interface PaymentDAO{
        @Query("SELECT * FROM paymententity WHERE id = :id")
        Entities.PaymentEntity getCardDetailsById(int id);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertCard(Entities.PaymentEntity card);

        @Update
        void updateCard(Entities.PaymentEntity card);

        @Delete
        void deleteCard(Entities.PaymentEntity card);
    }
}
