package sample.SCRUD;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Entities.Products;

import java.lang.reflect.Array;

public class EntityEditor {
    private Products product;

    public void productListChanged(TableView table, ObservableList<Products> productsList, int num){

            //заменить на изменение через поля
       // int trow = ((Products) table.getItems().get(num)).getId();
       // String name = ((Products)table.getItems().get(num)).getName();
       // double price = ((Products) table.getItems().get(num)).getPrice();
      //  int amount = ((Products) table.getItems().get(num)).getAmount();

            //System.out.println(trow + " " + name + " " + price + " " + amount);



      /*for (int row = 0; row < Products.getCount(); row++){
          int trow = ((Products) table.getItems().get(row)).getId();

          // System.out.println("_____ " + trow.getId());
         //  product = new Products(trow.getId(), trow.getName(), trow.getPrice(), trow.getAmount());
         //  productsList.add(product);
           //table.getSelectionModel().select(row);
          System.out.println(trow);
            //String string = String.join(";", product.toString());
        }
      //  System.out.println(productsList);
      //  System.out.println(trow);
        //System.out.println();*/
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
