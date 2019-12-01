package sample.SqlConnection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sample.SCRUD.EntityEditor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LoadAllIntoDB {
    private static Logger log = LogManager.getLogger();
    private static EntityEditor editor = new EntityEditor();

    public static void loadAll(){
        Connection con = ConnectDB.connect();

        Statement stmt = null;
        try {
            stmt = con.createStatement();

        stmt.executeUpdate("TRUNCATE TABLE Products");
        stmt.executeUpdate("TRUNCATE TABLE Dishes");
       // stmt.executeUpdate("TRUNCATE TABLE OrderDish");
       // stmt.executeUpdate("TRUNCATE TABLE Orders");
        stmt.executeUpdate("TRUNCATE TABLE Recipe");
        stmt.executeUpdate("TRUNCATE TABLE Staff");

        LoadIntoDB libd = new LoadIntoDB();
        libd.loadProductsIntoDB(con);
        libd.loadDishesIntoDB(con);
      //  libd.loadOrderDishesIntoDB(con);
      //  libd.loadOrdersIntoDB(con);
        libd.loadRecipeIntoDB(con);
        libd.loadStaffIntoDB(con);

        ConnectDB.closeConnection(con);
        } catch (SQLException e) {
            log.log(Level.ERROR,"Can't load files into DB", e);
            editor.printAlert("Can't connect to DB");
        }
    }
}
