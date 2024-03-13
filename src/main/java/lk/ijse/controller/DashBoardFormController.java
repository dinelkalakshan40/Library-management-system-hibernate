package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashBoardFormController {

    public Label lblUsername;
    public JFXButton btn_book;
    public AnchorPane arc_main;

    public void btnBookOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/view/book_form.fxml"));
        Parent load= fxmlLoader.load();
        BookFormController controller=fxmlLoader.getController();

        arc_main.getChildren().clear();
        arc_main.getChildren().add(load);


    }
}
