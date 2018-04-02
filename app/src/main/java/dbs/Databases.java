package dbs;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by Aayush on 19-Mar-18.
 */

public class Databases {
    @Database(entities = {Entities.UserEntity.class}, version = 1)
    public abstract static class UserDatabase extends RoomDatabase{
        private static UserDatabase INSTANCE;

        public abstract DAOs.UserDAO userDAO();

        public static UserDatabase getUserDatabase(Context context){
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,
                        "user-database").build();
            }
            return INSTANCE;
        }

        public static void destroyInstance(){
            INSTANCE = null;
        }
    }

    @Database(entities = {Entities.VendorEntity.class, Entities.ProductEntity.class}, version = 1)
    @TypeConverters({Converters.class})
    public abstract static class VendorDatabase extends RoomDatabase{
        private static VendorDatabase INSTANCE;

        public abstract DAOs.VendorDAO vendorDAO();

        public static VendorDatabase getVendorDatabase(Context context){
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        VendorDatabase.class, "vendor-database").build();
            }
            return INSTANCE;
        }

        public static void destroyInstance(){
            INSTANCE = null;
        }
    }

    @Database(entities = {Entities.PaymentEntity.class, Entities.VendorEntity.class,
            Entities.UserEntity.class, Entities.ProductEntity.class}, version = 1)
    @TypeConverters({Converters.class})
    public abstract static class PaymentDatabase extends RoomDatabase{
        private static PaymentDatabase INSTANCE;

        public abstract DAOs.PaymentDAO paymentDAO();

        public static PaymentDatabase getPaymentDatabase(Context context){
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PaymentDatabase.class,"payment-database").build();
            }
            return INSTANCE;
        }

        public static void destroyInstance(){
            INSTANCE = null;
        }
    }

    @Database(entities = {Entities.ProductEntity.class, Entities.VendorEntity.class}, version = 1)
    public abstract static class ProductDatabase extends RoomDatabase{
        private static ProductDatabase INSTANCE;

        public abstract DAOs.ProductDAO productDAO();

        public static ProductDatabase getProductDatabase(Context context){
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ProductDatabase.class,"product-database").build();
            }
            return INSTANCE;
        }

        public static void destroyInstance(){
            INSTANCE = null;
        }
    }
}
