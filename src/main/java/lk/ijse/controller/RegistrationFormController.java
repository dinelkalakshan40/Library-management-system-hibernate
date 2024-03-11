package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.dto.UserDto;

import java.io.IOException;

public class RegistrationFormController {
    public TextField Username;
    public TextField Email;

    public TextField Number;
    public PasswordField Password;
    public PasswordField Confirm_Password;

    public ImageView conformRight;
    public ImageView wrongID;
    public JFXButton login_to_lognpage;
    public JFXButton reset_btn;

    boolean usr,pw,rePw,email,tel;

    UserBO userBO=(UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);



    public void SubmitOnAction(ActionEvent actionEvent) throws Exception {


        String name=Username.getText();

        String password=Password.getText();

        String ConfirmPassword=Confirm_Password.getText();

        if(name.isEmpty() || password.isEmpty() || ConfirmPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Fill all Filed").show();
            return;
        }

        if (Password.getText().equalsIgnoreCase(Confirm_Password.getText())){
            boolean isSaved=userBO.saveUser(new UserDto(name,password));
                if (isSaved){
                    new Alert(Alert.AlertType.CONFIRMATION,"saved").show();
                }

        }else {
                new Alert(Alert.AlertType.ERROR).show();
        }
        Username.clear();
        Password.clear();
        Confirm_Password.clear();


        /*UserDto userDto=new UserDto(name,password);
        try {
            boolean isAdded= userBO.    saveUser(userDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Create Account Successful !", ButtonType.OK).show();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Account Not created Successful!", ButtonType.OK).show();
        }*/


    }

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.login_to_lognpage.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"))));
        window.centerOnScreen();

    }

    public void resetOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) this.login_to_lognpage.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/forgot-password-form.fxml"))));
        window.centerOnScreen();
    }
}
