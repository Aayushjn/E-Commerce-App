package dbs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.aayush.onlineshopping.Objects;

/**
 * Created by Aayush on 19-Mar-18.
 */

public class DAOs {
    @Dao
    public interface ProductDAO{
        @Query("SELECT * FROM productentity WHERE pId = :pId")
        Objects.Product getProductById(int pId);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertProduct(Objects.Product product);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateProduct(Objects.Product product);

        @Delete
        void deleteProduct(Objects.Product product);
    }

    @Dao
    public interface UserDAO{
        @Query("SELECT * FROM userentity WHERE id = :id")
        Objects.Product getUserById(int id);

        @Query("SELECT 1 FROM userentity WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM userentity WHERE password = :password")
        int isPasswordCorrect(String password);

        @Query("SELECT 1 FROM userentity WHERE id =  cost < amount")

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertUser(Objects.User user);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateUser(Objects.User user);

        @Delete
        void deleteUser(Objects.User user);
    }

    @Dao
    public interface VendorDAO{
        @Query("SELECT * FROM vendorentity WHERE id = :id")
        Objects.Product getVendorById(int id);

        @Query("SELECT 1 FROM vendorentity WHERE id = :id")
        int isRegistered(int id);

        @Query("SELECT 1 FROM vendorentity WHERE password = :password")
        int isPasswordCorrect(String password);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertVendor(Objects.Vendor vendor);

        @Update(onConflict = OnConflictStrategy.REPLACE)
        void updateVendor(Objects.Vendor vendor);

        @Delete
        void deleteVendor(Objects.Vendor vendor);
    }
}
