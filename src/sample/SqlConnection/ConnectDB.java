package sample.SqlConnection;

import javafx.collections.ObservableList;
import sample.Entities.Products;

import java.sql.*;

public class ConnectDB {

    public static void connect() {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://206.189.102.66:3306/db";
            con = DriverManager.getConnection(url, "WT", "sk7SszGA2uSL3ZkM");

            Statement stmt = con.createStatement();
            stmt.executeUpdate("TRUNCATE TABLE Products");
            stmt.executeUpdate("TRUNCATE TABLE Dishes");
            stmt.executeUpdate("TRUNCATE TABLE OrderDish");
            stmt.executeUpdate("TRUNCATE TABLE Orders");
            stmt.executeUpdate("TRUNCATE TABLE Recipe");
            stmt.executeUpdate("TRUNCATE TABLE Staff");

            LoadIntoDB libd = new LoadIntoDB();
            libd.loadProductsIntoDB(con);
            libd.loadDishesIntoDB(con);
            libd.loadOrderDishesIntoDB(con);
            libd.loadOrdersIntoDB(con);
            libd.loadRecipeIntoDB(con);
            libd.loadStaffIntoDB(con);

            stmt.close();
            con.close();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.out.println("Can't connect");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
