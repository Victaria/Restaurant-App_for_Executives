package sample.SCRUD;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entities.Dishes;
import sample.Entities.Products;

import java.io.*;

public class EntitiesLoader {
    private Products product;
    private Dishes dish;

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();

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
}
