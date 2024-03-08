package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.LoginBO;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {


    public Button forget_button;
    public Button sign_button;
    public Button login_button;
    public PasswordField passwordField;
    public TextField usernameTextField;

    LoginBO loginBO=(LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    private void clearFields(){
            usernameTextField.setText("");
            passwordField.setText("");
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String username=usernameTextField.getText();
        String password=passwordField.getText();
        try {
            boolean login = loginBO.login(username, password);
            if (username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Empty").show();
                return;
            }
            if (login) {
                AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/dash_board.fxml"));
                Scene scene = new Scene(anchorPane);
                Stage stage = (Stage) this.login_button.getScene().getWindow();
                stage.setScene(scene);
                stage.centerOnScreen();
            } else {
                new Alert(Alert.AlertType.ERROR, "oops! credentials are wrong!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage());
            clearFields();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnSignOnAction(ActionEvent actionEvent) {

    }
    public void btnForgetOnAction(ActionEvent actionEvent){
        
    }
}
