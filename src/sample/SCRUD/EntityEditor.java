package sample.SCRUD;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Entities.Products;

import java.lang.reflect.Array;

public class EntityEditor {
    Products product;

    public void productListChanged(TableView table, ObservableList<Products> productsList){
        EntitiesLoader loader = new EntitiesLoader();
       for (int row = 0; row < table.getItems().size(); row++){
           // while (row < table.getSelectionModel().getSelectedItems().size()){
           // product = (Products) table.getItems().get(row);
           product = new Products(table.getItems().get(row));
           table.getSelectionModel().select(row);
           System.out.println(table.getSelectionModel().getSelectedIndex());

            String string = String.join(";", product.toString());
           // loader.writeProductsFile(string);
          //  productsList.add((Products) table.getProperties().get(row));
        }
        System.out.println();
        //System.out.println();
    }

    public void dishesListChanged(){

    }

    public void categoryListChanged(){

    }

    public void ordersListChanged(){

    }

    public void receiptsListChanged(){

    }

    public void staffListChanged(){

    }

    public void fieldsDisabled(TextField nameField, TextField categoryField, TextField priceField, TextField amountField, TextField weightField, TextField dateField, TextField markupField, TextField sumField){
        nameField.setDisable(true);
        categoryField.setDisable(true);
        priceField.setDisable(true);
        amountField.setDisable(true);
        weightField.setDisable(true);
        dateField.setDisable(true);
        sumField.setDisable(true);
        markupField.setDisable(true);
    }
}
