package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.BookBO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    public TextField book_id;
    public TextField title;
    public TextField author;
    public TextField Genre;
    public JFXComboBox status;
    public TableView tblbook;
    public TableColumn colBookid;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colStatus;

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

    public void btnClear(ActionEvent actionEvent) {
    }

    public void btnDelete(ActionEvent actionEvent) {

    }

    public void btnUpdate(ActionEvent actionEvent) {

    }
    public void btnAdd(ActionEvent actionEvent){

    }
}
