package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
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

    public Button deleteBtn;
    public Button addBtn;
    private int idEdit;
    public  EntitiesLoader loader = new EntitiesLoader();

    private int flag = 0;
    /*
    * 1 - products
    * 2 - dishes
    * 3 - category
    * 4 - orders
    * 5 - receipts
    * 6 - staff
    */

    private int eAFlag;
    /*
    * 1 - add
    * 2 - edit
    */
    Products product;
    int num;

    @FXML
    public TextField nameField;

    @FXML
    public TextField categoryField;

    @FXML
    public TextField priceField;

    @FXML
    public TextField amountField;

    @FXML
    public TextField weightField;

    @FXML
    public TextField dateField;

    @FXML
    public TextField markupField;

    @FXML
    public TextField sumField;

    @FXML
    public Button productsBtn;

    @FXML
    public Button dishesBtn;

    @FXML
    public Button categoriesBtn;

    @FXML
    public Button ordersBtn;

    @FXML
    public Button receiptsBtn;

    @FXML
    public Button staffBtn;

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

    @FXML
    private TableColumn sumCol;

    @FXML
    private TableColumn markupCol;

    public void initialize(){

        EntityEditor editor = new EntityEditor();
        editor.fieldsDisabled(nameField, categoryField, priceField, amountField, weightField, dateField, sumField, markupField);

       // table.setEditable(true);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        sumCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        markupCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

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
        flag = 1;
    }

    public void dishesShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<Dishes, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Dishes, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("price"));
        markupCol.setCellValueFactory(new PropertyValueFactory<Dishes,Double>("markup"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("weight"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("sum"));

        table.setItems(dishesList);
        flag = 2;
    }

    public void categoriesShow(MouseEvent event) {

        flag = 3;
    }

    public void ordersShow(MouseEvent event) {

        flag = 4;
    }

    public void receiptsShow(MouseEvent event) {

        flag = 5;
    }

    public void staffShow(MouseEvent event) {

        flag = 6;
    }

    public void deleteProperty(MouseEvent event) {
       // Products selectedProduct = (Products) table.getItems().get();
      //  productsList.remove(selectedProduct);
        Products.decrCounter();
    }

    public void addProperty(MouseEvent event) {
        nameField.setDisable(false);

        switch (flag) {
            case 1:
                priceField.setDisable(false);
                amountField.setDisable(false);
                break;
            case 2:
                priceField.setDisable(false);
                markupField.setDisable(false);
                sumField.setDisable(false);
                weightField.setDisable(false);
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;

        }
        Products.incrCounter();

    }

    public void textChange(MouseEvent event) {
        EntityEditor editor = new EntityEditor();
        num = table.getSelectionModel().getSelectedIndex();
        System.out.println(num);
        if (num >= 0){
            eAFlag = 2;
        switch (flag){
            case 1:
                priceField.setDisable(false);
                amountField.setDisable(false);
                nameField.setDisable(false);

                product = (Products) table.getItems().get(num);
                idEdit = ((Products) table.getItems().get(num)).getId();
                nameField.setText(((Products)table.getItems().get(num)).getName());
                priceField.setText(Double.toString(((Products) table.getItems().get(num)).getPrice()));
                amountField.setText(Integer.toString(((Products) table.getItems().get(num)).getAmount()));
                //editor.productListChanged(table, productsList, num);
                break;
            case 2:
                editor.dishesListChanged();
                break;
            case 3:
                editor.categoryListChanged();
                break;
            case 4:
                editor.ordersListChanged();
                break;
            case 5:
                editor.receiptsListChanged();
                break;
            case 6:
                editor.staffListChanged();
                break;
            default:
                break;
        }
        }
    }

    public void submitAdding(ActionEvent actionEvent) {

        switch (flag){
            case 1:
                if (eAFlag == 1){
                String name = nameField.getText();
                Integer amount = Integer.valueOf(amountField.getText());
                Double price = Double.valueOf(priceField.getText());
                Integer id = productsList.size() + 2;
                //генерация id
                productsList.size();
                Products pr = new Products(id, name,price, amount);
                productsList.add(pr);
                    //add to file
                } else if (eAFlag == 2){
                    product.setName(nameField.getText());
                    product.setPrice(Double.valueOf(priceField.getText()));
                    product.setAmount(Integer.valueOf(amountField.getText()));
                    productsList.remove(num);
                    productsList.add(product);
                }
                table.setItems(productsList);
                loader.writeProductsFile(productsList);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
    }
}
}
