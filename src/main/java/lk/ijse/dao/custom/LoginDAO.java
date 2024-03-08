package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {

    boolean login(String username, String password) throws SQLException;
}
