package dbs;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Aayush on 22-Mar-18.
 */

public class Converters {
    @TypeConverter
    public static ArrayList<Integer> fromString(String value){
        Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Integer> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
