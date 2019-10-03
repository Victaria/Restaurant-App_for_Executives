package sample.Entities;

import java.util.Comparator;

public class Dishes implements Comparator {
    private int id;
    private String Name;
    private double price;
    private double weight;
    private double sum;

    private static int lastId = 0;

    public Dishes(int id, String name, double price, double weight, double sum) {
        this.id = id;
        Name = name;
        this.price = price;
        this.weight = weight;
        this.sum = sum;

        setLastId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public static int getLastId(){
        return lastId;
    }

    public static void setLastId(int id){
        if (lastId < id)
            lastId = id;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (o1.equals(o2)) return 0;
        else return -1;
    }
}
