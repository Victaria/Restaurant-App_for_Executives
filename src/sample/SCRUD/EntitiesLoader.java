package sample.SCRUD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entities.*;

import java.io.*;

public class EntitiesLoader {
    private Products product;
    private Dishes dish;
    private DishesCategory dishesCategory;
    private Order order;
    private Recipe recipe;
    private Staff staff;

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();
    private ObservableList<DishesCategory> dishesCategoryList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();

    public ObservableList<Products> loadProductFile(){
        try{
            File file = new File(path + "Products.txt");

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


    public ObservableList<DishesCategory> loadDishesCategoryFile(){

        return dishesCategoryList;
    }


    public ObservableList<Order> loadOrderFile(){

        return orderList;
    }


    public ObservableList<Recipe> loadRecipeFile(){

        return recipeList;
    }


    public ObservableList<Staff> loadStaffFile(){

        return staffList;
    }

    public void writeProductsFile(ObservableList<Products> product){
        try(FileWriter writer = new FileWriter(path + "TestFile.txt", false)) {
            for (Products products : product){
                writer.write(products.getId() + ";" + products.getName() + ";" + products.getPrice() + ";" + products.getAmount() + "\n");
            }
          //  writer.write(String.valueOf(product.toString().split(";")));
           // writer.write(product);
           // writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
