package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        URL url = Paths.get("./src/sample/login/login.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);

        LoginController.setStage(primaryStage);
        primaryStage.setTitle("Login");
         primaryStage.setScene(new Scene(root, 1200, 900));
         primaryStage.show();
        /*Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Restaurant");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();*/
    }


    public static void main(String[] args) {
        launch();
    }
}
