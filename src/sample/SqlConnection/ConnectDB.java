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

            LoadIntoDB libd = new LoadIntoDB();
            libd.loadProductsIntoDB(con);

           // stmt.executeUpdate();

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
