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
import javafx.util.StringConverter;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import sample.Entities.*;
import sample.SCRUD.EntitiesLoader;
import sample.SCRUD.EntityEditor;

import java.io.IOException;
import java.util.Date;

public class Controller {

    public Button deleteBtn;
    public Button addBtn;
    public Button submitBtn;
    public TextField searchField;
    public TableColumn dishNameCol;
    public TableColumn orderIdCol;
    public TableColumn staffNameCol;

    private int idEdit;
    public EntitiesLoader loader = new EntitiesLoader();

    private int flag = 0;
    /*
     * 1 - products
     * 2 - dishes
     * 3 - orderDish
     * 4 - orders
     * 5 - receipts
     * 6 - staff
     */

    private int eAFlag;
    /*
     * 1 - add
     * 2 - edit
     */
    private Products product;
    private Dishes dish;
    private Recipe recipe;
    private Order order;
    private Staff staff;
    private OrderDish orderDish;

    private int num;

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
    private ObservableList<Dishes> dishes2List = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Order> order2List = FXCollections.observableArrayList();
    private ObservableList<OrderDish> orderDishList = FXCollections.observableArrayList();
    private ObservableList<OrderDish> orderDish2List = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipe2List = FXCollections.observableArrayList();
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();

    @FXML
    private TableView table;

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn tableCol;

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
    private TableColumn productNameCol;

    private EntityEditor editor = new EntityEditor();
    private double weight;
    private double sum;

    public void initialize() {

        editor.fieldsDisabled(nameField, categoryField, priceField, amountField, weightField, dateField, sumField, markupField);
        submitBtn.setDisable(true);
        // table.setEditable(true);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sumCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        productNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dishNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        orderIdCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        staffNameCol.setCellFactory(TextFieldTableCell.forTableColumn());


        EntitiesLoader loader = new EntitiesLoader();
        productsList = loader.loadProductFile();
        dishesList = loader.loadDishesFile();
        orderList = loader.loadOrderFile();
        orderDishList = loader.loadOrderDishFile();
        recipeList = loader.loadRecipeFile();
        staffList = loader.loadStaffFile();
    }

    public void productsShow(MouseEvent event) {
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
        weightCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("weight"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("sum"));
        dishes2List.clear();

        for (Dishes dish : dishesList){
            dish.setPrice(0);
            for (Recipe recipe : recipeList){
                int i = recipe.compare(recipe.getDishName(), String.valueOf(dish.getId()));
                int k = recipe.compare(recipe.getDishName(), dish.getName());
                if ((i == 0) || (k == 0)){
                    for (Products product : productsList){
                        int j = product.compare(recipe.getProductName(), String.valueOf(product.getId()));
                        int f = product.compare(recipe.getProductName(), product.getName());
                        if ((j == 0) || (f == 0)){
                           sum = product.getPrice() * recipe.getAmount();
                           dish.setPrice(dish.getPrice() + sum);
                        }
                    }
                }
            }
            dish.setSum(dish.getPrice()*1.7);
            dishes2List.add(dish);
        }

        table.setItems(dishes2List);
        flag = 2;
    }

    public void orderDishesShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<OrderDish, Integer>("id"));
        amountCol.setCellValueFactory(new PropertyValueFactory<OrderDish, Integer>("amount"));
        dishNameCol.setCellValueFactory(new PropertyValueFactory<OrderDish, String>("dishName"));
        orderIdCol.setCellValueFactory(new PropertyValueFactory<OrderDish, Integer>("orderId"));
        orderDish2List.clear();

        for (OrderDish orderDish : orderDishList){
            for (Dishes dish : dishesList){
                int i = dish.compare(String.valueOf(dish.getId()), orderDish.getDishName());
                if (i == 0) orderDish.setDishName(dish.getName());
            }
            orderDish2List.add(orderDish);
        }

        table.setItems(orderDish2List);
        flag = 3;
    }

    public void ordersShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        tableCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("table"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("sum"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Order, String>("date")); //into Date
        staffNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("staffName"));
        order2List.clear();

        for (Order order : orderList){
            order.setSum(0);
            for (Staff staff : staffList){
                int i = order.compare(String.valueOf(order.getStaffName()), String.valueOf(staff.getId()));
                if (i == 0) order.setStaffName(staff.getName());
            }
            for (OrderDish orderDish : orderDishList){
                int i = orderDish.compare(order.getId(), orderDish.getOrderId());
                if ((i == 0)){
                    for (Dishes dish : dishesList) {
                        int j = dish.compare(orderDish.getDishName(), dish.getName());
                        if (j == 0) {
                            sum = dish.getSum() * orderDish.getAmount();
                            order.setSum(order.getSum() + sum);
                        }
                    }
                }
            }
            order2List.add(order);
        }

        table.setItems(order2List);
        flag = 4;
    }

    public void receiptsShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("id"));
        dishNameCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("dishName"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<Recipe, String>("productName"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Recipe, Integer>("amount"));
        recipe2List.clear();

        for (Recipe recipe : recipeList){
            for (Dishes dish : dishesList){
                int i = dish.compare(String.valueOf(dish.getId()), recipe.getDishName());
                if (i == 0) recipe.setDishName(dish.getName());
            }
            for (Products product : productsList) {
                int i = product.compare(String.valueOf(product.getId()), recipe.getProductName());
                if (i == 0) recipe.setProductName(product.getName());
            }
            recipe2List.add(recipe);
        }

        table.setItems(recipe2List);
        flag = 5;
    }

    public void staffShow(MouseEvent event) {
        idCol.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("name"));

        table.setItems(staffList);
        flag = 6;
    }

    public void deleteProperty(MouseEvent event) throws Throwable {
        num = table.getSelectionModel().getSelectedIndex();
        if (num >= 0) {
            switch (flag) {
                case 1:
                    product = (Products) table.getItems().get(num);
                    productsList.remove(num);
                   // Products.destroy(product);
                    loader.writeProductsFile(productsList);
                    break;
                case 2:
                    dish = (Dishes) table.getItems().get(num);
                    dishesList.remove(num);
                    loader.writeDishesFile(dishesList);
                    break;
                case 3:
                    orderDish = (OrderDish) table.getItems().get(num);
                    orderDishList.remove(num);
                    loader.writeOrderDishFile(orderDishList);
                    break;
                case 4:
                    order = (Order) table.getItems().get(num);
                    orderList.remove(num);
                    loader.writeOrderFile(orderList);
                    break;
                case 5:
                    recipe = (Recipe) table.getItems().get(num);
                    recipeList.remove(num);
                    loader.writeRecipeFile(recipeList);
                    break;
                case 6:
                    staff = (Staff) table.getItems().get(num);
                    staffList.remove(num);
                    loader.writeStaffFile(staffList);
                    break;
                default:
                    break;
            }
        }
    }

    public void addProperty(MouseEvent event) throws IOException {
        eAFlag = 1;
        submitBtn.setDisable(false);

        switch (flag) {
            case 1:
                nameField.setDisable(false);
                priceField.setDisable(false);
                amountField.setDisable(false);
                break;
            case 2:
                nameField.setDisable(false);
                priceField.setDisable(false);
                markupField.setDisable(false);
                sumField.setDisable(false);
                weightField.setDisable(false);
                ModalWindow. newWindow("add Dish");
                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                nameField.setDisable(false);

                break;

        }

    }

    public void textChange(MouseEvent event) throws IOException {
        editor = new EntityEditor();
        num = table.getSelectionModel().getSelectedIndex();
        System.out.println(num);
        if (num >= 0) {
            eAFlag = 2;
            submitBtn.setDisable(false);
            switch (flag) {
                case 1:
                    priceField.setDisable(false);
                    amountField.setDisable(false);
                    nameField.setDisable(false);

                    product = (Products) table.getItems().get(num);
                    idEdit = ((Products) table.getItems().get(num)).getId();
                    nameField.setText(((Products) table.getItems().get(num)).getName());
                    priceField.setText(Double.toString(((Products) table.getItems().get(num)).getPrice()));
                    amountField.setText(Integer.toString(((Products) table.getItems().get(num)).getAmount()));
                    break;
                case 2:
                    priceField.setDisable(false);
                    nameField.setDisable(false);
                    weightField.setDisable(false);
                    sumField.setDisable(false);

                    dish = (Dishes) table.getItems().get(num);
                    idEdit = (((Dishes) table.getItems().get(num)).getId());
                    nameField.setText(((Dishes) table.getItems().get(num)).getName());
                    priceField.setText(Double.toString(((Dishes) table.getItems().get(num)).getPrice()));
                    weightField.setText(Double.toString(((Dishes) table.getItems().get(num)).getWeight()));
                    sumField.setText(Double.toString(((Dishes) table.getItems().get(num)).getSum()));

                    ModalWindow.newWindow("edit dish");
                    break;
                case 3:

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

        switch (flag) {
            case 1:
                if (eAFlag == 1) {
                    String name = nameField.getText();
                    Integer amount = Integer.valueOf(amountField.getText());
                    Double price = Double.valueOf(priceField.getText());
                    Integer id = Products.getLastId() + 1;

                    productsList.size();
                    Products pr = new Products(id, name, price, amount);
                    productsList.add(pr);

                } else if (eAFlag == 2) {
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
                if (eAFlag == 1) {
                    String name = nameField.getText();
                    Double markup = Double.valueOf(markupField.getText());
                    Double price = Double.valueOf(priceField.getText());
                    Integer id = Dishes.getLastId() + 1;
                    Double weight = Double.valueOf(weightField.getText());
                    Double sum = Double.valueOf(sumField.getText());

                    dishesList.size();
                    Dishes dish = new Dishes(id, name, price, weight, sum);
                    dishesList.add(dish);

                } else if (eAFlag == 2) {
                    //markup don't have to be edit
                    dish.setName(nameField.getText());
                    dish.setPrice(Double.valueOf(priceField.getText()));
                    dish.setWeight(Double.valueOf(weightField.getText()));
                    dish.setSum(Double.valueOf(sumField.getText()));

                    dishesList.remove(num);
                    dishesList.add(dish);
                }
                table.setItems(dishesList);
                loader.writeDishesFile(dishesList);
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
        editor.fieldsClear(nameField, categoryField, priceField, amountField, weightField, dateField, sumField, markupField);
        editor.fieldsDisabled(nameField, categoryField, priceField, amountField, weightField, dateField, sumField, markupField);
        submitBtn.setDisable(true);
    }

    public void searchProperties(ActionEvent event) {
        int i;  // 0 - int, double; 2 - string
        int intStr = 0;
        double doublStr = 0;
        int equ;
        int equ1;
        int equ2;
        int equ3;
        int equ4;
        String strFromField = searchField.getText();
        //проблемы с пустым полем
        try {
            intStr = Integer.parseInt(strFromField);
            doublStr = Double.parseDouble(strFromField);
            i = 0;
        } catch (NumberFormatException ex) {
            try {
                doublStr = Double.parseDouble(strFromField);
                i = 1;
            } catch (NumberFormatException ex1) {
                i = 2;
            }
        }

            switch (flag) {
                case 1:
                    ObservableList<Products> res = FXCollections.observableArrayList();
                    for (Products product : productsList) {
                        equ = -1;
                        equ1 = -1;
                        equ3 = -1;
                        switch (i) {
                            case 0:
                                equ = product.compare(product.getId(), intStr);
                                equ1 = product.compare(product.getAmount(), intStr);
                            case 1:
                                equ3 = product.compare(product.getPrice(), doublStr);
                                break;
                            case 2:
                                equ = product.compare(product.getName(), strFromField);
                                break;
                        }
                        if ((equ == 0) || (equ1 == 0) || (equ3 == 0)){
                            res.add(product);
                        }
                    }
                    table.setItems(res);
                    break;
                case 2:
                    ObservableList<Dishes> dRes = FXCollections.observableArrayList();
                    for (Dishes dish : dishesList) {
                        equ = -1;
                        equ1 = -1;
                        equ3 = -1;
                        equ4 = -1;
                        switch (i) {
                            case 0:
                                equ = dish.compare(dish.getId(), intStr);
                            case 1:
                                equ1 = dish.compare(dish.getPrice(), doublStr);
                                equ3 = dish.compare(dish.getSum(), doublStr);
                                equ4 = dish.compare(dish.getWeight(), doublStr);
                                break;
                            case 2:
                                equ = dish.compare(dish.getName(), strFromField);
                                break;
                        }
                        if ((equ == 0) || (equ1 == 0) || (equ3 == 0) || (equ4 == 0)) {
                            dRes.add(dish);
                        }

                        table.setItems(dRes);
                    }
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



