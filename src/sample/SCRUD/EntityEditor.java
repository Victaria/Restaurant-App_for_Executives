package sample.SCRUD;

import javafx.scene.control.*;
import javafx.scene.control.TextField;

public class EntityEditor {

    public void fieldsDisabled(TextField nameField, TextField priceField, TextField amountField, TextField weightField, DatePicker dateChooser, ChoiceBox dishNameChooser, ChoiceBox orderIdChooser, ChoiceBox tableChooser, ChoiceBox staffNameChooser, ChoiceBox productNameChooser){
        nameField.setDisable(true);
        priceField.setDisable(true);
        amountField.setDisable(true);
        weightField.setDisable(true);
        dateChooser.setDisable(true);
        dishNameChooser.setDisable(true);
        orderIdChooser.setDisable(true);
        tableChooser.setDisable(true);
        staffNameChooser.setDisable(true);
        productNameChooser.setDisable(true);
    }

    public void fieldsClear(TextField nameField, TextField priceField, TextField amountField, TextField weightField, ChoiceBox dishNameChooser, ChoiceBox orderIdChooser, ChoiceBox tableChooser, ChoiceBox staffNameChooser, ChoiceBox productNameChooser){
        nameField.clear();
        priceField.clear();
        amountField.clear();
        weightField.clear();
        tableChooser.getItems().removeAll(tableChooser.getItems());
        staffNameChooser.getItems().removeAll(staffNameChooser.getItems());
        dishNameChooser.getItems().removeAll(dishNameChooser.getItems());
        orderIdChooser.getItems().removeAll(orderIdChooser.getItems());
        productNameChooser.getItems().removeAll(productNameChooser.getItems());
    }
}
