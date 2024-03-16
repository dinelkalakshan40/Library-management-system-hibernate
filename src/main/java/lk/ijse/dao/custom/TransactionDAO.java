package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.entity.Transction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface TransactionDAO extends CrudDAO<Transction> {

  //  boolean add(Transction entity) throws SQLException, ClassNotFoundException, IOException;

    String generateNewID() throws SQLException, ClassNotFoundException, IOException;

    List<String> loadBranchID() throws IOException;

    List<String> loadBookID() throws IOException;
}
