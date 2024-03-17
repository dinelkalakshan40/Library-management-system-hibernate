package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.TransactionBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.BranchDAO;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.TransactionDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.entity.Transction;
import lk.ijse.entity.tm.TransactionTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
    public TableView<TransactionTM> tblTrans;
    public TableColumn<?,?> coltrans_Id;
    public TableColumn<?,?> colbookUser;
    public TableColumn<?,?> colbranchId;
    public TableColumn<?,?> colbookId;
    public TableColumn<?,?> colDate;
    public TableColumn<?,?> colContact;

    TransactionBO transactionBO=(TransactionBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.TRANSACTION);
    TransactionDAO transactionDAO=(TransactionDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.TRANSACTION);

   // BranchDAO branchDAO=(BranchDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BRANCH);

  //  BookDAO bookDAO=(BookDAO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);

    ObservableList<TransactionTM> observableList;
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
            generateNextTransactionId();
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
            observableList.add(new TransactionTM(
                    transactionDto.getId(),
                    transactionDto.getUser(),
                    transactionDto.getBranch().getId(),
                    transactionDto.getBook().getId(),
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
    private void generateNextTransactionId() throws SQLException, IOException, ClassNotFoundException {
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


        generateNextTransactionId();
        getAll();
        clearFildes();

    }
    public void clearFildes(){
            txt_user.clear();
            txtbranch_Id.setValue(null);
            txtbook_id.setValue(null);
            txtdate.setValue(null);
            txtcontact.clear();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws Exception {
        //String id = txt_trans_ID.getText();
        String user = txt_user.getText();
        String branch_id= txtbranch_Id.getValue();
        String booK_id= txtbook_id.getValue();
        String date = String.valueOf(txtdate.getValue());
        String contact = txtcontact.getText();

        branch.setId(branch_id);
        book.setId(booK_id);

        if(transactionBO.updateTransaction(new TransactionDto(ID,user,branch,book,date,contact))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        clearFildes();
        generateNextTransactionId();
        getAll();


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws Exception {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!transactionBO.deleteTransaction(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextTransactionId();
        clearFildes();
        getAll();

    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFildes();
    }
    public void rowOnMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblTrans.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = coltrans_Id.getCellData(index).toString();
        txt_trans_ID.setText(coltrans_Id.getCellData(index).toString());
        txt_user.setText(colbookUser.getCellData(index).toString());
        txtbranch_Id.setValue(colbranchId.getCellData(index).toString());
        txtbook_id.setValue(colContact.getCellData(index).toString());
        txtdate.setValue(LocalDate.parse(colDate.getCellData(index).toString()));
        txtcontact.setText(colContact.getCellData(index).toString());

    }


}
