package dbs;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Aayush on 13-Mar-18.
 */

public class Entities {
    @Entity(foreignKeys = @ForeignKey(entity = VendorEntity.class, parentColumns = "id",
            childColumns = "vendId", onDelete = CASCADE))
    class ProductEntity{
        @PrimaryKey
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
    }

    @Entity
    class PersonEntity {
        @PrimaryKey
        @ColumnInfo(name = "id")
        private int id;

        @ColumnInfo(name = "address")
        private String address;

        @ColumnInfo(name = "emailId")
        private String emailId;

        @ColumnInfo(name = "name")
        private String name;

        @ColumnInfo(name = "password")
        private String password;

        PersonEntity(int id, String address, String emailId, String name, String password) {
            this.id = id;
            this.address = address;
            this.emailId = emailId;
            this.name = name;
            this.password = password;
        }
    }


    @Entity(foreignKeys = @ForeignKey(entity = ProductEntity.class, parentColumns = "pId",
            childColumns = "prodId", onDelete = CASCADE))
    class UserEntity extends PersonEntity {
        UserEntity(int id, String address, String emailId, String name, String password) {
            super(id, address, emailId, name, password);
        }
    }

    @Entity(foreignKeys = @ForeignKey(entity = ProductEntity.class, parentColumns = "pId",
            childColumns = "prodIds", onDelete = CASCADE))
    class VendorEntity extends PersonEntity{
        @ColumnInfo(name = "product")
        private List<Integer> prodIds;

        VendorEntity(int id, String address, String emailId, String name, String password) {
            super(id, address, emailId, name, password);
        }
    }

    @Entity(foreignKeys = @ForeignKey(entity = PersonEntity.class, parentColumns = "id",
            childColumns = "id", onDelete = CASCADE))
    class PaymentEntity{
        @ColumnInfo(name = "id")
        private int id;

        @ColumnInfo(name = "card")
        private long cardNumber;

        @ColumnInfo(name = "pin")
        private String cvv;

        @ColumnInfo(name = "amount")
        private float amount;

        PaymentEntity(int id, long cardNumber, String cvv, float amount) {
            this.id = id;
            this.cardNumber = cardNumber;
            this.cvv = cvv;
            this.amount = amount;
        }
    }
}
