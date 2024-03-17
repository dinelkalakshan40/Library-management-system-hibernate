package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.entity.Transction;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionFormController implements Initializable {

    public TextField txt_trans_ID;
    public TextField txt_user;
    public TextField txtcontact;
    public DatePicker txtdate;
    public JFXComboBox<String> txtbranch_Id;
    public JFXComboBox<String> txtbook_id;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnDelete;
    public Button btnClear;
    public TableView<Transction> tblTrans;
    public TableColumn coltrans_Id;
    public TableColumn colbookUser;
    public TableColumn colbranchId;
    public TableColumn colbookId;
    public TableColumn colDate;
    public TableColumn colContact;

    TransactionBO transactionBO=(TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);
    TransactionDAO transactionDAO=(TransactionDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);

    BranchDAO branchDAO=(BranchDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

    BookDAO bookDAO=(BookDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    ObservableList<Transction> observableList;
    String ID;

    Branch branch =new Branch();

    Book book = new Book();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadBranchID();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        try {
            loadBookID();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        try {
            getAll();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setCellValueFactory();

        try {
            generateNextUserId();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
    private void loadBranchID() throws SQLException, IOException {
        List<String> id = transactionDAO.loadBranchID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        txtbranch_Id.setItems(obList);
    }
    private void loadBookID() throws SQLException, IOException {
        List<String> id = transactionDAO.loadBookID();
        ObservableList<String> obList = FXCollections.observableArrayList();

        for (String un : id){
            obList.add(un);
        }
        txtbook_id.setItems(obList);
    }
    private void getAll() throws Exception {
        observableList = FXCollections.observableArrayList();
        List<TransactionDto> allTransaction = transactionBO.getAllTransaction();

        for (TransactionDto transactionDto : allTransaction){
            observableList.add(new Transction(
                    transactionDto.getId(),
                    transactionDto.getUser(),
                    transactionDto.getBranch(),
                    transactionDto.getBook(),
                    transactionDto.getDate(),
                    transactionDto.getContact()));
        }
        tblTrans.setItems(observableList);
    }
    void setCellValueFactory(){
        coltrans_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colbookUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        colbranchId.setCellValueFactory(new PropertyValueFactory<>("branch"));
        colbookId.setCellValueFactory(new PropertyValueFactory<>("book"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
    private void generateNextUserId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = transactionBO.generateNewTransactionID();
        txt_trans_ID.setText(nextId);
    }




    public void btnAddOnAction(ActionEvent actionEvent) throws Exception {
        String id = txt_trans_ID.getText();
        String user=txt_user.getText();
        String branch_id = txtbranch_Id.getValue();
        String book_id = txtbook_id.getValue();
        String date = String.valueOf(txtdate.getValue());
        String contact = txtcontact.getText();


       branch.setId(branch_id);
       book.setId(book_id);

        if (transactionBO.saveTransaction(new TransactionDto(id, user, branch, book, date,contact))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }


        generateNextUserId();
        getAll();

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void btnClearOnAction(ActionEvent actionEvent) {

    }


}
