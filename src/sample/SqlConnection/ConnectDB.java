package sample.SqlConnection;

import javafx.collections.ObservableList;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.Entities.Products;
import sample.SCRUD.EntityEditor;

import java.sql.*;

public class ConnectDB {
    private static Logger log = LogManager.getLogger();
    private static EntityEditor editor = new EntityEditor();

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

            log.log(Level.INFO,"Connection to DB is successfully");

            stmt.close();
            con.close();
            log.log(Level.INFO,"Connection closed successfully");
        } catch (SQLException sqlEx) {
            log.log(Level.ERROR,"Can't Connect to DB", sqlEx);
            editor.printAlert("Can't connect to DB");
        } catch (ClassNotFoundException e) {
            log.log(Level.ERROR,"Can't find Class", e);
            editor.printAlert("Can't connect to DB");
        }
    }
}
