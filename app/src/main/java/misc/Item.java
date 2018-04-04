package misc;

import java.io.Serializable;

public class Item implements Serializable{
    private String name;
    private String category;
    public float price;
    private int quantity;
    private int image;

    public Item() {}

    public Item(String name, String category, float price, int quantity, int image) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
