package lk.ijse.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    public boolean save(T entity) throws Exception;
    public boolean update(T entity) throws Exception;

    List<T> getAll() throws Exception;

}
