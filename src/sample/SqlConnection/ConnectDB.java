package sample.SqlConnection;

import java.sql.*;

public class ConnectDB {

    public static void connect() {
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://206.189.102.66:3306/db";
            con = DriverManager.getConnection(url, "WT", "sk7SszGA2uSL3ZkM");
           // String query = "SELECT id, name FROM Products";
            String query = "INSERT INTO Products (name, price, amount) VALUES('chili', 225, 5)";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            
           // ResultSet rs = stmt.executeQuery(query);
           /* while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of records in the table : " + count);
                System.out.println(rs.getString(2));
            }*/

            //rs.close();
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
