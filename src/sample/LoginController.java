package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;

public class LoginController {
    private static Stage stage;

    @FXML
    public TextField email_tf;

    @FXML
    public PasswordField password_tf;

    @FXML
    public Text failure_txt;

    public static void setStage(Stage st){
        stage = st;
    }
    public static Stage getStage(){
        return stage;
    }

    @FXML
    public void getCredentials() throws IOException {
        if (email_tf.getText().equals("admin") && password_tf.getText().equals("admin")){
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("sample.fxml"));

            stage.setTitle("Restaurant");
            stage.setScene(new Scene(fxmlLoader.load(), 1200, 900));
            stage.show();
        } else {
            failure_txt.setVisible(true);
        }

    }

    @FXML
    public void keyPressed(KeyEvent keyEvent) throws IOException {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            getCredentials();
        }
    }
}
