package misc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<Item> itemList;
    private float totalCost;

    public Cart(){
        itemList = new ArrayList<>();
        totalCost = 0;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public void addItem(Item item){
        itemList.add(item);
        totalCost += item.price;
    }

    public void removeItem(String name, String category){
        if(!itemList.isEmpty()){
            for(int i = 0;i < itemList.size();i++){
                if(category.equals(itemList.get(i).getCategory()) &&
                        name.equals(itemList.get(i).getName())){
                    totalCost -= Float.parseFloat(itemList.get(i).getPrice());
                    itemList.remove(i);
                }
            }
        }
    }

    public Item getItem(String name, String category) {
        Item p = null;
        if (!itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                if (category.equals(itemList.get(i).getCategory()) &&
                        name.equals(itemList.get(i).getName())) {
                    p = itemList.get(i);
                }
            }
        }
        return p;
    }
}
