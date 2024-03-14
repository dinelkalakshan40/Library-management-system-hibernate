package lk.ijse.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    public boolean save(T entity) throws Exception;
    public boolean update(T entity) throws Exception;

    public boolean delete(String id) throws SQLException, ClassNotFoundException, IOException;

    public List<T> getAll() throws Exception;

}
