package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import sample.Entities.Dishes;
import sample.Entities.Products;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.StringCharacterIterator;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;


public class Controller {

    @FXML
    private TableColumn sumCol;

    @FXML
    private TableColumn markupCol;

    private Products product;
    private Dishes dish;

    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();

    @FXML
    private TableView table;

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn categoryCol;

    @FXML
    private TableColumn priceCol;

    @FXML
    private TableColumn amountCol;

    @FXML
    private TableColumn weightCol;

    @FXML
    private TableColumn dateCol;

    private String path = "D:\\Disk_D\\VTPart2\\DataBase\\";

    public void productsShow(MouseEvent event){
        idCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Products, Double>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("amount"));

        try {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        table.setItems(productsList);

    }

    public void dishesShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<Dishes, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Dishes, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("price"));
        markupCol.setCellValueFactory(new PropertyValueFactory<Dishes,Double>("markup"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("weight"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("sum"));

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
        table.setItems(dishesList);

    }

    public void categoriesShow(MouseEvent event) {
    }

    public void ordersShow(MouseEvent event) {
    }
}
