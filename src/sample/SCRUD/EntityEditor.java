package sample.SCRUD;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Entities.Products;

import java.lang.reflect.Array;

public class EntityEditor {

    public void ordersListChanged(){

    }

    public void receiptsListChanged(){

    }

    public void staffListChanged(){

    }

    public void fieldsDisabled(TextField nameField, TextField categoryField, TextField priceField, TextField amountField, TextField weightField, TextField dateField, TextField sumField){
        nameField.setDisable(true);
        categoryField.setDisable(true);
        priceField.setDisable(true);
        amountField.setDisable(true);
        weightField.setDisable(true);
        dateField.setDisable(true);
        sumField.setDisable(true);
    }

    public void fieldsClear(TextField nameField, TextField categoryField, TextField priceField, TextField amountField, TextField weightField, TextField dateField, TextField sumField){
        nameField.clear();
        categoryField.clear();
        priceField.clear();
        amountField.clear();
        weightField.clear();
        dateField.clear();
        sumField.clear();
    }
}
