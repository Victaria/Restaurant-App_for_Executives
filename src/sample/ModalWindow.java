package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.text.TabableView;
import javax.swing.text.TableView;
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class ModalWindow {


    @FXML
    private TableView tableView;

    @FXML
    private void initialize() {
        tableView.setSize(450, 450);
    }

    public static void newWindow(String title) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ModalWindow.class.getResource("ModalWindow.fxml"));

       // AnchorPane page = (AnchorPane) loader.load();


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Pane pane = new Pane();

        //Button exitBtn = new Button("close");
        //exitBtn.addActionListener(event -> window.close());


        Scene scene = new Scene(pane, 600, 600);
        window.setScene(scene);

        window.setTitle(title);
        window.showAndWait();
    }
}
