package sample.SCRUD;

import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
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

    public void fieldsDisabled(TextField nameField, TextField priceField, TextField amountField, TextField weightField, DatePicker dateChooser){
        nameField.setDisable(true);
        priceField.setDisable(true);
        amountField.setDisable(true);
        weightField.setDisable(true);
        dateChooser.setDisable(true);
    }

    public void fieldsClear(TextField nameField, TextField priceField, TextField amountField, TextField weightField){
        nameField.clear();
        priceField.clear();
        amountField.clear();
        weightField.clear();
    }
}
