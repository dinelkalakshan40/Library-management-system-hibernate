package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDto;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {


    public Button forget_button;
    public Button sign_button;
    public Button login_button;
    public PasswordField passwordField;
    public TextField usernameTextField;

    UserDAO userDAO=(UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    private lk.ijse.dto.UserDto UserDto;
    private Object dto;

    boolean username,password,email,number ;

    public static String Gl0bUsrName;

    private void clearFields(){
            usernameTextField.setText("");
            passwordField.setText("");
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws Exception {
        String username = usernameTextField.getText();
        String password = passwordField.getText();

        if(username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Username or Password Empty").show();
            return;
        }

        if (userDAO.checkPassword(username,password)){
            Stage window = (Stage) this.login_button.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dash_board.fxml"))));
            window.centerOnScreen();


        }else {
            new Alert(Alert.AlertType.ERROR,"Please Check Username and password !!").show();
        }

        passwordField.clear();
        usernameTextField.clear();

    }

    /*public void btnLoginOnAction(ActionEvent actionEvent){
        if (username && password ){
            UserDto isUser=userBO.getAllUsers(new UserDto(usernameTextField.getText(),passwordField.getText()));
            if (isUser!=null){
                Gl0bUsrName=usernameTextField.getText();

                if (passwordField.getText().equals(isUser.getPassword())){
                    try {
                        Navigation.navigation(Rout.DASH_BOARD,root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else {
                    Validation.pwValidation(pwTxt);
                }
            }else {
                Validation.txtValidation(userNameTxt);
            }
        }
    }*/

    public void btnSignOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.sign_button.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/registration-form.fxml"))));
        window.centerOnScreen();




    }
    public void btnForgetOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.forget_button.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/forgot-password-form.fxml"))));
        window.centerOnScreen();
        
    }
}
