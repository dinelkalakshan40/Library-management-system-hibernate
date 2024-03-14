package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.BookDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BookBO extends SuperBO {


    List<BookDto> getAllBooks() throws Exception;

    boolean saveBook(BookDto dto) throws Exception;

    boolean updateBook(BookDto dto) throws Exception;

    boolean deleteBook(String id) throws SQLException, IOException, ClassNotFoundException;

    String generateNewBookID() throws SQLException, IOException, ClassNotFoundException;
}
