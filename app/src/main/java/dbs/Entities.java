package dbs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Aayush on 13-Mar-18.
 */

public class Entities {
    @Entity
    class ProductEntity{
        @PrimaryKey(autoGenerate = true)
        private int pId;

        @ColumnInfo(name = "cost")
        private float cost;

        @ColumnInfo(name = "quantity")
        private int quantity;

        @ColumnInfo(name = "category")
        private String category;

        @ColumnInfo(name = "color")
        private String color;

        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "size")
        private String size;

        @ColumnInfo(name = "vendor")
        private int vendId;
    }

    @Entity
    class PersonEntity {
        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "address")
        private String address;

        @ColumnInfo(name = "emailId")
        private String emailId;

        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "password")
        private String password;

        @ColumnInfo(name = "username")
        private String username;
    }


    @Entity(foreignKeys = @ForeignKey(entity = ProductEntity.class, parentColumns = "pId",
            childColumns = "prodId", onDelete = CASCADE))
    class UserEntity extends PersonEntity {
        private int prodId;
    }

    @Entity
    class VendorEntity{

    }
}
