package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import sample.Entities.Dishes;
import sample.Entities.Products;
import sample.SCRUD.EntitiesLoader;
import sample.SCRUD.EntityEditor;

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

   // private Products product;
  //  private Dishes dish;

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

    public void initialize(){
        EntitiesLoader loader = new EntitiesLoader();
        productsList = loader.loadProductFile();

        EntitiesLoader loader1 = new EntitiesLoader();
        dishesList = loader1.loadDishesFile();
    }

    public void productsShow(MouseEvent event){
        idCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Products, Double>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("amount"));

        table.setItems(productsList);
    }

    public void dishesShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<Dishes, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Dishes, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("price"));
        markupCol.setCellValueFactory(new PropertyValueFactory<Dishes,Double>("markup"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("weight"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("sum"));

        table.setItems(dishesList);
    }

    public void categoriesShow(MouseEvent event) {
    }

    public void ordersShow(MouseEvent event) {
    }

    public void receiptsShow(MouseEvent event) {
    }

    public void staffShow(MouseEvent event) {
    }

  /*  public void propertySelect(MouseEvent event) {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }*/

    public void deleteProperty(MouseEvent event) {
    }

    public void editProperty(MouseEvent event) {
        EntityEditor editor = new EntityEditor();
        editor.startEdit(table.getSelectionModel().getSelectedCells());
    }
}
