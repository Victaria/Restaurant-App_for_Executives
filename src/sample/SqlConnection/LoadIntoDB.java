package sample.SqlConnection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entities.*;
import sample.SCRUD.EntitiesLoader;
import sample.SCRUD.EntityEditor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
}
