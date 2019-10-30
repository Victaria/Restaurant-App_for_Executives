package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import sample.Entities.*;
import sample.SCRUD.EntitiesLoader;
import sample.SCRUD.EntityEditor;
import sample.SqlConnection.ConnectDB;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;

public class Controller {

    public Button deleteBtn;
    public Button addBtn;
    public Button submitBtn;
    public TextField searchField;
    public TableColumn dishNameCol;
    public TableColumn orderIdCol;
    public TableColumn staffNameCol;
    public DatePicker dataChooser;
    public ChoiceBox dishNameChooser;
    public ChoiceBox orderIdChooser;
    public ChoiceBox tableChooser;
    public ChoiceBox staffNameChooser;
    public ChoiceBox productNameChooser;

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
    public TextField priceField;

    @FXML
    public TextField amountField;

    @FXML
    public TextField weightField;

    @FXML
    public Button productsBtn;

    @FXML
    public Button dishesBtn;

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

    public void initialize() {
        editor.fieldsDisabled(nameField, priceField, amountField, weightField, dataChooser, dishNameChooser, orderIdChooser, tableChooser, staffNameChooser, productNameChooser);
        submitBtn.setDisable(true);

        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        amountCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        dateCol.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
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

      //  ConnectDB.connect();
    }

    public void productsShow() {
        idCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Products, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Products, Double>("price"));
        amountCol.setCellValueFactory(new PropertyValueFactory<Products, Integer>("amount"));

        table.setItems(productsList);
        flag = 1;
    }

    public void dishesShow() {
        idCol.setCellValueFactory(new PropertyValueFactory<Dishes, Integer>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Dishes, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("price"));
        weightCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("weight"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Dishes, Double>("sum"));
        dishes2List.clear();
        double sum;

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

    public void orderDishesShow() {
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

    public void ordersShow() {
        idCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("id"));
        tableCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("table"));
        sumCol.setCellValueFactory(new PropertyValueFactory<Order, Double>("sum"));
        dateCol.setCellValueFactory(new PropertyValueFactory<Order, LocalDate>("date"));
        staffNameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("staffName"));
        order2List.clear();
        double sum;

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

    public void receiptsShow() {
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

    public void staffShow() {
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
                    loader.writeProductsXMLFile(productsList);
                    table.setItems(productsList);
                    break;
                case 2:
                    dish = (Dishes) table.getItems().get(num);
                    dishesList.remove(num);
                    loader.writeDishesFile(dishesList);
                    table.setItems(dishesList);
                    break;
                case 3:
                    orderDish = (OrderDish) table.getItems().get(num);
                    orderDishList.remove(num);
                    loader.writeOrderDishFile(orderDishList);
                    table.setItems(orderDishList);
                    break;
                case 4:
                    order = (Order) table.getItems().get(num);
                    orderList.remove(num);
                    loader.writeOrderFile(orderList);
                    table.setItems(orderList);
                    break;
                case 5:
                    recipe = (Recipe) table.getItems().get(num);
                    recipeList.remove(num);
                    loader.writeRecipeFile(recipeList);
                    table.setItems(recipeList);
                    break;
                case 6:
                    staff = (Staff) table.getItems().get(num);
                    staffList.remove(num);
                    loader.writeStaffXMLFile(staffList);
                    table.setItems(staffList);
                    break;
                default:
                    break;
            }
        } else {
            editor.printAlert("You didn't choose an option");
        }
    }

    public void addProperty(MouseEvent event) throws IOException {
        eAFlag = 1;
        submitBtn.setDisable(false);
        editor.fieldsClear(nameField, priceField, amountField, weightField, dishNameChooser, orderIdChooser, tableChooser, staffNameChooser, productNameChooser);

        switch (flag) {
            case 1:
                nameField.setDisable(false);
                priceField.setDisable(false);
                amountField.setDisable(false);
                break;
            case 2:
                nameField.setDisable(false);
                weightField.setDisable(false);
                break;
            case 3:
                amountField.setDisable(false);
                dishNameChooser.setDisable(false);
                orderIdChooser.setDisable(false);

                for (Dishes dish : dishesList) dishNameChooser.getItems().add(dish.getName());
                for (Order ord : orderList) orderIdChooser.getItems().add(ord.getId());
                break;
            case 4:
                dataChooser.setDisable(false);
                tableChooser.setDisable(false);
                staffNameChooser.setDisable(false);

                for (int i = 1; i < 21; i++) tableChooser.getItems().add(i);
                for (Staff staff : staffList) staffNameChooser.getItems().add(staff.getName());
                break;
            case 5:
                dishNameChooser.setDisable(false);
                amountField.setDisable(false);
                productNameChooser.setDisable(false);

                for (Dishes dish : dishesList) dishNameChooser.getItems().add(dish.getName());
                for (Products pr : productsList) productNameChooser.getItems().add(pr.getName());
                break;
            case 6:
                nameField.setDisable(false);
                break;

        }

    }

    public void textChange(MouseEvent event) throws IOException {
        editor.fieldsClear(nameField, priceField, amountField, weightField, dishNameChooser, orderIdChooser, tableChooser, staffNameChooser, productNameChooser);
        num = table.getSelectionModel().getSelectedIndex();

        if (num >= 0) {
            eAFlag = 2;
            submitBtn.setDisable(false);
            switch (flag) {
                case 1:
                    nameField.setDisable(false);
                    priceField.setDisable(false);
                    amountField.setDisable(false);

                    product = (Products) table.getItems().get(num);
                    nameField.setText(((Products) table.getItems().get(num)).getName());
                    priceField.setText(Double.toString(((Products) table.getItems().get(num)).getPrice()));
                    amountField.setText(Integer.toString(((Products) table.getItems().get(num)).getAmount()));
                    break;
                case 2:
                    nameField.setDisable(false);
                    weightField.setDisable(false);

                    dish = (Dishes) table.getItems().get(num);
                    nameField.setText(((Dishes) table.getItems().get(num)).getName());
                    weightField.setText(Double.toString(((Dishes) table.getItems().get(num)).getWeight()));
                    break;
                case 3:
                    amountField.setDisable(false);
                    dishNameChooser.setDisable(false);
                    orderIdChooser.setDisable(false);

                    orderDish = (OrderDish) table.getItems().get(num);

                    amountField.setText(Integer.toString(((OrderDish) table.getItems().get(num)).getAmount()));
                    /* adding default-Values
                     * and filling out
                     * Choice Boxes*/
                    for (Dishes dish : dishesList){
                        dishNameChooser.getItems().add(dish.getName());
                        if (dish.compare(((OrderDish) table.getItems().get(num)).getDishName(), dish.getName()) == 0)
                            dishNameChooser.getSelectionModel().select(dish.getName());
                    }
                    int x = 0;
                    for (Order order : orderList){
                        orderIdChooser.getItems().add(order.getId());
                        if (order.compare(((OrderDish) table.getItems().get(num)).getOrderId(), order.getId()) == 0)
                            orderIdChooser.getSelectionModel().select(x);
                        x++;
                    }
                    break;
                case 4:
                    dataChooser.setDisable(false);
                    tableChooser.setDisable(false);
                    staffNameChooser.setDisable(false);

                    order = (Order) table.getItems().get(num);

                    /* adding default-Values
                     * and filling out
                     * Choice Boxes*/
                    for (Staff staff : staffList){
                        staffNameChooser.getItems().add(staff.getName());
                        if (staff.compare(((Order) table.getItems().get(num)).getStaffName(), staff.getName()) == 0)
                            staffNameChooser.getSelectionModel().select(staff.getName());
                    }
                    for (int i = 1; i < 21; i++) tableChooser.getItems().add(i);
                    tableChooser.getSelectionModel().select(((Order) table.getItems().get(num)).getTable() - 1);
                    break;
                case 5:
                    dishNameChooser.setDisable(false);
                    amountField.setDisable(false);
                    productNameChooser.setDisable(false);

                    recipe = (Recipe) table.getItems().get(num);

                    amountField.setText(Integer.toString(((Recipe) table.getItems().get(num)).getAmount()));

                    /* adding default-Values
                     * and filling out
                     * Choice Boxes*/
                    for (Dishes dish : dishesList){
                        dishNameChooser.getItems().add(dish.getName());
                        if (dish.compare(((Recipe) table.getItems().get(num)).getDishName(), dish.getName()) == 0)
                            dishNameChooser.getSelectionModel().select(dish.getName());
                    }
                    for (Products product : productsList){
                        productNameChooser.getItems().add(product.getName());
                        if (product.compare(((Recipe) table.getItems().get(num)).getProductName(), product.getName()) == 0)
                            productNameChooser.getSelectionModel().select(product.getName());
                    }
                    break;
                case 6:
                    nameField.setDisable(false);

                    staff = (Staff) table.getItems().get(num);
                    nameField.setText(((Staff) table.getItems().get(num)).getName());
                    break;
                default:
                    break;
            }
        } else {
            editor.printAlert("You didn't choose an option");
        }
    }

    public void submitAdding(ActionEvent actionEvent) throws Exception {
        switch (flag) {
            case 1:
                try {
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
                    productsShow();
                    loader.writeProductsXMLFile(productsList);
                } catch (Exception e){
                    editor.printAlert("Data are incorrect.");
                }
                break;
            case 2:
                try {
                    if (eAFlag == 1) {
                        String name = nameField.getText();
                        Integer id = Dishes.getLastId() + 1;
                        Double weight = Double.valueOf(weightField.getText());

                        dishesList.size();
                        Dishes dish = new Dishes(id, name, 0, weight, 0);
                        dishesList.add(dish);

                    } else if (eAFlag == 2) {
                        dish.setName(nameField.getText());
                        dish.setPrice(0);
                        dish.setWeight(Double.valueOf(weightField.getText()));
                        dish.setSum(0);

                        dishesList.remove(num);
                        dishesList.add(dish);
                    }
                    dishesShow();
                    loader.writeDishesFile(dishesList);
                } catch (Exception e){
                    editor.printAlert("Data are incorrect.");
                }
                break;
            case 3:
                try {
                    Integer dName = 0;
                    for (Dishes dish : dishesList){
                        if (dish.compare(dishNameChooser.getSelectionModel().getSelectedItem().toString(), dish.getName()) == 0) dName = dish.getId();
                    }
                    if (eAFlag == 1) {
                        Integer amount = Integer.valueOf(amountField.getText());
                        Integer orderId = Integer.valueOf(orderIdChooser.getSelectionModel().getSelectedItem().toString());
                        Integer id = OrderDish.getLastId() + 1;

                        OrderDish orderDish = new OrderDish(id, amount, dName.toString(), orderId);
                        orderDishList.add(orderDish);

                    }else if (eAFlag == 2) {
                        orderDish.setAmount(Integer.valueOf(amountField.getText()));
                        orderDish.setDishName(dName.toString());
                        orderDish.setOrderId(Integer.valueOf(orderIdChooser.getSelectionModel().getSelectedItem().toString()));
                        orderDishList.remove(num);
                        orderDishList.add(orderDish);
                    }
                    orderDishesShow();
                    loader.writeOrderDishFile(orderDishList);
                } catch (Exception e){
                    editor.printAlert("Data are incorrect.");
                }
                break;
            case 4:
                try{
                    Integer stName = 0;
                    for (Staff staff : staffList){
                        if (staff.compare(staffNameChooser.getValue(), staff.getName()) == 0) stName = staff.getId();
                    }

                    if (eAFlag == 1) {
                        Order order = new Order(Order.getLastId() + 1, Integer.valueOf(tableChooser.getSelectionModel().getSelectedItem().toString()), 0, dataChooser.getValue(), stName.toString());
                        orderList.add(order);

                    } else if (eAFlag == 2) {
                        order.setSum(0);
                        order.setStaffName(stName.toString());
                        order.setDate(dataChooser.getValue());
                        order.setTable(Integer.valueOf(tableChooser.getSelectionModel().getSelectedItem().toString()));
                        orderList.remove(num);
                        orderList.add(order);
                    }
                    ordersShow();
                    loader.writeOrderFile(orderList);
                } catch (Exception e){
                    editor.printAlert("Data are incorrect.");
                }
                break;
            case 5:
                try{
                    int prId = 0;
                    int dishId = 0;
                    for (Products product : productsList){
                        if (product.compare(productNameChooser.getSelectionModel().getSelectedItem().toString(), product.getName()) == 0) prId = product.getId();
                    }
                    for (Dishes dish : dishesList){
                        if (dish.compare(dishNameChooser.getSelectionModel().getSelectedItem().toString(), dish.getName()) == 0) dishId = dish.getId();
                    }

                    if (eAFlag == 1) {
                        Recipe recipe = new Recipe(Recipe.getLastId() + 1, String.valueOf(dishId), String.valueOf(prId), Integer.valueOf(amountField.getText()));
                        recipeList.add(recipe);

                    } else if (eAFlag == 2) {
                        recipe.setAmount(Integer.valueOf(amountField.getText()));
                        recipe.setProductName(String.valueOf(prId));
                        recipe.setDishName(String.valueOf(dishId));
                        recipeList.remove(num);
                        recipeList.add(recipe);
                    }
                    receiptsShow();
                    loader.writeRecipeFile(recipeList);
                } catch (Exception e){
                    editor.printAlert("Data are incorrect.");
                }
                break;
            case 6:
                try{
                    System.out.println(eAFlag);
                    if (eAFlag == 1) {
                        String name = nameField.getText();
                        Integer id = Staff.getLastId() + 1;

                        staffList.size();
                        Staff st = new Staff(id, name);
                        staffList.add(st);

                    } else if (eAFlag == 2) {
                        staff.setName(nameField.getText());

                        staffList.remove(num);
                        staffList.add(staff);
                    }
                    staffShow();
                    loader.writeStaffXMLFile(staffList);
                } catch (Exception e){
                    editor.printAlert("Data are incorrect.");
                }
                break;
        }
        editor.fieldsClear(nameField, priceField, amountField, weightField, dishNameChooser, orderIdChooser, tableChooser, staffNameChooser, productNameChooser);
        editor.fieldsDisabled(nameField, priceField, amountField, weightField, dataChooser,  dishNameChooser, orderIdChooser, tableChooser, staffNameChooser, productNameChooser);
        submitBtn.setDisable(true);
    }

    public void searchProperties(ActionEvent event) {
        int i;
        /* *0 - int,
           *1 - double;
           *2 - string */
        int intStr = 0;
        double doublStr = 0;
        int equ;
        int equ1;
        int equ2;
        int equ3;
        String strFromField = searchField.getText();
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
                        equ2 = -1;
                        switch (i) {
                            case 0:
                                equ = dish.compare(dish.getId(), intStr);
                            case 1:
                                equ1 = dish.compare(dish.getPrice(), doublStr);
                                equ3 = dish.compare(dish.getSum(), doublStr);
                                equ2 = dish.compare(dish.getWeight(), doublStr);
                                break;
                            case 2:
                                equ = dish.compare(dish.getName(), strFromField);
                                break;
                        }
                        if ((equ == 0) || (equ1 == 0) || (equ3 == 0) || (equ2 == 0)) {
                            dRes.add(dish);
                        }

                        table.setItems(dRes);
                    }
                    break;
                case 3:
                    ObservableList<OrderDish> oDRes = FXCollections.observableArrayList();
                    for (OrderDish orderDish : orderDishList){
                        equ = -1;
                        equ1 = -1;
                        equ2 = -1;
                        switch (i){
                            case 0:
                                equ = orderDish.compare(orderDish.getId(), intStr);
                                equ1 = orderDish.compare(orderDish.getAmount(), intStr);
                                equ2 = orderDish.compare(orderDish.getOrderId(), intStr);
                                break;
                            case 1:
                            case 2:
                                equ = orderDish.compare(orderDish.getDishName(), strFromField);
                                break;
                        }
                        if ((equ == 0) || (equ1 == 0) || (equ2 == 0)) {
                            oDRes.add(orderDish);
                        }
                        table.setItems(oDRes);
                    }
                    break;
                case 4:
                    ObservableList<Order> oRes = FXCollections.observableArrayList();
                    for (Order order : orderList){
                        equ = -1;
                        equ1 = -1;
                        equ2 = -1;
                        switch (i){
                            case 0:
                                equ = order.compare(order.getId(), intStr);
                                equ1 = order.compare(order.getTable(), intStr);
                            case 1:
                                equ2 = order.compare(order.getSum(), doublStr);
                                break;
                            case 2:
                                equ = order.compare(order.getDate(), strFromField);
                                equ1 = order.compare(order.getStaffName(), strFromField);
                                break;
                        }
                        if ((equ == 0) || (equ1 == 0) || (equ2 == 0)) {
                            oRes.add(order);
                        }
                        table.setItems(oRes);
                    }
                    break;
                case 5:
                    ObservableList<Recipe> rRes = FXCollections.observableArrayList();
                    for (Recipe recipe : recipeList){
                        equ = -1;
                        equ1 = -1;
                        switch (i){
                            case 0:
                                equ = recipe.compare(recipe.getId(), intStr);
                                equ1 = recipe.compare(recipe.getAmount(), intStr);
                                break;
                            case 1:
                            case 2:
                                equ = recipe.compare(recipe.getProductName(), strFromField);
                                equ1 = recipe.compare(recipe.getDishName(), strFromField);
                                break;
                        }
                        if ((equ == 0) || (equ1 == 0)) {
                            rRes.add(recipe);
                        }
                        table.setItems(rRes);
                    }
                    break;
                case 6:
                    ObservableList<Staff> sRes = FXCollections.observableArrayList();
                    for (Staff staff : staffList){
                        equ = -1;
                        switch (i){
                            case 0:
                                equ = staff.compare(staff.getId(), intStr);
                                break;
                            case 1:
                            case 2:
                                equ = staff.compare(staff.getName(), strFromField);
                                break;
                        }
                        if (equ == 0) {
                            sRes.add(staff);
                        }
                        table.setItems(sRes);
                    }
                    break;
                default:
                    break;
            }
        }

    public void loadIntoDB(ActionEvent event) throws IOException, ParserConfigurationException {
        loader.writeProductsXMLFile(productsList);
       // ConnectDB.connect();
    }
}



