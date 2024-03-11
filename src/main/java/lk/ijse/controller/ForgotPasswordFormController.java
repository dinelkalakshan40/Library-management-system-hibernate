package lk.ijse.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;

public class ForgotPasswordFormController {


    public TextField R_username;
    public TextField R_password;
    public TextField R_confirmpassword;
    public JFXButton back_Loginpage;


    UserBO userBO=(UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    public void btnResetOnAction(ActionEvent actionEvent) throws Exception {
        String name = R_username.getText();
        String password = R_password.getText();
        String CofirmPasswrd =R_confirmpassword.getText();

        if(name.isEmpty() || password.isEmpty() || CofirmPasswrd.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Fill all Filed").show();
            return;
        }


        if (password.equalsIgnoreCase(R_confirmpassword.getText())) {
            if(userBO.updateUser(new UserDto(name,password))){
                new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Don't match Passwords!!").show();
        }
        R_username.clear();
        R_password.clear();
        R_confirmpassword.clear();


    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.back_Loginpage.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"))));
        window.centerOnScreen();
    }

}
