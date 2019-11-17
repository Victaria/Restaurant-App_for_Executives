package sample.SqlConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entities.*;
import sample.SCRUD.EntitiesLoader;
import sample.SCRUD.EntityEditor;

import java.sql.*;
import java.time.LocalDate;

public class LoadIntoDB {
    private ObservableList<Products> productsList = FXCollections.observableArrayList();
    private ObservableList<Dishes> dishesList = FXCollections.observableArrayList();
    private ObservableList<OrderDish> orderDishList = FXCollections.observableArrayList();
    private ObservableList<Order> orderList = FXCollections.observableArrayList();
    private ObservableList<Recipe> recipeList = FXCollections.observableArrayList();
    private ObservableList<Staff> staffList = FXCollections.observableArrayList();

    EntitiesLoader loader = new EntitiesLoader();

    public void loadProductsIntoDB(Connection con) throws SQLException {
        productsList = loader.loadProductXMLFile();
        String query = "INSERT INTO Products (id, name, price, amount) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStmt = null;
        for (Products product : productsList){
            try {
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, product.getId());
                preparedStmt.setString(2, product.getName());
                preparedStmt.setDouble(3, product.getPrice());
                preparedStmt.setInt(4, product.getAmount());
                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            preparedStmt.close();
    }

    public void loadDishesIntoDB(Connection con) throws SQLException {
        dishesList = loader.loadDishesXMLFile();
        String query = "INSERT INTO Dishes (id, name, price, weight, sum) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = null;
        for (Dishes dish : dishesList){
            try {
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, dish.getId());
                preparedStmt.setString(2, dish.getName());
                preparedStmt.setDouble(3, dish.getPrice());
                preparedStmt.setDouble(4, dish.getWeight());
                preparedStmt.setDouble(5, dish.getSum());
                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        preparedStmt.close();
    }

    public void loadOrderDishesIntoDB(Connection con) throws SQLException {
        orderDishList = loader.loadOrderDishesXMLFile();
        String query = "INSERT INTO OrderDish (id, amount, dishName, orderId) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStmt = null;
        for (OrderDish orDish : orderDishList){
            try {
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, orDish.getId());
                preparedStmt.setInt(2, orDish.getAmount());
                preparedStmt.setString(3, orDish.getDishName());
                preparedStmt.setInt(4, orDish.getOrderId());
                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        preparedStmt.close();
    }

    public void loadOrdersIntoDB(Connection con) throws SQLException {
        orderList = loader.loadOrderXMLFile();
        String query = "INSERT INTO Orders (id, tableOrder, sum, dateOrder, staffName) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = null;
        for (Order order : orderList){
            try {
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, order.getId());
                preparedStmt.setInt(2, order.getTable());
                preparedStmt.setDouble(3, order.getSum());
                preparedStmt.setDate(4, Date.valueOf(order.getDate()));
                preparedStmt.setString(5, order.getStaffName());
                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        preparedStmt.close();
    }

    public void loadRecipeIntoDB(Connection con) throws SQLException {
        recipeList = loader.loadRecipeXMLFile();
        String query = "INSERT INTO Recipe (id, DishName, ProductName, amount) VALUES(?, ?, ?, ?)";
        PreparedStatement preparedStmt = null;
        for (Recipe recipe : recipeList){
            try {
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, recipe.getId());
                preparedStmt.setString(2, recipe.getDishName());
                preparedStmt.setString(3, recipe.getProductName());
                preparedStmt.setInt(4, recipe.getAmount());
                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        preparedStmt.close();
    }

    public void loadStaffIntoDB(Connection con) throws SQLException {
        staffList = loader.loadStaffXMLFile();
        String query = "INSERT INTO Staff (id, name) VALUES(?, ?)";
        PreparedStatement preparedStmt = null;
        for (Staff staff : staffList){
            try {
                preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, staff.getId());
                preparedStmt.setString(2, staff.getName());
                preparedStmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        preparedStmt.close();
    }
}
