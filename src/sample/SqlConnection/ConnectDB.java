package sample.SqlConnection;

import java.sql.*;

public class ConnectDB {

    public static void connect() {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://206.189.102.66:3306/db";
            con = DriverManager.getConnection(url, "WT", "sk7SszGA2uSL3ZkM");
           
            String query = "INSERT INTO Products (name, price, amount) VALUES('chili', 225, 5)";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);

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
