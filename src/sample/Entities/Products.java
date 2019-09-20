package sample.Entities;

public class Products {
    private int id;
    private String Name;
    private double price;
    private int amount;
    private Integer count = 0;

    public Products(int id, String name, double price, int amount) {
        this.id = id;
        Name = name;
        this.price = price;
        this.amount = amount;
        count++;
    }

    public Products(Object o) {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void printProduct(){
        System.out.println(this.getId() +" "+ this.getName() +" "+ this.getPrice() +" "+ this.getAmount());
    }

    public Integer getAmountOfProducts(){
        System.out.println(getId());
        return count;
    }
}
