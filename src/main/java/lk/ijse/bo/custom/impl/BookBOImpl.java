package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO {

    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.BOOK);

    @Override
    public List<BookDto> getAllBooks() throws Exception {
        List<BookDto> allBooks= new ArrayList<>();
        List<Book> all = bookDAO.getAll();
        for (Book book : all) {
           allBooks .add(new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(),book.getStatus()));
        }
        return allBooks;
    }

    @Override
    public boolean saveBook(BookDto dto) throws Exception {
        return bookDAO.save(new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(),dto.getStatus()));
    }

    @Override
    public boolean updateBook(BookDto dto) throws Exception {
        return bookDAO.update(new Book(dto.getId(), dto.getTitle(), dto.getAuthor(), dto.getGenre(),dto.getStatus()));
    }

    @Override
    public boolean deleteBook(String id) throws SQLException, IOException, ClassNotFoundException {
        return bookDAO.delete(id);
    }

    @Override
    public String generateNewBookID() throws SQLException, IOException, ClassNotFoundException {
        return bookDAO.generateNewID();
    }

}
