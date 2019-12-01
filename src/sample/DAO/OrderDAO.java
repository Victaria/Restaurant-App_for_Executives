package sample.DAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Entities.Dishes;
import sample.Entities.Order;
import sample.Entities.OrderDish;
import sample.SqlConnection.ConnectDB;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAO {
    private static ObservableList<Order> orderList = FXCollections.observableArrayList();
    private static ObservableList<OrderDish> orderDishesList = FXCollections.observableArrayList();

    public static ObservableList<Order> loadOrdersFromDB() throws SQLException {
        int id;
        int table;
        double sum;
        LocalDate date;
        String staffName;
        int userID;

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        con = ConnectDB.connect();
        statement = con.createStatement();
        resultSet = statement.executeQuery("select * from Orders");
        while (resultSet.next()){
            id = Integer.parseInt(resultSet.getString("id"));
            table = Integer.parseInt(resultSet.getString("tableOrder"));
            sum = Double.parseDouble(resultSet.getString("sum"));
            date = LocalDate.parse(String.valueOf(resultSet.getString("dateOrder")));
            if (resultSet.getString("staffName") != null)staffName = resultSet.getString("staffName"); else staffName = "NULL";
            if(resultSet.getString("userID") != null) userID = Integer.parseInt(resultSet.getString("userID"));
            else userID = 0;

            Order order = new Order(id, table, sum, date, staffName);
            order.setUserID(userID);

            orderList.add(order);
        }
        ConnectDB.closeConnection(con);
        return orderList;
    }

    public static ObservableList<OrderDish> loadOrderDishesFromDB() throws SQLException {
        int id;
        int amount;
        String dishName;
        int orderId;

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        con = ConnectDB.connect();
        statement = con.createStatement();
        resultSet = statement.executeQuery("select * from OrderDish");
        while (resultSet.next()){
            id = Integer.parseInt(resultSet.getString("id"));
            amount = Integer.parseInt(resultSet.getString("amount"));
            dishName = resultSet.getString("dishName");
            orderId = Integer.parseInt(resultSet.getString("orderId"));

            OrderDish orderDish = new OrderDish(id, amount, dishName, orderId);

            orderDishesList.add(orderDish);
        }
        ConnectDB.closeConnection(con);
        return orderDishesList;
    }

    public static void loadOrderIntoDB(ObservableList<Order> orderList) throws SQLException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        con = ConnectDB.connect();
        Statement stmt = null;

        stmt = con.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE Orders");

        String query = "INSERT INTO Orders(id, tableOrder , sum, dateOrder, staffName, userID) VALUES (?,?,?,?,?,? )"; //Insert user details into the table 'USERS'
        for (Order order : orderList) {
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, order.getId());
            preparedStatement.setInt(2, order.getTable());
            preparedStatement.setDouble(3, order.getSum());
            preparedStatement.setDate(4, java.sql.Date.valueOf(String.valueOf(order.getDate())));
            preparedStatement.setString(5, order.getStaffName());
            preparedStatement.setInt(6, order.getUserID());

            preparedStatement.executeUpdate();
        }

        ConnectDB.closeConnection(con);
    }

    public static void loadOrderDishesIntoDB(ObservableList<OrderDish> orderDishesList) throws SQLException {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        con = ConnectDB.connect();
        Statement stmt = null;

        stmt = con.createStatement();
        stmt.executeUpdate("TRUNCATE TABLE OrderDish");

        String query = "INSERT INTO OrderDish(id, amount, dishName, orderId) VALUES (?,?,?,?)"; //Insert user details into the table 'USERS'
        for (OrderDish orderDish : orderDishesList) {
            preparedStatement = con.prepareStatement(query); //Making use of prepared statements here to insert bunch of data
            preparedStatement.setInt(1, orderDish.getId());
            preparedStatement.setInt(2, orderDish.getAmount());
            preparedStatement.setString(3, orderDish.getDishName());
            preparedStatement.setInt(4, orderDish.getOrderId());

            preparedStatement.executeUpdate();
        }

        ConnectDB.closeConnection(con);
    }


}
