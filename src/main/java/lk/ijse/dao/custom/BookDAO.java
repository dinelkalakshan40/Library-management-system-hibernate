package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Book;

import java.io.IOException;

public interface BookDAO extends CrudDAO<Book> {


    String generateNewID() throws IOException;
}
