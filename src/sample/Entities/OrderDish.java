package sample.Entities;

import java.util.Comparator;

public class OrderDish implements Comparator {
    private int id;
    private int amount;
    private String dishName;
    private int orderId;

    private static int lastId = 0;

    public OrderDish(int id, int amount, String dishId, int orderId) {
        this.id = id;
        this.amount = amount;
        this.dishName = dishId;
        this.orderId = orderId;

        setLastId(id);
    }

    public OrderDish(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishId) {
        this.dishName = dishId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
