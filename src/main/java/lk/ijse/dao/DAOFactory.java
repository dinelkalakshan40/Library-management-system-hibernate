package lk.ijse.dao;

import lk.ijse.dao.custom.impl.LoginDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return(daoFactory== null) ? daoFactory =new DAOFactory(): daoFactory;
    }

    public enum DAOTypes{
        REGISTER, LOGIN
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }

}
