package dbs;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Aayush on 19-Mar-18.
 */

public class Databases {
    @Database(entities = {Entities.UserEntity.class}, version = 1)
    abstract class UserDatabase extends RoomDatabase{
        public abstract DAOs.UserDAO userDAO();
    }
}
