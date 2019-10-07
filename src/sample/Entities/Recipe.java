package sample.Entities;

import java.util.Comparator;

public class Recipe implements Comparator {
    private int id;
    private String dishName;
    private String productName;
    private int amount;

    private static int lastId = 0;

    public Recipe(int id, String dishId, String productId, int amount) {
        this.id = id;
        this.dishName = dishId;
        this.productName = productId;
        this.amount = amount;

        setLastId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishId) {
        this.dishName = dishId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productId) {
        this.productName = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int id) {
        if (lastId < id)
            lastId = id;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1.equals(o2)) return 0;
        else return -1;
    }
}
