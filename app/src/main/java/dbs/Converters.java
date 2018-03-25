package dbs;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aayush on 22-Mar-18.
 */

class ProductIds{
    private ArrayList<Integer> pIds;

    public ProductIds(ArrayList<Integer> pIds){
        this.pIds = pIds;
    }

    public ArrayList<Integer> getpIds(){
        return pIds;
    }

    public void setpIds(ArrayList<Integer> pIds){
        this.pIds = pIds;
    }
}

public class Converters {
    @TypeConverter
    public ProductIds toIdsFromValue(String value){
        List<String> temp = Arrays.asList(value.split("\\s*,\\s*"));
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for(int i = 0;i < temp.size();i++){
            ids.add(Integer.parseInt(temp.get(i)));
        }
        return new ProductIds(ids);
    }

    @TypeConverter
    public String toValueFromIds(ProductIds productIds){
        String value = "";
        for(int id : productIds.getpIds()){
            value += id + ",";
        }
        return value;
    }
}
