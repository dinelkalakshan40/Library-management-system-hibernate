package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BranchBO;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.BranchDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BranchFormController implements Initializable {


    public TextField txtbranch_Id;
    public TextField txtbranch_Name;
    public TextField txtbranch_Manger;
    public TextField txtContact;
    public TableView<Branch> tblbranch;
    public TableColumn<?,?> colbranch_id;
    public TableColumn<?,?> colbranch_Name;
    public TableColumn<?,?> colManager;
    public TableColumn<?,?> colContact;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXButton btnClear;

    BranchBO branchBO=(BranchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    ObservableList<Branch> observableList;

    String ID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
        try {
            generateNextBranchId();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void getAll() throws Exception {
        observableList = FXCollections.observableArrayList();
        List<BranchDto> allBranches = branchBO.getAllBranches();

        for (BranchDto branchDto : allBranches){
            observableList.add(new Branch(branchDto.getId(),branchDto.getBranch_name(),branchDto.getManager(),branchDto.getContact() ));
        }
        tblbranch.setItems(observableList);
    }
    private void generateNextBranchId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = branchBO.generateNewBranchID();
        txtbranch_Id.setText(nextId);
    }
    void setCellValueFactory(){
        colbranch_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colbranch_Name.setCellValueFactory(new PropertyValueFactory<>("branch_name"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("Manager"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
        String id=txtbranch_Id.getText();
        String name =  txtbranch_Name.getText();
        String Manager = txtbranch_Manger.getText();
        String contact = txtContact.getText();


        if (branchBO.saveBranch(new BranchDto(id, name, Manager,contact))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearTextFileds();
        generateNextBranchId();
        getAll();

    }
    public void clearTextFileds(){
        txtbranch_Name.clear();
        txtbranch_Manger.clear();
        txtContact.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        String name =  txtbranch_Name.getText();
        String Manger = txtbranch_Manger.getText();
        String contact = txtContact.getText();


        if(branchBO.updateBranch(new BranchDto(ID,name,Manger,contact))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        generateNextBranchId();
        clearTextFileds();
        getAll();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!branchBO.deleteBranch(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextBranchId();
        clearTextFileds();
        getAll();

    }

    public void btnClearOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        generateNextBranchId();
        clearTextFileds();
    }
    public void rowOnMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblbranch.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = colbranch_id.getCellData(index).toString();
        txtbranch_Id.setText(colbranch_id.getCellData(index).toString());
        txtbranch_Manger.setText(colManager.getCellData(index).toString());
        txtbranch_Name.setText(colbranch_Name.getCellData(index).toString());
        txtContact.setText(colContact.getCellData(index).toString());

    }
}
