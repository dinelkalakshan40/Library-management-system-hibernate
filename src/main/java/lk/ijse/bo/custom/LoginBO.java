package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    public boolean login(String username, String password) throws SQLException, ClassNotFoundException;

}
