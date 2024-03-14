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
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    public TextField book_id;
    public TextField title;
    public TextField author;
    public TextField Genre;
    public JFXComboBox<String> status;
    public TableView<Book> tblbook;
    public TableColumn<?,?> colBookid;
    public TableColumn<?,?> colTitle;
    public TableColumn<?,?> colAuthor;
    public TableColumn<?,?> colGenre;
    public TableColumn<?,?> colStatus;

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.BOOK);
    ObservableList<Book> observableList;

    String ID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("Available", "Non-Available");
        status.setItems(list);
        try {
            generateNextBookId();
        } catch (SQLException | IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setCellValueFactory();
    }
    private void generateNextBookId() throws SQLException, IOException, ClassNotFoundException {
        String nextId = bookBO.generateNewBookID();
        book_id.setText(nextId);
    }
    void setCellValueFactory(){
        colBookid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    private void getAll() throws Exception {
        observableList = FXCollections.observableArrayList();
        List<BookDto> allbook = bookBO.getAllBooks();

        for (BookDto bookDto : allbook){
            observableList.add(new Book(bookDto.getId(),bookDto.getTitle(),bookDto.getAuthor(),bookDto.getGenre(),bookDto.getStatus() ));
        }
        tblbook.setItems(observableList);
    }
    public void btnAdd(ActionEvent actionEvent) throws Exception {
        String id=book_id.getText();
        String Ttle =  title.getText();
        String Athor = author.getText();
        String Gnre = Genre.getText();
        String Stus = status.getValue();

        if (bookBO.saveBook(new BookDto(id, Ttle, Athor,Gnre,Stus))) {
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearTextFileds();
        generateNextBookId();
        getAll();


    }
    public void clearTextFileds(){
      //  book_id.clear();
        title.clear();
        author.clear();
        Genre.clear();
    }

    public void btnClear(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        generateNextBookId();
        clearTextFileds();
    }

    public void btnDelete(ActionEvent actionEvent) throws Exception {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();


        if (result.orElse(no) == yes) {
            if (!bookBO.deleteBook(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        generateNextBookId();
        clearTextFileds();
        getAll();

    }

    public void btnUpdate(ActionEvent actionEvent) throws Exception {
       // String id=book_id.getText();
        String Ttle =  title.getText();
        String Athor = author.getText();
        String Gnre = Genre.getText();
        String Stus = status.getValue();

        if(bookBO.updateBook(new BookDto(ID,Ttle,Athor,Gnre,Stus))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }

        generateNextBookId();
        clearTextFileds();
        getAll();

    }
    public void rowOnMouseClicked(MouseEvent mouseEvent) {
        Integer index = tblbook.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        ID = colBookid.getCellData(index).toString();
        book_id.setText(colBookid.getCellData(index).toString());
        title.setText(colTitle.getCellData(index).toString());
        author.setText(colAuthor.getCellData(index).toString());
        Genre.setText(colGenre.getCellData(index).toString());
        status.setValue(colStatus.getCellData(index).toString());
    }

}
