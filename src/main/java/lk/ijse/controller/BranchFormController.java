package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import java.util.ResourceBundle;

public class BranchFormController implements Initializable {


    public TextField txtbranch_Id;
    public TextField txtbranch_Name;
    public TextField txtbranch_Manger;
    public TextField txtContact;
    public TableView<Branch> tblbranch;
    public TableColumn colbranch_id;
    public TableColumn colbranch_Name;
    public TableColumn colManager;
    public TableColumn colContact;
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
        colbranch_Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colManager.setCellValueFactory(new PropertyValueFactory<>("Manager"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));

    }

    public void btnAddOnAction(ActionEvent actionEvent) {



    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }
}
