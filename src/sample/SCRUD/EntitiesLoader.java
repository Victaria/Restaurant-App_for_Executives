package sample.SCRUD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entities.*;

import java.io.*;

public class EntitiesLoader {
    private Products product;
    private Dishes dish;
    private OrderDish orderDish;
    private Order order;
    private Recipe recipe;
    private Staff staff;

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();
    private ObservableList<OrderDish> orderDishList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();

    public ObservableList<Products> loadProductFile(){
        try{
            File file = new File(path + "TestFile.txt");

            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                product = new Products(Integer.parseInt(cols[0]),cols[1],Double.parseDouble(cols[2]),Integer.parseInt(cols[3]));
                productsList.add(product);
                product.printProduct();
                line = reader.readLine();
            }
        } catch (
            FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
        e.printStackTrace();
    }
        return productsList;
    }

    public ObservableList<Dishes> loadDishesFile(){

        try {
            File file = new File(path + "Dishes.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                dish = new Dishes(Integer.parseInt(cols[0]),cols[1],Double.parseDouble(cols[2]),Double.parseDouble(cols[3]),Double.parseDouble(cols[4]),Double.parseDouble(cols[5]));
                dishesList.add(dish);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return dishesList;
    }


    public ObservableList<OrderDish> loadDOrderDishFile() {
        try {
            File file = new File(path + "OrderDish.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                orderDish = new OrderDish();
                orderDishList.add(orderDish);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return orderDishList;
    }

        public ObservableList<Order> loadOrderFile(){
            try {
                File file = new File(path + "Orders.txt");
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();

                while (line != null) {
                    String[] cols = line.split(";");
                    order = new Order(Integer.parseInt(cols[0]), cols[1], Double.parseDouble(cols[2]), Integer.parseInt(cols[3]));
                    orderList.add(order);

                    line = reader.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return orderList;
    }


    public ObservableList<Recipe> loadRecipeFile(){
        try {
            File file = new File(path + "Recipe.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                recipe = new Recipe();
                recipeList.add(recipe);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipeList;
    }


    public ObservableList<Staff> loadStaffFile(){
        try {
            File file = new File(path + "Staff.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();

            while (line != null) {
                String[] cols = line.split(";");
                staff = new Staff();
                staffList.add(staff);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return staffList;
    }

    public void writeProductsFile(ObservableList<Products> product){
        try(FileWriter writer = new FileWriter(path + "TestFile.txt", false)) {
            for (Products products : product){
                writer.write(products.getId() + ";" + products.getName() + ";" + products.getPrice() + ";" + products.getAmount() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeDishesFile(ObservableList<Dishes> dish){
        try(FileWriter writer = new FileWriter(path + "Dishes.txt", false)) {
            for (Dishes dishes : dish){
                writer.write(dishes.getId() + ";" + dishes.getName() + ";" + dishes.getPrice() + ";" + dishes.getWeight() + ";" + dishes.getMarkup() + ";" + dishes.getSum() +"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeOrderFile(ObservableList<Order> order){

    }

    public void writeOrderDishFile(ObservableList<OrderDish> orderDish){

    }

    public void writeRecipeFile(ObservableList<Recipe> recipe){

    }

    public void writeStaffFile(ObservableList<Staff> staff){

    }

}
