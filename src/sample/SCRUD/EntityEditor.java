package sample.SCRUD;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import sample.Entities.Products;

import java.awt.*;
import java.lang.reflect.Array;

public class EntityEditor {

    public void ordersListChanged(){

    }

    public void receiptsListChanged(){

    }

    public void staffListChanged(){

    }

    public void fieldsDisabled(TextField nameField, TextField priceField, TextField amountField, TextField weightField, DatePicker dateChooser, ChoiceBox dishNameChooser, ChoiceBox orderIdChooser){
        nameField.setDisable(true);
        priceField.setDisable(true);
        amountField.setDisable(true);
        weightField.setDisable(true);
        dateChooser.setDisable(true);
        dishNameChooser.setDisable(true);
        orderIdChooser.setDisable(true);
    }

    public void fieldsClear(TextField nameField, TextField priceField, TextField amountField, TextField weightField){
        nameField.clear();
        priceField.clear();
        amountField.clear();
        weightField.clear();

    }
}
